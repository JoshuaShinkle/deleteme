package com.google.common.math;

import com.google.android.exoplayer2.C3322C;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Ascii;
import com.google.common.base.Preconditions;
import com.google.common.primitives.UnsignedLongs;
import java.math.RoundingMode;

@GwtCompatible(emulated = true)
/* loaded from: classes2.dex */
public final class LongMath {

    @VisibleForTesting
    static final long FLOOR_SQRT_MAX_LONG = 3037000499L;

    @VisibleForTesting
    static final long MAX_POWER_OF_SQRT2_UNSIGNED = -5402926248376769404L;

    @VisibleForTesting
    static final long MAX_SIGNED_POWER_OF_TWO = 4611686018427387904L;
    private static final int SIEVE_30 = -545925251;

    @VisibleForTesting
    static final byte[] maxLog10ForLeadingZeros = {19, Ascii.DC2, Ascii.DC2, Ascii.DC2, Ascii.DC2, 17, 17, 17, Ascii.DLE, Ascii.DLE, Ascii.DLE, Ascii.f15389SI, Ascii.f15389SI, Ascii.f15389SI, Ascii.f15389SI, Ascii.f15390SO, Ascii.f15390SO, Ascii.f15390SO, Ascii.f15380CR, Ascii.f15380CR, Ascii.f15380CR, Ascii.f15382FF, Ascii.f15382FF, Ascii.f15382FF, Ascii.f15382FF, Ascii.f15393VT, Ascii.f15393VT, Ascii.f15393VT, 10, 10, 10, 9, 9, 9, 9, 8, 8, 8, 7, 7, 7, 6, 6, 6, 6, 5, 5, 5, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 1, 1, 1, 0, 0, 0};

    @VisibleForTesting
    @GwtIncompatible
    static final long[] powersOf10 = {1, 10, 100, 1000, 10000, 100000, C3322C.MICROS_PER_SECOND, 10000000, 100000000, C3322C.NANOS_PER_SECOND, 10000000000L, 100000000000L, 1000000000000L, 10000000000000L, 100000000000000L, 1000000000000000L, 10000000000000000L, 100000000000000000L, 1000000000000000000L};

