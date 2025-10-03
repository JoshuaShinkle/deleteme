package okhttp3;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.common.net.HttpHeaders;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import kotlin.collections.C5226i;
import okhttp3.C5517r;
import okhttp3.internal.connection.C5497c;
import p007a6.C0042f;
import p139m6.C5332e;

/* renamed from: okhttp3.z */
/* loaded from: classes2.dex */
public final class C5525z implements Closeable {

    /* renamed from: b */
    public final C5523x f19068b;

    /* renamed from: c */
    public final Protocol f19069c;

    /* renamed from: d */
    public final String f19070d;

    /* renamed from: e */
    public final int f19071e;

    /* renamed from: f */
    public final Handshake f19072f;

    /* renamed from: g */
    public final C5517r f19073g;

    /* renamed from: h */
    public final AbstractC5483a0 f19074h;

    /* renamed from: i */
    public final C5525z f19075i;

    /* renamed from: j */
    public final C5525z f19076j;

    /* renamed from: k */
    public final C5525z f19077k;

    /* renamed from: l */
    public final long f19078l;

    /* renamed from: m */
    public final long f19079m;

    /* renamed from: n */
    public final C5497c f19080n;

    /* renamed from: o */
    public C5487d f19081o;

    public C5525z(C5523x c5523x, Protocol protocol, String str, int i9, Handshake handshake, C5517r c5517r, AbstractC5483a0 abstractC5483a0, C5525z c5525z, C5525z c5525z2, C5525z c5525z3, long j9, long j10, C5497c c5497c) {
        C0042f.m158e(c5523x, "request");
        C0042f.m158e(protocol, "protocol");
        C0042f.m158e(str, "message");
        C0042f.m158e(c5517r, "headers");
        this.f19068b = c5523x;
        this.f19069c = protocol;
        this.f19070d = str;
        this.f19071e = i9;
        this.f19072f = handshake;
        this.f19073g = c5517r;
        this.f19074h = abstractC5483a0;
        this.f19075i = c5525z;
        this.f19076j = c5525z2;
        this.f19077k = c5525z3;
        this.f19078l = j9;
        this.f19079m = j10;
        this.f19080n = c5497c;
    }

    /* renamed from: B */
    public static /* synthetic */ String m21837B(C5525z c5525z, String str, String str2, int i9, Object obj) {
        if ((i9 & 2) != 0) {
            str2 = null;
        }
        return c5525z.m21838A(str, str2);
    }

    /* renamed from: A */
    public final String m21838A(String str, String str2) {
        C0042f.m158e(str, AppMeasurementSdk.ConditionalUserProperty.NAME);
        String strM21626a = this.f19073g.m21626a(str);
        return strM21626a == null ? str2 : strM21626a;
    }

    /* renamed from: C */
    public final C5517r m21839C() {
        return this.f19073g;
    }

    /* renamed from: D */
    public final boolean m21840D() {
        int i9 = this.f19071e;
        return 200 <= i9 && i9 < 300;
    }

    /* renamed from: E */
    public final String m21841E() {
        return this.f19070d;
    }

    /* renamed from: F */
    public final C5525z m21842F() {
        return this.f19075i;
    }

    /* renamed from: G */
    public final a m21843G() {
        return new a(this);
    }

    /* renamed from: H */
    public final C5525z m21844H() {
        return this.f19077k;
    }

    /* renamed from: I */
    public final Protocol m21845I() {
        return this.f19069c;
    }

    /* renamed from: J */
    public final long m21846J() {
        return this.f19079m;
    }

    /* renamed from: K */
    public final C5523x m21847K() {
        return this.f19068b;
    }

    /* renamed from: L */
    public final long m21848L() {
        return this.f19078l;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        AbstractC5483a0 abstractC5483a0 = this.f19074h;
        if (abstractC5483a0 == null) {
            throw new IllegalStateException("response is not eligible for a body and must not be closed".toString());
        }
        abstractC5483a0.close();
    }

    /* renamed from: f */
    public final AbstractC5483a0 m21849f() {
        return this.f19074h;
    }

    public String toString() {
        return "Response{protocol=" + this.f19069c + ", code=" + this.f19071e + ", message=" + this.f19070d + ", url=" + this.f19068b.m21811i() + '}';
    }

    /* renamed from: u */
    public final C5487d m21850u() {
        C5487d c5487d = this.f19081o;
        if (c5487d != null) {
            return c5487d;
        }
        C5487d c5487dM21254b = C5487d.f18507n.m21254b(this.f19073g);
        this.f19081o = c5487dM21254b;
        return c5487dM21254b;
    }

    /* renamed from: v */
    public final C5525z m21851v() {
        return this.f19076j;
    }

    /* renamed from: w */
    public final List<C5490g> m21852w() {
        String str;
        C5517r c5517r = this.f19073g;
        int i9 = this.f19071e;
        if (i9 == 401) {
            str = HttpHeaders.WWW_AUTHENTICATE;
        } else {
            if (i9 != 407) {
                return C5226i.m20400f();
            }
            str = HttpHeaders.PROXY_AUTHENTICATE;
        }
        return C5332e.m20945a(c5517r, str);
    }

    /* renamed from: x */
    public final int m21853x() {
        return this.f19071e;
    }

    /* renamed from: y */
    public final C5497c m21854y() {
        return this.f19080n;
    }

    /* renamed from: z */
    public final Handshake m21855z() {
        return this.f19072f;
    }

    /* renamed from: okhttp3.z$a */
    public static class a {

        /* renamed from: a */
        public C5523x f19082a;

        /* renamed from: b */
        public Protocol f19083b;

        /* renamed from: c */
        public int f19084c;

        /* renamed from: d */
        public String f19085d;

        /* renamed from: e */
        public Handshake f19086e;

