package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.ForwardingQueue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

@CanIgnoreReturnValue
@GwtIncompatible
/* loaded from: classes2.dex */
public abstract class ForwardingBlockingQueue<E> extends ForwardingQueue<E> implements BlockingQueue<E> {
    @Override // com.google.common.collect.ForwardingQueue, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
    public abstract BlockingQueue<E> delegate();

    @Override // java.util.concurrent.BlockingQueue
    public int drainTo(Collection<? super E> collection, int i9) {
        return delegate().drainTo(collection, i9);
    }

    @Override // java.util.concurrent.BlockingQueue
    public boolean offer(E e9, long j9, TimeUnit timeUnit) {
        return delegate().offer(e9, j9, timeUnit);
    }

    @Override // java.util.concurrent.BlockingQueue
    public E poll(long j9, TimeUnit timeUnit) {
        return delegate().poll(j9, timeUnit);
    }

    @Override // java.util.concurrent.BlockingQueue
    public void put(E e9) throws InterruptedException {
        delegate().put(e9);
    }

    @Override // java.util.concurrent.BlockingQueue
    public int remainingCapacity() {
        return delegate().remainingCapacity();
    }

    @Override // java.util.concurrent.BlockingQueue
    public E take() {
        return delegate().take();
    }

    @Override // java.util.concurrent.BlockingQueue
    public int drainTo(Collection<? super E> collection) {
        return delegate().drainTo(collection);
    }
}
