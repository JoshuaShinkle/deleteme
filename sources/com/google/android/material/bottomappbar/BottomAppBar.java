package com.google.android.material.bottomappbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.customview.view.AbsSavedState;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.material.C3476R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapePathModel;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import p042d0.C4647u;
import p224w.C6494a;

/* loaded from: classes2.dex */
public class BottomAppBar extends Toolbar implements CoordinatorLayout.InterfaceC0303b {
    private static final long ANIMATION_DURATION = 300;
    public static final int FAB_ALIGNMENT_MODE_CENTER = 0;
    public static final int FAB_ALIGNMENT_MODE_END = 1;
    private Animator attachAnimator;
    private int fabAlignmentMode;
    AnimatorListenerAdapter fabAnimationListener;
    private boolean fabAttached;
    private final int fabOffsetEndMode;
    private boolean hideOnScroll;
    private final MaterialShapeDrawable materialShapeDrawable;
    private Animator menuAnimator;
    private Animator modeAnimator;
    private final BottomAppBarTopEdgeTreatment topEdgeTreatment;

    public static class Behavior extends HideBottomViewOnScrollBehavior<BottomAppBar> {
        private final Rect fabContentRect;

        public Behavior() {
            this.fabContentRect = new Rect();
        }

        private boolean updateFabPositionAndVisibility(FloatingActionButton floatingActionButton, BottomAppBar bottomAppBar) {
            ((CoordinatorLayout.C0307f) floatingActionButton.getLayoutParams()).f1724d = 17;
            bottomAppBar.addFabAnimationListeners(floatingActionButton);
            return true;
        }

        @Override // com.google.android.material.behavior.HideBottomViewOnScrollBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.AbstractC0304c
        public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, BottomAppBar bottomAppBar, int i9) {
            FloatingActionButton floatingActionButtonFindDependentFab = bottomAppBar.findDependentFab();
            if (floatingActionButtonFindDependentFab != null) {
                updateFabPositionAndVisibility(floatingActionButtonFindDependentFab, bottomAppBar);
                floatingActionButtonFindDependentFab.getMeasuredContentRect(this.fabContentRect);
                bottomAppBar.setFabDiameter(this.fabContentRect.height());
            }
            if (!bottomAppBar.isAnimationRunning()) {
                bottomAppBar.setCutoutState();
            }
            coordinatorLayout.onLayoutChild(bottomAppBar, i9);
            return super.onLayoutChild(coordinatorLayout, (CoordinatorLayout) bottomAppBar, i9);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AbstractC0304c
        public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, BottomAppBar bottomAppBar, View view, View view2, int i9, int i10) {
            return bottomAppBar.getHideOnScroll() && super.onStartNestedScroll(coordinatorLayout, (CoordinatorLayout) bottomAppBar, view, view2, i9, i10);
        }

        @Override // com.google.android.material.behavior.HideBottomViewOnScrollBehavior
        public void slideDown(BottomAppBar bottomAppBar) {
            super.slideDown((Behavior) bottomAppBar);
            FloatingActionButton floatingActionButtonFindDependentFab = bottomAppBar.findDependentFab();
            if (floatingActionButtonFindDependentFab != null) {
                floatingActionButtonFindDependentFab.getContentRect(this.fabContentRect);
                float measuredHeight = floatingActionButtonFindDependentFab.getMeasuredHeight() - this.fabContentRect.height();
                floatingActionButtonFindDependentFab.clearAnimation();
                floatingActionButtonFindDependentFab.animate().translationY((-floatingActionButtonFindDependentFab.getPaddingBottom()) + measuredHeight).setInterpolator(AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR).setDuration(175L);
            }
        }

