package androidx.core.widget;

import android.content.res.Resources;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.firebase.iid.ServiceStarter;
import p042d0.C4647u;

/* renamed from: androidx.core.widget.a */
/* loaded from: classes.dex */
public abstract class AbstractViewOnTouchListenerC0326a implements View.OnTouchListener {

    /* renamed from: s */
    public static final int f1820s = ViewConfiguration.getTapTimeout();

    /* renamed from: d */
    public final View f1823d;

    /* renamed from: e */
    public Runnable f1824e;

    /* renamed from: h */
    public int f1827h;

    /* renamed from: i */
    public int f1828i;

    /* renamed from: m */
    public boolean f1832m;

    /* renamed from: n */
    public boolean f1833n;

    /* renamed from: o */
    public boolean f1834o;

    /* renamed from: p */
    public boolean f1835p;

    /* renamed from: q */
    public boolean f1836q;

    /* renamed from: r */
    public boolean f1837r;

    /* renamed from: b */
    public final a f1821b = new a();

    /* renamed from: c */
    public final Interpolator f1822c = new AccelerateInterpolator();

    /* renamed from: f */
    public float[] f1825f = {BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED};

    /* renamed from: g */
    public float[] f1826g = {Float.MAX_VALUE, Float.MAX_VALUE};

    /* renamed from: j */
    public float[] f1829j = {BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED};

    /* renamed from: k */
    public float[] f1830k = {BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED};

    /* renamed from: l */
    public float[] f1831l = {Float.MAX_VALUE, Float.MAX_VALUE};

    /* renamed from: androidx.core.widget.a$a */
    public static class a {

        /* renamed from: a */
        public int f1838a;

        /* renamed from: b */
        public int f1839b;

        /* renamed from: c */
        public float f1840c;

        /* renamed from: d */
        public float f1841d;

        /* renamed from: j */
        public float f1847j;

        /* renamed from: k */
        public int f1848k;

        /* renamed from: e */
        public long f1842e = Long.MIN_VALUE;

        /* renamed from: i */
        public long f1846i = -1;

        /* renamed from: f */
        public long f1843f = 0;

        /* renamed from: g */
        public int f1844g = 0;

        /* renamed from: h */
        public int f1845h = 0;

        /* renamed from: a */
        public void m1577a() {
            if (this.f1843f == 0) {
                throw new RuntimeException("Cannot compute scroll delta before calling start()");
            }
            long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            float fM1583g = m1583g(m1581e(jCurrentAnimationTimeMillis));
            long j9 = jCurrentAnimationTimeMillis - this.f1843f;
            this.f1843f = jCurrentAnimationTimeMillis;
            float f9 = j9 * fM1583g;
            this.f1844g = (int) (this.f1840c * f9);
            this.f1845h = (int) (f9 * this.f1841d);
        }

        /* renamed from: b */
        public int m1578b() {
            return this.f1844g;
        }

        /* renamed from: c */
        public int m1579c() {
            return this.f1845h;
        }

        /* renamed from: d */
        public int m1580d() {
            float f9 = this.f1840c;
            return (int) (f9 / Math.abs(f9));
        }

        /* renamed from: e */
        public final float m1581e(long j9) {
            if (j9 < this.f1842e) {
                return BitmapDescriptorFactory.HUE_RED;
            }
            long j10 = this.f1846i;
            if (j10 < 0 || j9 < j10) {
                return AbstractViewOnTouchListenerC0326a.m1555e((j9 - r0) / this.f1838a, BitmapDescriptorFactory.HUE_RED, 1.0f) * 0.5f;
            }
            float f9 = this.f1847j;
            return (1.0f - f9) + (f9 * AbstractViewOnTouchListenerC0326a.m1555e((j9 - j10) / this.f1848k, BitmapDescriptorFactory.HUE_RED, 1.0f));
        }

        /* renamed from: f */
        public int m1582f() {
            float f9 = this.f1841d;
            return (int) (f9 / Math.abs(f9));
        }

        /* renamed from: g */
        public final float m1583g(float f9) {
            return ((-4.0f) * f9 * f9) + (f9 * 4.0f);
        }

        /* renamed from: h */
        public boolean m1584h() {
            return this.f1846i > 0 && AnimationUtils.currentAnimationTimeMillis() > this.f1846i + ((long) this.f1848k);
        }

        /* renamed from: i */
        public void m1585i() {
            long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            this.f1848k = AbstractViewOnTouchListenerC0326a.m1556f((int) (jCurrentAnimationTimeMillis - this.f1842e), 0, this.f1839b);
            this.f1847j = m1581e(jCurrentAnimationTimeMillis);
            this.f1846i = jCurrentAnimationTimeMillis;
        }

