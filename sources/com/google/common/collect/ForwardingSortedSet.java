package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.SortedSet;

@GwtCompatible
/* loaded from: classes2.dex */
public abstract class ForwardingSortedSet<E> extends ForwardingSet<E> implements SortedSet<E> {
    private int unsafeCompare(Object obj, Object obj2) {
        Comparator<? super E> comparator = comparator();
        return comparator == null ? ((Comparable) obj).compareTo(obj2) : comparator.compare(obj, obj2);
    }

    @Override // java.util.SortedSet
    public Comparator<? super E> comparator() {
        return delegate().comparator();
    }

    @Override // com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
    public abstract SortedSet<E> delegate();

    @Override // java.util.SortedSet
    public E first() {
        return delegate().first();
    }

    @Override // java.util.SortedSet
    public SortedSet<E> headSet(E e9) {
        return delegate().headSet(e9);
    }

    @Override // java.util.SortedSet
    public E last() {
        return delegate().last();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.ForwardingCollection
    @Beta
    public boolean standardContains(Object obj) {
        try {
            return unsafeCompare(tailSet(obj).first(), obj) == 0;
        } catch (ClassCastException | NullPointerException | NoSuchElementException unused) {
            return false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.ForwardingCollection
    @Beta
    public boolean standardRemove(Object obj) {
        try {
            Iterator<E> it = tailSet(obj).iterator();
            if (it.hasNext() && unsafeCompare(it.next(), obj) == 0) {
                it.remove();
                return true;
            }
        } catch (ClassCastException | NullPointerException unused) {
        }
        return false;
    }

    @Beta
    public SortedSet<E> standardSubSet(E e9, E e10) {
        return tailSet(e9).headSet(e10);
    }

    @Override // java.util.SortedSet
    public SortedSet<E> subSet(E e9, E e10) {
        return delegate().subSet(e9, e10);
    }

    @Override // java.util.SortedSet
    public SortedSet<E> tailSet(E e9) {
        return delegate().tailSet(e9);
    }
}
