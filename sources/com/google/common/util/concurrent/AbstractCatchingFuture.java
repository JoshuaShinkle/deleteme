package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.errorprone.annotations.ForOverride;
import java.lang.Throwable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

@GwtCompatible
/* loaded from: classes2.dex */
abstract class AbstractCatchingFuture<V, X extends Throwable, F, T> extends AbstractFuture.TrustedFuture<V> implements Runnable {
    Class<X> exceptionType;
    F fallback;
    ListenableFuture<? extends V> inputFuture;

    public static final class AsyncCatchingFuture<V, X extends Throwable> extends AbstractCatchingFuture<V, X, AsyncFunction<? super X, ? extends V>, ListenableFuture<? extends V>> {
        public AsyncCatchingFuture(ListenableFuture<? extends V> listenableFuture, Class<X> cls, AsyncFunction<? super X, ? extends V> asyncFunction) {
            super(listenableFuture, cls, asyncFunction);
        }

        @Override // com.google.common.util.concurrent.AbstractCatchingFuture
        public /* bridge */ /* synthetic */ Object doFallback(Object obj, Throwable th) {
            return doFallback((AsyncFunction<? super AsyncFunction<? super X, ? extends V>, ? extends V>) obj, (AsyncFunction<? super X, ? extends V>) th);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public ListenableFuture<? extends V> doFallback(AsyncFunction<? super X, ? extends V> asyncFunction, X x8) {
            ListenableFuture<? extends V> listenableFutureApply = asyncFunction.apply(x8);
            Preconditions.checkNotNull(listenableFutureApply, "AsyncFunction.apply returned null instead of a Future. Did you mean to return immediateFuture(null)?");
            return listenableFutureApply;
        }

        @Override // com.google.common.util.concurrent.AbstractCatchingFuture
        public void setResult(ListenableFuture<? extends V> listenableFuture) {
            setFuture(listenableFuture);
        }
    }

    public static final class CatchingFuture<V, X extends Throwable> extends AbstractCatchingFuture<V, X, Function<? super X, ? extends V>, V> {
        public CatchingFuture(ListenableFuture<? extends V> listenableFuture, Class<X> cls, Function<? super X, ? extends V> function) {
            super(listenableFuture, cls, function);
        }

        @Override // com.google.common.util.concurrent.AbstractCatchingFuture
        public /* bridge */ /* synthetic */ Object doFallback(Object obj, Throwable th) {
            return doFallback((Function<? super Function<? super X, ? extends V>, ? extends V>) obj, (Function<? super X, ? extends V>) th);
        }

        @Override // com.google.common.util.concurrent.AbstractCatchingFuture
        public void setResult(V v8) {
            set(v8);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public V doFallback(Function<? super X, ? extends V> function, X x8) {
            return function.apply(x8);
        }
    }

    public AbstractCatchingFuture(ListenableFuture<? extends V> listenableFuture, Class<X> cls, F f9) {
        this.inputFuture = (ListenableFuture) Preconditions.checkNotNull(listenableFuture);
        this.exceptionType = (Class) Preconditions.checkNotNull(cls);
        this.fallback = (F) Preconditions.checkNotNull(f9);
    }

    public static <X extends Throwable, V> ListenableFuture<V> create(ListenableFuture<? extends V> listenableFuture, Class<X> cls, Function<? super X, ? extends V> function) {
        CatchingFuture catchingFuture = new CatchingFuture(listenableFuture, cls, function);
        listenableFuture.addListener(catchingFuture, MoreExecutors.directExecutor());
        return catchingFuture;
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public final void afterDone() {
        maybePropagateCancellation(this.inputFuture);
        this.inputFuture = null;
        this.exceptionType = null;
        this.fallback = null;
    }

    @ForOverride
    public abstract T doFallback(F f9, X x8);

    @Override // com.google.common.util.concurrent.AbstractFuture
    public String pendingToString() {
        ListenableFuture<? extends V> listenableFuture = this.inputFuture;
        Class<X> cls = this.exceptionType;
        F f9 = this.fallback;
        if (listenableFuture == null || cls == null || f9 == null) {
            return null;
        }
        return "input=[" + listenableFuture + "], exceptionType=[" + cls + "], fallback=[" + f9 + "]";
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:25:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0042  */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void run() {
        Object done;
        ListenableFuture<? extends V> listenableFuture = this.inputFuture;
        Class<X> cls = this.exceptionType;
        F f9 = this.fallback;
        if (((f9 == null) | (listenableFuture == null) | (cls == null)) || isCancelled()) {
            return;
        }
        Throwable th = null;
        this.inputFuture = null;
        this.exceptionType = null;
        this.fallback = null;
        try {
            done = Futures.getDone(listenableFuture);
        } catch (ExecutionException e9) {
            th = (Throwable) Preconditions.checkNotNull(e9.getCause());
            th = th;
            done = null;
            if (th == null) {
            }
        } catch (Throwable th2) {
            th = th2;
            th = th;
            done = null;
            if (th == null) {
            }
        }
        if (th == null) {
            set(done);
            return;
        }
        if (!Platform.isInstanceOfThrowableClass(th, cls)) {
            setException(th);
            return;
        }
        try {
            setResult(doFallback(f9, th));
        } catch (Throwable th3) {
            setException(th3);
        }
    }

    @ForOverride
    public abstract void setResult(T t8);

    public static <V, X extends Throwable> ListenableFuture<V> create(ListenableFuture<? extends V> listenableFuture, Class<X> cls, Function<? super X, ? extends V> function, Executor executor) {
        CatchingFuture catchingFuture = new CatchingFuture(listenableFuture, cls, function);
        listenableFuture.addListener(catchingFuture, MoreExecutors.rejectionPropagatingExecutor(executor, catchingFuture));
        return catchingFuture;
    }

    public static <X extends Throwable, V> ListenableFuture<V> create(ListenableFuture<? extends V> listenableFuture, Class<X> cls, AsyncFunction<? super X, ? extends V> asyncFunction) {
        AsyncCatchingFuture asyncCatchingFuture = new AsyncCatchingFuture(listenableFuture, cls, asyncFunction);
        listenableFuture.addListener(asyncCatchingFuture, MoreExecutors.directExecutor());
        return asyncCatchingFuture;
    }

    public static <X extends Throwable, V> ListenableFuture<V> create(ListenableFuture<? extends V> listenableFuture, Class<X> cls, AsyncFunction<? super X, ? extends V> asyncFunction, Executor executor) {
        AsyncCatchingFuture asyncCatchingFuture = new AsyncCatchingFuture(listenableFuture, cls, asyncFunction);
        listenableFuture.addListener(asyncCatchingFuture, MoreExecutors.rejectionPropagatingExecutor(executor, asyncCatchingFuture));
        return asyncCatchingFuture;
    }
}
