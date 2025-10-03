package com.google.android.material.bottomappbar;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.material.shape.EdgeTreatment;
import com.google.android.material.shape.ShapePath;

/* loaded from: classes2.dex */
public class BottomAppBarTopEdgeTreatment extends EdgeTreatment {
    private static final int ANGLE_LEFT = 180;
    private static final int ANGLE_UP = 270;
    private static final int ARC_HALF = 180;
    private static final int ARC_QUARTER = 90;
    private float cradleVerticalOffset;
    private float fabDiameter;
    private float fabMargin;
    private float horizontalOffset;
    private float roundedCornerRadius;

    public BottomAppBarTopEdgeTreatment(float f9, float f10, float f11) {
        this.fabMargin = f9;
        this.roundedCornerRadius = f10;
        this.cradleVerticalOffset = f11;
        if (f11 < BitmapDescriptorFactory.HUE_RED) {
            throw new IllegalArgumentException("cradleVerticalOffset must be positive.");
        }
        this.horizontalOffset = BitmapDescriptorFactory.HUE_RED;
    }

    public float getCradleVerticalOffset() {
        return this.cradleVerticalOffset;
    }

    @Override // com.google.android.material.shape.EdgeTreatment
    public void getEdgePath(float f9, float f10, ShapePath shapePath) {
        float f11 = this.fabDiameter;
        if (f11 == BitmapDescriptorFactory.HUE_RED) {
            shapePath.lineTo(f9, BitmapDescriptorFactory.HUE_RED);
            return;
        }
        float f12 = ((this.fabMargin * 2.0f) + f11) / 2.0f;
        float f13 = f10 * this.roundedCornerRadius;
        float f14 = (f9 / 2.0f) + this.horizontalOffset;
        float f15 = (this.cradleVerticalOffset * f10) + ((1.0f - f10) * f12);
        if (f15 / f12 >= 1.0f) {
            shapePath.lineTo(f9, BitmapDescriptorFactory.HUE_RED);
            return;
        }
        float f16 = f12 + f13;
        float f17 = f15 + f13;
        float fSqrt = (float) Math.sqrt((f16 * f16) - (f17 * f17));
        float f18 = f14 - fSqrt;
        float f19 = f14 + fSqrt;
        float degrees = (float) Math.toDegrees(Math.atan(fSqrt / f17));
        float f20 = 90.0f - degrees;
        float f21 = f18 - f13;
        shapePath.lineTo(f21, BitmapDescriptorFactory.HUE_RED);
        float f22 = f13 * 2.0f;
        shapePath.addArc(f21, BitmapDescriptorFactory.HUE_RED, f18 + f13, f22, 270.0f, degrees);
        shapePath.addArc(f14 - f12, (-f12) - f15, f14 + f12, f12 - f15, 180.0f - f20, (f20 * 2.0f) - 180.0f);
        shapePath.addArc(f19 - f13, BitmapDescriptorFactory.HUE_RED, f19 + f13, f22, 270.0f - degrees, degrees);
        shapePath.lineTo(f9, BitmapDescriptorFactory.HUE_RED);
    }

    public float getFabCradleMargin() {
        return this.fabMargin;
    }

    public float getFabCradleRoundedCornerRadius() {
        return this.roundedCornerRadius;
    }

    public float getFabDiameter() {
        return this.fabDiameter;
    }

    public float getHorizontalOffset() {
        return this.horizontalOffset;
    }

    public void setCradleVerticalOffset(float f9) {
        this.cradleVerticalOffset = f9;
    }

    public void setFabCradleMargin(float f9) {
        this.fabMargin = f9;
    }

    public void setFabCradleRoundedCornerRadius(float f9) {
        this.roundedCornerRadius = f9;
    }

    public void setFabDiameter(float f9) {
        this.fabDiameter = f9;
    }

    public void setHorizontalOffset(float f9) {
        this.horizontalOffset = f9;
    }
}
