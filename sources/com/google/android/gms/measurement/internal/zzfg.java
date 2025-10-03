package com.google.android.gms.measurement.internal;

/* loaded from: classes2.dex */
final class zzfg implements Runnable {
    private final /* synthetic */ boolean zza;
    private final /* synthetic */ zzfh zzb;

    public zzfg(zzfh zzfhVar, boolean z8) {
        this.zzb = zzfhVar;
        this.zza = z8;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zzb.zza(this.zza);
    }
}
