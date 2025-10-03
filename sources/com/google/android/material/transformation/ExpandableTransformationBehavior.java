package com.google.android.material.transformation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/* loaded from: classes2.dex */
public abstract class ExpandableTransformationBehavior extends ExpandableBehavior {
    private AnimatorSet currentAnimation;

    public ExpandableTransformationBehavior() {
    }

    public abstract AnimatorSet onCreateExpandedStateChangeAnimation(View view, View view2, boolean z8, boolean z9);

    @Override // com.google.android.material.transformation.ExpandableBehavior
    public boolean onExpandedStateChange(View view, View view2, boolean z8, boolean z9) {
        AnimatorSet animatorSet = this.currentAnimation;
        boolean z10 = animatorSet != null;
        if (z10) {
            animatorSet.cancel();
        }
        AnimatorSet animatorSetOnCreateExpandedStateChangeAnimation = onCreateExpandedStateChangeAnimation(view, view2, z8, z10);
        this.currentAnimation = animatorSetOnCreateExpandedStateChangeAnimation;
        animatorSetOnCreateExpandedStateChangeAnimation.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.transformation.ExpandableTransformationBehavior.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                ExpandableTransformationBehavior.this.currentAnimation = null;
            }
        });
        this.currentAnimation.start();
        if (!z9) {
            this.currentAnimation.end();
        }
        return true;
    }

    public ExpandableTransformationBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
