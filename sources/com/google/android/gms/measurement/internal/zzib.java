package com.google.android.gms.measurement.internal;

/* loaded from: classes2.dex */
final class zzib implements Runnable {
    private final /* synthetic */ zzad zza;
    private final /* synthetic */ long zzb;
    private final /* synthetic */ int zzc;
    private final /* synthetic */ long zzd;
    private final /* synthetic */ boolean zze;
    private final /* synthetic */ zzhe zzf;

    public zzib(zzhe zzheVar, zzad zzadVar, long j9, int i9, long j10, boolean z8) {
        this.zzf = zzheVar;
        this.zza = zzadVar;
        this.zzb = j9;
        this.zzc = i9;
        this.zzd = j10;
        this.zze = z8;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzf.zza(this.zza);
        this.zzf.zza(this.zzb, false);
        this.zzf.zza(this.zza, this.zzc, this.zzd, true, this.zze);
    }
}
