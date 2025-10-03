package com.google.android.gms.internal.measurement;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* loaded from: classes2.dex */
abstract class zzeg<K, V> implements zzfj<K, V> {

    @NullableDecl
    private transient Map<K, Collection<V>> zza;

    public boolean equals(@NullableDecl Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzfj) {
            return zza().equals(((zzfj) obj).zza());
        }
        return false;
    }

    public int hashCode() {
        return zza().hashCode();
    }

    public String toString() {
        return zza().toString();
    }

    public boolean zza(@NullableDecl Object obj) {
        Iterator<Collection<V>> it = zza().values().iterator();
        while (it.hasNext()) {
            if (it.next().contains(obj)) {
                return true;
            }
        }
        return false;
    }

    public abstract Map<K, Collection<V>> zzb();

    @Override // com.google.android.gms.internal.measurement.zzfj
    public Map<K, Collection<V>> zza() {
        Map<K, Collection<V>> map = this.zza;
        if (map != null) {
            return map;
        }
        Map<K, Collection<V>> mapZzb = zzb();
        this.zza = mapZzb;
        return mapZzb;
    }
}
