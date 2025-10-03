package com.google.zxing.common;

import com.google.zxing.Binarizer;
import com.google.zxing.LuminanceSource;
import java.lang.reflect.Array;

/* loaded from: classes2.dex */
public final class HybridBinarizer extends GlobalHistogramBinarizer {
    private static final int BLOCK_SIZE = 8;
    private static final int BLOCK_SIZE_MASK = 7;
    private static final int BLOCK_SIZE_POWER = 3;
    private static final int MINIMUM_DIMENSION = 40;
    private static final int MIN_DYNAMIC_RANGE = 24;
    private BitMatrix matrix;

    public HybridBinarizer(LuminanceSource luminanceSource) {
        super(luminanceSource);
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x008c A[PHI: r4
      0x008c: PHI (r4v5 int) = (r4v4 int), (r4v8 int), (r4v8 int) binds: [B:32:0x006c, B:34:0x0070, B:35:0x0072] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static int[][] calculateBlackPoints(byte[] bArr, int i9, int i10, int i11, int i12) {
        char c9;
        char c10 = 2;
        boolean z8 = true;
        int i13 = 0;
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, i10, i9);
        int i14 = 0;
        while (i14 < i10) {
            int i15 = i14 << 3;
            int i16 = i12 - 8;
            if (i15 > i16) {
                i15 = i16;
            }
            int i17 = i13;
            while (i17 < i9) {
                int i18 = i17 << 3;
                int i19 = i11 - 8;
                if (i18 > i19) {
                    i18 = i19;
                }
                int i20 = (i15 * i11) + i18;
                int i21 = i13;
                int i22 = i21;
                int i23 = i22;
                int i24 = 255;
                while (i21 < 8) {
                    for (int i25 = 0; i25 < 8; i25++) {
                        int i26 = bArr[i20 + i25] & 255;
                        i22 += i26;
                        if (i26 < i24) {
                            i24 = i26;
                        }
                        if (i26 > i23) {
                            i23 = i26;
                        }
                    }
                    if (i23 - i24 > 24) {
                        while (true) {
                            i21++;
                            i20 += i11;
                            if (i21 < 8) {
                                for (int i27 = 0; i27 < 8; i27++) {
                                    i22 += bArr[i20 + i27] & 255;
                                }
                            }
                        }
                    }
                    i21++;
                    i20 += i11;
                    z8 = true;
                }
                boolean z9 = z8;
                int i28 = i22 >> 6;
                if (i23 - i24 <= 24) {
                    i28 = i24 / 2;
                    if (i14 <= 0 || i17 <= 0) {
                        c9 = 2;
                    } else {
                        int[] iArr2 = iArr[i14 - 1];
                        int i29 = i17 - 1;
                        c9 = 2;
                        int i30 = ((iArr2[i17] + (iArr[i14][i29] * 2)) + iArr2[i29]) / 4;
                        if (i24 < i30) {
                            i28 = i30;
                        }
                    }
                }
                iArr[i14][i17] = i28;
                i17++;
                z8 = z9;
                c10 = c9;
                i13 = 0;
            }
            i14++;
            c10 = c10;
            i13 = 0;
        }
        return iArr;
    }

    private static void calculateThresholdForBlock(byte[] bArr, int i9, int i10, int i11, int i12, int[][] iArr, BitMatrix bitMatrix) {
        for (int i13 = 0; i13 < i10; i13++) {
            int i14 = i13 << 3;
            int i15 = i12 - 8;
            if (i14 > i15) {
                i14 = i15;
            }
            for (int i16 = 0; i16 < i9; i16++) {
                int i17 = i16 << 3;
                int i18 = i11 - 8;
                if (i17 <= i18) {
                    i18 = i17;
                }
                int iCap = cap(i16, 2, i9 - 3);
                int iCap2 = cap(i13, 2, i10 - 3);
                int i19 = 0;
                for (int i20 = -2; i20 <= 2; i20++) {
                    int[] iArr2 = iArr[iCap2 + i20];
                    i19 += iArr2[iCap - 2] + iArr2[iCap - 1] + iArr2[iCap] + iArr2[iCap + 1] + iArr2[iCap + 2];
                }
                thresholdBlock(bArr, i18, i14, i19 / 25, i11, bitMatrix);
            }
        }
    }

    private static int cap(int i9, int i10, int i11) {
        return i9 < i10 ? i10 : i9 > i11 ? i11 : i9;
    }

    private static void thresholdBlock(byte[] bArr, int i9, int i10, int i11, int i12, BitMatrix bitMatrix) {
        int i13 = (i10 * i12) + i9;
        int i14 = 0;
        while (i14 < 8) {
            for (int i15 = 0; i15 < 8; i15++) {
                if ((bArr[i13 + i15] & 255) <= i11) {
                    bitMatrix.set(i9 + i15, i10 + i14);
                }
            }
            i14++;
            i13 += i12;
        }
    }

    @Override // com.google.zxing.common.GlobalHistogramBinarizer, com.google.zxing.Binarizer
    public Binarizer createBinarizer(LuminanceSource luminanceSource) {
        return new HybridBinarizer(luminanceSource);
    }

    @Override // com.google.zxing.common.GlobalHistogramBinarizer, com.google.zxing.Binarizer
    public BitMatrix getBlackMatrix() {
        BitMatrix bitMatrix = this.matrix;
        if (bitMatrix != null) {
            return bitMatrix;
        }
        LuminanceSource luminanceSource = getLuminanceSource();
        int width = luminanceSource.getWidth();
        int height = luminanceSource.getHeight();
        if (width < 40 || height < 40) {
            this.matrix = super.getBlackMatrix();
        } else {
            byte[] matrix = luminanceSource.getMatrix();
            int i9 = width >> 3;
            if ((width & 7) != 0) {
                i9++;
            }
            int i10 = i9;
            int i11 = height >> 3;
            if ((height & 7) != 0) {
                i11++;
            }
            int i12 = i11;
            int[][] iArrCalculateBlackPoints = calculateBlackPoints(matrix, i10, i12, width, height);
            BitMatrix bitMatrix2 = new BitMatrix(width, height);
            calculateThresholdForBlock(matrix, i10, i12, width, height, iArrCalculateBlackPoints, bitMatrix2);
            this.matrix = bitMatrix2;
        }
        return this.matrix;
    }
}
