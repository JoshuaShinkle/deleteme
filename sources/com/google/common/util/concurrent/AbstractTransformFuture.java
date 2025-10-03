package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.errorprone.annotations.ForOverride;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

@GwtCompatible
/* loaded from: classes2.dex */
abstract class AbstractTransformFuture<I, O, F, T> extends AbstractFuture.TrustedFuture<O> implements Runnable {
    F function;
    ListenableFuture<? extends I> inputFuture;

    public static final class AsyncTransformFuture<I, O> extends AbstractTransformFuture<I, O, AsyncFunction<? super I, ? extends O>, ListenableFuture<? extends O>> {
        public AsyncTransformFuture(ListenableFuture<? extends I> listenableFuture, AsyncFunction<? super I, ? extends O> asyncFunction) {
            super(listenableFuture, asyncFunction);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.util.concurrent.AbstractTransformFuture
        public /* bridge */ /* synthetic */ Object doTransform(Object obj, Object obj2) {
            return doTransform((AsyncFunction<? super AsyncFunction<? super I, ? extends O>, ? extends O>) obj, (AsyncFunction<? super I, ? extends O>) obj2);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public ListenableFuture<? extends O> doTransform(AsyncFunction<? super I, ? extends O> asyncFunction, I i9) {
            ListenableFuture<? extends O> listenableFutureApply = asyncFunction.apply(i9);
            Preconditions.checkNotNull(listenableFutureApply, "AsyncFunction.apply returned null instead of a Future. Did you mean to return immediateFuture(null)?");
            return listenableFutureApply;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.util.concurrent.AbstractTransformFuture
        public void setResult(ListenableFuture<? extends O> listenableFuture) {
            setFuture(listenableFuture);
        }
    }

    public static final class TransformFuture<I, O> extends AbstractTransformFuture<I, O, Function<? super I, ? extends O>, O> {
        public TransformFuture(ListenableFuture<? extends I> listenableFuture, Function<? super I, ? extends O> function) {
            super(listenableFuture, function);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.util.concurrent.AbstractTransformFuture
        public /* bridge */ /* synthetic */ Object doTransform(Object obj, Object obj2) {
            return doTransform((Function<? super Function<? super I, ? extends O>, ? extends O>) obj, (Function<? super I, ? extends O>) obj2);
        }

        @Override // com.google.common.util.concurrent.AbstractTransformFuture
        public void setResult(O o8) {
            set(o8);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public O doTransform(Function<? super I, ? extends O> function, I i9) {
            return function.apply(i9);
        }
    }

    public AbstractTransformFuture(ListenableFuture<? extends I> listenableFuture, F f9) {
        this.inputFuture = (ListenableFuture) Preconditions.checkNotNull(listenableFuture);
        this.function = (F) Preconditions.checkNotNull(f9);
    }

    public static <I, O> ListenableFuture<O> create(ListenableFuture<I> listenableFuture, AsyncFunction<? super I, ? extends O> asyncFunction) {
        AsyncTransformFuture asyncTransformFuture = new AsyncTransformFuture(listenableFuture, asyncFunction);
        listenableFuture.addListener(asyncTransformFuture, MoreExecutors.directExecutor());
        return asyncTransformFuture;
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public final void afterDone() {
        maybePropagateCancellation(this.inputFuture);
        this.inputFuture = null;
        this.function = null;
    }

    @ForOverride
    public abstract T doTransform(F f9, I i9);

    @Override // com.google.common.util.concurrent.AbstractFuture
    public String pendingToString() {
        ListenableFuture<? extends I> listenableFuture = this.inputFuture;
        F f9 = this.function;
        if (listenableFuture == null || f9 == null) {
            return null;
        }
        return "inputFuture=[" + listenableFuture + "], function=[" + f9 + "]";
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.lang.Runnable
    public final void run() {
        ListenableFuture<? extends I> listenableFuture = this.inputFuture;
        F f9 = this.function;
        if ((isCancelled() | (listenableFuture == null)) || (f9 == null)) {
            return;
        }
        this.inputFuture = null;
        this.function = null;
        try {
            try {
                setResult(doTransform(f9, Futures.getDone(listenableFuture)));
            } catch (UndeclaredThrowableException e9) {
                setException(e9.getCause());
            } catch (Throwable th) {
                setException(th);
            }
        } catch (Error e10) {
            setException(e10);
        } catch (CancellationException unused) {
            cancel(false);
        } catch (RuntimeException e11) {
            setException(e11);
        } catch (ExecutionException e12) {
            setException(e12.getCause());
        }
    }

    @ForOverride
    public abstract void setResult(T t8);

    public static <I, O> ListenableFuture<O> create(ListenableFuture<I> listenableFuture, AsyncFunction<? super I, ? extends O> asyncFunction, Executor executor) {
        Preconditions.checkNotNull(executor);
        AsyncTransformFuture asyncTransformFuture = new AsyncTransformFuture(listenableFuture, asyncFunction);
        listenableFuture.addListener(asyncTransformFuture, MoreExecutors.rejectionPropagatingExecutor(executor, asyncTransformFuture));
        return asyncTransformFuture;
    }

    public static <I, O> ListenableFuture<O> create(ListenableFuture<I> listenableFuture, Function<? super I, ? extends O> function) {
        Preconditions.checkNotNull(function);
        TransformFuture transformFuture = new TransformFuture(listenableFuture, function);
        listenableFuture.addListener(transformFuture, MoreExecutors.directExecutor());
        return transformFuture;
    }

    public static <I, O> ListenableFuture<O> create(ListenableFuture<I> listenableFuture, Function<? super I, ? extends O> function, Executor executor) {
        Preconditions.checkNotNull(function);
        TransformFuture transformFuture = new TransformFuture(listenableFuture, function);
        listenableFuture.addListener(transformFuture, MoreExecutors.rejectionPropagatingExecutor(executor, transformFuture));
        return transformFuture;
    }
}
