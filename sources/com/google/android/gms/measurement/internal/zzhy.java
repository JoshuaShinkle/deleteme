package com.google.android.gms.measurement.internal;

/* loaded from: classes2.dex */
final class zzhy implements Runnable {
    private final /* synthetic */ Boolean zza;
    private final /* synthetic */ zzhe zzb;

    public zzhy(zzhe zzheVar, Boolean bool) {
        this.zzb = zzheVar;
        this.zza = bool;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zza(this.zza, true);
    }
}
