package okhttp3;

import java.net.Proxy;
import java.net.ProxySelector;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.collections.C5231n;
import okhttp3.AbstractC5516q;
import okhttp3.InterfaceC5488e;
import okhttp3.internal.connection.C5499e;
import okhttp3.internal.connection.C5501g;
import p007a6.C0040d;
import p007a6.C0042f;
import p098i6.C5057d;
import p168p6.C6113j;
import p186r6.C6224a;
import p195s6.AbstractC6269c;
import p195s6.C6270d;

/* renamed from: okhttp3.w */
/* loaded from: classes2.dex */
public class C5522w implements Cloneable, InterfaceC5488e.a {

    /* renamed from: E */
    public static final b f18989E = new b(null);

    /* renamed from: F */
    public static final List<Protocol> f18990F = C5057d.m19809w(Protocol.HTTP_2, Protocol.HTTP_1_1);

    /* renamed from: G */
    public static final List<C5510k> f18991G = C5057d.m19809w(C5510k.f18897i, C5510k.f18899k);

    /* renamed from: A */
    public final int f18992A;

    /* renamed from: B */
    public final int f18993B;

    /* renamed from: C */
    public final long f18994C;

    /* renamed from: D */
    public final C5501g f18995D;

    /* renamed from: b */
    public final C5514o f18996b;

    /* renamed from: c */
    public final C5509j f18997c;

    /* renamed from: d */
    public final List<InterfaceC5519t> f18998d;

    /* renamed from: e */
    public final List<InterfaceC5519t> f18999e;

    /* renamed from: f */
    public final AbstractC5516q.c f19000f;

    /* renamed from: g */
    public final boolean f19001g;

    /* renamed from: h */
    public final InterfaceC5484b f19002h;

    /* renamed from: i */
    public final boolean f19003i;

    /* renamed from: j */
    public final boolean f19004j;

    /* renamed from: k */
    public final InterfaceC5512m f19005k;

    /* renamed from: l */
    public final InterfaceC5515p f19006l;

    /* renamed from: m */
    public final Proxy f19007m;

    /* renamed from: n */
    public final ProxySelector f19008n;

    /* renamed from: o */
    public final InterfaceC5484b f19009o;

    /* renamed from: p */
    public final SocketFactory f19010p;

    /* renamed from: q */
    public final SSLSocketFactory f19011q;

    /* renamed from: r */
    public final X509TrustManager f19012r;

    /* renamed from: s */
    public final List<C5510k> f19013s;

    /* renamed from: t */
    public final List<Protocol> f19014t;

    /* renamed from: u */
    public final HostnameVerifier f19015u;

    /* renamed from: v */
    public final CertificatePinner f19016v;

    /* renamed from: w */
    public final AbstractC6269c f19017w;

    /* renamed from: x */
    public final int f19018x;

    /* renamed from: y */
    public final int f19019y;

    /* renamed from: z */
    public final int f19020z;

    /* renamed from: okhttp3.w$b */
    public static final class b {
        public b() {
        }

        public /* synthetic */ b(C0040d c0040d) {
            this();
        }

        /* renamed from: a */
        public final List<C5510k> m21801a() {
            return C5522w.f18991G;
        }

        /* renamed from: b */
        public final List<Protocol> m21802b() {
            return C5522w.f18990F;
        }
    }

