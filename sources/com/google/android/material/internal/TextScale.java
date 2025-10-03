package com.google.android.material.internal;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.transition.AbstractC0532m;
import androidx.transition.C0539t;
import java.util.Map;

/* loaded from: classes2.dex */
public class TextScale extends AbstractC0532m {
    private static final String PROPNAME_SCALE = "android:textscale:scale";

    private void captureValues(C0539t c0539t) {
        View view = c0539t.f2966b;
        if (view instanceof TextView) {
            c0539t.f2965a.put(PROPNAME_SCALE, Float.valueOf(((TextView) view).getScaleX()));
        }
    }

    @Override // androidx.transition.AbstractC0532m
    public void captureEndValues(C0539t c0539t) {
        captureValues(c0539t);
    }

    @Override // androidx.transition.AbstractC0532m
    public void captureStartValues(C0539t c0539t) {
        captureValues(c0539t);
    }

    @Override // androidx.transition.AbstractC0532m
    public Animator createAnimator(ViewGroup viewGroup, C0539t c0539t, C0539t c0539t2) {
        if (c0539t == null || c0539t2 == null || !(c0539t.f2966b instanceof TextView)) {
            return null;
        }
        View view = c0539t2.f2966b;
        if (!(view instanceof TextView)) {
            return null;
        }
        final TextView textView = (TextView) view;
        Map<String, Object> map = c0539t.f2965a;
        Map<String, Object> map2 = c0539t2.f2965a;
        float fFloatValue = map.get(PROPNAME_SCALE) != null ? ((Float) map.get(PROPNAME_SCALE)).floatValue() : 1.0f;
        float fFloatValue2 = map2.get(PROPNAME_SCALE) != null ? ((Float) map2.get(PROPNAME_SCALE)).floatValue() : 1.0f;
        if (fFloatValue == fFloatValue2) {
            return null;
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(fFloatValue, fFloatValue2);
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.internal.TextScale.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float fFloatValue3 = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                textView.setScaleX(fFloatValue3);
                textView.setScaleY(fFloatValue3);
            }
        });
        return valueAnimatorOfFloat;
    }
}
