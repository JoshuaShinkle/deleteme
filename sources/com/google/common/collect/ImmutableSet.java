package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.j2objc.annotations.RetainedWith;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;

@GwtCompatible(emulated = true, serializable = true)
/* loaded from: classes2.dex */
public abstract class ImmutableSet<E> extends ImmutableCollection<E> implements Set<E> {
    private static final int CUTOFF = 751619276;
    private static final double DESIRED_LOAD_FACTOR = 0.7d;
    static final int MAX_TABLE_SIZE = 1073741824;

    @RetainedWith
    @LazyInit
    private transient ImmutableList<E> asList;

    public static class Builder<E> extends ImmutableCollection.ArrayBasedBuilder<E> {
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
        public ImmutableSet<E> build() {
            ImmutableSet<E> immutableSetConstruct = ImmutableSet.construct(this.size, this.contents);
            this.size = immutableSetConstruct.size();
            this.forceCopy = true;
            return immutableSetConstruct;
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

    public static abstract class Indexed<E> extends ImmutableSet<E> {
        @Override // com.google.common.collect.ImmutableSet
        public ImmutableList<E> createAsList() {
            return new ImmutableList<E>() { // from class: com.google.common.collect.ImmutableSet.Indexed.1
                @Override // java.util.List
                public E get(int i9) {
                    return (E) Indexed.this.get(i9);
                }

                @Override // com.google.common.collect.ImmutableCollection
                public boolean isPartialView() {
                    return Indexed.this.isPartialView();
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
                public int size() {
                    return Indexed.this.size();
                }
            };
        }

        public abstract E get(int i9);

        @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
        public UnmodifiableIterator<E> iterator() {
            return asList().iterator();
        }
    }

    public static class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        final Object[] elements;

        public SerializedForm(Object[] objArr) {
            this.elements = objArr;
        }

        public Object readResolve() {
            return ImmutableSet.copyOf(this.elements);
        }
    }

    public static <E> Builder<E> builder() {
        return new Builder<>();
    }

    @VisibleForTesting
    static int chooseTableSize(int i9) {
        if (i9 >= CUTOFF) {
            Preconditions.checkArgument(i9 < 1073741824, "collection too large");
            return 1073741824;
        }
        int iHighestOneBit = Integer.highestOneBit(i9 - 1) << 1;
        while (iHighestOneBit * DESIRED_LOAD_FACTOR < i9) {
            iHighestOneBit <<= 1;
        }
        return iHighestOneBit;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <E> ImmutableSet<E> construct(int i9, Object... objArr) {
        if (i9 == 0) {
            return m17615of();
        }
        if (i9 == 1) {
            return m17616of(objArr[0]);
        }
        int iChooseTableSize = chooseTableSize(i9);
        Object[] objArr2 = new Object[iChooseTableSize];
        int i10 = iChooseTableSize - 1;
        int i11 = 0;
        int i12 = 0;
        for (int i13 = 0; i13 < i9; i13++) {
            Object objCheckElementNotNull = ObjectArrays.checkElementNotNull(objArr[i13], i13);
            int iHashCode = objCheckElementNotNull.hashCode();
            int iSmear = Hashing.smear(iHashCode);
            while (true) {
                int i14 = iSmear & i10;
                Object obj = objArr2[i14];
                if (obj == null) {
                    objArr[i12] = objCheckElementNotNull;
                    objArr2[i14] = objCheckElementNotNull;
                    i11 += iHashCode;
                    i12++;
                    break;
                }
                if (obj.equals(objCheckElementNotNull)) {
                    break;
                }
                iSmear++;
            }
        }
        Arrays.fill(objArr, i12, i9, (Object) null);
        if (i12 == 1) {
            return new SingletonImmutableSet(objArr[0], i11);
        }
        if (chooseTableSize(i12) < iChooseTableSize / 2) {
            return construct(i12, objArr);
        }
        if (i12 < objArr.length / 2) {
            objArr = Arrays.copyOf(objArr, i12);
        }
        return new RegularImmutableSet(objArr, i11, objArr2, i10, i12);
    }

    public static <E> ImmutableSet<E> copyOf(Collection<? extends E> collection) {
        if ((collection instanceof ImmutableSet) && !(collection instanceof SortedSet)) {
            ImmutableSet<E> immutableSet = (ImmutableSet) collection;
            if (!immutableSet.isPartialView()) {
                return immutableSet;
            }
        }
        Object[] array = collection.toArray();
        return construct(array.length, array);
    }

    /* renamed from: of */
    public static <E> ImmutableSet<E> m17615of() {
        return RegularImmutableSet.EMPTY;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public ImmutableList<E> asList() {
        ImmutableList<E> immutableList = this.asList;
        if (immutableList != null) {
            return immutableList;
        }
        ImmutableList<E> immutableListCreateAsList = createAsList();
        this.asList = immutableListCreateAsList;
        return immutableListCreateAsList;
    }

    public ImmutableList<E> createAsList() {
        return ImmutableList.asImmutableList(toArray());
    }

    @Override // java.util.Collection, java.util.Set
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof ImmutableSet) && isHashCodeFast() && ((ImmutableSet) obj).isHashCodeFast() && hashCode() != obj.hashCode()) {
            return false;
        }
        return Sets.equalsImpl(this, obj);
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        return Sets.hashCodeImpl(this);
    }

