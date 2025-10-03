package com.google.android.gms.tasks;

/* loaded from: classes2.dex */
final class zzc implements Runnable {
    final /* synthetic */ Task zza;
    final /* synthetic */ zzd zzb;

    public zzc(zzd zzdVar, Task task) {
        this.zzb = zzdVar;
        this.zza = task;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zza.isCanceled()) {
            this.zzb.zzc.zzc();
            return;
        }
        try {
            this.zzb.zzc.zzb(this.zzb.zzb.then(this.zza));
        } catch (RuntimeExecutionException e9) {
            if (e9.getCause() instanceof Exception) {
                this.zzb.zzc.zza((Exception) e9.getCause());
            } else {
                this.zzb.zzc.zza(e9);
            }
        } catch (Exception e10) {
            this.zzb.zzc.zza(e10);
        }
    }
}
