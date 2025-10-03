package okhttp3.internal.connection;

import java.io.IOException;
import java.net.Socket;
import java.util.List;
import okhttp3.AbstractC5516q;
import okhttp3.C5482a;
import okhttp3.C5485b0;
import okhttp3.C5518s;
import okhttp3.C5522w;
import okhttp3.internal.connection.C5502h;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.StreamResetException;
import p007a6.C0042f;
import p098i6.C5057d;
import p139m6.C5334g;
import p139m6.InterfaceC5331d;
import p203t5.C6319g;

/* renamed from: okhttp3.internal.connection.d */
/* loaded from: classes.dex */
public final class C5498d {

    /* renamed from: a */
    public final C5500f f18698a;

    /* renamed from: b */
    public final C5482a f18699b;

    /* renamed from: c */
    public final C5499e f18700c;

    /* renamed from: d */
    public final AbstractC5516q f18701d;

    /* renamed from: e */
    public C5502h.b f18702e;

    /* renamed from: f */
    public C5502h f18703f;

    /* renamed from: g */
    public int f18704g;

    /* renamed from: h */
    public int f18705h;

    /* renamed from: i */
    public int f18706i;

    /* renamed from: j */
    public C5485b0 f18707j;

    public C5498d(C5500f c5500f, C5482a c5482a, C5499e c5499e, AbstractC5516q abstractC5516q) {
        C0042f.m158e(c5500f, "connectionPool");
        C0042f.m158e(c5482a, "address");
        C0042f.m158e(c5499e, "call");
        C0042f.m158e(abstractC5516q, "eventListener");
        this.f18698a = c5500f;
        this.f18699b = c5482a;
        this.f18700c = c5499e;
        this.f18701d = abstractC5516q;
    }

