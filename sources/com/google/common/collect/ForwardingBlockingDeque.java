package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import java.util.Collection;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.TimeUnit;

@GwtIncompatible
@Deprecated
/* loaded from: classes2.dex */
public abstract class ForwardingBlockingDeque<E> extends ForwardingDeque<E> implements BlockingDeque<E> {
    @Override // com.google.common.collect.ForwardingDeque, com.google.common.collect.ForwardingQueue, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
    public abstract BlockingDeque<E> delegate();

    @Override // java.util.concurrent.BlockingQueue
    public int drainTo(Collection<? super E> collection) {
        return delegate().drainTo(collection);
    }

    @Override // java.util.concurrent.BlockingDeque, java.util.concurrent.BlockingQueue
    public boolean offer(E e9, long j9, TimeUnit timeUnit) {
        return delegate().offer(e9, j9, timeUnit);
    }

    @Override // java.util.concurrent.BlockingDeque
    public boolean offerFirst(E e9, long j9, TimeUnit timeUnit) {
        return delegate().offerFirst(e9, j9, timeUnit);
    }

    @Override // java.util.concurrent.BlockingDeque
    public boolean offerLast(E e9, long j9, TimeUnit timeUnit) {
        return delegate().offerLast(e9, j9, timeUnit);
    }

    @Override // java.util.concurrent.BlockingDeque, java.util.concurrent.BlockingQueue
    public E poll(long j9, TimeUnit timeUnit) {
        return delegate().poll(j9, timeUnit);
    }

    @Override // java.util.concurrent.BlockingDeque
    public E pollFirst(long j9, TimeUnit timeUnit) {
        return delegate().pollFirst(j9, timeUnit);
    }

    @Override // java.util.concurrent.BlockingDeque
    public E pollLast(long j9, TimeUnit timeUnit) {
        return delegate().pollLast(j9, timeUnit);
    }

    @Override // java.util.concurrent.BlockingDeque, java.util.concurrent.BlockingQueue
    public void put(E e9) throws InterruptedException {
        delegate().put(e9);
    }

    @Override // java.util.concurrent.BlockingDeque
    public void putFirst(E e9) throws InterruptedException {
        delegate().putFirst(e9);
    }

    @Override // java.util.concurrent.BlockingDeque
    public void putLast(E e9) throws InterruptedException {
        delegate().putLast(e9);
    }

    @Override // java.util.concurrent.BlockingQueue
    public int remainingCapacity() {
        return delegate().remainingCapacity();
    }

    @Override // java.util.concurrent.BlockingDeque, java.util.concurrent.BlockingQueue
    public E take() {
        return delegate().take();
    }

    @Override // java.util.concurrent.BlockingDeque
    public E takeFirst() {
        return delegate().takeFirst();
    }

    @Override // java.util.concurrent.BlockingDeque
    public E takeLast() {
        return delegate().takeLast();
    }

    @Override // java.util.concurrent.BlockingQueue
    public int drainTo(Collection<? super E> collection, int i9) {
        return delegate().drainTo(collection, i9);
    }
}
