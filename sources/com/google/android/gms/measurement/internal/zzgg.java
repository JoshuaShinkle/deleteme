package com.google.android.gms.measurement.internal;

/* loaded from: classes2.dex */
final class zzgg implements Runnable {
    private final /* synthetic */ zzw zza;
    private final /* synthetic */ zzgc zzb;

    public zzgg(zzgc zzgcVar, zzw zzwVar) {
        this.zzb = zzgcVar;
        this.zza = zzwVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zza.zzr();
        if (this.zza.zzc.zza() == null) {
            this.zzb.zza.zzb(this.zza);
        } else {
            this.zzb.zza.zza(this.zza);
        }
    }
}
