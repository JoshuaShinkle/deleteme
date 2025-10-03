package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.math.IntMath;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.j2objc.annotations.Weak;
import java.util.AbstractQueue;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

@Beta
@GwtCompatible
/* loaded from: classes2.dex */
public final class MinMaxPriorityQueue<E> extends AbstractQueue<E> {
    private static final int DEFAULT_CAPACITY = 11;
    private static final int EVEN_POWERS_OF_TWO = 1431655765;
    private static final int ODD_POWERS_OF_TWO = -1431655766;
    private final MinMaxPriorityQueue<E>.Heap maxHeap;

    @VisibleForTesting
    final int maximumSize;
    private final MinMaxPriorityQueue<E>.Heap minHeap;
    private int modCount;
    private Object[] queue;
    private int size;

    @Beta
    public static final class Builder<B> {
        private static final int UNSET_EXPECTED_SIZE = -1;
        private final Comparator<B> comparator;
        private int expectedSize;
        private int maximumSize;

        /* JADX INFO: Access modifiers changed from: private */
        public <T extends B> Ordering<T> ordering() {
            return Ordering.from(this.comparator);
        }

        public <T extends B> MinMaxPriorityQueue<T> create() {
            return create(Collections.emptySet());
        }

        @CanIgnoreReturnValue
        public Builder<B> expectedSize(int i9) {
            Preconditions.checkArgument(i9 >= 0);
            this.expectedSize = i9;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<B> maximumSize(int i9) {
            Preconditions.checkArgument(i9 > 0);
            this.maximumSize = i9;
            return this;
        }

        private Builder(Comparator<B> comparator) {
            this.expectedSize = -1;
            this.maximumSize = Integer.MAX_VALUE;
            this.comparator = (Comparator) Preconditions.checkNotNull(comparator);
        }

        public <T extends B> MinMaxPriorityQueue<T> create(Iterable<? extends T> iterable) {
            MinMaxPriorityQueue<T> minMaxPriorityQueue = new MinMaxPriorityQueue<>(this, MinMaxPriorityQueue.initialQueueSize(this.expectedSize, this.maximumSize, iterable));
            Iterator<? extends T> it = iterable.iterator();
            while (it.hasNext()) {
                minMaxPriorityQueue.offer(it.next());
            }
            return minMaxPriorityQueue;
        }
    }

    public class Heap {
        final Ordering<E> ordering;

        @Weak
        MinMaxPriorityQueue<E>.Heap otherHeap;

        public Heap(Ordering<E> ordering) {
            this.ordering = ordering;
        }

        private int getGrandparentIndex(int i9) {
            return getParentIndex(getParentIndex(i9));
        }

        private int getLeftChildIndex(int i9) {
            return (i9 * 2) + 1;
        }

        private int getParentIndex(int i9) {
            return (i9 - 1) / 2;
        }

