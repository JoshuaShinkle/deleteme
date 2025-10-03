package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzmb;

/* loaded from: classes2.dex */
final class zzgm implements Runnable {
    private final /* synthetic */ zzn zza;
    private final /* synthetic */ zzgc zzb;

    public zzgm(zzgc zzgcVar, zzn zznVar) {
        this.zzb = zzgcVar;
        this.zza = zznVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zza.zzr();
        zzkp zzkpVar = this.zzb.zza;
        zzn zznVar = this.zza;
        if (zzmb.zzb() && zzkpVar.zzb().zza(zzat.zzcp)) {
            zzkpVar.zzp().zzc();
            zzkpVar.zzn();
            Preconditions.checkNotEmpty(zznVar.zza);
            zzad zzadVarZza = zzad.zza(zznVar.zzw);
            zzad zzadVarZza2 = zzkpVar.zza(zznVar.zza);
            zzkpVar.zza(zznVar.zza, zzadVarZza);
            if (zzadVarZza.zza(zzadVarZza2)) {
                zzkpVar.zza(zznVar);
            }
        }
    }
}
