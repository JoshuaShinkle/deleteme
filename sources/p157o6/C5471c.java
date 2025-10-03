package p157o6;

import com.google.common.net.HttpHeaders;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import okhttp3.C5517r;
import okhttp3.C5522w;
import okhttp3.C5523x;
import okhttp3.C5525z;
import okhttp3.Protocol;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.http2.C5505b;
import okhttp3.internal.http2.ErrorCode;
import p007a6.C0040d;
import p007a6.C0042f;
import p098i6.C5057d;
import p139m6.C5332e;
import p139m6.C5334g;
import p139m6.C5336i;
import p139m6.C5338k;
import p139m6.InterfaceC5331d;
import p204t6.C6343x;
import p204t6.InterfaceC6340u;
import p204t6.InterfaceC6342w;

/* renamed from: o6.c */
/* loaded from: classes.dex */
public final class C5471c implements InterfaceC5331d {

    /* renamed from: g */
    public static final a f18404g = new a(null);

    /* renamed from: h */
    public static final List<String> f18405h = C5057d.m19809w("connection", "host", "keep-alive", "proxy-connection", "te", "transfer-encoding", "encoding", "upgrade", ":method", ":path", ":scheme", ":authority");

    /* renamed from: i */
    public static final List<String> f18406i = C5057d.m19809w("connection", "host", "keep-alive", "proxy-connection", "te", "transfer-encoding", "encoding", "upgrade");

    /* renamed from: a */
    public final RealConnection f18407a;

    /* renamed from: b */
    public final C5334g f18408b;

    /* renamed from: c */
    public final C5505b f18409c;

    /* renamed from: d */
    public volatile C5472d f18410d;

    /* renamed from: e */
    public final Protocol f18411e;

    /* renamed from: f */
    public volatile boolean f18412f;

    /* renamed from: o6.c$a */
    public static final class a {
        public a() {
        }

        public /* synthetic */ a(C0040d c0040d) {
            this();
        }

        /* renamed from: a */
        public final List<C5469a> m21121a(C5523x c5523x) {
            C0042f.m158e(c5523x, "request");
            C5517r c5517rM21807e = c5523x.m21807e();
            ArrayList arrayList = new ArrayList(c5517rM21807e.size() + 4);
            arrayList.add(new C5469a(C5469a.f18392g, c5523x.m21809g()));
            arrayList.add(new C5469a(C5469a.f18393h, C5336i.f18181a.m20973c(c5523x.m21811i())));
            String strM21806d = c5523x.m21806d(HttpHeaders.HOST);
            if (strM21806d != null) {
                arrayList.add(new C5469a(C5469a.f18395j, strM21806d));
            }
            arrayList.add(new C5469a(C5469a.f18394i, c5523x.m21811i().m21661p()));
            int size = c5517rM21807e.size();
            for (int i9 = 0; i9 < size; i9++) {
                String strM21627b = c5517rM21807e.m21627b(i9);
                Locale locale = Locale.US;
                C0042f.m157d(locale, "US");
                String lowerCase = strM21627b.toLowerCase(locale);
                C0042f.m157d(lowerCase, "this as java.lang.String).toLowerCase(locale)");
                if (!C5471c.f18405h.contains(lowerCase) || (C0042f.m154a(lowerCase, "te") && C0042f.m154a(c5517rM21807e.m21629d(i9), "trailers"))) {
                    arrayList.add(new C5469a(lowerCase, c5517rM21807e.m21629d(i9)));
                }
            }
            return arrayList;
        }

        /* renamed from: b */
        public final C5525z.a m21122b(C5517r c5517r, Protocol protocol) throws ProtocolException, NumberFormatException {
            C0042f.m158e(c5517r, "headerBlock");
            C0042f.m158e(protocol, "protocol");
            C5517r.a aVar = new C5517r.a();
            int size = c5517r.size();
            C5338k c5338kM20980a = null;
            for (int i9 = 0; i9 < size; i9++) {
                String strM21627b = c5517r.m21627b(i9);
                String strM21629d = c5517r.m21629d(i9);
                if (C0042f.m154a(strM21627b, ":status")) {
                    c5338kM20980a = C5338k.f18184d.m20980a("HTTP/1.1 " + strM21629d);
                } else if (!C5471c.f18406i.contains(strM21627b)) {
                    aVar.m21633c(strM21627b, strM21629d);
                }
            }
            if (c5338kM20980a != null) {
                return new C5525z.a().m21871p(protocol).m21862g(c5338kM20980a.f18186b).m21868m(c5338kM20980a.f18187c).m21866k(aVar.m21635e());
            }
            throw new ProtocolException("Expected ':status' header not present");
        }
    }

