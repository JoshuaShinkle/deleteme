package androidx.appcompat.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.LocaleList;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.core.widget.C0337l;
import androidx.core.widget.InterfaceC0327b;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.lang.ref.WeakReference;
import p010b.C0569j;
import p042d0.C4647u;
import p206u.C6348b;

/* renamed from: androidx.appcompat.widget.m */
/* loaded from: classes.dex */
public class C0241m {

    /* renamed from: a */
    public final TextView f1152a;

    /* renamed from: b */
    public C0246o0 f1153b;

    /* renamed from: c */
    public C0246o0 f1154c;

    /* renamed from: d */
    public C0246o0 f1155d;

    /* renamed from: e */
    public C0246o0 f1156e;

    /* renamed from: f */
    public C0246o0 f1157f;

    /* renamed from: g */
    public C0246o0 f1158g;

    /* renamed from: h */
    public C0246o0 f1159h;

    /* renamed from: i */
    public final C0243n f1160i;

    /* renamed from: j */
    public int f1161j = 0;

    /* renamed from: k */
    public int f1162k = -1;

    /* renamed from: l */
    public Typeface f1163l;

    /* renamed from: m */
    public boolean f1164m;

    /* renamed from: androidx.appcompat.widget.m$a */
    public class a extends C6348b.a {

        /* renamed from: a */
        public final /* synthetic */ int f1165a;

        /* renamed from: b */
        public final /* synthetic */ int f1166b;

        /* renamed from: c */
        public final /* synthetic */ WeakReference f1167c;

        public a(int i9, int i10, WeakReference weakReference) {
            this.f1165a = i9;
            this.f1166b = i10;
            this.f1167c = weakReference;
        }

        @Override // p206u.C6348b.a
        public void onFontRetrievalFailed(int i9) {
        }

        @Override // p206u.C6348b.a
        public void onFontRetrieved(Typeface typeface) {
            int i9;
            if (Build.VERSION.SDK_INT >= 28 && (i9 = this.f1165a) != -1) {
                typeface = Typeface.create(typeface, i9, (this.f1166b & 2) != 0);
            }
            C0241m.this.m955n(this.f1167c, typeface);
        }
    }

    public C0241m(TextView textView) {
        this.f1152a = textView;
        this.f1160i = new C0243n(textView);
    }

    /* renamed from: d */
    public static C0246o0 m940d(Context context, C0227f c0227f, int i9) {
        ColorStateList colorStateListM825f = c0227f.m825f(context, i9);
        if (colorStateListM825f == null) {
            return null;
        }
        C0246o0 c0246o0 = new C0246o0();
        c0246o0.f1190d = true;
        c0246o0.f1187a = colorStateListM825f;
        return c0246o0;
    }

    /* renamed from: A */
    public final void m941A(int i9, float f9) {
        this.f1160i.m989u(i9, f9);
    }

