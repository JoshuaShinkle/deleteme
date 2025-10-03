package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;

@GwtCompatible(emulated = true, serializable = true)
/* loaded from: classes2.dex */
class RegularImmutableList<E> extends ImmutableList<E> {
    static final ImmutableList<Object> EMPTY = new RegularImmutableList(new Object[0], 0);

    @VisibleForTesting
    final transient Object[] array;
    private final transient int size;

    public RegularImmutableList(Object[] objArr, int i9) {
        this.array = objArr;
        this.size = i9;
    }

    @Override // com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableCollection
    public int copyIntoArray(Object[] objArr, int i9) {
        System.arraycopy(this.array, 0, objArr, i9, this.size);
        return i9 + this.size;
    }

    @Override // java.util.List
    public E get(int i9) {
        Preconditions.checkElementIndex(i9, this.size);
        return (E) this.array[i9];
    }

    @Override // com.google.common.collect.ImmutableCollection
    public boolean isPartialView() {
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.size;
    }

    @Override // com.google.common.collect.ImmutableList, java.util.List
    public UnmodifiableListIterator<E> listIterator(int i9) {
        return Iterators.forArray(this.array, 0, this.size, i9);
    }
}
