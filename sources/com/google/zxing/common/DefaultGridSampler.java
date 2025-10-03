package com.google.zxing.common;

import com.google.zxing.NotFoundException;

/* loaded from: classes2.dex */
public final class DefaultGridSampler extends GridSampler {
    @Override // com.google.zxing.common.GridSampler
    public BitMatrix sampleGrid(BitMatrix bitMatrix, int i9, int i10, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16, float f17, float f18, float f19, float f20, float f21, float f22, float f23, float f24) {
        return sampleGrid(bitMatrix, i9, i10, PerspectiveTransform.quadrilateralToQuadrilateral(f9, f10, f11, f12, f13, f14, f15, f16, f17, f18, f19, f20, f21, f22, f23, f24));
    }

    @Override // com.google.zxing.common.GridSampler
    public BitMatrix sampleGrid(BitMatrix bitMatrix, int i9, int i10, PerspectiveTransform perspectiveTransform) throws NotFoundException {
        if (i9 > 0 && i10 > 0) {
            BitMatrix bitMatrix2 = new BitMatrix(i9, i10);
            int i11 = i9 * 2;
            float[] fArr = new float[i11];
            for (int i12 = 0; i12 < i10; i12++) {
                float f9 = i12 + 0.5f;
                for (int i13 = 0; i13 < i11; i13 += 2) {
                    fArr[i13] = (i13 / 2) + 0.5f;
                    fArr[i13 + 1] = f9;
                }
                perspectiveTransform.transformPoints(fArr);
                GridSampler.checkAndNudgePoints(bitMatrix, fArr);
                for (int i14 = 0; i14 < i11; i14 += 2) {
                    try {
                        if (bitMatrix.get((int) fArr[i14], (int) fArr[i14 + 1])) {
                            bitMatrix2.set(i14 / 2, i12);
                        }
                    } catch (ArrayIndexOutOfBoundsException unused) {
                        throw NotFoundException.getNotFoundInstance();
                    }
                }
            }
            return bitMatrix2;
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
