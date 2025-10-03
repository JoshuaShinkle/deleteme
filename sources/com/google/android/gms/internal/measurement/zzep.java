package com.google.android.gms.internal.measurement;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* loaded from: classes2.dex */
final class zzep extends AbstractSet {
    private final /* synthetic */ zzel zza;

    public zzep(zzel zzelVar) {
        this.zza = zzelVar;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.zza.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(@NullableDecl Object obj) {
        Map mapZzb = this.zza.zzb();
        if (mapZzb != null) {
            return mapZzb.entrySet().contains(obj);
        }
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            int iZza = this.zza.zza(entry.getKey());
            if (iZza != -1 && zzdu.zza(this.zza.zzc[iZza], entry.getValue())) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return this.zza.zzf();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(@NullableDecl Object obj) {
        Map mapZzb = this.zza.zzb();
        if (mapZzb != null) {
            return mapZzb.entrySet().remove(obj);
        }
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        if (this.zza.zza()) {
            return false;
        }
        int iZzi = this.zza.zzi();
        Object key = entry.getKey();
        Object value = entry.getValue();
        Object obj2 = this.zza.zze;
        zzel zzelVar = this.zza;
        int iZza = zzes.zza(key, value, iZzi, obj2, zzelVar.zza, zzelVar.zzb, zzelVar.zzc);
        if (iZza == -1) {
            return false;
        }
        this.zza.zza(iZza, iZzi);
        zzel.zzd(this.zza);
        this.zza.zzc();
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zza.size();
    }
}
