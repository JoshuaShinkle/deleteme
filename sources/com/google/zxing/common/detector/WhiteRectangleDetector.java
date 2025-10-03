package com.google.zxing.common.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;

/* loaded from: classes2.dex */
public final class WhiteRectangleDetector {
    private static final int CORR = 1;
    private static final int INIT_SIZE = 10;
    private final int downInit;
    private final int height;
    private final BitMatrix image;
    private final int leftInit;
    private final int rightInit;
    private final int upInit;
    private final int width;

    public WhiteRectangleDetector(BitMatrix bitMatrix) {
        this(bitMatrix, 10, bitMatrix.getWidth() / 2, bitMatrix.getHeight() / 2);
    }

    private ResultPoint[] centerEdges(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4) {
        float x8 = resultPoint.getX();
        float y8 = resultPoint.getY();
        float x9 = resultPoint2.getX();
        float y9 = resultPoint2.getY();
        float x10 = resultPoint3.getX();
        float y10 = resultPoint3.getY();
        float x11 = resultPoint4.getX();
        float y11 = resultPoint4.getY();
        return x8 < ((float) this.width) / 2.0f ? new ResultPoint[]{new ResultPoint(x11 - 1.0f, y11 + 1.0f), new ResultPoint(x9 + 1.0f, y9 + 1.0f), new ResultPoint(x10 - 1.0f, y10 - 1.0f), new ResultPoint(x8 + 1.0f, y8 - 1.0f)} : new ResultPoint[]{new ResultPoint(x11 + 1.0f, y11 + 1.0f), new ResultPoint(x9 + 1.0f, y9 - 1.0f), new ResultPoint(x10 - 1.0f, y10 + 1.0f), new ResultPoint(x8 - 1.0f, y8 - 1.0f)};
    }

    private boolean containsBlackPoint(int i9, int i10, int i11, boolean z8) {
        if (z8) {
            while (i9 <= i10) {
                if (this.image.get(i9, i11)) {
                    return true;
                }
                i9++;
            }
            return false;
        }
        while (i9 <= i10) {
            if (this.image.get(i11, i9)) {
                return true;
            }
            i9++;
        }
        return false;
    }

    private ResultPoint getBlackPointOnSegment(float f9, float f10, float f11, float f12) {
        int iRound = MathUtils.round(MathUtils.distance(f9, f10, f11, f12));
        float f13 = iRound;
        float f14 = (f11 - f9) / f13;
        float f15 = (f12 - f10) / f13;
        for (int i9 = 0; i9 < iRound; i9++) {
            float f16 = i9;
            int iRound2 = MathUtils.round((f16 * f14) + f9);
            int iRound3 = MathUtils.round((f16 * f15) + f10);
            if (this.image.get(iRound2, iRound3)) {
                return new ResultPoint(iRound2, iRound3);
            }
        }
        return null;
    }

    public ResultPoint[] detect() throws NotFoundException {
        int i9 = this.leftInit;
        int i10 = this.rightInit;
        int i11 = this.upInit;
        int i12 = this.downInit;
        boolean z8 = false;
        boolean z9 = false;
        boolean z10 = false;
        boolean z11 = false;
        boolean z12 = false;
        boolean z13 = false;
        boolean z14 = true;
        while (z14) {
            boolean z15 = false;
            boolean zContainsBlackPoint = true;
            while (true) {
                if ((!zContainsBlackPoint && z9) || i10 >= this.width) {
                    break;
                }
                zContainsBlackPoint = containsBlackPoint(i11, i12, i10, false);
                if (zContainsBlackPoint) {
                    i10++;
                    z9 = true;
                    z15 = true;
                } else if (!z9) {
                    i10++;
                }
            }
            if (i10 < this.width) {
                boolean zContainsBlackPoint2 = true;
                while (true) {
                    if ((!zContainsBlackPoint2 && z10) || i12 >= this.height) {
                        break;
                    }
                    zContainsBlackPoint2 = containsBlackPoint(i9, i10, i12, true);
                    if (zContainsBlackPoint2) {
                        i12++;
                        z10 = true;
                        z15 = true;
                    } else if (!z10) {
                        i12++;
                    }
                }
                if (i12 < this.height) {
                    boolean zContainsBlackPoint3 = true;
                    while (true) {
                        if ((!zContainsBlackPoint3 && z11) || i9 < 0) {
                            break;
                        }
                        zContainsBlackPoint3 = containsBlackPoint(i11, i12, i9, false);
                        if (zContainsBlackPoint3) {
                            i9--;
                            z11 = true;
                            z15 = true;
                        } else if (!z11) {
                            i9--;
                        }
                    }
                    if (i9 >= 0) {
                        z14 = z15;
                        boolean zContainsBlackPoint4 = true;
                        while (true) {
                            if ((!zContainsBlackPoint4 && z13) || i11 < 0) {
                                break;
                            }
                            zContainsBlackPoint4 = containsBlackPoint(i9, i10, i11, true);
                            if (zContainsBlackPoint4) {
                                i11--;
                                z14 = true;
                                z13 = true;
                            } else if (!z13) {
                                i11--;
                            }
                        }
                        if (i11 >= 0) {
                            if (z14) {
                                z12 = true;
                            }
                        }
                    }
                }
            }
            z8 = true;
            break;
        }
        if (z8 || !z12) {
            throw NotFoundException.getNotFoundInstance();
        }
        int i13 = i10 - i9;
        ResultPoint blackPointOnSegment = null;
        ResultPoint blackPointOnSegment2 = null;
        for (int i14 = 1; i14 < i13; i14++) {
            blackPointOnSegment2 = getBlackPointOnSegment(i9, i12 - i14, i9 + i14, i12);
            if (blackPointOnSegment2 != null) {
                break;
            }
        }
        if (blackPointOnSegment2 == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        ResultPoint blackPointOnSegment3 = null;
        for (int i15 = 1; i15 < i13; i15++) {
            blackPointOnSegment3 = getBlackPointOnSegment(i9, i11 + i15, i9 + i15, i11);
            if (blackPointOnSegment3 != null) {
                break;
            }
        }
        if (blackPointOnSegment3 == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        ResultPoint blackPointOnSegment4 = null;
        for (int i16 = 1; i16 < i13; i16++) {
            blackPointOnSegment4 = getBlackPointOnSegment(i10, i11 + i16, i10 - i16, i11);
            if (blackPointOnSegment4 != null) {
                break;
            }
        }
        if (blackPointOnSegment4 == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        for (int i17 = 1; i17 < i13; i17++) {
            blackPointOnSegment = getBlackPointOnSegment(i10, i12 - i17, i10 - i17, i12);
            if (blackPointOnSegment != null) {
                break;
            }
        }
        if (blackPointOnSegment != null) {
            return centerEdges(blackPointOnSegment, blackPointOnSegment2, blackPointOnSegment4, blackPointOnSegment3);
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public WhiteRectangleDetector(BitMatrix bitMatrix, int i9, int i10, int i11) throws NotFoundException {
        this.image = bitMatrix;
        int height = bitMatrix.getHeight();
        this.height = height;
        int width = bitMatrix.getWidth();
        this.width = width;
        int i12 = i9 / 2;
        int i13 = i10 - i12;
        this.leftInit = i13;
        int i14 = i10 + i12;
        this.rightInit = i14;
        int i15 = i11 - i12;
        this.upInit = i15;
        int i16 = i11 + i12;
        this.downInit = i16;
        if (i15 < 0 || i13 < 0 || i16 >= height || i14 >= width) {
            throw NotFoundException.getNotFoundInstance();
        }
    }
}
