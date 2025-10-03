package p118k6;

import com.google.common.net.HttpHeaders;
import java.io.IOException;
import kotlin.text.C5255l;
import okhttp3.AbstractC5483a0;
import okhttp3.AbstractC5516q;
import okhttp3.C5486c;
import okhttp3.C5517r;
import okhttp3.C5523x;
import okhttp3.C5525z;
import okhttp3.InterfaceC5488e;
import okhttp3.InterfaceC5519t;
import okhttp3.Protocol;
import okhttp3.internal.connection.C5499e;
import p007a6.C0040d;
import p007a6.C0042f;
import p098i6.C5057d;
import p118k6.C5201b;

/* renamed from: k6.a */
/* loaded from: classes.dex */
public final class C5200a implements InterfaceC5519t {

    /* renamed from: a */
    public static final a f17809a = new a(null);

    /* renamed from: k6.a$a */
    public static final class a {
        public a() {
        }

        public /* synthetic */ a(C0040d c0040d) {
            this();
        }

        /* renamed from: c */
        public final C5517r m20326c(C5517r c5517r, C5517r c5517r2) {
            C5517r.a aVar = new C5517r.a();
            int size = c5517r.size();
            for (int i9 = 0; i9 < size; i9++) {
                String strM21627b = c5517r.m21627b(i9);
                String strM21629d = c5517r.m21629d(i9);
                if ((!C5255l.m20513l(HttpHeaders.WARNING, strM21627b, true) || !C5255l.m20525x(strM21629d, "1", false, 2, null)) && (m20327d(strM21627b) || !m20328e(strM21627b) || c5517r2.m21626a(strM21627b) == null)) {
                    aVar.m21633c(strM21627b, strM21629d);
                }
            }
            int size2 = c5517r2.size();
            for (int i10 = 0; i10 < size2; i10++) {
                String strM21627b2 = c5517r2.m21627b(i10);
                if (!m20327d(strM21627b2) && m20328e(strM21627b2)) {
                    aVar.m21633c(strM21627b2, c5517r2.m21629d(i10));
                }
            }
            return aVar.m21635e();
        }

        /* renamed from: d */
        public final boolean m20327d(String str) {
            return C5255l.m20513l(HttpHeaders.CONTENT_LENGTH, str, true) || C5255l.m20513l(HttpHeaders.CONTENT_ENCODING, str, true) || C5255l.m20513l(HttpHeaders.CONTENT_TYPE, str, true);
        }

        /* renamed from: e */
        public final boolean m20328e(String str) {
            return (C5255l.m20513l(HttpHeaders.CONNECTION, str, true) || C5255l.m20513l("Keep-Alive", str, true) || C5255l.m20513l(HttpHeaders.PROXY_AUTHENTICATE, str, true) || C5255l.m20513l(HttpHeaders.PROXY_AUTHORIZATION, str, true) || C5255l.m20513l(HttpHeaders.f15464TE, str, true) || C5255l.m20513l("Trailers", str, true) || C5255l.m20513l(HttpHeaders.TRANSFER_ENCODING, str, true) || C5255l.m20513l(HttpHeaders.UPGRADE, str, true)) ? false : true;
        }

        /* renamed from: f */
        public final C5525z m20329f(C5525z c5525z) {
            return (c5525z != null ? c5525z.m21849f() : null) != null ? c5525z.m21843G().m21857b(null).m21858c() : c5525z;
        }
    }

    public C5200a(C5486c c5486c) {
    }

    @Override // okhttp3.InterfaceC5519t
    /* renamed from: a */
    public C5525z mo20323a(InterfaceC5519t.a aVar) throws IOException {
        AbstractC5516q abstractC5516qM21354m;
        C0042f.m158e(aVar, "chain");
        InterfaceC5488e interfaceC5488eCall = aVar.call();
        C5201b c5201bM20334b = new C5201b.b(System.currentTimeMillis(), aVar.mo20959b(), null).m20334b();
        C5523x c5523xM20331b = c5201bM20334b.m20331b();
        C5525z c5525zM20330a = c5201bM20334b.m20330a();
        C5499e c5499e = interfaceC5488eCall instanceof C5499e ? (C5499e) interfaceC5488eCall : null;
        if (c5499e == null || (abstractC5516qM21354m = c5499e.m21354m()) == null) {
            abstractC5516qM21354m = AbstractC5516q.f18937b;
        }
        if (c5523xM20331b == null && c5525zM20330a == null) {
            C5525z c5525zM21858c = new C5525z.a().m21873r(aVar.mo20959b()).m21871p(Protocol.HTTP_1_1).m21862g(504).m21868m("Unsatisfiable Request (only-if-cached)").m21857b(C5057d.f17445c).m21874s(-1L).m21872q(System.currentTimeMillis()).m21858c();
            abstractC5516qM21354m.m21625z(interfaceC5488eCall, c5525zM21858c);
            return c5525zM21858c;
        }
        if (c5523xM20331b == null) {
            C0042f.m155b(c5525zM20330a);
            C5525z c5525zM21858c2 = c5525zM20330a.m21843G().m21859d(f17809a.m20329f(c5525zM20330a)).m21858c();
            abstractC5516qM21354m.m21601b(interfaceC5488eCall, c5525zM21858c2);
            return c5525zM21858c2;
        }
        if (c5525zM20330a != null) {
            abstractC5516qM21354m.m21600a(interfaceC5488eCall, c5525zM20330a);
        }
        C5525z c5525zMo20958a = aVar.mo20958a(c5523xM20331b);
        if (c5525zM20330a != null) {
            boolean z8 = false;
            if (c5525zMo20958a != null && c5525zMo20958a.m21853x() == 304) {
                z8 = true;
            }
            if (z8) {
                C5525z.a aVarM21843G = c5525zM20330a.m21843G();
                a aVar2 = f17809a;
                aVarM21843G.m21866k(aVar2.m20326c(c5525zM20330a.m21839C(), c5525zMo20958a.m21839C())).m21874s(c5525zMo20958a.m21848L()).m21872q(c5525zMo20958a.m21846J()).m21859d(aVar2.m20329f(c5525zM20330a)).m21869n(aVar2.m20329f(c5525zMo20958a)).m21858c();
                AbstractC5483a0 abstractC5483a0M21849f = c5525zMo20958a.m21849f();
                C0042f.m155b(abstractC5483a0M21849f);
                abstractC5483a0M21849f.close();
                C0042f.m155b(null);
                throw null;
            }
            AbstractC5483a0 abstractC5483a0M21849f2 = c5525zM20330a.m21849f();
            if (abstractC5483a0M21849f2 != null) {
                C5057d.m19799m(abstractC5483a0M21849f2);
            }
        }
        C0042f.m155b(c5525zMo20958a);
        C5525z.a aVarM21843G2 = c5525zMo20958a.m21843G();
        a aVar3 = f17809a;
        return aVarM21843G2.m21859d(aVar3.m20329f(c5525zM20330a)).m21869n(aVar3.m20329f(c5525zMo20958a)).m21858c();
    }
}
