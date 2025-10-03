package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes2.dex */
final class zzhp implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ boolean zzb;
    private final /* synthetic */ zzhe zzc;

    public zzhp(zzhe zzheVar, AtomicReference atomicReference, boolean z8) {
        this.zzc = zzheVar;
        this.zza = atomicReference;
        this.zzb = z8;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzc.zzg().zza(this.zza, this.zzb);
    }
}
