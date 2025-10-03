package p139m6;

import com.google.common.net.HttpHeaders;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import java.util.List;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import kotlin.collections.C5226i;
import kotlin.collections.C5234q;
import kotlin.text.Regex;
import okhttp3.AbstractC5524y;
import okhttp3.C5485b0;
import okhttp3.C5518s;
import okhttp3.C5522w;
import okhttp3.C5523x;
import okhttp3.C5525z;
import okhttp3.InterfaceC5519t;
import okhttp3.internal.connection.C5497c;
import okhttp3.internal.connection.C5499e;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.connection.RouteException;
import okhttp3.internal.http2.ConnectionShutdownException;
import p007a6.C0040d;
import p007a6.C0042f;
import p098i6.C5057d;

/* renamed from: m6.j */
/* loaded from: classes.dex */
public final class C5337j implements InterfaceC5519t {

    /* renamed from: b */
    public static final a f18182b = new a(null);

    /* renamed from: a */
    public final C5522w f18183a;

    /* renamed from: m6.j$a */
    public static final class a {
        public a() {
        }

        public /* synthetic */ a(C0040d c0040d) {
            this();
        }
    }

    public C5337j(C5522w c5522w) {
        C0042f.m158e(c5522w, "client");
        this.f18183a = c5522w;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0040, code lost:
    
        r7 = r0;
        r0 = r1.m21356o();
        r6 = m20975c(r7, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0049, code lost:
    
        if (r6 != null) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x004b, code lost:
    
        if (r0 == null) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0051, code lost:
    
        if (r0.m21322m() == false) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0053, code lost:
    
        r1.m21367z();
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0056, code lost:
    
        r1.m21351j(false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0059, code lost:
    
        return r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x005a, code lost:
    
        r0 = r6.m21803a();
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x005e, code lost:
    
        if (r0 == null) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0064, code lost:
    
        if (r0.m21826g() == false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0066, code lost:
    
        r1.m21351j(false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0069, code lost:
    
        return r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x006a, code lost:
    
        r0 = r7.m21849f();
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x006e, code lost:
    
        if (r0 == null) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0070, code lost:
    
        p098i6.C5057d.m19799m(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0073, code lost:
    
        r8 = r8 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0077, code lost:
    
        if (r8 > 20) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0094, code lost:
    
        throw new java.net.ProtocolException("Too many follow-up requests: " + r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0026, code lost:
    
        if (r7 == null) goto L10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0028, code lost:
    
        r0 = r0.m21843G().m21870o(r7.m21843G().m21857b(null).m21858c()).m21858c();
     */
    @Override // okhttp3.InterfaceC5519t
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public C5525z mo20323a(InterfaceC5519t.a aVar) {
        C5523x c5523xM20975c;
        C0042f.m158e(aVar, "chain");
        C5334g c5334g = (C5334g) aVar;
        C5523x c5523xM20965i = c5334g.m20965i();
        C5499e c5499eM20961e = c5334g.m20961e();
        List listM20400f = C5226i.m20400f();
        int i9 = 0;
        C5525z c5525z = null;
        while (true) {
            boolean z8 = true;
            while (true) {
                c5499eM20961e.m21350i(c5523xM20965i, z8);
                try {
                    if (c5499eM20961e.m21360s()) {
                        throw new IOException("Canceled");
                    }
                    try {
                        C5525z c5525zMo20958a = c5334g.mo20958a(c5523xM20965i);
                        break;
                    } catch (IOException e9) {
                        if (!m20977e(e9, c5499eM20961e, c5523xM20965i, !(e9 instanceof ConnectionShutdownException))) {
                            throw C5057d.m19785Y(e9, listM20400f);
                        }
                        listM20400f = C5234q.m20413B(listM20400f, e9);
                    } catch (RouteException e10) {
                        if (!m20977e(e10.m21306c(), c5499eM20961e, c5523xM20965i, false)) {
                            throw C5057d.m19785Y(e10.m21305b(), listM20400f);
                        }
                        listM20400f = C5234q.m20413B(listM20400f, e10.m21305b());
                    }
                    c5499eM20961e.m21351j(true);
                    z8 = false;
                } catch (Throwable th) {
                    c5499eM20961e.m21351j(true);
                    throw th;
                }
            }
            c5499eM20961e.m21351j(true);
            c5523xM20965i = c5523xM20975c;
        }
    }

    /* renamed from: b */
    public final C5523x m20974b(C5525z c5525z, String str) {
        String strM21837B;
        C5518s c5518sM21660o;
        if (!this.f18183a.m21757r() || (strM21837B = C5525z.m21837B(c5525z, HttpHeaders.LOCATION, null, 2, null)) == null || (c5518sM21660o = c5525z.m21847K().m21811i().m21660o(strM21837B)) == null) {
            return null;
        }
        if (!C0042f.m154a(c5518sM21660o.m21661p(), c5525z.m21847K().m21811i().m21661p()) && !this.f18183a.m21758s()) {
            return null;
        }
        C5523x.a aVarM21810h = c5525z.m21847K().m21810h();
        if (C5333f.m20953a(str)) {
            int iM21853x = c5525z.m21853x();
            C5333f c5333f = C5333f.f18168a;
            boolean z8 = c5333f.m20956c(str) || iM21853x == 308 || iM21853x == 307;
            if (!c5333f.m20955b(str) || iM21853x == 308 || iM21853x == 307) {
                aVarM21810h.m21817f(str, z8 ? c5525z.m21847K().m21803a() : null);
            } else {
                aVarM21810h.m21817f("GET", null);
            }
            if (!z8) {
                aVarM21810h.m21819h(HttpHeaders.TRANSFER_ENCODING);
                aVarM21810h.m21819h(HttpHeaders.CONTENT_LENGTH);
                aVarM21810h.m21819h(HttpHeaders.CONTENT_TYPE);
            }
        }
        if (!C5057d.m19796j(c5525z.m21847K().m21811i(), c5518sM21660o)) {
            aVarM21810h.m21819h(HttpHeaders.AUTHORIZATION);
        }
        return aVarM21810h.m21821j(c5518sM21660o).m21813b();
    }

    /* renamed from: c */
    public final C5523x m20975c(C5525z c5525z, C5497c c5497c) throws ProtocolException {
        RealConnection realConnectionM21317h;
        C5485b0 c5485b0M21301z = (c5497c == null || (realConnectionM21317h = c5497c.m21317h()) == null) ? null : realConnectionM21317h.m21301z();
        int iM21853x = c5525z.m21853x();
        String strM21809g = c5525z.m21847K().m21809g();
        if (iM21853x != 307 && iM21853x != 308) {
            if (iM21853x == 401) {
                return this.f18183a.m21745e().mo19952a(c5485b0M21301z, c5525z);
            }
            if (iM21853x == 421) {
                AbstractC5524y abstractC5524yM21803a = c5525z.m21847K().m21803a();
                if ((abstractC5524yM21803a != null && abstractC5524yM21803a.m21826g()) || c5497c == null || !c5497c.m21321l()) {
                    return null;
                }
                c5497c.m21317h().m21299x();
                return c5525z.m21847K();
            }
            if (iM21853x == 503) {
                C5525z c5525zM21844H = c5525z.m21844H();
                if ((c5525zM21844H == null || c5525zM21844H.m21853x() != 503) && m20979g(c5525z, Integer.MAX_VALUE) == 0) {
                    return c5525z.m21847K();
                }
                return null;
            }
            if (iM21853x == 407) {
                C0042f.m155b(c5485b0M21301z);
                if (c5485b0M21301z.m21236b().type() == Proxy.Type.HTTP) {
                    return this.f18183a.m21736C().mo19952a(c5485b0M21301z, c5525z);
                }
                throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
            }
            if (iM21853x == 408) {
                if (!this.f18183a.m21739F()) {
                    return null;
                }
                AbstractC5524y abstractC5524yM21803a2 = c5525z.m21847K().m21803a();
                if (abstractC5524yM21803a2 != null && abstractC5524yM21803a2.m21826g()) {
                    return null;
                }
                C5525z c5525zM21844H2 = c5525z.m21844H();
                if ((c5525zM21844H2 == null || c5525zM21844H2.m21853x() != 408) && m20979g(c5525z, 0) <= 0) {
                    return c5525z.m21847K();
                }
                return null;
            }
            switch (iM21853x) {
                case 300:
                case 301:
                case 302:
                case 303:
                    break;
                default:
                    return null;
            }
        }
        return m20974b(c5525z, strM21809g);
    }

    /* renamed from: d */
    public final boolean m20976d(IOException iOException, boolean z8) {
        if (iOException instanceof ProtocolException) {
            return false;
        }
        return iOException instanceof InterruptedIOException ? (iOException instanceof SocketTimeoutException) && !z8 : (((iOException instanceof SSLHandshakeException) && (iOException.getCause() instanceof CertificateException)) || (iOException instanceof SSLPeerUnverifiedException)) ? false : true;
    }

    /* renamed from: e */
    public final boolean m20977e(IOException iOException, C5499e c5499e, C5523x c5523x, boolean z8) {
        if (this.f18183a.m21739F()) {
            return !(z8 && m20978f(iOException, c5523x)) && m20976d(iOException, z8) && c5499e.m21365x();
        }
        return false;
    }

    /* renamed from: f */
    public final boolean m20978f(IOException iOException, C5523x c5523x) {
        AbstractC5524y abstractC5524yM21803a = c5523x.m21803a();
        return (abstractC5524yM21803a != null && abstractC5524yM21803a.m21826g()) || (iOException instanceof FileNotFoundException);
    }

    /* renamed from: g */
    public final int m20979g(C5525z c5525z, int i9) throws NumberFormatException {
        String strM21837B = C5525z.m21837B(c5525z, HttpHeaders.RETRY_AFTER, null, 2, null);
        if (strM21837B == null) {
            return i9;
        }
        if (!new Regex("\\d+").m20441a(strM21837B)) {
            return Integer.MAX_VALUE;
        }
        Integer numValueOf = Integer.valueOf(strM21837B);
        C0042f.m157d(numValueOf, "valueOf(header)");
        return numValueOf.intValue();
    }
}
