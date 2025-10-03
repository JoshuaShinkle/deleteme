package com.google.android.gms.internal.measurement;

import java.util.Map;

/* loaded from: classes2.dex */
final class zzij<K> implements Map.Entry<K, Object> {
    private Map.Entry<K, zzih> zza;

    private zzij(Map.Entry<K, zzih> entry) {
        this.zza = entry;
    }

    @Override // java.util.Map.Entry
    public final K getKey() {
        return this.zza.getKey();
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        if (this.zza.getValue() == null) {
            return null;
        }
        return zzih.zza();
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        if (obj instanceof zzjg) {
            return this.zza.getValue().zza((zzjg) obj);
        }
        throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
    }

    public final zzih zza() {
        return this.zza.getValue();
    }
}
