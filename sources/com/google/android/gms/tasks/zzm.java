package com.google.android.gms.tasks;

/* loaded from: classes2.dex */
final class zzm implements Runnable {
    final /* synthetic */ Task zza;
    final /* synthetic */ zzn zzb;

    public zzm(zzn zznVar, Task task) {
        this.zzb = zznVar;
        this.zza = task;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zzb.zzb) {
            zzn zznVar = this.zzb;
            if (zznVar.zzc != null) {
                zznVar.zzc.onSuccess(this.zza.getResult());
            }
        }
    }
}
