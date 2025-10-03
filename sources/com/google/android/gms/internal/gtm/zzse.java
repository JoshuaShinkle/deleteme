package com.google.android.gms.internal.gtm;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* loaded from: classes2.dex */
public final class zzse<K, V> extends LinkedHashMap<K, V> {
    private static final zzse zzbct;
    private boolean zzavs;

    static {
        zzse zzseVar = new zzse();
        zzbct = zzseVar;
        zzseVar.zzavs = false;
    }

    private zzse() {
        this.zzavs = true;
    }

    public static <K, V> zzse<K, V> zzqf() {
        return zzbct;
    }

    private final void zzqh() {
        if (!this.zzavs) {
            throw new UnsupportedOperationException();
        }
    }

    private static int zzw(Object obj) {
        if (obj instanceof byte[]) {
            return zzre.hashCode((byte[]) obj);
        }
        if (obj instanceof zzrf) {
            throw new UnsupportedOperationException();
        }
        return obj.hashCode();
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final void clear() {
        zzqh();
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
        int iZzw = 0;
        for (Map.Entry<K, V> entry : entrySet()) {
            iZzw += zzw(entry.getValue()) ^ zzw(entry.getKey());
        }
        return iZzw;
    }

    public final boolean isMutable() {
        return this.zzavs;
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final V put(K k9, V v8) {
        zzqh();
        zzre.checkNotNull(k9);
        zzre.checkNotNull(v8);
        return (V) super.put(k9, v8);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final void putAll(Map<? extends K, ? extends V> map) {
        zzqh();
        for (K k9 : map.keySet()) {
            zzre.checkNotNull(k9);
            zzre.checkNotNull(map.get(k9));
        }
        super.putAll(map);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final V remove(Object obj) {
        zzqh();
        return (V) super.remove(obj);
    }

    public final void zza(zzse<K, V> zzseVar) {
        zzqh();
        if (zzseVar.isEmpty()) {
            return;
        }
        putAll(zzseVar);
    }

    public final void zzmi() {
        this.zzavs = false;
    }

    public final zzse<K, V> zzqg() {
        return isEmpty() ? new zzse<>() : new zzse<>(this);
    }

    private zzse(Map<K, V> map) {
        super(map);
        this.zzavs = true;
    }
}
