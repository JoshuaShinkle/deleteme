package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes2.dex */
final class zzhj implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ zzhe zzb;

    public zzhj(zzhe zzheVar, AtomicReference atomicReference) {
        this.zzb = zzheVar;
        this.zza = atomicReference;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zza) {
            try {
                this.zza.set(Boolean.valueOf(this.zzb.zzs().zzj(this.zzb.zzf().zzaa())));
            } finally {
                this.zza.notify();
            }
        }
    }
}
