package p224w;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;

/* renamed from: w.a */
/* loaded from: classes.dex */
public final class C6494a {
    /* renamed from: a */
    public static void m24838a(Drawable drawable) {
        drawable.clearColorFilter();
    }

    /* renamed from: b */
    public static int m24839b(Drawable drawable) {
        return drawable.getLayoutDirection();
    }

    /* renamed from: c */
    public static boolean m24840c(Drawable drawable) {
        return drawable.isAutoMirrored();
    }

    /* renamed from: d */
    public static void m24841d(Drawable drawable, boolean z8) {
        drawable.setAutoMirrored(z8);
    }

    /* renamed from: e */
    public static void m24842e(Drawable drawable, float f9, float f10) {
        drawable.setHotspot(f9, f10);
    }

    /* renamed from: f */
    public static void m24843f(Drawable drawable, int i9, int i10, int i11, int i12) {
        drawable.setHotspotBounds(i9, i10, i11, i12);
    }

    /* renamed from: g */
    public static boolean m24844g(Drawable drawable, int i9) {
        return drawable.setLayoutDirection(i9);
    }

    /* renamed from: h */
    public static void m24845h(Drawable drawable, int i9) {
        drawable.setTint(i9);
    }

    /* renamed from: i */
    public static void m24846i(Drawable drawable, ColorStateList colorStateList) {
        drawable.setTintList(colorStateList);
    }

    /* renamed from: j */
    public static void m24847j(Drawable drawable, PorterDuff.Mode mode) {
        drawable.setTintMode(mode);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: k */
    public static <T extends Drawable> T m24848k(Drawable drawable) {
        return drawable instanceof InterfaceC6502f ? (T) ((InterfaceC6502f) drawable).m24873b() : drawable;
    }

    /* renamed from: l */
    public static Drawable m24849l(Drawable drawable) {
        return drawable;
    }
}
