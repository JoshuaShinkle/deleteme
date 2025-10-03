package com.google.android.gms.measurement.internal;

/* loaded from: classes2.dex */
final class zzgq implements Runnable {
    private final /* synthetic */ zzkw zza;
    private final /* synthetic */ zzn zzb;
    private final /* synthetic */ zzgc zzc;

    public zzgq(zzgc zzgcVar, zzkw zzkwVar, zzn zznVar) {
        this.zzc = zzgcVar;
        this.zza = zzkwVar;
        this.zzb = zznVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzc.zza.zzr();
        if (this.zza.zza() == null) {
            this.zzc.zza.zzb(this.zza, this.zzb);
        } else {
            this.zzc.zza.zza(this.zza, this.zzb);
        }
    }
}
