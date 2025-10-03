package com.google.i18n.phonenumbers.repackaged.com.google.protobuf;

import com.google.common.base.Ascii;

/* loaded from: classes2.dex */
final class Utf8 {
    public static final int COMPLETE = 0;
    public static final int MALFORMED = -1;

    private Utf8() {
    }

    private static int incompleteStateFor(int i9) {
        if (i9 > -12) {
            return -1;
        }
        return i9;
    }

    private static int incompleteStateFor(int i9, int i10) {
        if (i9 > -12 || i10 > -65) {
            return -1;
        }
        return i9 ^ (i10 << 8);
    }

    private static int incompleteStateFor(int i9, int i10, int i11) {
        if (i9 > -12 || i10 > -65 || i11 > -65) {
            return -1;
        }
        return (i9 ^ (i10 << 8)) ^ (i11 << 16);
    }

    private static int incompleteStateFor(byte[] bArr, int i9, int i10) {
        byte b9 = bArr[i9 - 1];
        int i11 = i10 - i9;
        if (i11 == 0) {
            return incompleteStateFor(b9);
        }
        if (i11 == 1) {
            return incompleteStateFor(b9, bArr[i9]);
        }
        if (i11 == 2) {
            return incompleteStateFor(b9, bArr[i9], bArr[i9 + 1]);
        }
        throw new AssertionError();
    }

    public static boolean isValidUtf8(byte[] bArr) {
        return isValidUtf8(bArr, 0, bArr.length);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0015, code lost:
    
        if (r7[r8] > (-65)) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0046, code lost:
    
        if (r7[r8] > (-65)) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0083, code lost:
    
        if (r7[r6] > (-65)) goto L53;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int partialIsValidUtf8(int i9, byte[] bArr, int i10, int i11) {
        byte b9;
        int i12;
        int i13;
        if (i9 != 0) {
            if (i10 >= i11) {
                return i9;
            }
            byte b10 = (byte) i9;
            if (b10 < -32) {
                if (b10 >= -62) {
                    i13 = i10 + 1;
                }
                return -1;
            }
            if (b10 < -16) {
                byte b11 = (byte) (~(i9 >> 8));
                if (b11 == 0) {
                    int i14 = i10 + 1;
                    byte b12 = bArr[i10];
                    if (i14 >= i11) {
                        return incompleteStateFor(b10, b12);
                    }
                    i10 = i14;
                    b11 = b12;
                }
                if (b11 <= -65 && ((b10 != -32 || b11 >= -96) && (b10 != -19 || b11 < -96))) {
                    i13 = i10 + 1;
                }
                return -1;
            }
            byte b13 = (byte) (~(i9 >> 8));
            if (b13 == 0) {
                i12 = i10 + 1;
                b13 = bArr[i10];
                if (i12 >= i11) {
                    return incompleteStateFor(b10, b13);
                }
                b9 = 0;
            } else {
                b9 = (byte) (i9 >> 16);
                i12 = i10;
            }
            if (b9 == 0) {
                int i15 = i12 + 1;
                byte b14 = bArr[i12];
                if (i15 >= i11) {
                    return incompleteStateFor(b10, b13, b14);
                }
                b9 = b14;
                i12 = i15;
            }
            if (b13 <= -65 && (((b10 << Ascii.f15383FS) + (b13 + 112)) >> 30) == 0 && b9 <= -65) {
                i10 = i12 + 1;
            }
            return -1;
            i10 = i13;
        }
        return partialIsValidUtf8(bArr, i10, i11);
    }

    private static int partialIsValidUtf8NonAscii(byte[] bArr, int i9, int i10) {
        while (i9 < i10) {
            int i11 = i9 + 1;
            byte b9 = bArr[i9];
            if (b9 < 0) {
                if (b9 < -32) {
                    if (i11 >= i10) {
                        return b9;
                    }
                    if (b9 >= -62) {
                        i9 = i11 + 1;
                        if (bArr[i11] > -65) {
                        }
                    }
                    return -1;
                }
                if (b9 >= -16) {
                    if (i11 >= i10 - 2) {
                        return incompleteStateFor(bArr, i11, i10);
                    }
                    int i12 = i11 + 1;
                    byte b10 = bArr[i11];
                    if (b10 <= -65 && (((b9 << Ascii.f15383FS) + (b10 + 112)) >> 30) == 0) {
                        int i13 = i12 + 1;
                        if (bArr[i12] <= -65) {
                            i11 = i13 + 1;
                            if (bArr[i13] > -65) {
                            }
                        }
                    }
                    return -1;
                }
                if (i11 >= i10 - 1) {
                    return incompleteStateFor(bArr, i11, i10);
                }
                int i14 = i11 + 1;
                byte b11 = bArr[i11];
                if (b11 <= -65 && ((b9 != -32 || b11 >= -96) && (b9 != -19 || b11 < -96))) {
                    i9 = i14 + 1;
                    if (bArr[i14] > -65) {
                    }
                }
                return -1;
            }
            i9 = i11;
        }
        return 0;
    }

    public static boolean isValidUtf8(byte[] bArr, int i9, int i10) {
        return partialIsValidUtf8(bArr, i9, i10) == 0;
    }

    public static int partialIsValidUtf8(byte[] bArr, int i9, int i10) {
        while (i9 < i10 && bArr[i9] >= 0) {
            i9++;
        }
        if (i9 >= i10) {
            return 0;
        }
        return partialIsValidUtf8NonAscii(bArr, i9, i10);
    }
}
