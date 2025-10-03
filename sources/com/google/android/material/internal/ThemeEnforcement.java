package com.google.android.material.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.widget.C0250q0;
import com.google.android.material.C3476R;

/* loaded from: classes2.dex */
public final class ThemeEnforcement {
    private static final String APPCOMPAT_THEME_NAME = "Theme.AppCompat";
    private static final String MATERIAL_THEME_NAME = "Theme.MaterialComponents";
    private static final int[] APPCOMPAT_CHECK_ATTRS = {C3476R.attr.colorPrimary};
    private static final int[] MATERIAL_CHECK_ATTRS = {C3476R.attr.colorSecondary};

    private ThemeEnforcement() {
    }

    public static void checkAppCompatTheme(Context context) {
        checkTheme(context, APPCOMPAT_CHECK_ATTRS, APPCOMPAT_THEME_NAME);
    }

    private static void checkCompatibleTheme(Context context, AttributeSet attributeSet, int i9, int i10) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C3476R.styleable.ThemeEnforcement, i9, i10);
        boolean z8 = typedArrayObtainStyledAttributes.getBoolean(C3476R.styleable.ThemeEnforcement_enforceMaterialTheme, false);
        typedArrayObtainStyledAttributes.recycle();
        if (z8) {
            checkMaterialTheme(context);
        }
        checkAppCompatTheme(context);
    }

    public static void checkMaterialTheme(Context context) {
        checkTheme(context, MATERIAL_CHECK_ATTRS, MATERIAL_THEME_NAME);
    }

    private static void checkTextAppearance(Context context, AttributeSet attributeSet, int[] iArr, int i9, int i10, int... iArr2) {
        boolean zIsCustomTextAppearanceValid;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C3476R.styleable.ThemeEnforcement, i9, i10);
        if (!typedArrayObtainStyledAttributes.getBoolean(C3476R.styleable.ThemeEnforcement_enforceTextAppearance, false)) {
            typedArrayObtainStyledAttributes.recycle();
            return;
        }
        if (iArr2 == null || iArr2.length == 0) {
            zIsCustomTextAppearanceValid = typedArrayObtainStyledAttributes.getResourceId(C3476R.styleable.ThemeEnforcement_android_textAppearance, -1) != -1;
        } else {
            zIsCustomTextAppearanceValid = isCustomTextAppearanceValid(context, attributeSet, iArr, i9, i10, iArr2);
        }
        typedArrayObtainStyledAttributes.recycle();
        if (!zIsCustomTextAppearanceValid) {
            throw new IllegalArgumentException("This component requires that you specify a valid TextAppearance attribute. Update your app theme to inherit from Theme.MaterialComponents (or a descendant).");
        }
    }

    private static void checkTheme(Context context, int[] iArr, String str) {
        if (isTheme(context, iArr)) {
            return;
        }
        throw new IllegalArgumentException("The style on this component requires your app theme to be " + str + " (or a descendant).");
    }

    public static boolean isAppCompatTheme(Context context) {
        return isTheme(context, APPCOMPAT_CHECK_ATTRS);
    }

    private static boolean isCustomTextAppearanceValid(Context context, AttributeSet attributeSet, int[] iArr, int i9, int i10, int... iArr2) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i9, i10);
        for (int i11 : iArr2) {
            if (typedArrayObtainStyledAttributes.getResourceId(i11, -1) == -1) {
                typedArrayObtainStyledAttributes.recycle();
                return false;
            }
        }
        typedArrayObtainStyledAttributes.recycle();
        return true;
    }

    public static boolean isMaterialTheme(Context context) {
        return isTheme(context, MATERIAL_CHECK_ATTRS);
    }

    private static boolean isTheme(Context context, int[] iArr) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(iArr);
        boolean zHasValue = typedArrayObtainStyledAttributes.hasValue(0);
        typedArrayObtainStyledAttributes.recycle();
        return zHasValue;
    }

    public static TypedArray obtainStyledAttributes(Context context, AttributeSet attributeSet, int[] iArr, int i9, int i10, int... iArr2) {
        checkCompatibleTheme(context, attributeSet, i9, i10);
        checkTextAppearance(context, attributeSet, iArr, i9, i10, iArr2);
        return context.obtainStyledAttributes(attributeSet, iArr, i9, i10);
    }

    public static C0250q0 obtainTintedStyledAttributes(Context context, AttributeSet attributeSet, int[] iArr, int i9, int i10, int... iArr2) {
        checkCompatibleTheme(context, attributeSet, i9, i10);
        checkTextAppearance(context, attributeSet, iArr, i9, i10, iArr2);
        return C0250q0.m1004v(context, attributeSet, iArr, i9, i10);
    }
}
