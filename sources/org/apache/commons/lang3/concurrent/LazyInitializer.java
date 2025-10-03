package org.apache.commons.lang3.concurrent;

/* loaded from: classes.dex */
public abstract class LazyInitializer<T> implements ConcurrentInitializer<T> {
    private volatile T object;

    @Override // org.apache.commons.lang3.concurrent.ConcurrentInitializer
    public T get() {
        T tInitialize = this.object;
        if (tInitialize == null) {
            synchronized (this) {
                tInitialize = this.object;
                if (tInitialize == null) {
                    tInitialize = initialize();
                    this.object = tInitialize;
                }
            }
        }
        return tInitialize;
    }

    public abstract T initialize();
}
