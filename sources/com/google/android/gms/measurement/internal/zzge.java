package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes2.dex */
final class zzge implements Runnable {
    private final /* synthetic */ zzn zza;
    private final /* synthetic */ zzgc zzb;

    public zzge(zzgc zzgcVar, zzn zznVar) {
        this.zzb = zzgcVar;
        this.zza = zznVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zza.zzr();
        zzkp zzkpVar = this.zzb.zza;
        zzn zznVar = this.zza;
        zzkpVar.zzp().zzc();
        zzkpVar.zzn();
        Preconditions.checkNotEmpty(zznVar.zza);
        zzkpVar.zzc(zznVar);
    }
}
