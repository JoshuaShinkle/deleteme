package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.ImmutableSet;
import com.google.j2objc.annotations.Weak;
import java.io.Serializable;

@GwtCompatible(emulated = true)
/* loaded from: classes2.dex */
final class ImmutableMapKeySet<K, V> extends ImmutableSet.Indexed<K> {

    @Weak
    private final ImmutableMap<K, V> map;

    @GwtIncompatible
    public static class KeySetSerializedForm<K> implements Serializable {
        private static final long serialVersionUID = 0;
        final ImmutableMap<K, ?> map;

        public KeySetSerializedForm(ImmutableMap<K, ?> immutableMap) {
            this.map = immutableMap;
        }

        public Object readResolve() {
            return this.map.keySet();
        }
    }

    public ImmutableMapKeySet(ImmutableMap<K, V> immutableMap) {
        this.map = immutableMap;
    }

    @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        return this.map.containsKey(obj);
    }

    @Override // com.google.common.collect.ImmutableSet.Indexed
    public K get(int i9) {
        return this.map.entrySet().asList().get(i9).getKey();
    }

    @Override // com.google.common.collect.ImmutableCollection
    public boolean isPartialView() {
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public int size() {
        return this.map.size();
    }

    @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
    @GwtIncompatible
    public Object writeReplace() {
        return new KeySetSerializedForm(this.map);
    }

    @Override // com.google.common.collect.ImmutableSet.Indexed, com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
    public UnmodifiableIterator<K> iterator() {
        return this.map.keyIterator();
    }
}
