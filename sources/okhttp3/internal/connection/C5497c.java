package okhttp3.internal.connection;

import com.google.common.net.HttpHeaders;
import java.io.IOException;
import java.net.ProtocolException;
import okhttp3.AbstractC5483a0;
import okhttp3.AbstractC5516q;
import okhttp3.AbstractC5524y;
import okhttp3.C5523x;
import okhttp3.C5525z;
import p007a6.C0042f;
import p139m6.C5335h;
import p139m6.InterfaceC5331d;
import p204t6.AbstractC6325f;
import p204t6.AbstractC6326g;
import p204t6.C6322c;
import p204t6.C6331l;
import p204t6.InterfaceC6340u;
import p204t6.InterfaceC6342w;

/* renamed from: okhttp3.internal.connection.c */
/* loaded from: classes.dex */
public final class C5497c {

    /* renamed from: a */
    public final C5499e f18680a;

    /* renamed from: b */
    public final AbstractC5516q f18681b;

    /* renamed from: c */
    public final C5498d f18682c;

    /* renamed from: d */
    public final InterfaceC5331d f18683d;

    /* renamed from: e */
    public boolean f18684e;

    /* renamed from: f */
    public boolean f18685f;

    /* renamed from: g */
    public final RealConnection f18686g;

    /* renamed from: okhttp3.internal.connection.c$a */
    public final class a extends AbstractC6325f {

        /* renamed from: c */
        public final long f18687c;

        /* renamed from: d */
        public boolean f18688d;

        /* renamed from: e */
        public long f18689e;

        /* renamed from: f */
        public boolean f18690f;

        /* renamed from: g */
        public final /* synthetic */ C5497c f18691g;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(C5497c c5497c, InterfaceC6340u interfaceC6340u, long j9) {
            super(interfaceC6340u);
            C0042f.m158e(interfaceC6340u, "delegate");
            this.f18691g = c5497c;
            this.f18687c = j9;
        }

