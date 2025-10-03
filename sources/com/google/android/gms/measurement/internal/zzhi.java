package com.google.android.gms.measurement.internal;

/* loaded from: classes2.dex */
final class zzhi implements Runnable {
    private final /* synthetic */ zzhe zza;

    public zzhi(zzhe zzheVar) {
        this.zza = zzheVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zzb.zza();
    }
}
