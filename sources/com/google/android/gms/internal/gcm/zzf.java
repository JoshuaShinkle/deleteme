package com.google.android.gms.internal.gcm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;

/* loaded from: classes2.dex */
public interface zzf {
    ExecutorService zzd(int i9, ThreadFactory threadFactory, int i10);

    ExecutorService zzd(ThreadFactory threadFactory, int i9);

    ScheduledExecutorService zze(int i9, ThreadFactory threadFactory, int i10);
}
