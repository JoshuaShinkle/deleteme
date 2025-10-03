package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;

@GwtCompatible(emulated = true, serializable = true)
/* loaded from: classes2.dex */
final class RegularImmutableSet<E> extends ImmutableSet<E> {
    static final RegularImmutableSet<Object> EMPTY = new RegularImmutableSet<>(new Object[0], 0, null, 0, 0);

    @VisibleForTesting
    final transient Object[] elements;
    private final transient int hashCode;
    private final transient int mask;
    private final transient int size;

    @VisibleForTesting
    final transient Object[] table;

    public RegularImmutableSet(Object[] objArr, int i9, Object[] objArr2, int i10, int i11) {
        this.elements = objArr;
        this.table = objArr2;
        this.mask = i10;
        this.hashCode = i9;
        this.size = i11;
    }

    @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        Object[] objArr = this.table;
        if (obj == null || objArr == null) {
            return false;
        }
        int iSmearedHash = Hashing.smearedHash(obj);
        while (true) {
            int i9 = iSmearedHash & this.mask;
            Object obj2 = objArr[i9];
            if (obj2 == null) {
                return false;
            }
            if (obj2.equals(obj)) {
                return true;
            }
            iSmearedHash = i9 + 1;
        }
    }

    @Override // com.google.common.collect.ImmutableCollection
    public int copyIntoArray(Object[] objArr, int i9) {
        System.arraycopy(this.elements, 0, objArr, i9, this.size);
        return i9 + this.size;
    }

    @Override // com.google.common.collect.ImmutableSet
    public ImmutableList<E> createAsList() {
        return ImmutableList.asImmutableList(this.elements, this.size);
    }

    @Override // com.google.common.collect.ImmutableSet, java.util.Collection, java.util.Set
    public int hashCode() {
        return this.hashCode;
    }

    @Override // com.google.common.collect.ImmutableSet
    public boolean isHashCodeFast() {
        return true;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public boolean isPartialView() {
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public int size() {
        return this.size;
    }

    @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
    public UnmodifiableIterator<E> iterator() {
        return Iterators.forArray(this.elements, 0, this.size, 0);
    }
}
