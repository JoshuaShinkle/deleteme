package com.google.firebase.analytics;

import com.google.android.gms.internal.measurement.zzm;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
final class zza extends ThreadPoolExecutor {
    public zza(FirebaseAnalytics firebaseAnalytics, int i9, int i10, long j9, TimeUnit timeUnit, BlockingQueue blockingQueue) {
        super(0, 1, 30L, timeUnit, blockingQueue);
    }

    @Override // java.util.concurrent.ThreadPoolExecutor, java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        super.execute(zzm.zza().zza(runnable));
    }
}
