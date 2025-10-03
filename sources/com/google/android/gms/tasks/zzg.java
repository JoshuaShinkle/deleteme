package com.google.android.gms.tasks;

/* loaded from: classes2.dex */
final class zzg implements Runnable {
    final /* synthetic */ zzh zza;

    public zzg(zzh zzhVar) {
        this.zza = zzhVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zza.zzb) {
            zzh zzhVar = this.zza;
            if (zzhVar.zzc != null) {
                zzhVar.zzc.onCanceled();
            }
        }
    }
}
