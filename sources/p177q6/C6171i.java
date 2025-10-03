package p177q6;

import java.util.List;
import javax.net.ssl.SSLSocket;
import okhttp3.Protocol;
import org.bouncycastle.jsse.BCSSLParameters;
import org.bouncycastle.jsse.BCSSLSocket;
import p007a6.C0040d;
import p007a6.C0042f;
import p168p6.C6106c;
import p168p6.C6113j;
import p177q6.C6174l;

/* renamed from: q6.i */
/* loaded from: classes.dex */
public final class C6171i implements InterfaceC6175m {

    /* renamed from: a */
    public static final b f20814a = new b(null);

    /* renamed from: b */
    public static final C6174l.a f20815b = new a();

    /* renamed from: q6.i$a */
    public static final class a implements C6174l.a {
        @Override // p177q6.C6174l.a
        /* renamed from: a */
        public boolean mo23636a(SSLSocket sSLSocket) {
            C0042f.m158e(sSLSocket, "sslSocket");
            return C6106c.f20724e.m23420b() && (sSLSocket instanceof BCSSLSocket);
        }

        @Override // p177q6.C6174l.a
        /* renamed from: b */
        public InterfaceC6175m mo23637b(SSLSocket sSLSocket) {
            C0042f.m158e(sSLSocket, "sslSocket");
            return new C6171i();
        }
    }

    /* renamed from: q6.i$b */
    public static final class b {
        public b() {
        }

        public /* synthetic */ b(C0040d c0040d) {
            this();
        }

        /* renamed from: a */
        public final C6174l.a m23639a() {
            return C6171i.f20815b;
        }
    }

    @Override // p177q6.InterfaceC6175m
    /* renamed from: a */
    public boolean mo23617a(SSLSocket sSLSocket) {
        C0042f.m158e(sSLSocket, "sslSocket");
        return sSLSocket instanceof BCSSLSocket;
    }

    @Override // p177q6.InterfaceC6175m
    /* renamed from: b */
    public boolean mo23618b() {
        return C6106c.f20724e.m23420b();
    }

    @Override // p177q6.InterfaceC6175m
    /* renamed from: c */
    public String mo23619c(SSLSocket sSLSocket) {
        C0042f.m158e(sSLSocket, "sslSocket");
        String applicationProtocol = ((BCSSLSocket) sSLSocket).getApplicationProtocol();
        if (applicationProtocol == null ? true : C0042f.m154a(applicationProtocol, "")) {
            return null;
        }
        return applicationProtocol;
    }

    @Override // p177q6.InterfaceC6175m
    /* renamed from: d */
    public void mo23620d(SSLSocket sSLSocket, String str, List<? extends Protocol> list) {
        C0042f.m158e(sSLSocket, "sslSocket");
        C0042f.m158e(list, "protocols");
        if (mo23617a(sSLSocket)) {
            BCSSLSocket bCSSLSocket = (BCSSLSocket) sSLSocket;
            BCSSLParameters parameters = bCSSLSocket.getParameters();
            parameters.setApplicationProtocols((String[]) C6113j.f20745a.m23442b(list).toArray(new String[0]));
            bCSSLSocket.setParameters(parameters);
        }
    }
}
