package com.google.common.math;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Doubles;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.Iterator;

@Beta
@GwtIncompatible
/* loaded from: classes2.dex */
public final class StatsAccumulator {
    private long count = 0;
    private double mean = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    private double sumOfSquaresOfDeltas = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    private double min = Double.NaN;
    private double max = Double.NaN;

    public static double calculateNewMeanNonFinite(double d9, double d10) {
        if (Doubles.isFinite(d9)) {
            return d10;
        }
        if (Doubles.isFinite(d10) || d9 == d10) {
            return d9;
        }
        return Double.NaN;
    }

    public void add(double d9) {
        long j9 = this.count;
        if (j9 == 0) {
            this.count = 1L;
            this.mean = d9;
            this.min = d9;
            this.max = d9;
            if (Doubles.isFinite(d9)) {
                return;
            }
            this.sumOfSquaresOfDeltas = Double.NaN;
            return;
        }
        this.count = j9 + 1;
        if (Doubles.isFinite(d9) && Doubles.isFinite(this.mean)) {
            double d10 = this.mean;
            double d11 = d9 - d10;
            double d12 = d10 + (d11 / this.count);
            this.mean = d12;
            this.sumOfSquaresOfDeltas += d11 * (d9 - d12);
        } else {
            this.mean = calculateNewMeanNonFinite(this.mean, d9);
            this.sumOfSquaresOfDeltas = Double.NaN;
        }
        this.min = Math.min(this.min, d9);
        this.max = Math.max(this.max, d9);
    }

    public void addAll(Iterable<? extends Number> iterable) {
        Iterator<? extends Number> it = iterable.iterator();
        while (it.hasNext()) {
            add(it.next().doubleValue());
        }
    }

    public long count() {
        return this.count;
    }

    public double max() {
        Preconditions.checkState(this.count != 0);
        return this.max;
    }

    public double mean() {
        Preconditions.checkState(this.count != 0);
        return this.mean;
    }

    public double min() {
        Preconditions.checkState(this.count != 0);
        return this.min;
    }

    public final double populationStandardDeviation() {
        return Math.sqrt(populationVariance());
    }

    public final double populationVariance() {
        Preconditions.checkState(this.count != 0);
        if (Double.isNaN(this.sumOfSquaresOfDeltas)) {
            return Double.NaN;
        }
        return this.count == 1 ? FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE : DoubleUtils.ensureNonNegative(this.sumOfSquaresOfDeltas) / this.count;
    }

    public final double sampleStandardDeviation() {
        return Math.sqrt(sampleVariance());
    }

    public final double sampleVariance() {
        Preconditions.checkState(this.count > 1);
        if (Double.isNaN(this.sumOfSquaresOfDeltas)) {
            return Double.NaN;
        }
        return DoubleUtils.ensureNonNegative(this.sumOfSquaresOfDeltas) / (this.count - 1);
    }

    public Stats snapshot() {
        return new Stats(this.count, this.mean, this.sumOfSquaresOfDeltas, this.min, this.max);
    }

    public final double sum() {
        return this.mean * this.count;
    }

    public double sumOfSquaresOfDeltas() {
        return this.sumOfSquaresOfDeltas;
    }

    public void addAll(Iterator<? extends Number> it) {
        while (it.hasNext()) {
            add(it.next().doubleValue());
        }
    }

    public void addAll(double... dArr) {
        for (double d9 : dArr) {
            add(d9);
        }
    }

    public void addAll(int... iArr) {
        for (int i9 : iArr) {
            add(i9);
        }
    }

    public void addAll(long... jArr) {
        for (long j9 : jArr) {
            add(j9);
        }
    }

    public void addAll(Stats stats) {
        if (stats.count() == 0) {
            return;
        }
        long j9 = this.count;
        if (j9 == 0) {
            this.count = stats.count();
            this.mean = stats.mean();
            this.sumOfSquaresOfDeltas = stats.sumOfSquaresOfDeltas();
            this.min = stats.min();
            this.max = stats.max();
            return;
        }
        this.count = j9 + stats.count();
        if (Doubles.isFinite(this.mean) && Doubles.isFinite(stats.mean())) {
            double dMean = stats.mean();
            double d9 = this.mean;
            double d10 = dMean - d9;
            this.mean = d9 + ((stats.count() * d10) / this.count);
            this.sumOfSquaresOfDeltas += stats.sumOfSquaresOfDeltas() + (d10 * (stats.mean() - this.mean) * stats.count());
        } else {
            this.mean = calculateNewMeanNonFinite(this.mean, stats.mean());
            this.sumOfSquaresOfDeltas = Double.NaN;
        }
        this.min = Math.min(this.min, stats.min());
        this.max = Math.max(this.max, stats.max());
    }
}
