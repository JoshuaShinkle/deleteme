package com.google.android.gms.measurement.internal;

/* loaded from: classes2.dex */
final class zzgp implements Runnable {
    private final /* synthetic */ zzar zza;
    private final /* synthetic */ zzn zzb;
    private final /* synthetic */ zzgc zzc;

    public zzgp(zzgc zzgcVar, zzar zzarVar, zzn zznVar) {
        this.zzc = zzgcVar;
        this.zza = zzarVar;
        this.zzb = zznVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzar zzarVarZzb = this.zzc.zzb(this.zza, this.zzb);
        this.zzc.zza.zzr();
        this.zzc.zza.zza(zzarVarZzb, this.zzb);
    }
}
