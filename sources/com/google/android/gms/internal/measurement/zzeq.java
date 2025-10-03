package com.google.android.gms.internal.measurement;

import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* loaded from: classes2.dex */
final class zzeq extends zzeh {

    @NullableDecl
    private final Object zza;
    private int zzb;
    private final /* synthetic */ zzel zzc;

    public zzeq(zzel zzelVar, int i9) {
        this.zzc = zzelVar;
        this.zza = zzelVar.zzb[i9];
        this.zzb = i9;
    }

    private final void zza() {
        int i9 = this.zzb;
        if (i9 == -1 || i9 >= this.zzc.size() || !zzdu.zza(this.zza, this.zzc.zzb[this.zzb])) {
            this.zzb = this.zzc.zza(this.zza);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzeh, java.util.Map.Entry
    @NullableDecl
    public final Object getKey() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzeh, java.util.Map.Entry
    @NullableDecl
    public final Object getValue() {
        Map mapZzb = this.zzc.zzb();
        if (mapZzb != null) {
            return mapZzb.get(this.zza);
        }
        zza();
        int i9 = this.zzb;
        if (i9 == -1) {
            return null;
        }
        return this.zzc.zzc[i9];
    }

    @Override // com.google.android.gms.internal.measurement.zzeh, java.util.Map.Entry
    public final Object setValue(Object obj) {
        Map mapZzb = this.zzc.zzb();
        if (mapZzb != null) {
            return mapZzb.put(this.zza, obj);
        }
        zza();
        int i9 = this.zzb;
        if (i9 == -1) {
            this.zzc.put(this.zza, obj);
            return null;
        }
        Object[] objArr = this.zzc.zzc;
        Object obj2 = objArr[i9];
        objArr[i9] = obj;
        return obj2;
    }
}
