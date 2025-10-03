package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

@GwtCompatible
/* loaded from: classes2.dex */
final class ForwardingFluentFuture<V> extends FluentFuture<V> {
    private final ListenableFuture<V> delegate;

    public ForwardingFluentFuture(ListenableFuture<V> listenableFuture) {
        this.delegate = (ListenableFuture) Preconditions.checkNotNull(listenableFuture);
    }

    @Override // com.google.common.util.concurrent.ListenableFuture
    public void addListener(Runnable runnable, Executor executor) {
        this.delegate.addListener(runnable, executor);
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z8) {
        return this.delegate.cancel(z8);
    }

    @Override // java.util.concurrent.Future
    public V get() {
        return this.delegate.get();
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return this.delegate.isCancelled();
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return this.delegate.isDone();
    }

    @Override // java.util.concurrent.Future
    public V get(long j9, TimeUnit timeUnit) {
        return this.delegate.get(j9, timeUnit);
    }
}
