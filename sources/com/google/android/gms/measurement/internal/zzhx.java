package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes2.dex */
final class zzhx implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ zzhe zzb;

    public zzhx(zzhe zzheVar, AtomicReference atomicReference) {
        this.zzb = zzheVar;
        this.zza = atomicReference;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zza) {
            try {
                this.zza.set(Long.valueOf(this.zzb.zzs().zza(this.zzb.zzf().zzaa(), zzat.zzal)));
            } finally {
                this.zza.notify();
            }
        }
    }
}
