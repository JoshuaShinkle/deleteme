package com.google.common.math;

import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.extractor.ogg.DefaultOggSeeker;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import java.math.RoundingMode;

@GwtCompatible(emulated = true)
/* loaded from: classes2.dex */
public final class IntMath {

    @VisibleForTesting
    static final int FLOOR_SQRT_MAX_INT = 46340;

    @VisibleForTesting
    static final int MAX_POWER_OF_SQRT2_UNSIGNED = -1257966797;

    @VisibleForTesting
    static final int MAX_SIGNED_POWER_OF_TWO = 1073741824;

    @VisibleForTesting
    static final byte[] maxLog10ForLeadingZeros = {9, 9, 9, 8, 8, 8, 7, 7, 7, 6, 6, 6, 6, 5, 5, 5, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 1, 1, 1, 0, 0, 0, 0};

    @VisibleForTesting
    static final int[] powersOf10 = {1, 10, 100, CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT, 10000, DefaultOggSeeker.MATCH_BYTE_RANGE, 1000000, 10000000, 100000000, 1000000000};

    @VisibleForTesting
    static final int[] halfPowersOf10 = {3, 31, 316, 3162, 31622, 316227, 3162277, 31622776, 316227766, Integer.MAX_VALUE};
    private static final int[] factorials = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800, 39916800, 479001600};

    @VisibleForTesting
    static int[] biggestBinomials = {Integer.MAX_VALUE, Integer.MAX_VALUE, C3322C.DEFAULT_BUFFER_SEGMENT_SIZE, 2345, 477, 193, 110, 75, 58, 49, 43, 39, 37, 35, 34, 34, 33};

    /* renamed from: com.google.common.math.IntMath$1 */
    public static /* synthetic */ class C38941 {
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

    private IntMath() {
    }

    public static int binomial(int i9, int i10) {
        MathPreconditions.checkNonNegative("n", i9);
        MathPreconditions.checkNonNegative("k", i10);
        int i11 = 0;
        Preconditions.checkArgument(i10 <= i9, "k (%s) > n (%s)", i10, i9);
        if (i10 > (i9 >> 1)) {
            i10 = i9 - i10;
        }
        int[] iArr = biggestBinomials;
        if (i10 >= iArr.length || i9 > iArr[i10]) {
            return Integer.MAX_VALUE;
        }
        if (i10 == 0) {
            return 1;
        }
        if (i10 == 1) {
            return i9;
        }
        long j9 = 1;
        while (i11 < i10) {
            long j10 = j9 * (i9 - i11);
            i11++;
            j9 = j10 / i11;
        }
        return (int) j9;
    }

    @Beta
    public static int ceilingPowerOfTwo(int i9) {
        MathPreconditions.checkPositive("x", i9);
        if (i9 <= 1073741824) {
            return 1 << (-Integer.numberOfLeadingZeros(i9 - 1));
        }
        throw new ArithmeticException("ceilingPowerOfTwo(" + i9 + ") not representable as an int");
    }

    public static int checkedAdd(int i9, int i10) {
        long j9 = i9 + i10;
        int i11 = (int) j9;
        MathPreconditions.checkNoOverflow(j9 == ((long) i11));
        return i11;
    }

    public static int checkedMultiply(int i9, int i10) {
        long j9 = i9 * i10;
        int i11 = (int) j9;
        MathPreconditions.checkNoOverflow(j9 == ((long) i11));
        return i11;
    }

    public static int checkedPow(int i9, int i10) {
        MathPreconditions.checkNonNegative("exponent", i10);
        if (i9 == -2) {
            MathPreconditions.checkNoOverflow(i10 < 32);
            return (i10 & 1) == 0 ? 1 << i10 : (-1) << i10;
        }
        if (i9 == -1) {
            return (i10 & 1) == 0 ? 1 : -1;
        }
        if (i9 == 0) {
            return i10 == 0 ? 1 : 0;
        }
        if (i9 == 1) {
            return 1;
        }
        if (i9 == 2) {
            MathPreconditions.checkNoOverflow(i10 < 31);
            return 1 << i10;
        }
        int iCheckedMultiply = 1;
        while (i10 != 0) {
            if (i10 == 1) {
                return checkedMultiply(iCheckedMultiply, i9);
            }
            if ((i10 & 1) != 0) {
                iCheckedMultiply = checkedMultiply(iCheckedMultiply, i9);
            }
            i10 >>= 1;
            if (i10 > 0) {
                MathPreconditions.checkNoOverflow((-46340 <= i9) & (i9 <= FLOOR_SQRT_MAX_INT));
                i9 *= i9;
            }
        }
        return iCheckedMultiply;
    }

    public static int checkedSubtract(int i9, int i10) {
        long j9 = i9 - i10;
        int i11 = (int) j9;
        MathPreconditions.checkNoOverflow(j9 == ((long) i11));
        return i11;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:42:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int divide(int i9, int i10, RoundingMode roundingMode) {
        Preconditions.checkNotNull(roundingMode);
        if (i10 == 0) {
            throw new ArithmeticException("/ by zero");
        }
        int i11 = i9 / i10;
        int i12 = i9 - (i10 * i11);
        if (i12 == 0) {
            return i11;
        }
        int i13 = ((i9 ^ i10) >> 31) | 1;
        switch (C38941.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(i12 == 0);
                z = false;
                return !z ? i11 + i13 : i11;
            case 2:
                z = false;
                if (!z) {
                }
                break;
            case 3:
                if (i13 >= 0) {
                }
                if (!z) {
                }
                break;
            case 4:
                if (!z) {
                }
                break;
            case 5:
                if (i13 <= 0) {
                }
                if (!z) {
                }
                break;
            case 6:
            case 7:
            case 8:
                int iAbs = Math.abs(i12);
                int iAbs2 = iAbs - (Math.abs(i10) - iAbs);
                if (iAbs2 == 0) {
                    if (roundingMode != RoundingMode.HALF_UP) {
                        if (!((roundingMode == RoundingMode.HALF_EVEN) & ((i11 & 1) != 0))) {
                        }
                    }
                } else if (iAbs2 <= 0) {
                }
                if (!z) {
                }
                break;
            default:
                throw new AssertionError();
        }
    }

    public static int factorial(int i9) {
        MathPreconditions.checkNonNegative("n", i9);
        int[] iArr = factorials;
        if (i9 < iArr.length) {
            return iArr[i9];
        }
        return Integer.MAX_VALUE;
    }

    @Beta
    public static int floorPowerOfTwo(int i9) {
        MathPreconditions.checkPositive("x", i9);
        return Integer.highestOneBit(i9);
    }

    public static int gcd(int i9, int i10) {
        MathPreconditions.checkNonNegative("a", i9);
        MathPreconditions.checkNonNegative("b", i10);
        if (i9 == 0) {
            return i10;
        }
        if (i10 == 0) {
            return i9;
        }
        int iNumberOfTrailingZeros = Integer.numberOfTrailingZeros(i9);
        int iNumberOfTrailingZeros2 = i9 >> iNumberOfTrailingZeros;
        int iNumberOfTrailingZeros3 = Integer.numberOfTrailingZeros(i10);
        int i11 = i10 >> iNumberOfTrailingZeros3;
        while (iNumberOfTrailingZeros2 != i11) {
            int i12 = iNumberOfTrailingZeros2 - i11;
            int i13 = (i12 >> 31) & i12;
            int i14 = (i12 - i13) - i13;
            i11 += i13;
            iNumberOfTrailingZeros2 = i14 >> Integer.numberOfTrailingZeros(i14);
        }
        return iNumberOfTrailingZeros2 << Math.min(iNumberOfTrailingZeros, iNumberOfTrailingZeros3);
    }

    public static boolean isPowerOfTwo(int i9) {
        return (i9 > 0) & ((i9 & (i9 + (-1))) == 0);
    }

    @Beta
    @GwtIncompatible
    public static boolean isPrime(int i9) {
        return LongMath.isPrime(i9);
    }

    @VisibleForTesting
    public static int lessThanBranchFree(int i9, int i10) {
        return (~(~(i9 - i10))) >>> 31;
    }

    @GwtIncompatible
    public static int log10(int i9, RoundingMode roundingMode) {
        int iLessThanBranchFree;
        MathPreconditions.checkPositive("x", i9);
        int iLog10Floor = log10Floor(i9);
        int i10 = powersOf10[iLog10Floor];
        switch (C38941.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(i9 == i10);
            case 2:
            case 3:
                return iLog10Floor;
            case 4:
            case 5:
                iLessThanBranchFree = lessThanBranchFree(i10, i9);
                return iLog10Floor + iLessThanBranchFree;
            case 6:
            case 7:
            case 8:
                iLessThanBranchFree = lessThanBranchFree(halfPowersOf10[iLog10Floor], i9);
                return iLog10Floor + iLessThanBranchFree;
            default:
                throw new AssertionError();
        }
    }

    private static int log10Floor(int i9) {
        byte b9 = maxLog10ForLeadingZeros[Integer.numberOfLeadingZeros(i9)];
        return b9 - lessThanBranchFree(i9, powersOf10[b9]);
    }

    public static int log2(int i9, RoundingMode roundingMode) {
        MathPreconditions.checkPositive("x", i9);
        switch (C38941.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(isPowerOfTwo(i9));
                break;
            case 2:
            case 3:
                break;
            case 4:
            case 5:
                return 32 - Integer.numberOfLeadingZeros(i9 - 1);
            case 6:
            case 7:
            case 8:
                int iNumberOfLeadingZeros = Integer.numberOfLeadingZeros(i9);
                return (31 - iNumberOfLeadingZeros) + lessThanBranchFree(MAX_POWER_OF_SQRT2_UNSIGNED >>> iNumberOfLeadingZeros, i9);
            default:
                throw new AssertionError();
        }
        return 31 - Integer.numberOfLeadingZeros(i9);
    }

    public static int mean(int i9, int i10) {
        return (i9 & i10) + ((i9 ^ i10) >> 1);
    }

    public static int mod(int i9, int i10) {
        if (i10 > 0) {
            int i11 = i9 % i10;
            return i11 >= 0 ? i11 : i11 + i10;
        }
        throw new ArithmeticException("Modulus " + i10 + " must be > 0");
    }

    @GwtIncompatible
    public static int pow(int i9, int i10) {
        MathPreconditions.checkNonNegative("exponent", i10);
        if (i9 == -2) {
            if (i10 < 32) {
                return (i10 & 1) == 0 ? 1 << i10 : -(1 << i10);
            }
            return 0;
        }
        if (i9 == -1) {
            return (i10 & 1) == 0 ? 1 : -1;
        }
        if (i9 == 0) {
            return i10 == 0 ? 1 : 0;
        }
        if (i9 == 1) {
            return 1;
        }
        if (i9 == 2) {
            if (i10 < 32) {
                return 1 << i10;
            }
            return 0;
        }
        int i11 = 1;
        while (i10 != 0) {
            if (i10 == 1) {
                return i9 * i11;
            }
            i11 *= (i10 & 1) == 0 ? 1 : i9;
            i9 *= i9;
            i10 >>= 1;
        }
        return i11;
    }

    @Beta
    public static int saturatedAdd(int i9, int i10) {
        return Ints.saturatedCast(i9 + i10);
    }

    @Beta
    public static int saturatedMultiply(int i9, int i10) {
        return Ints.saturatedCast(i9 * i10);
    }

    @Beta
    public static int saturatedPow(int i9, int i10) {
        MathPreconditions.checkNonNegative("exponent", i10);
        if (i9 == -2) {
            return i10 >= 32 ? (i10 & 1) + Integer.MAX_VALUE : (i10 & 1) == 0 ? 1 << i10 : (-1) << i10;
        }
        if (i9 == -1) {
            return (i10 & 1) == 0 ? 1 : -1;
        }
        if (i9 == 0) {
            return i10 == 0 ? 1 : 0;
        }
        if (i9 == 1) {
            return 1;
        }
        if (i9 == 2) {
            if (i10 >= 31) {
                return Integer.MAX_VALUE;
            }
            return 1 << i10;
        }
        int i11 = ((i9 >>> 31) & i10 & 1) + Integer.MAX_VALUE;
        int iSaturatedMultiply = 1;
        while (i10 != 0) {
            if (i10 == 1) {
                return saturatedMultiply(iSaturatedMultiply, i9);
            }
            if ((i10 & 1) != 0) {
                iSaturatedMultiply = saturatedMultiply(iSaturatedMultiply, i9);
            }
            i10 >>= 1;
            if (i10 > 0) {
                if ((-46340 > i9) || (i9 > FLOOR_SQRT_MAX_INT)) {
                    return i11;
                }
                i9 *= i9;
            }
        }
        return iSaturatedMultiply;
    }

    @Beta
    public static int saturatedSubtract(int i9, int i10) {
        return Ints.saturatedCast(i9 - i10);
    }

    @GwtIncompatible
    public static int sqrt(int i9, RoundingMode roundingMode) {
        int iLessThanBranchFree;
        MathPreconditions.checkNonNegative("x", i9);
        int iSqrtFloor = sqrtFloor(i9);
        switch (C38941.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(iSqrtFloor * iSqrtFloor == i9);
            case 2:
            case 3:
                return iSqrtFloor;
            case 4:
            case 5:
                iLessThanBranchFree = lessThanBranchFree(iSqrtFloor * iSqrtFloor, i9);
                return iSqrtFloor + iLessThanBranchFree;
            case 6:
            case 7:
            case 8:
                iLessThanBranchFree = lessThanBranchFree((iSqrtFloor * iSqrtFloor) + iSqrtFloor, i9);
                return iSqrtFloor + iLessThanBranchFree;
            default:
                throw new AssertionError();
        }
    }

    private static int sqrtFloor(int i9) {
        return (int) Math.sqrt(i9);
    }
}