    public C5471c(C5522w c5522w, RealConnection realConnection, C5334g c5334g, C5505b c5505b) {
        C0042f.m158e(c5522w, "client");
        C0042f.m158e(realConnection, "connection");
        C0042f.m158e(c5334g, "chain");
        C0042f.m158e(c5505b, "http2Connection");
        this.f18407a = realConnection;
        this.f18408b = c5334g;
        this.f18409c = c5505b;
        List<Protocol> listM21734A = c5522w.m21734A();
        Protocol protocol = Protocol.H2_PRIOR_KNOWLEDGE;
        this.f18411e = listM21734A.contains(protocol) ? protocol : Protocol.HTTP_2;
    }

    @Override // p139m6.InterfaceC5331d
    /* renamed from: a */
    public void mo20937a() {
        C5472d c5472d = this.f18410d;
        C0042f.m155b(c5472d);
        c5472d.m21141n().close();
    }

    @Override // p139m6.InterfaceC5331d
    /* renamed from: b */
    public void mo20938b(C5523x c5523x) throws IOException {
        C0042f.m158e(c5523x, "request");
        if (this.f18410d != null) {
            return;
        }
        this.f18410d = this.f18409c.m21459a0(f18404g.m21121a(c5523x), c5523x.m21803a() != null);
        if (this.f18412f) {
            C5472d c5472d = this.f18410d;
            C0042f.m155b(c5472d);
            c5472d.m21133f(ErrorCode.CANCEL);
            throw new IOException("Canceled");
        }
        C5472d c5472d2 = this.f18410d;
        C0042f.m155b(c5472d2);
        C6343x c6343xM21149v = c5472d2.m21149v();
        long jM20964h = this.f18408b.m20964h();
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        c6343xM21149v.mo24245g(jM20964h, timeUnit);
        C5472d c5472d3 = this.f18410d;
        C0042f.m155b(c5472d3);
        c5472d3.m21127E().mo24245g(this.f18408b.m20966j(), timeUnit);
    }

    @Override // p139m6.InterfaceC5331d
    /* renamed from: c */
    public InterfaceC6342w mo20939c(C5525z c5525z) {
        C0042f.m158e(c5525z, "response");
        C5472d c5472d = this.f18410d;
        C0042f.m155b(c5472d);
        return c5472d.m21143p();
    }

    @Override // p139m6.InterfaceC5331d
    public void cancel() {
        this.f18412f = true;
        C5472d c5472d = this.f18410d;
        if (c5472d != null) {
            c5472d.m21133f(ErrorCode.CANCEL);
        }
    }

    @Override // p139m6.InterfaceC5331d
    /* renamed from: d */
    public C5525z.a mo20940d(boolean z8) throws NumberFormatException, IOException {
        C5472d c5472d = this.f18410d;
        if (c5472d == null) {
            throw new IOException("stream wasn't created");
        }
        C5525z.a aVarM21122b = f18404g.m21122b(c5472d.m21125C(), this.f18411e);
        if (z8 && aVarM21122b.m21863h() == 100) {
            return null;
        }
        return aVarM21122b;
    }

    @Override // p139m6.InterfaceC5331d
    /* renamed from: e */
    public RealConnection mo20941e() {
        return this.f18407a;
    }

    @Override // p139m6.InterfaceC5331d
    /* renamed from: f */
    public void mo20942f() {
        this.f18409c.flush();
    }

    @Override // p139m6.InterfaceC5331d
    /* renamed from: g */
    public long mo20943g(C5525z c5525z) {
        C0042f.m158e(c5525z, "response");
        if (C5332e.m20946b(c5525z)) {
            return C5057d.m19808v(c5525z);
        }
        return 0L;
    }

    @Override // p139m6.InterfaceC5331d
    /* renamed from: h */
    public InterfaceC6340u mo20944h(C5523x c5523x, long j9) {
        C0042f.m158e(c5523x, "request");
        C5472d c5472d = this.f18410d;
        C0042f.m155b(c5472d);
        return c5472d.m21141n();
    }
}
