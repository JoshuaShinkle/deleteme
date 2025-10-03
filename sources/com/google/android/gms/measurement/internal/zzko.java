package com.google.android.gms.measurement.internal;

/* loaded from: classes2.dex */
final class zzko implements Runnable {
    private final /* synthetic */ zzku zza;
    private final /* synthetic */ zzkp zzb;

    public zzko(zzkp zzkpVar, zzku zzkuVar) {
        this.zzb = zzkpVar;
        this.zza = zzkuVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zza(this.zza);
        this.zzb.zza();
    }
}
