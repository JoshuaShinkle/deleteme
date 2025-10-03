package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes2.dex */
final class zzht implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ zzhe zzb;

    public zzht(zzhe zzheVar, AtomicReference atomicReference) {
        this.zzb = zzheVar;
        this.zza = atomicReference;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zza) {
            try {
                this.zza.set(this.zzb.zzs().zzk(this.zzb.zzf().zzaa()));
            } finally {
                this.zza.notify();
            }
        }
    }
}
