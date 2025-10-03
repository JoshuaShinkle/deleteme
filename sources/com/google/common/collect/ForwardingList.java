package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@GwtCompatible
/* loaded from: classes2.dex */
public abstract class ForwardingList<E> extends ForwardingCollection<E> implements List<E> {
    public void add(int i9, E e9) {
        delegate().add(i9, e9);
    }

    @CanIgnoreReturnValue
    public boolean addAll(int i9, Collection<? extends E> collection) {
        return delegate().addAll(i9, collection);
    }

    @Override // com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
    public abstract List<E> delegate();

    @Override // java.util.Collection, java.util.List
    public boolean equals(Object obj) {
        return obj == this || delegate().equals(obj);
    }

    @Override // java.util.List
    public E get(int i9) {
        return delegate().get(i9);
    }

    @Override // java.util.Collection, java.util.List
    public int hashCode() {
        return delegate().hashCode();
    }

    @Override // java.util.List
    public int indexOf(Object obj) {
        return delegate().indexOf(obj);
    }

    @Override // java.util.List
    public int lastIndexOf(Object obj) {
        return delegate().lastIndexOf(obj);
    }

    @Override // java.util.List
    public ListIterator<E> listIterator() {
        return delegate().listIterator();
    }

    @Override // java.util.List
    @CanIgnoreReturnValue
    public E remove(int i9) {
        return delegate().remove(i9);
    }

    @Override // java.util.List
    @CanIgnoreReturnValue
    public E set(int i9, E e9) {
        return delegate().set(i9, e9);
    }

    public boolean standardAdd(E e9) {
        add(size(), e9);
        return true;
    }

    public boolean standardAddAll(int i9, Iterable<? extends E> iterable) {
        return Lists.addAllImpl(this, i9, iterable);
    }

    @Beta
    public boolean standardEquals(Object obj) {
        return Lists.equalsImpl(this, obj);
    }

    @Beta
    public int standardHashCode() {
        return Lists.hashCodeImpl(this);
    }

    public int standardIndexOf(Object obj) {
        return Lists.indexOfImpl(this, obj);
    }

    public Iterator<E> standardIterator() {
        return listIterator();
    }

    public int standardLastIndexOf(Object obj) {
        return Lists.lastIndexOfImpl(this, obj);
    }

    public ListIterator<E> standardListIterator() {
        return listIterator(0);
    }

    @Beta
    public List<E> standardSubList(int i9, int i10) {
        return Lists.subListImpl(this, i9, i10);
    }

    @Override // java.util.List
    public List<E> subList(int i9, int i10) {
        return delegate().subList(i9, i10);
    }

    @Override // java.util.List
    public ListIterator<E> listIterator(int i9) {
        return delegate().listIterator(i9);
    }

    @Beta
    public ListIterator<E> standardListIterator(int i9) {
        return Lists.listIteratorImpl(this, i9);
    }
}
