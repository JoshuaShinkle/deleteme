package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.concurrent.LazyInit;

@GwtCompatible(emulated = true, serializable = true)
/* loaded from: classes2.dex */
final class SingletonImmutableSet<E> extends ImmutableSet<E> {

    @LazyInit
    private transient int cachedHashCode;
    final transient E element;

    public SingletonImmutableSet(E e9) {
        this.element = (E) Preconditions.checkNotNull(e9);
    }

    @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        return this.element.equals(obj);
    }

    @Override // com.google.common.collect.ImmutableCollection
    public int copyIntoArray(Object[] objArr, int i9) {
        objArr[i9] = this.element;
        return i9 + 1;
    }

    @Override // com.google.common.collect.ImmutableSet
    public ImmutableList<E> createAsList() {
        return ImmutableList.m17574of((Object) this.element);
    }

    @Override // com.google.common.collect.ImmutableSet, java.util.Collection, java.util.Set
    public final int hashCode() {
        int i9 = this.cachedHashCode;
        if (i9 != 0) {
            return i9;
        }
        int iHashCode = this.element.hashCode();
        this.cachedHashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.google.common.collect.ImmutableSet
    public boolean isHashCodeFast() {
        return this.cachedHashCode != 0;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public boolean isPartialView() {
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public int size() {
        return 1;
    }

    @Override // java.util.AbstractCollection
    public String toString() {
        return '[' + this.element.toString() + ']';
    }

    @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
    public UnmodifiableIterator<E> iterator() {
        return Iterators.singletonIterator(this.element);
    }

    public SingletonImmutableSet(E e9, int i9) {
        this.element = e9;
        this.cachedHashCode = i9;
    }
}
