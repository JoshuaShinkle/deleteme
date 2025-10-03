package com.google.common.primitives;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Comparator;

@Beta
@GwtCompatible
/* loaded from: classes2.dex */
public final class UnsignedInts {
    static final long INT_MASK = 4294967295L;

    public enum LexicographicalComparator implements Comparator<int[]> {
        INSTANCE;

        @Override // java.lang.Enum
        public String toString() {
            return "UnsignedInts.lexicographicalComparator()";
        }

        @Override // java.util.Comparator
        public int compare(int[] iArr, int[] iArr2) {
            int iMin = Math.min(iArr.length, iArr2.length);
            for (int i9 = 0; i9 < iMin; i9++) {
                int i10 = iArr[i9];
                int i11 = iArr2[i9];
                if (i10 != i11) {
                    return UnsignedInts.compare(i10, i11);
                }
            }
            return iArr.length - iArr2.length;
        }
    }

    private UnsignedInts() {
    }

    public static int checkedCast(long j9) {
        Preconditions.checkArgument((j9 >> 32) == 0, "out of range: %s", j9);
        return (int) j9;
    }

    public static int compare(int i9, int i10) {
        return Ints.compare(flip(i9), flip(i10));
    }

    @CanIgnoreReturnValue
    public static int decode(String str) {
        ParseRequest parseRequestFromString = ParseRequest.fromString(str);
        try {
            return parseUnsignedInt(parseRequestFromString.rawValue, parseRequestFromString.radix);
        } catch (NumberFormatException e9) {
            NumberFormatException numberFormatException = new NumberFormatException("Error parsing value: " + str);
            numberFormatException.initCause(e9);
            throw numberFormatException;
        }
    }

    public static int divide(int i9, int i10) {
        return (int) (toLong(i9) / toLong(i10));
    }

    public static int flip(int i9) {
        return i9 ^ Integer.MIN_VALUE;
    }

    public static String join(String str, int... iArr) {
        Preconditions.checkNotNull(str);
        if (iArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(iArr.length * 5);
        sb.append(toString(iArr[0]));
        for (int i9 = 1; i9 < iArr.length; i9++) {
            sb.append(str);
            sb.append(toString(iArr[i9]));
        }
        return sb.toString();
    }

    public static Comparator<int[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    public static int max(int... iArr) {
        Preconditions.checkArgument(iArr.length > 0);
        int iFlip = flip(iArr[0]);
        for (int i9 = 1; i9 < iArr.length; i9++) {
            int iFlip2 = flip(iArr[i9]);
            if (iFlip2 > iFlip) {
                iFlip = iFlip2;
            }
        }
        return flip(iFlip);
    }

    public static int min(int... iArr) {
        Preconditions.checkArgument(iArr.length > 0);
        int iFlip = flip(iArr[0]);
        for (int i9 = 1; i9 < iArr.length; i9++) {
            int iFlip2 = flip(iArr[i9]);
            if (iFlip2 < iFlip) {
                iFlip = iFlip2;
            }
        }
        return flip(iFlip);
    }

    @CanIgnoreReturnValue
    public static int parseUnsignedInt(String str) {
        return parseUnsignedInt(str, 10);
    }

    public static int remainder(int i9, int i10) {
        return (int) (toLong(i9) % toLong(i10));
    }

    public static int saturatedCast(long j9) {
        if (j9 <= 0) {
            return 0;
        }
        if (j9 >= 4294967296L) {
            return -1;
        }
        return (int) j9;
    }

    public static long toLong(int i9) {
        return i9 & INT_MASK;
    }

    public static String toString(int i9) {
        return toString(i9, 10);
    }

    @CanIgnoreReturnValue
    public static int parseUnsignedInt(String str, int i9) throws NumberFormatException {
        Preconditions.checkNotNull(str);
        long j9 = Long.parseLong(str, i9);
        if ((INT_MASK & j9) == j9) {
            return (int) j9;
        }
        throw new NumberFormatException("Input " + str + " in base " + i9 + " is not in the range of an unsigned integer");
    }

    public static String toString(int i9, int i10) {
        return Long.toString(i9 & INT_MASK, i10);
    }
}
