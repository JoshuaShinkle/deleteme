package androidx.cardview.widget;

import android.graphics.drawable.Drawable;

/* renamed from: androidx.cardview.widget.e */
/* loaded from: classes.dex */
public class C0272e extends Drawable {

    /* renamed from: a */
    public static final double f1262a = Math.cos(Math.toRadians(45.0d));

    /* renamed from: a */
    public static float m1129a(float f9, float f10, boolean z8) {
        return z8 ? (float) (f9 + ((1.0d - f1262a) * f10)) : f9;
    }

    /* renamed from: b */
    public static float m1130b(float f9, float f10, boolean z8) {
        return z8 ? (float) ((f9 * 1.5f) + ((1.0d - f1262a) * f10)) : f9 * 1.5f;
    }
}
