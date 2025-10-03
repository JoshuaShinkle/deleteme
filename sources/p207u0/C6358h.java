package p207u0;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.SingleRequest;
import java.util.ArrayList;
import java.util.List;
import p012b1.AbstractC0588c;
import p190s1.C6244a;
import p190s1.C6250g;
import p190s1.C6252i;
import p190s1.InterfaceC6246c;
import p190s1.InterfaceC6247d;
import p190s1.InterfaceC6249f;
import p190s1.InterfaceFutureC6245b;
import p190s1.RunnableC6248e;
import p199t1.AbstractC6284i;
import p199t1.InterfaceC6283h;
import p217v1.C6449a;
import p226w1.C6516i;
import p226w1.C6517j;

/* renamed from: u0.h */
/* loaded from: classes.dex */
public class C6358h<TranscodeType> implements Cloneable {

    /* renamed from: r */
    public static final C6250g f21461r = new C6250g().mo23523h(AbstractC0588c.f3109c).mo23514Z(Priority.LOW).mo23522g0(true);

    /* renamed from: b */
    public final Context f21462b;

    /* renamed from: c */
    public final C6359i f21463c;

    /* renamed from: d */
    public final Class<TranscodeType> f21464d;

    /* renamed from: e */
    public final C6250g f21465e;

    /* renamed from: f */
    public final ComponentCallbacks2C6355e f21466f;

    /* renamed from: g */
    public final C6357g f21467g;

    /* renamed from: h */
    public C6250g f21468h;

    /* renamed from: i */
    public AbstractC6360j<?, ? super TranscodeType> f21469i;

    /* renamed from: j */
    public Object f21470j;

    /* renamed from: k */
    public List<InterfaceC6249f<TranscodeType>> f21471k;

    /* renamed from: l */
    public C6358h<TranscodeType> f21472l;

    /* renamed from: m */
    public C6358h<TranscodeType> f21473m;

    /* renamed from: n */
    public Float f21474n;

    /* renamed from: o */
    public boolean f21475o;

    /* renamed from: p */
    public boolean f21476p;

    /* renamed from: q */
    public boolean f21477q;

    /* renamed from: u0.h$a */
    public class a implements Runnable {

        /* renamed from: b */
        public final /* synthetic */ RunnableC6248e f21478b;