    public boolean isHashCodeFast() {
        return false;
    }

    @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
    public abstract UnmodifiableIterator<E> iterator();

    @Override // com.google.common.collect.ImmutableCollection
    Object writeReplace() {
        return new SerializedForm(toArray());
    }

    /* renamed from: of */
    public static <E> ImmutableSet<E> m17616of(E e9) {
        return new SingletonImmutableSet(e9);
    }

    /* renamed from: of */
    public static <E> ImmutableSet<E> m17617of(E e9, E e10) {
        return construct(2, e9, e10);
    }

    /* renamed from: of */
    public static <E> ImmutableSet<E> m17618of(E e9, E e10, E e11) {
        return construct(3, e9, e10, e11);
    }

    /* renamed from: of */
    public static <E> ImmutableSet<E> m17619of(E e9, E e10, E e11, E e12) {
        return construct(4, e9, e10, e11, e12);
    }

    public static <E> ImmutableSet<E> copyOf(Iterable<? extends E> iterable) {
        if (iterable instanceof Collection) {
            return copyOf((Collection) iterable);
        }
        return copyOf(iterable.iterator());
    }

    /* renamed from: of */
    public static <E> ImmutableSet<E> m17620of(E e9, E e10, E e11, E e12, E e13) {
        return construct(5, e9, e10, e11, e12, e13);
    }

    @SafeVarargs
    /* renamed from: of */
    public static <E> ImmutableSet<E> m17621of(E e9, E e10, E e11, E e12, E e13, E e14, E... eArr) {
        int length = eArr.length + 6;
        Object[] objArr = new Object[length];
        objArr[0] = e9;
        objArr[1] = e10;
        objArr[2] = e11;
        objArr[3] = e12;
        objArr[4] = e13;
        objArr[5] = e14;
        System.arraycopy(eArr, 0, objArr, 6, eArr.length);
        return construct(length, objArr);
    }

    public static <E> ImmutableSet<E> copyOf(Iterator<? extends E> it) {
        if (!it.hasNext()) {
            return m17615of();
        }
        E next = it.next();
        if (!it.hasNext()) {
            return m17616of((Object) next);
        }
        return new Builder().add((Builder) next).addAll((Iterator) it).build();
    }

    public static <E> ImmutableSet<E> copyOf(E[] eArr) {
        int length = eArr.length;
        if (length == 0) {
            return m17615of();
        }
        if (length != 1) {
            return construct(eArr.length, (Object[]) eArr.clone());
        }
        return m17616of((Object) eArr[0]);
    }
}