        @Override // p204t6.AbstractC6325f, p204t6.InterfaceC6340u, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.f18690f) {
                return;
            }
            this.f18690f = true;
            long j9 = this.f18687c;
            if (j9 != -1 && this.f18689e != j9) {
                throw new ProtocolException("unexpected end of stream");
            }
            try {
                super.close();
                m21331f(null);
            } catch (IOException e9) {
                throw m21331f(e9);
            }
        }

        /* renamed from: f */
        public final <E extends IOException> E m21331f(E e9) {
            if (this.f18688d) {
                return e9;
            }
            this.f18688d = true;
            return (E) this.f18691g.m21310a(this.f18689e, false, true, e9);
        }

        @Override // p204t6.AbstractC6325f, p204t6.InterfaceC6340u, java.io.Flushable
        public void flush() throws IOException {
            try {
                super.flush();
            } catch (IOException e9) {
                throw m21331f(e9);
            }
        }

        @Override // p204t6.AbstractC6325f, p204t6.InterfaceC6340u
        /* renamed from: q */
        public void mo21082q(C6322c c6322c, long j9) throws IOException {
            C0042f.m158e(c6322c, "source");
            if (!(!this.f18690f)) {
                throw new IllegalStateException("closed".toString());
            }
            long j10 = this.f18687c;
            if (j10 == -1 || this.f18689e + j9 <= j10) {
                try {
                    super.mo21082q(c6322c, j9);
                    this.f18689e += j9;
                    return;
                } catch (IOException e9) {
                    throw m21331f(e9);
                }
            }
            throw new ProtocolException("expected " + this.f18687c + " bytes but received " + (this.f18689e + j9));
        }
    }

    /* renamed from: okhttp3.internal.connection.c$b */
    public final class b extends AbstractC6326g {

        /* renamed from: c */
        public final long f18692c;

        /* renamed from: d */
        public long f18693d;

        /* renamed from: e */
        public boolean f18694e;

        /* renamed from: f */
        public boolean f18695f;

        /* renamed from: g */
        public boolean f18696g;

        /* renamed from: h */
        public final /* synthetic */ C5497c f18697h;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(C5497c c5497c, InterfaceC6342w interfaceC6342w, long j9) {
            super(interfaceC6342w);
            C0042f.m158e(interfaceC6342w, "delegate");
            this.f18697h = c5497c;
            this.f18692c = j9;
            this.f18694e = true;
            if (j9 == 0) {
                m21332u(null);
            }
        }

        @Override // p204t6.AbstractC6326g, p204t6.InterfaceC6342w, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.f18696g) {
                return;
            }
            this.f18696g = true;
            try {
                super.close();
                m21332u(null);
            } catch (IOException e9) {
                throw m21332u(e9);
            }
        }

        @Override // p204t6.InterfaceC6342w
        /* renamed from: d */
        public long mo21077d(C6322c c6322c, long j9) throws IOException {
            C0042f.m158e(c6322c, "sink");
            if (!(!this.f18696g)) {
                throw new IllegalStateException("closed".toString());
            }
            try {
                long jMo21077d = m24238f().mo21077d(c6322c, j9);
                if (this.f18694e) {
                    this.f18694e = false;
                    this.f18697h.m21318i().m21621v(this.f18697h.m21316g());
                }
                if (jMo21077d == -1) {
                    m21332u(null);
                    return -1L;
                }
                long j10 = this.f18693d + jMo21077d;
                long j11 = this.f18692c;
                if (j11 != -1 && j10 > j11) {
                    throw new ProtocolException("expected " + this.f18692c + " bytes but received " + j10);
                }
                this.f18693d = j10;
                if (j10 == j11) {
                    m21332u(null);
                }
                return jMo21077d;
            } catch (IOException e9) {
                throw m21332u(e9);
            }
        }

        /* renamed from: u */
        public final <E extends IOException> E m21332u(E e9) {
            if (this.f18695f) {
                return e9;
            }
            this.f18695f = true;
            if (e9 == null && this.f18694e) {
                this.f18694e = false;
                this.f18697h.m21318i().m21621v(this.f18697h.m21316g());
            }
            return (E) this.f18697h.m21310a(this.f18693d, true, false, e9);
        }
    }

    public C5497c(C5499e c5499e, AbstractC5516q abstractC5516q, C5498d c5498d, InterfaceC5331d interfaceC5331d) {
        C0042f.m158e(c5499e, "call");
        C0042f.m158e(abstractC5516q, "eventListener");
        C0042f.m158e(c5498d, "finder");
        C0042f.m158e(interfaceC5331d, "codec");
        this.f18680a = c5499e;
        this.f18681b = abstractC5516q;
        this.f18682c = c5498d;
        this.f18683d = interfaceC5331d;
        this.f18686g = interfaceC5331d.mo20941e();
    }

    /* renamed from: a */
    public final <E extends IOException> E m21310a(long j9, boolean z8, boolean z9, E e9) {
        if (e9 != null) {
            m21329t(e9);
        }
        if (z9) {
            if (e9 != null) {
                this.f18681b.m21617r(this.f18680a, e9);
            } else {
                this.f18681b.m21615p(this.f18680a, j9);
            }
        }
        if (z8) {
            if (e9 != null) {
                this.f18681b.m21622w(this.f18680a, e9);
            } else {
                this.f18681b.m21620u(this.f18680a, j9);
            }
        }
        return (E) this.f18680a.m21361t(this, z9, z8, e9);
    }

    /* renamed from: b */
    public final void m21311b() {
        this.f18683d.cancel();
    }

    /* renamed from: c */
    public final InterfaceC6340u m21312c(C5523x c5523x, boolean z8) {
        C0042f.m158e(c5523x, "request");
        this.f18684e = z8;
        AbstractC5524y abstractC5524yM21803a = c5523x.m21803a();
        C0042f.m155b(abstractC5524yM21803a);
        long jMo21715a = abstractC5524yM21803a.mo21715a();
        this.f18681b.m21616q(this.f18680a);
        return new a(this, this.f18683d.mo20944h(c5523x, jMo21715a), jMo21715a);
    }

    /* renamed from: d */
    public final void m21313d() {
        this.f18683d.cancel();
        this.f18680a.m21361t(this, true, true, null);
    }

    /* renamed from: e */
    public final void m21314e() {
        try {
            this.f18683d.mo20937a();
        } catch (IOException e9) {
            this.f18681b.m21617r(this.f18680a, e9);
            m21329t(e9);
            throw e9;
        }
    }

    /* renamed from: f */
    public final void m21315f() {
        try {
            this.f18683d.mo20942f();
        } catch (IOException e9) {
            this.f18681b.m21617r(this.f18680a, e9);
            m21329t(e9);
            throw e9;
        }
    }

    /* renamed from: g */
    public final C5499e m21316g() {
        return this.f18680a;
    }

    /* renamed from: h */
    public final RealConnection m21317h() {
        return this.f18686g;
    }

    /* renamed from: i */
    public final AbstractC5516q m21318i() {
        return this.f18681b;
    }

    /* renamed from: j */
    public final C5498d m21319j() {
        return this.f18682c;
    }

    /* renamed from: k */
    public final boolean m21320k() {
        return this.f18685f;
    }

    /* renamed from: l */
    public final boolean m21321l() {
        return !C0042f.m154a(this.f18682c.m21336d().m21228l().m21653h(), this.f18686g.m21301z().m21235a().m21228l().m21653h());
    }

    /* renamed from: m */
    public final boolean m21322m() {
        return this.f18684e;
    }

    /* renamed from: n */
    public final void m21323n() {
        this.f18683d.mo20941e().m21300y();
    }

    /* renamed from: o */
    public final void m21324o() {
        this.f18680a.m21361t(this, true, false, null);
    }

    /* renamed from: p */
    public final AbstractC5483a0 m21325p(C5525z c5525z) throws IOException {
        C0042f.m158e(c5525z, "response");
        try {
            String strM21837B = C5525z.m21837B(c5525z, HttpHeaders.CONTENT_TYPE, null, 2, null);
            long jMo20943g = this.f18683d.mo20943g(c5525z);
            return new C5335h(strM21837B, jMo20943g, C6331l.m24256b(new b(this, this.f18683d.mo20939c(c5525z), jMo20943g)));
        } catch (IOException e9) {
            this.f18681b.m21622w(this.f18680a, e9);
            m21329t(e9);
            throw e9;
        }
    }

    /* renamed from: q */
    public final C5525z.a m21326q(boolean z8) {
        try {
            C5525z.a aVarMo20940d = this.f18683d.mo20940d(z8);
            if (aVarMo20940d != null) {
                aVarMo20940d.m21867l(this);
            }
            return aVarMo20940d;
        } catch (IOException e9) {
            this.f18681b.m21622w(this.f18680a, e9);
            m21329t(e9);
            throw e9;
        }
    }

    /* renamed from: r */
    public final void m21327r(C5525z c5525z) {
        C0042f.m158e(c5525z, "response");
        this.f18681b.m21623x(this.f18680a, c5525z);
    }

    /* renamed from: s */
    public final void m21328s() {
        this.f18681b.m21624y(this.f18680a);
    }

    /* renamed from: t */
    public final void m21329t(IOException iOException) {
        this.f18685f = true;
        this.f18682c.m21340h(iOException);
        this.f18683d.mo20941e().m21276G(this.f18680a, iOException);
    }

    /* renamed from: u */
    public final void m21330u(C5523x c5523x) {
        C0042f.m158e(c5523x, "request");
        try {
            this.f18681b.m21619t(this.f18680a);
            this.f18683d.mo20938b(c5523x);
            this.f18681b.m21618s(this.f18680a, c5523x);
        } catch (IOException e9) {
            this.f18681b.m21617r(this.f18680a, e9);
            m21329t(e9);
            throw e9;
        }
    }
}
