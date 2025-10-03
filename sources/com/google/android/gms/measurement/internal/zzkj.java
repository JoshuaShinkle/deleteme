package com.google.android.gms.measurement.internal;

import android.app.ActivityManager;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzmn;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes2.dex */
final class zzkj {
    final /* synthetic */ zzkb zza;

    public zzkj(zzkb zzkbVar) {
        this.zza = zzkbVar;
    }

    @VisibleForTesting
    private final void zzb(long j9, boolean z8) throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        this.zza.zzc();
        if (this.zza.zzy.zzaa()) {
            this.zza.zzr().zzp.zza(j9);
            this.zza.zzq().zzw().zza("Session started, time", Long.valueOf(this.zza.zzl().elapsedRealtime()));
            Long lValueOf = Long.valueOf(j9 / 1000);
            this.zza.zze().zza("auto", "_sid", lValueOf, j9);
            this.zza.zzr().zzm.zza(false);
            Bundle bundle = new Bundle();
            bundle.putLong("_sid", lValueOf.longValue());
            if (this.zza.zzs().zza(zzat.zzbl) && z8) {
                bundle.putLong("_aib", 1L);
            }
            this.zza.zze().zza("auto", "_s", j9, bundle);
            if (zzmn.zzb() && this.zza.zzs().zza(zzat.zzbq)) {
                String strZza = this.zza.zzr().zzu.zza();
                if (TextUtils.isEmpty(strZza)) {
                    return;
                }
                Bundle bundle2 = new Bundle();
                bundle2.putString("_ffr", strZza);
                this.zza.zze().zza("auto", "_ssr", j9, bundle2);
            }
        }
    }

    public final void zza() {
        this.zza.zzc();
        if (this.zza.zzr().zza(this.zza.zzl().currentTimeMillis())) {
            this.zza.zzr().zzm.zza(true);
            ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
            ActivityManager.getMyMemoryState(runningAppProcessInfo);
            if (runningAppProcessInfo.importance == 100) {
                this.zza.zzq().zzw().zza("Detected application was in foreground");
                zzb(this.zza.zzl().currentTimeMillis(), false);
            }
        }
    }

    public final void zza(long j9, boolean z8) {
        this.zza.zzc();
        this.zza.zzaa();
        if (this.zza.zzr().zza(j9)) {
            this.zza.zzr().zzm.zza(true);
        }
        this.zza.zzr().zzp.zza(j9);
        if (this.zza.zzr().zzm.zza()) {
            zzb(j9, z8);
        }
    }
}
