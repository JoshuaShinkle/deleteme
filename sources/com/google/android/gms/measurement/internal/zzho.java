package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes2.dex */
final class zzho implements Runnable {
    private final /* synthetic */ long zza;
    private final /* synthetic */ zzhe zzb;

    public zzho(zzhe zzheVar, long j9) {
        this.zzb = zzheVar;
        this.zza = j9;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zza(this.zza, true);
        this.zzb.zzg().zza(new AtomicReference<>());
    }
}
