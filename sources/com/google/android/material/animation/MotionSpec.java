package com.google.android.material.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import p132m.C5308g;

/* loaded from: classes2.dex */
public class MotionSpec {
    private static final String TAG = "MotionSpec";
    private final C5308g<String, MotionTiming> timings = new C5308g<>();

    private static void addTimingFromAnimator(MotionSpec motionSpec, Animator animator) {
        if (animator instanceof ObjectAnimator) {
            ObjectAnimator objectAnimator = (ObjectAnimator) animator;
            motionSpec.setTiming(objectAnimator.getPropertyName(), MotionTiming.createFromAnimator(objectAnimator));
        } else {
            throw new IllegalArgumentException("Animator must be an ObjectAnimator: " + animator);
        }
    }

    public static MotionSpec createFromAttribute(Context context, TypedArray typedArray, int i9) {
        int resourceId;
        if (!typedArray.hasValue(i9) || (resourceId = typedArray.getResourceId(i9, 0)) == 0) {
            return null;
        }
        return createFromResource(context, resourceId);
    }

    public static MotionSpec createFromResource(Context context, int i9) throws Resources.NotFoundException {
        try {
            Animator animatorLoadAnimator = AnimatorInflater.loadAnimator(context, i9);
            if (animatorLoadAnimator instanceof AnimatorSet) {
                return createSpecFromAnimators(((AnimatorSet) animatorLoadAnimator).getChildAnimations());
            }
            if (animatorLoadAnimator == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(animatorLoadAnimator);
            return createSpecFromAnimators(arrayList);
        } catch (Exception e9) {
            Log.w(TAG, "Can't load animation resource ID #0x" + Integer.toHexString(i9), e9);
            return null;
        }
    }

    private static MotionSpec createSpecFromAnimators(List<Animator> list) {
        MotionSpec motionSpec = new MotionSpec();
        int size = list.size();
        for (int i9 = 0; i9 < size; i9++) {
            addTimingFromAnimator(motionSpec, list.get(i9));
        }
        return motionSpec;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.timings.equals(((MotionSpec) obj).timings);
    }

    public MotionTiming getTiming(String str) {
        if (hasTiming(str)) {
            return this.timings.get(str);
        }
        throw new IllegalArgumentException();
    }

    public long getTotalDuration() {
        int size = this.timings.size();
        long jMax = 0;
        for (int i9 = 0; i9 < size; i9++) {
            MotionTiming motionTimingM20755m = this.timings.m20755m(i9);
            jMax = Math.max(jMax, motionTimingM20755m.getDelay() + motionTimingM20755m.getDuration());
        }
        return jMax;
    }

    public boolean hasTiming(String str) {
        return this.timings.get(str) != null;
    }

    public int hashCode() {
        return this.timings.hashCode();
    }

    public void setTiming(String str, MotionTiming motionTiming) {
        this.timings.put(str, motionTiming);
    }

    public String toString() {
        return '\n' + getClass().getName() + '{' + Integer.toHexString(System.identityHashCode(this)) + " timings: " + this.timings + "}\n";
    }
}
