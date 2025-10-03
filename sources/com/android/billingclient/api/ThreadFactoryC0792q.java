package com.android.billingclient.api;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.android.billingclient.api.q */
/* loaded from: classes.dex */
public final class ThreadFactoryC0792q implements ThreadFactory {

    /* renamed from: a */
    public final ThreadFactory f3633a = Executors.defaultThreadFactory();

    /* renamed from: b */
    public final AtomicInteger f3634b = new AtomicInteger(1);

    public ThreadFactoryC0792q(C0766e c0766e) {
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        Thread threadNewThread = this.f3633a.newThread(runnable);
        threadNewThread.setName("PlayBillingLibrary-" + this.f3634b.getAndIncrement());
        return threadNewThread;
    }
}
