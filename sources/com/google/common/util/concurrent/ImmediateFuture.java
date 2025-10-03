package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.AbstractFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

@GwtCompatible(emulated = true)
/* loaded from: classes2.dex */
abstract class ImmediateFuture<V> extends FluentFuture<V> {
    private static final Logger log = Logger.getLogger(ImmediateFuture.class.getName());

    public static final class ImmediateCancelledFuture<V> extends AbstractFuture.TrustedFuture<V> {
        public ImmediateCancelledFuture() {
            cancel(false);
        }
    }

    @GwtIncompatible
    public static class ImmediateFailedCheckedFuture<V, X extends Exception> extends ImmediateFuture<V> implements CheckedFuture<V, X> {
        private final X thrown;

        public ImmediateFailedCheckedFuture(X x8) {
            this.thrown = x8;
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: X extends java.lang.Exception */
        @Override // com.google.common.util.concurrent.CheckedFuture
        public V checkedGet() throws X {
            throw this.thrown;
        }

        @Override // com.google.common.util.concurrent.ImmediateFuture, java.util.concurrent.Future
        public V get() throws ExecutionException {
            throw new ExecutionException(this.thrown);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: X extends java.lang.Exception */
        @Override // com.google.common.util.concurrent.CheckedFuture
        public V checkedGet(long j9, TimeUnit timeUnit) throws X {
            Preconditions.checkNotNull(timeUnit);
            throw this.thrown;
        }
    }

    public static final class ImmediateFailedFuture<V> extends AbstractFuture.TrustedFuture<V> {
        public ImmediateFailedFuture(Throwable th) {
            setException(th);
        }
    }

    @GwtIncompatible
    public static class ImmediateSuccessfulCheckedFuture<V, X extends Exception> extends ImmediateFuture<V> implements CheckedFuture<V, X> {
        private final V value;

        public ImmediateSuccessfulCheckedFuture(V v8) {
            this.value = v8;
        }

        @Override // com.google.common.util.concurrent.CheckedFuture
        public V checkedGet() {
            return this.value;
        }

        @Override // com.google.common.util.concurrent.ImmediateFuture, java.util.concurrent.Future
        public V get() {
            return this.value;
        }

        @Override // com.google.common.util.concurrent.CheckedFuture
        public V checkedGet(long j9, TimeUnit timeUnit) {
            Preconditions.checkNotNull(timeUnit);
            return this.value;
        }
    }

    public static class ImmediateSuccessfulFuture<V> extends ImmediateFuture<V> {
        static final ImmediateSuccessfulFuture<Object> NULL = new ImmediateSuccessfulFuture<>(null);
        private final V value;

        public ImmediateSuccessfulFuture(V v8) {
            this.value = v8;
        }

        @Override // com.google.common.util.concurrent.ImmediateFuture, java.util.concurrent.Future
        public V get() {
            return this.value;
        }
    }

    @Override // com.google.common.util.concurrent.ListenableFuture
    public void addListener(Runnable runnable, Executor executor) {
        Preconditions.checkNotNull(runnable, "Runnable was null.");
        Preconditions.checkNotNull(executor, "Executor was null.");
        try {
            executor.execute(runnable);
        } catch (RuntimeException e9) {
            log.log(Level.SEVERE, "RuntimeException while executing runnable " + runnable + " with executor " + executor, (Throwable) e9);
        }
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z8) {
        return false;
    }

    @Override // java.util.concurrent.Future
    public abstract V get();

    @Override // java.util.concurrent.Future
    public V get(long j9, TimeUnit timeUnit) {
        Preconditions.checkNotNull(timeUnit);
        return get();
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return false;
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return true;
    }
}
