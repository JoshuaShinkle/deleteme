package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Map;
import java.util.Set;

@GwtCompatible
/* loaded from: classes2.dex */
public interface BiMap<K, V> extends Map<K, V> {
    @CanIgnoreReturnValue
    V forcePut(K k9, V v8);

    BiMap<V, K> inverse();

    @CanIgnoreReturnValue
    V put(K k9, V v8);

    void putAll(Map<? extends K, ? extends V> map);

    @Override // java.util.Map
    Set<V> values();
}
