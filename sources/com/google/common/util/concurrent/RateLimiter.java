package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.SmoothRateLimiter;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@Beta
@GwtIncompatible
/* loaded from: classes2.dex */
public abstract class RateLimiter {
    private volatile Object mutexDoNotUseDirectly;
    private final SleepingStopwatch stopwatch;

    public static abstract class SleepingStopwatch {
        public static final SleepingStopwatch createFromSystemTimer() {
            return new SleepingStopwatch() { // from class: com.google.common.util.concurrent.RateLimiter.SleepingStopwatch.1
                final Stopwatch stopwatch = Stopwatch.createStarted();

                @Override // com.google.common.util.concurrent.RateLimiter.SleepingStopwatch
                public long readMicros() {
                    return this.stopwatch.elapsed(TimeUnit.MICROSECONDS);
                }

                @Override // com.google.common.util.concurrent.RateLimiter.SleepingStopwatch
                public void sleepMicrosUninterruptibly(long j9) {
                    if (j9 > 0) {
                        Uninterruptibles.sleepUninterruptibly(j9, TimeUnit.MICROSECONDS);
                    }
                }
            };
        }

        public abstract long readMicros();

        public abstract void sleepMicrosUninterruptibly(long j9);
    }

    public RateLimiter(SleepingStopwatch sleepingStopwatch) {
        this.stopwatch = (SleepingStopwatch) Preconditions.checkNotNull(sleepingStopwatch);
    }

    private boolean canAcquire(long j9, long j10) {
        return queryEarliestAvailable(j9) - j10 <= j9;
    }

    private static void checkPermits(int i9) {
        Preconditions.checkArgument(i9 > 0, "Requested permits (%s) must be positive", i9);
    }

    public static RateLimiter create(double d9) {
        return create(d9, SleepingStopwatch.createFromSystemTimer());
    }

    private Object mutex() {
        Object obj = this.mutexDoNotUseDirectly;
        if (obj == null) {
            synchronized (this) {
                obj = this.mutexDoNotUseDirectly;
                if (obj == null) {
                    obj = new Object();
                    this.mutexDoNotUseDirectly = obj;
                }
            }
        }
        return obj;
    }

    @CanIgnoreReturnValue
    public double acquire() {
        return acquire(1);
    }

    public abstract double doGetRate();

    public abstract void doSetRate(double d9, long j9);

    public final double getRate() {
        double dDoGetRate;
        synchronized (mutex()) {
            dDoGetRate = doGetRate();
        }
        return dDoGetRate;
    }

    public abstract long queryEarliestAvailable(long j9);

    public final long reserve(int i9) {
        long jReserveAndGetWaitLength;
        checkPermits(i9);
        synchronized (mutex()) {
            jReserveAndGetWaitLength = reserveAndGetWaitLength(i9, this.stopwatch.readMicros());
        }
        return jReserveAndGetWaitLength;
    }

    public final long reserveAndGetWaitLength(int i9, long j9) {
        return Math.max(reserveEarliestAvailable(i9, j9) - j9, 0L);
    }

    public abstract long reserveEarliestAvailable(int i9, long j9);

    public final void setRate(double d9) {
        Preconditions.checkArgument(d9 > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE && !Double.isNaN(d9), "rate must be positive");
        synchronized (mutex()) {
            doSetRate(d9, this.stopwatch.readMicros());
        }
    }

    public String toString() {
        return String.format(Locale.ROOT, "RateLimiter[stableRate=%3.1fqps]", Double.valueOf(getRate()));
    }

    public boolean tryAcquire(long j9, TimeUnit timeUnit) {
        return tryAcquire(1, j9, timeUnit);
    }

    @VisibleForTesting
    public static RateLimiter create(double d9, SleepingStopwatch sleepingStopwatch) {
        SmoothRateLimiter.SmoothBursty smoothBursty = new SmoothRateLimiter.SmoothBursty(sleepingStopwatch, 1.0d);
        smoothBursty.setRate(d9);
        return smoothBursty;
    }

    @CanIgnoreReturnValue
    public double acquire(int i9) {
        long jReserve = reserve(i9);
        this.stopwatch.sleepMicrosUninterruptibly(jReserve);
        return (jReserve * 1.0d) / TimeUnit.SECONDS.toMicros(1L);
    }

    public boolean tryAcquire(int i9) {
        return tryAcquire(i9, 0L, TimeUnit.MICROSECONDS);
    }

    public boolean tryAcquire() {
        return tryAcquire(1, 0L, TimeUnit.MICROSECONDS);
    }

    public static RateLimiter create(double d9, long j9, TimeUnit timeUnit) {
        Preconditions.checkArgument(j9 >= 0, "warmupPeriod must not be negative: %s", j9);
        return create(d9, j9, timeUnit, 3.0d, SleepingStopwatch.createFromSystemTimer());
    }

    public boolean tryAcquire(int i9, long j9, TimeUnit timeUnit) {
        long jMax = Math.max(timeUnit.toMicros(j9), 0L);
        checkPermits(i9);
        synchronized (mutex()) {
            long micros = this.stopwatch.readMicros();
            if (!canAcquire(micros, jMax)) {
                return false;
            }
            this.stopwatch.sleepMicrosUninterruptibly(reserveAndGetWaitLength(i9, micros));
            return true;
        }
    }

    @VisibleForTesting
    public static RateLimiter create(double d9, long j9, TimeUnit timeUnit, double d10, SleepingStopwatch sleepingStopwatch) {
        SmoothRateLimiter.SmoothWarmingUp smoothWarmingUp = new SmoothRateLimiter.SmoothWarmingUp(sleepingStopwatch, j9, timeUnit, d10);
        smoothWarmingUp.setRate(d9);
        return smoothWarmingUp;
    }
}
