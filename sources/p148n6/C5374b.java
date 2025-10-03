package p148n6;

import com.google.common.net.HttpHeaders;
import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;
import kotlin.text.C5255l;
import kotlin.text.StringsKt__StringsKt;
import okhttp3.C5517r;
import okhttp3.C5518s;
import okhttp3.C5522w;
import okhttp3.C5523x;
import okhttp3.C5525z;
import okhttp3.InterfaceC5512m;
import okhttp3.internal.connection.RealConnection;
import org.apache.commons.p159io.IOUtils;
import p007a6.C0040d;
import p007a6.C0042f;
import p098i6.C5057d;
import p139m6.C5332e;
import p139m6.C5336i;
import p139m6.C5338k;
import p139m6.InterfaceC5331d;
import p204t6.C6322c;
import p204t6.C6327h;
import p204t6.C6343x;
import p204t6.InterfaceC6323d;
import p204t6.InterfaceC6324e;
import p204t6.InterfaceC6340u;
import p204t6.InterfaceC6342w;

/* renamed from: n6.b */
/* loaded from: classes.dex */
public final class C5374b implements InterfaceC5331d {

    /* renamed from: h */
    public static final d f18255h = new d(null);

    /* renamed from: a */
    public final C5522w f18256a;

    /* renamed from: b */
    public final RealConnection f18257b;

    /* renamed from: c */
    public final InterfaceC6324e f18258c;

    /* renamed from: d */
    public final InterfaceC6323d f18259d;

    /* renamed from: e */
    public int f18260e;

    /* renamed from: f */
    public final C5373a f18261f;

    /* renamed from: g */
    public C5517r f18262g;

    /* renamed from: n6.b$a */
    public abstract class a implements InterfaceC6342w {

        /* renamed from: b */
        public final C6327h f18263b;

        /* renamed from: c */
        public boolean f18264c;

        public a() {
            this.f18263b = new C6327h(C5374b.this.f18258c.mo21076a());
        }

        @Override // p204t6.InterfaceC6342w
        /* renamed from: a */
        public C6343x mo21076a() {
            return this.f18263b;
        }

        @Override // p204t6.InterfaceC6342w
        /* renamed from: d */
        public long mo21077d(C6322c c6322c, long j9) throws IOException {
            C0042f.m158e(c6322c, "sink");
            try {
                return C5374b.this.f18258c.mo21077d(c6322c, j9);
            } catch (IOException e9) {
                C5374b.this.mo20941e().m21300y();
                m21079u();
                throw e9;
            }
        }

        /* renamed from: f */
        public final boolean m21078f() {
            return this.f18264c;
        }

        /* renamed from: u */
        public final void m21079u() {
            if (C5374b.this.f18260e == 6) {
                return;
            }
            if (C5374b.this.f18260e == 5) {
                C5374b.this.m21067r(this.f18263b);
                C5374b.this.f18260e = 6;
            } else {
                throw new IllegalStateException("state: " + C5374b.this.f18260e);
            }
        }

        /* renamed from: v */
        public final void m21080v(boolean z8) {
            this.f18264c = z8;
        }
    }

    /* renamed from: n6.b$b */
    public final class b implements InterfaceC6340u {

        /* renamed from: b */
        public final C6327h f18266b;

        /* renamed from: c */
        public boolean f18267c;

        public b() {
            this.f18266b = new C6327h(C5374b.this.f18259d.mo21081a());
        }

        @Override // p204t6.InterfaceC6340u
        /* renamed from: a */
        public C6343x mo21081a() {
            return this.f18266b;
        }

        @Override // p204t6.InterfaceC6340u, java.io.Closeable, java.lang.AutoCloseable
        public synchronized void close() {
            if (this.f18267c) {
                return;
            }
            this.f18267c = true;
            C5374b.this.f18259d.mo24221j("0\r\n\r\n");
            C5374b.this.m21067r(this.f18266b);
            C5374b.this.f18260e = 3;
        }

