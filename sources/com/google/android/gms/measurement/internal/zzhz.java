package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes2.dex */
final class zzhz implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ zzhe zzb;

    public zzhz(zzhe zzheVar, AtomicReference atomicReference) {
        this.zzb = zzheVar;
        this.zza = atomicReference;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zza) {
            try {
                this.zza.set(Double.valueOf(this.zzb.zzs().zzc(this.zzb.zzf().zzaa(), zzat.zzan)));
            } finally {
                this.zza.notify();
            }
        }
    }
}
