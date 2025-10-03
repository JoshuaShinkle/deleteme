package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@Beta
@GwtIncompatible
/* loaded from: classes2.dex */
public interface TimeLimiter {
    <T> T callUninterruptiblyWithTimeout(Callable<T> callable, long j9, TimeUnit timeUnit);

    <T> T callWithTimeout(Callable<T> callable, long j9, TimeUnit timeUnit);

    <T> T newProxy(T t8, Class<T> cls, long j9, TimeUnit timeUnit);

    void runUninterruptiblyWithTimeout(Runnable runnable, long j9, TimeUnit timeUnit);

    void runWithTimeout(Runnable runnable, long j9, TimeUnit timeUnit);
}
