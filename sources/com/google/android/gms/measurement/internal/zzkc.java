package com.google.android.gms.measurement.internal;

import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;

/* loaded from: classes2.dex */
final class zzkc {
    final /* synthetic */ zzkb zza;
    private zzkf zzb;

    public zzkc(zzkb zzkbVar) {
        this.zza = zzkbVar;
    }

    public final void zza() {
        this.zza.zzc();
        if (this.zza.zzs().zza(zzat.zzbk) && this.zzb != null) {
            this.zza.zzc.removeCallbacks(this.zzb);
        }
        if (this.zza.zzs().zza(zzat.zzbw)) {
            this.zza.zzr().zzr.zza(false);
        }
    }

    public final void zza(long j9) {
        if (this.zza.zzs().zza(zzat.zzbk)) {
            this.zzb = new zzkf(this, this.zza.zzl().currentTimeMillis(), j9);
            this.zza.zzc.postDelayed(this.zzb, AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS);
        }
    }
}
