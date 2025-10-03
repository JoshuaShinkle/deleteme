package com.google.android.gms.internal.measurement;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
final class zzk implements zzj {
    private zzk() {
    }

    private static ExecutorService zza(int i9, ThreadFactory threadFactory, int i10) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), threadFactory);
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        return Executors.unconfigurableExecutorService(threadPoolExecutor);
    }

    @Override // com.google.android.gms.internal.measurement.zzj
    public final ExecutorService zza(int i9) {
        return zza(1, Executors.defaultThreadFactory(), i9);
    }

    @Override // com.google.android.gms.internal.measurement.zzj
    public final ExecutorService zza(ThreadFactory threadFactory, int i9) {
        return zza(1, threadFactory, i9);
    }
}
