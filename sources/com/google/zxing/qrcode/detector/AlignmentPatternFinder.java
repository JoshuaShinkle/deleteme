package com.google.zxing.qrcode.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
final class AlignmentPatternFinder {
    private final int height;
    private final BitMatrix image;
    private final float moduleSize;
    private final ResultPointCallback resultPointCallback;
    private final int startX;
    private final int startY;
    private final int width;
    private final List<AlignmentPattern> possibleCenters = new ArrayList(5);
    private final int[] crossCheckStateCount = new int[3];

    public AlignmentPatternFinder(BitMatrix bitMatrix, int i9, int i10, int i11, int i12, float f9, ResultPointCallback resultPointCallback) {
        this.image = bitMatrix;
        this.startX = i9;
        this.startY = i10;
        this.width = i11;
        this.height = i12;
        this.moduleSize = f9;
        this.resultPointCallback = resultPointCallback;
    }

    private static float centerFromEnd(int[] iArr, int i9) {
        return (i9 - iArr[2]) - (iArr[1] / 2.0f);
    }

    private float crossCheckVertical(int i9, int i10, int i11, int i12) {
        BitMatrix bitMatrix = this.image;
        int height = bitMatrix.getHeight();
        int[] iArr = this.crossCheckStateCount;
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 0;
        int i13 = i9;
        while (i13 >= 0 && bitMatrix.get(i10, i13)) {
            int i14 = iArr[1];
            if (i14 > i11) {
                break;
            }
            iArr[1] = i14 + 1;
            i13--;
        }
        if (i13 < 0 || iArr[1] > i11) {
            return Float.NaN;
        }
        while (i13 >= 0 && !bitMatrix.get(i10, i13)) {
            int i15 = iArr[0];
            if (i15 > i11) {
                break;
            }
            iArr[0] = i15 + 1;
            i13--;
        }
        if (iArr[0] > i11) {
            return Float.NaN;
        }
        int i16 = i9 + 1;
        while (i16 < height && bitMatrix.get(i10, i16)) {
            int i17 = iArr[1];
            if (i17 > i11) {
                break;
            }
            iArr[1] = i17 + 1;
            i16++;
        }
        if (i16 == height || iArr[1] > i11) {
            return Float.NaN;
        }
        while (i16 < height && !bitMatrix.get(i10, i16)) {
            int i18 = iArr[2];
            if (i18 > i11) {
                break;
            }
            iArr[2] = i18 + 1;
            i16++;
        }
        int i19 = iArr[2];
        if (i19 <= i11 && Math.abs(((iArr[0] + iArr[1]) + i19) - i12) * 5 < i12 * 2 && foundPatternCross(iArr)) {
            return centerFromEnd(iArr, i16);
        }
        return Float.NaN;
    }

    private boolean foundPatternCross(int[] iArr) {
        float f9 = this.moduleSize;
        float f10 = f9 / 2.0f;
        for (int i9 = 0; i9 < 3; i9++) {
            if (Math.abs(f9 - iArr[i9]) >= f10) {
                return false;
            }
        }
        return true;
    }

    private AlignmentPattern handlePossibleCenter(int[] iArr, int i9, int i10) {
        int i11 = iArr[0] + iArr[1] + iArr[2];
        float fCenterFromEnd = centerFromEnd(iArr, i10);
        float fCrossCheckVertical = crossCheckVertical(i9, (int) fCenterFromEnd, iArr[1] * 2, i11);
        if (Float.isNaN(fCrossCheckVertical)) {
            return null;
        }
        float f9 = ((iArr[0] + iArr[1]) + iArr[2]) / 3.0f;
        for (AlignmentPattern alignmentPattern : this.possibleCenters) {
            if (alignmentPattern.aboutEquals(f9, fCrossCheckVertical, fCenterFromEnd)) {
                return alignmentPattern.combineEstimate(fCrossCheckVertical, fCenterFromEnd, f9);
            }
        }
        AlignmentPattern alignmentPattern2 = new AlignmentPattern(fCenterFromEnd, fCrossCheckVertical, f9);
        this.possibleCenters.add(alignmentPattern2);
        ResultPointCallback resultPointCallback = this.resultPointCallback;
        if (resultPointCallback == null) {
            return null;
        }
        resultPointCallback.foundPossibleResultPoint(alignmentPattern2);
        return null;
    }

    public AlignmentPattern find() throws NotFoundException {
        AlignmentPattern alignmentPatternHandlePossibleCenter;
        AlignmentPattern alignmentPatternHandlePossibleCenter2;
        int i9 = this.startX;
        int i10 = this.height;
        int i11 = this.width + i9;
        int i12 = this.startY + (i10 / 2);
        int[] iArr = new int[3];
        for (int i13 = 0; i13 < i10; i13++) {
            int i14 = ((i13 & 1) == 0 ? (i13 + 1) / 2 : -((i13 + 1) / 2)) + i12;
            iArr[0] = 0;
            iArr[1] = 0;
            iArr[2] = 0;
            int i15 = i9;
            while (i15 < i11 && !this.image.get(i15, i14)) {
                i15++;
            }
            int i16 = 0;
            while (i15 < i11) {
                if (!this.image.get(i15, i14)) {
                    if (i16 == 1) {
                        i16++;
                    }
                    iArr[i16] = iArr[i16] + 1;
                } else if (i16 == 1) {
                    iArr[i16] = iArr[i16] + 1;
                } else if (i16 != 2) {
                    i16++;
                    iArr[i16] = iArr[i16] + 1;
                } else {
                    if (foundPatternCross(iArr) && (alignmentPatternHandlePossibleCenter2 = handlePossibleCenter(iArr, i14, i15)) != null) {
                        return alignmentPatternHandlePossibleCenter2;
                    }
                    iArr[0] = iArr[2];
                    iArr[1] = 1;
                    iArr[2] = 0;
                    i16 = 1;
                }
                i15++;
            }
            if (foundPatternCross(iArr) && (alignmentPatternHandlePossibleCenter = handlePossibleCenter(iArr, i14, i11)) != null) {
                return alignmentPatternHandlePossibleCenter;
            }
        }
        if (this.possibleCenters.isEmpty()) {
            throw NotFoundException.getNotFoundInstance();
        }
        return this.possibleCenters.get(0);
    }
}
