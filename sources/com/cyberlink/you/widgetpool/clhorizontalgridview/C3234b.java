package com.cyberlink.you.widgetpool.clhorizontalgridview;

import android.content.Context;
import android.util.Log;
import android.view.ViewConfiguration;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

/* renamed from: com.cyberlink.you.widgetpool.clhorizontalgridview.b */
/* loaded from: classes.dex */
public class C3234b {

    /* renamed from: f */
    public static float f15032f = 8.0f;

    /* renamed from: g */
    public static float f15033g = 1.0f / m17247n(1.0f);

    /* renamed from: a */
    public int f15034a;

    /* renamed from: b */
    public final a f15035b;

    /* renamed from: c */
    public final a f15036c;

    /* renamed from: d */
    public Interpolator f15037d;

    /* renamed from: e */
    public final boolean f15038e;

    /* renamed from: com.cyberlink.you.widgetpool.clhorizontalgridview.b$a */
    public static class a {

        /* renamed from: p */
        public static float f15039p = (float) (Math.log(0.78d) / Math.log(0.9d));

        /* renamed from: q */
        public static final float[] f15040q = new float[101];

        /* renamed from: r */
        public static final float[] f15041r = new float[101];

        /* renamed from: a */
        public int f15042a;

        /* renamed from: b */
        public int f15043b;

        /* renamed from: c */
        public int f15044c;

        /* renamed from: d */
        public int f15045d;

        /* renamed from: e */
        public float f15046e;

        /* renamed from: f */
        public float f15047f;

        /* renamed from: g */
        public long f15048g;

        /* renamed from: h */
        public int f15049h;

        /* renamed from: i */
        public int f15050i;

        /* renamed from: j */
        public int f15051j;

        /* renamed from: l */
        public int f15053l;

        /* renamed from: o */
        public float f15056o;

        /* renamed from: m */
        public float f15054m = ViewConfiguration.getScrollFriction();

        /* renamed from: n */
        public int f15055n = 0;

        /* renamed from: k */
        public boolean f15052k = true;

        static {
            float f9;
            float f10;
            float f11;
            float f12;
            float f13;
            float f14;
            float f15;
            float f16;
            float f17;
            float f18;
            float f19 = BitmapDescriptorFactory.HUE_RED;
            float f20 = 0.0f;
            for (int i9 = 0; i9 < 100; i9++) {
                float f21 = i9 / 100.0f;
                float f22 = 1.0f;
                while (true) {
                    f9 = 2.0f;
                    f10 = ((f22 - f19) / 2.0f) + f19;
                    f11 = 3.0f;
                    f12 = 1.0f - f10;
                    f13 = f10 * 3.0f * f12;
                    f14 = f10 * f10 * f10;
                    float f23 = (((f12 * 0.175f) + (f10 * 0.35000002f)) * f13) + f14;
                    if (Math.abs(f23 - f21) < 1.0E-5d) {
                        break;
                    } else if (f23 > f21) {
                        f22 = f10;
                    } else {
                        f19 = f10;
                    }
                }
                f15040q[i9] = (f13 * ((f12 * 0.5f) + f10)) + f14;
                float f24 = 1.0f;
                while (true) {
                    f15 = ((f24 - f20) / f9) + f20;
                    f16 = 1.0f - f15;
                    f17 = f15 * f11 * f16;
                    f18 = f15 * f15 * f15;
                    float f25 = (((f16 * 0.5f) + f15) * f17) + f18;
                    if (Math.abs(f25 - f21) < 1.0E-5d) {
                        break;
                    }
                    if (f25 > f21) {
                        f24 = f15;
                    } else {
                        f20 = f15;
                    }
                    f9 = 2.0f;
                    f11 = 3.0f;
                }
                f15041r[i9] = (f17 * ((f16 * 0.175f) + (f15 * 0.35000002f))) + f18;
            }
            float[] fArr = f15040q;
            f15041r[100] = 1.0f;
            fArr[100] = 1.0f;
        }

        public a(Context context) {
            this.f15056o = context.getResources().getDisplayMetrics().density * 160.0f * 386.0878f * 0.84f;
        }

        /* renamed from: m */
        public static float m17268m(int i9) {
            return i9 > 0 ? -2000.0f : 2000.0f;
        }

        /* renamed from: h */
        public final void m17269h(int i9, int i10, int i11) {
            float fAbs = Math.abs((i11 - i9) / (i10 - i9));
            int i12 = (int) (fAbs * 100.0f);
            if (i12 < 100) {
                float f9 = i12 / 100.0f;
                int i13 = i12 + 1;
                float[] fArr = f15041r;
                float f10 = fArr[i12];
                this.f15049h = (int) (this.f15049h * (f10 + (((fAbs - f9) / ((i13 / 100.0f) - f9)) * (fArr[i13] - f10))));
            }
        }

