package com.google.android.material.animation;

import android.animation.TimeInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import p092i0.C5038a;
import p092i0.C5039b;
import p092i0.C5040c;

/* loaded from: classes2.dex */
public class AnimationUtils {
    public static final TimeInterpolator LINEAR_INTERPOLATOR = new LinearInterpolator();
    public static final TimeInterpolator FAST_OUT_SLOW_IN_INTERPOLATOR = new C5039b();
    public static final TimeInterpolator FAST_OUT_LINEAR_IN_INTERPOLATOR = new C5038a();
    public static final TimeInterpolator LINEAR_OUT_SLOW_IN_INTERPOLATOR = new C5040c();
    public static final TimeInterpolator DECELERATE_INTERPOLATOR = new DecelerateInterpolator();

    public static float lerp(float f9, float f10, float f11) {
        return f9 + (f11 * (f10 - f9));
    }

    public static int lerp(int i9, int i10, float f9) {
        return i9 + Math.round(f9 * (i10 - i9));
    }
}
