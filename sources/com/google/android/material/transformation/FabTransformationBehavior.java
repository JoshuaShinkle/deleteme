package com.google.android.material.transformation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.material.C3476R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.animation.AnimatorSetCompat;
import com.google.android.material.animation.ArgbEvaluatorCompat;
import com.google.android.material.animation.ChildrenAlphaProperty;
import com.google.android.material.animation.DrawableAlphaProperty;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.animation.MotionTiming;
import com.google.android.material.animation.Positioning;
import com.google.android.material.circularreveal.CircularRevealCompat;
import com.google.android.material.circularreveal.CircularRevealHelper;
import com.google.android.material.circularreveal.CircularRevealWidget;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.math.MathUtils;
import java.util.ArrayList;
import java.util.List;
import p042d0.C4647u;

/* loaded from: classes2.dex */
public abstract class FabTransformationBehavior extends ExpandableTransformationBehavior {
    private final int[] tmpArray;
    private final Rect tmpRect;
    private final RectF tmpRectF1;
    private final RectF tmpRectF2;

    public static class FabTransformationSpec {
        public Positioning positioning;
        public MotionSpec timings;
    }

    public FabTransformationBehavior() {
        this.tmpRect = new Rect();
        this.tmpRectF1 = new RectF();
        this.tmpRectF2 = new RectF();
        this.tmpArray = new int[2];
    }

    private ViewGroup calculateChildContentContainer(View view) {
        View viewFindViewById = view.findViewById(C3476R.id.mtrl_child_content_container);
        return viewFindViewById != null ? toViewGroupOrNull(viewFindViewById) : ((view instanceof TransformationChildLayout) || (view instanceof TransformationChildCard)) ? toViewGroupOrNull(((ViewGroup) view).getChildAt(0)) : toViewGroupOrNull(view);
    }

    private void calculateChildVisibleBoundsAtEndOfExpansion(View view, FabTransformationSpec fabTransformationSpec, MotionTiming motionTiming, MotionTiming motionTiming2, float f9, float f10, float f11, float f12, RectF rectF) {
        float fCalculateValueOfAnimationAtEndOfExpansion = calculateValueOfAnimationAtEndOfExpansion(fabTransformationSpec, motionTiming, f9, f11);
        float fCalculateValueOfAnimationAtEndOfExpansion2 = calculateValueOfAnimationAtEndOfExpansion(fabTransformationSpec, motionTiming2, f10, f12);
        Rect rect = this.tmpRect;
        view.getWindowVisibleDisplayFrame(rect);
        RectF rectF2 = this.tmpRectF1;
        rectF2.set(rect);
        RectF rectF3 = this.tmpRectF2;
        calculateWindowBounds(view, rectF3);
        rectF3.offset(fCalculateValueOfAnimationAtEndOfExpansion, fCalculateValueOfAnimationAtEndOfExpansion2);
        rectF3.intersect(rectF2);
        rectF.set(rectF3);
    }

    private float calculateRevealCenterX(View view, View view2, Positioning positioning) {
        RectF rectF = this.tmpRectF1;
        RectF rectF2 = this.tmpRectF2;
        calculateWindowBounds(view, rectF);
        calculateWindowBounds(view2, rectF2);
        rectF2.offset(-calculateTranslationX(view, view2, positioning), BitmapDescriptorFactory.HUE_RED);
        return rectF.centerX() - rectF2.left;
    }

    private float calculateRevealCenterY(View view, View view2, Positioning positioning) {
        RectF rectF = this.tmpRectF1;
        RectF rectF2 = this.tmpRectF2;
        calculateWindowBounds(view, rectF);
        calculateWindowBounds(view2, rectF2);
        rectF2.offset(BitmapDescriptorFactory.HUE_RED, -calculateTranslationY(view, view2, positioning));
        return rectF.centerY() - rectF2.top;
    }

    private float calculateTranslationX(View view, View view2, Positioning positioning) {
        float fCenterX;
        float fCenterX2;
        float f9;
        RectF rectF = this.tmpRectF1;
        RectF rectF2 = this.tmpRectF2;
        calculateWindowBounds(view, rectF);
        calculateWindowBounds(view2, rectF2);
        int i9 = positioning.gravity & 7;
        if (i9 == 1) {
            fCenterX = rectF2.centerX();
            fCenterX2 = rectF.centerX();
        } else if (i9 == 3) {
            fCenterX = rectF2.left;
            fCenterX2 = rectF.left;
        } else {
            if (i9 != 5) {
                f9 = BitmapDescriptorFactory.HUE_RED;
                return f9 + positioning.xAdjustment;
            }
            fCenterX = rectF2.right;
            fCenterX2 = rectF.right;
        }
        f9 = fCenterX - fCenterX2;
        return f9 + positioning.xAdjustment;
    }

