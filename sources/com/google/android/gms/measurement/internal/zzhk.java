package com.google.android.gms.measurement.internal;

/* loaded from: classes2.dex */
final class zzhk implements Runnable {
    private final /* synthetic */ long zza;
    private final /* synthetic */ zzhe zzb;

    public zzhk(zzhe zzheVar, long j9) {
        this.zzb = zzheVar;
        this.zza = j9;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zzr().zzl.zza(this.zza);
        this.zzb.zzq().zzv().zza("Session timeout duration set", Long.valueOf(this.zza));
    }
}
