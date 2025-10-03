package com.google.zxing;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.zxing.common.detector.MathUtils;

/* loaded from: classes2.dex */
public class ResultPoint {

    /* renamed from: x */
    private final float f15648x;

    /* renamed from: y */
    private final float f15649y;

    public ResultPoint(float f9, float f10) {
        this.f15648x = f9;
        this.f15649y = f10;
    }

    private static float crossProductZ(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3) {
        float f9 = resultPoint2.f15648x;
        float f10 = resultPoint2.f15649y;
        return ((resultPoint3.f15648x - f9) * (resultPoint.f15649y - f10)) - ((resultPoint3.f15649y - f10) * (resultPoint.f15648x - f9));
    }

    public static float distance(ResultPoint resultPoint, ResultPoint resultPoint2) {
        return MathUtils.distance(resultPoint.f15648x, resultPoint.f15649y, resultPoint2.f15648x, resultPoint2.f15649y);
    }

    public static void orderBestPatterns(ResultPoint[] resultPointArr) {
        ResultPoint resultPoint;
        ResultPoint resultPoint2;
        ResultPoint resultPoint3;
        float fDistance = distance(resultPointArr[0], resultPointArr[1]);
        float fDistance2 = distance(resultPointArr[1], resultPointArr[2]);
        float fDistance3 = distance(resultPointArr[0], resultPointArr[2]);
        if (fDistance2 >= fDistance && fDistance2 >= fDistance3) {
            resultPoint = resultPointArr[0];
            resultPoint2 = resultPointArr[1];
            resultPoint3 = resultPointArr[2];
        } else if (fDistance3 < fDistance2 || fDistance3 < fDistance) {
            resultPoint = resultPointArr[2];
            resultPoint2 = resultPointArr[0];
            resultPoint3 = resultPointArr[1];
        } else {
            resultPoint = resultPointArr[1];
            resultPoint2 = resultPointArr[0];
            resultPoint3 = resultPointArr[2];
        }
        if (crossProductZ(resultPoint2, resultPoint, resultPoint3) < BitmapDescriptorFactory.HUE_RED) {
            ResultPoint resultPoint4 = resultPoint3;
            resultPoint3 = resultPoint2;
            resultPoint2 = resultPoint4;
        }
        resultPointArr[0] = resultPoint2;
        resultPointArr[1] = resultPoint;
        resultPointArr[2] = resultPoint3;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof ResultPoint)) {
            return false;
        }
        ResultPoint resultPoint = (ResultPoint) obj;
        return this.f15648x == resultPoint.f15648x && this.f15649y == resultPoint.f15649y;
    }

    public final float getX() {
        return this.f15648x;
    }

    public final float getY() {
        return this.f15649y;
    }

    public final int hashCode() {
        return (Float.floatToIntBits(this.f15648x) * 31) + Float.floatToIntBits(this.f15649y);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(25);
        sb.append('(');
        sb.append(this.f15648x);
        sb.append(',');
        sb.append(this.f15649y);
        sb.append(')');
        return sb.toString();
    }
}
