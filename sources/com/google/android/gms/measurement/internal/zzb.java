package com.google.android.gms.measurement.internal;

/* loaded from: classes2.dex */
final class zzb implements Runnable {
    private final /* synthetic */ String zza;
    private final /* synthetic */ long zzb;
    private final /* synthetic */ zza zzc;

    public zzb(zza zzaVar, String str, long j9) {
        this.zzc = zzaVar;
        this.zza = str;
        this.zzb = j9;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzc.zzd(this.zza, this.zzb);
    }
}
