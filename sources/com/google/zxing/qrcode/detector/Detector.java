package com.google.zxing.qrcode.detector;

import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.common.GridSampler;
import com.google.zxing.common.PerspectiveTransform;
import com.google.zxing.common.detector.MathUtils;
import com.google.zxing.qrcode.decoder.Version;
import java.util.Map;

/* loaded from: classes2.dex */
public class Detector {
    private final BitMatrix image;
    private ResultPointCallback resultPointCallback;

    public Detector(BitMatrix bitMatrix) {
        this.image = bitMatrix;
    }

    private float calculateModuleSizeOneWay(ResultPoint resultPoint, ResultPoint resultPoint2) {
        float fSizeOfBlackWhiteBlackRunBothWays = sizeOfBlackWhiteBlackRunBothWays((int) resultPoint.getX(), (int) resultPoint.getY(), (int) resultPoint2.getX(), (int) resultPoint2.getY());
        float fSizeOfBlackWhiteBlackRunBothWays2 = sizeOfBlackWhiteBlackRunBothWays((int) resultPoint2.getX(), (int) resultPoint2.getY(), (int) resultPoint.getX(), (int) resultPoint.getY());
        return Float.isNaN(fSizeOfBlackWhiteBlackRunBothWays) ? fSizeOfBlackWhiteBlackRunBothWays2 / 7.0f : Float.isNaN(fSizeOfBlackWhiteBlackRunBothWays2) ? fSizeOfBlackWhiteBlackRunBothWays / 7.0f : (fSizeOfBlackWhiteBlackRunBothWays + fSizeOfBlackWhiteBlackRunBothWays2) / 14.0f;
    }

