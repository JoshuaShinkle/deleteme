package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Queue;

@Beta
@GwtCompatible
/* loaded from: classes2.dex */
public final class EvictingQueue<E> extends ForwardingQueue<E> implements Serializable {
    private static final long serialVersionUID = 0;
    private final Queue<E> delegate;

    @VisibleForTesting
    final int maxSize;

    private EvictingQueue(int i9) {
        Preconditions.checkArgument(i9 >= 0, "maxSize (%s) must >= 0", i9);
        this.delegate = new ArrayDeque(i9);
        this.maxSize = i9;
    }

    public static <E> EvictingQueue<E> create(int i9) {
        return new EvictingQueue<>(i9);
    }

    @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Queue
    @CanIgnoreReturnValue
    public boolean add(E e9) {
        Preconditions.checkNotNull(e9);
        if (this.maxSize == 0) {
            return true;
        }
        if (size() == this.maxSize) {
            this.delegate.remove();
        }
        this.delegate.add(e9);
        return true;
    }

    @Override // com.google.common.collect.ForwardingCollection, java.util.Collection
    @CanIgnoreReturnValue
    public boolean addAll(Collection<? extends E> collection) {
        int size = collection.size();
        if (size < this.maxSize) {
            return standardAddAll(collection);
        }
        clear();
        return Iterables.addAll(this, Iterables.skip(collection, size - this.maxSize));
    }

    @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        return delegate().contains(Preconditions.checkNotNull(obj));
    }

    @Override // com.google.common.collect.ForwardingQueue, java.util.Queue
    @CanIgnoreReturnValue
    public boolean offer(E e9) {
        return add(e9);
    }

    public int remainingCapacity() {
        return this.maxSize - size();
    }

    @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
    @CanIgnoreReturnValue
    public boolean remove(Object obj) {
        return delegate().remove(Preconditions.checkNotNull(obj));
    }

    @Override // com.google.common.collect.ForwardingQueue, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
    public Queue<E> delegate() {
        return this.delegate;
    }
}
