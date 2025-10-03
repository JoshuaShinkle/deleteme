package com.google.android.material.math;

/* loaded from: classes2.dex */
public final class MathUtils {
    public static final float DEFAULT_EPSILON = 1.0E-4f;

    private MathUtils() {
    }

    public static float dist(float f9, float f10, float f11, float f12) {
        return (float) Math.hypot(f11 - f9, f12 - f10);
    }

    public static float distanceToFurthestCorner(float f9, float f10, float f11, float f12, float f13, float f14) {
        return max(dist(f9, f10, f11, f12), dist(f9, f10, f13, f12), dist(f9, f10, f13, f14), dist(f9, f10, f11, f14));
    }

    public static boolean geq(float f9, float f10, float f11) {
        return f9 + f11 >= f10;
    }

    public static float lerp(float f9, float f10, float f11) {
        return ((1.0f - f11) * f9) + (f11 * f10);
    }

    private static float max(float f9, float f10, float f11, float f12) {
        return (f9 <= f10 || f9 <= f11 || f9 <= f12) ? (f10 <= f11 || f10 <= f12) ? f11 > f12 ? f11 : f12 : f10 : f9;
    }
}
