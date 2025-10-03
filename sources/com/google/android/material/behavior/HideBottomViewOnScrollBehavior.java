package com.google.android.material.behavior;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewPropertyAnimator;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.animation.AnimationUtils;

/* loaded from: classes2.dex */
public class HideBottomViewOnScrollBehavior<V extends View> extends CoordinatorLayout.AbstractC0304c<V> {
    protected static final int ENTER_ANIMATION_DURATION = 225;
    protected static final int EXIT_ANIMATION_DURATION = 175;
    private static final int STATE_SCROLLED_DOWN = 1;
    private static final int STATE_SCROLLED_UP = 2;
    private ViewPropertyAnimator currentAnimator;
    private int currentState;
    private int height;

    public HideBottomViewOnScrollBehavior() {
        this.height = 0;
        this.currentState = 2;
    }

    private void animateChildTo(V v8, int i9, long j9, TimeInterpolator timeInterpolator) {
        this.currentAnimator = v8.animate().translationY(i9).setInterpolator(timeInterpolator).setDuration(j9).setListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.behavior.HideBottomViewOnScrollBehavior.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                HideBottomViewOnScrollBehavior.this.currentAnimator = null;
            }
        });
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AbstractC0304c
    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, V v8, int i9) {
        this.height = v8.getMeasuredHeight();
        return super.onLayoutChild(coordinatorLayout, v8, i9);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AbstractC0304c
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, V v8, View view, int i9, int i10, int i11, int i12) {
        int i13 = this.currentState;
        if (i13 != 1 && i10 > 0) {
            slideDown(v8);
        } else {
            if (i13 == 2 || i10 >= 0) {
                return;
            }
            slideUp(v8);
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AbstractC0304c
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, V v8, View view, View view2, int i9) {
        return i9 == 2;
    }

    public void slideDown(V v8) {
        ViewPropertyAnimator viewPropertyAnimator = this.currentAnimator;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
            v8.clearAnimation();
        }
        this.currentState = 1;
        animateChildTo(v8, this.height, 175L, AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR);
    }

    public void slideUp(V v8) {
        ViewPropertyAnimator viewPropertyAnimator = this.currentAnimator;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
            v8.clearAnimation();
        }
        this.currentState = 2;
        animateChildTo(v8, 0, 225L, AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
    }

    public HideBottomViewOnScrollBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.height = 0;
        this.currentState = 2;
    }
}
