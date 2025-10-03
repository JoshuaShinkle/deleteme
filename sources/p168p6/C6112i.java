package p168p6;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.Protocol;
import org.openjsse.javax.net.ssl.SSLParameters;
import org.openjsse.net.ssl.OpenJSSE;
import p007a6.C0040d;
import p007a6.C0042f;

/* renamed from: p6.i */
/* loaded from: classes.dex */
public final class C6112i extends C6113j {

    /* renamed from: e */
    public static final a f20742e;

    /* renamed from: f */
    public static final boolean f20743f;

    /* renamed from: d */
    public final Provider f20744d;

    /* renamed from: p6.i$a */
    public static final class a {
        public a() {
        }

        public /* synthetic */ a(C0040d c0040d) {
            this();
        }

        /* renamed from: a */
        public final C6112i m23436a() {
            C0040d c0040d = null;
            if (m23437b()) {
                return new C6112i(c0040d);
            }
            return null;
        }

        /* renamed from: b */
        public final boolean m23437b() {
            return C6112i.f20743f;
        }
    }

    static {
        a aVar = new a(null);
        f20742e = aVar;
        boolean z8 = false;
        try {
            Class.forName("org.openjsse.net.ssl.OpenJSSE", false, aVar.getClass().getClassLoader());
            z8 = true;
        } catch (ClassNotFoundException unused) {
        }
        f20743f = z8;
    }

    public C6112i() {
        this.f20744d = new OpenJSSE();
    }

    public /* synthetic */ C6112i(C0040d c0040d) {
        this();
    }

    @Override // p168p6.C6113j
    /* renamed from: e */
    public void mo23403e(SSLSocket sSLSocket, String str, List<Protocol> list) {
        C0042f.m158e(sSLSocket, "sslSocket");
        C0042f.m158e(list, "protocols");
        if (!(sSLSocket instanceof org.openjsse.javax.net.ssl.SSLSocket)) {
            super.mo23403e(sSLSocket, str, list);
            return;
        }
        org.openjsse.javax.net.ssl.SSLSocket sSLSocket2 = (org.openjsse.javax.net.ssl.SSLSocket) sSLSocket;
        SSLParameters sSLParameters = sSLSocket2.getSSLParameters();
        if (sSLParameters instanceof SSLParameters) {
            sSLParameters.setApplicationProtocols((String[]) C6113j.f20745a.m23442b(list).toArray(new String[0]));
            sSLSocket2.setSSLParameters(sSLParameters);
        }
    }

    @Override // p168p6.C6113j
    /* renamed from: g */
    public String mo23404g(SSLSocket sSLSocket) {
        C0042f.m158e(sSLSocket, "sslSocket");
        if (!(sSLSocket instanceof org.openjsse.javax.net.ssl.SSLSocket)) {
            return super.mo23404g(sSLSocket);
        }
        String applicationProtocol = ((org.openjsse.javax.net.ssl.SSLSocket) sSLSocket).getApplicationProtocol();
        if (applicationProtocol == null ? true : C0042f.m154a(applicationProtocol, "")) {
            return null;
        }
        return applicationProtocol;
    }

    @Override // p168p6.C6113j
    /* renamed from: m */
    public SSLContext mo23417m() throws NoSuchAlgorithmException {
        SSLContext sSLContext = SSLContext.getInstance("TLSv1.3", this.f20744d);
        C0042f.m157d(sSLContext, "getInstance(\"TLSv1.3\", provider)");
        return sSLContext;
    }

    @Override // p168p6.C6113j
    /* renamed from: o */
    public X509TrustManager mo23418o() throws NoSuchAlgorithmException, KeyStoreException {
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm(), this.f20744d);
        trustManagerFactory.init((KeyStore) null);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        C0042f.m155b(trustManagers);
        if (trustManagers.length == 1 && (trustManagers[0] instanceof X509TrustManager)) {
            TrustManager trustManager = trustManagers[0];
            C0042f.m156c(trustManager, "null cannot be cast to non-null type javax.net.ssl.X509TrustManager");
            return (X509TrustManager) trustManager;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unexpected default trust managers: ");
        String string = Arrays.toString(trustManagers);
        C0042f.m157d(string, "toString(this)");
        sb.append(string);
        throw new IllegalStateException(sb.toString().toString());
    }
}
