package com.google.zxing.qrcode.detector;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class FinderPatternFinder {
    private static final int CENTER_QUORUM = 2;
    protected static final int MAX_MODULES = 57;
    protected static final int MIN_SKIP = 3;
    private final int[] crossCheckStateCount;
    private boolean hasSkipped;
    private final BitMatrix image;
    private final List<FinderPattern> possibleCenters;
    private final ResultPointCallback resultPointCallback;

    public static final class CenterComparator implements Comparator<FinderPattern>, Serializable {
        private final float average;

        private CenterComparator(float f9) {
            this.average = f9;
        }

        @Override // java.util.Comparator
        public int compare(FinderPattern finderPattern, FinderPattern finderPattern2) {
            if (finderPattern2.getCount() != finderPattern.getCount()) {
                return finderPattern2.getCount() - finderPattern.getCount();
            }
            float fAbs = Math.abs(finderPattern2.getEstimatedModuleSize() - this.average);
            float fAbs2 = Math.abs(finderPattern.getEstimatedModuleSize() - this.average);
            if (fAbs < fAbs2) {
                return 1;
            }
            return fAbs == fAbs2 ? 0 : -1;
        }
    }

    public static final class FurthestFromAverageComparator implements Comparator<FinderPattern>, Serializable {
        private final float average;

        private FurthestFromAverageComparator(float f9) {
            this.average = f9;
        }

        @Override // java.util.Comparator
        public int compare(FinderPattern finderPattern, FinderPattern finderPattern2) {
            float fAbs = Math.abs(finderPattern2.getEstimatedModuleSize() - this.average);
            float fAbs2 = Math.abs(finderPattern.getEstimatedModuleSize() - this.average);
            if (fAbs < fAbs2) {
                return -1;
            }
            return fAbs == fAbs2 ? 0 : 1;
        }
    }

    public FinderPatternFinder(BitMatrix bitMatrix) {
        this(bitMatrix, null);
    }

    private static float centerFromEnd(int[] iArr, int i9) {
        return ((i9 - iArr[4]) - iArr[3]) - (iArr[2] / 2.0f);
    }

    private boolean crossCheckDiagonal(int i9, int i10, int i11, int i12) {
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        int[] crossCheckStateCount = getCrossCheckStateCount();
        int i20 = 0;
        while (i9 >= i20 && i10 >= i20 && this.image.get(i10 - i20, i9 - i20)) {
            crossCheckStateCount[2] = crossCheckStateCount[2] + 1;
            i20++;
        }
        if (i9 < i20 || i10 < i20) {
            return false;
        }
        while (i9 >= i20 && i10 >= i20 && !this.image.get(i10 - i20, i9 - i20)) {
            int i21 = crossCheckStateCount[1];
            if (i21 > i11) {
                break;
            }
            crossCheckStateCount[1] = i21 + 1;
            i20++;
        }
        if (i9 < i20 || i10 < i20 || crossCheckStateCount[1] > i11) {
            return false;
        }
        while (i9 >= i20 && i10 >= i20 && this.image.get(i10 - i20, i9 - i20)) {
            int i22 = crossCheckStateCount[0];
            if (i22 > i11) {
                break;
            }
            crossCheckStateCount[0] = i22 + 1;
            i20++;
        }
        if (crossCheckStateCount[0] > i11) {
            return false;
        }
        int height = this.image.getHeight();
        int width = this.image.getWidth();
        int i23 = 1;
        while (true) {
            i13 = i9 + i23;
            if (i13 >= height || (i19 = i10 + i23) >= width || !this.image.get(i19, i13)) {
                break;
            }
            crossCheckStateCount[2] = crossCheckStateCount[2] + 1;
            i23++;
        }
        if (i13 >= height || i10 + i23 >= width) {
            return false;
        }
        while (true) {
            i14 = i9 + i23;
            if (i14 >= height || (i17 = i10 + i23) >= width || this.image.get(i17, i14) || (i18 = crossCheckStateCount[3]) >= i11) {
                break;
            }
            crossCheckStateCount[3] = i18 + 1;
            i23++;
        }
        if (i14 >= height || i10 + i23 >= width || crossCheckStateCount[3] >= i11) {
            return false;
        }
        while (true) {
            int i24 = i9 + i23;
            if (i24 >= height || (i15 = i10 + i23) >= width || !this.image.get(i15, i24) || (i16 = crossCheckStateCount[4]) >= i11) {
                break;
            }
            crossCheckStateCount[4] = i16 + 1;
            i23++;
        }
        int i25 = crossCheckStateCount[4];
        return i25 < i11 && Math.abs(((((crossCheckStateCount[0] + crossCheckStateCount[1]) + crossCheckStateCount[2]) + crossCheckStateCount[3]) + i25) - i12) < i12 * 2 && foundPatternCross(crossCheckStateCount);
    }

    private float crossCheckHorizontal(int i9, int i10, int i11, int i12) {
        int i13;
        int i14;
        int i15;
        BitMatrix bitMatrix = this.image;
        int width = bitMatrix.getWidth();
        int[] crossCheckStateCount = getCrossCheckStateCount();
        int i16 = i9;
        while (i16 >= 0 && bitMatrix.get(i16, i10)) {
            crossCheckStateCount[2] = crossCheckStateCount[2] + 1;
            i16--;
        }
        if (i16 < 0) {
            return Float.NaN;
        }
        while (i16 >= 0 && !bitMatrix.get(i16, i10)) {
            int i17 = crossCheckStateCount[1];
            if (i17 > i11) {
                break;
            }
            crossCheckStateCount[1] = i17 + 1;
            i16--;
        }
        if (i16 < 0 || crossCheckStateCount[1] > i11) {
            return Float.NaN;
        }
        while (i16 >= 0 && bitMatrix.get(i16, i10) && (i15 = crossCheckStateCount[0]) <= i11) {
            crossCheckStateCount[0] = i15 + 1;
            i16--;
        }
        if (crossCheckStateCount[0] > i11) {
            return Float.NaN;
        }
        int i18 = i9 + 1;
        while (i18 < width && bitMatrix.get(i18, i10)) {
            crossCheckStateCount[2] = crossCheckStateCount[2] + 1;
            i18++;
        }
        if (i18 == width) {
            return Float.NaN;
        }
        while (i18 < width && !bitMatrix.get(i18, i10) && (i14 = crossCheckStateCount[3]) < i11) {
            crossCheckStateCount[3] = i14 + 1;
            i18++;
        }
        if (i18 == width || crossCheckStateCount[3] >= i11) {
            return Float.NaN;
        }
        while (i18 < width && bitMatrix.get(i18, i10) && (i13 = crossCheckStateCount[4]) < i11) {
            crossCheckStateCount[4] = i13 + 1;
            i18++;
        }
        int i19 = crossCheckStateCount[4];
        if (i19 < i11 && Math.abs(((((crossCheckStateCount[0] + crossCheckStateCount[1]) + crossCheckStateCount[2]) + crossCheckStateCount[3]) + i19) - i12) * 5 < i12 && foundPatternCross(crossCheckStateCount)) {
            return centerFromEnd(crossCheckStateCount, i18);
        }
        return Float.NaN;
    }

    private float crossCheckVertical(int i9, int i10, int i11, int i12) {
        int i13;
        int i14;
        int i15;
        BitMatrix bitMatrix = this.image;
        int height = bitMatrix.getHeight();
        int[] crossCheckStateCount = getCrossCheckStateCount();
        int i16 = i9;
        while (i16 >= 0 && bitMatrix.get(i10, i16)) {
            crossCheckStateCount[2] = crossCheckStateCount[2] + 1;
            i16--;
        }
        if (i16 < 0) {
            return Float.NaN;
        }
        while (i16 >= 0 && !bitMatrix.get(i10, i16)) {
            int i17 = crossCheckStateCount[1];
            if (i17 > i11) {
                break;
            }
            crossCheckStateCount[1] = i17 + 1;
            i16--;
        }
        if (i16 < 0 || crossCheckStateCount[1] > i11) {
            return Float.NaN;
        }
        while (i16 >= 0 && bitMatrix.get(i10, i16) && (i15 = crossCheckStateCount[0]) <= i11) {
            crossCheckStateCount[0] = i15 + 1;
            i16--;
        }
        if (crossCheckStateCount[0] > i11) {
            return Float.NaN;
        }
        int i18 = i9 + 1;
        while (i18 < height && bitMatrix.get(i10, i18)) {
            crossCheckStateCount[2] = crossCheckStateCount[2] + 1;
            i18++;
        }
        if (i18 == height) {
            return Float.NaN;
        }
        while (i18 < height && !bitMatrix.get(i10, i18) && (i14 = crossCheckStateCount[3]) < i11) {
            crossCheckStateCount[3] = i14 + 1;
            i18++;
        }
        if (i18 == height || crossCheckStateCount[3] >= i11) {
            return Float.NaN;
        }
        while (i18 < height && bitMatrix.get(i10, i18) && (i13 = crossCheckStateCount[4]) < i11) {
            crossCheckStateCount[4] = i13 + 1;
            i18++;
        }
        int i19 = crossCheckStateCount[4];
        if (i19 < i11 && Math.abs(((((crossCheckStateCount[0] + crossCheckStateCount[1]) + crossCheckStateCount[2]) + crossCheckStateCount[3]) + i19) - i12) * 5 < i12 * 2 && foundPatternCross(crossCheckStateCount)) {
            return centerFromEnd(crossCheckStateCount, i18);
        }
        return Float.NaN;
    }

    private int findRowSkip() {
        if (this.possibleCenters.size() <= 1) {
            return 0;
        }
        FinderPattern finderPattern = null;
        for (FinderPattern finderPattern2 : this.possibleCenters) {
            if (finderPattern2.getCount() >= 2) {
                if (finderPattern != null) {
                    this.hasSkipped = true;
                    return ((int) (Math.abs(finderPattern.getX() - finderPattern2.getX()) - Math.abs(finderPattern.getY() - finderPattern2.getY()))) / 2;
                }
                finderPattern = finderPattern2;
            }
        }
        return 0;
    }

    public static boolean foundPatternCross(int[] iArr) {
        int i9 = 0;
        for (int i10 = 0; i10 < 5; i10++) {
            int i11 = iArr[i10];
            if (i11 == 0) {
                return false;
            }
            i9 += i11;
        }
        if (i9 < 7) {
            return false;
        }
        float f9 = i9 / 7.0f;
        float f10 = f9 / 2.0f;
        return Math.abs(f9 - ((float) iArr[0])) < f10 && Math.abs(f9 - ((float) iArr[1])) < f10 && Math.abs((f9 * 3.0f) - ((float) iArr[2])) < 3.0f * f10 && Math.abs(f9 - ((float) iArr[3])) < f10 && Math.abs(f9 - ((float) iArr[4])) < f10;
    }

    private int[] getCrossCheckStateCount() {
        int[] iArr = this.crossCheckStateCount;
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 0;
        iArr[3] = 0;
        iArr[4] = 0;
        return iArr;
    }

    private boolean haveMultiplyConfirmedCenters() {
        int size = this.possibleCenters.size();
        float fAbs = BitmapDescriptorFactory.HUE_RED;
        int i9 = 0;
        float estimatedModuleSize = 0.0f;
        for (FinderPattern finderPattern : this.possibleCenters) {
            if (finderPattern.getCount() >= 2) {
                i9++;
                estimatedModuleSize += finderPattern.getEstimatedModuleSize();
            }
        }
        if (i9 < 3) {
            return false;
        }
        float f9 = estimatedModuleSize / size;
        Iterator<FinderPattern> it = this.possibleCenters.iterator();
        while (it.hasNext()) {
            fAbs += Math.abs(it.next().getEstimatedModuleSize() - f9);
        }
        return fAbs <= estimatedModuleSize * 0.05f;
    }

    private FinderPattern[] selectBestPatterns() throws NotFoundException {
        int size = this.possibleCenters.size();
        if (size < 3) {
            throw NotFoundException.getNotFoundInstance();
        }
        float estimatedModuleSize = BitmapDescriptorFactory.HUE_RED;
        if (size > 3) {
            Iterator<FinderPattern> it = this.possibleCenters.iterator();
            float f9 = 0.0f;
            float f10 = 0.0f;
            while (it.hasNext()) {
                float estimatedModuleSize2 = it.next().getEstimatedModuleSize();
                f9 += estimatedModuleSize2;
                f10 += estimatedModuleSize2 * estimatedModuleSize2;
            }
            float f11 = f9 / size;
            float fSqrt = (float) Math.sqrt((f10 / r0) - (f11 * f11));
            Collections.sort(this.possibleCenters, new FurthestFromAverageComparator(f11));
            float fMax = Math.max(0.2f * f11, fSqrt);
            int i9 = 0;
            while (i9 < this.possibleCenters.size() && this.possibleCenters.size() > 3) {
                if (Math.abs(this.possibleCenters.get(i9).getEstimatedModuleSize() - f11) > fMax) {
                    this.possibleCenters.remove(i9);
                    i9--;
                }
                i9++;
            }
        }
        if (this.possibleCenters.size() > 3) {
            Iterator<FinderPattern> it2 = this.possibleCenters.iterator();
            while (it2.hasNext()) {
                estimatedModuleSize += it2.next().getEstimatedModuleSize();
            }
            Collections.sort(this.possibleCenters, new CenterComparator(estimatedModuleSize / this.possibleCenters.size()));
            List<FinderPattern> list = this.possibleCenters;
            list.subList(3, list.size()).clear();
        }
        return new FinderPattern[]{this.possibleCenters.get(0), this.possibleCenters.get(1), this.possibleCenters.get(2)};
    }

    public final FinderPatternInfo find(Map<DecodeHintType, ?> map) throws NotFoundException {
        boolean z8 = map != null && map.containsKey(DecodeHintType.TRY_HARDER);
        boolean z9 = map != null && map.containsKey(DecodeHintType.PURE_BARCODE);
        int height = this.image.getHeight();
        int width = this.image.getWidth();
        int i9 = (height * 3) / 228;
        if (i9 < 3 || z8) {
            i9 = 3;
        }
        int[] iArr = new int[5];
        int i10 = i9 - 1;
        boolean zHaveMultiplyConfirmedCenters = false;
        while (i10 < height && !zHaveMultiplyConfirmedCenters) {
            iArr[0] = 0;
            iArr[1] = 0;
            iArr[2] = 0;
            iArr[3] = 0;
            iArr[4] = 0;
            int i11 = 0;
            int i12 = 0;
            while (i11 < width) {
                if (this.image.get(i11, i10)) {
                    if ((i12 & 1) == 1) {
                        i12++;
                    }
                    iArr[i12] = iArr[i12] + 1;
                } else if ((i12 & 1) != 0) {
                    iArr[i12] = iArr[i12] + 1;
                } else if (i12 != 4) {
                    i12++;
                    iArr[i12] = iArr[i12] + 1;
                } else if (foundPatternCross(iArr) && handlePossibleCenter(iArr, i10, i11, z9)) {
                    if (this.hasSkipped) {
                        zHaveMultiplyConfirmedCenters = haveMultiplyConfirmedCenters();
                    } else {
                        int iFindRowSkip = findRowSkip();
                        int i13 = iArr[2];
                        if (iFindRowSkip > i13) {
                            i10 += (iFindRowSkip - i13) - 2;
                            i11 = width - 1;
                        }
                    }
                    iArr[0] = 0;
                    iArr[1] = 0;
                    iArr[2] = 0;
                    iArr[3] = 0;
                    iArr[4] = 0;
                    i12 = 0;
                    i9 = 2;
                } else {
                    iArr[0] = iArr[2];
                    iArr[1] = iArr[3];
                    iArr[2] = iArr[4];
                    iArr[3] = 1;
                    iArr[4] = 0;
                    i12 = 3;
                }
                i11++;
            }
            if (foundPatternCross(iArr) && handlePossibleCenter(iArr, i10, width, z9)) {
                i9 = iArr[0];
                if (this.hasSkipped) {
                    zHaveMultiplyConfirmedCenters = haveMultiplyConfirmedCenters();
                }
            }
            i10 += i9;
        }
        FinderPattern[] finderPatternArrSelectBestPatterns = selectBestPatterns();
        ResultPoint.orderBestPatterns(finderPatternArrSelectBestPatterns);
        return new FinderPatternInfo(finderPatternArrSelectBestPatterns);
    }

    public final BitMatrix getImage() {
        return this.image;
    }

    public final List<FinderPattern> getPossibleCenters() {
        return this.possibleCenters;
    }

    public final boolean handlePossibleCenter(int[] iArr, int i9, int i10, boolean z8) {
        boolean z9 = false;
        int i11 = iArr[0] + iArr[1] + iArr[2] + iArr[3] + iArr[4];
        int iCenterFromEnd = (int) centerFromEnd(iArr, i10);
        float fCrossCheckVertical = crossCheckVertical(i9, iCenterFromEnd, iArr[2], i11);
        if (!Float.isNaN(fCrossCheckVertical)) {
            int i12 = (int) fCrossCheckVertical;
            float fCrossCheckHorizontal = crossCheckHorizontal(iCenterFromEnd, i12, iArr[2], i11);
            if (!Float.isNaN(fCrossCheckHorizontal) && (!z8 || crossCheckDiagonal(i12, (int) fCrossCheckHorizontal, iArr[2], i11))) {
                float f9 = i11 / 7.0f;
                int i13 = 0;
                while (true) {
                    if (i13 >= this.possibleCenters.size()) {
                        break;
                    }
                    FinderPattern finderPattern = this.possibleCenters.get(i13);
                    if (finderPattern.aboutEquals(f9, fCrossCheckVertical, fCrossCheckHorizontal)) {
                        this.possibleCenters.set(i13, finderPattern.combineEstimate(fCrossCheckVertical, fCrossCheckHorizontal, f9));
                        z9 = true;
                        break;
                    }
                    i13++;
                }
                if (!z9) {
                    FinderPattern finderPattern2 = new FinderPattern(fCrossCheckHorizontal, fCrossCheckVertical, f9);
                    this.possibleCenters.add(finderPattern2);
                    ResultPointCallback resultPointCallback = this.resultPointCallback;
                    if (resultPointCallback != null) {
                        resultPointCallback.foundPossibleResultPoint(finderPattern2);
                    }
                }
                return true;
            }
        }
        return false;
    }

    public FinderPatternFinder(BitMatrix bitMatrix, ResultPointCallback resultPointCallback) {
        this.image = bitMatrix;
        this.possibleCenters = new ArrayList();
        this.crossCheckStateCount = new int[5];
        this.resultPointCallback = resultPointCallback;
    }
}
