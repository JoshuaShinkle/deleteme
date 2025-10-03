package org.apache.commons.lang3;

import java.util.UUID;

/* loaded from: classes.dex */
public class Conversion {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    public static char binaryBeMsb0ToHexDigit(boolean[] zArr) {
        return binaryBeMsb0ToHexDigit(zArr, 0);
    }

    public static byte binaryToByte(boolean[] zArr, int i9, byte b9, int i10, int i11) {
        if ((zArr.length == 0 && i9 == 0) || i11 == 0) {
            return b9;
        }
        if ((i11 - 1) + i10 >= 8) {
            throw new IllegalArgumentException("nBools-1+dstPos is greather or equal to than 8");
        }
        for (int i12 = 0; i12 < i11; i12++) {
            int i13 = (i12 * 1) + i10;
            b9 = (byte) ((b9 & (~(1 << i13))) | ((zArr[i12 + i9] ? 1 : 0) << i13));
        }
        return b9;
    }

    public static char binaryToHexDigit(boolean[] zArr) {
        return binaryToHexDigit(zArr, 0);
    }

    public static char binaryToHexDigitMsb0_4bits(boolean[] zArr) {
        return binaryToHexDigitMsb0_4bits(zArr, 0);
    }

    public static int binaryToInt(boolean[] zArr, int i9, int i10, int i11, int i12) {
        if ((zArr.length == 0 && i9 == 0) || i12 == 0) {
            return i10;
        }
        if ((i12 - 1) + i11 >= 32) {
            throw new IllegalArgumentException("nBools-1+dstPos is greather or equal to than 32");
        }
        for (int i13 = 0; i13 < i12; i13++) {
            int i14 = (i13 * 1) + i11;
            i10 = (i10 & (~(1 << i14))) | ((zArr[i13 + i9] ? 1 : 0) << i14);
        }
        return i10;
    }

    public static long binaryToLong(boolean[] zArr, int i9, long j9, int i10, int i11) {
        if ((zArr.length == 0 && i9 == 0) || i11 == 0) {
            return j9;
        }
        if ((i11 - 1) + i10 >= 64) {
            throw new IllegalArgumentException("nBools-1+dstPos is greather or equal to than 64");
        }
        for (int i12 = 0; i12 < i11; i12++) {
            int i13 = (i12 * 1) + i10;
            j9 = (j9 & (~(1 << i13))) | ((zArr[i12 + i9] ? 1L : 0L) << i13);
        }
        return j9;
    }

    public static short binaryToShort(boolean[] zArr, int i9, short s8, int i10, int i11) {
        if ((zArr.length == 0 && i9 == 0) || i11 == 0) {
            return s8;
        }
        if ((i11 - 1) + i10 >= 16) {
            throw new IllegalArgumentException("nBools-1+dstPos is greather or equal to than 16");
        }
        for (int i12 = 0; i12 < i11; i12++) {
            int i13 = (i12 * 1) + i10;
            s8 = (short) ((s8 & (~(1 << i13))) | ((zArr[i12 + i9] ? 1 : 0) << i13));
        }
        return s8;
    }

    public static int byteArrayToInt(byte[] bArr, int i9, int i10, int i11, int i12) {
        if ((bArr.length == 0 && i9 == 0) || i12 == 0) {
            return i10;
        }
        if (((i12 - 1) * 8) + i11 >= 32) {
            throw new IllegalArgumentException("(nBytes-1)*8+dstPos is greather or equal to than 32");
        }
        for (int i13 = 0; i13 < i12; i13++) {
            int i14 = (i13 * 8) + i11;
            i10 = (i10 & (~(255 << i14))) | ((bArr[i13 + i9] & 255) << i14);
        }
        return i10;
    }

