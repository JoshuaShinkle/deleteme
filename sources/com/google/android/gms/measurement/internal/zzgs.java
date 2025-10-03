package com.google.android.gms.measurement.internal;

/* loaded from: classes2.dex */
final class zzgs implements Runnable {
    private final /* synthetic */ zzn zza;
    private final /* synthetic */ zzgc zzb;

    public zzgs(zzgc zzgcVar, zzn zznVar) {
        this.zzb = zzgcVar;
        this.zza = zznVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zza.zzr();
        this.zzb.zza.zzb(this.zza);
    }
}
