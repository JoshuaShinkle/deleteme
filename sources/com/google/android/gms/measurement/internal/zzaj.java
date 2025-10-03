package com.google.android.gms.measurement.internal;

import android.os.Handler;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes2.dex */
abstract class zzaj {
    private static volatile Handler zzb;
    private final zzgw zza;
    private final Runnable zzc;
    private volatile long zzd;

    public zzaj(zzgw zzgwVar) {
        Preconditions.checkNotNull(zzgwVar);
        this.zza = zzgwVar;
        this.zzc = new zzai(this, zzgwVar);
    }

    private final Handler zzd() {
        Handler handler;
        if (zzb != null) {
            return zzb;
        }
        synchronized (zzaj.class) {
            if (zzb == null) {
                zzb = new com.google.android.gms.internal.measurement.zzq(this.zza.zzm().getMainLooper());
            }
            handler = zzb;
        }
        return handler;
    }

    public abstract void zza();

    public final void zza(long j9) {
        zzc();
        if (j9 >= 0) {
            this.zzd = this.zza.zzl().currentTimeMillis();
            if (zzd().postDelayed(this.zzc, j9)) {
                return;
            }
            this.zza.zzq().zze().zza("Failed to schedule delayed post. time", Long.valueOf(j9));
        }
    }

    public final boolean zzb() {
        return this.zzd != 0;
    }

    public final void zzc() {
        this.zzd = 0L;
        zzd().removeCallbacks(this.zzc);
    }

    public static /* synthetic */ long zza(zzaj zzajVar, long j9) {
        zzajVar.zzd = 0L;
        return 0L;
    }
}