        /* renamed from: j */
        public void m1586j(int i9) {
            this.f1839b = i9;
        }

        /* renamed from: k */
        public void m1587k(int i9) {
            this.f1838a = i9;
        }

        /* renamed from: l */
        public void m1588l(float f9, float f10) {
            this.f1840c = f9;
            this.f1841d = f10;
        }

        /* renamed from: m */
        public void m1589m() {
            long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            this.f1842e = jCurrentAnimationTimeMillis;
            this.f1846i = -1L;
            this.f1843f = jCurrentAnimationTimeMillis;
            this.f1847j = 0.5f;
            this.f1844g = 0;
            this.f1845h = 0;
        }
    }

    /* renamed from: androidx.core.widget.a$b */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AbstractViewOnTouchListenerC0326a abstractViewOnTouchListenerC0326a = AbstractViewOnTouchListenerC0326a.this;
            if (abstractViewOnTouchListenerC0326a.f1835p) {
                if (abstractViewOnTouchListenerC0326a.f1833n) {
                    abstractViewOnTouchListenerC0326a.f1833n = false;
                    abstractViewOnTouchListenerC0326a.f1821b.m1589m();
                }
                a aVar = AbstractViewOnTouchListenerC0326a.this.f1821b;
                if (aVar.m1584h() || !AbstractViewOnTouchListenerC0326a.this.m1575u()) {
                    AbstractViewOnTouchListenerC0326a.this.f1835p = false;
                    return;
                }
                AbstractViewOnTouchListenerC0326a abstractViewOnTouchListenerC0326a2 = AbstractViewOnTouchListenerC0326a.this;
                if (abstractViewOnTouchListenerC0326a2.f1834o) {
                    abstractViewOnTouchListenerC0326a2.f1834o = false;
                    abstractViewOnTouchListenerC0326a2.m1559c();
                }
                aVar.m1577a();
                AbstractViewOnTouchListenerC0326a.this.mo1564j(aVar.m1578b(), aVar.m1579c());
                C4647u.m18525U(AbstractViewOnTouchListenerC0326a.this.f1823d, this);
            }
        }
    }

    public AbstractViewOnTouchListenerC0326a(View view) {
        this.f1823d = view;
        float f9 = Resources.getSystem().getDisplayMetrics().density;
        float f10 = (int) ((1575.0f * f9) + 0.5f);
        m1569o(f10, f10);
        float f11 = (int) ((f9 * 315.0f) + 0.5f);
        m1570p(f11, f11);
        m1566l(1);
        m1568n(Float.MAX_VALUE, Float.MAX_VALUE);
        m1573s(0.2f, 0.2f);
        m1574t(1.0f, 1.0f);
        m1565k(f1820s);
        m1572r(ServiceStarter.ERROR_UNKNOWN);
        m1571q(ServiceStarter.ERROR_UNKNOWN);
    }

    /* renamed from: e */
    public static float m1555e(float f9, float f10, float f11) {
        return f9 > f11 ? f11 : f9 < f10 ? f10 : f9;
    }

    /* renamed from: f */
    public static int m1556f(int i9, int i10, int i11) {
        return i9 > i11 ? i11 : i9 < i10 ? i10 : i9;
    }

    /* renamed from: a */
    public abstract boolean mo1557a(int i9);

    /* renamed from: b */
    public abstract boolean mo1558b(int i9);

    /* renamed from: c */
    public void m1559c() {
        long jUptimeMillis = SystemClock.uptimeMillis();
        MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 0);
        this.f1823d.onTouchEvent(motionEventObtain);
        motionEventObtain.recycle();
    }

    /* renamed from: d */
    public final float m1560d(int i9, float f9, float f10, float f11) {
        float fM1562h = m1562h(this.f1825f[i9], f10, this.f1826g[i9], f9);
        if (fM1562h == BitmapDescriptorFactory.HUE_RED) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        float f12 = this.f1829j[i9];
        float f13 = this.f1830k[i9];
        float f14 = this.f1831l[i9];
        float f15 = f12 * f11;
        return fM1562h > BitmapDescriptorFactory.HUE_RED ? m1555e(fM1562h * f15, f13, f14) : -m1555e((-fM1562h) * f15, f13, f14);
    }

    /* renamed from: g */
    public final float m1561g(float f9, float f10) {
        if (f10 == BitmapDescriptorFactory.HUE_RED) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        int i9 = this.f1827h;
        if (i9 == 0 || i9 == 1) {
            if (f9 < f10) {
                if (f9 >= BitmapDescriptorFactory.HUE_RED) {
                    return 1.0f - (f9 / f10);
                }
                if (this.f1835p && i9 == 1) {
                    return 1.0f;
                }
            }
        } else if (i9 == 2 && f9 < BitmapDescriptorFactory.HUE_RED) {
            return f9 / (-f10);
        }
        return BitmapDescriptorFactory.HUE_RED;
    }

    /* renamed from: h */
    public final float m1562h(float f9, float f10, float f11, float f12) {
        float interpolation;
        float fM1555e = m1555e(f9 * f10, BitmapDescriptorFactory.HUE_RED, f11);
        float fM1561g = m1561g(f10 - f12, fM1555e) - m1561g(f12, fM1555e);
        if (fM1561g < BitmapDescriptorFactory.HUE_RED) {
            interpolation = -this.f1822c.getInterpolation(-fM1561g);
        } else {
            if (fM1561g <= BitmapDescriptorFactory.HUE_RED) {
                return BitmapDescriptorFactory.HUE_RED;
            }
            interpolation = this.f1822c.getInterpolation(fM1561g);
        }
        return m1555e(interpolation, -1.0f, 1.0f);
    }

    /* renamed from: i */
    public final void m1563i() {
        if (this.f1833n) {
            this.f1835p = false;
        } else {
            this.f1821b.m1585i();
        }
    }

    /* renamed from: j */
    public abstract void mo1564j(int i9, int i10);

    /* renamed from: k */
    public AbstractViewOnTouchListenerC0326a m1565k(int i9) {
        this.f1828i = i9;
        return this;
    }

    /* renamed from: l */
    public AbstractViewOnTouchListenerC0326a m1566l(int i9) {
        this.f1827h = i9;
        return this;
    }

    /* renamed from: m */
    public AbstractViewOnTouchListenerC0326a m1567m(boolean z8) {
        if (this.f1836q && !z8) {
            m1563i();
        }
        this.f1836q = z8;
        return this;
    }

    /* renamed from: n */
    public AbstractViewOnTouchListenerC0326a m1568n(float f9, float f10) {
        float[] fArr = this.f1826g;
        fArr[0] = f9;
        fArr[1] = f10;
        return this;
    }

    /* renamed from: o */
    public AbstractViewOnTouchListenerC0326a m1569o(float f9, float f10) {
        float[] fArr = this.f1831l;
        fArr[0] = f9 / 1000.0f;
        fArr[1] = f10 / 1000.0f;
        return this;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0016  */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (!this.f1836q) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 0) {
            if (actionMasked == 1) {
                m1563i();
            } else if (actionMasked != 2) {
                if (actionMasked == 3) {
                }
            }
            return this.f1837r && this.f1835p;
        }
        this.f1834o = true;
        this.f1832m = false;
        this.f1821b.m1588l(m1560d(0, motionEvent.getX(), view.getWidth(), this.f1823d.getWidth()), m1560d(1, motionEvent.getY(), view.getHeight(), this.f1823d.getHeight()));
        if (!this.f1835p && m1575u()) {
            m1576v();
        }
        if (this.f1837r) {
            return false;
        }
    }

    /* renamed from: p */
    public AbstractViewOnTouchListenerC0326a m1570p(float f9, float f10) {
        float[] fArr = this.f1830k;
        fArr[0] = f9 / 1000.0f;
        fArr[1] = f10 / 1000.0f;
        return this;
    }

    /* renamed from: q */
    public AbstractViewOnTouchListenerC0326a m1571q(int i9) {
        this.f1821b.m1586j(i9);
        return this;
    }

    /* renamed from: r */
    public AbstractViewOnTouchListenerC0326a m1572r(int i9) {
        this.f1821b.m1587k(i9);
        return this;
    }

    /* renamed from: s */
    public AbstractViewOnTouchListenerC0326a m1573s(float f9, float f10) {
        float[] fArr = this.f1825f;
        fArr[0] = f9;
        fArr[1] = f10;
        return this;
    }

    /* renamed from: t */
    public AbstractViewOnTouchListenerC0326a m1574t(float f9, float f10) {
        float[] fArr = this.f1829j;
        fArr[0] = f9 / 1000.0f;
        fArr[1] = f10 / 1000.0f;
        return this;
    }

    /* renamed from: u */
    public boolean m1575u() {
        a aVar = this.f1821b;
        int iM1582f = aVar.m1582f();
        int iM1580d = aVar.m1580d();
        return (iM1582f != 0 && mo1558b(iM1582f)) || (iM1580d != 0 && mo1557a(iM1580d));
    }

    /* renamed from: v */
    public final void m1576v() {
        int i9;
        if (this.f1824e == null) {
            this.f1824e = new b();
        }
        this.f1835p = true;
        this.f1833n = true;
        if (this.f1832m || (i9 = this.f1828i) <= 0) {
            this.f1824e.run();
        } else {
            C4647u.m18526V(this.f1823d, this.f1824e, i9);
        }
        this.f1832m = true;
    }
}
