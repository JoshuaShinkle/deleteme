package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.NoSuchElementException;

@GwtCompatible
/* loaded from: classes2.dex */
abstract class AbstractIndexedListIterator<E> extends UnmodifiableListIterator<E> {
    private int position;
    private final int size;

    public AbstractIndexedListIterator(int i9) {
        this(i9, 0);
    }

    public abstract E get(int i9);

    @Override // java.util.Iterator, java.util.ListIterator
    public final boolean hasNext() {
        return this.position < this.size;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.position > 0;
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int i9 = this.position;
        this.position = i9 + 1;
        return get(i9);
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.position;
    }

    @Override // java.util.ListIterator
    public final E previous() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        int i9 = this.position - 1;
        this.position = i9;
        return get(i9);
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.position - 1;
    }

    public AbstractIndexedListIterator(int i9, int i10) {
        Preconditions.checkPositionIndex(i10, i9);
        this.size = i9;
        this.position = i10;
    }
}