        @Override // com.google.android.material.behavior.HideBottomViewOnScrollBehavior
        public void slideUp(BottomAppBar bottomAppBar) {
            super.slideUp((Behavior) bottomAppBar);
            FloatingActionButton floatingActionButtonFindDependentFab = bottomAppBar.findDependentFab();
            if (floatingActionButtonFindDependentFab != null) {
                floatingActionButtonFindDependentFab.clearAnimation();
                floatingActionButtonFindDependentFab.animate().translationY(bottomAppBar.getFabTranslationY()).setInterpolator(AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR).setDuration(225L);
            }
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.fabContentRect = new Rect();
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface FabAlignmentMode {
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: com.google.android.material.bottomappbar.BottomAppBar.SavedState.1
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i9) {
                return new SavedState[i9];
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.ClassLoaderCreator
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }
        };
        int fabAlignmentMode;
        boolean fabAttached;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i9) {
            super.writeToParcel(parcel, i9);
            parcel.writeInt(this.fabAlignmentMode);
            parcel.writeInt(this.fabAttached ? 1 : 0);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.fabAlignmentMode = parcel.readInt();
            this.fabAttached = parcel.readInt() != 0;
        }
    }

    public BottomAppBar(Context context) {
        this(context, null, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addFabAnimationListeners(FloatingActionButton floatingActionButton) {
        removeFabAnimationListeners(floatingActionButton);
        floatingActionButton.addOnHideAnimationListener(this.fabAnimationListener);
        floatingActionButton.addOnShowAnimationListener(this.fabAnimationListener);
    }

    private void cancelAnimations() {
        Animator animator = this.attachAnimator;
        if (animator != null) {
            animator.cancel();
        }
        Animator animator2 = this.menuAnimator;
        if (animator2 != null) {
            animator2.cancel();
        }
        Animator animator3 = this.modeAnimator;
        if (animator3 != null) {
            animator3.cancel();
        }
    }

    private void createCradleShapeAnimation(boolean z8, List<Animator> list) {
        if (z8) {
            this.topEdgeTreatment.setHorizontalOffset(getFabTranslationX());
        }
        float[] fArr = new float[2];
        fArr[0] = this.materialShapeDrawable.getInterpolation();
        fArr[1] = z8 ? 1.0f : BitmapDescriptorFactory.HUE_RED;
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(fArr);
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.bottomappbar.BottomAppBar.6
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                BottomAppBar.this.materialShapeDrawable.setInterpolation(((Float) valueAnimator.getAnimatedValue()).floatValue());
            }
        });
        valueAnimatorOfFloat.setDuration(ANIMATION_DURATION);
        list.add(valueAnimatorOfFloat);
    }

    private void createCradleTranslationAnimation(int i9, List<Animator> list) {
        if (this.fabAttached) {
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(this.topEdgeTreatment.getHorizontalOffset(), getFabTranslationX(i9));
            valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.bottomappbar.BottomAppBar.2
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    BottomAppBar.this.topEdgeTreatment.setHorizontalOffset(((Float) valueAnimator.getAnimatedValue()).floatValue());
                    BottomAppBar.this.materialShapeDrawable.invalidateSelf();
                }
            });
            valueAnimatorOfFloat.setDuration(ANIMATION_DURATION);
            list.add(valueAnimatorOfFloat);
        }
    }

    private void createFabTranslationXAnimation(int i9, List<Animator> list) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(findDependentFab(), "translationX", getFabTranslationX(i9));
        objectAnimatorOfFloat.setDuration(ANIMATION_DURATION);
        list.add(objectAnimatorOfFloat);
    }

    private void createFabTranslationYAnimation(boolean z8, List<Animator> list) {
        FloatingActionButton floatingActionButtonFindDependentFab = findDependentFab();
        if (floatingActionButtonFindDependentFab == null) {
            return;
        }
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(floatingActionButtonFindDependentFab, "translationY", getFabTranslationY(z8));
        objectAnimatorOfFloat.setDuration(ANIMATION_DURATION);
        list.add(objectAnimatorOfFloat);
    }

    private void createMenuViewTranslationAnimation(final int i9, final boolean z8, List<Animator> list) {
        final ActionMenuView actionMenuView = getActionMenuView();
        if (actionMenuView == null) {
            return;
        }
        Animator animatorOfFloat = ObjectAnimator.ofFloat(actionMenuView, "alpha", 1.0f);
        if ((!this.fabAttached && (!z8 || !isVisibleFab())) || (this.fabAlignmentMode != 1 && i9 != 1)) {
            if (actionMenuView.getAlpha() < 1.0f) {
                list.add(animatorOfFloat);
            }
        } else {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(actionMenuView, "alpha", BitmapDescriptorFactory.HUE_RED);
            objectAnimatorOfFloat.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.bottomappbar.BottomAppBar.4
                public boolean cancelled;

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animator) {
                    this.cancelled = true;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    if (this.cancelled) {
                        return;
                    }
                    BottomAppBar.this.translateActionMenuView(actionMenuView, i9, z8);
                }
            });
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(150L);
            animatorSet.playSequentially(objectAnimatorOfFloat, animatorOfFloat);
            list.add(animatorSet);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public FloatingActionButton findDependentFab() {
        if (!(getParent() instanceof CoordinatorLayout)) {
            return null;
        }
        for (View view : ((CoordinatorLayout) getParent()).getDependents(this)) {
            if (view instanceof FloatingActionButton) {
                return (FloatingActionButton) view;
            }
        }
        return null;
    }

    private ActionMenuView getActionMenuView() {
        for (int i9 = 0; i9 < getChildCount(); i9++) {
            View childAt = getChildAt(i9);
            if (childAt instanceof ActionMenuView) {
                return (ActionMenuView) childAt;
            }
        }
        return null;
    }

    private int getFabTranslationX(int i9) {
        boolean z8 = C4647u.m18567s(this) == 1;
        if (i9 == 1) {
            return ((getMeasuredWidth() / 2) - this.fabOffsetEndMode) * (z8 ? -1 : 1);
        }
        return 0;
    }

    private float getFabTranslationY(boolean z8) {
        FloatingActionButton floatingActionButtonFindDependentFab = findDependentFab();
        if (floatingActionButtonFindDependentFab == null) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        Rect rect = new Rect();
        floatingActionButtonFindDependentFab.getContentRect(rect);
        float fHeight = rect.height();
        if (fHeight == BitmapDescriptorFactory.HUE_RED) {
            fHeight = floatingActionButtonFindDependentFab.getMeasuredHeight();
        }
        float height = floatingActionButtonFindDependentFab.getHeight() - rect.bottom;
        float height2 = floatingActionButtonFindDependentFab.getHeight() - rect.height();
        float f9 = (-getCradleVerticalOffset()) + (fHeight / 2.0f) + height;
        float paddingBottom = height2 - floatingActionButtonFindDependentFab.getPaddingBottom();
        float f10 = -getMeasuredHeight();
        if (!z8) {
            f9 = paddingBottom;
        }
        return f10 + f9;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isAnimationRunning() {
        Animator animator;
        Animator animator2;
        Animator animator3 = this.attachAnimator;
        return (animator3 != null && animator3.isRunning()) || ((animator = this.menuAnimator) != null && animator.isRunning()) || ((animator2 = this.modeAnimator) != null && animator2.isRunning());
    }

    private boolean isVisibleFab() {
        FloatingActionButton floatingActionButtonFindDependentFab = findDependentFab();
        return floatingActionButtonFindDependentFab != null && floatingActionButtonFindDependentFab.isOrWillBeShown();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeAnimateAttachChange(boolean z8) {
        if (C4647u.m18513I(this)) {
            Animator animator = this.attachAnimator;
            if (animator != null) {
                animator.cancel();
            }
            ArrayList arrayList = new ArrayList();
            createCradleShapeAnimation(z8 && isVisibleFab(), arrayList);
            createFabTranslationYAnimation(z8, arrayList);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(arrayList);
            this.attachAnimator = animatorSet;
            animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.bottomappbar.BottomAppBar.5
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator2) {
                    BottomAppBar.this.attachAnimator = null;
                }
            });
            this.attachAnimator.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeAnimateMenuView(int i9, boolean z8) {
        if (C4647u.m18513I(this)) {
            Animator animator = this.menuAnimator;
            if (animator != null) {
                animator.cancel();
            }
            ArrayList arrayList = new ArrayList();
            if (!isVisibleFab()) {
                i9 = 0;
                z8 = false;
            }
            createMenuViewTranslationAnimation(i9, z8, arrayList);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(arrayList);
            this.menuAnimator = animatorSet;
            animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.bottomappbar.BottomAppBar.3
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator2) {
                    BottomAppBar.this.menuAnimator = null;
                }
            });
            this.menuAnimator.start();
        }
    }

    private void maybeAnimateModeChange(int i9) {
        if (this.fabAlignmentMode == i9 || !C4647u.m18513I(this)) {
            return;
        }
        Animator animator = this.modeAnimator;
        if (animator != null) {
            animator.cancel();
        }
        ArrayList arrayList = new ArrayList();
        createCradleTranslationAnimation(i9, arrayList);
        createFabTranslationXAnimation(i9, arrayList);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(arrayList);
        this.modeAnimator = animatorSet;
        animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.bottomappbar.BottomAppBar.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator2) {
                BottomAppBar.this.modeAnimator = null;
            }
        });
        this.modeAnimator.start();
    }

    private void removeFabAnimationListeners(FloatingActionButton floatingActionButton) {
        floatingActionButton.removeOnHideAnimationListener(this.fabAnimationListener);
        floatingActionButton.removeOnShowAnimationListener(this.fabAnimationListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCutoutState() {
        this.topEdgeTreatment.setHorizontalOffset(getFabTranslationX());
        FloatingActionButton floatingActionButtonFindDependentFab = findDependentFab();
        this.materialShapeDrawable.setInterpolation((this.fabAttached && isVisibleFab()) ? 1.0f : BitmapDescriptorFactory.HUE_RED);
        if (floatingActionButtonFindDependentFab != null) {
            floatingActionButtonFindDependentFab.setTranslationY(getFabTranslationY());
            floatingActionButtonFindDependentFab.setTranslationX(getFabTranslationX());
        }
        ActionMenuView actionMenuView = getActionMenuView();
        if (actionMenuView != null) {
            actionMenuView.setAlpha(1.0f);
            if (isVisibleFab()) {
                translateActionMenuView(actionMenuView, this.fabAlignmentMode, this.fabAttached);
            } else {
                translateActionMenuView(actionMenuView, 0, false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void translateActionMenuView(ActionMenuView actionMenuView, int i9, boolean z8) {
        boolean z9 = C4647u.m18567s(this) == 1;
        int iMax = 0;
        for (int i10 = 0; i10 < getChildCount(); i10++) {
            View childAt = getChildAt(i10);
            if ((childAt.getLayoutParams() instanceof Toolbar.C0214e) && (((Toolbar.C0214e) childAt.getLayoutParams()).f376a & 8388615) == 8388611) {
                iMax = Math.max(iMax, z9 ? childAt.getLeft() : childAt.getRight());
            }
        }
        actionMenuView.setTranslationX((i9 == 1 && z8) ? iMax - (z9 ? actionMenuView.getRight() : actionMenuView.getLeft()) : BitmapDescriptorFactory.HUE_RED);
    }

    public ColorStateList getBackgroundTint() {
        return this.materialShapeDrawable.getTintList();
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.InterfaceC0303b
    public CoordinatorLayout.AbstractC0304c<BottomAppBar> getBehavior() {
        return new Behavior();
    }

    public float getCradleVerticalOffset() {
        return this.topEdgeTreatment.getCradleVerticalOffset();
    }

    public int getFabAlignmentMode() {
        return this.fabAlignmentMode;
    }

    public float getFabCradleMargin() {
        return this.topEdgeTreatment.getFabCradleMargin();
    }

    public float getFabCradleRoundedCornerRadius() {
        return this.topEdgeTreatment.getFabCradleRoundedCornerRadius();
    }

    public boolean getHideOnScroll() {
        return this.hideOnScroll;
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z8, int i9, int i10, int i11, int i12) {
        super.onLayout(z8, i9, i10, i11, i12);
        cancelAnimations();
        setCutoutState();
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.fabAlignmentMode = savedState.fabAlignmentMode;
        this.fabAttached = savedState.fabAttached;
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.fabAlignmentMode = this.fabAlignmentMode;
        savedState.fabAttached = this.fabAttached;
        return savedState;
    }

    public void replaceMenu(int i9) {
        getMenu().clear();
        inflateMenu(i9);
    }

    public void setBackgroundTint(ColorStateList colorStateList) {
        C6494a.m24846i(this.materialShapeDrawable, colorStateList);
    }

    public void setCradleVerticalOffset(float f9) {
        if (f9 != getCradleVerticalOffset()) {
            this.topEdgeTreatment.setCradleVerticalOffset(f9);
            this.materialShapeDrawable.invalidateSelf();
        }
    }

    public void setFabAlignmentMode(int i9) {
        maybeAnimateModeChange(i9);
        maybeAnimateMenuView(i9, this.fabAttached);
        this.fabAlignmentMode = i9;
    }

    public void setFabCradleMargin(float f9) {
        if (f9 != getFabCradleMargin()) {
            this.topEdgeTreatment.setFabCradleMargin(f9);
            this.materialShapeDrawable.invalidateSelf();
        }
    }

    public void setFabCradleRoundedCornerRadius(float f9) {
        if (f9 != getFabCradleRoundedCornerRadius()) {
            this.topEdgeTreatment.setFabCradleRoundedCornerRadius(f9);
            this.materialShapeDrawable.invalidateSelf();
        }
    }

    public void setFabDiameter(int i9) {
        float f9 = i9;
        if (f9 != this.topEdgeTreatment.getFabDiameter()) {
            this.topEdgeTreatment.setFabDiameter(f9);
            this.materialShapeDrawable.invalidateSelf();
        }
    }

    public void setHideOnScroll(boolean z8) {
        this.hideOnScroll = z8;
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setSubtitle(CharSequence charSequence) {
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setTitle(CharSequence charSequence) {
    }

    public BottomAppBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C3476R.attr.bottomAppBarStyle);
    }

    public BottomAppBar(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        this.fabAttached = true;
        this.fabAnimationListener = new AnimatorListenerAdapter() { // from class: com.google.android.material.bottomappbar.BottomAppBar.7
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                BottomAppBar bottomAppBar = BottomAppBar.this;
                bottomAppBar.maybeAnimateAttachChange(bottomAppBar.fabAttached);
                BottomAppBar bottomAppBar2 = BottomAppBar.this;
                bottomAppBar2.maybeAnimateMenuView(bottomAppBar2.fabAlignmentMode, BottomAppBar.this.fabAttached);
            }
        };
        TypedArray typedArrayObtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context, attributeSet, C3476R.styleable.BottomAppBar, i9, C3476R.style.Widget_MaterialComponents_BottomAppBar, new int[0]);
        ColorStateList colorStateList = MaterialResources.getColorStateList(context, typedArrayObtainStyledAttributes, C3476R.styleable.BottomAppBar_backgroundTint);
        float dimensionPixelOffset = typedArrayObtainStyledAttributes.getDimensionPixelOffset(C3476R.styleable.BottomAppBar_fabCradleMargin, 0);
        float dimensionPixelOffset2 = typedArrayObtainStyledAttributes.getDimensionPixelOffset(C3476R.styleable.BottomAppBar_fabCradleRoundedCornerRadius, 0);
        float dimensionPixelOffset3 = typedArrayObtainStyledAttributes.getDimensionPixelOffset(C3476R.styleable.BottomAppBar_fabCradleVerticalOffset, 0);
        this.fabAlignmentMode = typedArrayObtainStyledAttributes.getInt(C3476R.styleable.BottomAppBar_fabAlignmentMode, 0);
        this.hideOnScroll = typedArrayObtainStyledAttributes.getBoolean(C3476R.styleable.BottomAppBar_hideOnScroll, false);
        typedArrayObtainStyledAttributes.recycle();
        this.fabOffsetEndMode = getResources().getDimensionPixelOffset(C3476R.dimen.mtrl_bottomappbar_fabOffsetEndMode);
        BottomAppBarTopEdgeTreatment bottomAppBarTopEdgeTreatment = new BottomAppBarTopEdgeTreatment(dimensionPixelOffset, dimensionPixelOffset2, dimensionPixelOffset3);
        this.topEdgeTreatment = bottomAppBarTopEdgeTreatment;
        ShapePathModel shapePathModel = new ShapePathModel();
        shapePathModel.setTopEdge(bottomAppBarTopEdgeTreatment);
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(shapePathModel);
        this.materialShapeDrawable = materialShapeDrawable;
        materialShapeDrawable.setShadowEnabled(true);
        materialShapeDrawable.setPaintStyle(Paint.Style.FILL);
        C6494a.m24846i(materialShapeDrawable, colorStateList);
        C4647u.m18534b0(this, materialShapeDrawable);
    }

    private float getFabTranslationX() {
        return getFabTranslationX(this.fabAlignmentMode);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getFabTranslationY() {
        return getFabTranslationY(this.fabAttached);
    }
}
