package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;

@GwtCompatible(emulated = true)
/* loaded from: classes2.dex */
class RegularImmutableAsList<E> extends ImmutableAsList<E> {
    private final ImmutableCollection<E> delegate;
    private final ImmutableList<? extends E> delegateList;

    public RegularImmutableAsList(ImmutableCollection<E> immutableCollection, ImmutableList<? extends E> immutableList) {
        this.delegate = immutableCollection;
        this.delegateList = immutableList;
    }

    @Override // com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableCollection
    @GwtIncompatible
    public int copyIntoArray(Object[] objArr, int i9) {
        return this.delegateList.copyIntoArray(objArr, i9);
    }

    @Override // com.google.common.collect.ImmutableAsList
    public ImmutableCollection<E> delegateCollection() {
        return this.delegate;
    }

    public ImmutableList<? extends E> delegateList() {
        return this.delegateList;
    }

    @Override // java.util.List
    public E get(int i9) {
        return this.delegateList.get(i9);
    }

    @Override // com.google.common.collect.ImmutableList, java.util.List
    public UnmodifiableListIterator<E> listIterator(int i9) {
        return this.delegateList.listIterator(i9);
    }

    public RegularImmutableAsList(ImmutableCollection<E> immutableCollection, Object[] objArr) {
        this(immutableCollection, ImmutableList.asImmutableList(objArr));
    }

    public RegularImmutableAsList(ImmutableCollection<E> immutableCollection, Object[] objArr, int i9) {
        this(immutableCollection, ImmutableList.asImmutableList(objArr, i9));
    }
}
