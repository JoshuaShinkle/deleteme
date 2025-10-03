package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes2.dex */
final class zzhw implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ zzhe zzb;

    public zzhw(zzhe zzheVar, AtomicReference atomicReference) {
        this.zzb = zzheVar;
        this.zza = atomicReference;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zza) {
            try {
                this.zza.set(Integer.valueOf(this.zzb.zzs().zzb(this.zzb.zzf().zzaa(), zzat.zzam)));
            } finally {
                this.zza.notify();
            }
        }
    }
}
