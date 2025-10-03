package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.AbstractFuture;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.RunnableFuture;

@GwtCompatible
/* loaded from: classes2.dex */
class TrustedListenableFutureTask<V> extends AbstractFuture.TrustedFuture<V> implements RunnableFuture<V> {
    private InterruptibleTask task;

    public final class TrustedFutureInterruptibleAsyncTask extends InterruptibleTask {
        private final AsyncCallable<V> callable;

        public TrustedFutureInterruptibleAsyncTask(AsyncCallable<V> asyncCallable) {
            this.callable = (AsyncCallable) Preconditions.checkNotNull(asyncCallable);
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        public void runInterruptibly() {
            if (TrustedListenableFutureTask.this.isDone()) {
                return;
            }
            try {
                ListenableFuture<V> listenableFutureCall = this.callable.call();
                Preconditions.checkNotNull(listenableFutureCall, "AsyncCallable.call returned null instead of a Future. Did you mean to return immediateFuture(null)?");
                TrustedListenableFutureTask.this.setFuture(listenableFutureCall);
            } catch (Throwable th) {
                TrustedListenableFutureTask.this.setException(th);
            }
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask, java.util.concurrent.atomic.AtomicReference
        public String toString() {
            return this.callable.toString();
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        public boolean wasInterrupted() {
            return TrustedListenableFutureTask.this.wasInterrupted();
        }
    }

    public final class TrustedFutureInterruptibleTask extends InterruptibleTask {
        private final Callable<V> callable;

        public TrustedFutureInterruptibleTask(Callable<V> callable) {
            this.callable = (Callable) Preconditions.checkNotNull(callable);
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        public void runInterruptibly() {
            if (TrustedListenableFutureTask.this.isDone()) {
                return;
            }
            try {
                TrustedListenableFutureTask.this.set(this.callable.call());
            } catch (Throwable th) {
                TrustedListenableFutureTask.this.setException(th);
            }
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask, java.util.concurrent.atomic.AtomicReference
        public String toString() {
            return this.callable.toString();
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        public boolean wasInterrupted() {
            return TrustedListenableFutureTask.this.wasInterrupted();
        }
    }

    public TrustedListenableFutureTask(Callable<V> callable) {
        this.task = new TrustedFutureInterruptibleTask(callable);
    }

    public static <V> TrustedListenableFutureTask<V> create(AsyncCallable<V> asyncCallable) {
        return new TrustedListenableFutureTask<>(asyncCallable);
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public void afterDone() {
        InterruptibleTask interruptibleTask;
        super.afterDone();
        if (wasInterrupted() && (interruptibleTask = this.task) != null) {
            interruptibleTask.interruptTask();
        }
        this.task = null;
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public String pendingToString() {
        InterruptibleTask interruptibleTask = this.task;
        if (interruptibleTask == null) {
            return null;
        }
        return "task=[" + interruptibleTask + "]";
    }

    @Override // java.util.concurrent.RunnableFuture, java.lang.Runnable
    public void run() {
        InterruptibleTask interruptibleTask = this.task;
        if (interruptibleTask != null) {
            interruptibleTask.run();
        }
    }

    public static <V> TrustedListenableFutureTask<V> create(Callable<V> callable) {
        return new TrustedListenableFutureTask<>(callable);
    }

    public TrustedListenableFutureTask(AsyncCallable<V> asyncCallable) {
        this.task = new TrustedFutureInterruptibleAsyncTask(asyncCallable);
    }

    public static <V> TrustedListenableFutureTask<V> create(Runnable runnable, V v8) {
        return new TrustedListenableFutureTask<>(Executors.callable(runnable, v8));
    }
}
