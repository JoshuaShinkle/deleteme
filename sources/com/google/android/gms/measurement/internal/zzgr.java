package com.google.android.gms.measurement.internal;

import java.util.concurrent.Callable;

/* loaded from: classes2.dex */
final class zzgr implements Callable<byte[]> {
    private final /* synthetic */ zzar zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ zzgc zzc;

    public zzgr(zzgc zzgcVar, zzar zzarVar, String str) {
        this.zzc = zzgcVar;
        this.zza = zzarVar;
        this.zzb = str;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ byte[] call() {
        this.zzc.zza.zzr();
        return this.zzc.zza.zzg().zza(this.zza, this.zzb);
    }
}
