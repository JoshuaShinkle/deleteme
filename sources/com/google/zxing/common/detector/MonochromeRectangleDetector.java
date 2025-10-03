package com.google.zxing.common.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;

/* loaded from: classes2.dex */
public final class MonochromeRectangleDetector {
    private static final int MAX_MODULES = 32;
    private final BitMatrix image;

    public MonochromeRectangleDetector(BitMatrix bitMatrix) {
        this.image = bitMatrix;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0020  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0031 A[EDGE_INSN: B:69:0x0031->B:22:0x0031 BREAK  A[LOOP:1: B:13:0x001c->B:72:0x001c], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0067 A[EDGE_INSN: B:85:0x0067->B:47:0x0067 BREAK  A[LOOP:3: B:38:0x0053->B:90:0x0053], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private int[] blackWhiteRange(int i9, int i10, int i11, int i12, boolean z8) {
        int i13;
        int i14;
        int i15 = (i11 + i12) / 2;
        int i16 = i15;
        while (i16 >= i11) {
            BitMatrix bitMatrix = this.image;
            if (!z8) {
                if (!bitMatrix.get(i9, i16)) {
                    i14 = i16;
                    while (true) {
                        i14--;
                        if (i14 >= i11) {
                        }
                    }
                    int i17 = i16 - i14;
                    if (i14 < i11) {
                        break;
                    }
                    break;
                    break;
                }
                i16--;
            } else if (bitMatrix.get(i16, i9)) {
                i16--;
            } else {
                i14 = i16;
                while (true) {
                    i14--;
                    if (i14 >= i11) {
                        break;
                    }
                    BitMatrix bitMatrix2 = this.image;
                    if (z8) {
                        if (bitMatrix2.get(i14, i9)) {
                            break;
                        }
                    } else if (bitMatrix2.get(i9, i14)) {
                        break;
                    }
                }
                int i172 = i16 - i14;
                if (i14 < i11 || i172 > i10) {
                    break;
                }
                i16 = i14;
            }
        }
        int i18 = i16 + 1;
        while (i15 < i12) {
            BitMatrix bitMatrix3 = this.image;
            if (!z8) {
                if (!bitMatrix3.get(i9, i15)) {
                    i13 = i15;
                    while (true) {
                        i13++;
                        if (i13 < i12) {
                        }
                    }
                    int i19 = i13 - i15;
                    if (i13 >= i12) {
                        break;
                    }
                    break;
                    break;
                }
                i15++;
            } else if (bitMatrix3.get(i15, i9)) {
                i15++;
            } else {
                i13 = i15;
                while (true) {
                    i13++;
                    if (i13 < i12) {
                        break;
                    }
                    BitMatrix bitMatrix4 = this.image;
                    if (z8) {
                        if (bitMatrix4.get(i13, i9)) {
                            break;
                        }
                    } else if (bitMatrix4.get(i9, i13)) {
                        break;
                    }
                }
                int i192 = i13 - i15;
                if (i13 >= i12 || i192 > i10) {
                    break;
                }
                i15 = i13;
            }
        }
        int i20 = i15 - 1;
        if (i20 > i18) {
            return new int[]{i18, i20};
        }
        return null;
    }

    private ResultPoint findCornerFromCenter(int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17) throws NotFoundException {
        int[] iArr = null;
        int i18 = i9;
        int i19 = i13;
        while (i19 < i16 && i19 >= i15 && i18 < i12 && i18 >= i11) {
            int[] iArrBlackWhiteRange = i10 == 0 ? blackWhiteRange(i19, i17, i11, i12, true) : blackWhiteRange(i18, i17, i15, i16, false);
            if (iArrBlackWhiteRange == null) {
                if (iArr == null) {
                    throw NotFoundException.getNotFoundInstance();
                }
                if (i10 == 0) {
                    int i20 = i19 - i14;
                    int i21 = iArr[0];
                    if (i21 >= i9) {
                        return new ResultPoint(iArr[1], i20);
                    }
                    int i22 = iArr[1];
                    if (i22 > i9) {
                        return new ResultPoint(i14 > 0 ? i21 : i22, i20);
                    }
                    return new ResultPoint(i21, i20);
                }
                int i23 = i18 - i10;
                int i24 = iArr[0];
                if (i24 >= i13) {
                    return new ResultPoint(i23, iArr[1]);
                }
                int i25 = iArr[1];
                if (i25 > i13) {
                    return new ResultPoint(i23, i10 < 0 ? i24 : i25);
                }
                return new ResultPoint(i23, i24);
            }
            i19 += i14;
            i18 += i10;
            iArr = iArrBlackWhiteRange;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public ResultPoint[] detect() throws NotFoundException {
        int height = this.image.getHeight();
        int width = this.image.getWidth();
        int i9 = height / 2;
        int i10 = width / 2;
        int iMax = Math.max(1, height / 256);
        int iMax2 = Math.max(1, width / 256);
        int i11 = -iMax;
        int i12 = i10 / 2;
        int y8 = ((int) findCornerFromCenter(i10, 0, 0, width, i9, i11, 0, height, i12).getY()) - 1;
        int i13 = i9 / 2;
        ResultPoint resultPointFindCornerFromCenter = findCornerFromCenter(i10, -iMax2, 0, width, i9, 0, y8, height, i13);
        int x8 = ((int) resultPointFindCornerFromCenter.getX()) - 1;
        ResultPoint resultPointFindCornerFromCenter2 = findCornerFromCenter(i10, iMax2, x8, width, i9, 0, y8, height, i13);
        int x9 = ((int) resultPointFindCornerFromCenter2.getX()) + 1;
        ResultPoint resultPointFindCornerFromCenter3 = findCornerFromCenter(i10, 0, x8, x9, i9, iMax, y8, height, i12);
        return new ResultPoint[]{findCornerFromCenter(i10, 0, x8, x9, i9, i11, y8, ((int) resultPointFindCornerFromCenter3.getY()) + 1, i10 / 4), resultPointFindCornerFromCenter, resultPointFindCornerFromCenter2, resultPointFindCornerFromCenter3};
    }
}