        /* renamed from: i */
        public boolean m17270i() {
            int i9 = this.f15055n;
            if (i9 != 0) {
                if (i9 == 1) {
                    return false;
                }
                if (i9 == 2) {
                    this.f15048g += this.f15049h;
                    m17284x(this.f15044c, this.f15042a, 0);
                }
            } else {
                if (this.f15049h >= this.f15050i) {
                    return false;
                }
                this.f15042a = this.f15044c;
                int i10 = (int) this.f15046e;
                this.f15045d = i10;
                this.f15047f = m17268m(i10);
                this.f15048g += this.f15049h;
                m17278r();
            }
            m17285y();
            return true;
        }

        /* renamed from: j */
        public void m17271j() {
            this.f15043b = this.f15044c;
            this.f15052k = true;
        }

        /* renamed from: k */
        public final void m17272k(int i9, int i10, int i11) {
            float f9 = this.f15047f;
            float fSqrt = (float) Math.sqrt((((((i11 * i11) / 2.0f) / Math.abs(f9)) + Math.abs(i10 - i9)) * 2.0d) / Math.abs(this.f15047f));
            this.f15048g -= (int) ((fSqrt - ((-i11) / f9)) * 1000.0f);
            this.f15042a = i10;
            this.f15045d = (int) ((-this.f15047f) * fSqrt);
        }

        /* renamed from: l */
        public void m17273l(int i9, int i10, int i11, int i12, int i13) {
            double dM17275o;
            this.f15053l = i13;
            this.f15052k = false;
            this.f15045d = i10;
            this.f15046e = i10;
            this.f15050i = 0;
            this.f15049h = 0;
            this.f15048g = AnimationUtils.currentAnimationTimeMillis();
            this.f15042a = i9;
            this.f15043b = i9;
            if (i9 > i12 || i9 < i11) {
                m17281u(i9, i11, i12, i10);
                return;
            }
            this.f15055n = 0;
            if (i10 != 0) {
                int iM17276p = m17276p(i10);
                this.f15050i = iM17276p;
                this.f15049h = iM17276p;
                dM17275o = m17275o(i10);
            } else {
                dM17275o = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            }
            int iSignum = (int) (dM17275o * Math.signum(r0));
            this.f15051j = iSignum;
            int i14 = i9 + iSignum;
            this.f15044c = i14;
            if (i14 < i11) {
                m17269h(this.f15042a, i14, i11);
                this.f15044c = i11;
            }
            int i15 = this.f15044c;
            if (i15 > i12) {
                m17269h(this.f15042a, i15, i12);
                this.f15044c = i12;
            }
        }

        /* renamed from: n */
        public final double m17274n(int i9) {
            return Math.log((Math.abs(i9) * 0.35f) / (this.f15054m * this.f15056o));
        }

        /* renamed from: o */
        public final double m17275o(int i9) {
            double dM17274n = m17274n(i9);
            float f9 = f15039p;
            return this.f15054m * this.f15056o * Math.exp((f9 / (f9 - 1.0d)) * dM17274n);
        }

        /* renamed from: p */
        public final int m17276p(int i9) {
            return (int) (Math.exp(m17274n(i9) / (f15039p - 1.0d)) * 1000.0d);
        }

        /* renamed from: q */
        public void m17277q(int i9, int i10, int i11) {
            if (this.f15055n == 0) {
                this.f15053l = i11;
                this.f15048g = AnimationUtils.currentAnimationTimeMillis();
                m17281u(i9, i10, i10, (int) this.f15046e);
            }
        }

        /* renamed from: r */
        public final void m17278r() {
            int i9 = this.f15045d;
            float fAbs = (i9 * i9) / (Math.abs(this.f15047f) * 2.0f);
            float fSignum = Math.signum(this.f15045d);
            int i10 = this.f15053l;
            if (fAbs > i10) {
                float f9 = -fSignum;
                int i11 = this.f15045d;
                this.f15047f = ((f9 * i11) * i11) / (i10 * 2.0f);
                fAbs = i10;
            }
            this.f15053l = (int) fAbs;
            this.f15055n = 2;
            int i12 = this.f15042a;
            int i13 = this.f15045d;
            if (i13 <= 0) {
                fAbs = -fAbs;
            }
            this.f15044c = i12 + ((int) fAbs);
            this.f15049h = -((int) ((i13 * 1000.0f) / this.f15047f));
        }

        /* renamed from: s */
        public void m17279s(float f9) {
            this.f15054m = f9;
        }

