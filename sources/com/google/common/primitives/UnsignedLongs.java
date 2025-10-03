package com.google.common.primitives;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.math.BigInteger;
import java.util.Comparator;

@Beta
@GwtCompatible
/* loaded from: classes2.dex */
public final class UnsignedLongs {
    public static final long MAX_VALUE = -1;

    public enum LexicographicalComparator implements Comparator<long[]> {
        INSTANCE;

        @Override // java.lang.Enum
        public String toString() {
            return "UnsignedLongs.lexicographicalComparator()";
        }

        @Override // java.util.Comparator
        public int compare(long[] jArr, long[] jArr2) {
            int iMin = Math.min(jArr.length, jArr2.length);
            for (int i9 = 0; i9 < iMin; i9++) {
                long j9 = jArr[i9];
                long j10 = jArr2[i9];
                if (j9 != j10) {
                    return UnsignedLongs.compare(j9, j10);
                }
            }
            return jArr.length - jArr2.length;
        }
    }

    public static final class ParseOverflowDetection {
        static final long[] maxValueDivs = new long[37];
        static final int[] maxValueMods = new int[37];
        static final int[] maxSafeDigits = new int[37];

        static {
            BigInteger bigInteger = new BigInteger("10000000000000000", 16);
            for (int i9 = 2; i9 <= 36; i9++) {
                long j9 = i9;
                maxValueDivs[i9] = UnsignedLongs.divide(-1L, j9);
                maxValueMods[i9] = (int) UnsignedLongs.remainder(-1L, j9);
                maxSafeDigits[i9] = bigInteger.toString(i9).length() - 1;
            }
        }

        private ParseOverflowDetection() {
        }

        public static boolean overflowInParse(long j9, int i9, int i10) {
            if (j9 < 0) {
                return true;
            }
            long j10 = maxValueDivs[i10];
            if (j9 < j10) {
                return false;
            }
            return j9 > j10 || i9 > maxValueMods[i10];
        }
    }

    private UnsignedLongs() {
    }

    public static int compare(long j9, long j10) {
        return Longs.compare(flip(j9), flip(j10));
    }

    @CanIgnoreReturnValue
    public static long decode(String str) {
        ParseRequest parseRequestFromString = ParseRequest.fromString(str);
        try {
            return parseUnsignedLong(parseRequestFromString.rawValue, parseRequestFromString.radix);
        } catch (NumberFormatException e9) {
            NumberFormatException numberFormatException = new NumberFormatException("Error parsing value: " + str);
            numberFormatException.initCause(e9);
            throw numberFormatException;
        }
    }

    public static long divide(long j9, long j10) {
        if (j10 < 0) {
            return compare(j9, j10) < 0 ? 0L : 1L;
        }
        if (j9 >= 0) {
            return j9 / j10;
        }
        long j11 = ((j9 >>> 1) / j10) << 1;
        return j11 + (compare(j9 - (j11 * j10), j10) < 0 ? 0 : 1);
    }

    private static long flip(long j9) {
        return j9 ^ Long.MIN_VALUE;
    }

    public static String join(String str, long... jArr) {
        Preconditions.checkNotNull(str);
        if (jArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(jArr.length * 5);
        sb.append(toString(jArr[0]));
        for (int i9 = 1; i9 < jArr.length; i9++) {
            sb.append(str);
            sb.append(toString(jArr[i9]));
        }
        return sb.toString();
    }

    public static Comparator<long[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    public static long max(long... jArr) {
        Preconditions.checkArgument(jArr.length > 0);
        long jFlip = flip(jArr[0]);
        for (int i9 = 1; i9 < jArr.length; i9++) {
            long jFlip2 = flip(jArr[i9]);
            if (jFlip2 > jFlip) {
                jFlip = jFlip2;
            }
        }
        return flip(jFlip);
    }

    public static long min(long... jArr) {
        Preconditions.checkArgument(jArr.length > 0);
        long jFlip = flip(jArr[0]);
        for (int i9 = 1; i9 < jArr.length; i9++) {
            long jFlip2 = flip(jArr[i9]);
            if (jFlip2 < jFlip) {
                jFlip = jFlip2;
            }
        }
        return flip(jFlip);
    }

    @CanIgnoreReturnValue
    public static long parseUnsignedLong(String str) {
        return parseUnsignedLong(str, 10);
    }

    public static long remainder(long j9, long j10) {
        if (j10 < 0) {
            return compare(j9, j10) < 0 ? j9 : j9 - j10;
        }
        if (j9 >= 0) {
            return j9 % j10;
        }
        long j11 = j9 - ((((j9 >>> 1) / j10) << 1) * j10);
        if (compare(j11, j10) < 0) {
            j10 = 0;
        }
        return j11 - j10;
    }

    public static String toString(long j9) {
        return toString(j9, 10);
    }

    @CanIgnoreReturnValue
    public static long parseUnsignedLong(String str, int i9) {
        Preconditions.checkNotNull(str);
        if (str.length() == 0) {
            throw new NumberFormatException("empty string");
        }
        if (i9 < 2 || i9 > 36) {
            throw new NumberFormatException("illegal radix: " + i9);
        }
        int i10 = ParseOverflowDetection.maxSafeDigits[i9] - 1;
        long j9 = 0;
        for (int i11 = 0; i11 < str.length(); i11++) {
            int iDigit = Character.digit(str.charAt(i11), i9);
            if (iDigit == -1) {
                throw new NumberFormatException(str);
            }
            if (i11 > i10 && ParseOverflowDetection.overflowInParse(j9, iDigit, i9)) {
                throw new NumberFormatException("Too large for unsigned long: " + str);
            }
            j9 = (j9 * i9) + iDigit;
        }
        return j9;
    }

    public static String toString(long j9, int i9) {
        Preconditions.checkArgument(i9 >= 2 && i9 <= 36, "radix (%s) must be between Character.MIN_RADIX and Character.MAX_RADIX", i9);
        if (j9 == 0) {
            return "0";
        }
        if (j9 > 0) {
            return Long.toString(j9, i9);
        }
        int i10 = 64;
        char[] cArr = new char[64];
        int i11 = i9 - 1;
        if ((i9 & i11) == 0) {
            int iNumberOfTrailingZeros = Integer.numberOfTrailingZeros(i9);
            do {
                i10--;
                cArr[i10] = Character.forDigit(((int) j9) & i11, i9);
                j9 >>>= iNumberOfTrailingZeros;
            } while (j9 != 0);
        } else {
            long jDivide = (i9 & 1) == 0 ? (j9 >>> 1) / (i9 >>> 1) : divide(j9, i9);
            long j10 = i9;
            int i12 = 63;
            cArr[63] = Character.forDigit((int) (j9 - (jDivide * j10)), i9);
            while (jDivide > 0) {
                i12--;
                cArr[i12] = Character.forDigit((int) (jDivide % j10), i9);
                jDivide /= j10;
            }
            i10 = i12;
        }
        return new String(cArr, i10, 64 - i10);
    }
}
