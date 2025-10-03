package com.google.common.math;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.math.BigInteger;

@GwtIncompatible
/* loaded from: classes2.dex */
final class DoubleUtils {
    static final int EXPONENT_BIAS = 1023;
    static final long EXPONENT_MASK = 9218868437227405312L;
    static final long IMPLICIT_BIT = 4503599627370496L;

    @VisibleForTesting
    static final long ONE_BITS = 4607182418800017408L;
    static final int SIGNIFICAND_BITS = 52;
    static final long SIGNIFICAND_MASK = 4503599627370495L;
    static final long SIGN_MASK = Long.MIN_VALUE;

    private DoubleUtils() {
    }

    public static double bigToDouble(BigInteger bigInteger) {
        BigInteger bigIntegerAbs = bigInteger.abs();
        boolean z8 = true;
        int iBitLength = bigIntegerAbs.bitLength() - 1;
        if (iBitLength < 63) {
            return bigInteger.longValue();
        }
        if (iBitLength > EXPONENT_BIAS) {
            return bigInteger.signum() * Double.POSITIVE_INFINITY;
        }
        int i9 = (iBitLength - 52) - 1;
        long jLongValue = bigIntegerAbs.shiftRight(i9).longValue();
        long j9 = (jLongValue >> 1) & SIGNIFICAND_MASK;
        if ((jLongValue & 1) == 0 || ((j9 & 1) == 0 && bigIntegerAbs.getLowestSetBit() >= i9)) {
            z8 = false;
        }
        if (z8) {
            j9++;
        }
        return Double.longBitsToDouble((((iBitLength + EXPONENT_BIAS) << 52) + j9) | (bigInteger.signum() & Long.MIN_VALUE));
    }

    public static double ensureNonNegative(double d9) {
        Preconditions.checkArgument(!Double.isNaN(d9));
        return d9 > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? d9 : FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    public static long getSignificand(double d9) {
        Preconditions.checkArgument(isFinite(d9), "not a normal value");
        int exponent = Math.getExponent(d9);
        long jDoubleToRawLongBits = Double.doubleToRawLongBits(d9) & SIGNIFICAND_MASK;
        return exponent == -1023 ? jDoubleToRawLongBits << 1 : jDoubleToRawLongBits | IMPLICIT_BIT;
    }

    public static boolean isFinite(double d9) {
        return Math.getExponent(d9) <= EXPONENT_BIAS;
    }

    public static boolean isNormal(double d9) {
        return Math.getExponent(d9) >= -1022;
    }

    public static double nextDown(double d9) {
        return -Math.nextUp(-d9);
    }

    public static double scaleNormalize(double d9) {
        return Double.longBitsToDouble((Double.doubleToRawLongBits(d9) & SIGNIFICAND_MASK) | ONE_BITS);
    }
}
