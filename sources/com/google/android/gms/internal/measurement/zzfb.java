package com.google.android.gms.internal.measurement;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.apache.commons.p159io.FileUtils;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* loaded from: classes2.dex */
public abstract class zzfb<K, V> implements Serializable, Map<K, V> {
    private static final Map.Entry<?, ?>[] zza = new Map.Entry[0];
    private transient zzff<Map.Entry<K, V>> zzb;
    private transient zzff<K> zzc;
    private transient zzex<V> zzd;

    @Override // java.util.Map
    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public boolean containsKey(@NullableDecl Object obj) {
        return get(obj) != null;
    }

    @Override // java.util.Map
    public boolean containsValue(@NullableDecl Object obj) {
        return ((zzex) values()).contains(obj);
    }

    @Override // java.util.Map
    public /* synthetic */ Set entrySet() {
        zzff<Map.Entry<K, V>> zzffVar = this.zzb;
        if (zzffVar != null) {
            return zzffVar;
        }
        zzff<Map.Entry<K, V>> zzffVarZza = zza();
        this.zzb = zzffVarZza;
        return zzffVarZza;
    }

    @Override // java.util.Map
    public boolean equals(@NullableDecl Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Map) {
            return entrySet().equals(((Map) obj).entrySet());
        }
        return false;
    }

    @Override // java.util.Map
    public abstract V get(@NullableDecl Object obj);

    @Override // java.util.Map
    public final V getOrDefault(@NullableDecl Object obj, @NullableDecl V v8) {
        V v9 = get(obj);
        return v9 != null ? v9 : v8;
    }

    @Override // java.util.Map
    public int hashCode() {
        return zzfq.zza((zzff) entrySet());
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.Map
    public /* synthetic */ Set keySet() {
        zzff<K> zzffVar = this.zzc;
        if (zzffVar != null) {
            return zzffVar;
        }
        zzff<K> zzffVarZzb = zzb();
        this.zzc = zzffVarZzb;
        return zzffVarZzb;
    }

    @Override // java.util.Map
    @Deprecated
    public final V put(K k9, V v8) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @Deprecated
    public final void putAll(Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @Deprecated
    public final V remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        int size = size();
        if (size < 0) {
            StringBuilder sb = new StringBuilder("size".length() + 40);
            sb.append("size");
            sb.append(" cannot be negative but was: ");
            sb.append(size);
            throw new IllegalArgumentException(sb.toString());
        }
        StringBuilder sb2 = new StringBuilder((int) Math.min(size << 3, FileUtils.ONE_GB));
        sb2.append('{');
        boolean z8 = true;
        for (Map.Entry<K, V> entry : entrySet()) {
            if (!z8) {
                sb2.append(", ");
            }
            sb2.append(entry.getKey());
            sb2.append('=');
            sb2.append(entry.getValue());
            z8 = false;
        }
        sb2.append('}');
        return sb2.toString();
    }

    @Override // java.util.Map
    public /* synthetic */ Collection values() {
        zzex<V> zzexVar = this.zzd;
        if (zzexVar != null) {
            return zzexVar;
        }
        zzex<V> zzexVarZzc = zzc();
        this.zzd = zzexVarZzc;
        return zzexVarZzc;
    }

    public abstract zzff<Map.Entry<K, V>> zza();

    public abstract zzff<K> zzb();

    public abstract zzex<V> zzc();
}
