package com.google.android.gms.measurement.internal;

/* loaded from: classes2.dex */
final class zzia implements Runnable {
    private final /* synthetic */ zzad zza;
    private final /* synthetic */ int zzb;
    private final /* synthetic */ long zzc;
    private final /* synthetic */ boolean zzd;
    private final /* synthetic */ zzhe zze;

    public zzia(zzhe zzheVar, zzad zzadVar, int i9, long j9, boolean z8) {
        this.zze = zzheVar;
        this.zza = zzadVar;
        this.zzb = i9;
        this.zzc = j9;
        this.zzd = z8;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zze.zza(this.zza);
        this.zze.zza(this.zza, this.zzb, this.zzc, false, this.zzd);
    }
}