    private static int computeDimension(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, float f9) throws NotFoundException {
        int iRound = ((MathUtils.round(ResultPoint.distance(resultPoint, resultPoint2) / f9) + MathUtils.round(ResultPoint.distance(resultPoint, resultPoint3) / f9)) / 2) + 7;
        int i9 = iRound & 3;
        if (i9 == 0) {
            return iRound + 1;
        }
        if (i9 == 2) {
            return iRound - 1;
        }
        if (i9 != 3) {
            return iRound;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static PerspectiveTransform createTransform(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4, int i9) {
        float x8;
        float y8;
        float f9;
        float f10 = i9 - 3.5f;
        if (resultPoint4 != null) {
            x8 = resultPoint4.getX();
            y8 = resultPoint4.getY();
            f9 = f10 - 3.0f;
        } else {
            x8 = (resultPoint2.getX() - resultPoint.getX()) + resultPoint3.getX();
            y8 = (resultPoint2.getY() - resultPoint.getY()) + resultPoint3.getY();
            f9 = f10;
        }
        return PerspectiveTransform.quadrilateralToQuadrilateral(3.5f, 3.5f, f10, 3.5f, f9, f9, 3.5f, f10, resultPoint.getX(), resultPoint.getY(), resultPoint2.getX(), resultPoint2.getY(), x8, y8, resultPoint3.getX(), resultPoint3.getY());
    }

    private static BitMatrix sampleGrid(BitMatrix bitMatrix, PerspectiveTransform perspectiveTransform, int i9) {
        return GridSampler.getInstance().sampleGrid(bitMatrix, i9, i9, perspectiveTransform);
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x0086, code lost:
    
        if (r15 != r0) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x008e, code lost:
    
        return com.google.zxing.common.detector.MathUtils.distance(r19, r6, r1, r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x008f, code lost:
    
        return Float.NaN;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private float sizeOfBlackWhiteBlackRun(int i9, int i10, int i11, int i12) {
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        Detector detector;
        boolean z8;
        int i19;
        int i20 = 1;
        boolean z9 = Math.abs(i12 - i10) > Math.abs(i11 - i9);
        if (z9) {
            i14 = i9;
            i13 = i10;
            i16 = i11;
            i15 = i12;
        } else {
            i13 = i9;
            i14 = i10;
            i15 = i11;
            i16 = i12;
        }
        int iAbs = Math.abs(i15 - i13);
        int iAbs2 = Math.abs(i16 - i14);
        int i21 = 2;
        int i22 = (-iAbs) / 2;
        int i23 = i13 < i15 ? 1 : -1;
        int i24 = i14 < i16 ? 1 : -1;
        int i25 = i15 + i23;
        int i26 = i13;
        int i27 = i14;
        int i28 = 0;
        while (true) {
            if (i26 == i25) {
                i17 = i25;
                i18 = i21;
                break;
            }
            int i29 = z9 ? i27 : i26;
            int i30 = z9 ? i26 : i27;
            if (i28 == i20) {
                z8 = z9;
                i19 = i20;
                i17 = i25;
                detector = this;
            } else {
                detector = this;
                z8 = z9;
                i17 = i25;
                i19 = 0;
            }
            if (i19 == detector.image.get(i29, i30)) {
                if (i28 == 2) {
                    return MathUtils.distance(i26, i27, i13, i14);
                }
                i28++;
            }
            i22 += iAbs2;
            if (i22 > 0) {
                if (i27 == i16) {
                    i18 = 2;
                    break;
                }
                i27 += i24;
                i22 -= iAbs;
            }
            i26 += i23;
            i25 = i17;
            z9 = z8;
            i20 = 1;
            i21 = 2;
        }
    }

    private float sizeOfBlackWhiteBlackRunBothWays(int i9, int i10, int i11, int i12) {
        float width;
        float height;
        float fSizeOfBlackWhiteBlackRun = sizeOfBlackWhiteBlackRun(i9, i10, i11, i12);
        int width2 = i9 - (i11 - i9);
        int height2 = 0;
        if (width2 < 0) {
            width = i9 / (i9 - width2);
            width2 = 0;
        } else if (width2 >= this.image.getWidth()) {
            width = ((this.image.getWidth() - 1) - i9) / (width2 - i9);
            width2 = this.image.getWidth() - 1;
        } else {
            width = 1.0f;
        }
        float f9 = i10;
        int i13 = (int) (f9 - ((i12 - i10) * width));
        if (i13 < 0) {
            height = f9 / (i10 - i13);
        } else if (i13 >= this.image.getHeight()) {
            height = ((this.image.getHeight() - 1) - i10) / (i13 - i10);
            height2 = this.image.getHeight() - 1;
        } else {
            height2 = i13;
            height = 1.0f;
        }
        return (fSizeOfBlackWhiteBlackRun + sizeOfBlackWhiteBlackRun(i9, i10, (int) (i9 + ((width2 - i9) * height)), height2)) - 1.0f;
    }

    public final float calculateModuleSize(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3) {
        return (calculateModuleSizeOneWay(resultPoint, resultPoint2) + calculateModuleSizeOneWay(resultPoint, resultPoint3)) / 2.0f;
    }

    public DetectorResult detect() {
        return detect(null);
    }

    public final AlignmentPattern findAlignmentInRegion(float f9, int i9, int i10, float f10) throws NotFoundException {
        int i11 = (int) (f10 * f9);
        int iMax = Math.max(0, i9 - i11);
        int iMin = Math.min(this.image.getWidth() - 1, i9 + i11) - iMax;
        float f11 = 3.0f * f9;
        if (iMin < f11) {
            throw NotFoundException.getNotFoundInstance();
        }
        int iMax2 = Math.max(0, i10 - i11);
        int iMin2 = Math.min(this.image.getHeight() - 1, i10 + i11) - iMax2;
        if (iMin2 >= f11) {
            return new AlignmentPatternFinder(this.image, iMax, iMax2, iMin, iMin2, f9, this.resultPointCallback).find();
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public final BitMatrix getImage() {
        return this.image;
    }

    public final ResultPointCallback getResultPointCallback() {
        return this.resultPointCallback;
    }

    public final DetectorResult processFinderPatternInfo(FinderPatternInfo finderPatternInfo) throws NotFoundException, FormatException {
        AlignmentPattern alignmentPatternFindAlignmentInRegion;
        FinderPattern topLeft = finderPatternInfo.getTopLeft();
        FinderPattern topRight = finderPatternInfo.getTopRight();
        FinderPattern bottomLeft = finderPatternInfo.getBottomLeft();
        float fCalculateModuleSize = calculateModuleSize(topLeft, topRight, bottomLeft);
        if (fCalculateModuleSize < 1.0f) {
            throw NotFoundException.getNotFoundInstance();
        }
        int iComputeDimension = computeDimension(topLeft, topRight, bottomLeft, fCalculateModuleSize);
        Version provisionalVersionForDimension = Version.getProvisionalVersionForDimension(iComputeDimension);
        int dimensionForVersion = provisionalVersionForDimension.getDimensionForVersion() - 7;
        if (provisionalVersionForDimension.getAlignmentPatternCenters().length > 0) {
            float x8 = (topRight.getX() - topLeft.getX()) + bottomLeft.getX();
            float y8 = (topRight.getY() - topLeft.getY()) + bottomLeft.getY();
            float f9 = 1.0f - (3.0f / dimensionForVersion);
            int x9 = (int) (topLeft.getX() + ((x8 - topLeft.getX()) * f9));
            int y9 = (int) (topLeft.getY() + (f9 * (y8 - topLeft.getY())));
            for (int i9 = 4; i9 <= 16; i9 <<= 1) {
                try {
                    alignmentPatternFindAlignmentInRegion = findAlignmentInRegion(fCalculateModuleSize, x9, y9, i9);
                    break;
                } catch (NotFoundException unused) {
                }
            }
            alignmentPatternFindAlignmentInRegion = null;
        } else {
            alignmentPatternFindAlignmentInRegion = null;
        }
        return new DetectorResult(sampleGrid(this.image, createTransform(topLeft, topRight, bottomLeft, alignmentPatternFindAlignmentInRegion, iComputeDimension), iComputeDimension), alignmentPatternFindAlignmentInRegion == null ? new ResultPoint[]{bottomLeft, topLeft, topRight} : new ResultPoint[]{bottomLeft, topLeft, topRight, alignmentPatternFindAlignmentInRegion});
    }

    public final DetectorResult detect(Map<DecodeHintType, ?> map) {
        ResultPointCallback resultPointCallback = map == null ? null : (ResultPointCallback) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
        this.resultPointCallback = resultPointCallback;
        return processFinderPatternInfo(new FinderPatternFinder(this.image, resultPointCallback).find(map));
    }
}
