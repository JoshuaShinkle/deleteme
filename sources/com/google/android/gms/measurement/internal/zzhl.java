package com.google.android.gms.measurement.internal;

/* loaded from: classes2.dex */
final class zzhl implements Runnable {
    private final /* synthetic */ long zza;
    private final /* synthetic */ zzhe zzb;

    public zzhl(zzhe zzheVar, long j9) {
        this.zzb = zzheVar;
        this.zza = j9;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zzr().zzk.zza(this.zza);
        this.zzb.zzq().zzv().zza("Minimum session duration set", Long.valueOf(this.zza));
    }
}
