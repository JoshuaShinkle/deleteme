package com.google.zxing.multi.qrcode.detector;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.detector.FinderPattern;
import com.google.zxing.qrcode.detector.FinderPatternFinder;
import com.google.zxing.qrcode.detector.FinderPatternInfo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
final class MultiFinderPatternFinder extends FinderPatternFinder {
    private static final float DIFF_MODSIZE_CUTOFF = 0.5f;
    private static final float DIFF_MODSIZE_CUTOFF_PERCENT = 0.05f;
    private static final FinderPatternInfo[] EMPTY_RESULT_ARRAY = new FinderPatternInfo[0];
    private static final float MAX_MODULE_COUNT_PER_EDGE = 180.0f;
    private static final float MIN_MODULE_COUNT_PER_EDGE = 9.0f;

    public static final class ModuleSizeComparator implements Comparator<FinderPattern>, Serializable {
        private ModuleSizeComparator() {
        }

        @Override // java.util.Comparator
        public int compare(FinderPattern finderPattern, FinderPattern finderPattern2) {
            double estimatedModuleSize = finderPattern2.getEstimatedModuleSize() - finderPattern.getEstimatedModuleSize();
            if (estimatedModuleSize < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                return -1;
            }
            return estimatedModuleSize > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? 1 : 0;
        }
    }

    public MultiFinderPatternFinder(BitMatrix bitMatrix) {
        super(bitMatrix);
    }

    private FinderPattern[][] selectMutipleBestPatterns() throws NotFoundException {
        char c9;
        char c10;
        List<FinderPattern> possibleCenters = getPossibleCenters();
        int size = possibleCenters.size();
        int i9 = 3;
        if (size < 3) {
            throw NotFoundException.getNotFoundInstance();
        }
        char c11 = 2;
        char c12 = 0;
        if (size == 3) {
            return new FinderPattern[][]{new FinderPattern[]{possibleCenters.get(0), possibleCenters.get(1), possibleCenters.get(2)}};
        }
        Collections.sort(possibleCenters, new ModuleSizeComparator());
        ArrayList arrayList = new ArrayList();
        int i10 = 0;
        while (i10 < size - 2) {
            FinderPattern finderPattern = possibleCenters.get(i10);
            if (finderPattern != null) {
                int i11 = i10 + 1;
                while (i11 < size - 1) {
                    FinderPattern finderPattern2 = possibleCenters.get(i11);
                    if (finderPattern2 != null) {
                        float estimatedModuleSize = (finderPattern.getEstimatedModuleSize() - finderPattern2.getEstimatedModuleSize()) / Math.min(finderPattern.getEstimatedModuleSize(), finderPattern2.getEstimatedModuleSize());
                        float fAbs = Math.abs(finderPattern.getEstimatedModuleSize() - finderPattern2.getEstimatedModuleSize());
                        float f9 = DIFF_MODSIZE_CUTOFF;
                        float f10 = DIFF_MODSIZE_CUTOFF_PERCENT;
                        if (fAbs > DIFF_MODSIZE_CUTOFF && estimatedModuleSize >= DIFF_MODSIZE_CUTOFF_PERCENT) {
                            break;
                        }
                        int i12 = i11 + 1;
                        while (i12 < size) {
                            FinderPattern finderPattern3 = possibleCenters.get(i12);
                            if (finderPattern3 != null) {
                                float estimatedModuleSize2 = (finderPattern2.getEstimatedModuleSize() - finderPattern3.getEstimatedModuleSize()) / Math.min(finderPattern2.getEstimatedModuleSize(), finderPattern3.getEstimatedModuleSize());
                                if (Math.abs(finderPattern2.getEstimatedModuleSize() - finderPattern3.getEstimatedModuleSize()) > f9 && estimatedModuleSize2 >= f10) {
                                    c10 = 2;
                                    break;
                                }
                                FinderPattern[] finderPatternArr = new FinderPattern[i9];
                                finderPatternArr[c12] = finderPattern;
                                finderPatternArr[1] = finderPattern2;
                                c9 = 2;
                                finderPatternArr[2] = finderPattern3;
                                ResultPoint.orderBestPatterns(finderPatternArr);
                                FinderPatternInfo finderPatternInfo = new FinderPatternInfo(finderPatternArr);
                                float fDistance = ResultPoint.distance(finderPatternInfo.getTopLeft(), finderPatternInfo.getBottomLeft());
                                float fDistance2 = ResultPoint.distance(finderPatternInfo.getTopRight(), finderPatternInfo.getBottomLeft());
                                float fDistance3 = ResultPoint.distance(finderPatternInfo.getTopLeft(), finderPatternInfo.getTopRight());
                                float estimatedModuleSize3 = (fDistance + fDistance3) / (finderPattern.getEstimatedModuleSize() * 2.0f);
                                if (estimatedModuleSize3 <= 180.0f && estimatedModuleSize3 >= MIN_MODULE_COUNT_PER_EDGE && Math.abs((fDistance - fDistance3) / Math.min(fDistance, fDistance3)) < 0.1f) {
                                    float fSqrt = (float) Math.sqrt((fDistance * fDistance) + (fDistance3 * fDistance3));
                                    if (Math.abs((fDistance2 - fSqrt) / Math.min(fDistance2, fSqrt)) < 0.1f) {
                                        arrayList.add(finderPatternArr);
                                    }
                                }
                            } else {
                                c9 = c11;
                            }
                            i12++;
                            c11 = c9;
                            i9 = 3;
                            c12 = 0;
                            f9 = DIFF_MODSIZE_CUTOFF;
                            f10 = DIFF_MODSIZE_CUTOFF_PERCENT;
                        }
                        c10 = c11;
                    } else {
                        c10 = c11;
                    }
                    i11++;
                    c11 = c10;
                    i9 = 3;
                    c12 = 0;
                }
            }
            i10++;
            c11 = c11;
            i9 = 3;
            c12 = 0;
        }
        if (arrayList.isEmpty()) {
            throw NotFoundException.getNotFoundInstance();
        }
        return (FinderPattern[][]) arrayList.toArray(new FinderPattern[arrayList.size()][]);
    }

