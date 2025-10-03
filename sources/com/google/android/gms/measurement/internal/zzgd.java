package com.google.android.gms.measurement.internal;

/* loaded from: classes2.dex */
final class zzgd implements Runnable {
    private final /* synthetic */ zzhf zza;
    private final /* synthetic */ zzgb zzb;

    public zzgd(zzgb zzgbVar, zzhf zzhfVar) {
        this.zzb = zzgbVar;
        this.zza = zzhfVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zza(this.zza);
        this.zzb.zza(this.zza.zzg);
    }
}
