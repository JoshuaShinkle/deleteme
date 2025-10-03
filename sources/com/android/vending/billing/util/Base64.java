package com.android.vending.billing.util;

import com.google.common.base.Ascii;

/* loaded from: classes.dex */
public class Base64 {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final boolean DECODE = false;
    public static final boolean ENCODE = true;
    private static final byte EQUALS_SIGN_ENC = -1;
    private static final byte NEW_LINE = 10;
    private static final byte[] ALPHABET = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
    private static final byte[] WEBSAFE_ALPHABET = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};
    private static final byte WHITE_SPACE_ENC = -5;
    private static final byte EQUALS_SIGN = 61;
    private static final byte[] DECODABET = {-9, -9, -9, -9, -9, -9, -9, -9, -9, WHITE_SPACE_ENC, WHITE_SPACE_ENC, -9, -9, WHITE_SPACE_ENC, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, WHITE_SPACE_ENC, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, -9, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, EQUALS_SIGN, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, Ascii.f15393VT, Ascii.f15382FF, Ascii.f15380CR, Ascii.f15390SO, Ascii.f15389SI, Ascii.DLE, 17, Ascii.DC2, 19, Ascii.DC4, Ascii.NAK, Ascii.SYN, Ascii.ETB, Ascii.CAN, Ascii.f15381EM, -9, -9, -9, -9, -9, -9, Ascii.SUB, Ascii.ESC, Ascii.f15383FS, Ascii.f15384GS, Ascii.f15388RS, Ascii.f15392US, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -9, -9, -9, -9, -9};
    private static final byte[] WEBSAFE_DECODABET = {-9, -9, -9, -9, -9, -9, -9, -9, -9, WHITE_SPACE_ENC, WHITE_SPACE_ENC, -9, -9, WHITE_SPACE_ENC, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, WHITE_SPACE_ENC, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, 52, 53, 54, 55, 56, 57, 58, 59, 60, EQUALS_SIGN, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, Ascii.f15393VT, Ascii.f15382FF, Ascii.f15380CR, Ascii.f15390SO, Ascii.f15389SI, Ascii.DLE, 17, Ascii.DC2, 19, Ascii.DC4, Ascii.NAK, Ascii.SYN, Ascii.ETB, Ascii.CAN, Ascii.f15381EM, -9, -9, -9, -9, 63, -9, Ascii.SUB, Ascii.ESC, Ascii.f15383FS, Ascii.f15384GS, Ascii.f15388RS, Ascii.f15392US, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -9, -9, -9, -9, -9};

    private Base64() {
    }

    public static byte[] decode(String str) {
        byte[] bytes = str.getBytes();
        return decode(bytes, 0, bytes.length);
    }

    private static int decode4to3(byte[] bArr, int i9, byte[] bArr2, int i10, byte[] bArr3) {
        byte b9 = bArr[i9 + 2];
        if (b9 == 61) {
            bArr2[i10] = (byte) ((((bArr3[bArr[i9 + 1]] << Ascii.CAN) >>> 12) | ((bArr3[bArr[i9]] << Ascii.CAN) >>> 6)) >>> 16);
            return 1;
        }
        byte b10 = bArr[i9 + 3];
        if (b10 == 61) {
            int i11 = ((bArr3[bArr[i9 + 1]] << Ascii.CAN) >>> 12) | ((bArr3[bArr[i9]] << Ascii.CAN) >>> 6) | ((bArr3[b9] << Ascii.CAN) >>> 18);
            bArr2[i10] = (byte) (i11 >>> 16);
            bArr2[i10 + 1] = (byte) (i11 >>> 8);
            return 2;
        }
        int i12 = ((bArr3[bArr[i9 + 1]] << Ascii.CAN) >>> 12) | ((bArr3[bArr[i9]] << Ascii.CAN) >>> 6) | ((bArr3[b9] << Ascii.CAN) >>> 18) | ((bArr3[b10] << Ascii.CAN) >>> 24);
        bArr2[i10] = (byte) (i12 >> 16);
        bArr2[i10 + 1] = (byte) (i12 >> 8);
        bArr2[i10 + 2] = (byte) i12;
        return 3;
    }

    public static byte[] decodeWebSafe(String str) {
        byte[] bytes = str.getBytes();
        return decodeWebSafe(bytes, 0, bytes.length);
    }

    public static String encode(byte[] bArr) {
        return encode(bArr, 0, bArr.length, ALPHABET, true);
    }

    private static byte[] encode3to4(byte[] bArr, int i9, int i10, byte[] bArr2, int i11, byte[] bArr3) {
        int i12 = (i10 > 0 ? (bArr[i9] << Ascii.CAN) >>> 8 : 0) | (i10 > 1 ? (bArr[i9 + 1] << Ascii.CAN) >>> 16 : 0) | (i10 > 2 ? (bArr[i9 + 2] << Ascii.CAN) >>> 24 : 0);
        if (i10 == 1) {
            bArr2[i11] = bArr3[i12 >>> 18];
            bArr2[i11 + 1] = bArr3[(i12 >>> 12) & 63];
            bArr2[i11 + 2] = EQUALS_SIGN;
            bArr2[i11 + 3] = EQUALS_SIGN;
            return bArr2;
        }
        if (i10 == 2) {
            bArr2[i11] = bArr3[i12 >>> 18];
            bArr2[i11 + 1] = bArr3[(i12 >>> 12) & 63];
            bArr2[i11 + 2] = bArr3[(i12 >>> 6) & 63];
            bArr2[i11 + 3] = EQUALS_SIGN;
            return bArr2;
        }
        if (i10 != 3) {
            return bArr2;
        }
        bArr2[i11] = bArr3[i12 >>> 18];
        bArr2[i11 + 1] = bArr3[(i12 >>> 12) & 63];
        bArr2[i11 + 2] = bArr3[(i12 >>> 6) & 63];
        bArr2[i11 + 3] = bArr3[i12 & 63];
        return bArr2;
    }

    public static String encodeWebSafe(byte[] bArr, boolean z8) {
        return encode(bArr, 0, bArr.length, WEBSAFE_ALPHABET, z8);
    }

    public static String encode(byte[] bArr, int i9, int i10, byte[] bArr2, boolean z8) {
        byte[] bArrEncode = encode(bArr, i9, i10, bArr2, Integer.MAX_VALUE);
        int length = bArrEncode.length;
        while (!z8 && length > 0 && bArrEncode[length - 1] == 61) {
            length--;
        }
        return new String(bArrEncode, 0, length);
    }

    public static byte[] decode(byte[] bArr) {
        return decode(bArr, 0, bArr.length);
    }

    public static byte[] decodeWebSafe(byte[] bArr) {
        return decodeWebSafe(bArr, 0, bArr.length);
    }

    public static byte[] decode(byte[] bArr, int i9, int i10) {
        return decode(bArr, i9, i10, DECODABET);
    }

    public static byte[] decodeWebSafe(byte[] bArr, int i9, int i10) {
        return decode(bArr, i9, i10, WEBSAFE_DECODABET);
    }

    public static byte[] decode(byte[] bArr, int i9, int i10, byte[] bArr2) throws Base64DecoderException {
        byte[] bArr3 = new byte[((i10 * 3) / 4) + 2];
        byte[] bArr4 = new byte[4];
        int i11 = 0;
        int i12 = 0;
        int iDecode4to3 = 0;
        while (true) {
            if (i11 >= i10) {
                break;
            }
            int i13 = i11 + i9;
            byte b9 = (byte) (bArr[i13] & Ascii.DEL);
            byte b10 = bArr2[b9];
            if (b10 < -5) {
                throw new Base64DecoderException("Bad Base64 input character at " + i11 + ": " + ((int) bArr[i13]) + "(decimal)");
            }
            if (b10 >= -1) {
                if (b9 == 61) {
                    int i14 = i10 - i11;
                    byte b11 = (byte) (bArr[(i10 - 1) + i9] & Ascii.DEL);
                    if (i12 == 0 || i12 == 1) {
                        throw new Base64DecoderException("invalid padding byte '=' at byte offset " + i11);
                    }
                    if ((i12 == 3 && i14 > 2) || (i12 == 4 && i14 > 1)) {
                        throw new Base64DecoderException("padding byte '=' falsely signals end of encoded value at offset " + i11);
                    }
                    if (b11 != 61 && b11 != 10) {
                        throw new Base64DecoderException("encoded value has invalid trailing byte");
                    }
                } else {
                    int i15 = i12 + 1;
                    bArr4[i12] = b9;
                    if (i15 == 4) {
                        iDecode4to3 += decode4to3(bArr4, 0, bArr3, iDecode4to3, bArr2);
                        i12 = 0;
                    } else {
                        i12 = i15;
                    }
                }
            }
            i11++;
        }
        if (i12 != 0) {
            if (i12 != 1) {
                bArr4[i12] = EQUALS_SIGN;
                iDecode4to3 += decode4to3(bArr4, 0, bArr3, iDecode4to3, bArr2);
            } else {
                throw new Base64DecoderException("single trailing character at offset " + (i10 - 1));
            }
        }
        byte[] bArr5 = new byte[iDecode4to3];
        System.arraycopy(bArr3, 0, bArr5, 0, iDecode4to3);
        return bArr5;
    }

    public static byte[] encode(byte[] bArr, int i9, int i10, byte[] bArr2, int i11) {
        int i12 = ((i10 + 2) / 3) * 4;
        byte[] bArr3 = new byte[i12 + (i12 / i11)];
        int i13 = i10 - 2;
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        while (i14 < i13) {
            int i17 = ((bArr[i14 + i9] << Ascii.CAN) >>> 8) | ((bArr[(i14 + 1) + i9] << Ascii.CAN) >>> 16) | ((bArr[(i14 + 2) + i9] << Ascii.CAN) >>> 24);
            bArr3[i15] = bArr2[i17 >>> 18];
            int i18 = i15 + 1;
            bArr3[i18] = bArr2[(i17 >>> 12) & 63];
            bArr3[i15 + 2] = bArr2[(i17 >>> 6) & 63];
            bArr3[i15 + 3] = bArr2[i17 & 63];
            i16 += 4;
            if (i16 == i11) {
                bArr3[i15 + 4] = 10;
                i16 = 0;
                i15 = i18;
            }
            i14 += 3;
            i15 += 4;
        }
        if (i14 < i10) {
            encode3to4(bArr, i14 + i9, i10 - i14, bArr3, i15, bArr2);
            if (i16 + 4 == i11) {
                bArr3[i15 + 4] = 10;
            }
        }
        return bArr3;
    }
}
