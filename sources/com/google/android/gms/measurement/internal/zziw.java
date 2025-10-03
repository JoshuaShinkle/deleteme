package com.google.android.gms.measurement.internal;

/* loaded from: classes2.dex */
final class zziw implements Runnable {
    private final /* synthetic */ boolean zza;
    private final /* synthetic */ zzkw zzb;
    private final /* synthetic */ zzn zzc;
    private final /* synthetic */ zziv zzd;

    public zziw(zziv zzivVar, boolean z8, zzkw zzkwVar, zzn zznVar) {
        this.zzd = zzivVar;
        this.zza = z8;
        this.zzb = zzkwVar;
        this.zzc = zznVar;
    }

    @Override // java.lang.Runnable
    public final void run() throws Throwable {
        zzep zzepVar = this.zzd.zzb;
        if (zzepVar == null) {
            this.zzd.zzq().zze().zza("Discarding data. Failed to set user property");
        } else {
            this.zzd.zza(zzepVar, this.zza ? null : this.zzb, this.zzc);
            this.zzd.zzaj();
        }
    }
}
