package com.google.android.material.appbar;

import android.R;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.material.C3476R;
import com.google.android.material.internal.ThemeEnforcement;

/* loaded from: classes2.dex */
class ViewUtilsLollipop {
    private static final int[] STATE_LIST_ANIM_ATTRS = {R.attr.stateListAnimator};

    public static void setBoundsViewOutlineProvider(View view) {
        view.setOutlineProvider(ViewOutlineProvider.BOUNDS);
    }

    public static void setDefaultAppBarLayoutStateListAnimator(View view, float f9) throws Resources.NotFoundException {
        int integer = view.getResources().getInteger(C3476R.integer.app_bar_elevation_anim_duration);
        StateListAnimator stateListAnimator = new StateListAnimator();
        long j9 = integer;
        stateListAnimator.addState(new int[]{R.attr.enabled, C3476R.attr.state_liftable, -C3476R.attr.state_lifted}, ObjectAnimator.ofFloat(view, "elevation", BitmapDescriptorFactory.HUE_RED).setDuration(j9));
        stateListAnimator.addState(new int[]{R.attr.enabled}, ObjectAnimator.ofFloat(view, "elevation", f9).setDuration(j9));
        stateListAnimator.addState(new int[0], ObjectAnimator.ofFloat(view, "elevation", BitmapDescriptorFactory.HUE_RED).setDuration(0L));
        view.setStateListAnimator(stateListAnimator);
    }

    public static void setStateListAnimatorFromAttrs(View view, AttributeSet attributeSet, int i9, int i10) {
        Context context = view.getContext();
        TypedArray typedArrayObtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context, attributeSet, STATE_LIST_ANIM_ATTRS, i9, i10, new int[0]);
        try {
            if (typedArrayObtainStyledAttributes.hasValue(0)) {
                view.setStateListAnimator(AnimatorInflater.loadStateListAnimator(context, typedArrayObtainStyledAttributes.getResourceId(0, 0)));
            }
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }
}