        @Override // p204t6.InterfaceC6340u, java.io.Flushable
        public synchronized void flush() {
            if (this.f18267c) {
                return;
            }
            C5374b.this.f18259d.flush();
        }

        @Override // p204t6.InterfaceC6340u
        /* renamed from: q */
        public void mo21082q(C6322c c6322c, long j9) {
            C0042f.m158e(c6322c, "source");
            if (!(!this.f18267c)) {
                throw new IllegalStateException("closed".toString());
            }
            if (j9 == 0) {
                return;
            }
            C5374b.this.f18259d.mo24223l(j9);
            C5374b.this.f18259d.mo24221j(IOUtils.LINE_SEPARATOR_WINDOWS);
            C5374b.this.f18259d.mo21082q(c6322c, j9);
            C5374b.this.f18259d.mo24221j(IOUtils.LINE_SEPARATOR_WINDOWS);
        }
    }

    /* renamed from: n6.b$c */
    public final class c extends a {

        /* renamed from: e */
        public final C5518s f18269e;

        /* renamed from: f */
        public long f18270f;

        /* renamed from: g */
        public boolean f18271g;

        /* renamed from: h */
        public final /* synthetic */ C5374b f18272h;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(C5374b c5374b, C5518s c5518s) {
            super();
            C0042f.m158e(c5518s, "url");
            this.f18272h = c5374b;
            this.f18269e = c5518s;
            this.f18270f = -1L;
            this.f18271g = true;
        }

