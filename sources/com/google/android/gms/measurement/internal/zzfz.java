package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/* loaded from: classes2.dex */
final class zzfz<V> extends FutureTask<V> implements Comparable<zzfz<V>> {
    final boolean zza;
    private final long zzb;
    private final String zzc;
    private final /* synthetic */ zzfu zzd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzfz(zzfu zzfuVar, Callable<V> callable, boolean z8, String str) {
        super(com.google.android.gms.internal.measurement.zzm.zza().zza(callable));
        this.zzd = zzfuVar;
        Preconditions.checkNotNull(str);
        long andIncrement = zzfu.zzj.getAndIncrement();
        this.zzb = andIncrement;
        this.zzc = str;
        this.zza = z8;
        if (andIncrement == Long.MAX_VALUE) {
            zzfuVar.zzq().zze().zza("Tasks index overflow");
        }
    }

    @Override // java.lang.Comparable
    public final /* synthetic */ int compareTo(Object obj) {
        zzfz zzfzVar = (zzfz) obj;
        boolean z8 = this.zza;
        if (z8 != zzfzVar.zza) {
            return z8 ? -1 : 1;
        }
        long j9 = this.zzb;
        long j10 = zzfzVar.zzb;
        if (j9 < j10) {
            return -1;
        }
        if (j9 > j10) {
            return 1;
        }
        this.zzd.zzq().zzf().zza("Two tasks share the same index. index", Long.valueOf(this.zzb));
        return 0;
    }

    @Override // java.util.concurrent.FutureTask
    public final void setException(Throwable th) {
        this.zzd.zzq().zze().zza(this.zzc, th);
        if (th instanceof zzfx) {
            Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), th);
        }
        super.setException(th);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzfz(zzfu zzfuVar, Runnable runnable, boolean z8, String str) {
        super(com.google.android.gms.internal.measurement.zzm.zza().zza(runnable), null);
        this.zzd = zzfuVar;
        Preconditions.checkNotNull(str);
        long andIncrement = zzfu.zzj.getAndIncrement();
        this.zzb = andIncrement;
        this.zzc = str;
        this.zza = z8;
        if (andIncrement == Long.MAX_VALUE) {
            zzfuVar.zzq().zze().zza("Tasks index overflow");
        }
    }
}