    private float calculateTranslationY(View view, View view2, Positioning positioning) {
        float fCenterY;
        float fCenterY2;
        float f9;
        RectF rectF = this.tmpRectF1;
        RectF rectF2 = this.tmpRectF2;
        calculateWindowBounds(view, rectF);
        calculateWindowBounds(view2, rectF2);
        int i9 = positioning.gravity & 112;
        if (i9 == 16) {
            fCenterY = rectF2.centerY();
            fCenterY2 = rectF.centerY();
        } else if (i9 == 48) {
            fCenterY = rectF2.top;
            fCenterY2 = rectF.top;
        } else {
            if (i9 != 80) {
                f9 = BitmapDescriptorFactory.HUE_RED;
                return f9 + positioning.yAdjustment;
            }
            fCenterY = rectF2.bottom;
            fCenterY2 = rectF.bottom;
        }
        f9 = fCenterY - fCenterY2;
        return f9 + positioning.yAdjustment;
    }

    private float calculateValueOfAnimationAtEndOfExpansion(FabTransformationSpec fabTransformationSpec, MotionTiming motionTiming, float f9, float f10) {
        long delay = motionTiming.getDelay();
        long duration = motionTiming.getDuration();
        MotionTiming timing = fabTransformationSpec.timings.getTiming("expansion");
        return AnimationUtils.lerp(f9, f10, motionTiming.getInterpolator().getInterpolation((((timing.getDelay() + timing.getDuration()) + 17) - delay) / duration));
    }

    private void calculateWindowBounds(View view, RectF rectF) {
        rectF.set(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, view.getWidth(), view.getHeight());
        view.getLocationInWindow(this.tmpArray);
        rectF.offsetTo(r0[0], r0[1]);
        rectF.offset((int) (-view.getTranslationX()), (int) (-view.getTranslationY()));
    }

