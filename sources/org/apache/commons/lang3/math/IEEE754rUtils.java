package org.apache.commons.lang3.math;

/* loaded from: classes.dex */
public class IEEE754rUtils {
    public static double max(double[] dArr) {
        if (dArr == null) {
            throw new IllegalArgumentException("The Array must not be null");
        }
        if (dArr.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
        double dMax = dArr[0];
        for (int i9 = 1; i9 < dArr.length; i9++) {
            dMax = max(dArr[i9], dMax);
        }
        return dMax;
    }

    public static double min(double[] dArr) {
        if (dArr == null) {
            throw new IllegalArgumentException("The Array must not be null");
        }
        if (dArr.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
        double dMin = dArr[0];
        for (int i9 = 1; i9 < dArr.length; i9++) {
            dMin = min(dArr[i9], dMin);
        }
        return dMin;
    }

    public static float max(float[] fArr) {
        if (fArr != null) {
            if (fArr.length != 0) {
                float fMax = fArr[0];
                for (int i9 = 1; i9 < fArr.length; i9++) {
                    fMax = max(fArr[i9], fMax);
                }
                return fMax;
            }
            throw new IllegalArgumentException("Array cannot be empty.");
        }
        throw new IllegalArgumentException("The Array must not be null");
    }

    public static float min(float[] fArr) {
        if (fArr != null) {
            if (fArr.length != 0) {
                float fMin = fArr[0];
                for (int i9 = 1; i9 < fArr.length; i9++) {
                    fMin = min(fArr[i9], fMin);
                }
                return fMin;
            }
            throw new IllegalArgumentException("Array cannot be empty.");
        }
        throw new IllegalArgumentException("The Array must not be null");
    }

    public static double max(double d9, double d10, double d11) {
        return max(max(d9, d10), d11);
    }

    public static double min(double d9, double d10, double d11) {
        return min(min(d9, d10), d11);
    }

    public static double max(double d9, double d10) {
        return Double.isNaN(d9) ? d10 : Double.isNaN(d10) ? d9 : Math.max(d9, d10);
    }

    public static double min(double d9, double d10) {
        return Double.isNaN(d9) ? d10 : Double.isNaN(d10) ? d9 : Math.min(d9, d10);
    }

    public static float max(float f9, float f10, float f11) {
        return max(max(f9, f10), f11);
    }

    public static float min(float f9, float f10, float f11) {
        return min(min(f9, f10), f11);
    }

    public static float max(float f9, float f10) {
        return Float.isNaN(f9) ? f10 : Float.isNaN(f10) ? f9 : Math.max(f9, f10);
    }

    public static float min(float f9, float f10) {
        return Float.isNaN(f9) ? f10 : Float.isNaN(f10) ? f9 : Math.min(f9, f10);
    }
}
