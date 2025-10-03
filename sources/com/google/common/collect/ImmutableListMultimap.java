package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Serialization;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.j2objc.annotations.RetainedWith;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@GwtCompatible(emulated = true, serializable = true)
/* loaded from: classes2.dex */
public class ImmutableListMultimap<K, V> extends ImmutableMultimap<K, V> implements ListMultimap<K, V> {

    @GwtIncompatible
    private static final long serialVersionUID = 0;

    @RetainedWith
    @LazyInit
    private transient ImmutableListMultimap<V, K> inverse;

    public static final class Builder<K, V> extends ImmutableMultimap.Builder<K, V> {
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        public /* bridge */ /* synthetic */ ImmutableMultimap.Builder put(Object obj, Object obj2) {
            return put((Builder<K, V>) obj, obj2);
        }

        @Override // com.google.common.collect.ImmutableMultimap.Builder
        public ImmutableListMultimap<K, V> build() {
            return (ImmutableListMultimap) super.build();
        }

        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        public Builder<K, V> orderKeysBy(Comparator<? super K> comparator) {
            super.orderKeysBy((Comparator) comparator);
            return this;
        }

        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        public Builder<K, V> orderValuesBy(Comparator<? super V> comparator) {
            super.orderValuesBy((Comparator) comparator);
            return this;
        }

        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        public Builder<K, V> put(K k9, V v8) {
            super.put((Builder<K, V>) k9, (K) v8);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        public /* bridge */ /* synthetic */ ImmutableMultimap.Builder putAll(Object obj, Iterable iterable) {
            return putAll((Builder<K, V>) obj, iterable);
        }

        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        public Builder<K, V> put(Map.Entry<? extends K, ? extends V> entry) {
            super.put((Map.Entry) entry);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        public /* bridge */ /* synthetic */ ImmutableMultimap.Builder putAll(Object obj, Object[] objArr) {
            return putAll((Builder<K, V>) obj, objArr);
        }

        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        @Beta
        public Builder<K, V> putAll(Iterable<? extends Map.Entry<? extends K, ? extends V>> iterable) {
            super.putAll((Iterable) iterable);
            return this;
        }

        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        public Builder<K, V> putAll(K k9, Iterable<? extends V> iterable) {
            super.putAll((Builder<K, V>) k9, (Iterable) iterable);
            return this;
        }

        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        public Builder<K, V> putAll(K k9, V... vArr) {
            super.putAll((Builder<K, V>) k9, (Object[]) vArr);
            return this;
        }

        @Override // com.google.common.collect.ImmutableMultimap.Builder
        @CanIgnoreReturnValue
        public Builder<K, V> putAll(Multimap<? extends K, ? extends V> multimap) {
            super.putAll((Multimap) multimap);
            return this;
        }
    }

    public ImmutableListMultimap(ImmutableMap<K, ImmutableList<V>> immutableMap, int i9) {
        super(immutableMap, i9);
    }

    public static <K, V> Builder<K, V> builder() {
        return new Builder<>();
    }

    public static <K, V> ImmutableListMultimap<K, V> copyOf(Multimap<? extends K, ? extends V> multimap) {
        if (multimap.isEmpty()) {
            return m17586of();
        }
        if (multimap instanceof ImmutableListMultimap) {
            ImmutableListMultimap<K, V> immutableListMultimap = (ImmutableListMultimap) multimap;
            if (!immutableListMultimap.isPartialView()) {
                return immutableListMultimap;
            }
        }
        ImmutableMap.Builder builder = new ImmutableMap.Builder(multimap.asMap().size());
        int size = 0;
        for (Map.Entry<? extends K, Collection<? extends V>> entry : multimap.asMap().entrySet()) {
            ImmutableList immutableListCopyOf = ImmutableList.copyOf((Collection) entry.getValue());
            if (!immutableListCopyOf.isEmpty()) {
                builder.put(entry.getKey(), immutableListCopyOf);
                size += immutableListCopyOf.size();
            }
        }
        return new ImmutableListMultimap<>(builder.build(), size);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ImmutableListMultimap<V, K> invert() {
        Builder builder = builder();
        UnmodifiableIterator it = entries().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            builder.put((Builder) entry.getValue(), entry.getKey());
        }
        ImmutableListMultimap<V, K> immutableListMultimapBuild = builder.build();
        immutableListMultimapBuild.inverse = this;
        return immutableListMultimapBuild;
    }

    /* renamed from: of */
    public static <K, V> ImmutableListMultimap<K, V> m17586of() {
        return EmptyImmutableListMultimap.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @GwtIncompatible
    private void readObject(ObjectInputStream objectInputStream) throws IllegalAccessException, ClassNotFoundException, IOException, IllegalArgumentException {
        objectInputStream.defaultReadObject();
        int i9 = objectInputStream.readInt();
        if (i9 < 0) {
            throw new InvalidObjectException("Invalid key count " + i9);
        }
        ImmutableMap.Builder builder = ImmutableMap.builder();
        int i10 = 0;
        for (int i11 = 0; i11 < i9; i11++) {
            Object object = objectInputStream.readObject();
            int i12 = objectInputStream.readInt();
            if (i12 <= 0) {
                throw new InvalidObjectException("Invalid value count " + i12);
            }
            ImmutableList.Builder builder2 = ImmutableList.builder();
            for (int i13 = 0; i13 < i12; i13++) {
                builder2.add((ImmutableList.Builder) objectInputStream.readObject());
            }
            builder.put(object, builder2.build());
            i10 += i12;
        }
        try {
            ImmutableMultimap.FieldSettersHolder.MAP_FIELD_SETTER.set((Serialization.FieldSetter<ImmutableMultimap>) this, (Object) builder.build());
            ImmutableMultimap.FieldSettersHolder.SIZE_FIELD_SETTER.set((Serialization.FieldSetter<ImmutableMultimap>) this, i10);
        } catch (IllegalArgumentException e9) {
            throw ((InvalidObjectException) new InvalidObjectException(e9.getMessage()).initCause(e9));
        }
    }

    @GwtIncompatible
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        Serialization.writeMultimap(this, objectOutputStream);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.ImmutableMultimap, com.google.common.collect.Multimap
    public /* bridge */ /* synthetic */ ImmutableCollection get(Object obj) {
        return get((ImmutableListMultimap<K, V>) obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.ImmutableMultimap, com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    @CanIgnoreReturnValue
    @Deprecated
    public /* bridge */ /* synthetic */ ImmutableCollection replaceValues(Object obj, Iterable iterable) {
        return replaceValues((ImmutableListMultimap<K, V>) obj, iterable);
    }

    /* renamed from: of */
    public static <K, V> ImmutableListMultimap<K, V> m17587of(K k9, V v8) {
        Builder builder = builder();
        builder.put((Builder) k9, (K) v8);
        return builder.build();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.ImmutableMultimap, com.google.common.collect.Multimap
    public /* bridge */ /* synthetic */ Collection get(Object obj) {
        return get((ImmutableListMultimap<K, V>) obj);
    }

    @Override // com.google.common.collect.ImmutableMultimap
    public ImmutableListMultimap<V, K> inverse() {
        ImmutableListMultimap<V, K> immutableListMultimap = this.inverse;
        if (immutableListMultimap != null) {
            return immutableListMultimap;
        }
        ImmutableListMultimap<V, K> immutableListMultimapInvert = invert();
        this.inverse = immutableListMultimapInvert;
        return immutableListMultimapInvert;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.ImmutableMultimap, com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    @CanIgnoreReturnValue
    @Deprecated
    public /* bridge */ /* synthetic */ Collection replaceValues(Object obj, Iterable iterable) {
        return replaceValues((ImmutableListMultimap<K, V>) obj, iterable);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.ImmutableMultimap, com.google.common.collect.Multimap
    public /* bridge */ /* synthetic */ List get(Object obj) {
        return get((ImmutableListMultimap<K, V>) obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.ImmutableMultimap, com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    @CanIgnoreReturnValue
    @Deprecated
    public /* bridge */ /* synthetic */ List replaceValues(Object obj, Iterable iterable) {
        return replaceValues((ImmutableListMultimap<K, V>) obj, iterable);
    }

    @Override // com.google.common.collect.ImmutableMultimap, com.google.common.collect.Multimap
    public ImmutableList<V> get(K k9) {
        ImmutableList<V> immutableList = (ImmutableList) this.map.get(k9);
        return immutableList == null ? ImmutableList.m17573of() : immutableList;
    }

    @Override // com.google.common.collect.ImmutableMultimap, com.google.common.collect.Multimap
    @CanIgnoreReturnValue
    @Deprecated
    public ImmutableList<V> removeAll(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.ImmutableMultimap, com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    @CanIgnoreReturnValue
    @Deprecated
    public ImmutableList<V> replaceValues(K k9, Iterable<? extends V> iterable) {
        throw new UnsupportedOperationException();
    }

    /* renamed from: of */
    public static <K, V> ImmutableListMultimap<K, V> m17588of(K k9, V v8, K k10, V v9) {
        Builder builder = builder();
        builder.put((Builder) k9, (K) v8);
        builder.put((Builder) k10, (K) v9);
        return builder.build();
    }

    /* renamed from: of */
    public static <K, V> ImmutableListMultimap<K, V> m17589of(K k9, V v8, K k10, V v9, K k11, V v10) {
        Builder builder = builder();
        builder.put((Builder) k9, (K) v8);
        builder.put((Builder) k10, (K) v9);
        builder.put((Builder) k11, (K) v10);
        return builder.build();
    }

    @Beta
    public static <K, V> ImmutableListMultimap<K, V> copyOf(Iterable<? extends Map.Entry<? extends K, ? extends V>> iterable) {
        return new Builder().putAll((Iterable) iterable).build();
    }

    /* renamed from: of */
    public static <K, V> ImmutableListMultimap<K, V> m17590of(K k9, V v8, K k10, V v9, K k11, V v10, K k12, V v11) {
        Builder builder = builder();
        builder.put((Builder) k9, (K) v8);
        builder.put((Builder) k10, (K) v9);
        builder.put((Builder) k11, (K) v10);
        builder.put((Builder) k12, (K) v11);
        return builder.build();
    }

    /* renamed from: of */
    public static <K, V> ImmutableListMultimap<K, V> m17591of(K k9, V v8, K k10, V v9, K k11, V v10, K k12, V v11, K k13, V v12) {
        Builder builder = builder();
        builder.put((Builder) k9, (K) v8);
        builder.put((Builder) k10, (K) v9);
        builder.put((Builder) k11, (K) v10);
        builder.put((Builder) k12, (K) v11);
        builder.put((Builder) k13, (K) v12);
        return builder.build();
    }
}
