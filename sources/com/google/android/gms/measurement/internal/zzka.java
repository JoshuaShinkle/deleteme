package com.google.android.gms.measurement.internal;

/* loaded from: classes2.dex */
final class zzka implements Runnable {
    private final /* synthetic */ long zza;
    private final /* synthetic */ zzkb zzb;

    public zzka(zzkb zzkbVar, long j9) {
        this.zzb = zzkbVar;
        this.zza = j9;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zzb(this.zza);
    }
}