    public static long byteArrayToLong(byte[] bArr, int i9, long j9, int i10, int i11) {
        if ((bArr.length == 0 && i9 == 0) || i11 == 0) {
            return j9;
        }
        if (((i11 - 1) * 8) + i10 >= 64) {
            throw new IllegalArgumentException("(nBytes-1)*8+dstPos is greather or equal to than 64");
        }
        for (int i12 = 0; i12 < i11; i12++) {
            int i13 = (i12 * 8) + i10;
            j9 = (j9 & (~(255 << i13))) | ((bArr[i12 + i9] & 255) << i13);
        }
        return j9;
    }

    public static short byteArrayToShort(byte[] bArr, int i9, short s8, int i10, int i11) {
        if ((bArr.length == 0 && i9 == 0) || i11 == 0) {
            return s8;
        }
        if (((i11 - 1) * 8) + i10 >= 16) {
            throw new IllegalArgumentException("(nBytes-1)*8+dstPos is greather or equal to than 16");
        }
        for (int i12 = 0; i12 < i11; i12++) {
            int i13 = (i12 * 8) + i10;
            s8 = (short) ((s8 & (~(255 << i13))) | ((bArr[i12 + i9] & 255) << i13));
        }
        return s8;
    }

    public static UUID byteArrayToUuid(byte[] bArr, int i9) {
        if (bArr.length - i9 >= 16) {
            return new UUID(byteArrayToLong(bArr, i9, 0L, 0, 8), byteArrayToLong(bArr, i9 + 8, 0L, 0, 8));
        }
        throw new IllegalArgumentException("Need at least 16 bytes for UUID");
    }

    public static boolean[] byteToBinary(byte b9, int i9, boolean[] zArr, int i10, int i11) {
        if (i11 == 0) {
            return zArr;
        }
        if ((i11 - 1) + i9 >= 8) {
            throw new IllegalArgumentException("nBools-1+srcPos is greather or equal to than 8");
        }
        for (int i12 = 0; i12 < i11; i12++) {
            int i13 = i10 + i12;
            boolean z8 = true;
            if (((b9 >> ((i12 * 1) + i9)) & 1) == 0) {
                z8 = false;
            }
            zArr[i13] = z8;
        }
        return zArr;
    }

