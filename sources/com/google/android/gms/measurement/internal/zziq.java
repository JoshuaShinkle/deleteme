package com.google.android.gms.measurement.internal;

/* loaded from: classes2.dex */
final class zziq implements Runnable {
    private final /* synthetic */ long zza;
    private final /* synthetic */ zzim zzb;

    public zziq(zzim zzimVar, long j9) {
        this.zzb = zzimVar;
        this.zza = j9;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zzd().zza(this.zza);
        this.zzb.zza = null;
    }
}
