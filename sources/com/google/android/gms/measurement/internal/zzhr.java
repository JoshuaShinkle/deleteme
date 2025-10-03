package com.google.android.gms.measurement.internal;

/* loaded from: classes2.dex */
final class zzhr implements Runnable {
    private final /* synthetic */ zzhd zza;
    private final /* synthetic */ zzhe zzb;

    public zzhr(zzhe zzheVar, zzhd zzhdVar) {
        this.zzb = zzheVar;
        this.zza = zzhdVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zza(this.zza);
    }
}
