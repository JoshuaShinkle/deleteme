package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.widget.CompoundButton;

/* renamed from: androidx.core.widget.c */
/* loaded from: classes.dex */
public final class C0328c {
    /* renamed from: a */
    public static Drawable m1590a(CompoundButton compoundButton) {
        return compoundButton.getButtonDrawable();
    }

    /* renamed from: b */
    public static void m1591b(CompoundButton compoundButton, ColorStateList colorStateList) {
        compoundButton.setButtonTintList(colorStateList);
    }

    /* renamed from: c */
    public static void m1592c(CompoundButton compoundButton, PorterDuff.Mode mode) {
        compoundButton.setButtonTintMode(mode);
    }
}
