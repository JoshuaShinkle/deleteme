package com.google.common.primitives;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Ascii;
import com.google.common.base.Preconditions;
import java.util.Comparator;

@GwtCompatible
/* loaded from: classes2.dex */
public final class SignedBytes {
    public static final byte MAX_POWER_OF_TWO = 64;

    public enum LexicographicalComparator implements Comparator<byte[]> {
        INSTANCE;

        @Override // java.lang.Enum
        public String toString() {
            return "SignedBytes.lexicographicalComparator()";
        }

        @Override // java.util.Comparator
        public int compare(byte[] bArr, byte[] bArr2) {
            int iMin = Math.min(bArr.length, bArr2.length);
            for (int i9 = 0; i9 < iMin; i9++) {
                int iCompare = SignedBytes.compare(bArr[i9], bArr2[i9]);
                if (iCompare != 0) {
                    return iCompare;
                }
            }
            return bArr.length - bArr2.length;
        }
    }

    private SignedBytes() {
    }

    public static byte checkedCast(long j9) {
        byte b9 = (byte) j9;
        Preconditions.checkArgument(((long) b9) == j9, "Out of range: %s", j9);
        return b9;
    }

    public static int compare(byte b9, byte b10) {
        return b9 - b10;
    }

    public static String join(String str, byte... bArr) {
        Preconditions.checkNotNull(str);
        if (bArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(bArr.length * 5);
        sb.append((int) bArr[0]);
        for (int i9 = 1; i9 < bArr.length; i9++) {
            sb.append(str);
            sb.append((int) bArr[i9]);
        }
        return sb.toString();
    }

    public static Comparator<byte[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    public static byte max(byte... bArr) {
        Preconditions.checkArgument(bArr.length > 0);
        byte b9 = bArr[0];
        for (int i9 = 1; i9 < bArr.length; i9++) {
            byte b10 = bArr[i9];
            if (b10 > b9) {
                b9 = b10;
            }
        }
        return b9;
    }

    public static byte min(byte... bArr) {
        Preconditions.checkArgument(bArr.length > 0);
        byte b9 = bArr[0];
        for (int i9 = 1; i9 < bArr.length; i9++) {
            byte b10 = bArr[i9];
            if (b10 < b9) {
                b9 = b10;
            }
        }
        return b9;
    }

    public static byte saturatedCast(long j9) {
        return j9 > 127 ? Ascii.DEL : j9 < -128 ? UnsignedBytes.MAX_POWER_OF_TWO : (byte) j9;
    }
}
