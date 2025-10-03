package com.google.zxing.common;

import com.google.zxing.NotFoundException;

/* loaded from: classes2.dex */
public abstract class GridSampler {
    private static GridSampler gridSampler = new DefaultGridSampler();

    /* JADX WARN: Removed duplicated region for block: B:18:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0075  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void checkAndNudgePoints(BitMatrix bitMatrix, float[] fArr) {
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        boolean z8 = true;
        for (int i9 = 0; i9 < fArr.length && z8; i9 += 2) {
            int i10 = (int) fArr[i9];
            int i11 = i9 + 1;
            int i12 = (int) fArr[i11];
            if (i10 < -1 || i10 > width || i12 < -1 || i12 > height) {
                throw NotFoundException.getNotFoundInstance();
            }
            if (i10 == -1) {
                fArr[i9] = 0.0f;
            } else if (i10 == width) {
                fArr[i9] = width - 1;
            } else {
                z8 = false;
                if (i12 != -1) {
                    fArr[i11] = 0.0f;
                } else if (i12 == height) {
                    fArr[i11] = height - 1;
                }
                z8 = true;
            }
            z8 = true;
            if (i12 != -1) {
            }
            z8 = true;
        }
        boolean z9 = true;
        for (int length = fArr.length - 2; length >= 0 && z9; length -= 2) {
            int i13 = (int) fArr[length];
            int i14 = length + 1;
            int i15 = (int) fArr[i14];
            if (i13 < -1 || i13 > width || i15 < -1 || i15 > height) {
                throw NotFoundException.getNotFoundInstance();
            }
            if (i13 == -1) {
                fArr[length] = 0.0f;
            } else if (i13 == width) {
                fArr[length] = width - 1;
            } else {
                z9 = false;
                if (i15 != -1) {
                    fArr[i14] = 0.0f;
                } else if (i15 == height) {
                    fArr[i14] = height - 1;
                }
                z9 = true;
            }
            z9 = true;
            if (i15 != -1) {
            }
            z9 = true;
        }
    }

    public static GridSampler getInstance() {
        return gridSampler;
    }

    public static void setGridSampler(GridSampler gridSampler2) {
        gridSampler = gridSampler2;
    }

    public abstract BitMatrix sampleGrid(BitMatrix bitMatrix, int i9, int i10, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16, float f17, float f18, float f19, float f20, float f21, float f22, float f23, float f24);

    public abstract BitMatrix sampleGrid(BitMatrix bitMatrix, int i9, int i10, PerspectiveTransform perspectiveTransform);
}
