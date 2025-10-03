package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.ImmutableSortedMap;

@GwtIncompatible
/* loaded from: classes2.dex */
abstract class ImmutableSortedMapFauxverideShim<K, V> extends ImmutableMap<K, V> {
    @Deprecated
    public static <K, V> ImmutableSortedMap.Builder<K, V> builder() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    /* renamed from: of */
    public static <K, V> ImmutableSortedMap<K, V> m17635of(K k9, V v8) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    /* renamed from: of */
    public static <K, V> ImmutableSortedMap<K, V> m17636of(K k9, V v8, K k10, V v9) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    /* renamed from: of */
    public static <K, V> ImmutableSortedMap<K, V> m17637of(K k9, V v8, K k10, V v9, K k11, V v10) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    /* renamed from: of */
    public static <K, V> ImmutableSortedMap<K, V> m17638of(K k9, V v8, K k10, V v9, K k11, V v10, K k12, V v11) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    /* renamed from: of */
    public static <K, V> ImmutableSortedMap<K, V> m17639of(K k9, V v8, K k10, V v9, K k11, V v10, K k12, V v11, K k13, V v12) {
        throw new UnsupportedOperationException();
    }
}