        /* renamed from: t */
        public boolean m17280t(int i9, int i10, int i11) {
            this.f15052k = true;
            this.f15044c = i9;
            this.f15042a = i9;
            this.f15045d = 0;
            this.f15048g = AnimationUtils.currentAnimationTimeMillis();
            this.f15049h = 0;
            if (i9 < i10) {
                m17284x(i9, i10, 0);
            } else if (i9 > i11) {
                m17284x(i9, i11, 0);
            }
            return !this.f15052k;
        }

        /* renamed from: u */
        public final void m17281u(int i9, int i10, int i11, int i12) {
            if (i9 > i10 && i9 < i11) {
                Log.e("OverScroller", "startAfterEdge called from a valid position");
                this.f15052k = true;
                return;
            }
            boolean z8 = i9 > i11;
            int i13 = z8 ? i11 : i10;
            if ((i9 - i13) * i12 >= 0) {
                m17282v(i9, i13, i12);
            } else if (m17275o(i12) > Math.abs(r4)) {
                m17273l(i9, i12, z8 ? i10 : i9, z8 ? i9 : i11, this.f15053l);
            } else {
                m17284x(i9, i13, i12);
            }
        }

        /* renamed from: v */
        public final void m17282v(int i9, int i10, int i11) {
            this.f15047f = m17268m(i11 == 0 ? i9 - i10 : i11);
            m17272k(i9, i10, i11);
            m17278r();
        }

        /* renamed from: w */
        public void m17283w(int i9, int i10, int i11) {
            this.f15052k = false;
            this.f15042a = i9;
            this.f15044c = i9 + i10;
            this.f15048g = AnimationUtils.currentAnimationTimeMillis();
            this.f15049h = i11;
            this.f15047f = BitmapDescriptorFactory.HUE_RED;
            this.f15045d = 0;
        }

        /* renamed from: x */
        public final void m17284x(int i9, int i10, int i11) {
            this.f15052k = false;
            this.f15055n = 1;
            this.f15042a = i9;
            this.f15044c = i10;
            int i12 = i9 - i10;
            this.f15047f = m17268m(i12);
            this.f15045d = -i12;
            this.f15053l = Math.abs(i12);
            this.f15049h = (int) (Math.sqrt((i12 * (-2.0d)) / this.f15047f) * 1000.0d);
        }

        /* renamed from: y */
        public boolean m17285y() {
            float f9;
            float f10;
            double d9;
            double d10;
            long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis() - this.f15048g;
            int i9 = this.f15049h;
            if (jCurrentAnimationTimeMillis > i9) {
                return false;
            }
            int i10 = this.f15055n;
            if (i10 == 0) {
                int i11 = this.f15050i;
                float f11 = jCurrentAnimationTimeMillis / i11;
                int i12 = (int) (f11 * 100.0f);
                if (i12 < 100) {
                    float f12 = i12 / 100.0f;
                    int i13 = i12 + 1;
                    float[] fArr = f15040q;
                    float f13 = fArr[i12];
                    f10 = (fArr[i13] - f13) / ((i13 / 100.0f) - f12);
                    f9 = f13 + ((f11 - f12) * f10);
                } else {
                    f9 = 1.0f;
                    f10 = BitmapDescriptorFactory.HUE_RED;
                }
                int i14 = this.f15051j;
                d9 = f9 * i14;
                this.f15046e = ((f10 * i14) / i11) * 1000.0f;
            } else {
                if (i10 != 1) {
                    if (i10 != 2) {
                        d10 = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                    } else {
                        float f14 = jCurrentAnimationTimeMillis / 1000.0f;
                        int i15 = this.f15045d;
                        float f15 = this.f15047f;
                        this.f15046e = i15 + (f15 * f14);
                        d10 = (i15 * f14) + (((f15 * f14) * f14) / 2.0f);
                    }
                    this.f15043b = this.f15042a + ((int) Math.round(d10));
                    return true;
                }
                float f16 = jCurrentAnimationTimeMillis / i9;
                float f17 = f16 * f16;
                float fSignum = Math.signum(this.f15045d);
                int i16 = this.f15053l;
                d9 = i16 * fSignum * ((3.0f * f17) - ((2.0f * f16) * f17));
                this.f15046e = fSignum * i16 * 6.0f * ((-f16) + f17);
            }
            d10 = d9;
            this.f15043b = this.f15042a + ((int) Math.round(d10));
            return true;
        }

        /* renamed from: z */
        public void m17286z(float f9) {
            this.f15043b = this.f15042a + Math.round(f9 * (this.f15044c - r0));
        }
    }

    public C3234b(Context context) {
        this(context, null);
    }

    /* renamed from: n */
    public static float m17247n(float f9) {
        float f10 = f9 * f15032f;
        return (f10 < 1.0f ? f10 - (1.0f - ((float) Math.exp(-f10))) : 0.36787945f + ((1.0f - ((float) Math.exp(1.0f - f10))) * 0.63212055f)) * f15033g;
    }

