package com.google.common.math;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.math.BigInteger;

@CanIgnoreReturnValue
@GwtCompatible
/* loaded from: classes2.dex */
final class MathPreconditions {
    private MathPreconditions() {
    }

    public static void checkInRange(boolean z8) {
        if (!z8) {
            throw new ArithmeticException("not in range");
        }
    }

    public static void checkNoOverflow(boolean z8) {
        if (!z8) {
            throw new ArithmeticException("overflow");
        }
    }

    public static int checkNonNegative(String str, int i9) {
        if (i9 >= 0) {
            return i9;
        }
        throw new IllegalArgumentException(str + " (" + i9 + ") must be >= 0");
    }

    public static int checkPositive(String str, int i9) {
        if (i9 > 0) {
            return i9;
        }
        throw new IllegalArgumentException(str + " (" + i9 + ") must be > 0");
    }

    public static void checkRoundingUnnecessary(boolean z8) {
        if (!z8) {
            throw new ArithmeticException("mode was UNNECESSARY, but rounding was necessary");
        }
    }

    public static long checkNonNegative(String str, long j9) {
        if (j9 >= 0) {
            return j9;
        }
        throw new IllegalArgumentException(str + " (" + j9 + ") must be >= 0");
    }

    public static long checkPositive(String str, long j9) {
        if (j9 > 0) {
            return j9;
        }
        throw new IllegalArgumentException(str + " (" + j9 + ") must be > 0");
    }

    public static BigInteger checkNonNegative(String str, BigInteger bigInteger) {
        if (bigInteger.signum() >= 0) {
            return bigInteger;
        }
        throw new IllegalArgumentException(str + " (" + bigInteger + ") must be >= 0");
    }

    public static BigInteger checkPositive(String str, BigInteger bigInteger) {
        if (bigInteger.signum() > 0) {
            return bigInteger;
        }
        throw new IllegalArgumentException(str + " (" + bigInteger + ") must be > 0");
    }

    public static double checkNonNegative(String str, double d9) {
        if (d9 >= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            return d9;
        }
        throw new IllegalArgumentException(str + " (" + d9 + ") must be >= 0");
    }
}
