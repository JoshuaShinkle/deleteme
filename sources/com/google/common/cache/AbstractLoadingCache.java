package com.google.common.cache;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.UncheckedExecutionException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@GwtIncompatible
/* loaded from: classes2.dex */
public abstract class AbstractLoadingCache<K, V> extends AbstractCache<K, V> implements LoadingCache<K, V> {
    @Override // com.google.common.cache.LoadingCache, com.google.common.base.Function
    public final V apply(K k9) {
        return getUnchecked(k9);
    }

    @Override // com.google.common.cache.LoadingCache
    public ImmutableMap<K, V> getAll(Iterable<? extends K> iterable) {
        LinkedHashMap linkedHashMapNewLinkedHashMap = Maps.newLinkedHashMap();
        for (K k9 : iterable) {
            if (!linkedHashMapNewLinkedHashMap.containsKey(k9)) {
                linkedHashMapNewLinkedHashMap.put(k9, get(k9));
            }
        }
        return ImmutableMap.copyOf((Map) linkedHashMapNewLinkedHashMap);
    }

    @Override // com.google.common.cache.LoadingCache
    public V getUnchecked(K k9) {
        try {
            return get(k9);
        } catch (ExecutionException e9) {
            throw new UncheckedExecutionException(e9.getCause());
        }
    }

    @Override // com.google.common.cache.LoadingCache
    public void refresh(K k9) {
        throw new UnsupportedOperationException();
    }
}
