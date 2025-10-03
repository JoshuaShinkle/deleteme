package com.google.android.gms.internal.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
final class zan implements zam {
    private zan() {
    }

    @Override // com.google.android.gms.internal.base.zam
    public final ExecutorService zaa(int i9, int i10) {
        return zaa(4, Executors.defaultThreadFactory(), i10);
    }

    @Override // com.google.android.gms.internal.base.zam
    public final ExecutorService zaa(int i9, ThreadFactory threadFactory, int i10) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(i9, i9, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), threadFactory);
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        return Executors.unconfigurableExecutorService(threadPoolExecutor);
    }

    @Override // com.google.android.gms.internal.base.zam
    public final ExecutorService zaa(ThreadFactory threadFactory, int i9) {
        return zaa(1, threadFactory, i9);
    }
}
