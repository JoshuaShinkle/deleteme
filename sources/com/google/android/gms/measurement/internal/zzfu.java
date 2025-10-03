package com.google.android.gms.measurement.internal;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import java.lang.Thread;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes2.dex */
public final class zzfu extends zzgx {
    private static final AtomicLong zzj = new AtomicLong(Long.MIN_VALUE);
    private zzfy zza;
    private zzfy zzb;
    private final PriorityBlockingQueue<zzfz<?>> zzc;
    private final BlockingQueue<zzfz<?>> zzd;
    private final Thread.UncaughtExceptionHandler zze;
    private final Thread.UncaughtExceptionHandler zzf;
    private final Object zzg;
    private final Semaphore zzh;
    private volatile boolean zzi;

    public zzfu(zzgb zzgbVar) {
        super(zzgbVar);
        this.zzg = new Object();
        this.zzh = new Semaphore(2);
        this.zzc = new PriorityBlockingQueue<>();
        this.zzd = new LinkedBlockingQueue();
        this.zze = new zzfw(this, "Thread death: Uncaught exception on worker thread");
        this.zzf = new zzfw(this, "Thread death: Uncaught exception on network thread");
    }

    public final <V> Future<V> zza(Callable<V> callable) {
        zzaa();
        Preconditions.checkNotNull(callable);
        zzfz<?> zzfzVar = new zzfz<>(this, (Callable<?>) callable, false, "Task exception on worker thread");
        if (Thread.currentThread() == this.zza) {
            if (!this.zzc.isEmpty()) {
                zzq().zzh().zza("Callable skipped the worker queue.");
            }
            zzfzVar.run();
        } else {
            zza(zzfzVar);
        }
        return zzfzVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final void zzb() {
        if (Thread.currentThread() != this.zzb) {
            throw new IllegalStateException("Call expected from network thread");
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final void zzc() {
        if (Thread.currentThread() != this.zza) {
            throw new IllegalStateException("Call expected from worker thread");
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzgx
    public final boolean zzd() {
        return false;
    }

    public final boolean zzf() {
        return Thread.currentThread() == this.zza;
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ zzal zzk() {
        return super.zzk();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu, com.google.android.gms.measurement.internal.zzgw
    public final /* bridge */ /* synthetic */ Clock zzl() {
        return super.zzl();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu, com.google.android.gms.measurement.internal.zzgw
    public final /* bridge */ /* synthetic */ Context zzm() {
        return super.zzm();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ zzev zzn() {
        return super.zzn();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ zzkx zzo() {
        return super.zzo();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu, com.google.android.gms.measurement.internal.zzgw
    public final /* bridge */ /* synthetic */ zzfu zzp() {
        return super.zzp();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu, com.google.android.gms.measurement.internal.zzgw
    public final /* bridge */ /* synthetic */ zzex zzq() {
        return super.zzq();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ zzfj zzr() {
        return super.zzr();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ zzy zzs() {
        return super.zzs();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu, com.google.android.gms.measurement.internal.zzgw
    public final /* bridge */ /* synthetic */ zzx zzt() {
        return super.zzt();
    }

    public final <V> Future<V> zzb(Callable<V> callable) {
        zzaa();
        Preconditions.checkNotNull(callable);
        zzfz<?> zzfzVar = new zzfz<>(this, (Callable<?>) callable, true, "Task exception on worker thread");
        if (Thread.currentThread() == this.zza) {
            zzfzVar.run();
        } else {
            zza(zzfzVar);
        }
        return zzfzVar;
    }

    public final void zzc(Runnable runnable) {
        zzaa();
        Preconditions.checkNotNull(runnable);
        zzfz<?> zzfzVar = new zzfz<>(this, runnable, false, "Task exception on network thread");
        synchronized (this.zzg) {
            this.zzd.add(zzfzVar);
            zzfy zzfyVar = this.zzb;
            if (zzfyVar == null) {
                zzfy zzfyVar2 = new zzfy(this, "Measurement Network", this.zzd);
                this.zzb = zzfyVar2;
                zzfyVar2.setUncaughtExceptionHandler(this.zzf);
                this.zzb.start();
            } else {
                zzfyVar.zza();
            }
        }
    }

    public final void zza(Runnable runnable) {
        zzaa();
        Preconditions.checkNotNull(runnable);
        zza(new zzfz<>(this, runnable, false, "Task exception on worker thread"));
    }

    public final void zzb(Runnable runnable) {
        zzaa();
        Preconditions.checkNotNull(runnable);
        zza(new zzfz<>(this, runnable, true, "Task exception on worker thread"));
    }

    public final <T> T zza(AtomicReference<T> atomicReference, long j9, String str, Runnable runnable) {
        synchronized (atomicReference) {
            zzp().zza(runnable);
            try {
                atomicReference.wait(j9);
            } catch (InterruptedException unused) {
                zzez zzezVarZzh = zzq().zzh();
                String strValueOf = String.valueOf(str);
                zzezVarZzh.zza(strValueOf.length() != 0 ? "Interrupted waiting for ".concat(strValueOf) : new String("Interrupted waiting for "));
                return null;
            }
        }
        T t8 = atomicReference.get();
        if (t8 == null) {
            zzez zzezVarZzh2 = zzq().zzh();
            String strValueOf2 = String.valueOf(str);
            zzezVarZzh2.zza(strValueOf2.length() != 0 ? "Timed out waiting for ".concat(strValueOf2) : new String("Timed out waiting for "));
        }
        return t8;
    }

    public static /* synthetic */ zzfy zzb(zzfu zzfuVar, zzfy zzfyVar) {
        zzfuVar.zzb = null;
        return null;
    }

    private final void zza(zzfz<?> zzfzVar) {
        synchronized (this.zzg) {
            this.zzc.add(zzfzVar);
            zzfy zzfyVar = this.zza;
            if (zzfyVar == null) {
                zzfy zzfyVar2 = new zzfy(this, "Measurement Worker", this.zzc);
                this.zza = zzfyVar2;
                zzfyVar2.setUncaughtExceptionHandler(this.zze);
                this.zza.start();
            } else {
                zzfyVar.zza();
            }
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ void zza() {
        super.zza();
    }

    public static /* synthetic */ zzfy zza(zzfu zzfuVar, zzfy zzfyVar) {
        zzfuVar.zza = null;
        return null;
    }
}