    private void createChildrenFadeAnimation(View view, View view2, boolean z8, boolean z9, FabTransformationSpec fabTransformationSpec, List<Animator> list, List<Animator.AnimatorListener> list2) {
        ViewGroup viewGroupCalculateChildContentContainer;
        ObjectAnimator objectAnimatorOfFloat;
        if (view2 instanceof ViewGroup) {
            if (((view2 instanceof CircularRevealWidget) && CircularRevealHelper.STRATEGY == 0) || (viewGroupCalculateChildContentContainer = calculateChildContentContainer(view2)) == null) {
                return;
            }
            if (z8) {
                if (!z9) {
                    ChildrenAlphaProperty.CHILDREN_ALPHA.set(viewGroupCalculateChildContentContainer, Float.valueOf(BitmapDescriptorFactory.HUE_RED));
                }
                objectAnimatorOfFloat = ObjectAnimator.ofFloat(viewGroupCalculateChildContentContainer, ChildrenAlphaProperty.CHILDREN_ALPHA, 1.0f);
            } else {
                objectAnimatorOfFloat = ObjectAnimator.ofFloat(viewGroupCalculateChildContentContainer, ChildrenAlphaProperty.CHILDREN_ALPHA, BitmapDescriptorFactory.HUE_RED);
            }
            fabTransformationSpec.timings.getTiming("contentFade").apply(objectAnimatorOfFloat);
            list.add(objectAnimatorOfFloat);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void createColorAnimation(View view, View view2, boolean z8, boolean z9, FabTransformationSpec fabTransformationSpec, List<Animator> list, List<Animator.AnimatorListener> list2) {
        ObjectAnimator objectAnimatorOfInt;
        if (view2 instanceof CircularRevealWidget) {
            CircularRevealWidget circularRevealWidget = (CircularRevealWidget) view2;
            int backgroundTint = getBackgroundTint(view);
            int i9 = 16777215 & backgroundTint;
            if (z8) {
                if (!z9) {
                    circularRevealWidget.setCircularRevealScrimColor(backgroundTint);
                }
                objectAnimatorOfInt = ObjectAnimator.ofInt(circularRevealWidget, CircularRevealWidget.CircularRevealScrimColorProperty.CIRCULAR_REVEAL_SCRIM_COLOR, i9);
            } else {
                objectAnimatorOfInt = ObjectAnimator.ofInt(circularRevealWidget, CircularRevealWidget.CircularRevealScrimColorProperty.CIRCULAR_REVEAL_SCRIM_COLOR, backgroundTint);
            }
            objectAnimatorOfInt.setEvaluator(ArgbEvaluatorCompat.getInstance());
            fabTransformationSpec.timings.getTiming(TtmlNode.ATTR_TTS_COLOR).apply(objectAnimatorOfInt);
            list.add(objectAnimatorOfInt);
        }
    }

    @TargetApi(21)
    private void createElevationAnimation(View view, View view2, boolean z8, boolean z9, FabTransformationSpec fabTransformationSpec, List<Animator> list, List<Animator.AnimatorListener> list2) {
        ObjectAnimator objectAnimatorOfFloat;
        float fM18559o = C4647u.m18559o(view2) - C4647u.m18559o(view);
        if (z8) {
            if (!z9) {
                view2.setTranslationZ(-fM18559o);
            }
            objectAnimatorOfFloat = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_Z, BitmapDescriptorFactory.HUE_RED);
        } else {
            objectAnimatorOfFloat = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_Z, -fM18559o);
        }
        fabTransformationSpec.timings.getTiming("elevation").apply(objectAnimatorOfFloat);
        list.add(objectAnimatorOfFloat);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void createExpansionAnimation(View view, View view2, boolean z8, boolean z9, FabTransformationSpec fabTransformationSpec, float f9, float f10, List<Animator> list, List<Animator.AnimatorListener> list2) {
        Animator animatorCreateCircularReveal;
        if (view2 instanceof CircularRevealWidget) {
            final CircularRevealWidget circularRevealWidget = (CircularRevealWidget) view2;
            float fCalculateRevealCenterX = calculateRevealCenterX(view, view2, fabTransformationSpec.positioning);
            float fCalculateRevealCenterY = calculateRevealCenterY(view, view2, fabTransformationSpec.positioning);
            ((FloatingActionButton) view).getContentRect(this.tmpRect);
            float fWidth = this.tmpRect.width() / 2.0f;
            MotionTiming timing = fabTransformationSpec.timings.getTiming("expansion");
            if (z8) {
                if (!z9) {
                    circularRevealWidget.setRevealInfo(new CircularRevealWidget.RevealInfo(fCalculateRevealCenterX, fCalculateRevealCenterY, fWidth));
                }
                if (z9) {
                    fWidth = circularRevealWidget.getRevealInfo().radius;
                }
                animatorCreateCircularReveal = CircularRevealCompat.createCircularReveal(circularRevealWidget, fCalculateRevealCenterX, fCalculateRevealCenterY, MathUtils.distanceToFurthestCorner(fCalculateRevealCenterX, fCalculateRevealCenterY, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, f9, f10));
                animatorCreateCircularReveal.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.transformation.FabTransformationBehavior.4
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(Animator animator) {
                        CircularRevealWidget.RevealInfo revealInfo = circularRevealWidget.getRevealInfo();
                        revealInfo.radius = Float.MAX_VALUE;
                        circularRevealWidget.setRevealInfo(revealInfo);
                    }
                });
                createPreFillRadialExpansion(view2, timing.getDelay(), (int) fCalculateRevealCenterX, (int) fCalculateRevealCenterY, fWidth, list);
            } else {
                float f11 = circularRevealWidget.getRevealInfo().radius;
                Animator animatorCreateCircularReveal2 = CircularRevealCompat.createCircularReveal(circularRevealWidget, fCalculateRevealCenterX, fCalculateRevealCenterY, fWidth);
                int i9 = (int) fCalculateRevealCenterX;
                int i10 = (int) fCalculateRevealCenterY;
                createPreFillRadialExpansion(view2, timing.getDelay(), i9, i10, f11, list);
                createPostFillRadialExpansion(view2, timing.getDelay(), timing.getDuration(), fabTransformationSpec.timings.getTotalDuration(), i9, i10, fWidth, list);
                animatorCreateCircularReveal = animatorCreateCircularReveal2;
            }
            timing.apply(animatorCreateCircularReveal);
            list.add(animatorCreateCircularReveal);
            list2.add(CircularRevealCompat.createCircularRevealListener(circularRevealWidget));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void createIconFadeAnimation(View view, final View view2, boolean z8, boolean z9, FabTransformationSpec fabTransformationSpec, List<Animator> list, List<Animator.AnimatorListener> list2) {
        ObjectAnimator objectAnimatorOfInt;
        if ((view2 instanceof CircularRevealWidget) && (view instanceof ImageView)) {
            final CircularRevealWidget circularRevealWidget = (CircularRevealWidget) view2;
            final Drawable drawable = ((ImageView) view).getDrawable();
            if (drawable == null) {
                return;
            }
            drawable.mutate();
            if (z8) {
                if (!z9) {
                    drawable.setAlpha(255);
                }
                objectAnimatorOfInt = ObjectAnimator.ofInt(drawable, DrawableAlphaProperty.DRAWABLE_ALPHA_COMPAT, 0);
            } else {
                objectAnimatorOfInt = ObjectAnimator.ofInt(drawable, DrawableAlphaProperty.DRAWABLE_ALPHA_COMPAT, 255);
            }
            objectAnimatorOfInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.transformation.FabTransformationBehavior.2
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    view2.invalidate();
                }
            });
            fabTransformationSpec.timings.getTiming("iconFade").apply(objectAnimatorOfInt);
            list.add(objectAnimatorOfInt);
            list2.add(new AnimatorListenerAdapter() { // from class: com.google.android.material.transformation.FabTransformationBehavior.3
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    circularRevealWidget.setCircularRevealOverlayDrawable(null);
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                    circularRevealWidget.setCircularRevealOverlayDrawable(drawable);
                }
            });
        }
    }

    private void createPostFillRadialExpansion(View view, long j9, long j10, long j11, int i9, int i10, float f9, List<Animator> list) {
        long j12 = j9 + j10;
        if (j12 < j11) {
            Animator animatorCreateCircularReveal = ViewAnimationUtils.createCircularReveal(view, i9, i10, f9, f9);
            animatorCreateCircularReveal.setStartDelay(j12);
            animatorCreateCircularReveal.setDuration(j11 - j12);
            list.add(animatorCreateCircularReveal);
        }
    }

    private void createPreFillRadialExpansion(View view, long j9, int i9, int i10, float f9, List<Animator> list) {
        if (j9 > 0) {
            Animator animatorCreateCircularReveal = ViewAnimationUtils.createCircularReveal(view, i9, i10, f9, f9);
            animatorCreateCircularReveal.setStartDelay(0L);
            animatorCreateCircularReveal.setDuration(j9);
            list.add(animatorCreateCircularReveal);
        }
    }

    private void createTranslationAnimation(View view, View view2, boolean z8, boolean z9, FabTransformationSpec fabTransformationSpec, List<Animator> list, List<Animator.AnimatorListener> list2, RectF rectF) {
        MotionTiming timing;
        MotionTiming timing2;
        ObjectAnimator objectAnimatorOfFloat;
        ObjectAnimator objectAnimatorOfFloat2;
        float fCalculateTranslationX = calculateTranslationX(view, view2, fabTransformationSpec.positioning);
        float fCalculateTranslationY = calculateTranslationY(view, view2, fabTransformationSpec.positioning);
        if (fCalculateTranslationX == BitmapDescriptorFactory.HUE_RED || fCalculateTranslationY == BitmapDescriptorFactory.HUE_RED) {
            timing = fabTransformationSpec.timings.getTiming("translationXLinear");
            timing2 = fabTransformationSpec.timings.getTiming("translationYLinear");
        } else if ((!z8 || fCalculateTranslationY >= BitmapDescriptorFactory.HUE_RED) && (z8 || fCalculateTranslationY <= BitmapDescriptorFactory.HUE_RED)) {
            timing = fabTransformationSpec.timings.getTiming("translationXCurveDownwards");
            timing2 = fabTransformationSpec.timings.getTiming("translationYCurveDownwards");
        } else {
            timing = fabTransformationSpec.timings.getTiming("translationXCurveUpwards");
            timing2 = fabTransformationSpec.timings.getTiming("translationYCurveUpwards");
        }
        MotionTiming motionTiming = timing;
        MotionTiming motionTiming2 = timing2;
        if (z8) {
            if (!z9) {
                view2.setTranslationX(-fCalculateTranslationX);
                view2.setTranslationY(-fCalculateTranslationY);
            }
            objectAnimatorOfFloat = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_X, BitmapDescriptorFactory.HUE_RED);
            objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_Y, BitmapDescriptorFactory.HUE_RED);
            calculateChildVisibleBoundsAtEndOfExpansion(view2, fabTransformationSpec, motionTiming, motionTiming2, -fCalculateTranslationX, -fCalculateTranslationY, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, rectF);
        } else {
            objectAnimatorOfFloat = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_X, -fCalculateTranslationX);
            objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_Y, -fCalculateTranslationY);
        }
        motionTiming.apply(objectAnimatorOfFloat);
        motionTiming2.apply(objectAnimatorOfFloat2);
        list.add(objectAnimatorOfFloat);
        list.add(objectAnimatorOfFloat2);
    }

    private int getBackgroundTint(View view) {
        ColorStateList colorStateListM18551k = C4647u.m18551k(view);
        if (colorStateListM18551k != null) {
            return colorStateListM18551k.getColorForState(view.getDrawableState(), colorStateListM18551k.getDefaultColor());
        }
        return 0;
    }

    private ViewGroup toViewGroupOrNull(View view) {
        if (view instanceof ViewGroup) {
            return (ViewGroup) view;
        }
        return null;
    }

    @Override // com.google.android.material.transformation.ExpandableBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.AbstractC0304c
    public boolean layoutDependsOn(CoordinatorLayout coordinatorLayout, View view, View view2) {
        if (view.getVisibility() == 8) {
            throw new IllegalStateException("This behavior cannot be attached to a GONE view. Set the view to INVISIBLE instead.");
        }
        if (!(view2 instanceof FloatingActionButton)) {
            return false;
        }
        int expandedComponentIdHint = ((FloatingActionButton) view2).getExpandedComponentIdHint();
        return expandedComponentIdHint == 0 || expandedComponentIdHint == view.getId();
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AbstractC0304c
    public void onAttachedToLayoutParams(CoordinatorLayout.C0307f c0307f) {
        if (c0307f.f1728h == 0) {
            c0307f.f1728h = 80;
        }
    }

    @Override // com.google.android.material.transformation.ExpandableTransformationBehavior
    public AnimatorSet onCreateExpandedStateChangeAnimation(final View view, final View view2, final boolean z8, boolean z9) {
        FabTransformationSpec fabTransformationSpecOnCreateMotionSpec = onCreateMotionSpec(view2.getContext(), z8);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        createElevationAnimation(view, view2, z8, z9, fabTransformationSpecOnCreateMotionSpec, arrayList, arrayList2);
        RectF rectF = this.tmpRectF1;
        createTranslationAnimation(view, view2, z8, z9, fabTransformationSpecOnCreateMotionSpec, arrayList, arrayList2, rectF);
        float fWidth = rectF.width();
        float fHeight = rectF.height();
        createIconFadeAnimation(view, view2, z8, z9, fabTransformationSpecOnCreateMotionSpec, arrayList, arrayList2);
        createExpansionAnimation(view, view2, z8, z9, fabTransformationSpecOnCreateMotionSpec, fWidth, fHeight, arrayList, arrayList2);
        createColorAnimation(view, view2, z8, z9, fabTransformationSpecOnCreateMotionSpec, arrayList, arrayList2);
        createChildrenFadeAnimation(view, view2, z8, z9, fabTransformationSpecOnCreateMotionSpec, arrayList, arrayList2);
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSetCompat.playTogether(animatorSet, arrayList);
        animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.transformation.FabTransformationBehavior.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (z8) {
                    return;
                }
                view2.setVisibility(4);
                view.setAlpha(1.0f);
                view.setVisibility(0);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                if (z8) {
                    view2.setVisibility(0);
                    view.setAlpha(BitmapDescriptorFactory.HUE_RED);
                    view.setVisibility(4);
                }
            }
        });
        int size = arrayList2.size();
        for (int i9 = 0; i9 < size; i9++) {
            animatorSet.addListener(arrayList2.get(i9));
        }
        return animatorSet;
    }

    public abstract FabTransformationSpec onCreateMotionSpec(Context context, boolean z8);

    public FabTransformationBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.tmpRect = new Rect();
        this.tmpRectF1 = new RectF();
        this.tmpRectF2 = new RectF();
        this.tmpArray = new int[2];
    }
}
