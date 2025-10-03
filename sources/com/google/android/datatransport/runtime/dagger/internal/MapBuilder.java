package com.google.android.datatransport.runtime.dagger.internal;

import java.util.Collections;
import java.util.Map;

/* loaded from: classes.dex */
public final class MapBuilder<K, V> {
    private final Map<K, V> contributions;

    private MapBuilder(int i9) {
        this.contributions = DaggerCollections.newLinkedHashMapWithExpectedSize(i9);
    }

    public static <K, V> MapBuilder<K, V> newMapBuilder(int i9) {
        return new MapBuilder<>(i9);
    }

    public Map<K, V> build() {
        return this.contributions.size() != 0 ? Collections.unmodifiableMap(this.contributions) : Collections.emptyMap();
    }

    public MapBuilder<K, V> put(K k9, V v8) {
        this.contributions.put(k9, v8);
        return this;
    }

    public MapBuilder<K, V> putAll(Map<K, V> map) {
        this.contributions.putAll(map);
        return this;
    }
}
