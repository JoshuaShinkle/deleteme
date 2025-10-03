package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* loaded from: classes2.dex */
public final class zzja<K, V> extends LinkedHashMap<K, V> {
    private static final zzja zzb;
    private boolean zza;

    static {
        zzja zzjaVar = new zzja();
        zzb = zzjaVar;
        zzjaVar.zza = false;
    }

    private zzja() {
        this.zza = true;
    }

    public static <K, V> zzja<K, V> zza() {
        return zzb;
    }

    private final void zze() {
        if (!this.zza) {
            throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final void clear() {
        zze();
        super.clear();
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Set<Map.Entry<K, V>> entrySet() {
        return isEmpty() ? Collections.emptySet() : super.entrySet();
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x005c A[RETURN] */
    @Override // java.util.AbstractMap, java.util.Map
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean equals(Object obj) {
        boolean z8;
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (this == map) {
                z8 = true;
                if (z8) {
                    return true;
                }
            } else {
                if (size() == map.size()) {
                    for (Map.Entry<K, V> entry : entrySet()) {
                        if (map.containsKey(entry.getKey())) {
                            V value = entry.getValue();
                            Object obj2 = map.get(entry.getKey());
                            if (!(((value instanceof byte[]) && (obj2 instanceof byte[])) ? Arrays.equals((byte[]) value, (byte[]) obj2) : value.equals(obj2))) {
                            }
                        }
                    }
                    z8 = true;
                    if (z8) {
                    }
                }
                z8 = false;
                if (z8) {
                }
            }
        }
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        int iZza = 0;
        for (Map.Entry<K, V> entry : entrySet()) {
            iZza += zza(entry.getValue()) ^ zza(entry.getKey());
        }
        return iZza;
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final V put(K k9, V v8) {
        zze();
        zzhx.zza(k9);
        zzhx.zza(v8);
        return (V) super.put(k9, v8);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final void putAll(Map<? extends K, ? extends V> map) {
        zze();
        for (K k9 : map.keySet()) {
            zzhx.zza(k9);
            zzhx.zza(map.get(k9));
        }
        super.putAll(map);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final V remove(Object obj) {
        zze();
        return (V) super.remove(obj);
    }

    public final zzja<K, V> zzb() {
        return isEmpty() ? new zzja<>() : new zzja<>(this);
    }

    public final void zzc() {
        this.zza = false;
    }

    public final boolean zzd() {
        return this.zza;
    }

    public final void zza(zzja<K, V> zzjaVar) {
        zze();
        if (zzjaVar.isEmpty()) {
            return;
        }
        putAll(zzjaVar);
    }

    private zzja(Map<K, V> map) {
        super(map);
        this.zza = true;
    }

    private static int zza(Object obj) {
        if (obj instanceof byte[]) {
            return zzhx.zzc((byte[]) obj);
        }
        if (!(obj instanceof zzia)) {
            return obj.hashCode();
        }
        throw new UnsupportedOperationException();
    }
}
