package com.cyberlink.you.widgetpool.clhorizontalgridview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import com.cyberlink.p030U.R;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* renamed from: com.cyberlink.you.widgetpool.clhorizontalgridview.a */
/* loaded from: classes.dex */
public class C3233a {

    /* renamed from: A */
    public final int f15003A;

    /* renamed from: B */
    public final int f15004B;

    /* renamed from: C */
    public final int f15005C;

    /* renamed from: a */
    public final Drawable f15006a;

    /* renamed from: b */
    public final Drawable f15007b;

    /* renamed from: c */
    public int f15008c;

    /* renamed from: d */
    public int f15009d;

    /* renamed from: e */
    public int f15010e;

    /* renamed from: f */
    public int f15011f;

    /* renamed from: g */
    public final int f15012g;

    /* renamed from: h */
    public float f15013h;

    /* renamed from: i */
    public float f15014i;

    /* renamed from: j */
    public float f15015j;

    /* renamed from: k */
    public float f15016k;

    /* renamed from: l */
    public float f15017l;

    /* renamed from: m */
    public float f15018m;

    /* renamed from: n */
    public float f15019n;

    /* renamed from: o */
    public float f15020o;

    /* renamed from: p */
    public float f15021p;

    /* renamed from: q */
    public float f15022q;

    /* renamed from: r */
    public float f15023r;

    /* renamed from: s */
    public float f15024s;

    /* renamed from: t */
    public long f15025t;

    /* renamed from: u */
    public float f15026u;

    /* renamed from: v */
    public final Interpolator f15027v;

    /* renamed from: x */
    public float f15029x;

    /* renamed from: z */
    public final int f15031z;

    /* renamed from: w */
    public int f15028w = 0;

    /* renamed from: y */
    public final Rect f15030y = new Rect();

    public C3233a(Context context) throws Resources.NotFoundException {
        Resources resources = context.getResources();
        Drawable drawable = resources.getDrawable(R.drawable.overscroll_edge_h);
        this.f15006a = drawable;
        Drawable drawable2 = resources.getDrawable(R.drawable.overscroll_glow_h);
        this.f15007b = drawable2;
        this.f15031z = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable2.getIntrinsicHeight();
        this.f15003A = intrinsicHeight;
        int intrinsicWidth = drawable2.getIntrinsicWidth();
        this.f15004B = intrinsicWidth;
        this.f15005C = (int) (Math.min((((intrinsicWidth * 4.0f) * intrinsicWidth) / intrinsicHeight) * 0.6f, intrinsicWidth * 4.0f) + 0.5f);
        this.f15012g = (int) ((resources.getDisplayMetrics().density * 300.0f) + 0.5f);
        this.f15027v = new DecelerateInterpolator();
    }

    /* renamed from: a */
    public boolean m17237a(Canvas canvas) {
        m17246j();
        this.f15007b.setAlpha((int) (Math.max(BitmapDescriptorFactory.HUE_RED, Math.min(this.f15015j, 1.0f)) * 255.0f));
        int i9 = this.f15004B;
        int iMin = (int) Math.min((((i9 * this.f15016k) * i9) / this.f15003A) * 0.6f, i9 * 4.0f);
        int i10 = this.f15009d;
        int i11 = this.f15012g;
        if (i10 < i11) {
            int i12 = (i10 - i11) / 2;
            this.f15007b.setBounds(0, i12, iMin, i10 - i12);
        } else {
            this.f15007b.setBounds(0, 0, iMin, i10);
        }
        this.f15007b.draw(canvas);
        this.f15006a.setAlpha((int) (Math.max(BitmapDescriptorFactory.HUE_RED, Math.min(this.f15013h, 1.0f)) * 255.0f));
        int i13 = (int) (this.f15031z * this.f15014i);
        int i14 = this.f15009d;
        int i15 = this.f15012g;
        if (i14 < i15) {
            int i16 = (i14 - i15) / 2;
            this.f15006a.setBounds(0, i16, i13, i14 - i16);
        } else {
            this.f15006a.setBounds(0, 0, i13, i14);
        }
        this.f15006a.draw(canvas);
        if (this.f15028w == 3 && iMin == 0 && i13 == 0) {
            this.f15028w = 0;
        }
        return this.f15028w != 0;
    }

    /* renamed from: b */
    public void m17238b() {
        this.f15028w = 0;
    }

    /* renamed from: c */
    public Rect m17239c(boolean z8) {
        this.f15030y.set(0, 0, this.f15005C, this.f15009d);
        this.f15030y.offset(this.f15010e - (z8 ? this.f15005C : 0), this.f15011f);
        return this.f15030y;
    }

    /* renamed from: d */
    public boolean m17240d() {
        return this.f15028w == 0;
    }

    /* renamed from: e */
    public void m17241e(int i9) {
        this.f15028w = 2;
        int iMax = Math.max(100, Math.abs(i9));
        this.f15025t = AnimationUtils.currentAnimationTimeMillis();
        this.f15026u = (iMax * 0.03f) + 0.1f;
        this.f15017l = BitmapDescriptorFactory.HUE_RED;
        this.f15019n = BitmapDescriptorFactory.HUE_RED;
        this.f15014i = BitmapDescriptorFactory.HUE_RED;
        this.f15021p = 0.5f;
        this.f15023r = BitmapDescriptorFactory.HUE_RED;
        this.f15018m = Math.max(0, Math.min(r0, 1));
        this.f15020o = Math.max(0.5f, Math.min(iMax * 8, 1.0f));
        this.f15024s = Math.min(((iMax / 100) * iMax * 1.5E-4f) + 0.025f, 1.75f);
        this.f15022q = Math.max(this.f15021p, Math.min(iMax * 16 * 1.0E-5f, 1.0f));
    }