    /* renamed from: a */
    public void m17248a() {
        this.f15035b.m17271j();
        this.f15036c.m17271j();
    }

    /* renamed from: b */
    public boolean m17249b() {
        if (m17254g()) {
            return false;
        }
        int i9 = this.f15034a;
        if (i9 == 0) {
            long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis() - this.f15035b.f15048g;
            int i10 = this.f15035b.f15049h;
            if (jCurrentAnimationTimeMillis < i10) {
                float f9 = jCurrentAnimationTimeMillis / i10;
                Interpolator interpolator = this.f15037d;
                float fM17247n = interpolator == null ? m17247n(f9) : interpolator.getInterpolation(f9);
                this.f15035b.m17286z(fM17247n);
                this.f15036c.m17286z(fM17247n);
            } else {
                m17248a();
            }
        } else if (i9 == 1) {
            if (!this.f15035b.f15052k && !this.f15035b.m17285y() && !this.f15035b.m17270i()) {
                this.f15035b.m17271j();
            }
            if (!this.f15036c.f15052k && !this.f15036c.m17285y() && !this.f15036c.m17270i()) {
                this.f15036c.m17271j();
            }
        }
        return true;
    }

    /* renamed from: c */
    public void m17250c(int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16) {
        m17251d(i9, i10, i11, i12, i13, i14, i15, i16, 0, 0);
    }

    /* renamed from: d */
    public void m17251d(int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18) {
        int i19;
        int i20;
        int i21;
        int i22;
        if (this.f15038e && !m17254g()) {
            float f9 = this.f15035b.f15046e;
            float f10 = this.f15036c.f15046e;
            i19 = i11;
            float f11 = i19;
            if (Math.signum(f11) == Math.signum(f9)) {
                i20 = i12;
                float f12 = i20;
                if (Math.signum(f12) == Math.signum(f10)) {
                    i21 = (int) (f12 + f10);
                    i22 = (int) (f11 + f9);
                }
                this.f15034a = 1;
                this.f15035b.m17273l(i9, i22, i13, i14, i17);
                this.f15036c.m17273l(i10, i21, i15, i16, i18);
            }
            i21 = i20;
            i22 = i19;
            this.f15034a = 1;
            this.f15035b.m17273l(i9, i22, i13, i14, i17);
            this.f15036c.m17273l(i10, i21, i15, i16, i18);
        }
        i19 = i11;
        i20 = i12;
        i21 = i20;
        i22 = i19;
        this.f15034a = 1;
        this.f15035b.m17273l(i9, i22, i13, i14, i17);
        this.f15036c.m17273l(i10, i21, i15, i16, i18);
    }

    /* renamed from: e */
    public float m17252e() {
        return (float) Math.sqrt((this.f15035b.f15046e * this.f15035b.f15046e) + (this.f15036c.f15046e * this.f15036c.f15046e));
    }

    /* renamed from: f */
    public final int m17253f() {
        return this.f15035b.f15043b;
    }

    /* renamed from: g */
    public final boolean m17254g() {
        return this.f15035b.f15052k && this.f15036c.f15052k;
    }

    /* renamed from: h */
    public boolean m17255h(float f9, float f10) {
        return !m17254g() && Math.signum(f9) == Math.signum((float) (this.f15035b.f15044c - this.f15035b.f15042a)) && Math.signum(f10) == Math.signum((float) (this.f15036c.f15044c - this.f15036c.f15042a));
    }

    /* renamed from: i */
    public void m17256i(int i9, int i10, int i11) {
        this.f15035b.m17277q(i9, i10, i11);
    }

    /* renamed from: j */
    public final void m17257j(float f9) {
        this.f15035b.m17279s(f9);
        this.f15036c.m17279s(f9);
    }

    /* renamed from: k */
    public void m17258k(Interpolator interpolator) {
        this.f15037d = interpolator;
    }

    /* renamed from: l */
    public boolean m17259l(int i9, int i10, int i11, int i12, int i13, int i14) {
        this.f15034a = 1;
        return this.f15035b.m17280t(i9, i11, i12) || this.f15036c.m17280t(i10, i13, i14);
    }

    /* renamed from: m */
    public void m17260m(int i9, int i10, int i11, int i12, int i13) {
        this.f15034a = 0;
        this.f15035b.m17283w(i9, i11, i13);
        this.f15036c.m17283w(i10, i12, i13);
    }

    public C3234b(Context context, Interpolator interpolator) {
        this(context, interpolator, true);
    }

    public C3234b(Context context, Interpolator interpolator, boolean z8) {
        this.f15037d = interpolator;
        this.f15038e = z8;
        this.f15035b = new a(context);
        this.f15036c = new a(context);
    }
}
