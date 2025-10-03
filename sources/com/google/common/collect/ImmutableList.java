package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

@GwtCompatible(emulated = true, serializable = true)
/* loaded from: classes2.dex */
public abstract class ImmutableList<E> extends ImmutableCollection<E> implements List<E>, RandomAccess {

    public static final class Builder<E> extends ImmutableCollection.ArrayBasedBuilder<E> {
        public Builder() {
            this(4);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public /* bridge */ /* synthetic */ ImmutableCollection.ArrayBasedBuilder add(Object obj) {
            return add((Builder<E>) obj);
        }

        public Builder(int i9) {
            super(i9);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public /* bridge */ /* synthetic */ ImmutableCollection.Builder add(Object obj) {
            return add((Builder<E>) obj);
        }

        @Override // com.google.common.collect.ImmutableCollection.Builder
        public ImmutableList<E> build() {
            this.forceCopy = true;
            return ImmutableList.asImmutableList(this.contents, this.size);
        }

        @Override // com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public Builder<E> addAll(Iterable<? extends E> iterable) {
            super.addAll((Iterable) iterable);
            return this;
        }

        @Override // com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public Builder<E> add(E e9) {
            super.add((Builder<E>) e9);
            return this;
        }

        @Override // com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public Builder<E> addAll(Iterator<? extends E> it) {
            super.addAll((Iterator) it);
            return this;
        }

