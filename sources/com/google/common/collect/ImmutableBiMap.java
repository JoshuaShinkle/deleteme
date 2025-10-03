package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableMap;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;

@GwtCompatible(emulated = true, serializable = true)
/* loaded from: classes2.dex */
public abstract class ImmutableBiMap<K, V> extends ImmutableMap<K, V> implements BiMap<K, V> {

    public static final class Builder<K, V> extends ImmutableMap.Builder<K, V> {
        public Builder() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.ImmutableMap.Builder
        @CanIgnoreReturnValue
        public /* bridge */ /* synthetic */ ImmutableMap.Builder put(Object obj, Object obj2) {
            return put((Builder<K, V>) obj, obj2);
        }

        public Builder(int i9) {
            super(i9);
        }

        @Override // com.google.common.collect.ImmutableMap.Builder
        public ImmutableBiMap<K, V> build() {
            if (this.size == 0) {
                return ImmutableBiMap.m17565of();
            }
            sortEntries();
            this.entriesUsed = true;
            return new RegularImmutableBiMap(this.alternatingKeysAndValues, this.size);
        }

        @Override // com.google.common.collect.ImmutableMap.Builder
        @CanIgnoreReturnValue
        @Beta
        public Builder<K, V> orderEntriesByValue(Comparator<? super V> comparator) {
            super.orderEntriesByValue((Comparator) comparator);
            return this;
        }

        @Override // com.google.common.collect.ImmutableMap.Builder
        @CanIgnoreReturnValue
        public Builder<K, V> put(K k9, V v8) {
            super.put((Builder<K, V>) k9, (K) v8);
            return this;
        }

        @Override // com.google.common.collect.ImmutableMap.Builder
        @CanIgnoreReturnValue
        public Builder<K, V> putAll(Map<? extends K, ? extends V> map) {
            super.putAll((Map) map);
            return this;
        }

        @Override // com.google.common.collect.ImmutableMap.Builder
        @CanIgnoreReturnValue
        public Builder<K, V> put(Map.Entry<? extends K, ? extends V> entry) {
            super.put((Map.Entry) entry);
            return this;
        }

        @Override // com.google.common.collect.ImmutableMap.Builder
        @CanIgnoreReturnValue
        @Beta
        public Builder<K, V> putAll(Iterable<? extends Map.Entry<? extends K, ? extends V>> iterable) {
            super.putAll((Iterable) iterable);
            return this;
        }
    }

    public static class SerializedForm extends ImmutableMap.SerializedForm {
        private static final long serialVersionUID = 0;

        public SerializedForm(ImmutableBiMap<?, ?> immutableBiMap) {
            super(immutableBiMap);
        }

        @Override // com.google.common.collect.ImmutableMap.SerializedForm
        public Object readResolve() {
            return createMap(new Builder());
        }
    }

    public static <K, V> Builder<K, V> builder() {
        return new Builder<>();
    }

    public static <K, V> ImmutableBiMap<K, V> copyOf(Map<? extends K, ? extends V> map) {
        if (map instanceof ImmutableBiMap) {
            ImmutableBiMap<K, V> immutableBiMap = (ImmutableBiMap) map;
            if (!immutableBiMap.isPartialView()) {
                return immutableBiMap;
            }
        }
        return copyOf((Iterable) map.entrySet());
    }

    /* renamed from: of */
    public static <K, V> ImmutableBiMap<K, V> m17565of() {
        return RegularImmutableBiMap.EMPTY;
    }

    @Override // com.google.common.collect.BiMap
    @CanIgnoreReturnValue
    @Deprecated
    public V forcePut(K k9, V v8) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.BiMap
    public abstract ImmutableBiMap<V, K> inverse();

    @Override // com.google.common.collect.ImmutableMap
    public Object writeReplace() {
        return new SerializedForm(this);
    }

    /* renamed from: of */
    public static <K, V> ImmutableBiMap<K, V> m17566of(K k9, V v8) {
        CollectPreconditions.checkEntryNotNull(k9, v8);
        return new RegularImmutableBiMap(new Object[]{k9, v8}, 1);
    }

    @Override // com.google.common.collect.ImmutableMap
    public final ImmutableSet<V> createValues() {
        throw new AssertionError("should never be called");
    }

    /* renamed from: of */
    public static <K, V> ImmutableBiMap<K, V> m17567of(K k9, V v8, K k10, V v9) {
        CollectPreconditions.checkEntryNotNull(k9, v8);
        CollectPreconditions.checkEntryNotNull(k10, v9);
        return new RegularImmutableBiMap(new Object[]{k9, v8, k10, v9}, 2);
    }

    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    public ImmutableSet<V> values() {
        return inverse().keySet();
    }

    @Beta
    public static <K, V> ImmutableBiMap<K, V> copyOf(Iterable<? extends Map.Entry<? extends K, ? extends V>> iterable) {
        return new Builder(iterable instanceof Collection ? ((Collection) iterable).size() : 4).putAll((Iterable) iterable).build();
    }

    /* renamed from: of */
    public static <K, V> ImmutableBiMap<K, V> m17568of(K k9, V v8, K k10, V v9, K k11, V v10) {
        CollectPreconditions.checkEntryNotNull(k9, v8);
        CollectPreconditions.checkEntryNotNull(k10, v9);
        CollectPreconditions.checkEntryNotNull(k11, v10);
        return new RegularImmutableBiMap(new Object[]{k9, v8, k10, v9, k11, v10}, 3);
    }

    /* renamed from: of */
    public static <K, V> ImmutableBiMap<K, V> m17569of(K k9, V v8, K k10, V v9, K k11, V v10, K k12, V v11) {
        CollectPreconditions.checkEntryNotNull(k9, v8);
        CollectPreconditions.checkEntryNotNull(k10, v9);
        CollectPreconditions.checkEntryNotNull(k11, v10);
        CollectPreconditions.checkEntryNotNull(k12, v11);
        return new RegularImmutableBiMap(new Object[]{k9, v8, k10, v9, k11, v10, k12, v11}, 4);
    }

    /* renamed from: of */
    public static <K, V> ImmutableBiMap<K, V> m17570of(K k9, V v8, K k10, V v9, K k11, V v10, K k12, V v11, K k13, V v12) {
        CollectPreconditions.checkEntryNotNull(k9, v8);
        CollectPreconditions.checkEntryNotNull(k10, v9);
        CollectPreconditions.checkEntryNotNull(k11, v10);
        CollectPreconditions.checkEntryNotNull(k12, v11);
        CollectPreconditions.checkEntryNotNull(k13, v12);
        return new RegularImmutableBiMap(new Object[]{k9, v8, k10, v9, k11, v10, k12, v11, k13, v12}, 5);
    }
}
