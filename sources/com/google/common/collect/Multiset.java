package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.CompatibleWith;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

@GwtCompatible
/* loaded from: classes2.dex */
public interface Multiset<E> extends Collection<E> {

    public interface Entry<E> {
        boolean equals(Object obj);

        int getCount();

        E getElement();

        int hashCode();

        String toString();
    }

    @CanIgnoreReturnValue
    int add(E e9, int i9);

    @CanIgnoreReturnValue
    boolean add(E e9);

    boolean contains(Object obj);

    @Override // java.util.Collection
    boolean containsAll(Collection<?> collection);

    int count(@CompatibleWith("E") Object obj);

    Set<E> elementSet();

    Set<Entry<E>> entrySet();

    boolean equals(Object obj);

    int hashCode();

    Iterator<E> iterator();

    @CanIgnoreReturnValue
    int remove(@CompatibleWith("E") Object obj, int i9);

    @CanIgnoreReturnValue
    boolean remove(Object obj);

    @CanIgnoreReturnValue
    boolean removeAll(Collection<?> collection);

    @CanIgnoreReturnValue
    boolean retainAll(Collection<?> collection);

    @CanIgnoreReturnValue
    int setCount(E e9, int i9);

    @CanIgnoreReturnValue
    boolean setCount(E e9, int i9, int i10);

    int size();

    String toString();
}
