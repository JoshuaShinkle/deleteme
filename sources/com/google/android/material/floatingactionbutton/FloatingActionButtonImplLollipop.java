package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.util.Property;
import android.view.View;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.material.internal.CircularBorderDrawable;
import com.google.android.material.internal.CircularBorderDrawableLollipop;
import com.google.android.material.internal.VisibilityAwareImageButton;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shadow.ShadowDrawableWrapper;
import com.google.android.material.shadow.ShadowViewDelegate;
import java.util.ArrayList;
import p224w.C6494a;

/* loaded from: classes2.dex */
class FloatingActionButtonImplLollipop extends FloatingActionButtonImpl {
    private InsetDrawable insetDrawable;

    public static class AlwaysStatefulGradientDrawable extends GradientDrawable {
        @Override // android.graphics.drawable.GradientDrawable, android.graphics.drawable.Drawable
        public boolean isStateful() {
            return true;
        }
    }

    public FloatingActionButtonImplLollipop(VisibilityAwareImageButton visibilityAwareImageButton, ShadowViewDelegate shadowViewDelegate) {
        super(visibilityAwareImageButton, shadowViewDelegate);
    }

    private Animator createElevationAnimator(float f9, float f10) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ObjectAnimator.ofFloat(this.view, "elevation", f9).setDuration(0L)).with(ObjectAnimator.ofFloat(this.view, (Property<VisibilityAwareImageButton, Float>) View.TRANSLATION_Z, f10).setDuration(100L));
        animatorSet.setInterpolator(FloatingActionButtonImpl.ELEVATION_ANIM_INTERPOLATOR);
        return animatorSet;
    }

    @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl
    public float getElevation() {
        return this.view.getElevation();
    }

    @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl
    public void getPadding(Rect rect) {
        if (!this.shadowViewDelegate.isCompatPaddingEnabled()) {
            rect.set(0, 0, 0, 0);
            return;
        }
        float radius = this.shadowViewDelegate.getRadius();
        float elevation = getElevation() + this.pressedTranslationZ;
        int iCeil = (int) Math.ceil(ShadowDrawableWrapper.calculateHorizontalPadding(elevation, radius, false));
        int iCeil2 = (int) Math.ceil(ShadowDrawableWrapper.calculateVerticalPadding(elevation, radius, false));
        rect.set(iCeil, iCeil2, iCeil, iCeil2);
    }

    @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl
    public void jumpDrawableToCurrentState() {
    }

    @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl
    public CircularBorderDrawable newCircularDrawable() {
        return new CircularBorderDrawableLollipop();
    }

    @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl
    public GradientDrawable newGradientDrawableForShape() {
        return new AlwaysStatefulGradientDrawable();
    }

    @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl
    public void onCompatShadowChanged() {
        updatePadding();
    }

    @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl
    public void onDrawableStateChanged(int[] iArr) {
    }

    @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl
    public void onElevationsChanged(float f9, float f10, float f11) {
        StateListAnimator stateListAnimator = new StateListAnimator();
        stateListAnimator.addState(FloatingActionButtonImpl.PRESSED_ENABLED_STATE_SET, createElevationAnimator(f9, f11));
        stateListAnimator.addState(FloatingActionButtonImpl.HOVERED_FOCUSED_ENABLED_STATE_SET, createElevationAnimator(f9, f10));
        stateListAnimator.addState(FloatingActionButtonImpl.FOCUSED_ENABLED_STATE_SET, createElevationAnimator(f9, f10));
        stateListAnimator.addState(FloatingActionButtonImpl.HOVERED_ENABLED_STATE_SET, createElevationAnimator(f9, f10));
        AnimatorSet animatorSet = new AnimatorSet();
        ArrayList arrayList = new ArrayList();
        arrayList.add(ObjectAnimator.ofFloat(this.view, "elevation", f9).setDuration(0L));
        arrayList.add(ObjectAnimator.ofFloat(this.view, (Property<VisibilityAwareImageButton, Float>) View.TRANSLATION_Z, BitmapDescriptorFactory.HUE_RED).setDuration(100L));
        animatorSet.playSequentially((Animator[]) arrayList.toArray(new Animator[0]));
        animatorSet.setInterpolator(FloatingActionButtonImpl.ELEVATION_ANIM_INTERPOLATOR);
        stateListAnimator.addState(FloatingActionButtonImpl.ENABLED_STATE_SET, animatorSet);
        stateListAnimator.addState(FloatingActionButtonImpl.EMPTY_STATE_SET, createElevationAnimator(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED));
        this.view.setStateListAnimator(stateListAnimator);
        if (this.shadowViewDelegate.isCompatPaddingEnabled()) {
            updatePadding();
        }
    }

    @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl
    public void onPaddingUpdated(Rect rect) {
        if (!this.shadowViewDelegate.isCompatPaddingEnabled()) {
            this.shadowViewDelegate.setBackgroundDrawable(this.rippleDrawable);
            return;
        }
        InsetDrawable insetDrawable = new InsetDrawable(this.rippleDrawable, rect.left, rect.top, rect.right, rect.bottom);
        this.insetDrawable = insetDrawable;
        this.shadowViewDelegate.setBackgroundDrawable(insetDrawable);
    }

    @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl
    public boolean requirePreDrawListener() {
        return false;
    }

    @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl
    public void setBackgroundDrawable(ColorStateList colorStateList, PorterDuff.Mode mode, ColorStateList colorStateList2, int i9) {
        Drawable layerDrawable;
        Drawable drawableM24849l = C6494a.m24849l(createShapeDrawable());
        this.shapeDrawable = drawableM24849l;
        C6494a.m24846i(drawableM24849l, colorStateList);
        if (mode != null) {
            C6494a.m24847j(this.shapeDrawable, mode);
        }
        if (i9 > 0) {
            this.borderDrawable = createBorderDrawable(i9, colorStateList);
            layerDrawable = new LayerDrawable(new Drawable[]{this.borderDrawable, this.shapeDrawable});
        } else {
            this.borderDrawable = null;
            layerDrawable = this.shapeDrawable;
        }
        RippleDrawable rippleDrawable = new RippleDrawable(RippleUtils.convertToRippleDrawableColor(colorStateList2), layerDrawable, null);
        this.rippleDrawable = rippleDrawable;
        this.contentBackground = rippleDrawable;
        this.shadowViewDelegate.setBackgroundDrawable(rippleDrawable);
    }

    @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl
    public void setRippleColor(ColorStateList colorStateList) {
        Drawable drawable = this.rippleDrawable;
        if (drawable instanceof RippleDrawable) {
            ((RippleDrawable) drawable).setColor(RippleUtils.convertToRippleDrawableColor(colorStateList));
        } else {
            super.setRippleColor(colorStateList);
        }
    }
}
