package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import java.util.concurrent.ConcurrentMap;

@GwtCompatible
/* loaded from: classes2.dex */
public interface LoadingCache<K, V> extends Cache<K, V>, Function<K, V> {
    @Override // com.google.common.base.Function
    @Deprecated
    V apply(K k9);

    @Override // com.google.common.cache.Cache
    ConcurrentMap<K, V> asMap();

    V get(K k9);

    ImmutableMap<K, V> getAll(Iterable<? extends K> iterable);

    V getUnchecked(K k9);

    void refresh(K k9);
}