        public a(RunnableC6248e runnableC6248e) {
            this.f21478b = runnableC6248e;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f21478b.isCancelled()) {
                return;
            }
            C6358h c6358h = C6358h.this;
            RunnableC6248e runnableC6248e = this.f21478b;
            c6358h.m24420l(runnableC6248e, runnableC6248e);
        }
    }

    /* renamed from: u0.h$b */
    public static /* synthetic */ class b {

        /* renamed from: a */
        public static final /* synthetic */ int[] f21480a;

        /* renamed from: b */
        public static final /* synthetic */ int[] f21481b;

        static {
            int[] iArr = new int[Priority.values().length];
            f21481b = iArr;
            try {
                iArr[Priority.LOW.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f21481b[Priority.NORMAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f21481b[Priority.HIGH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f21481b[Priority.IMMEDIATE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[ImageView.ScaleType.values().length];
            f21480a = iArr2;
            try {
                iArr2[ImageView.ScaleType.CENTER_CROP.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f21480a[ImageView.ScaleType.CENTER_INSIDE.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f21480a[ImageView.ScaleType.FIT_CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f21480a[ImageView.ScaleType.FIT_START.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f21480a[ImageView.ScaleType.FIT_END.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f21480a[ImageView.ScaleType.FIT_XY.ordinal()] = 6;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f21480a[ImageView.ScaleType.CENTER.ordinal()] = 7;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f21480a[ImageView.ScaleType.MATRIX.ordinal()] = 8;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    public C6358h(ComponentCallbacks2C6355e componentCallbacks2C6355e, C6359i c6359i, Class<TranscodeType> cls, Context context) {
        this.f21475o = true;
        this.f21466f = componentCallbacks2C6355e;
        this.f21463c = c6359i;
        this.f21464d = cls;
        C6250g c6250gM24433n = c6359i.m24433n();
        this.f21465e = c6250gM24433n;
        this.f21462b = context;
        this.f21469i = c6359i.m24434o(cls);
        this.f21468h = c6250gM24433n;
        this.f21467g = componentCallbacks2C6355e.m24394j();
    }

    /* renamed from: a */
    public C6358h<TranscodeType> mo23565a(InterfaceC6249f<TranscodeType> interfaceC6249f) {
        if (interfaceC6249f != null) {
            if (this.f21471k == null) {
                this.f21471k = new ArrayList();
            }
            this.f21471k.add(interfaceC6249f);
        }
        return this;
    }

    /* renamed from: b */
    public C6358h<TranscodeType> mo23566b(C6250g c6250g) {
        C6516i.m24938d(c6250g);
        this.f21468h = m24417i().mo23515a(c6250g);
        return this;
    }

    /* renamed from: c */
    public final InterfaceC6246c m24413c(InterfaceC6283h<TranscodeType> interfaceC6283h, InterfaceC6249f<TranscodeType> interfaceC6249f, C6250g c6250g) {
        return m24414d(interfaceC6283h, interfaceC6249f, null, this.f21469i, c6250g.m23947x(), c6250g.m23944u(), c6250g.m23943t(), c6250g);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: d */
    public final InterfaceC6246c m24414d(InterfaceC6283h<TranscodeType> interfaceC6283h, InterfaceC6249f<TranscodeType> interfaceC6249f, InterfaceC6247d interfaceC6247d, AbstractC6360j<?, ? super TranscodeType> abstractC6360j, Priority priority, int i9, int i10, C6250g c6250g) {
        InterfaceC6247d interfaceC6247d2;
        InterfaceC6247d c6244a;
        if (this.f21473m != null) {
            c6244a = new C6244a(interfaceC6247d);
            interfaceC6247d2 = c6244a;
        } else {
            interfaceC6247d2 = null;
            c6244a = interfaceC6247d;
        }
        InterfaceC6246c interfaceC6246cM24415e = m24415e(interfaceC6283h, interfaceC6249f, c6244a, abstractC6360j, priority, i9, i10, c6250g);
        if (interfaceC6247d2 == null) {
            return interfaceC6246cM24415e;
        }
        int iM23944u = this.f21473m.f21468h.m23944u();
        int iM23943t = this.f21473m.f21468h.m23943t();
        if (C6517j.m24959t(i9, i10) && !this.f21473m.f21468h.m23924N()) {
            iM23944u = c6250g.m23944u();
            iM23943t = c6250g.m23943t();
        }
        C6358h<TranscodeType> c6358h = this.f21473m;
        C6244a c6244a2 = interfaceC6247d2;
        c6244a2.m23897r(interfaceC6246cM24415e, c6358h.m24414d(interfaceC6283h, interfaceC6249f, interfaceC6247d2, c6358h.f21469i, c6358h.f21468h.m23947x(), iM23944u, iM23943t, this.f21473m.f21468h));
        return c6244a2;
    }

    /* renamed from: e */
    public final InterfaceC6246c m24415e(InterfaceC6283h<TranscodeType> interfaceC6283h, InterfaceC6249f<TranscodeType> interfaceC6249f, InterfaceC6247d interfaceC6247d, AbstractC6360j<?, ? super TranscodeType> abstractC6360j, Priority priority, int i9, int i10, C6250g c6250g) {
        C6358h<TranscodeType> c6358h = this.f21472l;
        if (c6358h == null) {
            if (this.f21474n == null) {
                return m24427v(interfaceC6283h, interfaceC6249f, c6250g, interfaceC6247d, abstractC6360j, priority, i9, i10);
            }
            C6252i c6252i = new C6252i(interfaceC6247d);
            c6252i.m23954q(m24427v(interfaceC6283h, interfaceC6249f, c6250g, c6252i, abstractC6360j, priority, i9, i10), m24427v(interfaceC6283h, interfaceC6249f, c6250g.clone().mo23521f0(this.f21474n.floatValue()), c6252i, abstractC6360j, m24418j(priority), i9, i10));
            return c6252i;
        }
        if (this.f21477q) {
            throw new IllegalStateException("You cannot use a request as both the main request and a thumbnail, consider using clone() on the request(s) passed to thumbnail()");
        }
        AbstractC6360j<?, ? super TranscodeType> abstractC6360j2 = c6358h.f21475o ? abstractC6360j : c6358h.f21469i;
        Priority priorityM23947x = c6358h.f21468h.m23918G() ? this.f21472l.f21468h.m23947x() : m24418j(priority);
        int iM23944u = this.f21472l.f21468h.m23944u();
        int iM23943t = this.f21472l.f21468h.m23943t();
        if (C6517j.m24959t(i9, i10) && !this.f21472l.f21468h.m23924N()) {
            iM23944u = c6250g.m23944u();
            iM23943t = c6250g.m23943t();
        }
        C6252i c6252i2 = new C6252i(interfaceC6247d);
        InterfaceC6246c interfaceC6246cM24427v = m24427v(interfaceC6283h, interfaceC6249f, c6250g, c6252i2, abstractC6360j, priority, i9, i10);
        this.f21477q = true;
        C6358h<TranscodeType> c6358h2 = this.f21472l;
        InterfaceC6246c interfaceC6246cM24414d = c6358h2.m24414d(interfaceC6283h, interfaceC6249f, c6252i2, abstractC6360j2, priorityM23947x, iM23944u, iM23943t, c6358h2.f21468h);
        this.f21477q = false;
        c6252i2.m23954q(interfaceC6246cM24427v, interfaceC6246cM24414d);
        return c6252i2;
    }

    @Override // 
    /* renamed from: g */
    public C6358h<TranscodeType> mo23567g() {
        try {
            C6358h<TranscodeType> c6358h = (C6358h) super.clone();
            c6358h.f21468h = c6358h.f21468h.clone();
            c6358h.f21469i = c6358h.f21469i.clone();
            return c6358h;
        } catch (CloneNotSupportedException e9) {
            throw new RuntimeException(e9);
        }
    }

    /* renamed from: h */
    public C6358h<TranscodeType> m24416h(C6358h<TranscodeType> c6358h) {
        this.f21473m = c6358h;
        return this;
    }

    /* renamed from: i */
    public C6250g m24417i() {
        C6250g c6250g = this.f21465e;
        C6250g c6250g2 = this.f21468h;
        return c6250g == c6250g2 ? c6250g2.clone() : c6250g2;
    }

    /* renamed from: j */
    public final Priority m24418j(Priority priority) {
        int i9 = b.f21481b[priority.ordinal()];
        if (i9 == 1) {
            return Priority.NORMAL;
        }
        if (i9 == 2) {
            return Priority.HIGH;
        }
        if (i9 == 3 || i9 == 4) {
            return Priority.IMMEDIATE;
        }
        throw new IllegalArgumentException("unknown priority: " + this.f21468h.m23947x());
    }

    /* renamed from: k */
    public <Y extends InterfaceC6283h<TranscodeType>> Y m24419k(Y y8) {
        return (Y) m24420l(y8, null);
    }

    /* renamed from: l */
    public <Y extends InterfaceC6283h<TranscodeType>> Y m24420l(Y y8, InterfaceC6249f<TranscodeType> interfaceC6249f) {
        return (Y) m24421m(y8, interfaceC6249f, m24417i());
    }

    /* renamed from: m */
    public final <Y extends InterfaceC6283h<TranscodeType>> Y m24421m(Y y8, InterfaceC6249f<TranscodeType> interfaceC6249f, C6250g c6250g) {
        C6517j.m24941b();
        C6516i.m24938d(y8);
        if (!this.f21476p) {
            throw new IllegalArgumentException("You must call #load() before calling #into()");
        }
        C6250g c6250gMo23516b = c6250g.mo23516b();
        InterfaceC6246c interfaceC6246cM24413c = m24413c(y8, interfaceC6249f, c6250gMo23516b);
        InterfaceC6246c interfaceC6246cMo23901h = y8.mo23901h();
        if (!interfaceC6246cM24413c.mo4013f(interfaceC6246cMo23901h) || m24423o(c6250gMo23516b, interfaceC6246cMo23901h)) {
            this.f21463c.m24432m(y8);
            y8.mo23898a(interfaceC6246cM24413c);
            this.f21463c.m24439u(y8, interfaceC6246cM24413c);
            return y8;
        }
        interfaceC6246cM24413c.mo4009b();
        if (!((InterfaceC6246c) C6516i.m24938d(interfaceC6246cMo23901h)).isRunning()) {
            interfaceC6246cMo23901h.mo4016i();
        }
        return y8;
    }

    /* renamed from: n */
    public AbstractC6284i<ImageView, TranscodeType> m24422n(ImageView imageView) {
        C6517j.m24941b();
        C6516i.m24938d(imageView);
        C6250g c6250gMo23509Q = this.f21468h;
        if (!c6250gMo23509Q.m23923M() && c6250gMo23509Q.m23921K() && imageView.getScaleType() != null) {
            switch (b.f21480a[imageView.getScaleType().ordinal()]) {
                case 1:
                    c6250gMo23509Q = c6250gMo23509Q.clone().mo23509Q();
                    break;
                case 2:
                    c6250gMo23509Q = c6250gMo23509Q.clone().mo23510R();
                    break;
                case 3:
                case 4:
                case 5:
                    c6250gMo23509Q = c6250gMo23509Q.clone().mo23511T();
                    break;
                case 6:
                    c6250gMo23509Q = c6250gMo23509Q.clone().mo23510R();
                    break;
            }
        }
        return (AbstractC6284i) m24421m(this.f21467g.m24405a(imageView, this.f21464d), null, c6250gMo23509Q);
    }

    /* renamed from: o */
    public final boolean m24423o(C6250g c6250g, InterfaceC6246c interfaceC6246c) {
        return !c6250g.m23917F() && interfaceC6246c.isComplete();
    }

    /* renamed from: p */
    public C6358h<TranscodeType> m24424p(InterfaceC6249f<TranscodeType> interfaceC6249f) {
        this.f21471k = null;
        return mo23565a(interfaceC6249f);
    }

    /* renamed from: q */
    public C6358h<TranscodeType> m24425q(Uri uri) {
        return m24426u(uri);
    }

    /* renamed from: r */
    public C6358h<TranscodeType> mo23568r(Integer num) {
        return m24426u(num).mo23566b(C6250g.m23910e0(C6449a.m24695c(this.f21462b)));
    }

    /* renamed from: s */
    public C6358h<TranscodeType> mo23569s(Object obj) {
        return m24426u(obj);
    }

    /* renamed from: t */
    public C6358h<TranscodeType> mo23570t(String str) {
        return m24426u(str);
    }

    /* renamed from: u */
    public final C6358h<TranscodeType> m24426u(Object obj) {
        this.f21470j = obj;
        this.f21476p = true;
        return this;
    }

    /* renamed from: v */
    public final InterfaceC6246c m24427v(InterfaceC6283h<TranscodeType> interfaceC6283h, InterfaceC6249f<TranscodeType> interfaceC6249f, C6250g c6250g, InterfaceC6247d interfaceC6247d, AbstractC6360j<?, ? super TranscodeType> abstractC6360j, Priority priority, int i9, int i10) {
        Context context = this.f21462b;
        C6357g c6357g = this.f21467g;
        return SingleRequest.m4001A(context, c6357g, this.f21470j, this.f21464d, c6250g, i9, i10, priority, interfaceC6283h, interfaceC6249f, this.f21471k, interfaceC6247d, c6357g.m24409e(), abstractC6360j.m24443b());
    }

    /* renamed from: w */
    public InterfaceFutureC6245b<TranscodeType> m24428w() {
        return m24429x(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    /* renamed from: x */
    public InterfaceFutureC6245b<TranscodeType> m24429x(int i9, int i10) {
        RunnableC6248e runnableC6248e = new RunnableC6248e(this.f21467g.m24411g(), i9, i10);
        if (C6517j.m24956q()) {
            this.f21467g.m24411g().post(new a(runnableC6248e));
        } else {
            m24420l(runnableC6248e, runnableC6248e);
        }
        return runnableC6248e;
    }

    /* renamed from: y */
    public C6358h<TranscodeType> m24430y(C6358h<TranscodeType> c6358h) {
        this.f21472l = c6358h;
        return this;
    }

    public C6358h(Class<TranscodeType> cls, C6358h<?> c6358h) {
        this(c6358h.f21466f, c6358h.f21463c, cls, c6358h.f21462b);
        this.f21470j = c6358h.f21470j;
        this.f21476p = c6358h.f21476p;
        this.f21468h = c6358h.f21468h;
    }
}
