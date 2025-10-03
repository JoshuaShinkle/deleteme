package com.google.android.material.animation;

import android.util.Property;
import android.view.ViewGroup;
import com.google.android.material.C3476R;

/* loaded from: classes2.dex */
public class ChildrenAlphaProperty extends Property<ViewGroup, Float> {
    public static final Property<ViewGroup, Float> CHILDREN_ALPHA = new ChildrenAlphaProperty("childrenAlpha");

    private ChildrenAlphaProperty(String str) {
        super(Float.class, str);
    }

    @Override // android.util.Property
    public Float get(ViewGroup viewGroup) {
        Float f9 = (Float) viewGroup.getTag(C3476R.id.mtrl_internal_children_alpha_tag);
        return f9 != null ? f9 : Float.valueOf(1.0f);
    }

    @Override // android.util.Property
    public void set(ViewGroup viewGroup, Float f9) {
        float fFloatValue = f9.floatValue();
        viewGroup.setTag(C3476R.id.mtrl_internal_children_alpha_tag, Float.valueOf(fFloatValue));
        int childCount = viewGroup.getChildCount();
        for (int i9 = 0; i9 < childCount; i9++) {
            viewGroup.getChildAt(i9).setAlpha(fFloatValue);
        }
    }
}
