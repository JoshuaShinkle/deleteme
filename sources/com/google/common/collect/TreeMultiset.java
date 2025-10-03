package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.Serialization;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.Set;

@GwtCompatible(emulated = true)
/* loaded from: classes2.dex */
public final class TreeMultiset<E> extends AbstractSortedMultiset<E> implements Serializable {

    @GwtIncompatible
    private static final long serialVersionUID = 1;
    private final transient AvlNode<E> header;
    private final transient GeneralRange<E> range;
    private final transient Reference<AvlNode<E>> rootReference;

    /* renamed from: com.google.common.collect.TreeMultiset$4 */
    public static /* synthetic */ class C38034 {
        static final /* synthetic */ int[] $SwitchMap$com$google$common$collect$BoundType;

        static {
            int[] iArr = new int[BoundType.values().length];
            $SwitchMap$com$google$common$collect$BoundType = iArr;
            try {
                iArr[BoundType.OPEN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$common$collect$BoundType[BoundType.CLOSED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public enum Aggregate {
        SIZE { // from class: com.google.common.collect.TreeMultiset.Aggregate.1
            @Override // com.google.common.collect.TreeMultiset.Aggregate
            public int nodeAggregate(AvlNode<?> avlNode) {
                return ((AvlNode) avlNode).elemCount;
            }

            @Override // com.google.common.collect.TreeMultiset.Aggregate
            public long treeAggregate(AvlNode<?> avlNode) {
                if (avlNode == null) {
                    return 0L;
                }
                return ((AvlNode) avlNode).totalCount;
            }
        },
        DISTINCT { // from class: com.google.common.collect.TreeMultiset.Aggregate.2
            @Override // com.google.common.collect.TreeMultiset.Aggregate
            public int nodeAggregate(AvlNode<?> avlNode) {
                return 1;
            }

            @Override // com.google.common.collect.TreeMultiset.Aggregate
            public long treeAggregate(AvlNode<?> avlNode) {
                if (avlNode == null) {
                    return 0L;
                }
                return ((AvlNode) avlNode).distinctElements;
            }
        };

        public abstract int nodeAggregate(AvlNode<?> avlNode);

        public abstract long treeAggregate(AvlNode<?> avlNode);
    }

    public static final class Reference<T> {
        private T value;

        private Reference() {
        }

        public void checkAndSet(T t8, T t9) {
            if (this.value != t8) {
                throw new ConcurrentModificationException();
            }
            this.value = t9;
        }

        public T get() {
            return this.value;
        }
    }

    public TreeMultiset(Reference<AvlNode<E>> reference, GeneralRange<E> generalRange, AvlNode<E> avlNode) {
        super(generalRange.comparator());
        this.rootReference = reference;
        this.range = generalRange;
        this.header = avlNode;
    }

    private long aggregateAboveRange(Aggregate aggregate, AvlNode<E> avlNode) {
        long jTreeAggregate;
        long jAggregateAboveRange;
        if (avlNode == null) {
            return 0L;
        }
        int iCompare = comparator().compare(this.range.getUpperEndpoint(), ((AvlNode) avlNode).elem);
        if (iCompare > 0) {
            return aggregateAboveRange(aggregate, ((AvlNode) avlNode).right);
        }
        if (iCompare == 0) {
            int i9 = C38034.$SwitchMap$com$google$common$collect$BoundType[this.range.getUpperBoundType().ordinal()];
            if (i9 != 1) {
                if (i9 == 2) {
                    return aggregate.treeAggregate(((AvlNode) avlNode).right);
                }
                throw new AssertionError();
            }
            jTreeAggregate = aggregate.nodeAggregate(avlNode);
            jAggregateAboveRange = aggregate.treeAggregate(((AvlNode) avlNode).right);
        } else {
            jTreeAggregate = aggregate.treeAggregate(((AvlNode) avlNode).right) + aggregate.nodeAggregate(avlNode);
            jAggregateAboveRange = aggregateAboveRange(aggregate, ((AvlNode) avlNode).left);
        }
        return jTreeAggregate + jAggregateAboveRange;
    }

    private long aggregateBelowRange(Aggregate aggregate, AvlNode<E> avlNode) {
        long jTreeAggregate;
        long jAggregateBelowRange;
        if (avlNode == null) {
            return 0L;
        }
        int iCompare = comparator().compare(this.range.getLowerEndpoint(), ((AvlNode) avlNode).elem);
        if (iCompare < 0) {
            return aggregateBelowRange(aggregate, ((AvlNode) avlNode).left);
        }
        if (iCompare == 0) {
            int i9 = C38034.$SwitchMap$com$google$common$collect$BoundType[this.range.getLowerBoundType().ordinal()];
            if (i9 != 1) {
                if (i9 == 2) {
                    return aggregate.treeAggregate(((AvlNode) avlNode).left);
                }
                throw new AssertionError();
            }
            jTreeAggregate = aggregate.nodeAggregate(avlNode);
            jAggregateBelowRange = aggregate.treeAggregate(((AvlNode) avlNode).left);
        } else {
            jTreeAggregate = aggregate.treeAggregate(((AvlNode) avlNode).left) + aggregate.nodeAggregate(avlNode);
            jAggregateBelowRange = aggregateBelowRange(aggregate, ((AvlNode) avlNode).right);
        }
        return jTreeAggregate + jAggregateBelowRange;
    }

    private long aggregateForEntries(Aggregate aggregate) {
        AvlNode<E> avlNode = this.rootReference.get();
        long jTreeAggregate = aggregate.treeAggregate(avlNode);
        if (this.range.hasLowerBound()) {
            jTreeAggregate -= aggregateBelowRange(aggregate, avlNode);
        }
        return this.range.hasUpperBound() ? jTreeAggregate - aggregateAboveRange(aggregate, avlNode) : jTreeAggregate;
    }

    public static <E extends Comparable> TreeMultiset<E> create() {
        return new TreeMultiset<>(Ordering.natural());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AvlNode<E> firstNode() {
        AvlNode<E> avlNodeCeiling;
        if (this.rootReference.get() == null) {
            return null;
        }
        if (this.range.hasLowerBound()) {
            E lowerEndpoint = this.range.getLowerEndpoint();
            avlNodeCeiling = this.rootReference.get().ceiling(comparator(), lowerEndpoint);
            if (avlNodeCeiling == null) {
                return null;
            }
            if (this.range.getLowerBoundType() == BoundType.OPEN && comparator().compare(lowerEndpoint, avlNodeCeiling.getElement()) == 0) {
                avlNodeCeiling = ((AvlNode) avlNodeCeiling).succ;
            }
        } else {
            avlNodeCeiling = ((AvlNode) this.header).succ;
        }
        if (avlNodeCeiling == this.header || !this.range.contains(avlNodeCeiling.getElement())) {
            return null;
        }
        return avlNodeCeiling;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AvlNode<E> lastNode() {
        AvlNode<E> avlNodeFloor;
        if (this.rootReference.get() == null) {
            return null;
        }
        if (this.range.hasUpperBound()) {
            E upperEndpoint = this.range.getUpperEndpoint();
            avlNodeFloor = this.rootReference.get().floor(comparator(), upperEndpoint);
            if (avlNodeFloor == null) {
                return null;
            }
            if (this.range.getUpperBoundType() == BoundType.OPEN && comparator().compare(upperEndpoint, avlNodeFloor.getElement()) == 0) {
                avlNodeFloor = ((AvlNode) avlNodeFloor).pred;
            }
        } else {
            avlNodeFloor = ((AvlNode) this.header).pred;
        }
        if (avlNodeFloor == this.header || !this.range.contains(avlNodeFloor.getElement())) {
            return null;
        }
        return avlNodeFloor;
    }

    @GwtIncompatible
    private void readObject(ObjectInputStream objectInputStream) throws IllegalAccessException, ClassNotFoundException, IOException, IllegalArgumentException {
        objectInputStream.defaultReadObject();
        Comparator comparator = (Comparator) objectInputStream.readObject();
        Serialization.getFieldSetter(AbstractSortedMultiset.class, "comparator").set((Serialization.FieldSetter) this, (Object) comparator);
        Serialization.getFieldSetter(TreeMultiset.class, "range").set((Serialization.FieldSetter) this, (Object) GeneralRange.all(comparator));
        Serialization.getFieldSetter(TreeMultiset.class, "rootReference").set((Serialization.FieldSetter) this, (Object) new Reference());
        AvlNode avlNode = new AvlNode(null, 1);
        Serialization.getFieldSetter(TreeMultiset.class, "header").set((Serialization.FieldSetter) this, (Object) avlNode);
        successor(avlNode, avlNode);
        Serialization.populateMultiset(this, objectInputStream);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> void successor(AvlNode<T> avlNode, AvlNode<T> avlNode2) {
        ((AvlNode) avlNode).succ = avlNode2;
        ((AvlNode) avlNode2).pred = avlNode;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Multiset.Entry<E> wrapEntry(final AvlNode<E> avlNode) {
        return new Multisets.AbstractEntry<E>() { // from class: com.google.common.collect.TreeMultiset.1
            @Override // com.google.common.collect.Multiset.Entry
            public int getCount() {
                int count = avlNode.getCount();
                return count == 0 ? TreeMultiset.this.count(getElement()) : count;
            }

            @Override // com.google.common.collect.Multiset.Entry
            public E getElement() {
                return (E) avlNode.getElement();
            }
        };
    }

    @GwtIncompatible
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(elementSet().comparator());
        Serialization.writeMultiset(this, objectOutputStream);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ boolean add(Object obj) {
        return super.add(obj);
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ boolean addAll(Collection collection) {
        return super.addAll(collection);
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection
    public /* bridge */ /* synthetic */ void clear() {
        super.clear();
    }

    @Override // com.google.common.collect.AbstractSortedMultiset, com.google.common.collect.SortedMultiset, com.google.common.collect.SortedIterable
    public /* bridge */ /* synthetic */ Comparator comparator() {
        return super.comparator();
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    public /* bridge */ /* synthetic */ boolean contains(Object obj) {
        return super.contains(obj);
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    public int count(Object obj) {
        try {
            AvlNode<E> avlNode = this.rootReference.get();
            if (this.range.contains(obj) && avlNode != null) {
                return avlNode.count(comparator(), obj);
            }
        } catch (ClassCastException | NullPointerException unused) {
        }
        return 0;
    }

    @Override // com.google.common.collect.AbstractSortedMultiset
    public Iterator<Multiset.Entry<E>> descendingEntryIterator() {
        return new Iterator<Multiset.Entry<E>>() { // from class: com.google.common.collect.TreeMultiset.3
            AvlNode<E> current;
            Multiset.Entry<E> prevEntry = null;

            {
                this.current = TreeMultiset.this.lastNode();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                if (this.current == null) {
                    return false;
                }
                if (!TreeMultiset.this.range.tooLow(this.current.getElement())) {
                    return true;
                }
                this.current = null;
                return false;
            }

            @Override // java.util.Iterator
            public void remove() {
                CollectPreconditions.checkRemove(this.prevEntry != null);
                TreeMultiset.this.setCount(this.prevEntry.getElement(), 0);
                this.prevEntry = null;
            }

            @Override // java.util.Iterator
            public Multiset.Entry<E> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Multiset.Entry<E> entryWrapEntry = TreeMultiset.this.wrapEntry(this.current);
                this.prevEntry = entryWrapEntry;
                if (((AvlNode) this.current).pred == TreeMultiset.this.header) {
                    this.current = null;
                } else {
                    this.current = ((AvlNode) this.current).pred;
                }
                return entryWrapEntry;
            }
        };
    }

    @Override // com.google.common.collect.AbstractSortedMultiset, com.google.common.collect.SortedMultiset
    public /* bridge */ /* synthetic */ SortedMultiset descendingMultiset() {
        return super.descendingMultiset();
    }

    @Override // com.google.common.collect.AbstractMultiset
    public int distinctElements() {
        return Ints.saturatedCast(aggregateForEntries(Aggregate.DISTINCT));
    }

    @Override // com.google.common.collect.AbstractSortedMultiset, com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    public /* bridge */ /* synthetic */ NavigableSet elementSet() {
        return super.elementSet();
    }

    @Override // com.google.common.collect.AbstractMultiset
    public Iterator<Multiset.Entry<E>> entryIterator() {
        return new Iterator<Multiset.Entry<E>>() { // from class: com.google.common.collect.TreeMultiset.2
            AvlNode<E> current;
            Multiset.Entry<E> prevEntry;

            {
                this.current = TreeMultiset.this.firstNode();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                if (this.current == null) {
                    return false;
                }
                if (!TreeMultiset.this.range.tooHigh(this.current.getElement())) {
                    return true;
                }
                this.current = null;
                return false;
            }

            @Override // java.util.Iterator
            public void remove() {
                CollectPreconditions.checkRemove(this.prevEntry != null);
                TreeMultiset.this.setCount(this.prevEntry.getElement(), 0);
                this.prevEntry = null;
            }

            @Override // java.util.Iterator
            public Multiset.Entry<E> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Multiset.Entry<E> entryWrapEntry = TreeMultiset.this.wrapEntry(this.current);
                this.prevEntry = entryWrapEntry;
                if (((AvlNode) this.current).succ == TreeMultiset.this.header) {
                    this.current = null;
                } else {
                    this.current = ((AvlNode) this.current).succ;
                }
                return entryWrapEntry;
            }
        };
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    public /* bridge */ /* synthetic */ Set entrySet() {
        return super.entrySet();
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.Collection, com.google.common.collect.Multiset
    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override // com.google.common.collect.AbstractSortedMultiset, com.google.common.collect.SortedMultiset
    public /* bridge */ /* synthetic */ Multiset.Entry firstEntry() {
        return super.firstEntry();
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.Collection, com.google.common.collect.Multiset
    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // com.google.common.collect.SortedMultiset
    public SortedMultiset<E> headMultiset(E e9, BoundType boundType) {
        return new TreeMultiset(this.rootReference, this.range.intersect(GeneralRange.upTo(comparator(), e9, boundType)), this.header);
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection
    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.google.common.collect.Multiset
    public /* bridge */ /* synthetic */ Iterator iterator() {
        return super.iterator();
    }

    @Override // com.google.common.collect.AbstractSortedMultiset, com.google.common.collect.SortedMultiset
    public /* bridge */ /* synthetic */ Multiset.Entry lastEntry() {
        return super.lastEntry();
    }

    @Override // com.google.common.collect.AbstractSortedMultiset, com.google.common.collect.SortedMultiset
    public /* bridge */ /* synthetic */ Multiset.Entry pollFirstEntry() {
        return super.pollFirstEntry();
    }

    @Override // com.google.common.collect.AbstractSortedMultiset, com.google.common.collect.SortedMultiset
    public /* bridge */ /* synthetic */ Multiset.Entry pollLastEntry() {
        return super.pollLastEntry();
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ boolean remove(Object obj) {
        return super.remove(obj);
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ boolean removeAll(Collection collection) {
        return super.removeAll(collection);
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ boolean retainAll(Collection collection) {
        return super.retainAll(collection);
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public int setCount(E e9, int i9) {
        CollectPreconditions.checkNonnegative(i9, "count");
        if (!this.range.contains(e9)) {
            Preconditions.checkArgument(i9 == 0);
            return 0;
        }
        AvlNode<E> avlNode = this.rootReference.get();
        if (avlNode == null) {
            if (i9 > 0) {
                add(e9, i9);
            }
            return 0;
        }
        int[] iArr = new int[1];
        this.rootReference.checkAndSet(avlNode, avlNode.setCount(comparator(), e9, i9, iArr));
        return iArr[0];
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    public int size() {
        return Ints.saturatedCast(aggregateForEntries(Aggregate.SIZE));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.AbstractSortedMultiset, com.google.common.collect.SortedMultiset
    public /* bridge */ /* synthetic */ SortedMultiset subMultiset(Object obj, BoundType boundType, Object obj2, BoundType boundType2) {
        return super.subMultiset(obj, boundType, obj2, boundType2);
    }

    @Override // com.google.common.collect.SortedMultiset
    public SortedMultiset<E> tailMultiset(E e9, BoundType boundType) {
        return new TreeMultiset(this.rootReference, this.range.intersect(GeneralRange.downTo(comparator(), e9, boundType)), this.header);
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, com.google.common.collect.Multiset
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public static <E> TreeMultiset<E> create(Comparator<? super E> comparator) {
        return comparator == null ? new TreeMultiset<>(Ordering.natural()) : new TreeMultiset<>(comparator);
    }

    public static int distinctElements(AvlNode<?> avlNode) {
        if (avlNode == null) {
            return 0;
        }
        return ((AvlNode) avlNode).distinctElements;
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public int add(E e9, int i9) {
        CollectPreconditions.checkNonnegative(i9, "occurrences");
        if (i9 == 0) {
            return count(e9);
        }
        Preconditions.checkArgument(this.range.contains(e9));
        AvlNode<E> avlNode = this.rootReference.get();
        if (avlNode != null) {
            int[] iArr = new int[1];
            this.rootReference.checkAndSet(avlNode, avlNode.add(comparator(), e9, i9, iArr));
            return iArr[0];
        }
        comparator().compare(e9, e9);
        AvlNode<E> avlNode2 = new AvlNode<>(e9, i9);
        AvlNode<E> avlNode3 = this.header;
        successor(avlNode3, avlNode2, avlNode3);
        this.rootReference.checkAndSet(avlNode, avlNode2);
        return 0;
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public int remove(Object obj, int i9) {
        CollectPreconditions.checkNonnegative(i9, "occurrences");
        if (i9 == 0) {
            return count(obj);
        }
        AvlNode<E> avlNode = this.rootReference.get();
        int[] iArr = new int[1];
        try {
            if (this.range.contains(obj) && avlNode != null) {
                this.rootReference.checkAndSet(avlNode, avlNode.remove(comparator(), obj, i9, iArr));
                return iArr[0];
            }
        } catch (ClassCastException | NullPointerException unused) {
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> void successor(AvlNode<T> avlNode, AvlNode<T> avlNode2, AvlNode<T> avlNode3) {
        successor(avlNode, avlNode2);
        successor(avlNode2, avlNode3);
    }

    public static <E extends Comparable> TreeMultiset<E> create(Iterable<? extends E> iterable) {
        TreeMultiset<E> treeMultisetCreate = create();
        Iterables.addAll(treeMultisetCreate, iterable);
        return treeMultisetCreate;
    }

    public TreeMultiset(Comparator<? super E> comparator) {
        super(comparator);
        this.range = GeneralRange.all(comparator);
        AvlNode<E> avlNode = new AvlNode<>(null, 1);
        this.header = avlNode;
        successor(avlNode, avlNode);
        this.rootReference = new Reference<>();
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public boolean setCount(E e9, int i9, int i10) {
        CollectPreconditions.checkNonnegative(i10, "newCount");
        CollectPreconditions.checkNonnegative(i9, "oldCount");
        Preconditions.checkArgument(this.range.contains(e9));
        AvlNode<E> avlNode = this.rootReference.get();
        if (avlNode != null) {
            int[] iArr = new int[1];
            this.rootReference.checkAndSet(avlNode, avlNode.setCount(comparator(), e9, i9, i10, iArr));
            return iArr[0] == i9;
        }
        if (i9 != 0) {
            return false;
        }
        if (i10 > 0) {
            add(e9, i10);
        }
        return true;
    }

    public static final class AvlNode<E> extends Multisets.AbstractEntry<E> {
        private int distinctElements;
        private final E elem;
        private int elemCount;
        private int height;
        private AvlNode<E> left;
        private AvlNode<E> pred;
        private AvlNode<E> right;
        private AvlNode<E> succ;
        private long totalCount;

        public AvlNode(E e9, int i9) {
            Preconditions.checkArgument(i9 > 0);
            this.elem = e9;
            this.elemCount = i9;
            this.totalCount = i9;
            this.distinctElements = 1;
            this.height = 1;
            this.left = null;
            this.right = null;
        }

        private AvlNode<E> addLeftChild(E e9, int i9) {
            AvlNode<E> avlNode = new AvlNode<>(e9, i9);
            this.left = avlNode;
            TreeMultiset.successor(this.pred, avlNode, this);
            this.height = Math.max(2, this.height);
            this.distinctElements++;
            this.totalCount += i9;
            return this;
        }

        private AvlNode<E> addRightChild(E e9, int i9) {
            AvlNode<E> avlNode = new AvlNode<>(e9, i9);
            this.right = avlNode;
            TreeMultiset.successor(this, avlNode, this.succ);
            this.height = Math.max(2, this.height);
            this.distinctElements++;
            this.totalCount += i9;
            return this;
        }

        private int balanceFactor() {
            return height(this.left) - height(this.right);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        public AvlNode<E> ceiling(Comparator<? super E> comparator, E e9) {
            int iCompare = comparator.compare(e9, this.elem);
            if (iCompare < 0) {
                AvlNode<E> avlNode = this.left;
                return avlNode == null ? this : (AvlNode) MoreObjects.firstNonNull(avlNode.ceiling(comparator, e9), this);
            }
            if (iCompare == 0) {
                return this;
            }
            AvlNode<E> avlNode2 = this.right;
            if (avlNode2 == null) {
                return null;
            }
            return avlNode2.ceiling(comparator, e9);
        }

        private AvlNode<E> deleteMe() {
            int i9 = this.elemCount;
            this.elemCount = 0;
            TreeMultiset.successor(this.pred, this.succ);
            AvlNode<E> avlNode = this.left;
            if (avlNode == null) {
                return this.right;
            }
            AvlNode<E> avlNode2 = this.right;
            if (avlNode2 == null) {
                return avlNode;
            }
            if (avlNode.height >= avlNode2.height) {
                AvlNode<E> avlNode3 = this.pred;
                avlNode3.left = avlNode.removeMax(avlNode3);
                avlNode3.right = this.right;
                avlNode3.distinctElements = this.distinctElements - 1;
                avlNode3.totalCount = this.totalCount - i9;
                return avlNode3.rebalance();
            }
            AvlNode<E> avlNode4 = this.succ;
            avlNode4.right = avlNode2.removeMin(avlNode4);
            avlNode4.left = this.left;
            avlNode4.distinctElements = this.distinctElements - 1;
            avlNode4.totalCount = this.totalCount - i9;
            return avlNode4.rebalance();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        public AvlNode<E> floor(Comparator<? super E> comparator, E e9) {
            int iCompare = comparator.compare(e9, this.elem);
            if (iCompare > 0) {
                AvlNode<E> avlNode = this.right;
                return avlNode == null ? this : (AvlNode) MoreObjects.firstNonNull(avlNode.floor(comparator, e9), this);
            }
            if (iCompare == 0) {
                return this;
            }
            AvlNode<E> avlNode2 = this.left;
            if (avlNode2 == null) {
                return null;
            }
            return avlNode2.floor(comparator, e9);
        }

        private static int height(AvlNode<?> avlNode) {
            if (avlNode == null) {
                return 0;
            }
            return ((AvlNode) avlNode).height;
        }

        private AvlNode<E> rebalance() {
            int iBalanceFactor = balanceFactor();
            if (iBalanceFactor == -2) {
                if (this.right.balanceFactor() > 0) {
                    this.right = this.right.rotateRight();
                }
                return rotateLeft();
            }
            if (iBalanceFactor != 2) {
                recomputeHeight();
                return this;
            }
            if (this.left.balanceFactor() < 0) {
                this.left = this.left.rotateLeft();
            }
            return rotateRight();
        }

        private void recompute() {
            recomputeMultiset();
            recomputeHeight();
        }

        private void recomputeHeight() {
            this.height = Math.max(height(this.left), height(this.right)) + 1;
        }

        private void recomputeMultiset() {
            this.distinctElements = TreeMultiset.distinctElements(this.left) + 1 + TreeMultiset.distinctElements(this.right);
            this.totalCount = this.elemCount + totalCount(this.left) + totalCount(this.right);
        }

        private AvlNode<E> removeMax(AvlNode<E> avlNode) {
            AvlNode<E> avlNode2 = this.right;
            if (avlNode2 == null) {
                return this.left;
            }
            this.right = avlNode2.removeMax(avlNode);
            this.distinctElements--;
            this.totalCount -= avlNode.elemCount;
            return rebalance();
        }

        private AvlNode<E> removeMin(AvlNode<E> avlNode) {
            AvlNode<E> avlNode2 = this.left;
            if (avlNode2 == null) {
                return this.right;
            }
            this.left = avlNode2.removeMin(avlNode);
            this.distinctElements--;
            this.totalCount -= avlNode.elemCount;
            return rebalance();
        }

        private AvlNode<E> rotateLeft() {
            Preconditions.checkState(this.right != null);
            AvlNode<E> avlNode = this.right;
            this.right = avlNode.left;
            avlNode.left = this;
            avlNode.totalCount = this.totalCount;
            avlNode.distinctElements = this.distinctElements;
            recompute();
            avlNode.recomputeHeight();
            return avlNode;
        }

        private AvlNode<E> rotateRight() {
            Preconditions.checkState(this.left != null);
            AvlNode<E> avlNode = this.left;
            this.left = avlNode.right;
            avlNode.right = this;
            avlNode.totalCount = this.totalCount;
            avlNode.distinctElements = this.distinctElements;
            recompute();
            avlNode.recomputeHeight();
            return avlNode;
        }

        private static long totalCount(AvlNode<?> avlNode) {
            if (avlNode == null) {
                return 0L;
            }
            return ((AvlNode) avlNode).totalCount;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public AvlNode<E> add(Comparator<? super E> comparator, E e9, int i9, int[] iArr) {
            int iCompare = comparator.compare(e9, this.elem);
            if (iCompare < 0) {
                AvlNode<E> avlNode = this.left;
                if (avlNode == null) {
                    iArr[0] = 0;
                    return addLeftChild(e9, i9);
                }
                int i10 = avlNode.height;
                AvlNode<E> avlNodeAdd = avlNode.add(comparator, e9, i9, iArr);
                this.left = avlNodeAdd;
                if (iArr[0] == 0) {
                    this.distinctElements++;
                }
                this.totalCount += i9;
                return avlNodeAdd.height == i10 ? this : rebalance();
            }
            if (iCompare <= 0) {
                int i11 = this.elemCount;
                iArr[0] = i11;
                long j9 = i9;
                Preconditions.checkArgument(((long) i11) + j9 <= 2147483647L);
                this.elemCount += i9;
                this.totalCount += j9;
                return this;
            }
            AvlNode<E> avlNode2 = this.right;
            if (avlNode2 == null) {
                iArr[0] = 0;
                return addRightChild(e9, i9);
            }
            int i12 = avlNode2.height;
            AvlNode<E> avlNodeAdd2 = avlNode2.add(comparator, e9, i9, iArr);
            this.right = avlNodeAdd2;
            if (iArr[0] == 0) {
                this.distinctElements++;
            }
            this.totalCount += i9;
            return avlNodeAdd2.height == i12 ? this : rebalance();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int count(Comparator<? super E> comparator, E e9) {
            int iCompare = comparator.compare(e9, this.elem);
            if (iCompare < 0) {
                AvlNode<E> avlNode = this.left;
                if (avlNode == null) {
                    return 0;
                }
                return avlNode.count(comparator, e9);
            }
            if (iCompare <= 0) {
                return this.elemCount;
            }
            AvlNode<E> avlNode2 = this.right;
            if (avlNode2 == null) {
                return 0;
            }
            return avlNode2.count(comparator, e9);
        }

        @Override // com.google.common.collect.Multiset.Entry
        public int getCount() {
            return this.elemCount;
        }

        @Override // com.google.common.collect.Multiset.Entry
        public E getElement() {
            return this.elem;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public AvlNode<E> remove(Comparator<? super E> comparator, E e9, int i9, int[] iArr) {
            int iCompare = comparator.compare(e9, this.elem);
            if (iCompare < 0) {
                AvlNode<E> avlNode = this.left;
                if (avlNode == null) {
                    iArr[0] = 0;
                    return this;
                }
                this.left = avlNode.remove(comparator, e9, i9, iArr);
                int i10 = iArr[0];
                if (i10 > 0) {
                    if (i9 >= i10) {
                        this.distinctElements--;
                        this.totalCount -= i10;
                    } else {
                        this.totalCount -= i9;
                    }
                }
                return i10 == 0 ? this : rebalance();
            }
            if (iCompare <= 0) {
                int i11 = this.elemCount;
                iArr[0] = i11;
                if (i9 >= i11) {
                    return deleteMe();
                }
                this.elemCount = i11 - i9;
                this.totalCount -= i9;
                return this;
            }
            AvlNode<E> avlNode2 = this.right;
            if (avlNode2 == null) {
                iArr[0] = 0;
                return this;
            }
            this.right = avlNode2.remove(comparator, e9, i9, iArr);
            int i12 = iArr[0];
            if (i12 > 0) {
                if (i9 >= i12) {
                    this.distinctElements--;
                    this.totalCount -= i12;
                } else {
                    this.totalCount -= i9;
                }
            }
            return rebalance();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public AvlNode<E> setCount(Comparator<? super E> comparator, E e9, int i9, int[] iArr) {
            int iCompare = comparator.compare(e9, this.elem);
            if (iCompare < 0) {
                AvlNode<E> avlNode = this.left;
                if (avlNode == null) {
                    iArr[0] = 0;
                    return i9 > 0 ? addLeftChild(e9, i9) : this;
                }
                this.left = avlNode.setCount(comparator, e9, i9, iArr);
                if (i9 == 0 && iArr[0] != 0) {
                    this.distinctElements--;
                } else if (i9 > 0 && iArr[0] == 0) {
                    this.distinctElements++;
                }
                this.totalCount += i9 - iArr[0];
                return rebalance();
            }
            if (iCompare <= 0) {
                iArr[0] = this.elemCount;
                if (i9 == 0) {
                    return deleteMe();
                }
                this.totalCount += i9 - r3;
                this.elemCount = i9;
                return this;
            }
            AvlNode<E> avlNode2 = this.right;
            if (avlNode2 == null) {
                iArr[0] = 0;
                return i9 > 0 ? addRightChild(e9, i9) : this;
            }
            this.right = avlNode2.setCount(comparator, e9, i9, iArr);
            if (i9 == 0 && iArr[0] != 0) {
                this.distinctElements--;
            } else if (i9 > 0 && iArr[0] == 0) {
                this.distinctElements++;
            }
            this.totalCount += i9 - iArr[0];
            return rebalance();
        }

        @Override // com.google.common.collect.Multisets.AbstractEntry, com.google.common.collect.Multiset.Entry
        public String toString() {
            return Multisets.immutableEntry(getElement(), getCount()).toString();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public AvlNode<E> setCount(Comparator<? super E> comparator, E e9, int i9, int i10, int[] iArr) {
            int iCompare = comparator.compare(e9, this.elem);
            if (iCompare < 0) {
                AvlNode<E> avlNode = this.left;
                if (avlNode == null) {
                    iArr[0] = 0;
                    return (i9 != 0 || i10 <= 0) ? this : addLeftChild(e9, i10);
                }
                this.left = avlNode.setCount(comparator, e9, i9, i10, iArr);
                int i11 = iArr[0];
                if (i11 == i9) {
                    if (i10 == 0 && i11 != 0) {
                        this.distinctElements--;
                    } else if (i10 > 0 && i11 == 0) {
                        this.distinctElements++;
                    }
                    this.totalCount += i10 - i11;
                }
                return rebalance();
            }
            if (iCompare > 0) {
                AvlNode<E> avlNode2 = this.right;
                if (avlNode2 == null) {
                    iArr[0] = 0;
                    return (i9 != 0 || i10 <= 0) ? this : addRightChild(e9, i10);
                }
                this.right = avlNode2.setCount(comparator, e9, i9, i10, iArr);
                int i12 = iArr[0];
                if (i12 == i9) {
                    if (i10 == 0 && i12 != 0) {
                        this.distinctElements--;
                    } else if (i10 > 0 && i12 == 0) {
                        this.distinctElements++;
                    }
                    this.totalCount += i10 - i12;
                }
                return rebalance();
            }
            int i13 = this.elemCount;
            iArr[0] = i13;
            if (i9 == i13) {
                if (i10 == 0) {
                    return deleteMe();
                }
                this.totalCount += i10 - i13;
                this.elemCount = i10;
            }
            return this;
        }
    }
}
