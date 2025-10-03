package com.google.common.base;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;

@Beta
@GwtCompatible(emulated = true)
/* loaded from: classes2.dex */
public final class Utf8 {
    private Utf8() {
    }

    public static int encodedLength(CharSequence charSequence) {
        int length = charSequence.length();
        int i9 = 0;
        while (i9 < length && charSequence.charAt(i9) < 128) {
            i9++;
        }
        int iEncodedLengthGeneral = length;
        while (true) {
            if (i9 < length) {
                char cCharAt = charSequence.charAt(i9);
                if (cCharAt >= 2048) {
                    iEncodedLengthGeneral += encodedLengthGeneral(charSequence, i9);
                    break;
                }
                iEncodedLengthGeneral += (127 - cCharAt) >>> 31;
                i9++;
            } else {
                break;
            }
        }
        if (iEncodedLengthGeneral >= length) {
            return iEncodedLengthGeneral;
        }
        throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (iEncodedLengthGeneral + 4294967296L));
    }

    private static int encodedLengthGeneral(CharSequence charSequence, int i9) {
        int length = charSequence.length();
        int i10 = 0;
        while (i9 < length) {
            char cCharAt = charSequence.charAt(i9);
            if (cCharAt < 2048) {
                i10 += (127 - cCharAt) >>> 31;
            } else {
                i10 += 2;
                if (55296 <= cCharAt && cCharAt <= 57343) {
                    if (Character.codePointAt(charSequence, i9) == cCharAt) {
                        throw new IllegalArgumentException(unpairedSurrogateMsg(i9));
                    }
                    i9++;
                }
            }
            i9++;
        }
        return i10;
    }

    public static boolean isWellFormed(byte[] bArr) {
        return isWellFormed(bArr, 0, bArr.length);
    }

    private static boolean isWellFormedSlowPath(byte[] bArr, int i9, int i10) {
        byte b9;
        while (i9 < i10) {
            int i11 = i9 + 1;
            byte b10 = bArr[i9];
            if (b10 < 0) {
                if (b10 < -32) {
                    if (i11 != i10 && b10 >= -62) {
                        i9 = i11 + 1;
                        if (bArr[i11] > -65) {
                        }
                    }
                    return false;
                }
                if (b10 < -16) {
                    int i12 = i11 + 1;
                    if (i12 < i10 && (b9 = bArr[i11]) <= -65 && ((b10 != -32 || b9 >= -96) && (b10 != -19 || -96 > b9))) {
                        i9 = i12 + 1;
                        if (bArr[i12] > -65) {
                        }
                    }
                    return false;
                }
                if (i11 + 2 >= i10) {
                    return false;
                }
                int i13 = i11 + 1;
                byte b11 = bArr[i11];
                if (b11 <= -65 && (((b10 << Ascii.f15383FS) + (b11 + 112)) >> 30) == 0) {
                    int i14 = i13 + 1;
                    if (bArr[i13] <= -65) {
                        i11 = i14 + 1;
                        if (bArr[i14] > -65) {
                        }
                    }
                }
                return false;
            }
            i9 = i11;
        }
        return true;
    }

    private static String unpairedSurrogateMsg(int i9) {
        return "Unpaired surrogate at index " + i9;
    }

    public static boolean isWellFormed(byte[] bArr, int i9, int i10) {
        int i11 = i10 + i9;
        Preconditions.checkPositionIndexes(i9, i11, bArr.length);
        while (i9 < i11) {
            if (bArr[i9] < 0) {
                return isWellFormedSlowPath(bArr, i9, i11);
            }
            i9++;
        }
        return true;
    }
}
