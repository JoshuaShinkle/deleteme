package com.google.android.gms.measurement.internal;

/* loaded from: classes2.dex */
final class zzai implements Runnable {
    private final /* synthetic */ zzgw zza;
    private final /* synthetic */ zzaj zzb;

    public zzai(zzaj zzajVar, zzgw zzgwVar) {
        this.zzb = zzajVar;
        this.zza = zzgwVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zzt();
        if (zzx.zza()) {
            this.zza.zzp().zza(this);
            return;
        }
        boolean zZzb = this.zzb.zzb();
        zzaj.zza(this.zzb, 0L);
        if (zZzb) {
            this.zzb.zza();
        }
    }
}
