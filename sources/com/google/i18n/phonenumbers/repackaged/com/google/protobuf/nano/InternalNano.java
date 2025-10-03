package com.google.i18n.phonenumbers.repackaged.com.google.protobuf.nano;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.apache.commons.lang3.CharEncoding;

/* loaded from: classes2.dex */
public final class InternalNano {
    public static final Object LAZY_INIT_LOCK = new Object();

    private InternalNano() {
    }

    public static byte[] bytesDefaultValue(String str) {
        try {
            return str.getBytes(CharEncoding.ISO_8859_1);
        } catch (UnsupportedEncodingException e9) {
            throw new IllegalStateException("Java VM does not support a standard character set.", e9);
        }
    }

    public static void cloneUnknownFieldData(ExtendableMessageNano extendableMessageNano, ExtendableMessageNano extendableMessageNano2) {
        FieldArray fieldArray = extendableMessageNano.unknownFieldData;
        if (fieldArray != null) {
            extendableMessageNano2.unknownFieldData = fieldArray.m25571clone();
        }
    }

    public static byte[] copyFromUtf8(String str) {
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException unused) {
            throw new RuntimeException("UTF-8 not supported?");
        }
    }

    public static boolean equals(int[] iArr, int[] iArr2) {
        return (iArr == null || iArr.length == 0) ? iArr2 == null || iArr2.length == 0 : Arrays.equals(iArr, iArr2);
    }

    public static int hashCode(int[] iArr) {
        if (iArr == null || iArr.length == 0) {
            return 0;
        }
        return Arrays.hashCode(iArr);
    }

    public static String stringDefaultValue(String str) {
        try {
            return new String(str.getBytes(CharEncoding.ISO_8859_1), "UTF-8");
        } catch (UnsupportedEncodingException e9) {
            throw new IllegalStateException("Java VM does not support a standard character set.", e9);
        }
    }

    public static int hashCode(long[] jArr) {
        if (jArr == null || jArr.length == 0) {
            return 0;
        }
        return Arrays.hashCode(jArr);
    }

    public static int hashCode(float[] fArr) {
        if (fArr == null || fArr.length == 0) {
            return 0;
        }
        return Arrays.hashCode(fArr);
    }

    public static boolean equals(long[] jArr, long[] jArr2) {
        if (jArr == null || jArr.length == 0) {
            return jArr2 == null || jArr2.length == 0;
        }
        return Arrays.equals(jArr, jArr2);
    }

    public static int hashCode(double[] dArr) {
        if (dArr == null || dArr.length == 0) {
            return 0;
        }
        return Arrays.hashCode(dArr);
    }

    public static int hashCode(boolean[] zArr) {
        if (zArr == null || zArr.length == 0) {
            return 0;
        }
        return Arrays.hashCode(zArr);
    }

    public static int hashCode(byte[][] bArr) {
        int length = bArr == null ? 0 : bArr.length;
        int iHashCode = 0;
        for (int i9 = 0; i9 < length; i9++) {
            byte[] bArr2 = bArr[i9];
            if (bArr2 != null) {
                iHashCode = (iHashCode * 31) + Arrays.hashCode(bArr2);
            }
        }
        return iHashCode;
    }

    public static boolean equals(float[] fArr, float[] fArr2) {
        if (fArr == null || fArr.length == 0) {
            return fArr2 == null || fArr2.length == 0;
        }
        return Arrays.equals(fArr, fArr2);
    }

    public static int hashCode(Object[] objArr) {
        int length = objArr == null ? 0 : objArr.length;
        int iHashCode = 0;
        for (int i9 = 0; i9 < length; i9++) {
            Object obj = objArr[i9];
            if (obj != null) {
                iHashCode = (iHashCode * 31) + obj.hashCode();
            }
        }
        return iHashCode;
    }

    public static boolean equals(double[] dArr, double[] dArr2) {
        if (dArr == null || dArr.length == 0) {
            return dArr2 == null || dArr2.length == 0;
        }
        return Arrays.equals(dArr, dArr2);
    }

    public static boolean equals(boolean[] zArr, boolean[] zArr2) {
        if (zArr == null || zArr.length == 0) {
            return zArr2 == null || zArr2.length == 0;
        }
        return Arrays.equals(zArr, zArr2);
    }

    public static boolean equals(byte[][] bArr, byte[][] bArr2) {
        int length = bArr == null ? 0 : bArr.length;
        int length2 = bArr2 == null ? 0 : bArr2.length;
        int i9 = 0;
        int i10 = 0;
        while (true) {
            if (i9 >= length || bArr[i9] != null) {
                while (i10 < length2 && bArr2[i10] == null) {
                    i10++;
                }
                boolean z8 = i9 >= length;
                boolean z9 = i10 >= length2;
                if (z8 && z9) {
                    return true;
                }
                if (z8 != z9 || !Arrays.equals(bArr[i9], bArr2[i10])) {
                    return false;
                }
                i9++;
                i10++;
            } else {
                i9++;
            }
        }
    }

    public static boolean equals(Object[] objArr, Object[] objArr2) {
        int length = objArr == null ? 0 : objArr.length;
        int length2 = objArr2 == null ? 0 : objArr2.length;
        int i9 = 0;
        int i10 = 0;
        while (true) {
            if (i9 >= length || objArr[i9] != null) {
                while (i10 < length2 && objArr2[i10] == null) {
                    i10++;
                }
                boolean z8 = i9 >= length;
                boolean z9 = i10 >= length2;
                if (z8 && z9) {
                    return true;
                }
                if (z8 != z9 || !objArr[i9].equals(objArr2[i10])) {
                    return false;
                }
                i9++;
                i10++;
            } else {
                i9++;
            }
        }
    }
}
