package com.google.common.math;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

@Beta
@GwtIncompatible
/* loaded from: classes2.dex */
public abstract class LinearTransformation {

    public static final class LinearTransformationBuilder {

        /* renamed from: x1 */
        private final double f15461x1;

        /* renamed from: y1 */
        private final double f15462y1;

        public LinearTransformation and(double d9, double d10) {
            Preconditions.checkArgument(DoubleUtils.isFinite(d9) && DoubleUtils.isFinite(d10));
            double d11 = this.f15461x1;
            if (d9 != d11) {
                return withSlope((d10 - this.f15462y1) / (d9 - d11));
            }
            Preconditions.checkArgument(d10 != this.f15462y1);
            return new VerticalLinearTransformation(this.f15461x1);
        }

        public LinearTransformation withSlope(double d9) {
            Preconditions.checkArgument(!Double.isNaN(d9));
            return DoubleUtils.isFinite(d9) ? new RegularLinearTransformation(d9, this.f15462y1 - (this.f15461x1 * d9)) : new VerticalLinearTransformation(this.f15461x1);
        }

        private LinearTransformationBuilder(double d9, double d10) {
            this.f15461x1 = d9;
            this.f15462y1 = d10;
        }
    }

    public static final class NaNLinearTransformation extends LinearTransformation {
        static final NaNLinearTransformation INSTANCE = new NaNLinearTransformation();

        private NaNLinearTransformation() {
        }

        @Override // com.google.common.math.LinearTransformation
        public LinearTransformation inverse() {
            return this;
        }

        @Override // com.google.common.math.LinearTransformation
        public boolean isHorizontal() {
            return false;
        }

        @Override // com.google.common.math.LinearTransformation
        public boolean isVertical() {
            return false;
        }

        @Override // com.google.common.math.LinearTransformation
        public double slope() {
            return Double.NaN;
        }

        public String toString() {
            return "NaN";
        }

        @Override // com.google.common.math.LinearTransformation
        public double transform(double d9) {
            return Double.NaN;
        }
    }

    public static LinearTransformation forNaN() {
        return NaNLinearTransformation.INSTANCE;
    }

    public static LinearTransformation horizontal(double d9) {
        Preconditions.checkArgument(DoubleUtils.isFinite(d9));
        return new RegularLinearTransformation(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, d9);
    }

    public static LinearTransformationBuilder mapping(double d9, double d10) {
        Preconditions.checkArgument(DoubleUtils.isFinite(d9) && DoubleUtils.isFinite(d10));
        return new LinearTransformationBuilder(d9, d10);
    }

    public static LinearTransformation vertical(double d9) {
        Preconditions.checkArgument(DoubleUtils.isFinite(d9));
        return new VerticalLinearTransformation(d9);
    }

    public abstract LinearTransformation inverse();

    public abstract boolean isHorizontal();

    public abstract boolean isVertical();

    public abstract double slope();

    public abstract double transform(double d9);

    public static final class VerticalLinearTransformation extends LinearTransformation {

        @LazyInit
        LinearTransformation inverse;

        /* renamed from: x */
        final double f15463x;

        public VerticalLinearTransformation(double d9) {
            this.f15463x = d9;
            this.inverse = null;
        }

        private LinearTransformation createInverse() {
            return new RegularLinearTransformation(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, this.f15463x, this);
        }

        @Override // com.google.common.math.LinearTransformation
        public LinearTransformation inverse() {
            LinearTransformation linearTransformation = this.inverse;
            if (linearTransformation != null) {
                return linearTransformation;
            }
            LinearTransformation linearTransformationCreateInverse = createInverse();
            this.inverse = linearTransformationCreateInverse;
            return linearTransformationCreateInverse;
        }

        @Override // com.google.common.math.LinearTransformation
        public boolean isHorizontal() {
            return false;
        }

        @Override // com.google.common.math.LinearTransformation
        public boolean isVertical() {
            return true;
        }

        @Override // com.google.common.math.LinearTransformation
        public double slope() {
            throw new IllegalStateException();
        }

        public String toString() {
            return String.format("x = %g", Double.valueOf(this.f15463x));
        }

        @Override // com.google.common.math.LinearTransformation
        public double transform(double d9) {
            throw new IllegalStateException();
        }

        public VerticalLinearTransformation(double d9, LinearTransformation linearTransformation) {
            this.f15463x = d9;
            this.inverse = linearTransformation;
        }
    }

    public static final class RegularLinearTransformation extends LinearTransformation {

        @LazyInit
        LinearTransformation inverse;
        final double slope;
        final double yIntercept;

        public RegularLinearTransformation(double d9, double d10) {
            this.slope = d9;
            this.yIntercept = d10;
            this.inverse = null;
        }

        private LinearTransformation createInverse() {
            double d9 = this.slope;
            return d9 != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? new RegularLinearTransformation(1.0d / d9, (this.yIntercept * (-1.0d)) / d9, this) : new VerticalLinearTransformation(this.yIntercept, this);
        }

        @Override // com.google.common.math.LinearTransformation
        public LinearTransformation inverse() {
            LinearTransformation linearTransformation = this.inverse;
            if (linearTransformation != null) {
                return linearTransformation;
            }
            LinearTransformation linearTransformationCreateInverse = createInverse();
            this.inverse = linearTransformationCreateInverse;
            return linearTransformationCreateInverse;
        }

        @Override // com.google.common.math.LinearTransformation
        public boolean isHorizontal() {
            return this.slope == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        }

        @Override // com.google.common.math.LinearTransformation
        public boolean isVertical() {
            return false;
        }

        @Override // com.google.common.math.LinearTransformation
        public double slope() {
            return this.slope;
        }

        public String toString() {
            return String.format("y = %g * x + %g", Double.valueOf(this.slope), Double.valueOf(this.yIntercept));
        }

        @Override // com.google.common.math.LinearTransformation
        public double transform(double d9) {
            return (d9 * this.slope) + this.yIntercept;
        }

        public RegularLinearTransformation(double d9, double d10, LinearTransformation linearTransformation) {
            this.slope = d9;
            this.yIntercept = d10;
            this.inverse = linearTransformation;
        }
    }
}
