package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import androidx.core.widget.C0328c;
import p010b.C0569j;
import p020c.C0694a;
import p042d0.C4647u;
import p224w.C6494a;

/* renamed from: androidx.appcompat.widget.e */
/* loaded from: classes.dex */
public class C0225e {

    /* renamed from: a */
    public final CompoundButton f1048a;

    /* renamed from: b */
    public ColorStateList f1049b = null;

    /* renamed from: c */
    public PorterDuff.Mode f1050c = null;

    /* renamed from: d */
    public boolean f1051d = false;

    /* renamed from: e */
    public boolean f1052e = false;

    /* renamed from: f */
    public boolean f1053f;

    public C0225e(CompoundButton compoundButton) {
        this.f1048a = compoundButton;
    }

    /* renamed from: a */
    public void m809a() {
        Drawable drawableM1590a = C0328c.m1590a(this.f1048a);
        if (drawableM1590a != null) {
            if (this.f1051d || this.f1052e) {
                Drawable drawableMutate = C6494a.m24849l(drawableM1590a).mutate();
                if (this.f1051d) {
                    C6494a.m24846i(drawableMutate, this.f1049b);
                }
                if (this.f1052e) {
                    C6494a.m24847j(drawableMutate, this.f1050c);
                }
                if (drawableMutate.isStateful()) {
                    drawableMutate.setState(this.f1048a.getDrawableState());
                }
                this.f1048a.setButtonDrawable(drawableMutate);
            }
        }
    }

    /* renamed from: b */
    public int m810b(int i9) {
        return i9;
    }

    /* renamed from: c */
    public ColorStateList m811c() {
        return this.f1049b;
    }

    /* renamed from: d */
    public PorterDuff.Mode m812d() {
        return this.f1050c;
    }

    /* renamed from: e */
    public void m813e(AttributeSet attributeSet, int i9) {
        boolean z8;
        int iM1018n;
        int iM1018n2;
        Context context = this.f1048a.getContext();
        int[] iArr = C0569j.CompoundButton;
        C0250q0 c0250q0M1004v = C0250q0.m1004v(context, attributeSet, iArr, i9, 0);
        CompoundButton compoundButton = this.f1048a;
        C4647u.m18528X(compoundButton, compoundButton.getContext(), iArr, attributeSet, c0250q0M1004v.m1022r(), i9, 0);
        try {
            int i10 = C0569j.CompoundButton_buttonCompat;
            if (!c0250q0M1004v.m1023s(i10) || (iM1018n2 = c0250q0M1004v.m1018n(i10, 0)) == 0) {
                z8 = false;
            } else {
                try {
                    CompoundButton compoundButton2 = this.f1048a;
                    compoundButton2.setButtonDrawable(C0694a.m3458b(compoundButton2.getContext(), iM1018n2));
                    z8 = true;
                } catch (Resources.NotFoundException unused) {
                }
            }
            if (!z8) {
                int i11 = C0569j.CompoundButton_android_button;
                if (c0250q0M1004v.m1023s(i11) && (iM1018n = c0250q0M1004v.m1018n(i11, 0)) != 0) {
                    CompoundButton compoundButton3 = this.f1048a;
                    compoundButton3.setButtonDrawable(C0694a.m3458b(compoundButton3.getContext(), iM1018n));
                }
            }
            int i12 = C0569j.CompoundButton_buttonTint;
            if (c0250q0M1004v.m1023s(i12)) {
                C0328c.m1591b(this.f1048a, c0250q0M1004v.m1007c(i12));
            }
            int i13 = C0569j.CompoundButton_buttonTintMode;
            if (c0250q0M1004v.m1023s(i13)) {
                C0328c.m1592c(this.f1048a, C0262x.m1076d(c0250q0M1004v.m1015k(i13, -1), null));
            }
        } finally {
            c0250q0M1004v.m1024w();
        }
    }

    /* renamed from: f */
    public void m814f() {
        if (this.f1053f) {
            this.f1053f = false;
        } else {
            this.f1053f = true;
            m809a();
        }
    }

    /* renamed from: g */
    public void m815g(ColorStateList colorStateList) {
        this.f1049b = colorStateList;
        this.f1051d = true;
        m809a();
    }

    /* renamed from: h */
    public void m816h(PorterDuff.Mode mode) {
        this.f1050c = mode;
        this.f1052e = true;
        m809a();
    }
}
