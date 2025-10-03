package com.google.zxing.qrcode.encoder;

/* loaded from: classes2.dex */
final class MaskUtil {

    /* renamed from: N1 */
    private static final int f15656N1 = 3;

    /* renamed from: N2 */
    private static final int f15657N2 = 3;

    /* renamed from: N3 */
    private static final int f15658N3 = 40;

    /* renamed from: N4 */
    private static final int f15659N4 = 10;

    private MaskUtil() {
    }

    public static int applyMaskPenaltyRule1(ByteMatrix byteMatrix) {
        return applyMaskPenaltyRule1Internal(byteMatrix, true) + applyMaskPenaltyRule1Internal(byteMatrix, false);
    }

    private static int applyMaskPenaltyRule1Internal(ByteMatrix byteMatrix, boolean z8) {
        int height = z8 ? byteMatrix.getHeight() : byteMatrix.getWidth();
        int width = z8 ? byteMatrix.getWidth() : byteMatrix.getHeight();
        byte[][] array = byteMatrix.getArray();
        int i9 = 0;
        for (int i10 = 0; i10 < height; i10++) {
            byte b9 = -1;
            int i11 = 0;
            for (int i12 = 0; i12 < width; i12++) {
                byte b10 = z8 ? array[i10][i12] : array[i12][i10];
                if (b10 == b9) {
                    i11++;
                } else {
                    if (i11 >= 5) {
                        i9 += (i11 - 5) + 3;
                    }
                    i11 = 1;
                    b9 = b10;
                }
            }
            if (i11 >= 5) {
                i9 += (i11 - 5) + 3;
            }
        }
        return i9;
    }

    public static int applyMaskPenaltyRule2(ByteMatrix byteMatrix) {
        byte[][] array = byteMatrix.getArray();
        int width = byteMatrix.getWidth();
        int height = byteMatrix.getHeight();
        int i9 = 0;
        for (int i10 = 0; i10 < height - 1; i10++) {
            int i11 = 0;
            while (i11 < width - 1) {
                byte[] bArr = array[i10];
                byte b9 = bArr[i11];
                int i12 = i11 + 1;
                if (b9 == bArr[i12]) {
                    byte[] bArr2 = array[i10 + 1];
                    if (b9 == bArr2[i11] && b9 == bArr2[i12]) {
                        i9++;
                    }
                }
                i11 = i12;
            }
        }
        return i9 * 3;
    }

    public static int applyMaskPenaltyRule3(ByteMatrix byteMatrix) {
        byte[][] array = byteMatrix.getArray();
        int width = byteMatrix.getWidth();
        int height = byteMatrix.getHeight();
        int i9 = 0;
        for (int i10 = 0; i10 < height; i10++) {
            for (int i11 = 0; i11 < width; i11++) {
                byte[] bArr = array[i10];
                int i12 = i11 + 6;
                if (i12 < width && bArr[i11] == 1 && bArr[i11 + 1] == 0 && bArr[i11 + 2] == 1 && bArr[i11 + 3] == 1 && bArr[i11 + 4] == 1 && bArr[i11 + 5] == 0 && bArr[i12] == 1 && (isWhiteHorizontal(bArr, i11 - 4, i11) || isWhiteHorizontal(bArr, i11 + 7, i11 + 11))) {
                    i9++;
                }
                int i13 = i10 + 6;
                if (i13 < height && array[i10][i11] == 1 && array[i10 + 1][i11] == 0 && array[i10 + 2][i11] == 1 && array[i10 + 3][i11] == 1 && array[i10 + 4][i11] == 1 && array[i10 + 5][i11] == 0 && array[i13][i11] == 1 && (isWhiteVertical(array, i11, i10 - 4, i10) || isWhiteVertical(array, i11, i10 + 7, i10 + 11))) {
                    i9++;
                }
            }
        }
        return i9 * 40;
    }

    public static int applyMaskPenaltyRule4(ByteMatrix byteMatrix) {
        byte[][] array = byteMatrix.getArray();
        int width = byteMatrix.getWidth();
        int height = byteMatrix.getHeight();
        int i9 = 0;
        for (int i10 = 0; i10 < height; i10++) {
            byte[] bArr = array[i10];
            for (int i11 = 0; i11 < width; i11++) {
                if (bArr[i11] == 1) {
                    i9++;
                }
            }
        }
        int height2 = byteMatrix.getHeight() * byteMatrix.getWidth();
        return ((Math.abs((i9 * 2) - height2) * 10) / height2) * 10;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0045 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean getDataMaskBit(int i9, int i10, int i11) {
        int i12;
        int i13;
        switch (i9) {
            case 0:
                i11 += i10;
                i12 = i11 & 1;
                return i12 != 0;
            case 1:
                i12 = i11 & 1;
                if (i12 != 0) {
                }
                break;
            case 2:
                i12 = i10 % 3;
                if (i12 != 0) {
                }
                break;
            case 3:
                i12 = (i11 + i10) % 3;
                if (i12 != 0) {
                }
                break;
            case 4:
                i11 /= 2;
                i10 /= 3;
                i11 += i10;
                i12 = i11 & 1;
                if (i12 != 0) {
                }
                break;
            case 5:
                int i14 = i11 * i10;
                i12 = (i14 & 1) + (i14 % 3);
                if (i12 != 0) {
                }
                break;
            case 6:
                int i15 = i11 * i10;
                i13 = (i15 & 1) + (i15 % 3);
                i12 = i13 & 1;
                if (i12 != 0) {
                }
                break;
            case 7:
                i13 = ((i11 * i10) % 3) + ((i11 + i10) & 1);
                i12 = i13 & 1;
                if (i12 != 0) {
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid mask pattern: " + i9);
        }
    }

    private static boolean isWhiteHorizontal(byte[] bArr, int i9, int i10) {
        while (i9 < i10) {
            if (i9 >= 0 && i9 < bArr.length && bArr[i9] == 1) {
                return false;
            }
            i9++;
        }
        return true;
    }

    private static boolean isWhiteVertical(byte[][] bArr, int i9, int i10, int i11) {
        while (i10 < i11) {
            if (i10 >= 0 && i10 < bArr.length && bArr[i10][i9] == 1) {
                return false;
            }
            i10++;
        }
        return true;
    }
}
