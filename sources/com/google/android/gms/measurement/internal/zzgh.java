package com.google.android.gms.measurement.internal;

/* loaded from: classes2.dex */
final class zzgh implements Runnable {
    private final /* synthetic */ zzw zza;
    private final /* synthetic */ zzn zzb;
    private final /* synthetic */ zzgc zzc;

    public zzgh(zzgc zzgcVar, zzw zzwVar, zzn zznVar) {
        this.zzc = zzgcVar;
        this.zza = zzwVar;
        this.zzb = zznVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzc.zza.zzr();
        if (this.zza.zzc.zza() == null) {
            this.zzc.zza.zzb(this.zza, this.zzb);
        } else {
            this.zzc.zza.zza(this.zza, this.zzb);
        }
    }
}
