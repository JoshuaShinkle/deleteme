package com.google.common.math;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@GwtCompatible(emulated = true)
/* loaded from: classes2.dex */
public final class BigIntegerMath {

    @VisibleForTesting
    static final int SQRT2_PRECOMPUTE_THRESHOLD = 256;

    @VisibleForTesting
    static final BigInteger SQRT2_PRECOMPUTED_BITS = new BigInteger("16a09e667f3bcc908b2fb1366ea957d3e3adec17512775099da2f590b0667322a", 16);
    private static final double LN_10 = Math.log(10.0d);
    private static final double LN_2 = Math.log(2.0d);

    /* renamed from: com.google.common.math.BigIntegerMath$1 */
    public static /* synthetic */ class C38921 {
        static final /* synthetic */ int[] $SwitchMap$java$math$RoundingMode;

        static {
            int[] iArr = new int[RoundingMode.values().length];
            $SwitchMap$java$math$RoundingMode = iArr;
            try {
                iArr[RoundingMode.UNNECESSARY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.DOWN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.FLOOR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.UP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.CEILING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_DOWN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_UP.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_EVEN.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    private BigIntegerMath() {
    }

    public static BigInteger binomial(int i9, int i10) {
        int i11;
        MathPreconditions.checkNonNegative("n", i9);
        MathPreconditions.checkNonNegative("k", i10);
        int i12 = 1;
        Preconditions.checkArgument(i10 <= i9, "k (%s) > n (%s)", i10, i9);
        if (i10 > (i9 >> 1)) {
            i10 = i9 - i10;
        }
        int[] iArr = LongMath.biggestBinomials;
        if (i10 < iArr.length && i9 <= iArr[i10]) {
            return BigInteger.valueOf(LongMath.binomial(i9, i10));
        }
        BigInteger bigIntegerDivide = BigInteger.ONE;
        long j9 = i9;
        int iLog2 = LongMath.log2(j9, RoundingMode.CEILING);
        long j10 = 1;
        while (true) {
            int i13 = iLog2;
            while (i12 < i10) {
                i11 = i9 - i12;
                i12++;
                i13 += iLog2;
                if (i13 >= 63) {
                    break;
                }
                j9 *= i11;
                j10 *= i12;
            }
            return bigIntegerDivide.multiply(BigInteger.valueOf(j9)).divide(BigInteger.valueOf(j10));
            bigIntegerDivide = bigIntegerDivide.multiply(BigInteger.valueOf(j9)).divide(BigInteger.valueOf(j10));
            j9 = i11;
            j10 = i12;
        }
    }

    @Beta
    public static BigInteger ceilingPowerOfTwo(BigInteger bigInteger) {
        return BigInteger.ZERO.setBit(log2(bigInteger, RoundingMode.CEILING));
    }

    @GwtIncompatible
    public static BigInteger divide(BigInteger bigInteger, BigInteger bigInteger2, RoundingMode roundingMode) {
        return new BigDecimal(bigInteger).divide(new BigDecimal(bigInteger2), 0, roundingMode).toBigIntegerExact();
    }

    public static BigInteger factorial(int i9) {
        MathPreconditions.checkNonNegative("n", i9);
        long[] jArr = LongMath.factorials;
        if (i9 < jArr.length) {
            return BigInteger.valueOf(jArr[i9]);
        }
        ArrayList arrayList = new ArrayList(IntMath.divide(IntMath.log2(i9, RoundingMode.CEILING) * i9, 64, RoundingMode.CEILING));
        int length = jArr.length;
        long j9 = jArr[length - 1];
        int iNumberOfTrailingZeros = Long.numberOfTrailingZeros(j9);
        long j10 = j9 >> iNumberOfTrailingZeros;
        int iLog2 = LongMath.log2(j10, RoundingMode.FLOOR) + 1;
        long j11 = length;
        int iLog22 = LongMath.log2(j11, RoundingMode.FLOOR) + 1;
        int i10 = 1 << (iLog22 - 1);
        while (j11 <= i9) {
            if ((j11 & i10) != 0) {
                i10 <<= 1;
                iLog22++;
            }
            int iNumberOfTrailingZeros2 = Long.numberOfTrailingZeros(j11);
            long j12 = j11 >> iNumberOfTrailingZeros2;
            iNumberOfTrailingZeros += iNumberOfTrailingZeros2;
            if ((iLog22 - iNumberOfTrailingZeros2) + iLog2 >= 64) {
                arrayList.add(BigInteger.valueOf(j10));
                j10 = 1;
            }
            j10 *= j12;
            iLog2 = LongMath.log2(j10, RoundingMode.FLOOR) + 1;
            j11++;
        }
        if (j10 > 1) {
            arrayList.add(BigInteger.valueOf(j10));
        }
        return listProduct(arrayList).shiftLeft(iNumberOfTrailingZeros);
    }

    @GwtIncompatible
    public static boolean fitsInLong(BigInteger bigInteger) {
        return bigInteger.bitLength() <= 63;
    }

    @Beta
    public static BigInteger floorPowerOfTwo(BigInteger bigInteger) {
        return BigInteger.ZERO.setBit(log2(bigInteger, RoundingMode.FLOOR));
    }

    public static boolean isPowerOfTwo(BigInteger bigInteger) {
        Preconditions.checkNotNull(bigInteger);
        return bigInteger.signum() > 0 && bigInteger.getLowestSetBit() == bigInteger.bitLength() - 1;
    }

    public static BigInteger listProduct(List<BigInteger> list) {
        return listProduct(list, 0, list.size());
    }

    @GwtIncompatible
    public static int log10(BigInteger bigInteger, RoundingMode roundingMode) {
        int iCompareTo;
        MathPreconditions.checkPositive("x", bigInteger);
        if (fitsInLong(bigInteger)) {
            return LongMath.log10(bigInteger.longValue(), roundingMode);
        }
        int iLog2 = (int) ((log2(bigInteger, RoundingMode.FLOOR) * LN_2) / LN_10);
        BigInteger bigIntegerPow = BigInteger.TEN.pow(iLog2);
        int iCompareTo2 = bigIntegerPow.compareTo(bigInteger);
        if (iCompareTo2 > 0) {
            do {
                iLog2--;
                bigIntegerPow = bigIntegerPow.divide(BigInteger.TEN);
                iCompareTo = bigIntegerPow.compareTo(bigInteger);
            } while (iCompareTo > 0);
        } else {
            BigInteger bigIntegerMultiply = BigInteger.TEN.multiply(bigIntegerPow);
            int i9 = iCompareTo2;
            int iCompareTo3 = bigIntegerMultiply.compareTo(bigInteger);
            while (iCompareTo3 <= 0) {
                iLog2++;
                BigInteger bigIntegerMultiply2 = BigInteger.TEN.multiply(bigIntegerMultiply);
                int iCompareTo4 = bigIntegerMultiply2.compareTo(bigInteger);
                BigInteger bigInteger2 = bigIntegerMultiply;
                bigIntegerMultiply = bigIntegerMultiply2;
                bigIntegerPow = bigInteger2;
                i9 = iCompareTo3;
                iCompareTo3 = iCompareTo4;
            }
            iCompareTo = i9;
        }
        switch (C38921.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(iCompareTo == 0);
            case 2:
            case 3:
                return iLog2;
            case 4:
            case 5:
                return bigIntegerPow.equals(bigInteger) ? iLog2 : iLog2 + 1;
            case 6:
            case 7:
            case 8:
                return bigInteger.pow(2).compareTo(bigIntegerPow.pow(2).multiply(BigInteger.TEN)) <= 0 ? iLog2 : iLog2 + 1;
            default:
                throw new AssertionError();
        }
    }

    public static int log2(BigInteger bigInteger, RoundingMode roundingMode) {
        MathPreconditions.checkPositive("x", (BigInteger) Preconditions.checkNotNull(bigInteger));
        int iBitLength = bigInteger.bitLength() - 1;
        switch (C38921.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(isPowerOfTwo(bigInteger));
            case 2:
            case 3:
                return iBitLength;
            case 4:
            case 5:
                return isPowerOfTwo(bigInteger) ? iBitLength : iBitLength + 1;
            case 6:
            case 7:
            case 8:
                return iBitLength < SQRT2_PRECOMPUTE_THRESHOLD ? bigInteger.compareTo(SQRT2_PRECOMPUTED_BITS.shiftRight(256 - iBitLength)) <= 0 ? iBitLength : iBitLength + 1 : bigInteger.pow(2).bitLength() + (-1) < (iBitLength * 2) + 1 ? iBitLength : iBitLength + 1;
            default:
                throw new AssertionError();
        }
    }

    @GwtIncompatible
    public static BigInteger sqrt(BigInteger bigInteger, RoundingMode roundingMode) {
        MathPreconditions.checkNonNegative("x", bigInteger);
        if (fitsInLong(bigInteger)) {
            return BigInteger.valueOf(LongMath.sqrt(bigInteger.longValue(), roundingMode));
        }
        BigInteger bigIntegerSqrtFloor = sqrtFloor(bigInteger);
        switch (C38921.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(bigIntegerSqrtFloor.pow(2).equals(bigInteger));
            case 2:
            case 3:
                return bigIntegerSqrtFloor;
            case 4:
            case 5:
                int iIntValue = bigIntegerSqrtFloor.intValue();
                return iIntValue * iIntValue == bigInteger.intValue() && bigIntegerSqrtFloor.pow(2).equals(bigInteger) ? bigIntegerSqrtFloor : bigIntegerSqrtFloor.add(BigInteger.ONE);
            case 6:
            case 7:
            case 8:
                return bigIntegerSqrtFloor.pow(2).add(bigIntegerSqrtFloor).compareTo(bigInteger) >= 0 ? bigIntegerSqrtFloor : bigIntegerSqrtFloor.add(BigInteger.ONE);
            default:
                throw new AssertionError();
        }
    }

    @GwtIncompatible
    private static BigInteger sqrtApproxWithDoubles(BigInteger bigInteger) {
        return DoubleMath.roundToBigInteger(Math.sqrt(DoubleUtils.bigToDouble(bigInteger)), RoundingMode.HALF_EVEN);
    }

    @GwtIncompatible
    private static BigInteger sqrtFloor(BigInteger bigInteger) {
        BigInteger bigIntegerShiftLeft;
        int iLog2 = log2(bigInteger, RoundingMode.FLOOR);
        if (iLog2 < 1023) {
            bigIntegerShiftLeft = sqrtApproxWithDoubles(bigInteger);
        } else {
            int i9 = (iLog2 - 52) & (-2);
            bigIntegerShiftLeft = sqrtApproxWithDoubles(bigInteger.shiftRight(i9)).shiftLeft(i9 >> 1);
        }
        BigInteger bigIntegerShiftRight = bigIntegerShiftLeft.add(bigInteger.divide(bigIntegerShiftLeft)).shiftRight(1);
        if (bigIntegerShiftLeft.equals(bigIntegerShiftRight)) {
            return bigIntegerShiftLeft;
        }
        while (true) {
            BigInteger bigIntegerShiftRight2 = bigIntegerShiftRight.add(bigInteger.divide(bigIntegerShiftRight)).shiftRight(1);
            if (bigIntegerShiftRight2.compareTo(bigIntegerShiftRight) >= 0) {
                return bigIntegerShiftRight;
            }
            bigIntegerShiftRight = bigIntegerShiftRight2;
        }
    }

    public static BigInteger listProduct(List<BigInteger> list, int i9, int i10) {
        int i11 = i10 - i9;
        if (i11 == 0) {
            return BigInteger.ONE;
        }
        if (i11 == 1) {
            return list.get(i9);
        }
        if (i11 == 2) {
            return list.get(i9).multiply(list.get(i9 + 1));
        }
        if (i11 == 3) {
            return list.get(i9).multiply(list.get(i9 + 1)).multiply(list.get(i9 + 2));
        }
        int i12 = (i10 + i9) >>> 1;
        return listProduct(list, i9, i12).multiply(listProduct(list, i12, i10));
    }
}
