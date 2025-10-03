package com.google.zxing.common.detector;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* loaded from: classes2.dex */
public final class MathUtils {
    private MathUtils() {
    }

    public static float distance(float f9, float f10, float f11, float f12) {
        float f13 = f9 - f11;
        float f14 = f10 - f12;
        return (float) Math.sqrt((f13 * f13) + (f14 * f14));
    }

    public static int round(float f9) {
        return (int) (f9 + (f9 < BitmapDescriptorFactory.HUE_RED ? -0.5f : 0.5f));
    }

    public static float distance(int i9, int i10, int i11, int i12) {
        int i13 = i9 - i11;
        int i14 = i10 - i12;
        return (float) Math.sqrt((i13 * i13) + (i14 * i14));
    }
}
