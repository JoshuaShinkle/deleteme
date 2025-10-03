package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.NoSuchElementException;

@GwtCompatible
/* loaded from: classes2.dex */
public abstract class AbstractSequentialIterator<T> extends UnmodifiableIterator<T> {
    private T nextOrNull;

    public AbstractSequentialIterator(T t8) {
        this.nextOrNull = t8;
    }

    public abstract T computeNext(T t8);

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.nextOrNull != null;
    }

    @Override // java.util.Iterator
    public final T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        try {
            T t8 = this.nextOrNull;
            this.nextOrNull = computeNext(t8);
            return t8;
        } catch (Throwable th) {
            this.nextOrNull = computeNext(this.nextOrNull);
            throw th;
        }
    }
}