    public static String byteToHex(byte b9, int i9, String str, int i10, int i11) {
        if (i11 == 0) {
            return str;
        }
        if (((i11 - 1) * 4) + i9 >= 8) {
            throw new IllegalArgumentException("(nHexs-1)*4+srcPos is greather or equal to than 8");
        }
        StringBuilder sb = new StringBuilder(str);
        int length = sb.length();
        for (int i12 = 0; i12 < i11; i12++) {
            int i13 = (b9 >> ((i12 * 4) + i9)) & 15;
            int i14 = i10 + i12;
            if (i14 == length) {
                length++;
                sb.append(intToHexDigit(i13));
            } else {
                sb.setCharAt(i14, intToHexDigit(i13));
            }
        }
        return sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean[] hexDigitMsb0ToBinary(char c9) {
        switch (c9) {
            case '0':
                return new boolean[]{false, false, false, false};
            case '1':
                return new boolean[]{false, false, false, true};
            case '2':
                return new boolean[]{false, false, true, false};
            case '3':
                return new boolean[]{false, false, true, true};
            case '4':
                return new boolean[]{false, true, false, false};
            case '5':
                return new boolean[]{false, true, false, true};
            case '6':
                return new boolean[]{false, true, true, false};
            case '7':
                return new boolean[]{false, true, true, true};
            case '8':
                return new boolean[]{true, false, false, false};
            case '9':
                return new boolean[]{true, false, false, true};
            default:
                switch (c9) {
                    case 'A':
                        return new boolean[]{true, false, true, false};
                    case 'B':
                        return new boolean[]{true, false, true, true};
                    case 'C':
                        return new boolean[]{true, true, false, false};
                    case 'D':
                        return new boolean[]{true, true, false, true};
                    case 'E':
                        return new boolean[]{true, true, true, false};
                    case 'F':
                        return new boolean[]{true, true, true, true};
                    default:
                        switch (c9) {
                            case 'a':
                                break;
                            case 'b':
                                break;
                            case 'c':
                                break;
                            case 'd':
                                break;
                            case 'e':
                                break;
                            case 'f':
                                break;
                            default:
                                throw new IllegalArgumentException("Cannot interpret '" + c9 + "' as a hexadecimal digit");
                        }
                }
        }
    }

    public static int hexDigitMsb0ToInt(char c9) {
        switch (c9) {
            case '0':
                return 0;
            case '1':
                return 8;
            case '2':
                return 4;
            case '3':
                return 12;
            case '4':
                return 2;
            case '5':
                return 10;
            case '6':
                return 6;
            case '7':
                return 14;
            case '8':
                return 1;
            case '9':
                return 9;
            default:
                switch (c9) {
                    case 'A':
                        return 5;
                    case 'B':
                        return 13;
                    case 'C':
                        return 3;
                    case 'D':
                        return 11;
                    case 'E':
                        return 7;
                    case 'F':
                        return 15;
                    default:
                        switch (c9) {
                            case 'a':
                                return 5;
                            case 'b':
                                return 13;
                            case 'c':
                                return 3;
                            case 'd':
                                return 11;
                            case 'e':
                                return 7;
                            case 'f':
                                return 15;
                            default:
                                throw new IllegalArgumentException("Cannot interpret '" + c9 + "' as a hexadecimal digit");
                        }
                }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean[] hexDigitToBinary(char c9) {
        switch (c9) {
            case '0':
                return new boolean[]{false, false, false, false};
            case '1':
                return new boolean[]{true, false, false, false};
            case '2':
                return new boolean[]{false, true, false, false};
            case '3':
                return new boolean[]{true, true, false, false};
            case '4':
                return new boolean[]{false, false, true, false};
            case '5':
                return new boolean[]{true, false, true, false};
            case '6':
                return new boolean[]{false, true, true, false};
            case '7':
                return new boolean[]{true, true, true, false};
            case '8':
                return new boolean[]{false, false, false, true};
            case '9':
                return new boolean[]{true, false, false, true};
            default:
                switch (c9) {
                    case 'A':
                        return new boolean[]{false, true, false, true};
                    case 'B':
                        return new boolean[]{true, true, false, true};
                    case 'C':
                        return new boolean[]{false, false, true, true};
                    case 'D':
                        return new boolean[]{true, false, true, true};
                    case 'E':
                        return new boolean[]{false, true, true, true};
                    case 'F':
                        return new boolean[]{true, true, true, true};
                    default:
                        switch (c9) {
                            case 'a':
                                break;
                            case 'b':
                                break;
                            case 'c':
                                break;
                            case 'd':
                                break;
                            case 'e':
                                break;
                            case 'f':
                                break;
                            default:
                                throw new IllegalArgumentException("Cannot interpret '" + c9 + "' as a hexadecimal digit");
                        }
                }
        }
    }

    public static int hexDigitToInt(char c9) {
        int iDigit = Character.digit(c9, 16);
        if (iDigit >= 0) {
            return iDigit;
        }
        throw new IllegalArgumentException("Cannot interpret '" + c9 + "' as a hexadecimal digit");
    }

    public static byte hexToByte(String str, int i9, byte b9, int i10, int i11) {
        if (i11 == 0) {
            return b9;
        }
        if (((i11 - 1) * 4) + i10 >= 8) {
            throw new IllegalArgumentException("(nHexs-1)*4+dstPos is greather or equal to than 8");
        }
        for (int i12 = 0; i12 < i11; i12++) {
            int i13 = (i12 * 4) + i10;
            b9 = (byte) ((b9 & (~(15 << i13))) | ((hexDigitToInt(str.charAt(i12 + i9)) & 15) << i13));
        }
        return b9;
    }

    public static int hexToInt(String str, int i9, int i10, int i11, int i12) {
        if (i12 == 0) {
            return i10;
        }
        if (((i12 - 1) * 4) + i11 >= 32) {
            throw new IllegalArgumentException("(nHexs-1)*4+dstPos is greather or equal to than 32");
        }
        for (int i13 = 0; i13 < i12; i13++) {
            int i14 = (i13 * 4) + i11;
            i10 = (i10 & (~(15 << i14))) | ((hexDigitToInt(str.charAt(i13 + i9)) & 15) << i14);
        }
        return i10;
    }

    public static long hexToLong(String str, int i9, long j9, int i10, int i11) {
        if (i11 == 0) {
            return j9;
        }
        if (((i11 - 1) * 4) + i10 >= 64) {
            throw new IllegalArgumentException("(nHexs-1)*4+dstPos is greather or equal to than 64");
        }
        for (int i12 = 0; i12 < i11; i12++) {
            int i13 = (i12 * 4) + i10;
            j9 = (j9 & (~(15 << i13))) | ((hexDigitToInt(str.charAt(i12 + i9)) & 15) << i13);
        }
        return j9;
    }

    public static short hexToShort(String str, int i9, short s8, int i10, int i11) {
        if (i11 == 0) {
            return s8;
        }
        if (((i11 - 1) * 4) + i10 >= 16) {
            throw new IllegalArgumentException("(nHexs-1)*4+dstPos is greather or equal to than 16");
        }
        for (int i12 = 0; i12 < i11; i12++) {
            int i13 = (i12 * 4) + i10;
            s8 = (short) ((s8 & (~(15 << i13))) | ((hexDigitToInt(str.charAt(i12 + i9)) & 15) << i13));
        }
        return s8;
    }

    public static long intArrayToLong(int[] iArr, int i9, long j9, int i10, int i11) {
        if ((iArr.length == 0 && i9 == 0) || i11 == 0) {
            return j9;
        }
        if (((i11 - 1) * 32) + i10 >= 64) {
            throw new IllegalArgumentException("(nInts-1)*32+dstPos is greather or equal to than 64");
        }
        for (int i12 = 0; i12 < i11; i12++) {
            int i13 = (i12 * 32) + i10;
            j9 = (j9 & (~(4294967295 << i13))) | ((iArr[i12 + i9] & 4294967295L) << i13);
        }
        return j9;
    }

    public static boolean[] intToBinary(int i9, int i10, boolean[] zArr, int i11, int i12) {
        if (i12 == 0) {
            return zArr;
        }
        if ((i12 - 1) + i10 >= 32) {
            throw new IllegalArgumentException("nBools-1+srcPos is greather or equal to than 32");
        }
        for (int i13 = 0; i13 < i12; i13++) {
            int i14 = i11 + i13;
            boolean z8 = true;
            if (((i9 >> ((i13 * 1) + i10)) & 1) == 0) {
                z8 = false;
            }
            zArr[i14] = z8;
        }
        return zArr;
    }

    public static byte[] intToByteArray(int i9, int i10, byte[] bArr, int i11, int i12) {
        if (i12 == 0) {
            return bArr;
        }
        if (((i12 - 1) * 8) + i10 >= 32) {
            throw new IllegalArgumentException("(nBytes-1)*8+srcPos is greather or equal to than 32");
        }
        for (int i13 = 0; i13 < i12; i13++) {
            bArr[i11 + i13] = (byte) ((i9 >> ((i13 * 8) + i10)) & 255);
        }
        return bArr;
    }

    public static String intToHex(int i9, int i10, String str, int i11, int i12) {
        if (i12 == 0) {
            return str;
        }
        if (((i12 - 1) * 4) + i10 >= 32) {
            throw new IllegalArgumentException("(nHexs-1)*4+srcPos is greather or equal to than 32");
        }
        StringBuilder sb = new StringBuilder(str);
        int length = sb.length();
        for (int i13 = 0; i13 < i12; i13++) {
            int i14 = (i9 >> ((i13 * 4) + i10)) & 15;
            int i15 = i11 + i13;
            if (i15 == length) {
                length++;
                sb.append(intToHexDigit(i14));
            } else {
                sb.setCharAt(i15, intToHexDigit(i14));
            }
        }
        return sb.toString();
    }

    public static char intToHexDigit(int i9) {
        char cForDigit = Character.forDigit(i9, 16);
        if (cForDigit != 0) {
            return cForDigit;
        }
        throw new IllegalArgumentException("nibble value not between 0 and 15: " + i9);
    }

    public static char intToHexDigitMsb0(int i9) {
        switch (i9) {
            case 0:
                return '0';
            case 1:
                return '8';
            case 2:
                return '4';
            case 3:
                return 'c';
            case 4:
                return '2';
            case 5:
                return 'a';
            case 6:
                return '6';
            case 7:
                return 'e';
            case 8:
                return '1';
            case 9:
                return '9';
            case 10:
                return '5';
            case 11:
                return 'd';
            case 12:
                return '3';
            case 13:
                return 'b';
            case 14:
                return '7';
            case 15:
                return 'f';
            default:
                throw new IllegalArgumentException("nibble value not between 0 and 15: " + i9);
        }
    }

    public static short[] intToShortArray(int i9, int i10, short[] sArr, int i11, int i12) {
        if (i12 == 0) {
            return sArr;
        }
        if (((i12 - 1) * 16) + i10 >= 32) {
            throw new IllegalArgumentException("(nShorts-1)*16+srcPos is greather or equal to than 32");
        }
        for (int i13 = 0; i13 < i12; i13++) {
            sArr[i11 + i13] = (short) ((i9 >> ((i13 * 16) + i10)) & 65535);
        }
        return sArr;
    }

    public static boolean[] longToBinary(long j9, int i9, boolean[] zArr, int i10, int i11) {
        if (i11 == 0) {
            return zArr;
        }
        if ((i11 - 1) + i9 >= 64) {
            throw new IllegalArgumentException("nBools-1+srcPos is greather or equal to than 64");
        }
        for (int i12 = 0; i12 < i11; i12++) {
            zArr[i10 + i12] = (1 & (j9 >> ((i12 * 1) + i9))) != 0;
        }
        return zArr;
    }

    public static byte[] longToByteArray(long j9, int i9, byte[] bArr, int i10, int i11) {
        if (i11 == 0) {
            return bArr;
        }
        if (((i11 - 1) * 8) + i9 >= 64) {
            throw new IllegalArgumentException("(nBytes-1)*8+srcPos is greather or equal to than 64");
        }
        for (int i12 = 0; i12 < i11; i12++) {
            bArr[i10 + i12] = (byte) (255 & (j9 >> ((i12 * 8) + i9)));
        }
        return bArr;
    }

    public static String longToHex(long j9, int i9, String str, int i10, int i11) {
        if (i11 == 0) {
            return str;
        }
        if (((i11 - 1) * 4) + i9 >= 64) {
            throw new IllegalArgumentException("(nHexs-1)*4+srcPos is greather or equal to than 64");
        }
        StringBuilder sb = new StringBuilder(str);
        int length = sb.length();
        for (int i12 = 0; i12 < i11; i12++) {
            int i13 = (int) ((j9 >> ((i12 * 4) + i9)) & 15);
            int i14 = i10 + i12;
            if (i14 == length) {
                length++;
                sb.append(intToHexDigit(i13));
            } else {
                sb.setCharAt(i14, intToHexDigit(i13));
            }
        }
        return sb.toString();
    }

    public static int[] longToIntArray(long j9, int i9, int[] iArr, int i10, int i11) {
        if (i11 == 0) {
            return iArr;
        }
        if (((i11 - 1) * 32) + i9 >= 64) {
            throw new IllegalArgumentException("(nInts-1)*32+srcPos is greather or equal to than 64");
        }
        for (int i12 = 0; i12 < i11; i12++) {
            iArr[i10 + i12] = (int) ((-1) & (j9 >> ((i12 * 32) + i9)));
        }
        return iArr;
    }

    public static short[] longToShortArray(long j9, int i9, short[] sArr, int i10, int i11) {
        if (i11 == 0) {
            return sArr;
        }
        if (((i11 - 1) * 16) + i9 >= 64) {
            throw new IllegalArgumentException("(nShorts-1)*16+srcPos is greather or equal to than 64");
        }
        for (int i12 = 0; i12 < i11; i12++) {
            sArr[i10 + i12] = (short) (65535 & (j9 >> ((i12 * 16) + i9)));
        }
        return sArr;
    }

    public static int shortArrayToInt(short[] sArr, int i9, int i10, int i11, int i12) {
        if ((sArr.length == 0 && i9 == 0) || i12 == 0) {
            return i10;
        }
        if (((i12 - 1) * 16) + i11 >= 32) {
            throw new IllegalArgumentException("(nShorts-1)*16+dstPos is greather or equal to than 32");
        }
        for (int i13 = 0; i13 < i12; i13++) {
            int i14 = (i13 * 16) + i11;
            i10 = (i10 & (~(65535 << i14))) | ((sArr[i13 + i9] & 65535) << i14);
        }
        return i10;
    }

    public static long shortArrayToLong(short[] sArr, int i9, long j9, int i10, int i11) {
        if ((sArr.length == 0 && i9 == 0) || i11 == 0) {
            return j9;
        }
        if (((i11 - 1) * 16) + i10 >= 64) {
            throw new IllegalArgumentException("(nShorts-1)*16+dstPos is greather or equal to than 64");
        }
        for (int i12 = 0; i12 < i11; i12++) {
            int i13 = (i12 * 16) + i10;
            j9 = (j9 & (~(65535 << i13))) | ((sArr[i12 + i9] & 65535) << i13);
        }
        return j9;
    }

    public static boolean[] shortToBinary(short s8, int i9, boolean[] zArr, int i10, int i11) {
        if (i11 == 0) {
            return zArr;
        }
        if ((i11 - 1) + i9 >= 16) {
            throw new IllegalArgumentException("nBools-1+srcPos is greather or equal to than 16");
        }
        for (int i12 = 0; i12 < i11; i12++) {
            int i13 = i10 + i12;
            boolean z8 = true;
            if (((s8 >> ((i12 * 1) + i9)) & 1) == 0) {
                z8 = false;
            }
            zArr[i13] = z8;
        }
        return zArr;
    }

    public static byte[] shortToByteArray(short s8, int i9, byte[] bArr, int i10, int i11) {
        if (i11 == 0) {
            return bArr;
        }
        if (((i11 - 1) * 8) + i9 >= 16) {
            throw new IllegalArgumentException("(nBytes-1)*8+srcPos is greather or equal to than 16");
        }
        for (int i12 = 0; i12 < i11; i12++) {
            bArr[i10 + i12] = (byte) ((s8 >> ((i12 * 8) + i9)) & 255);
        }
        return bArr;
    }

    public static String shortToHex(short s8, int i9, String str, int i10, int i11) {
        if (i11 == 0) {
            return str;
        }
        if (((i11 - 1) * 4) + i9 >= 16) {
            throw new IllegalArgumentException("(nHexs-1)*4+srcPos is greather or equal to than 16");
        }
        StringBuilder sb = new StringBuilder(str);
        int length = sb.length();
        for (int i12 = 0; i12 < i11; i12++) {
            int i13 = (s8 >> ((i12 * 4) + i9)) & 15;
            int i14 = i10 + i12;
            if (i14 == length) {
                length++;
                sb.append(intToHexDigit(i13));
            } else {
                sb.setCharAt(i14, intToHexDigit(i13));
            }
        }
        return sb.toString();
    }

    public static byte[] uuidToByteArray(UUID uuid, byte[] bArr, int i9, int i10) {
        if (i10 == 0) {
            return bArr;
        }
        if (i10 > 16) {
            throw new IllegalArgumentException("nBytes is greather than 16");
        }
        longToByteArray(uuid.getMostSignificantBits(), 0, bArr, i9, i10 > 8 ? 8 : i10);
        if (i10 >= 8) {
            longToByteArray(uuid.getLeastSignificantBits(), 0, bArr, i9 + 8, i10 - 8);
        }
        return bArr;
    }

    public static char binaryBeMsb0ToHexDigit(boolean[] zArr, int i9) {
        if (zArr.length == 0) {
            throw new IllegalArgumentException("Cannot convert an empty array.");
        }
        int length = ((zArr.length - 1) - i9) + 1;
        int iMin = Math.min(4, length);
        boolean[] zArr2 = new boolean[4];
        System.arraycopy(zArr, length - iMin, zArr2, 4 - iMin, iMin);
        return zArr2[0] ? zArr2[1] ? zArr2[2] ? zArr2[3] ? 'f' : 'e' : zArr2[3] ? 'd' : 'c' : zArr2[2] ? zArr2[3] ? 'b' : 'a' : zArr2[3] ? '9' : '8' : zArr2[1] ? zArr2[2] ? zArr2[3] ? '7' : '6' : zArr2[3] ? '5' : '4' : zArr2[2] ? zArr2[3] ? '3' : '2' : zArr2[3] ? '1' : '0';
    }

    public static char binaryToHexDigit(boolean[] zArr, int i9) {
        if (zArr.length == 0) {
            throw new IllegalArgumentException("Cannot convert an empty array.");
        }
        int i10 = i9 + 3;
        if (zArr.length <= i10 || !zArr[i10]) {
            int i11 = i9 + 2;
            if (zArr.length <= i11 || !zArr[i11]) {
                int i12 = i9 + 1;
                return (zArr.length <= i12 || !zArr[i12]) ? zArr[i9] ? '1' : '0' : zArr[i9] ? '3' : '2';
            }
            int i13 = i9 + 1;
            return (zArr.length <= i13 || !zArr[i13]) ? zArr[i9] ? '5' : '4' : zArr[i9] ? '7' : '6';
        }
        int i14 = i9 + 2;
        if (zArr.length <= i14 || !zArr[i14]) {
            int i15 = i9 + 1;
            return (zArr.length <= i15 || !zArr[i15]) ? zArr[i9] ? '9' : '8' : zArr[i9] ? 'b' : 'a';
        }
        int i16 = i9 + 1;
        return (zArr.length <= i16 || !zArr[i16]) ? zArr[i9] ? 'd' : 'c' : zArr[i9] ? 'f' : 'e';
    }

    public static char binaryToHexDigitMsb0_4bits(boolean[] zArr, int i9) {
        if (zArr.length > 8) {
            throw new IllegalArgumentException("src.length>8: src.length=" + zArr.length);
        }
        if (zArr.length - i9 >= 4) {
            return zArr[i9 + 3] ? zArr[i9 + 2] ? zArr[i9 + 1] ? zArr[i9] ? 'f' : '7' : zArr[i9] ? 'b' : '3' : zArr[i9 + 1] ? zArr[i9] ? 'd' : '5' : zArr[i9] ? '9' : '1' : zArr[i9 + 2] ? zArr[i9 + 1] ? zArr[i9] ? 'e' : '6' : zArr[i9] ? 'a' : '2' : zArr[i9 + 1] ? zArr[i9] ? 'c' : '4' : zArr[i9] ? '8' : '0';
        }
        throw new IllegalArgumentException("src.length-srcPos<4: src.length=" + zArr.length + ", srcPos=" + i9);
    }
}