        private int getRightChildIndex(int i9) {
            return (i9 * 2) + 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean verifyIndex(int i9) {
            if (getLeftChildIndex(i9) < MinMaxPriorityQueue.this.size && compareElements(i9, getLeftChildIndex(i9)) > 0) {
                return false;
            }
            if (getRightChildIndex(i9) < MinMaxPriorityQueue.this.size && compareElements(i9, getRightChildIndex(i9)) > 0) {
                return false;
            }
            if (i9 <= 0 || compareElements(i9, getParentIndex(i9)) <= 0) {
                return i9 <= 2 || compareElements(getGrandparentIndex(i9), i9) <= 0;
            }
            return false;
        }

        public void bubbleUp(int i9, E e9) {
            Heap heap;
            int iCrossOverUp = crossOverUp(i9, e9);
            if (iCrossOverUp == i9) {
                iCrossOverUp = i9;
                heap = this;
            } else {
                heap = this.otherHeap;
            }
            heap.bubbleUpAlternatingLevels(iCrossOverUp, e9);
        }

        @CanIgnoreReturnValue
        public int bubbleUpAlternatingLevels(int i9, E e9) {
            while (i9 > 2) {
                int grandparentIndex = getGrandparentIndex(i9);
                Object objElementData = MinMaxPriorityQueue.this.elementData(grandparentIndex);
                if (this.ordering.compare(objElementData, e9) <= 0) {
                    break;
                }
                MinMaxPriorityQueue.this.queue[i9] = objElementData;
                i9 = grandparentIndex;
            }
            MinMaxPriorityQueue.this.queue[i9] = e9;
            return i9;
        }

        public int compareElements(int i9, int i10) {
            return this.ordering.compare(MinMaxPriorityQueue.this.elementData(i9), MinMaxPriorityQueue.this.elementData(i10));
        }

        public int crossOver(int i9, E e9) {
            int iFindMinChild = findMinChild(i9);
            if (iFindMinChild <= 0 || this.ordering.compare(MinMaxPriorityQueue.this.elementData(iFindMinChild), e9) >= 0) {
                return crossOverUp(i9, e9);
            }
            MinMaxPriorityQueue.this.queue[i9] = MinMaxPriorityQueue.this.elementData(iFindMinChild);
            MinMaxPriorityQueue.this.queue[iFindMinChild] = e9;
            return iFindMinChild;
        }

        public int crossOverUp(int i9, E e9) {
            int rightChildIndex;
            if (i9 == 0) {
                MinMaxPriorityQueue.this.queue[0] = e9;
                return 0;
            }
            int parentIndex = getParentIndex(i9);
            Object objElementData = MinMaxPriorityQueue.this.elementData(parentIndex);
            if (parentIndex != 0 && (rightChildIndex = getRightChildIndex(getParentIndex(parentIndex))) != parentIndex && getLeftChildIndex(rightChildIndex) >= MinMaxPriorityQueue.this.size) {
                Object objElementData2 = MinMaxPriorityQueue.this.elementData(rightChildIndex);
                if (this.ordering.compare(objElementData2, objElementData) < 0) {
                    parentIndex = rightChildIndex;
                    objElementData = objElementData2;
                }
            }
            if (this.ordering.compare(objElementData, e9) >= 0) {
                MinMaxPriorityQueue.this.queue[i9] = e9;
                return i9;
            }
            MinMaxPriorityQueue.this.queue[i9] = objElementData;
            MinMaxPriorityQueue.this.queue[parentIndex] = e9;
            return parentIndex;
        }

        public int fillHoleAt(int i9) {
            while (true) {
                int iFindMinGrandChild = findMinGrandChild(i9);
                if (iFindMinGrandChild <= 0) {
                    return i9;
                }
                MinMaxPriorityQueue.this.queue[i9] = MinMaxPriorityQueue.this.elementData(iFindMinGrandChild);
                i9 = iFindMinGrandChild;
            }
        }

        public int findMin(int i9, int i10) {
            if (i9 >= MinMaxPriorityQueue.this.size) {
                return -1;
            }
            Preconditions.checkState(i9 > 0);
            int iMin = Math.min(i9, MinMaxPriorityQueue.this.size - i10) + i10;
            for (int i11 = i9 + 1; i11 < iMin; i11++) {
                if (compareElements(i11, i9) < 0) {
                    i9 = i11;
                }
            }
            return i9;
        }

        public int findMinChild(int i9) {
            return findMin(getLeftChildIndex(i9), 2);
        }

        public int findMinGrandChild(int i9) {
            int leftChildIndex = getLeftChildIndex(i9);
            if (leftChildIndex < 0) {
                return -1;
            }
            return findMin(getLeftChildIndex(leftChildIndex), 4);
        }

        public int swapWithConceptuallyLastElement(E e9) {
            int rightChildIndex;
            int parentIndex = getParentIndex(MinMaxPriorityQueue.this.size);
            if (parentIndex != 0 && (rightChildIndex = getRightChildIndex(getParentIndex(parentIndex))) != parentIndex && getLeftChildIndex(rightChildIndex) >= MinMaxPriorityQueue.this.size) {
                Object objElementData = MinMaxPriorityQueue.this.elementData(rightChildIndex);
                if (this.ordering.compare(objElementData, e9) < 0) {
                    MinMaxPriorityQueue.this.queue[rightChildIndex] = e9;
                    MinMaxPriorityQueue.this.queue[MinMaxPriorityQueue.this.size] = objElementData;
                    return rightChildIndex;
                }
            }
            return MinMaxPriorityQueue.this.size;
        }

        public MoveDesc<E> tryCrossOverAndBubbleUp(int i9, int i10, E e9) {
            int iCrossOver = crossOver(i10, e9);
            if (iCrossOver == i10) {
                return null;
            }
            Object objElementData = iCrossOver < i9 ? MinMaxPriorityQueue.this.elementData(i9) : MinMaxPriorityQueue.this.elementData(getParentIndex(i9));
            if (this.otherHeap.bubbleUpAlternatingLevels(iCrossOver, e9) < i9) {
                return new MoveDesc<>(e9, objElementData);
            }
            return null;
        }
    }