    @VisibleForTesting
    @GwtIncompatible
    static final long[] halfPowersOf10 = {3, 31, 316, 3162, 31622, 316227, 3162277, 31622776, 316227766, 3162277660L, 31622776601L, 316227766016L, 3162277660168L, 31622776601683L, 316227766016837L, 3162277660168379L, 31622776601683793L, 316227766016837933L, 3162277660168379331L};
    static final long[] factorials = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800, 39916800, 479001600, 6227020800L, 87178291200L, 1307674368000L, 20922789888000L, 355687428096000L, 6402373705728000L, 121645100408832000L, 2432902008176640000L};
    static final int[] biggestBinomials = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 3810779, 121977, 16175, 4337, 1733, 887, 534, 361, 265, 206, 169, 143, 125, 111, 101, 94, 88, 83, 79, 76, 74, 72, 70, 69, 68, 67, 67, 66, 66, 66, 66};

    @VisibleForTesting
    static final int[] biggestSimpleBinomials = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 2642246, 86251, 11724, 3218, 1313, 684, 419, 287, 214, 169, 139, 119, 105, 95, 87, 81, 76, 73, 70, 68, 66, 64, 63, 62, 62, 61, 61, 61};
    private static final long[][] millerRabinBaseSets = {new long[]{291830, 126401071349994536L}, new long[]{885594168, 725270293939359937L, 3569819667048198375L}, new long[]{273919523040L, 15, 7363882082L, 992620450144556L}, new long[]{47636622961200L, 2, 2570940, 211991001, 3749873356L}, new long[]{7999252175582850L, 2, 4130806001517L, 149795463772692060L, 186635894390467037L, 3967304179347715805L}, new long[]{585226005592931976L, 2, 123635709730000L, 9233062284813009L, 43835965440333360L, 761179012939631437L, 1263739024124850375L}, new long[]{Long.MAX_VALUE, 2, 325, 9375, 28178, 450775, 9780504, 1795265022}};

    /* renamed from: com.google.common.math.LongMath$1 */
    public static /* synthetic */ class C38961 {
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

    public enum MillerRabinTester {
        SMALL { // from class: com.google.common.math.LongMath.MillerRabinTester.1
            @Override // com.google.common.math.LongMath.MillerRabinTester
            public long mulMod(long j9, long j10, long j11) {
                return (j9 * j10) % j11;
            }

            @Override // com.google.common.math.LongMath.MillerRabinTester
            public long squareMod(long j9, long j10) {
                return (j9 * j9) % j10;
            }
        },
        LARGE { // from class: com.google.common.math.LongMath.MillerRabinTester.2
            private long plusMod(long j9, long j10, long j11) {
                long j12 = j9 + j10;
                return j9 >= j11 - j10 ? j12 - j11 : j12;
            }

            private long times2ToThe32Mod(long j9, long j10) {
                int i9 = 32;
                do {
                    int iMin = Math.min(i9, Long.numberOfLeadingZeros(j9));
                    j9 = UnsignedLongs.remainder(j9 << iMin, j10);
                    i9 -= iMin;
                } while (i9 > 0);
                return j9;
            }

            @Override // com.google.common.math.LongMath.MillerRabinTester
            public long mulMod(long j9, long j10, long j11) {
                long j12 = j9 >>> 32;
                long j13 = j10 >>> 32;
                long j14 = j9 & 4294967295L;
                long j15 = j10 & 4294967295L;
                long jTimes2ToThe32Mod = times2ToThe32Mod(j12 * j13, j11) + (j12 * j15);
                if (jTimes2ToThe32Mod < 0) {
                    jTimes2ToThe32Mod = UnsignedLongs.remainder(jTimes2ToThe32Mod, j11);
                }
                return plusMod(times2ToThe32Mod(jTimes2ToThe32Mod + (j13 * j14), j11), UnsignedLongs.remainder(j14 * j15, j11), j11);
            }

            @Override // com.google.common.math.LongMath.MillerRabinTester
            public long squareMod(long j9, long j10) {
                long j11 = j9 >>> 32;
                long j12 = j9 & 4294967295L;
                long jTimes2ToThe32Mod = times2ToThe32Mod(j11 * j11, j10);
                long jRemainder = j11 * j12 * 2;
                if (jRemainder < 0) {
                    jRemainder = UnsignedLongs.remainder(jRemainder, j10);
                }
                return plusMod(times2ToThe32Mod(jTimes2ToThe32Mod + jRemainder, j10), UnsignedLongs.remainder(j12 * j12, j10), j10);
            }
        };

        private long powMod(long j9, long j10, long j11) {
            long jMulMod = 1;
            while (j10 != 0) {
                if ((j10 & 1) != 0) {
                    jMulMod = mulMod(jMulMod, j9, j11);
                }
                j9 = squareMod(j9, j11);
                j10 >>= 1;
            }
            return jMulMod;
        }

        public static boolean test(long j9, long j10) {
            return (j10 <= LongMath.FLOOR_SQRT_MAX_LONG ? SMALL : LARGE).testWitness(j9, j10);
        }

        private boolean testWitness(long j9, long j10) {
            long j11 = j10 - 1;
            int iNumberOfTrailingZeros = Long.numberOfTrailingZeros(j11);
            long j12 = j11 >> iNumberOfTrailingZeros;
            long j13 = j9 % j10;
            if (j13 == 0) {
                return true;
            }
            long jPowMod = powMod(j13, j12, j10);
            if (jPowMod == 1) {
                return true;
            }
            int i9 = 0;
            while (jPowMod != j11) {
                i9++;
                if (i9 == iNumberOfTrailingZeros) {
                    return false;
                }
                jPowMod = squareMod(jPowMod, j10);
            }
            return true;
        }

        public abstract long mulMod(long j9, long j10, long j11);

        public abstract long squareMod(long j9, long j10);

        /* synthetic */ MillerRabinTester(C38961 c38961) {
            this();
        }
    }

    private LongMath() {
    }

    public static long binomial(int i9, int i10) {
        MathPreconditions.checkNonNegative("n", i9);
        MathPreconditions.checkNonNegative("k", i10);
        Preconditions.checkArgument(i10 <= i9, "k (%s) > n (%s)", i10, i9);
        if (i10 > (i9 >> 1)) {
            i10 = i9 - i10;
        }
        long jMultiplyFraction = 1;
        if (i10 == 0) {
            return 1L;
        }
        if (i10 == 1) {
            return i9;
        }
        long[] jArr = factorials;
        if (i9 < jArr.length) {
            return jArr[i9] / (jArr[i10] * jArr[i9 - i10]);
        }
        int[] iArr = biggestBinomials;
        if (i10 >= iArr.length || i9 > iArr[i10]) {
            return Long.MAX_VALUE;
        }
        int[] iArr2 = biggestSimpleBinomials;
        if (i10 < iArr2.length && i9 <= iArr2[i10]) {
            int i11 = i9 - 1;
            long j9 = i9;
            for (int i12 = 2; i12 <= i10; i12++) {
                j9 = (j9 * i11) / i12;
                i11--;
            }
            return j9;
        }
        long j10 = i9;
        int iLog2 = log2(j10, RoundingMode.CEILING);
        int i13 = i9 - 1;
        int i14 = iLog2;
        int i15 = 2;
        long j11 = j10;
        long j12 = 1;
        while (i15 <= i10) {
            i14 += iLog2;
            if (i14 < 63) {
                j11 *= i13;
                j12 *= i15;
            } else {
                jMultiplyFraction = multiplyFraction(jMultiplyFraction, j11, j12);
                j11 = i13;
                j12 = i15;
                i14 = iLog2;
            }
            i15++;
            i13--;
        }
        return multiplyFraction(jMultiplyFraction, j11, j12);
    }

    @Beta
    public static long ceilingPowerOfTwo(long j9) {
        MathPreconditions.checkPositive("x", j9);
        if (j9 <= 4611686018427387904L) {
            return 1 << (-Long.numberOfLeadingZeros(j9 - 1));
        }
        throw new ArithmeticException("ceilingPowerOfTwo(" + j9 + ") is not representable as a long");
    }

    @GwtIncompatible
    public static long checkedAdd(long j9, long j10) {
        long j11 = j9 + j10;
        MathPreconditions.checkNoOverflow(((j10 ^ j9) < 0) | ((j9 ^ j11) >= 0));
        return j11;
    }

    @GwtIncompatible
    public static long checkedMultiply(long j9, long j10) {
        int iNumberOfLeadingZeros = Long.numberOfLeadingZeros(j9) + Long.numberOfLeadingZeros(~j9) + Long.numberOfLeadingZeros(j10) + Long.numberOfLeadingZeros(~j10);
        if (iNumberOfLeadingZeros > 65) {
            return j9 * j10;
        }
        boolean z8 = true;
        MathPreconditions.checkNoOverflow(iNumberOfLeadingZeros >= 64);
        MathPreconditions.checkNoOverflow((j9 >= 0) | (j10 != Long.MIN_VALUE));
        long j11 = j9 * j10;
        if (j9 != 0 && j11 / j9 != j10) {
            z8 = false;
        }
        MathPreconditions.checkNoOverflow(z8);
        return j11;
    }

    @GwtIncompatible
    public static long checkedPow(long j9, int i9) {
        MathPreconditions.checkNonNegative("exponent", i9);
        long jCheckedMultiply = 1;
        if (!(j9 >= -2) || !(j9 <= 2)) {
            while (i9 != 0) {
                if (i9 == 1) {
                    return checkedMultiply(jCheckedMultiply, j9);
                }
                if ((i9 & 1) != 0) {
                    jCheckedMultiply = checkedMultiply(jCheckedMultiply, j9);
                }
                i9 >>= 1;
                if (i9 > 0) {
                    MathPreconditions.checkNoOverflow(-3037000499L <= j9 && j9 <= FLOOR_SQRT_MAX_LONG);
                    j9 *= j9;
                }
            }
            return jCheckedMultiply;
        }
        int i10 = (int) j9;
        if (i10 == -2) {
            MathPreconditions.checkNoOverflow(i9 < 64);
            return (i9 & 1) == 0 ? 1 << i9 : (-1) << i9;
        }
        if (i10 == -1) {
            return (i9 & 1) == 0 ? 1L : -1L;
        }
        if (i10 == 0) {
            return i9 == 0 ? 1L : 0L;
        }
        if (i10 == 1) {
            return 1L;
        }
        if (i10 != 2) {
            throw new AssertionError();
        }
        MathPreconditions.checkNoOverflow(i9 < 63);
        return 1 << i9;
    }

    @GwtIncompatible
    public static long checkedSubtract(long j9, long j10) {
        long j11 = j9 - j10;
        MathPreconditions.checkNoOverflow(((j10 ^ j9) >= 0) | ((j9 ^ j11) >= 0));
        return j11;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:38:? A[RETURN, SYNTHETIC] */
    @GwtIncompatible
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static long divide(long j9, long j10, RoundingMode roundingMode) {
        Preconditions.checkNotNull(roundingMode);
        long j11 = j9 / j10;
        long j12 = j9 - (j10 * j11);
        if (j12 == 0) {
            return j11;
        }
        int i9 = ((int) ((j9 ^ j10) >> 63)) | 1;
        switch (C38961.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(j12 == 0);
                z = false;
                return !z ? j11 + i9 : j11;
            case 2:
                z = false;
                if (!z) {
                }
                break;
            case 3:
                if (i9 >= 0) {
                }
                if (!z) {
                }
                break;
            case 4:
                if (!z) {
                }
                break;
            case 5:
                if (i9 <= 0) {
                }
                if (!z) {
                }
                break;
            case 6:
            case 7:
            case 8:
                long jAbs = Math.abs(j12);
                long jAbs2 = jAbs - (Math.abs(j10) - jAbs);
                if (jAbs2 == 0) {
                    z = (((1 & j11) != 0) & (roundingMode == RoundingMode.HALF_EVEN)) | (roundingMode == RoundingMode.HALF_UP);
                } else if (jAbs2 <= 0) {
                }
                if (!z) {
                }
                break;
            default:
                throw new AssertionError();
        }
    }

    @GwtIncompatible
    public static long factorial(int i9) {
        MathPreconditions.checkNonNegative("n", i9);
        long[] jArr = factorials;
        if (i9 < jArr.length) {
            return jArr[i9];
        }
        return Long.MAX_VALUE;
    }

    public static boolean fitsInInt(long j9) {
        return ((long) ((int) j9)) == j9;
    }

    @Beta
    public static long floorPowerOfTwo(long j9) {
        MathPreconditions.checkPositive("x", j9);
        return 1 << (63 - Long.numberOfLeadingZeros(j9));
    }

    public static long gcd(long j9, long j10) {
        MathPreconditions.checkNonNegative("a", j9);
        MathPreconditions.checkNonNegative("b", j10);
        if (j9 == 0) {
            return j10;
        }
        if (j10 == 0) {
            return j9;
        }
        int iNumberOfTrailingZeros = Long.numberOfTrailingZeros(j9);
        long jNumberOfTrailingZeros = j9 >> iNumberOfTrailingZeros;
        int iNumberOfTrailingZeros2 = Long.numberOfTrailingZeros(j10);
        long j11 = j10 >> iNumberOfTrailingZeros2;
        while (jNumberOfTrailingZeros != j11) {
            long j12 = jNumberOfTrailingZeros - j11;
            long j13 = (j12 >> 63) & j12;
            long j14 = (j12 - j13) - j13;
            j11 += j13;
            jNumberOfTrailingZeros = j14 >> Long.numberOfTrailingZeros(j14);
        }
        return jNumberOfTrailingZeros << Math.min(iNumberOfTrailingZeros, iNumberOfTrailingZeros2);
    }

    public static boolean isPowerOfTwo(long j9) {
        return (j9 > 0) & ((j9 & (j9 - 1)) == 0);
    }

    @Beta
    @GwtIncompatible
    public static boolean isPrime(long j9) {
        if (j9 < 2) {
            MathPreconditions.checkNonNegative("n", j9);
            return false;
        }
        if (j9 == 2 || j9 == 3 || j9 == 5 || j9 == 7 || j9 == 11 || j9 == 13) {
            return true;
        }
        if (((1 << ((int) (j9 % 30))) & SIEVE_30) != 0 || j9 % 7 == 0 || j9 % 11 == 0 || j9 % 13 == 0) {
            return false;
        }
        if (j9 < 289) {
            return true;
        }
        for (long[] jArr : millerRabinBaseSets) {
            if (j9 <= jArr[0]) {
                for (int i9 = 1; i9 < jArr.length; i9++) {
                    if (!MillerRabinTester.test(jArr[i9], j9)) {
                        return false;
                    }
                }
                return true;
            }
        }
        throw new AssertionError();
    }

    @VisibleForTesting
    public static int lessThanBranchFree(long j9, long j10) {
        return (int) ((~(~(j9 - j10))) >>> 63);
    }

    @GwtIncompatible
    public static int log10(long j9, RoundingMode roundingMode) {
        int iLessThanBranchFree;
        MathPreconditions.checkPositive("x", j9);
        int iLog10Floor = log10Floor(j9);
        long j10 = powersOf10[iLog10Floor];
        switch (C38961.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(j9 == j10);
            case 2:
            case 3:
                return iLog10Floor;
            case 4:
            case 5:
                iLessThanBranchFree = lessThanBranchFree(j10, j9);
                return iLog10Floor + iLessThanBranchFree;
            case 6:
            case 7:
            case 8:
                iLessThanBranchFree = lessThanBranchFree(halfPowersOf10[iLog10Floor], j9);
                return iLog10Floor + iLessThanBranchFree;
            default:
                throw new AssertionError();
        }
    }

    @GwtIncompatible
    public static int log10Floor(long j9) {
        byte b9 = maxLog10ForLeadingZeros[Long.numberOfLeadingZeros(j9)];
        return b9 - lessThanBranchFree(j9, powersOf10[b9]);
    }

    public static int log2(long j9, RoundingMode roundingMode) {
        MathPreconditions.checkPositive("x", j9);
        switch (C38961.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(isPowerOfTwo(j9));
                break;
            case 2:
            case 3:
                break;
            case 4:
            case 5:
                return 64 - Long.numberOfLeadingZeros(j9 - 1);
            case 6:
            case 7:
            case 8:
                int iNumberOfLeadingZeros = Long.numberOfLeadingZeros(j9);
                return (63 - iNumberOfLeadingZeros) + lessThanBranchFree(MAX_POWER_OF_SQRT2_UNSIGNED >>> iNumberOfLeadingZeros, j9);
            default:
                throw new AssertionError("impossible");
        }
        return 63 - Long.numberOfLeadingZeros(j9);
    }

    public static long mean(long j9, long j10) {
        return (j9 & j10) + ((j9 ^ j10) >> 1);
    }

    @GwtIncompatible
    public static int mod(long j9, int i9) {
        return (int) mod(j9, i9);
    }

    public static long multiplyFraction(long j9, long j10, long j11) {
        if (j9 == 1) {
            return j10 / j11;
        }
        long jGcd = gcd(j9, j11);
        return (j9 / jGcd) * (j10 / (j11 / jGcd));
    }

    @GwtIncompatible
    public static long pow(long j9, int i9) {
        MathPreconditions.checkNonNegative("exponent", i9);
        if (-2 > j9 || j9 > 2) {
            long j10 = 1;
            while (i9 != 0) {
                if (i9 == 1) {
                    return j10 * j9;
                }
                j10 *= (i9 & 1) == 0 ? 1L : j9;
                j9 *= j9;
                i9 >>= 1;
            }
            return j10;
        }
        int i10 = (int) j9;
        if (i10 == -2) {
            if (i9 < 64) {
                return (i9 & 1) == 0 ? 1 << i9 : -(1 << i9);
            }
            return 0L;
        }
        if (i10 == -1) {
            return (i9 & 1) == 0 ? 1L : -1L;
        }
        if (i10 == 0) {
            return i9 == 0 ? 1L : 0L;
        }
        if (i10 == 1) {
            return 1L;
        }
        if (i10 != 2) {
            throw new AssertionError();
        }
        if (i9 < 64) {
            return 1 << i9;
        }
        return 0L;
    }

    @Beta
    public static long saturatedAdd(long j9, long j10) {
        long j11 = j9 + j10;
        return (((j10 ^ j9) > 0L ? 1 : ((j10 ^ j9) == 0L ? 0 : -1)) < 0) | ((j9 ^ j11) >= 0) ? j11 : ((j11 >>> 63) ^ 1) + Long.MAX_VALUE;
    }

    @Beta
    public static long saturatedMultiply(long j9, long j10) {
        int iNumberOfLeadingZeros = Long.numberOfLeadingZeros(j9) + Long.numberOfLeadingZeros(~j9) + Long.numberOfLeadingZeros(j10) + Long.numberOfLeadingZeros(~j10);
        if (iNumberOfLeadingZeros > 65) {
            return j9 * j10;
        }
        long j11 = ((j9 ^ j10) >>> 63) + Long.MAX_VALUE;
        if ((iNumberOfLeadingZeros < 64) || ((j10 == Long.MIN_VALUE) & (j9 < 0))) {
            return j11;
        }
        long j12 = j9 * j10;
        return (j9 == 0 || j12 / j9 == j10) ? j12 : j11;
    }

    @Beta
    public static long saturatedPow(long j9, int i9) {
        MathPreconditions.checkNonNegative("exponent", i9);
        long jSaturatedMultiply = 1;
        if (!(j9 >= -2) || !(j9 <= 2)) {
            long j10 = ((j9 >>> 63) & i9 & 1) + Long.MAX_VALUE;
            while (i9 != 0) {
                if (i9 == 1) {
                    return saturatedMultiply(jSaturatedMultiply, j9);
                }
                if ((i9 & 1) != 0) {
                    jSaturatedMultiply = saturatedMultiply(jSaturatedMultiply, j9);
                }
                i9 >>= 1;
                if (i9 > 0) {
                    if ((-3037000499L > j9) || (j9 > FLOOR_SQRT_MAX_LONG)) {
                        return j10;
                    }
                    j9 *= j9;
                }
            }
            return jSaturatedMultiply;
        }
        int i10 = (int) j9;
        if (i10 == -2) {
            return i9 >= 64 ? (i9 & 1) + Long.MAX_VALUE : (i9 & 1) == 0 ? 1 << i9 : (-1) << i9;
        }
        if (i10 == -1) {
            return (i9 & 1) == 0 ? 1L : -1L;
        }
        if (i10 == 0) {
            return i9 == 0 ? 1L : 0L;
        }
        if (i10 == 1) {
            return 1L;
        }
        if (i10 != 2) {
            throw new AssertionError();
        }
        if (i9 >= 63) {
            return Long.MAX_VALUE;
        }
        return 1 << i9;
    }

    @Beta
    public static long saturatedSubtract(long j9, long j10) {
        long j11 = j9 - j10;
        return (((j10 ^ j9) > 0L ? 1 : ((j10 ^ j9) == 0L ? 0 : -1)) >= 0) | ((j9 ^ j11) >= 0) ? j11 : ((j11 >>> 63) ^ 1) + Long.MAX_VALUE;
    }

    @GwtIncompatible
    public static long sqrt(long j9, RoundingMode roundingMode) {
        MathPreconditions.checkNonNegative("x", j9);
        if (fitsInInt(j9)) {
            return IntMath.sqrt((int) j9, roundingMode);
        }
        long jSqrt = (long) Math.sqrt(j9);
        long j10 = jSqrt * jSqrt;
        switch (C38961.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(j10 == j9);
                return jSqrt;
            case 2:
            case 3:
                return j9 < j10 ? jSqrt - 1 : jSqrt;
            case 4:
            case 5:
                return j9 > j10 ? jSqrt + 1 : jSqrt;
            case 6:
            case 7:
            case 8:
                return (jSqrt - (j9 >= j10 ? 0 : 1)) + lessThanBranchFree((r0 * r0) + r0, j9);
            default:
                throw new AssertionError();
        }
    }

    @GwtIncompatible
    public static long mod(long j9, long j10) {
        if (j10 <= 0) {
            throw new ArithmeticException("Modulus must be positive");
        }
        long j11 = j9 % j10;
        return j11 >= 0 ? j11 : j11 + j10;
    }
}