    /* renamed from: a */
    public final InterfaceC5331d m21333a(C5522w c5522w, C5334g c5334g) {
        C0042f.m158e(c5522w, "client");
        C0042f.m158e(c5334g, "chain");
        try {
            return m21335c(c5334g.m20962f(), c5334g.m20964h(), c5334g.m20966j(), c5522w.m21765z(), c5522w.m21739F(), !C0042f.m154a(c5334g.m20965i().m21809g(), "GET")).m21298w(c5522w, c5334g);
        } catch (IOException e9) {
            m21340h(e9);
            throw new RouteException(e9);
        } catch (RouteException e10) {
            m21340h(e10.m21306c());
            throw e10;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:58:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0150  */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final RealConnection m21334b(int i9, int i10, int i11, int i12, boolean z8) throws IOException {
        List<C5485b0> listM21390a;
        Socket socketM21364w;
        if (this.f18700c.m21360s()) {
            throw new IOException("Canceled");
        }
        RealConnection realConnectionM21353l = this.f18700c.m21353l();
        if (realConnectionM21353l != null) {
            synchronized (realConnectionM21353l) {
                socketM21364w = (realConnectionM21353l.m21291p() || !m21339g(realConnectionM21353l.m21301z().m21235a().m21228l())) ? this.f18700c.m21364w() : null;
                C6319g c6319g = C6319g.f21314a;
            }
            if (this.f18700c.m21353l() != null) {
                if (socketM21364w == null) {
                    return realConnectionM21353l;
                }
                throw new IllegalStateException("Check failed.".toString());
            }
            if (socketM21364w != null) {
                C5057d.m19800n(socketM21364w);
            }
            this.f18701d.m21610k(this.f18700c, realConnectionM21353l);
        }
        this.f18704g = 0;
        this.f18705h = 0;
        this.f18706i = 0;
        if (this.f18698a.m21374a(this.f18699b, this.f18700c, null, false)) {
            RealConnection realConnectionM21353l2 = this.f18700c.m21353l();
            C0042f.m155b(realConnectionM21353l2);
            this.f18701d.m21609j(this.f18700c, realConnectionM21353l2);
            return realConnectionM21353l2;
        }
        C5485b0 c5485b0M21392c = this.f18707j;
        try {
            if (c5485b0M21392c == null) {
                C5502h.b bVar = this.f18702e;
                if (bVar != null) {
                    C0042f.m155b(bVar);
                    if (bVar.m21391b()) {
                        C5502h.b bVar2 = this.f18702e;
                        C0042f.m155b(bVar2);
                        c5485b0M21392c = bVar2.m21392c();
                    }
                }
                C5502h c5502h = this.f18703f;
                if (c5502h == null) {
                    c5502h = new C5502h(this.f18699b, this.f18700c.m21352k().m21759t(), this.f18700c, this.f18701d);
                    this.f18703f = c5502h;
                }
                C5502h.b bVarM21385c = c5502h.m21385c();
                this.f18702e = bVarM21385c;
                listM21390a = bVarM21385c.m21390a();
                if (this.f18700c.m21360s()) {
                    throw new IOException("Canceled");
                }
                if (this.f18698a.m21374a(this.f18699b, this.f18700c, listM21390a, false)) {
                    RealConnection realConnectionM21353l3 = this.f18700c.m21353l();
                    C0042f.m155b(realConnectionM21353l3);
                    this.f18701d.m21609j(this.f18700c, realConnectionM21353l3);
                    return realConnectionM21353l3;
                }
                c5485b0M21392c = bVarM21385c.m21392c();
                RealConnection realConnection = new RealConnection(this.f18698a, c5485b0M21392c);
                this.f18700c.m21366y(realConnection);
                realConnection.m21281f(i9, i10, i11, i12, z8, this.f18700c, this.f18701d);
                this.f18700c.m21366y(null);
                this.f18700c.m21352k().m21759t().m21379a(realConnection.m21301z());
                if (this.f18698a.m21374a(this.f18699b, this.f18700c, listM21390a, true)) {
                    synchronized (realConnection) {
                        this.f18698a.m21378e(realConnection);
                        this.f18700c.m21345c(realConnection);
                        C6319g c6319g2 = C6319g.f21314a;
                    }
                    this.f18701d.m21609j(this.f18700c, realConnection);
                    return realConnection;
                }
                RealConnection realConnectionM21353l4 = this.f18700c.m21353l();
                C0042f.m155b(realConnectionM21353l4);
                this.f18707j = c5485b0M21392c;
                C5057d.m19800n(realConnection.m21273D());
                this.f18701d.m21609j(this.f18700c, realConnectionM21353l4);
                return realConnectionM21353l4;
            }
            C0042f.m155b(c5485b0M21392c);
            this.f18707j = null;
            realConnection.m21281f(i9, i10, i11, i12, z8, this.f18700c, this.f18701d);
            this.f18700c.m21366y(null);
            this.f18700c.m21352k().m21759t().m21379a(realConnection.m21301z());
            if (this.f18698a.m21374a(this.f18699b, this.f18700c, listM21390a, true)) {
            }
        } catch (Throwable th) {
            this.f18700c.m21366y(null);
            throw th;
        }
        listM21390a = null;
        RealConnection realConnection2 = new RealConnection(this.f18698a, c5485b0M21392c);
        this.f18700c.m21366y(realConnection2);
    }

    /* renamed from: c */
    public final RealConnection m21335c(int i9, int i10, int i11, int i12, boolean z8, boolean z9) throws IOException {
        while (true) {
            RealConnection realConnectionM21334b = m21334b(i9, i10, i11, i12, z8);
            if (realConnectionM21334b.m21296u(z9)) {
                return realConnectionM21334b;
            }
            realConnectionM21334b.m21300y();
            if (this.f18707j == null) {
                C5502h.b bVar = this.f18702e;
                if (bVar != null ? bVar.m21391b() : true) {
                    continue;
                } else {
                    C5502h c5502h = this.f18703f;
                    if (!(c5502h != null ? c5502h.m21383a() : true)) {
                        throw new IOException("exhausted all routes");
                    }
                }
            }
        }
    }

    /* renamed from: d */
    public final C5482a m21336d() {
        return this.f18699b;
    }

    /* renamed from: e */
    public final boolean m21337e() {
        C5502h c5502h;
        boolean z8 = false;
        if (this.f18704g == 0 && this.f18705h == 0 && this.f18706i == 0) {
            return false;
        }
        if (this.f18707j != null) {
            return true;
        }
        C5485b0 c5485b0M21338f = m21338f();
        if (c5485b0M21338f != null) {
            this.f18707j = c5485b0M21338f;
            return true;
        }
        C5502h.b bVar = this.f18702e;
        if (bVar != null && bVar.m21391b()) {
            z8 = true;
        }
        if (z8 || (c5502h = this.f18703f) == null) {
            return true;
        }
        return c5502h.m21383a();
    }

    /* renamed from: f */
    public final C5485b0 m21338f() {
        RealConnection realConnectionM21353l;
        if (this.f18704g > 1 || this.f18705h > 1 || this.f18706i > 0 || (realConnectionM21353l = this.f18700c.m21353l()) == null) {
            return null;
        }
        synchronized (realConnectionM21353l) {
            if (realConnectionM21353l.m21292q() != 0) {
                return null;
            }
            if (C5057d.m19796j(realConnectionM21353l.m21301z().m21235a().m21228l(), this.f18699b.m21228l())) {
                return realConnectionM21353l.m21301z();
            }
            return null;
        }
    }

    /* renamed from: g */
    public final boolean m21339g(C5518s c5518s) {
        C0042f.m158e(c5518s, "url");
        C5518s c5518sM21228l = this.f18699b.m21228l();
        return c5518s.m21657l() == c5518sM21228l.m21657l() && C0042f.m154a(c5518s.m21653h(), c5518sM21228l.m21653h());
    }

    /* renamed from: h */
    public final void m21340h(IOException iOException) {
        C0042f.m158e(iOException, "e");
        this.f18707j = null;
        if ((iOException instanceof StreamResetException) && ((StreamResetException) iOException).errorCode == ErrorCode.REFUSED_STREAM) {
            this.f18704g++;
        } else if (iOException instanceof ConnectionShutdownException) {
            this.f18705h++;
        } else {
            this.f18706i++;
        }
    }
}