    /* renamed from: B */
    public final void m942B(Context context, C0250q0 c0250q0) {
        String strM1019o;
        this.f1161j = c0250q0.m1015k(C0569j.TextAppearance_android_textStyle, this.f1161j);
        int i9 = Build.VERSION.SDK_INT;
        if (i9 >= 28) {
            int iM1015k = c0250q0.m1015k(C0569j.TextAppearance_android_textFontWeight, -1);
            this.f1162k = iM1015k;
            if (iM1015k != -1) {
                this.f1161j = (this.f1161j & 2) | 0;
            }
        }
        int i10 = C0569j.TextAppearance_android_fontFamily;
        if (!c0250q0.m1023s(i10) && !c0250q0.m1023s(C0569j.TextAppearance_fontFamily)) {
            int i11 = C0569j.TextAppearance_android_typeface;
            if (c0250q0.m1023s(i11)) {
                this.f1164m = false;
                int iM1015k2 = c0250q0.m1015k(i11, 1);
                if (iM1015k2 == 1) {
                    this.f1163l = Typeface.SANS_SERIF;
                    return;
                } else if (iM1015k2 == 2) {
                    this.f1163l = Typeface.SERIF;
                    return;
                } else {
                    if (iM1015k2 != 3) {
                        return;
                    }
                    this.f1163l = Typeface.MONOSPACE;
                    return;
                }
            }
            return;
        }
        this.f1163l = null;
        int i12 = C0569j.TextAppearance_fontFamily;
        if (c0250q0.m1023s(i12)) {
            i10 = i12;
        }
        int i13 = this.f1162k;
        int i14 = this.f1161j;
        if (!context.isRestricted()) {
            try {
                Typeface typefaceM1014j = c0250q0.m1014j(i10, this.f1161j, new a(i13, i14, new WeakReference(this.f1152a)));
                if (typefaceM1014j != null) {
                    if (i9 < 28 || this.f1162k == -1) {
                        this.f1163l = typefaceM1014j;
                    } else {
                        this.f1163l = Typeface.create(Typeface.create(typefaceM1014j, 0), this.f1162k, (this.f1161j & 2) != 0);
                    }
                }
                this.f1164m = this.f1163l == null;
            } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
            }
        }
        if (this.f1163l != null || (strM1019o = c0250q0.m1019o(i10)) == null) {
            return;
        }
        if (Build.VERSION.SDK_INT < 28 || this.f1162k == -1) {
            this.f1163l = Typeface.create(strM1019o, this.f1161j);
        } else {
            this.f1163l = Typeface.create(Typeface.create(strM1019o, 0), this.f1162k, (this.f1161j & 2) != 0);
        }
    }

    /* renamed from: a */
    public final void m943a(Drawable drawable, C0246o0 c0246o0) {
        if (drawable == null || c0246o0 == null) {
            return;
        }
        C0227f.m822i(drawable, c0246o0, this.f1152a.getDrawableState());
    }

    /* renamed from: b */
    public void m944b() {
        if (this.f1153b != null || this.f1154c != null || this.f1155d != null || this.f1156e != null) {
            Drawable[] compoundDrawables = this.f1152a.getCompoundDrawables();
            m943a(compoundDrawables[0], this.f1153b);
            m943a(compoundDrawables[1], this.f1154c);
            m943a(compoundDrawables[2], this.f1155d);
            m943a(compoundDrawables[3], this.f1156e);
        }
        if (this.f1157f == null && this.f1158g == null) {
            return;
        }
        Drawable[] compoundDrawablesRelative = this.f1152a.getCompoundDrawablesRelative();
        m943a(compoundDrawablesRelative[0], this.f1157f);
        m943a(compoundDrawablesRelative[2], this.f1158g);
    }

    /* renamed from: c */
    public void m945c() {
        this.f1160i.m971a();
    }

    /* renamed from: e */
    public int m946e() {
        return this.f1160i.m977g();
    }

    /* renamed from: f */
    public int m947f() {
        return this.f1160i.m978h();
    }

    /* renamed from: g */
    public int m948g() {
        return this.f1160i.m979i();
    }

    /* renamed from: h */
    public int[] m949h() {
        return this.f1160i.m980j();
    }

    /* renamed from: i */
    public int m950i() {
        return this.f1160i.m981k();
    }

    /* renamed from: j */
    public ColorStateList m951j() {
        C0246o0 c0246o0 = this.f1159h;
        if (c0246o0 != null) {
            return c0246o0.f1187a;
        }
        return null;
    }

    /* renamed from: k */
    public PorterDuff.Mode m952k() {
        C0246o0 c0246o0 = this.f1159h;
        if (c0246o0 != null) {
            return c0246o0.f1188b;
        }
        return null;
    }

    /* renamed from: l */
    public boolean m953l() {
        return this.f1160i.m983o();
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0101  */
    @SuppressLint({"NewApi"})
    /* renamed from: m */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void m954m(AttributeSet attributeSet, int i9) {
        boolean zM1005a;
        boolean z8;
        String strM1019o;
        String strM1019o2;
        boolean z9;
        Context context = this.f1152a.getContext();
        C0227f c0227fM819b = C0227f.m819b();
        int[] iArr = C0569j.AppCompatTextHelper;
        C0250q0 c0250q0M1004v = C0250q0.m1004v(context, attributeSet, iArr, i9, 0);
        TextView textView = this.f1152a;
        C4647u.m18528X(textView, textView.getContext(), iArr, attributeSet, c0250q0M1004v.m1022r(), i9, 0);
        int iM1018n = c0250q0M1004v.m1018n(C0569j.AppCompatTextHelper_android_textAppearance, -1);
        int i10 = C0569j.AppCompatTextHelper_android_drawableLeft;
        if (c0250q0M1004v.m1023s(i10)) {
            this.f1153b = m940d(context, c0227fM819b, c0250q0M1004v.m1018n(i10, 0));
        }
        int i11 = C0569j.AppCompatTextHelper_android_drawableTop;
        if (c0250q0M1004v.m1023s(i11)) {
            this.f1154c = m940d(context, c0227fM819b, c0250q0M1004v.m1018n(i11, 0));
        }
        int i12 = C0569j.AppCompatTextHelper_android_drawableRight;
        if (c0250q0M1004v.m1023s(i12)) {
            this.f1155d = m940d(context, c0227fM819b, c0250q0M1004v.m1018n(i12, 0));
        }
        int i13 = C0569j.AppCompatTextHelper_android_drawableBottom;
        if (c0250q0M1004v.m1023s(i13)) {
            this.f1156e = m940d(context, c0227fM819b, c0250q0M1004v.m1018n(i13, 0));
        }
        int i14 = Build.VERSION.SDK_INT;
        int i15 = C0569j.AppCompatTextHelper_android_drawableStart;
        if (c0250q0M1004v.m1023s(i15)) {
            this.f1157f = m940d(context, c0227fM819b, c0250q0M1004v.m1018n(i15, 0));
        }
        int i16 = C0569j.AppCompatTextHelper_android_drawableEnd;
        if (c0250q0M1004v.m1023s(i16)) {
            this.f1158g = m940d(context, c0227fM819b, c0250q0M1004v.m1018n(i16, 0));
        }
        c0250q0M1004v.m1024w();
        boolean z10 = this.f1152a.getTransformationMethod() instanceof PasswordTransformationMethod;
        if (iM1018n != -1) {
            C0250q0 c0250q0M1002t = C0250q0.m1002t(context, iM1018n, C0569j.TextAppearance);
            if (!z10) {
                int i17 = C0569j.TextAppearance_textAllCaps;
                if (c0250q0M1002t.m1023s(i17)) {
                    zM1005a = c0250q0M1002t.m1005a(i17, false);
                    z8 = true;
                } else {
                    zM1005a = false;
                    z8 = false;
                }
                m942B(context, c0250q0M1002t);
                int i18 = C0569j.TextAppearance_textLocale;
                strM1019o = c0250q0M1002t.m1023s(i18) ? c0250q0M1002t.m1019o(i18) : null;
                int i19 = C0569j.TextAppearance_fontVariationSettings;
                strM1019o2 = c0250q0M1002t.m1023s(i19) ? c0250q0M1002t.m1019o(i19) : null;
                c0250q0M1002t.m1024w();
            }
        } else {
            zM1005a = false;
            z8 = false;
            strM1019o = null;
            strM1019o2 = null;
        }
        C0250q0 c0250q0M1004v2 = C0250q0.m1004v(context, attributeSet, C0569j.TextAppearance, i9, 0);
        if (!z10) {
            int i20 = C0569j.TextAppearance_textAllCaps;
            if (c0250q0M1004v2.m1023s(i20)) {
                zM1005a = c0250q0M1004v2.m1005a(i20, false);
                z9 = true;
            } else {
                z9 = z8;
            }
        }
        int i21 = C0569j.TextAppearance_textLocale;
        if (c0250q0M1004v2.m1023s(i21)) {
            strM1019o = c0250q0M1004v2.m1019o(i21);
        }
        int i22 = C0569j.TextAppearance_fontVariationSettings;
        if (c0250q0M1004v2.m1023s(i22)) {
            strM1019o2 = c0250q0M1004v2.m1019o(i22);
        }
        if (i14 >= 28) {
            int i23 = C0569j.TextAppearance_android_textSize;
            if (c0250q0M1004v2.m1023s(i23) && c0250q0M1004v2.m1010f(i23, -1) == 0) {
                this.f1152a.setTextSize(0, BitmapDescriptorFactory.HUE_RED);
            }
        }
        m942B(context, c0250q0M1004v2);
        c0250q0M1004v2.m1024w();
        if (!z10 && z9) {
            m959r(zM1005a);
        }
        Typeface typeface = this.f1163l;
        if (typeface != null) {
            if (this.f1162k == -1) {
                this.f1152a.setTypeface(typeface, this.f1161j);
            } else {
                this.f1152a.setTypeface(typeface);
            }
        }
        if (strM1019o2 != null) {
            this.f1152a.setFontVariationSettings(strM1019o2);
        }
        if (strM1019o != null) {
            this.f1152a.setTextLocales(LocaleList.forLanguageTags(strM1019o));
        }
        this.f1160i.m984p(attributeSet, i9);
        if (InterfaceC0327b.f1850a && this.f1160i.m981k() != 0) {
            int[] iArrM980j = this.f1160i.m980j();
            if (iArrM980j.length > 0) {
                if (this.f1152a.getAutoSizeStepGranularity() != -1.0f) {
                    this.f1152a.setAutoSizeTextTypeUniformWithConfiguration(this.f1160i.m978h(), this.f1160i.m977g(), this.f1160i.m979i(), 0);
                } else {
                    this.f1152a.setAutoSizeTextTypeUniformWithPresetSizes(iArrM980j, 0);
                }
            }
        }
        C0250q0 c0250q0M1003u = C0250q0.m1003u(context, attributeSet, C0569j.AppCompatTextView);
        int iM1018n2 = c0250q0M1003u.m1018n(C0569j.AppCompatTextView_drawableLeftCompat, -1);
        Drawable drawableM823c = iM1018n2 != -1 ? c0227fM819b.m823c(context, iM1018n2) : null;
        int iM1018n3 = c0250q0M1003u.m1018n(C0569j.AppCompatTextView_drawableTopCompat, -1);
        Drawable drawableM823c2 = iM1018n3 != -1 ? c0227fM819b.m823c(context, iM1018n3) : null;
        int iM1018n4 = c0250q0M1003u.m1018n(C0569j.AppCompatTextView_drawableRightCompat, -1);
        Drawable drawableM823c3 = iM1018n4 != -1 ? c0227fM819b.m823c(context, iM1018n4) : null;
        int iM1018n5 = c0250q0M1003u.m1018n(C0569j.AppCompatTextView_drawableBottomCompat, -1);
        Drawable drawableM823c4 = iM1018n5 != -1 ? c0227fM819b.m823c(context, iM1018n5) : null;
        int iM1018n6 = c0250q0M1003u.m1018n(C0569j.AppCompatTextView_drawableStartCompat, -1);
        Drawable drawableM823c5 = iM1018n6 != -1 ? c0227fM819b.m823c(context, iM1018n6) : null;
        int iM1018n7 = c0250q0M1003u.m1018n(C0569j.AppCompatTextView_drawableEndCompat, -1);
        m965x(drawableM823c, drawableM823c2, drawableM823c3, drawableM823c4, drawableM823c5, iM1018n7 != -1 ? c0227fM819b.m823c(context, iM1018n7) : null);
        int i24 = C0569j.AppCompatTextView_drawableTint;
        if (c0250q0M1003u.m1023s(i24)) {
            C0337l.m1613h(this.f1152a, c0250q0M1003u.m1007c(i24));
        }
        int i25 = C0569j.AppCompatTextView_drawableTintMode;
        if (c0250q0M1003u.m1023s(i25)) {
            C0337l.m1614i(this.f1152a, C0262x.m1076d(c0250q0M1003u.m1015k(i25, -1), null));
        }
        int iM1010f = c0250q0M1003u.m1010f(C0569j.AppCompatTextView_firstBaselineToTopHeight, -1);
        int iM1010f2 = c0250q0M1003u.m1010f(C0569j.AppCompatTextView_lastBaselineToBottomHeight, -1);
        int iM1010f3 = c0250q0M1003u.m1010f(C0569j.AppCompatTextView_lineHeight, -1);
        c0250q0M1003u.m1024w();
        if (iM1010f != -1) {
            C0337l.m1616k(this.f1152a, iM1010f);
        }
        if (iM1010f2 != -1) {
            C0337l.m1617l(this.f1152a, iM1010f2);
        }
        if (iM1010f3 != -1) {
            C0337l.m1618m(this.f1152a, iM1010f3);
        }
    }

    /* renamed from: n */
    public void m955n(WeakReference<TextView> weakReference, Typeface typeface) {
        if (this.f1164m) {
            this.f1163l = typeface;
            TextView textView = weakReference.get();
            if (textView != null) {
                textView.setTypeface(typeface, this.f1161j);
            }
        }
    }

    /* renamed from: o */
    public void m956o(boolean z8, int i9, int i10, int i11, int i12) {
        if (InterfaceC0327b.f1850a) {
            return;
        }
        m945c();
    }

    /* renamed from: p */
    public void m957p() {
        m944b();
    }

    /* renamed from: q */
    public void m958q(Context context, int i9) {
        String strM1019o;
        C0250q0 c0250q0M1002t = C0250q0.m1002t(context, i9, C0569j.TextAppearance);
        int i10 = C0569j.TextAppearance_textAllCaps;
        if (c0250q0M1002t.m1023s(i10)) {
            m959r(c0250q0M1002t.m1005a(i10, false));
        }
        int i11 = C0569j.TextAppearance_android_textSize;
        if (c0250q0M1002t.m1023s(i11) && c0250q0M1002t.m1010f(i11, -1) == 0) {
            this.f1152a.setTextSize(0, BitmapDescriptorFactory.HUE_RED);
        }
        m942B(context, c0250q0M1002t);
        int i12 = C0569j.TextAppearance_fontVariationSettings;
        if (c0250q0M1002t.m1023s(i12) && (strM1019o = c0250q0M1002t.m1019o(i12)) != null) {
            this.f1152a.setFontVariationSettings(strM1019o);
        }
        c0250q0M1002t.m1024w();
        Typeface typeface = this.f1163l;
        if (typeface != null) {
            this.f1152a.setTypeface(typeface, this.f1161j);
        }
    }

    /* renamed from: r */
    public void m959r(boolean z8) {
        this.f1152a.setAllCaps(z8);
    }

    /* renamed from: s */
    public void m960s(int i9, int i10, int i11, int i12) {
        this.f1160i.m985q(i9, i10, i11, i12);
    }

    /* renamed from: t */
    public void m961t(int[] iArr, int i9) {
        this.f1160i.m986r(iArr, i9);
    }

    /* renamed from: u */
    public void m962u(int i9) {
        this.f1160i.m987s(i9);
    }

    /* renamed from: v */
    public void m963v(ColorStateList colorStateList) {
        if (this.f1159h == null) {
            this.f1159h = new C0246o0();
        }
        C0246o0 c0246o0 = this.f1159h;
        c0246o0.f1187a = colorStateList;
        c0246o0.f1190d = colorStateList != null;
        m966y();
    }

    /* renamed from: w */
    public void m964w(PorterDuff.Mode mode) {
        if (this.f1159h == null) {
            this.f1159h = new C0246o0();
        }
        C0246o0 c0246o0 = this.f1159h;
        c0246o0.f1188b = mode;
        c0246o0.f1189c = mode != null;
        m966y();
    }

    /* renamed from: x */
    public final void m965x(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4, Drawable drawable5, Drawable drawable6) {
        if (drawable5 != null || drawable6 != null) {
            Drawable[] compoundDrawablesRelative = this.f1152a.getCompoundDrawablesRelative();
            TextView textView = this.f1152a;
            if (drawable5 == null) {
                drawable5 = compoundDrawablesRelative[0];
            }
            if (drawable2 == null) {
                drawable2 = compoundDrawablesRelative[1];
            }
            if (drawable6 == null) {
                drawable6 = compoundDrawablesRelative[2];
            }
            if (drawable4 == null) {
                drawable4 = compoundDrawablesRelative[3];
            }
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable5, drawable2, drawable6, drawable4);
            return;
        }
        if (drawable == null && drawable2 == null && drawable3 == null && drawable4 == null) {
            return;
        }
        Drawable[] compoundDrawablesRelative2 = this.f1152a.getCompoundDrawablesRelative();
        Drawable drawable7 = compoundDrawablesRelative2[0];
        if (drawable7 != null || compoundDrawablesRelative2[2] != null) {
            TextView textView2 = this.f1152a;
            if (drawable2 == null) {
                drawable2 = compoundDrawablesRelative2[1];
            }
            Drawable drawable8 = compoundDrawablesRelative2[2];
            if (drawable4 == null) {
                drawable4 = compoundDrawablesRelative2[3];
            }
            textView2.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable7, drawable2, drawable8, drawable4);
            return;
        }
        Drawable[] compoundDrawables = this.f1152a.getCompoundDrawables();
        TextView textView3 = this.f1152a;
        if (drawable == null) {
            drawable = compoundDrawables[0];
        }
        if (drawable2 == null) {
            drawable2 = compoundDrawables[1];
        }
        if (drawable3 == null) {
            drawable3 = compoundDrawables[2];
        }
        if (drawable4 == null) {
            drawable4 = compoundDrawables[3];
        }
        textView3.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
    }

    /* renamed from: y */
    public final void m966y() {
        C0246o0 c0246o0 = this.f1159h;
        this.f1153b = c0246o0;
        this.f1154c = c0246o0;
        this.f1155d = c0246o0;
        this.f1156e = c0246o0;
        this.f1157f = c0246o0;
        this.f1158g = c0246o0;
    }

    /* renamed from: z */
    public void m967z(int i9, float f9) {
        if (InterfaceC0327b.f1850a || m953l()) {
            return;
        }
        m941A(i9, f9);
    }
}
