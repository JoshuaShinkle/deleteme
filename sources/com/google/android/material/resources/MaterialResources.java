package com.google.android.material.resources;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import p020c.C0694a;

/* loaded from: classes2.dex */
public class MaterialResources {
    private MaterialResources() {
    }

    public static ColorStateList getColorStateList(Context context, TypedArray typedArray, int i9) {
        int resourceId;
        ColorStateList colorStateListM3457a;
        return (!typedArray.hasValue(i9) || (resourceId = typedArray.getResourceId(i9, 0)) == 0 || (colorStateListM3457a = C0694a.m3457a(context, resourceId)) == null) ? typedArray.getColorStateList(i9) : colorStateListM3457a;
    }

    public static Drawable getDrawable(Context context, TypedArray typedArray, int i9) {
        int resourceId;
        Drawable drawableM3458b;
        return (!typedArray.hasValue(i9) || (resourceId = typedArray.getResourceId(i9, 0)) == 0 || (drawableM3458b = C0694a.m3458b(context, resourceId)) == null) ? typedArray.getDrawable(i9) : drawableM3458b;
    }

    public static int getIndexWithValue(TypedArray typedArray, int i9, int i10) {
        return typedArray.hasValue(i9) ? i9 : i10;
    }

    public static TextAppearance getTextAppearance(Context context, TypedArray typedArray, int i9) {
        int resourceId;
        if (!typedArray.hasValue(i9) || (resourceId = typedArray.getResourceId(i9, 0)) == 0) {
            return null;
        }
        return new TextAppearance(context, resourceId);
    }
}