        /* renamed from: f */
        public C5517r.a f19087f;

        /* renamed from: g */
        public AbstractC5483a0 f19088g;

        /* renamed from: h */
        public C5525z f19089h;

        /* renamed from: i */
        public C5525z f19090i;

        /* renamed from: j */
        public C5525z f19091j;

        /* renamed from: k */
        public long f19092k;

        /* renamed from: l */
        public long f19093l;

        /* renamed from: m */
        public C5497c f19094m;

        public a() {
            this.f19084c = -1;
            this.f19087f = new C5517r.a();
        }

        /* renamed from: a */
        public a m21856a(String str, String str2) {
            C0042f.m158e(str, AppMeasurementSdk.ConditionalUserProperty.NAME);
            C0042f.m158e(str2, "value");
            this.f19087f.m21631a(str, str2);
            return this;
        }

        /* renamed from: b */
        public a m21857b(AbstractC5483a0 abstractC5483a0) {
            this.f19088g = abstractC5483a0;
            return this;
        }

        /* renamed from: c */
        public C5525z m21858c() {
            int i9 = this.f19084c;
            if (!(i9 >= 0)) {
                throw new IllegalStateException(("code < 0: " + this.f19084c).toString());
            }
            C5523x c5523x = this.f19082a;
            if (c5523x == null) {
                throw new IllegalStateException("request == null".toString());
            }
            Protocol protocol = this.f19083b;
            if (protocol == null) {
                throw new IllegalStateException("protocol == null".toString());
            }
            String str = this.f19085d;
            if (str != null) {
                return new C5525z(c5523x, protocol, str, i9, this.f19086e, this.f19087f.m21635e(), this.f19088g, this.f19089h, this.f19090i, this.f19091j, this.f19092k, this.f19093l, this.f19094m);
            }
            throw new IllegalStateException("message == null".toString());
        }

        /* renamed from: d */
        public a m21859d(C5525z c5525z) {
            m21861f("cacheResponse", c5525z);
            this.f19090i = c5525z;
            return this;
        }

        /* renamed from: e */
        public final void m21860e(C5525z c5525z) {
            if (c5525z != null) {
                if (!(c5525z.m21849f() == null)) {
                    throw new IllegalArgumentException("priorResponse.body != null".toString());
                }
            }
        }

        /* renamed from: f */
        public final void m21861f(String str, C5525z c5525z) {
            if (c5525z != null) {
                if (!(c5525z.m21849f() == null)) {
                    throw new IllegalArgumentException((str + ".body != null").toString());
                }
                if (!(c5525z.m21842F() == null)) {
                    throw new IllegalArgumentException((str + ".networkResponse != null").toString());
                }
                if (!(c5525z.m21851v() == null)) {
                    throw new IllegalArgumentException((str + ".cacheResponse != null").toString());
                }
                if (c5525z.m21844H() == null) {
                    return;
                }
                throw new IllegalArgumentException((str + ".priorResponse != null").toString());
            }
        }

        /* renamed from: g */
        public a m21862g(int i9) {
            this.f19084c = i9;
            return this;
        }

        /* renamed from: h */
        public final int m21863h() {
            return this.f19084c;
        }

        /* renamed from: i */
        public a m21864i(Handshake handshake) {
            this.f19086e = handshake;
            return this;
        }

        /* renamed from: j */
        public a m21865j(String str, String str2) {
            C0042f.m158e(str, AppMeasurementSdk.ConditionalUserProperty.NAME);
            C0042f.m158e(str2, "value");
            this.f19087f.m21638h(str, str2);
            return this;
        }

        /* renamed from: k */
        public a m21866k(C5517r c5517r) {
            C0042f.m158e(c5517r, "headers");
            this.f19087f = c5517r.m21628c();
            return this;
        }

        /* renamed from: l */
        public final void m21867l(C5497c c5497c) {
            C0042f.m158e(c5497c, "deferredTrailers");
            this.f19094m = c5497c;
        }

        /* renamed from: m */
        public a m21868m(String str) {
            C0042f.m158e(str, "message");
            this.f19085d = str;
            return this;
        }

        /* renamed from: n */
        public a m21869n(C5525z c5525z) {
            m21861f("networkResponse", c5525z);
            this.f19089h = c5525z;
            return this;
        }

        /* renamed from: o */
        public a m21870o(C5525z c5525z) {
            m21860e(c5525z);
            this.f19091j = c5525z;
            return this;
        }

        /* renamed from: p */
        public a m21871p(Protocol protocol) {
            C0042f.m158e(protocol, "protocol");
            this.f19083b = protocol;
            return this;
        }

        /* renamed from: q */
        public a m21872q(long j9) {
            this.f19093l = j9;
            return this;
        }

        /* renamed from: r */
        public a m21873r(C5523x c5523x) {
            C0042f.m158e(c5523x, "request");
            this.f19082a = c5523x;
            return this;
        }

        /* renamed from: s */
        public a m21874s(long j9) {
            this.f19092k = j9;
            return this;
        }

        public a(C5525z c5525z) {
            C0042f.m158e(c5525z, "response");
            this.f19084c = -1;
            this.f19082a = c5525z.m21847K();
            this.f19083b = c5525z.m21845I();
            this.f19084c = c5525z.m21853x();
            this.f19085d = c5525z.m21841E();
            this.f19086e = c5525z.m21855z();
            this.f19087f = c5525z.m21839C().m21628c();
            this.f19088g = c5525z.m21849f();
            this.f19089h = c5525z.m21842F();
            this.f19090i = c5525z.m21851v();
            this.f19091j = c5525z.m21844H();
            this.f19092k = c5525z.m21848L();
            this.f19093l = c5525z.m21846J();
            this.f19094m = c5525z.m21854y();
        }
    }
}
