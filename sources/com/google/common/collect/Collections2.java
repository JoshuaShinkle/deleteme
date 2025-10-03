package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Multiset;
import com.google.common.math.IntMath;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.commons.p159io.FileUtils;

@GwtCompatible
/* loaded from: classes2.dex */
public final class Collections2 {

    public static class FilteredCollection<E> extends AbstractCollection<E> {
        final Predicate<? super E> predicate;
        final Collection<E> unfiltered;

        public FilteredCollection(Collection<E> collection, Predicate<? super E> predicate) {
            this.unfiltered = collection;
            this.predicate = predicate;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean add(E e9) {
            Preconditions.checkArgument(this.predicate.apply(e9));
            return this.unfiltered.add(e9);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean addAll(Collection<? extends E> collection) {
            Iterator<? extends E> it = collection.iterator();
            while (it.hasNext()) {
                Preconditions.checkArgument(this.predicate.apply(it.next()));
            }
            return this.unfiltered.addAll(collection);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            Iterables.removeIf(this.unfiltered, this.predicate);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            if (Collections2.safeContains(this.unfiltered, obj)) {
                return this.predicate.apply(obj);
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean containsAll(Collection<?> collection) {
            return Collections2.containsAllImpl(this, collection);
        }

        public FilteredCollection<E> createCombined(Predicate<? super E> predicate) {
            return new FilteredCollection<>(this.unfiltered, Predicates.and(this.predicate, predicate));
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return !Iterables.any(this.unfiltered, this.predicate);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<E> iterator() {
            return Iterators.filter(this.unfiltered.iterator(), this.predicate);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean remove(Object obj) {
            return contains(obj) && this.unfiltered.remove(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean removeAll(Collection<?> collection) {
            Iterator<E> it = this.unfiltered.iterator();
            boolean z8 = false;
            while (it.hasNext()) {
                E next = it.next();
                if (this.predicate.apply(next) && collection.contains(next)) {
                    it.remove();
                    z8 = true;
                }
            }
            return z8;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean retainAll(Collection<?> collection) {
            Iterator<E> it = this.unfiltered.iterator();
            boolean z8 = false;
            while (it.hasNext()) {
                E next = it.next();
                if (this.predicate.apply(next) && !collection.contains(next)) {
                    it.remove();
                    z8 = true;
                }
            }
            return z8;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            Iterator<E> it = this.unfiltered.iterator();
            int i9 = 0;
            while (it.hasNext()) {
                if (this.predicate.apply(it.next())) {
                    i9++;
                }
            }
            return i9;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public Object[] toArray() {
            return Lists.newArrayList(iterator()).toArray();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public <T> T[] toArray(T[] tArr) {
            return (T[]) Lists.newArrayList(iterator()).toArray(tArr);
        }
    }

    public static final class OrderedPermutationCollection<E> extends AbstractCollection<List<E>> {
        final Comparator<? super E> comparator;
        final ImmutableList<E> inputList;
        final int size;

        public OrderedPermutationCollection(Iterable<E> iterable, Comparator<? super E> comparator) {
            ImmutableList<E> immutableListSortedCopyOf = ImmutableList.sortedCopyOf(comparator, iterable);
            this.inputList = immutableListSortedCopyOf;
            this.comparator = comparator;
            this.size = calculateSize(immutableListSortedCopyOf, comparator);
        }

        private static <E> int calculateSize(List<E> list, Comparator<? super E> comparator) {
            int i9 = 1;
            int iSaturatedMultiply = 1;
            int i10 = 1;
            while (i9 < list.size()) {
                if (comparator.compare(list.get(i9 - 1), list.get(i9)) < 0) {
                    iSaturatedMultiply = IntMath.saturatedMultiply(iSaturatedMultiply, IntMath.binomial(i9, i10));
                    if (iSaturatedMultiply == Integer.MAX_VALUE) {
                        return Integer.MAX_VALUE;
                    }
                    i10 = 0;
                }
                i9++;
                i10++;
            }
            return IntMath.saturatedMultiply(iSaturatedMultiply, IntMath.binomial(i9, i10));
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            if (!(obj instanceof List)) {
                return false;
            }
            return Collections2.isPermutation(this.inputList, (List) obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<List<E>> iterator() {
            return new OrderedPermutationIterator(this.inputList, this.comparator);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return this.size;
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            return "orderedPermutationCollection(" + this.inputList + ")";
        }
    }

    public static final class OrderedPermutationIterator<E> extends AbstractIterator<List<E>> {
        final Comparator<? super E> comparator;
        List<E> nextPermutation;

        public OrderedPermutationIterator(List<E> list, Comparator<? super E> comparator) {
            this.nextPermutation = Lists.newArrayList(list);
            this.comparator = comparator;
        }

        public void calculateNextPermutation() {
            int iFindNextJ = findNextJ();
            if (iFindNextJ == -1) {
                this.nextPermutation = null;
                return;
            }
            Collections.swap(this.nextPermutation, iFindNextJ, findNextL(iFindNextJ));
            Collections.reverse(this.nextPermutation.subList(iFindNextJ + 1, this.nextPermutation.size()));
        }

        public int findNextJ() {
            for (int size = this.nextPermutation.size() - 2; size >= 0; size--) {
                if (this.comparator.compare(this.nextPermutation.get(size), this.nextPermutation.get(size + 1)) < 0) {
                    return size;
                }
            }
            return -1;
        }

        public int findNextL(int i9) {
            E e9 = this.nextPermutation.get(i9);
            for (int size = this.nextPermutation.size() - 1; size > i9; size--) {
                if (this.comparator.compare(e9, this.nextPermutation.get(size)) < 0) {
                    return size;
                }
            }
            throw new AssertionError("this statement should be unreachable");
        }

        @Override // com.google.common.collect.AbstractIterator
        public List<E> computeNext() {
            List<E> list = this.nextPermutation;
            if (list == null) {
                return endOfData();
            }
            ImmutableList immutableListCopyOf = ImmutableList.copyOf((Collection) list);
            calculateNextPermutation();
            return immutableListCopyOf;
        }
    }

    public static final class PermutationCollection<E> extends AbstractCollection<List<E>> {
        final ImmutableList<E> inputList;

        public PermutationCollection(ImmutableList<E> immutableList) {
            this.inputList = immutableList;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            if (!(obj instanceof List)) {
                return false;
            }
            return Collections2.isPermutation(this.inputList, (List) obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<List<E>> iterator() {
            return new PermutationIterator(this.inputList);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return IntMath.factorial(this.inputList.size());
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            return "permutations(" + this.inputList + ")";
        }
    }

    public static class PermutationIterator<E> extends AbstractIterator<List<E>> {

        /* renamed from: c */
        final int[] f15415c;

        /* renamed from: j */
        int f15416j;
        final List<E> list;

        /* renamed from: o */
        final int[] f15417o;

        public PermutationIterator(List<E> list) {
            this.list = new ArrayList(list);
            int size = list.size();
            int[] iArr = new int[size];
            this.f15415c = iArr;
            int[] iArr2 = new int[size];
            this.f15417o = iArr2;
            Arrays.fill(iArr, 0);
            Arrays.fill(iArr2, 1);
            this.f15416j = Integer.MAX_VALUE;
        }

        public void calculateNextPermutation() {
            int size = this.list.size() - 1;
            this.f15416j = size;
            if (size == -1) {
                return;
            }
            int i9 = 0;
            while (true) {
                int[] iArr = this.f15415c;
                int i10 = this.f15416j;
                int i11 = iArr[i10];
                int i12 = this.f15417o[i10] + i11;
                if (i12 < 0) {
                    switchDirection();
                } else if (i12 != i10 + 1) {
                    Collections.swap(this.list, (i10 - i11) + i9, (i10 - i12) + i9);
                    this.f15415c[this.f15416j] = i12;
                    return;
                } else {
                    if (i10 == 0) {
                        return;
                    }
                    i9++;
                    switchDirection();
                }
            }
        }

        public void switchDirection() {
            int[] iArr = this.f15417o;
            int i9 = this.f15416j;
            iArr[i9] = -iArr[i9];
            this.f15416j = i9 - 1;
        }

        @Override // com.google.common.collect.AbstractIterator
        public List<E> computeNext() {
            if (this.f15416j <= 0) {
                return endOfData();
            }
            ImmutableList immutableListCopyOf = ImmutableList.copyOf((Collection) this.list);
            calculateNextPermutation();
            return immutableListCopyOf;
        }
    }

    public static class TransformedCollection<F, T> extends AbstractCollection<T> {
        final Collection<F> fromCollection;
        final Function<? super F, ? extends T> function;

        public TransformedCollection(Collection<F> collection, Function<? super F, ? extends T> function) {
            this.fromCollection = (Collection) Preconditions.checkNotNull(collection);
            this.function = (Function) Preconditions.checkNotNull(function);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            this.fromCollection.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return this.fromCollection.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<T> iterator() {
            return Iterators.transform(this.fromCollection.iterator(), this.function);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return this.fromCollection.size();
        }
    }

    private Collections2() {
    }

    public static <T> Collection<T> cast(Iterable<T> iterable) {
        return (Collection) iterable;
    }

    public static boolean containsAllImpl(Collection<?> collection, Collection<?> collection2) {
        Iterator<?> it = collection2.iterator();
        while (it.hasNext()) {
            if (!collection.contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    private static <E> Set<Multiset.Entry<E>> counts(Collection<E> collection) {
        ObjectCountHashMap objectCountHashMap = new ObjectCountHashMap();
        for (E e9 : collection) {
            objectCountHashMap.put(e9, objectCountHashMap.get(e9) + 1);
        }
        return (Set<Multiset.Entry<E>>) objectCountHashMap.entrySet();
    }

    public static <E> Collection<E> filter(Collection<E> collection, Predicate<? super E> predicate) {
        return collection instanceof FilteredCollection ? ((FilteredCollection) collection).createCombined(predicate) : new FilteredCollection((Collection) Preconditions.checkNotNull(collection), (Predicate) Preconditions.checkNotNull(predicate));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isPermutation(List<?> list, List<?> list2) {
        return list.size() == list2.size() && counts(list).equals(counts(list2));
    }

    public static StringBuilder newStringBuilderForCollection(int i9) {
        CollectPreconditions.checkNonnegative(i9, "size");
        return new StringBuilder((int) Math.min(i9 * 8, FileUtils.ONE_GB));
    }

    @Beta
    public static <E extends Comparable<? super E>> Collection<List<E>> orderedPermutations(Iterable<E> iterable) {
        return orderedPermutations(iterable, Ordering.natural());
    }

    @Beta
    public static <E> Collection<List<E>> permutations(Collection<E> collection) {
        return new PermutationCollection(ImmutableList.copyOf((Collection) collection));
    }

    public static boolean safeContains(Collection<?> collection, Object obj) {
        Preconditions.checkNotNull(collection);
        try {
            return collection.contains(obj);
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    public static boolean safeRemove(Collection<?> collection, Object obj) {
        Preconditions.checkNotNull(collection);
        try {
            return collection.remove(obj);
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    public static String toStringImpl(Collection<?> collection) {
        StringBuilder sbNewStringBuilderForCollection = newStringBuilderForCollection(collection.size());
        sbNewStringBuilderForCollection.append('[');
        boolean z8 = true;
        for (Object obj : collection) {
            if (!z8) {
                sbNewStringBuilderForCollection.append(", ");
            }
            if (obj == collection) {
                sbNewStringBuilderForCollection.append("(this Collection)");
            } else {
                sbNewStringBuilderForCollection.append(obj);
            }
            z8 = false;
        }
        sbNewStringBuilderForCollection.append(']');
        return sbNewStringBuilderForCollection.toString();
    }

    public static <F, T> Collection<T> transform(Collection<F> collection, Function<? super F, T> function) {
        return new TransformedCollection(collection, function);
    }

    @Beta
    public static <E> Collection<List<E>> orderedPermutations(Iterable<E> iterable, Comparator<? super E> comparator) {
        return new OrderedPermutationCollection(iterable, comparator);
    }
}
