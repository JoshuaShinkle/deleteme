package com.google.zxing.qrcode.detector;

import com.google.zxing.ResultPoint;

/* loaded from: classes2.dex */
public final class FinderPattern extends ResultPoint {
    private final int count;
    private final float estimatedModuleSize;

    public FinderPattern(float f9, float f10, float f11) {
        this(f9, f10, f11, 1);
    }

    public boolean aboutEquals(float f9, float f10, float f11) {
        if (Math.abs(f10 - getY()) > f9 || Math.abs(f11 - getX()) > f9) {
            return false;
        }
        float fAbs = Math.abs(f9 - this.estimatedModuleSize);
        return fAbs <= 1.0f || fAbs <= this.estimatedModuleSize;
    }

    public FinderPattern combineEstimate(float f9, float f10, float f11) {
        int i9 = this.count;
        int i10 = i9 + 1;
        float x8 = (i9 * getX()) + f10;
        float f12 = i10;
        return new FinderPattern(x8 / f12, ((this.count * getY()) + f9) / f12, ((this.count * this.estimatedModuleSize) + f11) / f12, i10);
    }

    public int getCount() {
        return this.count;
    }

    public float getEstimatedModuleSize() {
        return this.estimatedModuleSize;
    }

    private FinderPattern(float f9, float f10, float f11, int i9) {
        super(f9, f10);
        this.estimatedModuleSize = f11;
        this.count = i9;
    }
}
