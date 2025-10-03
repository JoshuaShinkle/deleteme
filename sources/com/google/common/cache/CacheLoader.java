package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

@GwtCompatible(emulated = true)
/* loaded from: classes2.dex */
public abstract class CacheLoader<K, V> {

    public static final class FunctionToCacheLoader<K, V> extends CacheLoader<K, V> implements Serializable {
        private static final long serialVersionUID = 0;
        private final Function<K, V> computingFunction;

        public FunctionToCacheLoader(Function<K, V> function) {
            this.computingFunction = (Function) Preconditions.checkNotNull(function);
        }

        @Override // com.google.common.cache.CacheLoader
        public V load(K k9) {
            return (V) this.computingFunction.apply(Preconditions.checkNotNull(k9));
        }
    }

    public static final class InvalidCacheLoadException extends RuntimeException {
        public InvalidCacheLoadException(String str) {
            super(str);
        }
    }

    public static final class SupplierToCacheLoader<V> extends CacheLoader<Object, V> implements Serializable {
        private static final long serialVersionUID = 0;
        private final Supplier<V> computingSupplier;

        public SupplierToCacheLoader(Supplier<V> supplier) {
            this.computingSupplier = (Supplier) Preconditions.checkNotNull(supplier);
        }

        @Override // com.google.common.cache.CacheLoader
        public V load(Object obj) {
            Preconditions.checkNotNull(obj);
            return this.computingSupplier.get();
        }
    }

    public static final class UnsupportedLoadingOperationException extends UnsupportedOperationException {
    }

    @GwtIncompatible
    public static <K, V> CacheLoader<K, V> asyncReloading(CacheLoader<K, V> cacheLoader, final Executor executor) {
        Preconditions.checkNotNull(cacheLoader);
        Preconditions.checkNotNull(executor);
        return new CacheLoader<K, V>() { // from class: com.google.common.cache.CacheLoader.1
            @Override // com.google.common.cache.CacheLoader
            public V load(K k9) {
                return (V) CacheLoader.this.load(k9);
            }

            @Override // com.google.common.cache.CacheLoader
            public Map<K, V> loadAll(Iterable<? extends K> iterable) {
                return CacheLoader.this.loadAll(iterable);
            }

            @Override // com.google.common.cache.CacheLoader
            public ListenableFuture<V> reload(final K k9, final V v8) {
                ListenableFutureTask listenableFutureTaskCreate = ListenableFutureTask.create(new Callable<V>() { // from class: com.google.common.cache.CacheLoader.1.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.concurrent.Callable
                    public V call() {
                        return CacheLoader.this.reload(k9, v8).get();
                    }
                });
                executor.execute(listenableFutureTaskCreate);
                return listenableFutureTaskCreate;
            }
        };
    }

    public static <K, V> CacheLoader<K, V> from(Function<K, V> function) {
        return new FunctionToCacheLoader(function);
    }

    public abstract V load(K k9);

    public Map<K, V> loadAll(Iterable<? extends K> iterable) {
        throw new UnsupportedLoadingOperationException();
    }

    @GwtIncompatible
    public ListenableFuture<V> reload(K k9, V v8) {
        Preconditions.checkNotNull(k9);
        Preconditions.checkNotNull(v8);
        return Futures.immediateFuture(load(k9));
    }

    public static <V> CacheLoader<Object, V> from(Supplier<V> supplier) {
        return new SupplierToCacheLoader(supplier);
    }
}
