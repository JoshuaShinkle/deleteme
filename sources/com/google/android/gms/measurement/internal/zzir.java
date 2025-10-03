package com.google.android.gms.measurement.internal;

/* loaded from: classes2.dex */
final class zzir implements Runnable {
    private final /* synthetic */ zzim zza;

    public zzir(zzim zzimVar) {
        this.zza = zzimVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzim zzimVar = this.zza;
        zzimVar.zza = zzimVar.zzh;
    }
}
