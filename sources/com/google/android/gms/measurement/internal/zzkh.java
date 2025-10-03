package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzne;
import com.google.android.gms.internal.measurement.zznf;
import org.apache.commons.lang3.time.DateUtils;

/* loaded from: classes2.dex */
final class zzkh {

    @VisibleForTesting
    private long zza;

    @VisibleForTesting
    private long zzb;
    private final zzaj zzc;
    private final /* synthetic */ zzkb zzd;

    public zzkh(zzkb zzkbVar) {
        this.zzd = zzkbVar;
        this.zzc = new zzkg(this, zzkbVar.zzy);
        long jElapsedRealtime = zzkbVar.zzl().elapsedRealtime();
        this.zza = jElapsedRealtime;
        this.zzb = jElapsedRealtime;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzc() {
        this.zzd.zzc();
        zza(false, false, this.zzd.zzl().elapsedRealtime());
        this.zzd.zzd().zza(this.zzd.zzl().elapsedRealtime());
    }

    public final void zza(long j9) {
        this.zzd.zzc();
        this.zzc.zzc();
        this.zza = j9;
        this.zzb = j9;
    }

    public final void zzb(long j9) {
        this.zzc.zzc();
    }

    @VisibleForTesting
    public final long zzb() {
        long jElapsedRealtime = this.zzd.zzl().elapsedRealtime();
        long j9 = jElapsedRealtime - this.zzb;
        this.zzb = jElapsedRealtime;
        return j9;
    }

    @VisibleForTesting
    public final long zzc(long j9) {
        long j10 = j9 - this.zzb;
        this.zzb = j9;
        return j10;
    }

    public final void zza() {
        this.zzc.zzc();
        this.zza = 0L;
        this.zzb = 0L;
    }

    public final boolean zza(boolean z8, boolean z9, long j9) {
        this.zzd.zzc();
        this.zzd.zzv();
        if (!zzne.zzb() || !this.zzd.zzs().zza(zzat.zzbr) || this.zzd.zzy.zzaa()) {
            this.zzd.zzr().zzp.zza(this.zzd.zzl().currentTimeMillis());
        }
        long jZzc = j9 - this.zza;
        if (!z8 && jZzc < 1000) {
            this.zzd.zzq().zzw().zza("Screen exposed for less than 1000 ms. Event not sent. time", Long.valueOf(jZzc));
            return false;
        }
        if (this.zzd.zzs().zza(zzat.zzat) && !z9) {
            jZzc = (zznf.zzb() && this.zzd.zzs().zza(zzat.zzav)) ? zzc(j9) : zzb();
        }
        this.zzd.zzq().zzw().zza("Recording user engagement, ms", Long.valueOf(jZzc));
        Bundle bundle = new Bundle();
        bundle.putLong("_et", jZzc);
        zzim.zza(this.zzd.zzh().zza(!this.zzd.zzs().zzh().booleanValue()), bundle, true);
        if (this.zzd.zzs().zza(zzat.zzat) && !this.zzd.zzs().zza(zzat.zzau) && z9) {
            bundle.putLong("_fr", 1L);
        }
        if (!this.zzd.zzs().zza(zzat.zzau) || !z9) {
            this.zzd.zze().zza("auto", "_e", bundle);
        }
        this.zza = j9;
        this.zzc.zzc();
        this.zzc.zza(DateUtils.MILLIS_PER_HOUR);
        return true;
    }
}
