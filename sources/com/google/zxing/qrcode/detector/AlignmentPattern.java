package com.google.zxing.qrcode.detector;

import com.google.zxing.ResultPoint;

/* loaded from: classes2.dex */
public final class AlignmentPattern extends ResultPoint {
    private final float estimatedModuleSize;

    public AlignmentPattern(float f9, float f10, float f11) {
        super(f9, f10);
        this.estimatedModuleSize = f11;
    }

    public boolean aboutEquals(float f9, float f10, float f11) {
        if (Math.abs(f10 - getY()) > f9 || Math.abs(f11 - getX()) > f9) {
            return false;
        }
        float fAbs = Math.abs(f9 - this.estimatedModuleSize);
        return fAbs <= 1.0f || fAbs <= this.estimatedModuleSize;
    }

    public AlignmentPattern combineEstimate(float f9, float f10, float f11) {
        return new AlignmentPattern((getX() + f10) / 2.0f, (getY() + f9) / 2.0f, (this.estimatedModuleSize + f11) / 2.0f);
    }
}