    public C5522w(a aVar) throws NoSuchAlgorithmException, KeyStoreException {
        ProxySelector proxySelectorM21800z;
        C0042f.m158e(aVar, "builder");
        this.f18996b = aVar.m21787m();
        this.f18997c = aVar.m21784j();
        this.f18998d = C5057d.m19779S(aVar.m21793s());
        this.f18999e = C5057d.m19779S(aVar.m21795u());
        this.f19000f = aVar.m21789o();
        this.f19001g = aVar.m21767B();
        this.f19002h = aVar.m21778d();
        this.f19003i = aVar.m21790p();
        this.f19004j = aVar.m21791q();
        this.f19005k = aVar.m21786l();
        aVar.m21779e();
        this.f19006l = aVar.m21788n();
        this.f19007m = aVar.m21798x();
        if (aVar.m21798x() != null) {
            proxySelectorM21800z = C6224a.f20951a;
        } else {
            proxySelectorM21800z = aVar.m21800z();
            proxySelectorM21800z = proxySelectorM21800z == null ? ProxySelector.getDefault() : proxySelectorM21800z;
            if (proxySelectorM21800z == null) {
                proxySelectorM21800z = C6224a.f20951a;
            }
        }
        this.f19008n = proxySelectorM21800z;
        this.f19009o = aVar.m21799y();
        this.f19010p = aVar.m21769D();
        List<C5510k> listM21785k = aVar.m21785k();
        this.f19013s = listM21785k;
        this.f19014t = aVar.m21797w();
        this.f19015u = aVar.m21792r();
        this.f19018x = aVar.m21780f();
        this.f19019y = aVar.m21783i();
        this.f19020z = aVar.m21766A();
        this.f18992A = aVar.m21771F();
        this.f18993B = aVar.m21796v();
        this.f18994C = aVar.m21794t();
        C5501g c5501gM21768C = aVar.m21768C();
        this.f18995D = c5501gM21768C == null ? new C5501g() : c5501gM21768C;
        List<C5510k> list = listM21785k;
        boolean z8 = true;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            Iterator<T> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (((C5510k) it.next()).m21560f()) {
                    z8 = false;
                    break;
                }
            }
        }
        if (z8) {
            this.f19011q = null;
            this.f19017w = null;
            this.f19012r = null;
            this.f19016v = CertificatePinner.f18458d;
        } else if (aVar.m21770E() != null) {
            this.f19011q = aVar.m21770E();
            AbstractC6269c abstractC6269cM21781g = aVar.m21781g();
            C0042f.m155b(abstractC6269cM21781g);
            this.f19017w = abstractC6269cM21781g;
            X509TrustManager x509TrustManagerM21772G = aVar.m21772G();
            C0042f.m155b(x509TrustManagerM21772G);
            this.f19012r = x509TrustManagerM21772G;
            CertificatePinner certificatePinnerM21782h = aVar.m21782h();
            C0042f.m155b(abstractC6269cM21781g);
            this.f19016v = certificatePinnerM21782h.m21191e(abstractC6269cM21781g);
        } else {
            C6113j.a aVar2 = C6113j.f20745a;
            X509TrustManager x509TrustManagerMo23418o = aVar2.m23447g().mo23418o();
            this.f19012r = x509TrustManagerMo23418o;
            C6113j c6113jM23447g = aVar2.m23447g();
            C0042f.m155b(x509TrustManagerMo23418o);
            this.f19011q = c6113jM23447g.mo23422n(x509TrustManagerMo23418o);
            AbstractC6269c.a aVar3 = AbstractC6269c.f21151a;
            C0042f.m155b(x509TrustManagerMo23418o);
            AbstractC6269c abstractC6269cM24011a = aVar3.m24011a(x509TrustManagerMo23418o);
            this.f19017w = abstractC6269cM24011a;
            CertificatePinner certificatePinnerM21782h2 = aVar.m21782h();
            C0042f.m155b(abstractC6269cM24011a);
            this.f19016v = certificatePinnerM21782h2.m21191e(abstractC6269cM24011a);
        }
        m21742I();
    }

    /* renamed from: A */
    public final List<Protocol> m21734A() {
        return this.f19014t;
    }

    /* renamed from: B */
    public final Proxy m21735B() {
        return this.f19007m;
    }

    /* renamed from: C */
    public final InterfaceC5484b m21736C() {
        return this.f19009o;
    }

    /* renamed from: D */
    public final ProxySelector m21737D() {
        return this.f19008n;
    }

    /* renamed from: E */
    public final int m21738E() {
        return this.f19020z;
    }

    /* renamed from: F */
    public final boolean m21739F() {
        return this.f19001g;
    }

    /* renamed from: G */
    public final SocketFactory m21740G() {
        return this.f19010p;
    }

    /* renamed from: H */
    public final SSLSocketFactory m21741H() {
        SSLSocketFactory sSLSocketFactory = this.f19011q;
        if (sSLSocketFactory != null) {
            return sSLSocketFactory;
        }
        throw new IllegalStateException("CLEARTEXT-only client");
    }

    /* renamed from: I */
    public final void m21742I() {
        boolean z8;
        C0042f.m156c(this.f18998d, "null cannot be cast to non-null type kotlin.collections.List<okhttp3.Interceptor?>");
        if (!(!r0.contains(null))) {
            throw new IllegalStateException(("Null interceptor: " + this.f18998d).toString());
        }
        C0042f.m156c(this.f18999e, "null cannot be cast to non-null type kotlin.collections.List<okhttp3.Interceptor?>");
        if (!(!r0.contains(null))) {
            throw new IllegalStateException(("Null network interceptor: " + this.f18999e).toString());
        }
        List<C5510k> list = this.f19013s;
        if ((list instanceof Collection) && list.isEmpty()) {
            z8 = true;
        } else {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                if (((C5510k) it.next()).m21560f()) {
                    z8 = false;
                    break;
                }
            }
            z8 = true;
        }
        if (!z8) {
            if (this.f19011q == null) {
                throw new IllegalStateException("sslSocketFactory == null".toString());
            }
            if (this.f19017w == null) {
                throw new IllegalStateException("certificateChainCleaner == null".toString());
            }
            if (this.f19012r == null) {
                throw new IllegalStateException("x509TrustManager == null".toString());
            }
            return;
        }
        if (!(this.f19011q == null)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        if (!(this.f19017w == null)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        if (!(this.f19012r == null)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        if (!C0042f.m154a(this.f19016v, CertificatePinner.f18458d)) {
            throw new IllegalStateException("Check failed.".toString());
        }
    }

    /* renamed from: J */
    public final int m21743J() {
        return this.f18992A;
    }

    /* renamed from: K */
    public final X509TrustManager m21744K() {
        return this.f19012r;
    }

    @Override // okhttp3.InterfaceC5488e.a
    /* renamed from: a */
    public InterfaceC5488e mo21256a(C5523x c5523x) {
        C0042f.m158e(c5523x, "request");
        return new C5499e(this, c5523x, false);
    }

    public Object clone() {
        return super.clone();
    }

    /* renamed from: e */
    public final InterfaceC5484b m21745e() {
        return this.f19002h;
    }

    /* renamed from: g */
    public final C5486c m21746g() {
        return null;
    }

    /* renamed from: h */
    public final int m21747h() {
        return this.f19018x;
    }

    /* renamed from: i */
    public final AbstractC6269c m21748i() {
        return this.f19017w;
    }

    /* renamed from: j */
    public final CertificatePinner m21749j() {
        return this.f19016v;
    }

    /* renamed from: k */
    public final int m21750k() {
        return this.f19019y;
    }

    /* renamed from: l */
    public final C5509j m21751l() {
        return this.f18997c;
    }

    /* renamed from: m */
    public final List<C5510k> m21752m() {
        return this.f19013s;
    }

    /* renamed from: n */
    public final InterfaceC5512m m21753n() {
        return this.f19005k;
    }

    /* renamed from: o */
    public final C5514o m21754o() {
        return this.f18996b;
    }

    /* renamed from: p */
    public final InterfaceC5515p m21755p() {
        return this.f19006l;
    }

    /* renamed from: q */
    public final AbstractC5516q.c m21756q() {
        return this.f19000f;
    }

    /* renamed from: r */
    public final boolean m21757r() {
        return this.f19003i;
    }

    /* renamed from: s */
    public final boolean m21758s() {
        return this.f19004j;
    }

    /* renamed from: t */
    public final C5501g m21759t() {
        return this.f18995D;
    }

    /* renamed from: u */
    public final HostnameVerifier m21760u() {
        return this.f19015u;
    }

    /* renamed from: v */
    public final List<InterfaceC5519t> m21761v() {
        return this.f18998d;
    }

    /* renamed from: w */
    public final long m21762w() {
        return this.f18994C;
    }

    /* renamed from: x */
    public final List<InterfaceC5519t> m21763x() {
        return this.f18999e;
    }

    /* renamed from: y */
    public a m21764y() {
        return new a(this);
    }

    /* renamed from: z */
    public final int m21765z() {
        return this.f18993B;
    }

    /* renamed from: okhttp3.w$a */
    public static final class a {

        /* renamed from: A */
        public int f19021A;

        /* renamed from: B */
        public long f19022B;

        /* renamed from: C */
        public C5501g f19023C;

        /* renamed from: a */
        public C5514o f19024a;

        /* renamed from: b */
        public C5509j f19025b;

        /* renamed from: c */
        public final List<InterfaceC5519t> f19026c;

        /* renamed from: d */
        public final List<InterfaceC5519t> f19027d;

        /* renamed from: e */
        public AbstractC5516q.c f19028e;

        /* renamed from: f */
        public boolean f19029f;

        /* renamed from: g */
        public InterfaceC5484b f19030g;

        /* renamed from: h */
        public boolean f19031h;

        /* renamed from: i */
        public boolean f19032i;

        /* renamed from: j */
        public InterfaceC5512m f19033j;

        /* renamed from: k */
        public InterfaceC5515p f19034k;

        /* renamed from: l */
        public Proxy f19035l;

        /* renamed from: m */
        public ProxySelector f19036m;

        /* renamed from: n */
        public InterfaceC5484b f19037n;

        /* renamed from: o */
        public SocketFactory f19038o;

        /* renamed from: p */
        public SSLSocketFactory f19039p;

        /* renamed from: q */
        public X509TrustManager f19040q;

        /* renamed from: r */
        public List<C5510k> f19041r;

        /* renamed from: s */
        public List<? extends Protocol> f19042s;

        /* renamed from: t */
        public HostnameVerifier f19043t;

        /* renamed from: u */
        public CertificatePinner f19044u;

        /* renamed from: v */
        public AbstractC6269c f19045v;

        /* renamed from: w */
        public int f19046w;

        /* renamed from: x */
        public int f19047x;

        /* renamed from: y */
        public int f19048y;

        /* renamed from: z */
        public int f19049z;

        public a() {
            this.f19024a = new C5514o();
            this.f19025b = new C5509j();
            this.f19026c = new ArrayList();
            this.f19027d = new ArrayList();
            this.f19028e = C5057d.m19793g(AbstractC5516q.f18937b);
            this.f19029f = true;
            InterfaceC5484b interfaceC5484b = InterfaceC5484b.f18501b;
            this.f19030g = interfaceC5484b;
            this.f19031h = true;
            this.f19032i = true;
            this.f19033j = InterfaceC5512m.f18923b;
            this.f19034k = InterfaceC5515p.f18934b;
            this.f19037n = interfaceC5484b;
            SocketFactory socketFactory = SocketFactory.getDefault();
            C0042f.m157d(socketFactory, "getDefault()");
            this.f19038o = socketFactory;
            b bVar = C5522w.f18989E;
            this.f19041r = bVar.m21801a();
            this.f19042s = bVar.m21802b();
            this.f19043t = C6270d.f21152a;
            this.f19044u = CertificatePinner.f18458d;
            this.f19047x = 10000;
            this.f19048y = 10000;
            this.f19049z = 10000;
            this.f19022B = 1024L;
        }

        /* renamed from: A */
        public final int m21766A() {
            return this.f19048y;
        }

        /* renamed from: B */
        public final boolean m21767B() {
            return this.f19029f;
        }

        /* renamed from: C */
        public final C5501g m21768C() {
            return this.f19023C;
        }

        /* renamed from: D */
        public final SocketFactory m21769D() {
            return this.f19038o;
        }

        /* renamed from: E */
        public final SSLSocketFactory m21770E() {
            return this.f19039p;
        }

        /* renamed from: F */
        public final int m21771F() {
            return this.f19049z;
        }

        /* renamed from: G */
        public final X509TrustManager m21772G() {
            return this.f19040q;
        }

        /* renamed from: H */
        public final a m21773H(long j9, TimeUnit timeUnit) {
            C0042f.m158e(timeUnit, "unit");
            this.f19048y = C5057d.m19797k("timeout", j9, timeUnit);
            return this;
        }

        /* renamed from: I */
        public final a m21774I(long j9, TimeUnit timeUnit) {
            C0042f.m158e(timeUnit, "unit");
            this.f19049z = C5057d.m19797k("timeout", j9, timeUnit);
            return this;
        }

        /* renamed from: a */
        public final C5522w m21775a() {
            return new C5522w(this);
        }

        /* renamed from: b */
        public final a m21776b(CertificatePinner certificatePinner) {
            C0042f.m158e(certificatePinner, "certificatePinner");
            if (!C0042f.m154a(certificatePinner, this.f19044u)) {
                this.f19023C = null;
            }
            this.f19044u = certificatePinner;
            return this;
        }

        /* renamed from: c */
        public final a m21777c(long j9, TimeUnit timeUnit) {
            C0042f.m158e(timeUnit, "unit");
            this.f19047x = C5057d.m19797k("timeout", j9, timeUnit);
            return this;
        }

        /* renamed from: d */
        public final InterfaceC5484b m21778d() {
            return this.f19030g;
        }

        /* renamed from: e */
        public final C5486c m21779e() {
            return null;
        }

        /* renamed from: f */
        public final int m21780f() {
            return this.f19046w;
        }

        /* renamed from: g */
        public final AbstractC6269c m21781g() {
            return this.f19045v;
        }

        /* renamed from: h */
        public final CertificatePinner m21782h() {
            return this.f19044u;
        }

        /* renamed from: i */
        public final int m21783i() {
            return this.f19047x;
        }

        /* renamed from: j */
        public final C5509j m21784j() {
            return this.f19025b;
        }

        /* renamed from: k */
        public final List<C5510k> m21785k() {
            return this.f19041r;
        }

        /* renamed from: l */
        public final InterfaceC5512m m21786l() {
            return this.f19033j;
        }

        /* renamed from: m */
        public final C5514o m21787m() {
            return this.f19024a;
        }

        /* renamed from: n */
        public final InterfaceC5515p m21788n() {
            return this.f19034k;
        }

        /* renamed from: o */
        public final AbstractC5516q.c m21789o() {
            return this.f19028e;
        }

        /* renamed from: p */
        public final boolean m21790p() {
            return this.f19031h;
        }

        /* renamed from: q */
        public final boolean m21791q() {
            return this.f19032i;
        }

        /* renamed from: r */
        public final HostnameVerifier m21792r() {
            return this.f19043t;
        }

        /* renamed from: s */
        public final List<InterfaceC5519t> m21793s() {
            return this.f19026c;
        }

        /* renamed from: t */
        public final long m21794t() {
            return this.f19022B;
        }

        /* renamed from: u */
        public final List<InterfaceC5519t> m21795u() {
            return this.f19027d;
        }

        /* renamed from: v */
        public final int m21796v() {
            return this.f19021A;
        }

        /* renamed from: w */
        public final List<Protocol> m21797w() {
            return this.f19042s;
        }

        /* renamed from: x */
        public final Proxy m21798x() {
            return this.f19035l;
        }

        /* renamed from: y */
        public final InterfaceC5484b m21799y() {
            return this.f19037n;
        }

        /* renamed from: z */
        public final ProxySelector m21800z() {
            return this.f19036m;
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public a(C5522w c5522w) {
            this();
            C0042f.m158e(c5522w, "okHttpClient");
            this.f19024a = c5522w.m21754o();
            this.f19025b = c5522w.m21751l();
            C5231n.m20410p(this.f19026c, c5522w.m21761v());
            C5231n.m20410p(this.f19027d, c5522w.m21763x());
            this.f19028e = c5522w.m21756q();
            this.f19029f = c5522w.m21739F();
            this.f19030g = c5522w.m21745e();
            this.f19031h = c5522w.m21757r();
            this.f19032i = c5522w.m21758s();
            this.f19033j = c5522w.m21753n();
            c5522w.m21746g();
            this.f19034k = c5522w.m21755p();
            this.f19035l = c5522w.m21735B();
            this.f19036m = c5522w.m21737D();
            this.f19037n = c5522w.m21736C();
            this.f19038o = c5522w.m21740G();
            this.f19039p = c5522w.f19011q;
            this.f19040q = c5522w.m21744K();
            this.f19041r = c5522w.m21752m();
            this.f19042s = c5522w.m21734A();
            this.f19043t = c5522w.m21760u();
            this.f19044u = c5522w.m21749j();
            this.f19045v = c5522w.m21748i();
            this.f19046w = c5522w.m21747h();
            this.f19047x = c5522w.m21750k();
            this.f19048y = c5522w.m21738E();
            this.f19049z = c5522w.m21743J();
            this.f19021A = c5522w.m21765z();
            this.f19022B = c5522w.m21762w();
            this.f19023C = c5522w.m21759t();
        }
    }

    public C5522w() {
        this(new a());
    }
}
