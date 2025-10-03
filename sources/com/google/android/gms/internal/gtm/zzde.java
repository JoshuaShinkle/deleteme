package com.google.android.gms.internal.gtm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;

/* loaded from: classes2.dex */
public interface zzde {
    ScheduledExecutorService zza(int i9, int i10);

    ScheduledExecutorService zza(int i9, ThreadFactory threadFactory, int i10);

    ExecutorService zzr(int i9);
}
