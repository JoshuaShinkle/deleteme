package org.apache.commons.lang3.concurrent;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class ConcurrentUtils {

    public static final class ConstantFuture<T> implements Future<T> {
        private final T value;

        public ConstantFuture(T t8) {
            this.value = t8;
        }

        @Override // java.util.concurrent.Future
        public boolean cancel(boolean z8) {
            return false;
        }

        @Override // java.util.concurrent.Future
        public T get() {
            return this.value;
        }

        @Override // java.util.concurrent.Future
        public boolean isCancelled() {
            return false;
        }

        @Override // java.util.concurrent.Future
        public boolean isDone() {
            return true;
        }

        @Override // java.util.concurrent.Future
        public T get(long j9, TimeUnit timeUnit) {
            return this.value;
        }
    }

    private ConcurrentUtils() {
    }

    public static Throwable checkedException(Throwable th) {
        if (th != null && !(th instanceof RuntimeException) && !(th instanceof Error)) {
            return th;
        }
        throw new IllegalArgumentException("Not a checked exception: " + th);
    }

    public static <T> Future<T> constantFuture(T t8) {
        return new ConstantFuture(t8);
    }

    public static <K, V> V createIfAbsent(ConcurrentMap<K, V> concurrentMap, K k9, ConcurrentInitializer<V> concurrentInitializer) {
        if (concurrentMap == null || concurrentInitializer == null) {
            return null;
        }
        V v8 = concurrentMap.get(k9);
        return v8 == null ? (V) putIfAbsent(concurrentMap, k9, concurrentInitializer.get()) : v8;
    }

    public static <K, V> V createIfAbsentUnchecked(ConcurrentMap<K, V> concurrentMap, K k9, ConcurrentInitializer<V> concurrentInitializer) {
        try {
            return (V) createIfAbsent(concurrentMap, k9, concurrentInitializer);
        } catch (ConcurrentException e9) {
            throw new ConcurrentRuntimeException(e9.getCause());
        }
    }

    public static ConcurrentException extractCause(ExecutionException executionException) {
        if (executionException == null || executionException.getCause() == null) {
            return null;
        }
        throwCause(executionException);
        return new ConcurrentException(executionException.getMessage(), executionException.getCause());
    }

    public static ConcurrentRuntimeException extractCauseUnchecked(ExecutionException executionException) {
        if (executionException == null || executionException.getCause() == null) {
            return null;
        }
        throwCause(executionException);
        return new ConcurrentRuntimeException(executionException.getMessage(), executionException.getCause());
    }

    public static void handleCause(ExecutionException executionException) throws ConcurrentException {
        ConcurrentException concurrentExceptionExtractCause = extractCause(executionException);
        if (concurrentExceptionExtractCause != null) {
            throw concurrentExceptionExtractCause;
        }
    }

    public static void handleCauseUnchecked(ExecutionException executionException) {
        ConcurrentRuntimeException concurrentRuntimeExceptionExtractCauseUnchecked = extractCauseUnchecked(executionException);
        if (concurrentRuntimeExceptionExtractCauseUnchecked != null) {
            throw concurrentRuntimeExceptionExtractCauseUnchecked;
        }
    }

    public static <T> T initialize(ConcurrentInitializer<T> concurrentInitializer) {
        if (concurrentInitializer != null) {
            return concurrentInitializer.get();
        }
        return null;
    }

    public static <T> T initializeUnchecked(ConcurrentInitializer<T> concurrentInitializer) {
        try {
            return (T) initialize(concurrentInitializer);
        } catch (ConcurrentException e9) {
            throw new ConcurrentRuntimeException(e9.getCause());
        }
    }

    public static <K, V> V putIfAbsent(ConcurrentMap<K, V> concurrentMap, K k9, V v8) {
        if (concurrentMap == null) {
            return null;
        }
        V vPutIfAbsent = concurrentMap.putIfAbsent(k9, v8);
        return vPutIfAbsent != null ? vPutIfAbsent : v8;
    }

    private static void throwCause(ExecutionException executionException) {
        if (executionException.getCause() instanceof RuntimeException) {
            throw ((RuntimeException) executionException.getCause());
        }
        if (executionException.getCause() instanceof Error) {
            throw ((Error) executionException.getCause());
        }
    }
}