        @Override // com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public Builder<E> add(E... eArr) {
            super.add((Object[]) eArr);
            return this;
        }
    }

    public static class ReverseImmutableList<E> extends ImmutableList<E> {
        private final transient ImmutableList<E> forwardList;

        public ReverseImmutableList(ImmutableList<E> immutableList) {
            this.forwardList = immutableList;
        }

        private int reverseIndex(int i9) {
            return (size() - 1) - i9;
        }

        private int reversePosition(int i9) {
            return size() - i9;
        }

        @Override // com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return this.forwardList.contains(obj);
        }

        @Override // java.util.List
        public E get(int i9) {
            Preconditions.checkElementIndex(i9, size());
            return this.forwardList.get(reverseIndex(i9));
        }

        @Override // com.google.common.collect.ImmutableList, java.util.List
        public int indexOf(Object obj) {
            int iLastIndexOf = this.forwardList.lastIndexOf(obj);
            if (iLastIndexOf >= 0) {
                return reverseIndex(iLastIndexOf);
            }
            return -1;
        }

        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return this.forwardList.isPartialView();
        }

        @Override // com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
        public /* bridge */ /* synthetic */ Iterator iterator() {
            return super.iterator();
        }

        @Override // com.google.common.collect.ImmutableList, java.util.List
        public int lastIndexOf(Object obj) {
            int iIndexOf = this.forwardList.indexOf(obj);
            if (iIndexOf >= 0) {
                return reverseIndex(iIndexOf);
            }
            return -1;
        }

        @Override // com.google.common.collect.ImmutableList, java.util.List
        public /* bridge */ /* synthetic */ ListIterator listIterator() {
            return super.listIterator();
        }

        @Override // com.google.common.collect.ImmutableList
        public ImmutableList<E> reverse() {
            return this.forwardList;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.forwardList.size();
        }

        @Override // com.google.common.collect.ImmutableList, java.util.List
        public /* bridge */ /* synthetic */ ListIterator listIterator(int i9) {
            return super.listIterator(i9);
        }

        @Override // com.google.common.collect.ImmutableList, java.util.List
        public ImmutableList<E> subList(int i9, int i10) {
            Preconditions.checkPositionIndexes(i9, i10, size());
            return this.forwardList.subList(reversePosition(i10), reversePosition(i9)).reverse();
        }
    }

    public static class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        final Object[] elements;

        public SerializedForm(Object[] objArr) {
            this.elements = objArr;
        }

        public Object readResolve() {
            return ImmutableList.copyOf(this.elements);
        }
    }

    public class SubList extends ImmutableList<E> {
        final transient int length;
        final transient int offset;

        public SubList(int i9, int i10) {
            this.offset = i9;
            this.length = i10;
        }

        @Override // java.util.List
        public E get(int i9) {
            Preconditions.checkElementIndex(i9, this.length);
            return ImmutableList.this.get(i9 + this.offset);
        }

        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return true;
        }

        @Override // com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
        public /* bridge */ /* synthetic */ Iterator iterator() {
            return super.iterator();
        }

        @Override // com.google.common.collect.ImmutableList, java.util.List
        public /* bridge */ /* synthetic */ ListIterator listIterator() {
            return super.listIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.length;
        }

        @Override // com.google.common.collect.ImmutableList, java.util.List
        public /* bridge */ /* synthetic */ ListIterator listIterator(int i9) {
            return super.listIterator(i9);
        }

        @Override // com.google.common.collect.ImmutableList, java.util.List
        public ImmutableList<E> subList(int i9, int i10) {
            Preconditions.checkPositionIndexes(i9, i10, this.length);
            ImmutableList immutableList = ImmutableList.this;
            int i11 = this.offset;
            return immutableList.subList(i9 + i11, i10 + i11);
        }
    }

    public static <E> ImmutableList<E> asImmutableList(Object[] objArr) {
        return asImmutableList(objArr, objArr.length);
    }

    public static <E> Builder<E> builder() {
        return new Builder<>();
    }

    private static <E> ImmutableList<E> construct(Object... objArr) {
        return asImmutableList(ObjectArrays.checkElementsNotNull(objArr));
    }

    public static <E> ImmutableList<E> copyOf(Iterable<? extends E> iterable) {
        Preconditions.checkNotNull(iterable);
        return iterable instanceof Collection ? copyOf((Collection) iterable) : copyOf(iterable.iterator());
    }

    /* renamed from: of */
    public static <E> ImmutableList<E> m17573of() {
        return (ImmutableList<E>) RegularImmutableList.EMPTY;
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Use SerializedForm");
    }

    public static <E extends Comparable<? super E>> ImmutableList<E> sortedCopyOf(Iterable<? extends E> iterable) {
        Comparable[] comparableArr = (Comparable[]) Iterables.toArray(iterable, new Comparable[0]);
        ObjectArrays.checkElementsNotNull(comparableArr);
        Arrays.sort(comparableArr);
        return asImmutableList(comparableArr);
    }

    @Override // java.util.List
    @Deprecated
    public final void add(int i9, E e9) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    @CanIgnoreReturnValue
    @Deprecated
    public final boolean addAll(int i9, Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.ImmutableCollection
    public final ImmutableList<E> asList() {
        return this;
    }

    @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public int copyIntoArray(Object[] objArr, int i9) {
        int size = size();
        for (int i10 = 0; i10 < size; i10++) {
            objArr[i9 + i10] = get(i10);
        }
        return i9 + size;
    }

    @Override // java.util.Collection, java.util.List
    public boolean equals(Object obj) {
        return Lists.equalsImpl(this, obj);
    }

    @Override // java.util.Collection, java.util.List
    public int hashCode() {
        int size = size();
        int i9 = 1;
        for (int i10 = 0; i10 < size; i10++) {
            i9 = ~(~((i9 * 31) + get(i10).hashCode()));
        }
        return i9;
    }

    @Override // java.util.List
    public int indexOf(Object obj) {
        if (obj == null) {
            return -1;
        }
        return Lists.indexOfImpl(this, obj);
    }

    @Override // java.util.List
    public int lastIndexOf(Object obj) {
        if (obj == null) {
            return -1;
        }
        return Lists.lastIndexOfImpl(this, obj);
    }

    @Override // java.util.List
    @CanIgnoreReturnValue
    @Deprecated
    public final E remove(int i9) {
        throw new UnsupportedOperationException();
    }

    public ImmutableList<E> reverse() {
        return size() <= 1 ? this : new ReverseImmutableList(this);
    }

    @Override // java.util.List
    @CanIgnoreReturnValue
    @Deprecated
    public final E set(int i9, E e9) {
        throw new UnsupportedOperationException();
    }

    public ImmutableList<E> subListUnchecked(int i9, int i10) {
        return new SubList(i9, i10 - i9);
    }

    @Override // com.google.common.collect.ImmutableCollection
    public Object writeReplace() {
        return new SerializedForm(toArray());
    }

    public static <E> ImmutableList<E> asImmutableList(Object[] objArr, int i9) {
        return i9 == 0 ? m17573of() : new RegularImmutableList(objArr, i9);
    }

    /* renamed from: of */
    public static <E> ImmutableList<E> m17574of(E e9) {
        return construct(e9);
    }

    @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
    public UnmodifiableIterator<E> iterator() {
        return listIterator();
    }

    @Override // java.util.List
    public ImmutableList<E> subList(int i9, int i10) {
        Preconditions.checkPositionIndexes(i9, i10, size());
        int i11 = i10 - i9;
        return i11 == size() ? this : i11 == 0 ? m17573of() : subListUnchecked(i9, i10);
    }

    /* renamed from: of */
    public static <E> ImmutableList<E> m17575of(E e9, E e10) {
        return construct(e9, e10);
    }

    @Override // java.util.List
    public UnmodifiableListIterator<E> listIterator() {
        return listIterator(0);
    }

    /* renamed from: of */
    public static <E> ImmutableList<E> m17576of(E e9, E e10, E e11) {
        return construct(e9, e10, e11);
    }

    @Override // java.util.List
    public UnmodifiableListIterator<E> listIterator(int i9) {
        return new AbstractIndexedListIterator<E>(size(), i9) { // from class: com.google.common.collect.ImmutableList.1
            @Override // com.google.common.collect.AbstractIndexedListIterator
            public E get(int i10) {
                return ImmutableList.this.get(i10);
            }
        };
    }

    public static <E> ImmutableList<E> copyOf(Collection<? extends E> collection) {
        if (collection instanceof ImmutableCollection) {
            ImmutableList<E> immutableListAsList = ((ImmutableCollection) collection).asList();
            return immutableListAsList.isPartialView() ? asImmutableList(immutableListAsList.toArray()) : immutableListAsList;
        }
        return construct(collection.toArray());
    }

    /* renamed from: of */
    public static <E> ImmutableList<E> m17577of(E e9, E e10, E e11, E e12) {
        return construct(e9, e10, e11, e12);
    }

    public static <E> ImmutableList<E> sortedCopyOf(Comparator<? super E> comparator, Iterable<? extends E> iterable) {
        Preconditions.checkNotNull(comparator);
        Object[] array = Iterables.toArray(iterable);
        ObjectArrays.checkElementsNotNull(array);
        Arrays.sort(array, comparator);
        return asImmutableList(array);
    }

    /* renamed from: of */
    public static <E> ImmutableList<E> m17578of(E e9, E e10, E e11, E e12, E e13) {
        return construct(e9, e10, e11, e12, e13);
    }

    /* renamed from: of */
    public static <E> ImmutableList<E> m17579of(E e9, E e10, E e11, E e12, E e13, E e14) {
        return construct(e9, e10, e11, e12, e13, e14);
    }

    /* renamed from: of */
    public static <E> ImmutableList<E> m17580of(E e9, E e10, E e11, E e12, E e13, E e14, E e15) {
        return construct(e9, e10, e11, e12, e13, e14, e15);
    }

    public static <E> ImmutableList<E> copyOf(Iterator<? extends E> it) {
        if (!it.hasNext()) {
            return m17573of();
        }
        E next = it.next();
        if (!it.hasNext()) {
            return m17574of((Object) next);
        }
        return new Builder().add((Builder) next).addAll((Iterator) it).build();
    }

    /* renamed from: of */
    public static <E> ImmutableList<E> m17581of(E e9, E e10, E e11, E e12, E e13, E e14, E e15, E e16) {
        return construct(e9, e10, e11, e12, e13, e14, e15, e16);
    }

    /* renamed from: of */
    public static <E> ImmutableList<E> m17582of(E e9, E e10, E e11, E e12, E e13, E e14, E e15, E e16, E e17) {
        return construct(e9, e10, e11, e12, e13, e14, e15, e16, e17);
    }

    /* renamed from: of */
    public static <E> ImmutableList<E> m17583of(E e9, E e10, E e11, E e12, E e13, E e14, E e15, E e16, E e17, E e18) {
        return construct(e9, e10, e11, e12, e13, e14, e15, e16, e17, e18);
    }

    /* renamed from: of */
    public static <E> ImmutableList<E> m17584of(E e9, E e10, E e11, E e12, E e13, E e14, E e15, E e16, E e17, E e18, E e19) {
        return construct(e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19);
    }

    @SafeVarargs
    /* renamed from: of */
    public static <E> ImmutableList<E> m17585of(E e9, E e10, E e11, E e12, E e13, E e14, E e15, E e16, E e17, E e18, E e19, E e20, E... eArr) {
        Object[] objArr = new Object[eArr.length + 12];
        objArr[0] = e9;
        objArr[1] = e10;
        objArr[2] = e11;
        objArr[3] = e12;
        objArr[4] = e13;
        objArr[5] = e14;
        objArr[6] = e15;
        objArr[7] = e16;
        objArr[8] = e17;
        objArr[9] = e18;
        objArr[10] = e19;
        objArr[11] = e20;
        System.arraycopy(eArr, 0, objArr, 12, eArr.length);
        return construct(objArr);
    }

    public static <E> ImmutableList<E> copyOf(E[] eArr) {
        if (eArr.length == 0) {
            return m17573of();
        }
        return construct((Object[]) eArr.clone());
    }
}
