package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import androidx.core.widget.C0330e;
import p010b.C0569j;
import p020c.C0694a;
import p042d0.C4647u;

/* renamed from: androidx.appcompat.widget.h */
/* loaded from: classes.dex */
public class C0231h {

    /* renamed from: a */
    public final ImageView f1079a;

    /* renamed from: b */
    public C0246o0 f1080b;

    /* renamed from: c */
    public C0246o0 f1081c;

    /* renamed from: d */
    public C0246o0 f1082d;

    public C0231h(ImageView imageView) {
        this.f1079a = imageView;
    }

    /* renamed from: a */
    public final boolean m873a(Drawable drawable) {
        if (this.f1082d == null) {
            this.f1082d = new C0246o0();
        }
        C0246o0 c0246o0 = this.f1082d;
        c0246o0.m1000a();
        ColorStateList colorStateListM1594a = C0330e.m1594a(this.f1079a);
        if (colorStateListM1594a != null) {
            c0246o0.f1190d = true;
            c0246o0.f1187a = colorStateListM1594a;
        }
        PorterDuff.Mode modeM1595b = C0330e.m1595b(this.f1079a);
        if (modeM1595b != null) {
            c0246o0.f1189c = true;
            c0246o0.f1188b = modeM1595b;
        }
        if (!c0246o0.f1190d && !c0246o0.f1189c) {
            return false;
        }
        C0227f.m822i(drawable, c0246o0, this.f1079a.getDrawableState());
        return true;
    }

    /* renamed from: b */
    public void m874b() {
        Drawable drawable = this.f1079a.getDrawable();
        if (drawable != null) {
            C0262x.m1074b(drawable);
        }
        if (drawable != null) {
            if (m882j() && m873a(drawable)) {
                return;
            }
            C0246o0 c0246o0 = this.f1081c;
            if (c0246o0 != null) {
                C0227f.m822i(drawable, c0246o0, this.f1079a.getDrawableState());
                return;
            }
            C0246o0 c0246o02 = this.f1080b;
            if (c0246o02 != null) {
                C0227f.m822i(drawable, c0246o02, this.f1079a.getDrawableState());
            }
        }
    }

    /* renamed from: c */
    public ColorStateList m875c() {
        C0246o0 c0246o0 = this.f1081c;
        if (c0246o0 != null) {
            return c0246o0.f1187a;
        }
        return null;
    }

    /* renamed from: d */
    public PorterDuff.Mode m876d() {
        C0246o0 c0246o0 = this.f1081c;
        if (c0246o0 != null) {
            return c0246o0.f1188b;
        }
        return null;
    }

    /* renamed from: e */
    public boolean m877e() {
        return !(this.f1079a.getBackground() instanceof RippleDrawable);
    }

    /* renamed from: f */
    public void m878f(AttributeSet attributeSet, int i9) {
        int iM1018n;
        Context context = this.f1079a.getContext();
        int[] iArr = C0569j.AppCompatImageView;
        C0250q0 c0250q0M1004v = C0250q0.m1004v(context, attributeSet, iArr, i9, 0);
        ImageView imageView = this.f1079a;
        C4647u.m18528X(imageView, imageView.getContext(), iArr, attributeSet, c0250q0M1004v.m1022r(), i9, 0);
        try {
            Drawable drawable = this.f1079a.getDrawable();
            if (drawable == null && (iM1018n = c0250q0M1004v.m1018n(C0569j.AppCompatImageView_srcCompat, -1)) != -1 && (drawable = C0694a.m3458b(this.f1079a.getContext(), iM1018n)) != null) {
                this.f1079a.setImageDrawable(drawable);
            }
            if (drawable != null) {
                C0262x.m1074b(drawable);
            }
            int i10 = C0569j.AppCompatImageView_tint;
            if (c0250q0M1004v.m1023s(i10)) {
                C0330e.m1596c(this.f1079a, c0250q0M1004v.m1007c(i10));
            }
            int i11 = C0569j.AppCompatImageView_tintMode;
            if (c0250q0M1004v.m1023s(i11)) {
                C0330e.m1597d(this.f1079a, C0262x.m1076d(c0250q0M1004v.m1015k(i11, -1), null));
            }
        } finally {
            c0250q0M1004v.m1024w();
        }
    }

    /* renamed from: g */
    public void m879g(int i9) {
        if (i9 != 0) {
            Drawable drawableM3458b = C0694a.m3458b(this.f1079a.getContext(), i9);
            if (drawableM3458b != null) {
                C0262x.m1074b(drawableM3458b);
            }
            this.f1079a.setImageDrawable(drawableM3458b);
        } else {
            this.f1079a.setImageDrawable(null);
        }
        m874b();
    }

    /* renamed from: h */
    public void m880h(ColorStateList colorStateList) {
        if (this.f1081c == null) {
            this.f1081c = new C0246o0();
        }
        C0246o0 c0246o0 = this.f1081c;
        c0246o0.f1187a = colorStateList;
        c0246o0.f1190d = true;
        m874b();
    }

    /* renamed from: i */
    public void m881i(PorterDuff.Mode mode) {
        if (this.f1081c == null) {
            this.f1081c = new C0246o0();
        }
        C0246o0 c0246o0 = this.f1081c;
        c0246o0.f1188b = mode;
        c0246o0.f1189c = true;
        m874b();
    }

    /* renamed from: j */
    public final boolean m882j() {
        return this.f1080b != null;
    }
}
