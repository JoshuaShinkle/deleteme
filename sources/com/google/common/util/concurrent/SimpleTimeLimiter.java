package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ObjectArrays;
import com.google.common.collect.Sets;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Beta
@GwtIncompatible
/* loaded from: classes2.dex */
public final class SimpleTimeLimiter implements TimeLimiter {
    private final ExecutorService executor;

    private SimpleTimeLimiter(ExecutorService executorService) {
        this.executor = (ExecutorService) Preconditions.checkNotNull(executorService);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> T callWithTimeout(Callable<T> callable, long j9, TimeUnit timeUnit, boolean z8) throws Exception {
        Preconditions.checkNotNull(callable);
        Preconditions.checkNotNull(timeUnit);
        checkPositiveTimeout(j9);
        Future<T> futureSubmit = this.executor.submit(callable);
        try {
            if (!z8) {
                return (T) Uninterruptibles.getUninterruptibly(futureSubmit, j9, timeUnit);
            }
            try {
                return futureSubmit.get(j9, timeUnit);
            } catch (InterruptedException e9) {
                futureSubmit.cancel(true);
                throw e9;
            }
        } catch (ExecutionException e10) {
            throw throwCause(e10, true);
        } catch (TimeoutException e11) {
            futureSubmit.cancel(true);
            throw new UncheckedTimeoutException(e11);
        }
    }

    private static void checkPositiveTimeout(long j9) {
        Preconditions.checkArgument(j9 > 0, "timeout must be positive: %s", j9);
    }

    public static SimpleTimeLimiter create(ExecutorService executorService) {
        return new SimpleTimeLimiter(executorService);
    }

    private static boolean declaresInterruptedEx(Method method) {
        for (Class<?> cls : method.getExceptionTypes()) {
            if (cls == InterruptedException.class) {
                return true;
            }
        }
        return false;
    }

    private static Set<Method> findInterruptibleMethods(Class<?> cls) throws SecurityException {
        HashSet hashSetNewHashSet = Sets.newHashSet();
        for (Method method : cls.getMethods()) {
            if (declaresInterruptedEx(method)) {
                hashSetNewHashSet.add(method);
            }
        }
        return hashSetNewHashSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Exception throwCause(Exception exc, boolean z8) throws Exception {
        Throwable cause = exc.getCause();
        if (cause == null) {
            throw exc;
        }
        if (z8) {
            cause.setStackTrace((StackTraceElement[]) ObjectArrays.concat(cause.getStackTrace(), exc.getStackTrace(), StackTraceElement.class));
        }
        if (cause instanceof Exception) {
            throw ((Exception) cause);
        }
        if (cause instanceof Error) {
            throw ((Error) cause);
        }
        throw exc;
    }

    private void wrapAndThrowExecutionExceptionOrError(Throwable th) throws ExecutionException {
        if (th instanceof Error) {
            throw new ExecutionError((Error) th);
        }
        if (!(th instanceof RuntimeException)) {
            throw new ExecutionException(th);
        }
        throw new UncheckedExecutionException(th);
    }

    private void wrapAndThrowRuntimeExecutionExceptionOrError(Throwable th) {
        if (!(th instanceof Error)) {
            throw new UncheckedExecutionException(th);
        }
        throw new ExecutionError((Error) th);
    }

    @Override // com.google.common.util.concurrent.TimeLimiter
    public <T> T callUninterruptiblyWithTimeout(Callable<T> callable, long j9, TimeUnit timeUnit) throws ExecutionException, TimeoutException {
        Preconditions.checkNotNull(callable);
        Preconditions.checkNotNull(timeUnit);
        checkPositiveTimeout(j9);
        Future<T> futureSubmit = this.executor.submit(callable);
        try {
            return (T) Uninterruptibles.getUninterruptibly(futureSubmit, j9, timeUnit);
        } catch (ExecutionException e9) {
            wrapAndThrowExecutionExceptionOrError(e9.getCause());
            throw new AssertionError();
        } catch (TimeoutException e10) {
            futureSubmit.cancel(true);
            throw e10;
        }
    }

    @Override // com.google.common.util.concurrent.TimeLimiter
    public <T> T newProxy(final T t8, Class<T> cls, final long j9, final TimeUnit timeUnit) throws SecurityException {
        Preconditions.checkNotNull(t8);
        Preconditions.checkNotNull(cls);
        Preconditions.checkNotNull(timeUnit);
        checkPositiveTimeout(j9);
        Preconditions.checkArgument(cls.isInterface(), "interfaceType must be an interface type");
        final Set<Method> setFindInterruptibleMethods = findInterruptibleMethods(cls);
        return (T) newProxy(cls, new InvocationHandler() { // from class: com.google.common.util.concurrent.SimpleTimeLimiter.1
            @Override // java.lang.reflect.InvocationHandler
            public Object invoke(Object obj, final Method method, final Object[] objArr) {
                return SimpleTimeLimiter.this.callWithTimeout(new Callable<Object>() { // from class: com.google.common.util.concurrent.SimpleTimeLimiter.1.1
                    @Override // java.util.concurrent.Callable
                    public Object call() throws Exception {
                        try {
                            return method.invoke(t8, objArr);
                        } catch (InvocationTargetException e9) {
                            throw SimpleTimeLimiter.throwCause(e9, false);
                        }
                    }
                }, j9, timeUnit, setFindInterruptibleMethods.contains(method));
            }
        });
    }

    @Override // com.google.common.util.concurrent.TimeLimiter
    public void runUninterruptiblyWithTimeout(Runnable runnable, long j9, TimeUnit timeUnit) throws TimeoutException {
        Preconditions.checkNotNull(runnable);
        Preconditions.checkNotNull(timeUnit);
        checkPositiveTimeout(j9);
        Future<?> futureSubmit = this.executor.submit(runnable);
        try {
            Uninterruptibles.getUninterruptibly(futureSubmit, j9, timeUnit);
        } catch (ExecutionException e9) {
            wrapAndThrowRuntimeExecutionExceptionOrError(e9.getCause());
            throw new AssertionError();
        } catch (TimeoutException e10) {
            futureSubmit.cancel(true);
            throw e10;
        }
    }

    @Override // com.google.common.util.concurrent.TimeLimiter
    public void runWithTimeout(Runnable runnable, long j9, TimeUnit timeUnit) throws Throwable {
        Preconditions.checkNotNull(runnable);
        Preconditions.checkNotNull(timeUnit);
        checkPositiveTimeout(j9);
        Future<?> futureSubmit = this.executor.submit(runnable);
        try {
            futureSubmit.get(j9, timeUnit);
        } catch (InterruptedException e9) {
            e = e9;
            futureSubmit.cancel(true);
            throw e;
        } catch (ExecutionException e10) {
            wrapAndThrowRuntimeExecutionExceptionOrError(e10.getCause());
            throw new AssertionError();
        } catch (TimeoutException e11) {
            e = e11;
            futureSubmit.cancel(true);
            throw e;
        }
    }

    private static <T> T newProxy(Class<T> cls, InvocationHandler invocationHandler) {
        return cls.cast(Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, invocationHandler));
    }

    @Override // com.google.common.util.concurrent.TimeLimiter
    public <T> T callWithTimeout(Callable<T> callable, long j9, TimeUnit timeUnit) throws Throwable {
        Preconditions.checkNotNull(callable);
        Preconditions.checkNotNull(timeUnit);
        checkPositiveTimeout(j9);
        Future<T> futureSubmit = this.executor.submit(callable);
        try {
            return futureSubmit.get(j9, timeUnit);
        } catch (InterruptedException e9) {
            e = e9;
            futureSubmit.cancel(true);
            throw e;
        } catch (ExecutionException e10) {
            wrapAndThrowExecutionExceptionOrError(e10.getCause());
            throw new AssertionError();
        } catch (TimeoutException e11) {
            e = e11;
            futureSubmit.cancel(true);
            throw e;
        }
    }
}
