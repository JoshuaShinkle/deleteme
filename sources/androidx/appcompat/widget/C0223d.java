package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import p010b.C0569j;
import p042d0.C4647u;

/* renamed from: androidx.appcompat.widget.d */
/* loaded from: classes.dex */
public class C0223d {

    /* renamed from: a */
    public final View f1042a;

    /* renamed from: d */
    public C0246o0 f1045d;

    /* renamed from: e */
    public C0246o0 f1046e;

    /* renamed from: f */
    public C0246o0 f1047f;

    /* renamed from: c */
    public int f1044c = -1;

    /* renamed from: b */
    public final C0227f f1043b = C0227f.m819b();

    public C0223d(View view) {
        this.f1042a = view;
    }

    /* renamed from: a */
    public final boolean m798a(Drawable drawable) {
        if (this.f1047f == null) {
            this.f1047f = new C0246o0();
        }
        C0246o0 c0246o0 = this.f1047f;
        c0246o0.m1000a();
        ColorStateList colorStateListM18551k = C4647u.m18551k(this.f1042a);
        if (colorStateListM18551k != null) {
            c0246o0.f1190d = true;
            c0246o0.f1187a = colorStateListM18551k;
        }
        PorterDuff.Mode modeM18553l = C4647u.m18553l(this.f1042a);
        if (modeM18553l != null) {
            c0246o0.f1189c = true;
            c0246o0.f1188b = modeM18553l;
        }
        if (!c0246o0.f1190d && !c0246o0.f1189c) {
            return false;
        }
        C0227f.m822i(drawable, c0246o0, this.f1042a.getDrawableState());
        return true;
    }

    /* renamed from: b */
    public void m799b() {
        Drawable background = this.f1042a.getBackground();
        if (background != null) {
            if (m808k() && m798a(background)) {
                return;
            }
            C0246o0 c0246o0 = this.f1046e;
            if (c0246o0 != null) {
                C0227f.m822i(background, c0246o0, this.f1042a.getDrawableState());
                return;
            }
            C0246o0 c0246o02 = this.f1045d;
            if (c0246o02 != null) {
                C0227f.m822i(background, c0246o02, this.f1042a.getDrawableState());
            }
        }
    }

    /* renamed from: c */
    public ColorStateList m800c() {
        C0246o0 c0246o0 = this.f1046e;
        if (c0246o0 != null) {
            return c0246o0.f1187a;
        }
        return null;
    }

    /* renamed from: d */
    public PorterDuff.Mode m801d() {
        C0246o0 c0246o0 = this.f1046e;
        if (c0246o0 != null) {
            return c0246o0.f1188b;
        }
        return null;
    }

    /* renamed from: e */
    public void m802e(AttributeSet attributeSet, int i9) {
        Context context = this.f1042a.getContext();
        int[] iArr = C0569j.ViewBackgroundHelper;
        C0250q0 c0250q0M1004v = C0250q0.m1004v(context, attributeSet, iArr, i9, 0);
        View view = this.f1042a;
        C4647u.m18528X(view, view.getContext(), iArr, attributeSet, c0250q0M1004v.m1022r(), i9, 0);
        try {
            int i10 = C0569j.ViewBackgroundHelper_android_background;
            if (c0250q0M1004v.m1023s(i10)) {
                this.f1044c = c0250q0M1004v.m1018n(i10, -1);
                ColorStateList colorStateListM825f = this.f1043b.m825f(this.f1042a.getContext(), this.f1044c);
                if (colorStateListM825f != null) {
                    m805h(colorStateListM825f);
                }
            }
            int i11 = C0569j.ViewBackgroundHelper_backgroundTint;
            if (c0250q0M1004v.m1023s(i11)) {
                C4647u.m18536c0(this.f1042a, c0250q0M1004v.m1007c(i11));
            }
            int i12 = C0569j.ViewBackgroundHelper_backgroundTintMode;
            if (c0250q0M1004v.m1023s(i12)) {
                C4647u.m18538d0(this.f1042a, C0262x.m1076d(c0250q0M1004v.m1015k(i12, -1), null));
            }
        } finally {
            c0250q0M1004v.m1024w();
        }
    }

    /* renamed from: f */
    public void m803f(Drawable drawable) {
        this.f1044c = -1;
        m805h(null);
        m799b();
    }

    /* renamed from: g */
    public void m804g(int i9) {
        this.f1044c = i9;
        C0227f c0227f = this.f1043b;
        m805h(c0227f != null ? c0227f.m825f(this.f1042a.getContext(), i9) : null);
        m799b();
    }

    /* renamed from: h */
    public void m805h(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.f1045d == null) {
                this.f1045d = new C0246o0();
            }
            C0246o0 c0246o0 = this.f1045d;
            c0246o0.f1187a = colorStateList;
            c0246o0.f1190d = true;
        } else {
            this.f1045d = null;
        }
        m799b();
    }

    /* renamed from: i */
    public void m806i(ColorStateList colorStateList) {
        if (this.f1046e == null) {
            this.f1046e = new C0246o0();
        }
        C0246o0 c0246o0 = this.f1046e;
        c0246o0.f1187a = colorStateList;
        c0246o0.f1190d = true;
        m799b();
    }

    /* renamed from: j */
    public void m807j(PorterDuff.Mode mode) {
        if (this.f1046e == null) {
            this.f1046e = new C0246o0();
        }
        C0246o0 c0246o0 = this.f1046e;
        c0246o0.f1188b = mode;
        c0246o0.f1189c = true;
        m799b();
    }

    /* renamed from: k */
    public final boolean m808k() {
        return this.f1045d != null;
    }
}
