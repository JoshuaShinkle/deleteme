package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

/* loaded from: classes2.dex */
final class zze implements Runnable {
    final /* synthetic */ Task zza;
    final /* synthetic */ zzf zzb;

    public zze(zzf zzfVar, Task task) {
        this.zzb = zzfVar;
        this.zza = task;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            Task task = (Task) this.zzb.zzb.then(this.zza);
            if (task == null) {
                this.zzb.onFailure(new NullPointerException("Continuation returned null"));
                return;
            }
            Executor executor = TaskExecutors.zza;
            task.addOnSuccessListener(executor, this.zzb);
            task.addOnFailureListener(executor, this.zzb);
            task.addOnCanceledListener(executor, this.zzb);
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