    /* renamed from: f */
    public void m17242f(float f9) {
        long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        int i9 = this.f15028w;
        if (i9 != 4 || jCurrentAnimationTimeMillis - this.f15025t >= this.f15026u) {
            if (i9 != 1) {
                this.f15016k = 1.0f;
            }
            this.f15028w = 1;
            this.f15025t = jCurrentAnimationTimeMillis;
            this.f15026u = 167.0f;
            float f10 = this.f15029x + f9;
            this.f15029x = f10;
            float fAbs = Math.abs(f10);
            float fMax = Math.max(0.6f, Math.min(fAbs, 1.0f));
            this.f15017l = fMax;
            this.f15013h = fMax;
            float fMax2 = Math.max(0.5f, Math.min(fAbs * 7.0f, 1.0f));
            this.f15019n = fMax2;
            this.f15014i = fMax2;
            float fMin = Math.min(1.0f, this.f15015j + (Math.abs(f9) * 1.1f));
            this.f15021p = fMin;
            this.f15015j = fMin;
            float fAbs2 = Math.abs(f9);
            if (f9 > BitmapDescriptorFactory.HUE_RED && this.f15029x < BitmapDescriptorFactory.HUE_RED) {
                fAbs2 = -fAbs2;
            }
            if (this.f15029x == BitmapDescriptorFactory.HUE_RED) {
                this.f15016k = BitmapDescriptorFactory.HUE_RED;
            }
            float fMin2 = Math.min(4.0f, Math.max(BitmapDescriptorFactory.HUE_RED, this.f15016k + (fAbs2 * 7.0f)));
            this.f15023r = fMin2;
            this.f15016k = fMin2;
            this.f15018m = this.f15013h;
            this.f15020o = this.f15014i;
            this.f15022q = this.f15015j;
            this.f15024s = fMin2;
        }
    }

    /* renamed from: g */
    public void m17243g() {
        this.f15029x = BitmapDescriptorFactory.HUE_RED;
        int i9 = this.f15028w;
        if (i9 == 1 || i9 == 4) {
            this.f15028w = 3;
            this.f15017l = this.f15013h;
            this.f15019n = this.f15014i;
            this.f15021p = this.f15015j;
            this.f15023r = this.f15016k;
            this.f15018m = BitmapDescriptorFactory.HUE_RED;
            this.f15020o = BitmapDescriptorFactory.HUE_RED;
            this.f15022q = BitmapDescriptorFactory.HUE_RED;
            this.f15024s = BitmapDescriptorFactory.HUE_RED;
            this.f15025t = AnimationUtils.currentAnimationTimeMillis();
            this.f15026u = 1000.0f;
        }
    }

    /* renamed from: h */
    public void m17244h(int i9, int i10) {
        this.f15010e = i9;
        this.f15011f = i10;
    }

    /* renamed from: i */
    public void m17245i(int i9, int i10) {
        this.f15008c = i9;
        this.f15009d = i10;
    }

    /* renamed from: j */
    public final void m17246j() {
        float fMin = Math.min((AnimationUtils.currentAnimationTimeMillis() - this.f15025t) / this.f15026u, 1.0f);
        float interpolation = this.f15027v.getInterpolation(fMin);
        float f9 = this.f15017l;
        this.f15013h = f9 + ((this.f15018m - f9) * interpolation);
        float f10 = this.f15019n;
        float f11 = this.f15020o;
        this.f15014i = ((f11 - f10) * interpolation) + f10;
        float f12 = this.f15021p;
        this.f15015j = f12 + ((this.f15022q - f12) * interpolation);
        float f13 = this.f15023r;
        float f14 = this.f15024s;
        this.f15016k = f13 + ((f14 - f13) * interpolation);
        if (fMin >= 0.999f) {
            int i9 = this.f15028w;
            if (i9 == 1) {
                this.f15028w = 4;
                this.f15025t = AnimationUtils.currentAnimationTimeMillis();
                this.f15026u = 1000.0f;
                this.f15017l = this.f15013h;
                this.f15019n = this.f15014i;
                this.f15021p = this.f15015j;
                this.f15023r = this.f15016k;
                this.f15018m = BitmapDescriptorFactory.HUE_RED;
                this.f15020o = BitmapDescriptorFactory.HUE_RED;
                this.f15022q = BitmapDescriptorFactory.HUE_RED;
                this.f15024s = BitmapDescriptorFactory.HUE_RED;
                return;
            }
            if (i9 != 2) {
                if (i9 == 3) {
                    this.f15028w = 0;
                    return;
                } else {
                    if (i9 != 4) {
                        return;
                    }
                    this.f15014i = f10 + ((f11 - f10) * interpolation * (f14 != BitmapDescriptorFactory.HUE_RED ? 1.0f / (f14 * f14) : Float.MAX_VALUE));
                    this.f15028w = 3;
                    return;
                }
            }
            this.f15028w = 3;
            this.f15025t = AnimationUtils.currentAnimationTimeMillis();
            this.f15026u = 1000.0f;
            this.f15017l = this.f15013h;
            this.f15019n = this.f15014i;
            this.f15021p = this.f15015j;
            this.f15023r = this.f15016k;
            this.f15018m = BitmapDescriptorFactory.HUE_RED;
            this.f15020o = BitmapDescriptorFactory.HUE_RED;
            this.f15022q = BitmapDescriptorFactory.HUE_RED;
            this.f15024s = BitmapDescriptorFactory.HUE_RED;
        }
    }
}
