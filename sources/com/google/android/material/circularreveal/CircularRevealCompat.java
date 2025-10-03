package com.google.android.material.circularreveal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.util.Property;
import android.view.View;
import android.view.ViewAnimationUtils;
import com.google.android.material.circularreveal.CircularRevealWidget;

/* loaded from: classes2.dex */
public final class CircularRevealCompat {
    private CircularRevealCompat() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Animator createCircularReveal(CircularRevealWidget circularRevealWidget, float f9, float f10, float f11) {
        ObjectAnimator objectAnimatorOfObject = ObjectAnimator.ofObject(circularRevealWidget, (Property<CircularRevealWidget, V>) CircularRevealWidget.CircularRevealProperty.CIRCULAR_REVEAL, (TypeEvaluator) CircularRevealWidget.CircularRevealEvaluator.CIRCULAR_REVEAL, (Object[]) new CircularRevealWidget.RevealInfo[]{new CircularRevealWidget.RevealInfo(f9, f10, f11)});
        CircularRevealWidget.RevealInfo revealInfo = circularRevealWidget.getRevealInfo();
        if (revealInfo == null) {
            throw new IllegalStateException("Caller must set a non-null RevealInfo before calling this.");
        }
        Animator animatorCreateCircularReveal = ViewAnimationUtils.createCircularReveal((View) circularRevealWidget, (int) f9, (int) f10, revealInfo.radius, f11);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimatorOfObject, animatorCreateCircularReveal);
        return animatorSet;
    }

    public static Animator.AnimatorListener createCircularRevealListener(final CircularRevealWidget circularRevealWidget) {
        return new AnimatorListenerAdapter() { // from class: com.google.android.material.circularreveal.CircularRevealCompat.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                circularRevealWidget.destroyCircularRevealCache();
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                circularRevealWidget.buildCircularRevealCache();
            }
        };
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Animator createCircularReveal(CircularRevealWidget circularRevealWidget, float f9, float f10, float f11, float f12) {
        ObjectAnimator objectAnimatorOfObject = ObjectAnimator.ofObject(circularRevealWidget, (Property<CircularRevealWidget, V>) CircularRevealWidget.CircularRevealProperty.CIRCULAR_REVEAL, (TypeEvaluator) CircularRevealWidget.CircularRevealEvaluator.CIRCULAR_REVEAL, (Object[]) new CircularRevealWidget.RevealInfo[]{new CircularRevealWidget.RevealInfo(f9, f10, f11), new CircularRevealWidget.RevealInfo(f9, f10, f12)});
        Animator animatorCreateCircularReveal = ViewAnimationUtils.createCircularReveal((View) circularRevealWidget, (int) f9, (int) f10, f11, f12);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimatorOfObject, animatorCreateCircularReveal);
        return animatorSet;
    }
}
