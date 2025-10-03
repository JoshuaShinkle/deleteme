package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;

@GwtCompatible
/* loaded from: classes2.dex */
public interface Cache<K, V> {
    ConcurrentMap<K, V> asMap();

    void cleanUp();

    V get(K k9, Callable<? extends V> callable);

    ImmutableMap<K, V> getAllPresent(Iterable<?> iterable);

    V getIfPresent(Object obj);

    void invalidate(Object obj);

    void invalidateAll();

    void invalidateAll(Iterable<?> iterable);

    void put(K k9, V v8);

    void putAll(Map<? extends K, ? extends V> map);

    long size();

    CacheStats stats();
}
