package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes2.dex */
final class zzhv implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ zzhe zze;

    public zzhv(zzhe zzheVar, AtomicReference atomicReference, String str, String str2, String str3) {
        this.zze = zzheVar;
        this.zza = atomicReference;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = str3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zze.zzy.zzv().zza(this.zza, this.zzb, this.zzc, this.zzd);
    }
}
