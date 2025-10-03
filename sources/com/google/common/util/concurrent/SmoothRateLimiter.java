package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.math.LongMath;
import com.google.common.util.concurrent.RateLimiter;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.concurrent.TimeUnit;

@GwtIncompatible
/* loaded from: classes2.dex */
abstract class SmoothRateLimiter extends RateLimiter {
    double maxPermits;
    private long nextFreeTicketMicros;
    double stableIntervalMicros;
    double storedPermits;

    public static final class SmoothBursty extends SmoothRateLimiter {
        final double maxBurstSeconds;

        public SmoothBursty(RateLimiter.SleepingStopwatch sleepingStopwatch, double d9) {
            super(sleepingStopwatch);
            this.maxBurstSeconds = d9;
        }

        @Override // com.google.common.util.concurrent.SmoothRateLimiter
        public double coolDownIntervalMicros() {
            return this.stableIntervalMicros;
        }

        @Override // com.google.common.util.concurrent.SmoothRateLimiter
        public void doSetRate(double d9, double d10) {
            double d11 = this.maxPermits;
            double d12 = this.maxBurstSeconds * d9;
            this.maxPermits = d12;
            if (d11 == Double.POSITIVE_INFINITY) {
                this.storedPermits = d12;
                return;
            }
            double d13 = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            if (d11 != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                d13 = (this.storedPermits * d12) / d11;
            }
            this.storedPermits = d13;
        }

        @Override // com.google.common.util.concurrent.SmoothRateLimiter
        public long storedPermitsToWaitTime(double d9, double d10) {
            return 0L;
        }
    }

    public static final class SmoothWarmingUp extends SmoothRateLimiter {
        private double coldFactor;
        private double slope;
        private double thresholdPermits;
        private final long warmupPeriodMicros;

        public SmoothWarmingUp(RateLimiter.SleepingStopwatch sleepingStopwatch, long j9, TimeUnit timeUnit, double d9) {
            super(sleepingStopwatch);
            this.warmupPeriodMicros = timeUnit.toMicros(j9);
            this.coldFactor = d9;
        }

        private double permitsToTime(double d9) {
            return this.stableIntervalMicros + (d9 * this.slope);
        }

        @Override // com.google.common.util.concurrent.SmoothRateLimiter
        public double coolDownIntervalMicros() {
            return this.warmupPeriodMicros / this.maxPermits;
        }

        @Override // com.google.common.util.concurrent.SmoothRateLimiter
        public void doSetRate(double d9, double d10) {
            double d11 = this.maxPermits;
            double d12 = this.coldFactor * d10;
            long j9 = this.warmupPeriodMicros;
            double d13 = (j9 * 0.5d) / d10;
            this.thresholdPermits = d13;
            double d14 = ((j9 * 2.0d) / (d10 + d12)) + d13;
            this.maxPermits = d14;
            this.slope = (d12 - d10) / (d14 - d13);
            if (d11 == Double.POSITIVE_INFINITY) {
                this.storedPermits = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                return;
            }
            if (d11 != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                d14 = (this.storedPermits * d14) / d11;
            }
            this.storedPermits = d14;
        }

        @Override // com.google.common.util.concurrent.SmoothRateLimiter
        public long storedPermitsToWaitTime(double d9, double d10) {
            long jPermitsToTime;
            double d11 = d9 - this.thresholdPermits;
            if (d11 > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                double dMin = Math.min(d11, d10);
                jPermitsToTime = (long) (((permitsToTime(d11) + permitsToTime(d11 - dMin)) * dMin) / 2.0d);
                d10 -= dMin;
            } else {
                jPermitsToTime = 0;
            }
            return (long) (jPermitsToTime + (this.stableIntervalMicros * d10));
        }
    }

    public abstract double coolDownIntervalMicros();

    @Override // com.google.common.util.concurrent.RateLimiter
    public final double doGetRate() {
        return TimeUnit.SECONDS.toMicros(1L) / this.stableIntervalMicros;
    }

    public abstract void doSetRate(double d9, double d10);

    @Override // com.google.common.util.concurrent.RateLimiter
    public final void doSetRate(double d9, long j9) {
        resync(j9);
        double micros = TimeUnit.SECONDS.toMicros(1L) / d9;
        this.stableIntervalMicros = micros;
        doSetRate(d9, micros);
    }

    @Override // com.google.common.util.concurrent.RateLimiter
    public final long queryEarliestAvailable(long j9) {
        return this.nextFreeTicketMicros;
    }

    @Override // com.google.common.util.concurrent.RateLimiter
    public final long reserveEarliestAvailable(int i9, long j9) {
        resync(j9);
        long j10 = this.nextFreeTicketMicros;
        double d9 = i9;
        double dMin = Math.min(d9, this.storedPermits);
        this.nextFreeTicketMicros = LongMath.saturatedAdd(this.nextFreeTicketMicros, storedPermitsToWaitTime(this.storedPermits, dMin) + ((long) ((d9 - dMin) * this.stableIntervalMicros)));
        this.storedPermits -= dMin;
        return j10;
    }

    public void resync(long j9) {
        if (j9 > this.nextFreeTicketMicros) {
            this.storedPermits = Math.min(this.maxPermits, this.storedPermits + ((j9 - r0) / coolDownIntervalMicros()));
            this.nextFreeTicketMicros = j9;
        }
    }

    public abstract long storedPermitsToWaitTime(double d9, double d10);

    private SmoothRateLimiter(RateLimiter.SleepingStopwatch sleepingStopwatch) {
        super(sleepingStopwatch);
        this.nextFreeTicketMicros = 0L;
    }
}
