package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

@GwtCompatible
/* loaded from: classes2.dex */
final class CombinedFuture<V> extends AggregateFuture<Object, V> {

    public final class AsyncCallableInterruptibleTask extends CombinedFuture<V>.CombinedFutureInterruptibleTask {
        private final AsyncCallable<V> callable;

        public AsyncCallableInterruptibleTask(AsyncCallable<V> asyncCallable, Executor executor) {
            super(executor);
            this.callable = (AsyncCallable) Preconditions.checkNotNull(asyncCallable);
        }

        @Override // com.google.common.util.concurrent.CombinedFuture.CombinedFutureInterruptibleTask
        public void setValue() {
            CombinedFuture.this.setFuture(this.callable.call());
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask, java.util.concurrent.atomic.AtomicReference
        public String toString() {
            return this.callable.toString();
        }
    }

    public final class CallableInterruptibleTask extends CombinedFuture<V>.CombinedFutureInterruptibleTask {
        private final Callable<V> callable;

        public CallableInterruptibleTask(Callable<V> callable, Executor executor) {
            super(executor);
            this.callable = (Callable) Preconditions.checkNotNull(callable);
        }

        @Override // com.google.common.util.concurrent.CombinedFuture.CombinedFutureInterruptibleTask
        public void setValue() {
            CombinedFuture.this.set(this.callable.call());
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask, java.util.concurrent.atomic.AtomicReference
        public String toString() {
            return this.callable.toString();
        }
    }

    public abstract class CombinedFutureInterruptibleTask extends InterruptibleTask {
        private final Executor listenerExecutor;
        volatile boolean thrownByExecute = true;

        public CombinedFutureInterruptibleTask(Executor executor) {
            this.listenerExecutor = (Executor) Preconditions.checkNotNull(executor);
        }

        public final void execute() {
            try {
                this.listenerExecutor.execute(this);
            } catch (RejectedExecutionException e9) {
                if (this.thrownByExecute) {
                    CombinedFuture.this.setException(e9);
                }
            }
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        public final void runInterruptibly() {
            this.thrownByExecute = false;
            if (CombinedFuture.this.isDone()) {
                return;
            }
            try {
                setValue();
            } catch (CancellationException unused) {
                CombinedFuture.this.cancel(false);
            } catch (ExecutionException e9) {
                CombinedFuture.this.setException(e9.getCause());
            } catch (Throwable th) {
                CombinedFuture.this.setException(th);
            }
        }

        public abstract void setValue();

        @Override // com.google.common.util.concurrent.InterruptibleTask
        public final boolean wasInterrupted() {
            return CombinedFuture.this.wasInterrupted();
        }
    }

    public final class CombinedFutureRunningState extends AggregateFuture<Object, V>.RunningState {
        private CombinedFuture<V>.CombinedFutureInterruptibleTask task;

        public CombinedFutureRunningState(ImmutableCollection<? extends ListenableFuture<? extends Object>> immutableCollection, boolean z8, CombinedFuture<V>.CombinedFutureInterruptibleTask combinedFutureInterruptibleTask) {
            super(immutableCollection, z8, false);
            this.task = combinedFutureInterruptibleTask;
        }

        @Override // com.google.common.util.concurrent.AggregateFuture.RunningState
        public void collectOneValue(boolean z8, int i9, Object obj) {
        }

        @Override // com.google.common.util.concurrent.AggregateFuture.RunningState
        public void handleAllCompleted() {
            CombinedFuture<V>.CombinedFutureInterruptibleTask combinedFutureInterruptibleTask = this.task;
            if (combinedFutureInterruptibleTask != null) {
                combinedFutureInterruptibleTask.execute();
            } else {
                Preconditions.checkState(CombinedFuture.this.isDone());
            }
        }

        @Override // com.google.common.util.concurrent.AggregateFuture.RunningState
        public void interruptTask() {
            CombinedFuture<V>.CombinedFutureInterruptibleTask combinedFutureInterruptibleTask = this.task;
            if (combinedFutureInterruptibleTask != null) {
                combinedFutureInterruptibleTask.interruptTask();
            }
        }

        @Override // com.google.common.util.concurrent.AggregateFuture.RunningState
        public void releaseResourcesAfterFailure() {
            super.releaseResourcesAfterFailure();
            this.task = null;
        }
    }

    public CombinedFuture(ImmutableCollection<? extends ListenableFuture<?>> immutableCollection, boolean z8, Executor executor, AsyncCallable<V> asyncCallable) {
        init(new CombinedFutureRunningState(immutableCollection, z8, new AsyncCallableInterruptibleTask(asyncCallable, executor)));
    }

    public CombinedFuture(ImmutableCollection<? extends ListenableFuture<?>> immutableCollection, boolean z8, Executor executor, Callable<V> callable) {
        init(new CombinedFutureRunningState(immutableCollection, z8, new CallableInterruptibleTask(callable, executor)));
    }
}
