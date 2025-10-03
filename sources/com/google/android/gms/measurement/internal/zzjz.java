package com.google.android.gms.measurement.internal;

/* loaded from: classes2.dex */
final class zzjz implements Runnable {
    private final /* synthetic */ zzkp zza;
    private final /* synthetic */ Runnable zzb;

    public zzjz(zzju zzjuVar, zzkp zzkpVar, Runnable runnable) {
        this.zza = zzkpVar;
        this.zzb = runnable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zzr();
        this.zza.zza(this.zzb);
        this.zza.zzo();
    }
}