    public static class MoveDesc<E> {
        final E replaced;
        final E toTrickle;

        public MoveDesc(E e9, E e10) {
            this.toTrickle = e9;
            this.replaced = e10;
        }
    }

    public class QueueIterator implements Iterator<E> {
        private boolean canRemove;
        private int cursor;
        private int expectedModCount;
        private Queue<E> forgetMeNot;
        private E lastFromForgetMeNot;
        private int nextCursor;
        private List<E> skipMe;

        private QueueIterator() {
            this.cursor = -1;
            this.nextCursor = -1;
            this.expectedModCount = MinMaxPriorityQueue.this.modCount;
        }

        private void checkModCount() {
            if (MinMaxPriorityQueue.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }

        private boolean foundAndRemovedExactReference(Iterable<E> iterable, E e9) {
            Iterator<E> it = iterable.iterator();
            while (it.hasNext()) {
                if (it.next() == e9) {
                    it.remove();
                    return true;
                }
            }
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private void nextNotInSkipMe(int i9) {
            if (this.nextCursor < i9) {
                if (this.skipMe != null) {
                    while (i9 < MinMaxPriorityQueue.this.size() && foundAndRemovedExactReference(this.skipMe, MinMaxPriorityQueue.this.elementData(i9))) {
                        i9++;
                    }
                }
                this.nextCursor = i9;
            }
        }

        private boolean removeExact(Object obj) {
            for (int i9 = 0; i9 < MinMaxPriorityQueue.this.size; i9++) {
                if (MinMaxPriorityQueue.this.queue[i9] == obj) {
                    MinMaxPriorityQueue.this.removeAt(i9);
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            checkModCount();
            nextNotInSkipMe(this.cursor + 1);
            if (this.nextCursor < MinMaxPriorityQueue.this.size()) {
                return true;
            }
            Queue<E> queue = this.forgetMeNot;
            return (queue == null || queue.isEmpty()) ? false : true;
        }

        @Override // java.util.Iterator
        public E next() {
            checkModCount();
            nextNotInSkipMe(this.cursor + 1);
            if (this.nextCursor < MinMaxPriorityQueue.this.size()) {
                int i9 = this.nextCursor;
                this.cursor = i9;
                this.canRemove = true;
                return (E) MinMaxPriorityQueue.this.elementData(i9);
            }
            if (this.forgetMeNot != null) {
                this.cursor = MinMaxPriorityQueue.this.size();
                E ePoll = this.forgetMeNot.poll();
                this.lastFromForgetMeNot = ePoll;
                if (ePoll != null) {
                    this.canRemove = true;
                    return ePoll;
                }
            }
            throw new NoSuchElementException("iterator moved past last element in queue.");
        }

        @Override // java.util.Iterator
        public void remove() {
            CollectPreconditions.checkRemove(this.canRemove);
            checkModCount();
            this.canRemove = false;
            this.expectedModCount++;
            if (this.cursor >= MinMaxPriorityQueue.this.size()) {
                Preconditions.checkState(removeExact(this.lastFromForgetMeNot));
                this.lastFromForgetMeNot = null;
                return;
            }
            MoveDesc<E> moveDescRemoveAt = MinMaxPriorityQueue.this.removeAt(this.cursor);
            if (moveDescRemoveAt != null) {
                if (this.forgetMeNot == null) {
                    this.forgetMeNot = new ArrayDeque();
                    this.skipMe = new ArrayList(3);
                }
                if (!foundAndRemovedExactReference(this.skipMe, moveDescRemoveAt.toTrickle)) {
                    this.forgetMeNot.add(moveDescRemoveAt.toTrickle);
                }
                if (!foundAndRemovedExactReference(this.forgetMeNot, moveDescRemoveAt.replaced)) {
                    this.skipMe.add(moveDescRemoveAt.replaced);
                }
            }
            this.cursor--;
            this.nextCursor--;
        }
    }

    private int calculateNewCapacity() {
        int length = this.queue.length;
        return capAtMaximumSize(length < 64 ? (length + 1) * 2 : IntMath.checkedMultiply(length / 2, 3), this.maximumSize);
    }

    private static int capAtMaximumSize(int i9, int i10) {
        return Math.min(i9 - 1, i10) + 1;
    }

    public static <E extends Comparable<E>> MinMaxPriorityQueue<E> create() {
        return new Builder(Ordering.natural()).create();
    }

    public static Builder<Comparable> expectedSize(int i9) {
        return new Builder(Ordering.natural()).expectedSize(i9);
    }

    private MoveDesc<E> fillHole(int i9, E e9) {
        MinMaxPriorityQueue<E>.Heap heapHeapForIndex = heapForIndex(i9);
        int iFillHoleAt = heapHeapForIndex.fillHoleAt(i9);
        int iBubbleUpAlternatingLevels = heapHeapForIndex.bubbleUpAlternatingLevels(iFillHoleAt, e9);
        if (iBubbleUpAlternatingLevels == iFillHoleAt) {
            return heapHeapForIndex.tryCrossOverAndBubbleUp(i9, iFillHoleAt, e9);
        }
        if (iBubbleUpAlternatingLevels < i9) {
            return new MoveDesc<>(e9, elementData(i9));
        }
        return null;
    }

    private int getMaxElementIndex() {
        int i9 = this.size;
        if (i9 != 1) {
            return (i9 == 2 || this.maxHeap.compareElements(1, 2) <= 0) ? 1 : 2;
        }
        return 0;
    }

    private void growIfNeeded() {
        if (this.size > this.queue.length) {
            Object[] objArr = new Object[calculateNewCapacity()];
            Object[] objArr2 = this.queue;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.queue = objArr;
        }
    }

    private MinMaxPriorityQueue<E>.Heap heapForIndex(int i9) {
        return isEvenLevel(i9) ? this.minHeap : this.maxHeap;
    }

    @VisibleForTesting
    public static int initialQueueSize(int i9, int i10, Iterable<?> iterable) {
        if (i9 == -1) {
            i9 = 11;
        }
        if (iterable instanceof Collection) {
            i9 = Math.max(i9, ((Collection) iterable).size());
        }
        return capAtMaximumSize(i9, i10);
    }

    @VisibleForTesting
    public static boolean isEvenLevel(int i9) {
        int i10 = ~(~(i9 + 1));
        Preconditions.checkState(i10 > 0, "negative index");
        return (EVEN_POWERS_OF_TWO & i10) > (i10 & ODD_POWERS_OF_TWO);
    }

    public static Builder<Comparable> maximumSize(int i9) {
        return new Builder(Ordering.natural()).maximumSize(i9);
    }

    public static <B> Builder<B> orderedBy(Comparator<B> comparator) {
        return new Builder<>(comparator);
    }

    private E removeAndGet(int i9) {
        E eElementData = elementData(i9);
        removeAt(i9);
        return eElementData;
    }

    @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection, java.util.Queue
    @CanIgnoreReturnValue
    public boolean add(E e9) {
        offer(e9);
        return true;
    }

    @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection
    @CanIgnoreReturnValue
    public boolean addAll(Collection<? extends E> collection) {
        Iterator<? extends E> it = collection.iterator();
        boolean z8 = false;
        while (it.hasNext()) {
            offer(it.next());
            z8 = true;
        }
        return z8;
    }

    @VisibleForTesting
    public int capacity() {
        return this.queue.length;
    }

    @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection
    public void clear() {
        for (int i9 = 0; i9 < this.size; i9++) {
            this.queue[i9] = null;
        }
        this.size = 0;
    }

    public Comparator<? super E> comparator() {
        return this.minHeap.ordering;
    }

    public E elementData(int i9) {
        return (E) this.queue[i9];
    }

    @VisibleForTesting
    public boolean isIntact() {
        for (int i9 = 1; i9 < this.size; i9++) {
            if (!heapForIndex(i9).verifyIndex(i9)) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return new QueueIterator();
    }

    @Override // java.util.Queue
    @CanIgnoreReturnValue
    public boolean offer(E e9) {
        Preconditions.checkNotNull(e9);
        this.modCount++;
        int i9 = this.size;
        this.size = i9 + 1;
        growIfNeeded();
        heapForIndex(i9).bubbleUp(i9, e9);
        return this.size <= this.maximumSize || pollLast() != e9;
    }

    @Override // java.util.Queue
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return elementData(0);
    }

    public E peekFirst() {
        return peek();
    }

    public E peekLast() {
        if (isEmpty()) {
            return null;
        }
        return elementData(getMaxElementIndex());
    }

    @Override // java.util.Queue
    @CanIgnoreReturnValue
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        return removeAndGet(0);
    }

    @CanIgnoreReturnValue
    public E pollFirst() {
        return poll();
    }

    @CanIgnoreReturnValue
    public E pollLast() {
        if (isEmpty()) {
            return null;
        }
        return removeAndGet(getMaxElementIndex());
    }

    @VisibleForTesting
    @CanIgnoreReturnValue
    public MoveDesc<E> removeAt(int i9) {
        Preconditions.checkPositionIndex(i9, this.size);
        this.modCount++;
        int i10 = this.size - 1;
        this.size = i10;
        if (i10 == i9) {
            this.queue[i10] = null;
            return null;
        }
        E eElementData = elementData(i10);
        int iSwapWithConceptuallyLastElement = heapForIndex(this.size).swapWithConceptuallyLastElement(eElementData);
        if (iSwapWithConceptuallyLastElement == i9) {
            this.queue[this.size] = null;
            return null;
        }
        E eElementData2 = elementData(this.size);
        this.queue[this.size] = null;
        MoveDesc<E> moveDescFillHole = fillHole(i9, eElementData2);
        return iSwapWithConceptuallyLastElement < i9 ? moveDescFillHole == null ? new MoveDesc<>(eElementData, eElementData2) : new MoveDesc<>(eElementData, moveDescFillHole.replaced) : moveDescFillHole;
    }

    @CanIgnoreReturnValue
    public E removeFirst() {
        return remove();
    }

    @CanIgnoreReturnValue
    public E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return removeAndGet(getMaxElementIndex());
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public int size() {
        return this.size;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public Object[] toArray() {
        int i9 = this.size;
        Object[] objArr = new Object[i9];
        System.arraycopy(this.queue, 0, objArr, 0, i9);
        return objArr;
    }

    private MinMaxPriorityQueue(Builder<? super E> builder, int i9) {
        Ordering ordering = builder.ordering();
        MinMaxPriorityQueue<E>.Heap heap = new Heap(ordering);
        this.minHeap = heap;
        MinMaxPriorityQueue<E>.Heap heap2 = new Heap(ordering.reverse());
        this.maxHeap = heap2;
        heap.otherHeap = heap2;
        heap2.otherHeap = heap;
        this.maximumSize = ((Builder) builder).maximumSize;
        this.queue = new Object[i9];
    }

    public static <E extends Comparable<E>> MinMaxPriorityQueue<E> create(Iterable<? extends E> iterable) {
        return new Builder(Ordering.natural()).create(iterable);
    }
}