        @Override // p204t6.InterfaceC6342w, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (m21078f()) {
                return;
            }
            if (this.f18271g && !C5057d.m19805s(this, 100, TimeUnit.MILLISECONDS)) {
                this.f18272h.mo20941e().m21300y();
                m21079u();
            }
            m21080v(true);
        }

        @Override // p148n6.C5374b.a, p204t6.InterfaceC6342w
        /* renamed from: d */
        public long mo21077d(C6322c c6322c, long j9) throws IOException {
            C0042f.m158e(c6322c, "sink");
            if (!(j9 >= 0)) {
                throw new IllegalArgumentException(("byteCount < 0: " + j9).toString());
            }
            if (!(!m21078f())) {
                throw new IllegalStateException("closed".toString());
            }
            if (!this.f18271g) {
                return -1L;
            }
            long j10 = this.f18270f;
            if (j10 == 0 || j10 == -1) {
                m21083w();
                if (!this.f18271g) {
                    return -1L;
                }
            }
            long jMo21077d = super.mo21077d(c6322c, Math.min(j9, this.f18270f));
            if (jMo21077d != -1) {
                this.f18270f -= jMo21077d;
                return jMo21077d;
            }
            this.f18272h.mo20941e().m21300y();
            ProtocolException protocolException = new ProtocolException("unexpected end of stream");
            m21079u();
            throw protocolException;
        }

        /* renamed from: w */
        public final void m21083w() throws ProtocolException {
            if (this.f18270f != -1) {
                this.f18272h.f18258c.mo24224m();
            }
            try {
                this.f18270f = this.f18272h.f18258c.mo24230t();
                String string = StringsKt__StringsKt.m20487m0(this.f18272h.f18258c.mo24224m()).toString();
                if (this.f18270f >= 0) {
                    if (!(string.length() > 0) || C5255l.m20525x(string, ";", false, 2, null)) {
                        if (this.f18270f == 0) {
                            this.f18271g = false;
                            C5374b c5374b = this.f18272h;
                            c5374b.f18262g = c5374b.f18261f.m21055a();
                            C5522w c5522w = this.f18272h.f18256a;
                            C0042f.m155b(c5522w);
                            InterfaceC5512m interfaceC5512mM21753n = c5522w.m21753n();
                            C5518s c5518s = this.f18269e;
                            C5517r c5517r = this.f18272h.f18262g;
                            C0042f.m155b(c5517r);
                            C5332e.m20950f(interfaceC5512mM21753n, c5518s, c5517r);
                            m21079u();
                            return;
                        }
                        return;
                    }
                }
                throw new ProtocolException("expected chunk size and optional extensions but was \"" + this.f18270f + string + '\"');
            } catch (NumberFormatException e9) {
                throw new ProtocolException(e9.getMessage());
            }
        }
    }

    /* renamed from: n6.b$d */
    public static final class d {
        public d() {
        }

        public /* synthetic */ d(C0040d c0040d) {
            this();
        }
    }

    /* renamed from: n6.b$e */
    public final class e extends a {

        /* renamed from: e */
        public long f18273e;

        public e(long j9) {
            super();
            this.f18273e = j9;
            if (j9 == 0) {
                m21079u();
            }
        }

        @Override // p204t6.InterfaceC6342w, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (m21078f()) {
                return;
            }
            if (this.f18273e != 0 && !C5057d.m19805s(this, 100, TimeUnit.MILLISECONDS)) {
                C5374b.this.mo20941e().m21300y();
                m21079u();
            }
            m21080v(true);
        }

        @Override // p148n6.C5374b.a, p204t6.InterfaceC6342w
        /* renamed from: d */
        public long mo21077d(C6322c c6322c, long j9) throws IOException {
            C0042f.m158e(c6322c, "sink");
            if (!(j9 >= 0)) {
                throw new IllegalArgumentException(("byteCount < 0: " + j9).toString());
            }
            if (!(!m21078f())) {
                throw new IllegalStateException("closed".toString());
            }
            long j10 = this.f18273e;
            if (j10 == 0) {
                return -1L;
            }
            long jMo21077d = super.mo21077d(c6322c, Math.min(j10, j9));
            if (jMo21077d == -1) {
                C5374b.this.mo20941e().m21300y();
                ProtocolException protocolException = new ProtocolException("unexpected end of stream");
                m21079u();
                throw protocolException;
            }
            long j11 = this.f18273e - jMo21077d;
            this.f18273e = j11;
            if (j11 == 0) {
                m21079u();
            }
            return jMo21077d;
        }
    }

    /* renamed from: n6.b$f */
    public final class f implements InterfaceC6340u {

        /* renamed from: b */
        public final C6327h f18275b;

        /* renamed from: c */
        public boolean f18276c;

        public f() {
            this.f18275b = new C6327h(C5374b.this.f18259d.mo21081a());
        }

        @Override // p204t6.InterfaceC6340u
        /* renamed from: a */
        public C6343x mo21081a() {
            return this.f18275b;
        }

        @Override // p204t6.InterfaceC6340u, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.f18276c) {
                return;
            }
            this.f18276c = true;
            C5374b.this.m21067r(this.f18275b);
            C5374b.this.f18260e = 3;
        }

        @Override // p204t6.InterfaceC6340u, java.io.Flushable
        public void flush() {
            if (this.f18276c) {
                return;
            }
            C5374b.this.f18259d.flush();
        }

        @Override // p204t6.InterfaceC6340u
        /* renamed from: q */
        public void mo21082q(C6322c c6322c, long j9) {
            C0042f.m158e(c6322c, "source");
            if (!(!this.f18276c)) {
                throw new IllegalStateException("closed".toString());
            }
            C5057d.m19798l(c6322c.size(), 0L, j9);
            C5374b.this.f18259d.mo21082q(c6322c, j9);
        }
    }

    /* renamed from: n6.b$g */
    public final class g extends a {

        /* renamed from: e */
        public boolean f18278e;

        public g() {
            super();
        }

        @Override // p204t6.InterfaceC6342w, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (m21078f()) {
                return;
            }
            if (!this.f18278e) {
                m21079u();
            }
            m21080v(true);
        }

        @Override // p148n6.C5374b.a, p204t6.InterfaceC6342w
        /* renamed from: d */
        public long mo21077d(C6322c c6322c, long j9) throws IOException {
            C0042f.m158e(c6322c, "sink");
            if (!(j9 >= 0)) {
                throw new IllegalArgumentException(("byteCount < 0: " + j9).toString());
            }
            if (!(!m21078f())) {
                throw new IllegalStateException("closed".toString());
            }
            if (this.f18278e) {
                return -1L;
            }
            long jMo21077d = super.mo21077d(c6322c, j9);
            if (jMo21077d != -1) {
                return jMo21077d;
            }
            this.f18278e = true;
            m21079u();
            return -1L;
        }
    }

    public C5374b(C5522w c5522w, RealConnection realConnection, InterfaceC6324e interfaceC6324e, InterfaceC6323d interfaceC6323d) {
        C0042f.m158e(realConnection, "connection");
        C0042f.m158e(interfaceC6324e, "source");
        C0042f.m158e(interfaceC6323d, "sink");
        this.f18256a = c5522w;
        this.f18257b = realConnection;
        this.f18258c = interfaceC6324e;
        this.f18259d = interfaceC6323d;
        this.f18261f = new C5373a(interfaceC6324e);
    }

    /* renamed from: A */
    public final void m21066A(C5517r c5517r, String str) {
        C0042f.m158e(c5517r, "headers");
        C0042f.m158e(str, "requestLine");
        if (!(this.f18260e == 0)) {
            throw new IllegalStateException(("state: " + this.f18260e).toString());
        }
        this.f18259d.mo24221j(str).mo24221j(IOUtils.LINE_SEPARATOR_WINDOWS);
        int size = c5517r.size();
        for (int i9 = 0; i9 < size; i9++) {
            this.f18259d.mo24221j(c5517r.m21627b(i9)).mo24221j(": ").mo24221j(c5517r.m21629d(i9)).mo24221j(IOUtils.LINE_SEPARATOR_WINDOWS);
        }
        this.f18259d.mo24221j(IOUtils.LINE_SEPARATOR_WINDOWS);
        this.f18260e = 1;
    }

    @Override // p139m6.InterfaceC5331d
    /* renamed from: a */
    public void mo20937a() {
        this.f18259d.flush();
    }

    @Override // p139m6.InterfaceC5331d
    /* renamed from: b */
    public void mo20938b(C5523x c5523x) {
        C0042f.m158e(c5523x, "request");
        C5336i c5336i = C5336i.f18181a;
        Proxy.Type type = mo20941e().m21301z().m21236b().type();
        C0042f.m157d(type, "connection.route().proxy.type()");
        m21066A(c5523x.m21807e(), c5336i.m20971a(c5523x, type));
    }

    @Override // p139m6.InterfaceC5331d
    /* renamed from: c */
    public InterfaceC6342w mo20939c(C5525z c5525z) {
        C0042f.m158e(c5525z, "response");
        if (!C5332e.m20946b(c5525z)) {
            return m21072w(0L);
        }
        if (m21069t(c5525z)) {
            return m21071v(c5525z.m21847K().m21811i());
        }
        long jM19808v = C5057d.m19808v(c5525z);
        return jM19808v != -1 ? m21072w(jM19808v) : m21074y();
    }

    @Override // p139m6.InterfaceC5331d
    public void cancel() throws IOException {
        mo20941e().m21279d();
    }

    @Override // p139m6.InterfaceC5331d
    /* renamed from: d */
    public C5525z.a mo20940d(boolean z8) {
        int i9 = this.f18260e;
        boolean z9 = false;
        if (!(i9 == 1 || i9 == 2 || i9 == 3)) {
            throw new IllegalStateException(("state: " + this.f18260e).toString());
        }
        try {
            C5338k c5338kM20980a = C5338k.f18184d.m20980a(this.f18261f.m21056b());
            C5525z.a aVarM21866k = new C5525z.a().m21871p(c5338kM20980a.f18185a).m21862g(c5338kM20980a.f18186b).m21868m(c5338kM20980a.f18187c).m21866k(this.f18261f.m21055a());
            if (z8 && c5338kM20980a.f18186b == 100) {
                return null;
            }
            int i10 = c5338kM20980a.f18186b;
            if (i10 == 100) {
                this.f18260e = 3;
                return aVarM21866k;
            }
            if (102 <= i10 && i10 < 200) {
                z9 = true;
            }
            if (z9) {
                this.f18260e = 3;
                return aVarM21866k;
            }
            this.f18260e = 4;
            return aVarM21866k;
        } catch (EOFException e9) {
            throw new IOException("unexpected end of stream on " + mo20941e().m21301z().m21235a().m21228l().m21659n(), e9);
        }
    }

    @Override // p139m6.InterfaceC5331d
    /* renamed from: e */
    public RealConnection mo20941e() {
        return this.f18257b;
    }

    @Override // p139m6.InterfaceC5331d
    /* renamed from: f */
    public void mo20942f() {
        this.f18259d.flush();
    }

    @Override // p139m6.InterfaceC5331d
    /* renamed from: g */
    public long mo20943g(C5525z c5525z) {
        C0042f.m158e(c5525z, "response");
        if (!C5332e.m20946b(c5525z)) {
            return 0L;
        }
        if (m21069t(c5525z)) {
            return -1L;
        }
        return C5057d.m19808v(c5525z);
    }

    @Override // p139m6.InterfaceC5331d
    /* renamed from: h */
    public InterfaceC6340u mo20944h(C5523x c5523x, long j9) throws ProtocolException {
        C0042f.m158e(c5523x, "request");
        if (c5523x.m21803a() != null && c5523x.m21803a().m21825f()) {
            throw new ProtocolException("Duplex connections are not supported for HTTP/1");
        }
        if (m21068s(c5523x)) {
            return m21070u();
        }
        if (j9 != -1) {
            return m21073x();
        }
        throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
    }

    /* renamed from: r */
    public final void m21067r(C6327h c6327h) {
        C6343x c6343xM24246i = c6327h.m24246i();
        c6327h.m24247j(C6343x.f21381e);
        c6343xM24246i.mo24239a();
        c6343xM24246i.mo24240b();
    }

    /* renamed from: s */
    public final boolean m21068s(C5523x c5523x) {
        return C5255l.m20513l("chunked", c5523x.m21806d(HttpHeaders.TRANSFER_ENCODING), true);
    }

    /* renamed from: t */
    public final boolean m21069t(C5525z c5525z) {
        return C5255l.m20513l("chunked", C5525z.m21837B(c5525z, HttpHeaders.TRANSFER_ENCODING, null, 2, null), true);
    }

    /* renamed from: u */
    public final InterfaceC6340u m21070u() {
        if (this.f18260e == 1) {
            this.f18260e = 2;
            return new b();
        }
        throw new IllegalStateException(("state: " + this.f18260e).toString());
    }

    /* renamed from: v */
    public final InterfaceC6342w m21071v(C5518s c5518s) {
        if (this.f18260e == 4) {
            this.f18260e = 5;
            return new c(this, c5518s);
        }
        throw new IllegalStateException(("state: " + this.f18260e).toString());
    }

    /* renamed from: w */
    public final InterfaceC6342w m21072w(long j9) {
        if (this.f18260e == 4) {
            this.f18260e = 5;
            return new e(j9);
        }
        throw new IllegalStateException(("state: " + this.f18260e).toString());
    }

    /* renamed from: x */
    public final InterfaceC6340u m21073x() {
        if (this.f18260e == 1) {
            this.f18260e = 2;
            return new f();
        }
        throw new IllegalStateException(("state: " + this.f18260e).toString());
    }

    /* renamed from: y */
    public final InterfaceC6342w m21074y() {
        if (this.f18260e == 4) {
            this.f18260e = 5;
            mo20941e().m21300y();
            return new g();
        }
        throw new IllegalStateException(("state: " + this.f18260e).toString());
    }

    /* renamed from: z */
    public final void m21075z(C5525z c5525z) {
        C0042f.m158e(c5525z, "response");
        long jM19808v = C5057d.m19808v(c5525z);
        if (jM19808v == -1) {
            return;
        }
        InterfaceC6342w interfaceC6342wM21072w = m21072w(jM19808v);
        C5057d.m19772L(interfaceC6342wM21072w, Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
        interfaceC6342wM21072w.close();
    }
}
