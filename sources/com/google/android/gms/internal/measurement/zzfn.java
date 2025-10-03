package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map;

/* loaded from: classes2.dex */
final class zzfn<K, V> extends zzff<Map.Entry<K, V>> {
    private final transient zzfb<K, V> zza;
    private final transient Object[] zzb;
    private final transient int zzc = 0;
    private final transient int zzd;

    public zzfn(zzfb<K, V> zzfbVar, Object[] objArr, int i9, int i10) {
        this.zza = zzfbVar;
        this.zzb = objArr;
        this.zzd = i10;
    }

    @Override // com.google.android.gms.internal.measurement.zzex, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (value != null && value.equals(this.zza.get(key))) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.measurement.zzff, com.google.android.gms.internal.measurement.zzex, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final /* synthetic */ Iterator iterator() {
        return iterator();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.measurement.zzex
    /* renamed from: zzb */
    public final zzfs<Map.Entry<K, V>> iterator() {
        return (zzfs) zzc().iterator();
    }

    @Override // com.google.android.gms.internal.measurement.zzex
    public final boolean zzg() {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzff
    public final zzew<Map.Entry<K, V>> zzh() {
        return new zzfm(this);
    }

    @Override // com.google.android.gms.internal.measurement.zzex
    public final int zzb(Object[] objArr, int i9) {
        return zzc().zzb(objArr, i9);
    }
}
