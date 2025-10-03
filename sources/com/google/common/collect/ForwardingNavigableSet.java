package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.SortedSet;

@GwtIncompatible
/* loaded from: classes2.dex */
public abstract class ForwardingNavigableSet<E> extends ForwardingSortedSet<E> implements NavigableSet<E> {

    @Beta
    public class StandardDescendingSet extends Sets.DescendingSet<E> {
        public StandardDescendingSet() {
            super(ForwardingNavigableSet.this);
        }
    }

    @Override // java.util.NavigableSet
    public E ceiling(E e9) {
        return delegate().ceiling(e9);
    }

    @Override // com.google.common.collect.ForwardingSortedSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
    public abstract NavigableSet<E> delegate();

    @Override // java.util.NavigableSet
    public Iterator<E> descendingIterator() {
        return delegate().descendingIterator();
    }

    @Override // java.util.NavigableSet
    public NavigableSet<E> descendingSet() {
        return delegate().descendingSet();
    }

    @Override // java.util.NavigableSet
    public E floor(E e9) {
        return delegate().floor(e9);
    }

    @Override // java.util.NavigableSet
    public NavigableSet<E> headSet(E e9, boolean z8) {
        return delegate().headSet(e9, z8);
    }

    @Override // java.util.NavigableSet
    public E higher(E e9) {
        return delegate().higher(e9);
    }

    @Override // java.util.NavigableSet
    public E lower(E e9) {
        return delegate().lower(e9);
    }

    @Override // java.util.NavigableSet
    public E pollFirst() {
        return delegate().pollFirst();
    }

    @Override // java.util.NavigableSet
    public E pollLast() {
        return delegate().pollLast();
    }

    public E standardCeiling(E e9) {
        return (E) Iterators.getNext(tailSet(e9, true).iterator(), null);
    }

    public E standardFirst() {
        return iterator().next();
    }

    public E standardFloor(E e9) {
        return (E) Iterators.getNext(headSet(e9, true).descendingIterator(), null);
    }

    public SortedSet<E> standardHeadSet(E e9) {
        return headSet(e9, false);
    }

    public E standardHigher(E e9) {
        return (E) Iterators.getNext(tailSet(e9, false).iterator(), null);
    }

    public E standardLast() {
        return descendingIterator().next();
    }

    public E standardLower(E e9) {
        return (E) Iterators.getNext(headSet(e9, false).descendingIterator(), null);
    }

    public E standardPollFirst() {
        return (E) Iterators.pollNext(iterator());
    }

    public E standardPollLast() {
        return (E) Iterators.pollNext(descendingIterator());
    }

    @Beta
    public NavigableSet<E> standardSubSet(E e9, boolean z8, E e10, boolean z9) {
        return tailSet(e9, z8).headSet(e10, z9);
    }

    public SortedSet<E> standardTailSet(E e9) {
        return tailSet(e9, true);
    }

    @Override // java.util.NavigableSet
    public NavigableSet<E> subSet(E e9, boolean z8, E e10, boolean z9) {
        return delegate().subSet(e9, z8, e10, z9);
    }

    @Override // java.util.NavigableSet
    public NavigableSet<E> tailSet(E e9, boolean z8) {
        return delegate().tailSet(e9, z8);
    }

    @Override // com.google.common.collect.ForwardingSortedSet
    public SortedSet<E> standardSubSet(E e9, E e10) {
        return subSet(e9, true, e10, false);
    }
}
