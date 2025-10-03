package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.widget.ImageView;

/* renamed from: androidx.core.widget.e */
/* loaded from: classes.dex */
public class C0330e {
    /* renamed from: a */
    public static ColorStateList m1594a(ImageView imageView) {
        return imageView.getImageTintList();
    }

    /* renamed from: b */
    public static PorterDuff.Mode m1595b(ImageView imageView) {
        return imageView.getImageTintMode();
    }

    /* renamed from: c */
    public static void m1596c(ImageView imageView, ColorStateList colorStateList) {
        imageView.setImageTintList(colorStateList);
    }

    /* renamed from: d */
    public static void m1597d(ImageView imageView, PorterDuff.Mode mode) {
        imageView.setImageTintMode(mode);
    }
}
