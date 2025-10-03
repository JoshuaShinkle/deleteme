package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;

@GwtCompatible(serializable = true)
/* loaded from: classes2.dex */
class ImmutableEntry<K, V> extends AbstractMapEntry<K, V> implements Serializable {
    private static final long serialVersionUID = 0;
    final K key;
    final V value;

    public ImmutableEntry(K k9, V v8) {
        this.key = k9;
        this.value = v8;
    }

    @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
    public final K getKey() {
        return this.key;
    }

    @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
    public final V getValue() {
        return this.value;
    }

    @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
    public final V setValue(V v8) {
        throw new UnsupportedOperationException();
    }
}
