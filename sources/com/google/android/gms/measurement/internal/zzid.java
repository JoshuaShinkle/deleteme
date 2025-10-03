package com.google.android.gms.measurement.internal;

/* loaded from: classes2.dex */
final class zzid implements Runnable {
    private final /* synthetic */ boolean zza;
    private final /* synthetic */ zzhe zzb;

    public zzid(zzhe zzheVar, boolean z8) {
        this.zzb = zzheVar;
        this.zza = z8;
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean zZzaa = this.zzb.zzy.zzaa();
        boolean zZzz = this.zzb.zzy.zzz();
        this.zzb.zzy.zza(this.zza);
        if (zZzz == this.zza) {
            this.zzb.zzy.zzq().zzw().zza("Default data collection state already set to", Boolean.valueOf(this.zza));
        }
        if (this.zzb.zzy.zzaa() == zZzaa || this.zzb.zzy.zzaa() != this.zzb.zzy.zzz()) {
            this.zzb.zzy.zzq().zzj().zza("Default data collection is different than actual status", Boolean.valueOf(this.zza), Boolean.valueOf(zZzaa));
        }
        this.zzb.zzal();
    }
}