    public FinderPatternInfo[] findMulti(Map<DecodeHintType, ?> map) throws NotFoundException {
        boolean z8 = map != null && map.containsKey(DecodeHintType.TRY_HARDER);
        boolean z9 = map != null && map.containsKey(DecodeHintType.PURE_BARCODE);
        BitMatrix image = getImage();
        int height = image.getHeight();
        int width = image.getWidth();
        int i9 = (int) ((height / 228.0f) * 3.0f);
        if (i9 < 3 || z8) {
            i9 = 3;
        }
        int[] iArr = new int[5];
        for (int i10 = i9 - 1; i10 < height; i10 += i9) {
            iArr[0] = 0;
            iArr[1] = 0;
            iArr[2] = 0;
            iArr[3] = 0;
            iArr[4] = 0;
            int i11 = 0;
            for (int i12 = 0; i12 < width; i12++) {
                if (image.get(i12, i10)) {
                    if ((i11 & 1) == 1) {
                        i11++;
                    }
                    iArr[i11] = iArr[i11] + 1;
                } else if ((i11 & 1) != 0) {
                    iArr[i11] = iArr[i11] + 1;
                } else if (i11 != 4) {
                    i11++;
                    iArr[i11] = iArr[i11] + 1;
                } else if (FinderPatternFinder.foundPatternCross(iArr) && handlePossibleCenter(iArr, i10, i12, z9)) {
                    iArr[0] = 0;
                    iArr[1] = 0;
                    iArr[2] = 0;
                    iArr[3] = 0;
                    iArr[4] = 0;
                    i11 = 0;
                } else {
                    iArr[0] = iArr[2];
                    iArr[1] = iArr[3];
                    iArr[2] = iArr[4];
                    iArr[3] = 1;
                    iArr[4] = 0;
                    i11 = 3;
                }
            }
            if (FinderPatternFinder.foundPatternCross(iArr)) {
                handlePossibleCenter(iArr, i10, width, z9);
            }
        }
        FinderPattern[][] finderPatternArrSelectMutipleBestPatterns = selectMutipleBestPatterns();
        ArrayList arrayList = new ArrayList();
        for (FinderPattern[] finderPatternArr : finderPatternArrSelectMutipleBestPatterns) {
            ResultPoint.orderBestPatterns(finderPatternArr);
            arrayList.add(new FinderPatternInfo(finderPatternArr));
        }
        return arrayList.isEmpty() ? EMPTY_RESULT_ARRAY : (FinderPatternInfo[]) arrayList.toArray(new FinderPatternInfo[arrayList.size()]);
    }

    public MultiFinderPatternFinder(BitMatrix bitMatrix, ResultPointCallback resultPointCallback) {
        super(bitMatrix, resultPointCallback);
    }
}
