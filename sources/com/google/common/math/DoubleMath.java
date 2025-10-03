package com.google.common.math;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Booleans;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Iterator;

@GwtCompatible(emulated = true)
/* loaded from: classes2.dex */
public final class DoubleMath {

    @VisibleForTesting
    static final int MAX_FACTORIAL = 170;
    private static final double MAX_INT_AS_DOUBLE = 2.147483647E9d;
    private static final double MAX_LONG_AS_DOUBLE_PLUS_ONE = 9.223372036854776E18d;
    private static final double MIN_INT_AS_DOUBLE = -2.147483648E9d;
    private static final double MIN_LONG_AS_DOUBLE = -9.223372036854776E18d;
    private static final double LN_2 = Math.log(2.0d);

    @VisibleForTesting
    static final double[] everySixteenthFactorial = {1.0d, 2.0922789888E13d, 2.631308369336935E35d, 1.2413915592536073E61d, 1.2688693218588417E89d, 7.156945704626381E118d, 9.916779348709496E149d, 1.974506857221074E182d, 3.856204823625804E215d, 5.5502938327393044E249d, 4.7147236359920616E284d};

    /* renamed from: com.google.common.math.DoubleMath$1 */
    public static /* synthetic */ class C38931 {
        static final /* synthetic */ int[] $SwitchMap$java$math$RoundingMode;

        static {
            int[] iArr = new int[RoundingMode.values().length];
            $SwitchMap$java$math$RoundingMode = iArr;
            try {
                iArr[RoundingMode.UNNECESSARY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.FLOOR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.CEILING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.DOWN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.UP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_EVEN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_UP.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_DOWN.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    private DoubleMath() {
    }

    @CanIgnoreReturnValue
    @GwtIncompatible
    private static double checkFinite(double d9) {
        Preconditions.checkArgument(DoubleUtils.isFinite(d9));
        return d9;
    }

    public static double factorial(int i9) {
        MathPreconditions.checkNonNegative("n", i9);
        if (i9 > MAX_FACTORIAL) {
            return Double.POSITIVE_INFINITY;
        }
        double d9 = 1.0d;
        for (int i10 = (i9 & (-16)) + 1; i10 <= i9; i10++) {
            d9 *= i10;
        }
        return d9 * everySixteenthFactorial[i9 >> 4];
    }

    public static int fuzzyCompare(double d9, double d10, double d11) {
        if (fuzzyEquals(d9, d10, d11)) {
            return 0;
        }
        if (d9 < d10) {
            return -1;
        }
        if (d9 > d10) {
            return 1;
        }
        return Booleans.compare(Double.isNaN(d9), Double.isNaN(d10));
    }

    public static boolean fuzzyEquals(double d9, double d10, double d11) {
        MathPreconditions.checkNonNegative("tolerance", d11);
        return Math.copySign(d9 - d10, 1.0d) <= d11 || d9 == d10 || (Double.isNaN(d9) && Double.isNaN(d10));
    }

    @GwtIncompatible
    public static boolean isMathematicalInteger(double d9) {
        return DoubleUtils.isFinite(d9) && (d9 == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || 52 - Long.numberOfTrailingZeros(DoubleUtils.getSignificand(d9)) <= Math.getExponent(d9));
    }

    @GwtIncompatible
    public static boolean isPowerOfTwo(double d9) {
        if (d9 <= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || !DoubleUtils.isFinite(d9)) {
            return false;
        }
        long significand = DoubleUtils.getSignificand(d9);
        return (significand & (significand - 1)) == 0;
    }

    public static double log2(double d9) {
        return Math.log(d9) / LN_2;
    }

    @GwtIncompatible
    @Deprecated
    public static double mean(double... dArr) {
        Preconditions.checkArgument(dArr.length > 0, "Cannot take mean of 0 values");
        double dCheckFinite = checkFinite(dArr[0]);
        long j9 = 1;
        for (int i9 = 1; i9 < dArr.length; i9++) {
            checkFinite(dArr[i9]);
            j9++;
            dCheckFinite += (dArr[i9] - dCheckFinite) / j9;
        }
        return dCheckFinite;
    }

    @GwtIncompatible
    public static double roundIntermediate(double d9, RoundingMode roundingMode) {
        if (!DoubleUtils.isFinite(d9)) {
            throw new ArithmeticException("input is infinite or NaN");
        }
        switch (C38931.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(isMathematicalInteger(d9));
                return d9;
            case 2:
                return (d9 >= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || isMathematicalInteger(d9)) ? d9 : ((long) d9) - 1;
            case 3:
                return (d9 <= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || isMathematicalInteger(d9)) ? d9 : ((long) d9) + 1;
            case 4:
                return d9;
            case 5:
                if (isMathematicalInteger(d9)) {
                    return d9;
                }
                return ((long) d9) + (d9 > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? 1 : -1);
            case 6:
                return Math.rint(d9);
            case 7:
                double dRint = Math.rint(d9);
                return Math.abs(d9 - dRint) == 0.5d ? d9 + Math.copySign(0.5d, d9) : dRint;
            case 8:
                double dRint2 = Math.rint(d9);
                return Math.abs(d9 - dRint2) == 0.5d ? d9 : dRint2;
            default:
                throw new AssertionError();
        }
    }

    @GwtIncompatible
    public static BigInteger roundToBigInteger(double d9, RoundingMode roundingMode) {
        double dRoundIntermediate = roundIntermediate(d9, roundingMode);
        if ((MIN_LONG_AS_DOUBLE - dRoundIntermediate < 1.0d) && (dRoundIntermediate < MAX_LONG_AS_DOUBLE_PLUS_ONE)) {
            return BigInteger.valueOf((long) dRoundIntermediate);
        }
        BigInteger bigIntegerShiftLeft = BigInteger.valueOf(DoubleUtils.getSignificand(dRoundIntermediate)).shiftLeft(Math.getExponent(dRoundIntermediate) - 52);
        return dRoundIntermediate < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? bigIntegerShiftLeft.negate() : bigIntegerShiftLeft;
    }

    @GwtIncompatible
    public static int roundToInt(double d9, RoundingMode roundingMode) {
        double dRoundIntermediate = roundIntermediate(d9, roundingMode);
        MathPreconditions.checkInRange((dRoundIntermediate > -2.147483649E9d) & (dRoundIntermediate < 2.147483648E9d));
        return (int) dRoundIntermediate;
    }

    @GwtIncompatible
    public static long roundToLong(double d9, RoundingMode roundingMode) {
        double dRoundIntermediate = roundIntermediate(d9, roundingMode);
        MathPreconditions.checkInRange((MIN_LONG_AS_DOUBLE - dRoundIntermediate < 1.0d) & (dRoundIntermediate < MAX_LONG_AS_DOUBLE_PLUS_ONE));
        return (long) dRoundIntermediate;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:29:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
    @GwtIncompatible
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int log2(double d9, RoundingMode roundingMode) {
        boolean zIsPowerOfTwo;
        Preconditions.checkArgument(d9 > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE && DoubleUtils.isFinite(d9), "x must be positive and finite");
        int exponent = Math.getExponent(d9);
        if (!DoubleUtils.isNormal(d9)) {
            return log2(d9 * 4.503599627370496E15d, roundingMode) - 52;
        }
        switch (C38931.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(isPowerOfTwo(d9));
                return !z ? exponent + 1 : exponent;
            case 2:
                if (!z) {
                }
                break;
            case 3:
                z = !isPowerOfTwo(d9);
                if (!z) {
                }
                break;
            case 4:
                z = exponent < 0;
                zIsPowerOfTwo = isPowerOfTwo(d9);
                z &= !zIsPowerOfTwo;
                if (!z) {
                }
                break;
            case 5:
                z = exponent >= 0;
                zIsPowerOfTwo = isPowerOfTwo(d9);
                z &= !zIsPowerOfTwo;
                if (!z) {
                }
                break;
            case 6:
            case 7:
            case 8:
                double dScaleNormalize = DoubleUtils.scaleNormalize(d9);
                if (dScaleNormalize * dScaleNormalize > 2.0d) {
                    z = true;
                }
                if (!z) {
                }
                break;
            default:
                throw new AssertionError();
        }
    }

    @Deprecated
    public static double mean(int... iArr) {
        Preconditions.checkArgument(iArr.length > 0, "Cannot take mean of 0 values");
        long j9 = 0;
        for (int i9 : iArr) {
            j9 += i9;
        }
        return j9 / iArr.length;
    }

    @Deprecated
    public static double mean(long... jArr) {
        Preconditions.checkArgument(jArr.length > 0, "Cannot take mean of 0 values");
        double d9 = jArr[0];
        long j9 = 1;
        for (int i9 = 1; i9 < jArr.length; i9++) {
            j9++;
            d9 += (jArr[i9] - d9) / j9;
        }
        return d9;
    }

    @GwtIncompatible
    @Deprecated
    public static double mean(Iterable<? extends Number> iterable) {
        return mean(iterable.iterator());
    }

    @GwtIncompatible
    @Deprecated
    public static double mean(Iterator<? extends Number> it) {
        Preconditions.checkArgument(it.hasNext(), "Cannot take mean of 0 values");
        double dCheckFinite = checkFinite(it.next().doubleValue());
        long j9 = 1;
        while (it.hasNext()) {
            j9++;
            dCheckFinite += (checkFinite(it.next().doubleValue()) - dCheckFinite) / j9;
        }
        return dCheckFinite;
    }
}
