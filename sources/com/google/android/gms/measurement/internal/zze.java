package com.google.android.gms.measurement.internal;

/* loaded from: classes2.dex */
final class zze implements Runnable {
    private final /* synthetic */ long zza;
    private final /* synthetic */ zza zzb;

    public zze(zza zzaVar, long j9) {
        this.zzb = zzaVar;
        this.zza = j9;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zzb(this.zza);
    }
}
