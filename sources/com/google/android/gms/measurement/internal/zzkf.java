package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes2.dex */
final class zzkf implements Runnable {
    long zza;
    long zzb;
    final /* synthetic */ zzkc zzc;

    public zzkf(zzkc zzkcVar, long j9, long j10) {
        this.zzc = zzkcVar;
        this.zza = j9;
        this.zzb = j10;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzc.zza.zzp().zza(new Runnable(this) { // from class: com.google.android.gms.measurement.internal.zzke
            private final zzkf zza;

            {
                this.zza = this;
            }

            @Override // java.lang.Runnable
            public final void run() throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
                zzkf zzkfVar = this.zza;
                zzkc zzkcVar = zzkfVar.zzc;
                long j9 = zzkfVar.zza;
                long j10 = zzkfVar.zzb;
                zzkcVar.zza.zzc();
                zzkcVar.zza.zzq().zzv().zza("Application going to the background");
                boolean z8 = true;
                if (zzkcVar.zza.zzs().zza(zzat.zzbw)) {
                    zzkcVar.zza.zzr().zzr.zza(true);
                }
                Bundle bundle = new Bundle();
                if (!zzkcVar.zza.zzs().zzh().booleanValue()) {
                    zzkcVar.zza.zzb.zzb(j10);
                    if (zzkcVar.zza.zzs().zza(zzat.zzbn)) {
                        bundle.putLong("_et", zzkcVar.zza.zza(j10));
                        zzim.zza(zzkcVar.zza.zzh().zza(true), bundle, true);
                    } else {
                        z8 = false;
                    }
                    zzkcVar.zza.zza(false, z8, j10);
                }
                zzkcVar.zza.zze().zza("auto", "_ab", j9, bundle);
            }
        });
    }
}
