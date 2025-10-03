package com.google.zxing.common;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* loaded from: classes2.dex */
public final class PerspectiveTransform {
    private final float a11;
    private final float a12;
    private final float a13;
    private final float a21;
    private final float a22;
    private final float a23;
    private final float a31;
    private final float a32;
    private final float a33;

    private PerspectiveTransform(float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16, float f17) {
        this.a11 = f9;
        this.a12 = f12;
        this.a13 = f15;
        this.a21 = f10;
        this.a22 = f13;
        this.a23 = f16;
        this.a31 = f11;
        this.a32 = f14;
        this.a33 = f17;
    }

    public static PerspectiveTransform quadrilateralToQuadrilateral(float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16, float f17, float f18, float f19, float f20, float f21, float f22, float f23, float f24) {
        return squareToQuadrilateral(f17, f18, f19, f20, f21, f22, f23, f24).times(quadrilateralToSquare(f9, f10, f11, f12, f13, f14, f15, f16));
    }

    public static PerspectiveTransform quadrilateralToSquare(float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16) {
        return squareToQuadrilateral(f9, f10, f11, f12, f13, f14, f15, f16).buildAdjoint();
    }

    public static PerspectiveTransform squareToQuadrilateral(float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16) {
        float f17 = ((f9 - f11) + f13) - f15;
        float f18 = ((f10 - f12) + f14) - f16;
        if (f17 == BitmapDescriptorFactory.HUE_RED && f18 == BitmapDescriptorFactory.HUE_RED) {
            return new PerspectiveTransform(f11 - f9, f13 - f11, f9, f12 - f10, f14 - f12, f10, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f);
        }
        float f19 = f11 - f13;
        float f20 = f15 - f13;
        float f21 = f12 - f14;
        float f22 = f16 - f14;
        float f23 = (f19 * f22) - (f20 * f21);
        float f24 = ((f22 * f17) - (f20 * f18)) / f23;
        float f25 = ((f19 * f18) - (f17 * f21)) / f23;
        return new PerspectiveTransform((f24 * f11) + (f11 - f9), (f25 * f15) + (f15 - f9), f9, (f12 - f10) + (f24 * f12), (f16 - f10) + (f25 * f16), f10, f24, f25, 1.0f);
    }

    public PerspectiveTransform buildAdjoint() {
        float f9 = this.a22;
        float f10 = this.a33;
        float f11 = this.a23;
        float f12 = this.a32;
        float f13 = (f9 * f10) - (f11 * f12);
        float f14 = this.a31;
        float f15 = this.a21;
        float f16 = (f11 * f14) - (f15 * f10);
        float f17 = (f15 * f12) - (f9 * f14);
        float f18 = this.a13;
        float f19 = this.a12;
        float f20 = (f18 * f12) - (f19 * f10);
        float f21 = this.a11;
        return new PerspectiveTransform(f13, f16, f17, f20, (f10 * f21) - (f18 * f14), (f14 * f19) - (f12 * f21), (f19 * f11) - (f18 * f9), (f18 * f15) - (f11 * f21), (f21 * f9) - (f19 * f15));
    }

    public PerspectiveTransform times(PerspectiveTransform perspectiveTransform) {
        float f9 = this.a11;
        float f10 = perspectiveTransform.a11;
        float f11 = this.a21;
        float f12 = perspectiveTransform.a12;
        float f13 = this.a31;
        float f14 = perspectiveTransform.a13;
        float f15 = (f9 * f10) + (f11 * f12) + (f13 * f14);
        float f16 = perspectiveTransform.a21;
        float f17 = perspectiveTransform.a22;
        float f18 = perspectiveTransform.a23;
        float f19 = (f9 * f16) + (f11 * f17) + (f13 * f18);
        float f20 = perspectiveTransform.a31;
        float f21 = perspectiveTransform.a32;
        float f22 = perspectiveTransform.a33;
        float f23 = (f9 * f20) + (f11 * f21) + (f13 * f22);
        float f24 = this.a12;
        float f25 = this.a22;
        float f26 = this.a32;
        float f27 = (f24 * f10) + (f25 * f12) + (f26 * f14);
        float f28 = (f24 * f16) + (f25 * f17) + (f26 * f18);
        float f29 = (f26 * f22) + (f24 * f20) + (f25 * f21);
        float f30 = this.a13;
        float f31 = this.a23;
        float f32 = (f10 * f30) + (f12 * f31);
        float f33 = this.a33;
        return new PerspectiveTransform(f15, f19, f23, f27, f28, f29, (f14 * f33) + f32, (f16 * f30) + (f17 * f31) + (f18 * f33), (f30 * f20) + (f31 * f21) + (f33 * f22));
    }

    public void transformPoints(float[] fArr) {
        int length = fArr.length;
        float f9 = this.a11;
        float f10 = this.a12;
        float f11 = this.a13;
        float f12 = this.a21;
        float f13 = this.a22;
        float f14 = this.a23;
        float f15 = this.a31;
        float f16 = this.a32;
        float f17 = this.a33;
        for (int i9 = 0; i9 < length; i9 += 2) {
            float f18 = fArr[i9];
            int i10 = i9 + 1;
            float f19 = fArr[i10];
            float f20 = (f11 * f18) + (f14 * f19) + f17;
            fArr[i9] = (((f9 * f18) + (f12 * f19)) + f15) / f20;
            fArr[i10] = (((f18 * f10) + (f19 * f13)) + f16) / f20;
        }
    }

    public void transformPoints(float[] fArr, float[] fArr2) {
        int length = fArr.length;
        for (int i9 = 0; i9 < length; i9++) {
            float f9 = fArr[i9];
            float f10 = fArr2[i9];
            float f11 = (this.a13 * f9) + (this.a23 * f10) + this.a33;
            fArr[i9] = (((this.a11 * f9) + (this.a21 * f10)) + this.a31) / f11;
            fArr2[i9] = (((this.a12 * f9) + (this.a22 * f10)) + this.a32) / f11;
        }
    }
}
