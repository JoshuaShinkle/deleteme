package p168p6;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.Protocol;
import org.bouncycastle.jsse.BCSSLParameters;
import org.bouncycastle.jsse.BCSSLSocket;
import org.bouncycastle.jsse.provider.BouncyCastleJsseProvider;
import p007a6.C0040d;
import p007a6.C0042f;

/* renamed from: p6.c */
/* loaded from: classes.dex */
public final class C6106c extends C6113j {

    /* renamed from: e */
    public static final a f20724e;

    /* renamed from: f */
    public static final boolean f20725f;

    /* renamed from: d */
    public final Provider f20726d;

    /* renamed from: p6.c$a */
    public static final class a {
        public a() {
        }

        public /* synthetic */ a(C0040d c0040d) {
            this();
        }

        /* renamed from: a */
        public final C6106c m23419a() {
            C0040d c0040d = null;
            if (m23420b()) {
                return new C6106c(c0040d);
            }
            return null;
        }

        /* renamed from: b */
        public final boolean m23420b() {
            return C6106c.f20725f;
        }
    }

    static {
        a aVar = new a(null);
        f20724e = aVar;
        boolean z8 = false;
        try {
            Class.forName("org.bouncycastle.jsse.provider.BouncyCastleJsseProvider", false, aVar.getClass().getClassLoader());
            z8 = true;
        } catch (ClassNotFoundException unused) {
        }
        f20725f = z8;
    }

    public C6106c() {
        this.f20726d = new BouncyCastleJsseProvider();
    }

    public /* synthetic */ C6106c(C0040d c0040d) {
        this();
    }

    @Override // p168p6.C6113j
    /* renamed from: e */
    public void mo23403e(SSLSocket sSLSocket, String str, List<Protocol> list) {
        C0042f.m158e(sSLSocket, "sslSocket");
        C0042f.m158e(list, "protocols");
        if (!(sSLSocket instanceof BCSSLSocket)) {
            super.mo23403e(sSLSocket, str, list);
            return;
        }
        BCSSLSocket bCSSLSocket = (BCSSLSocket) sSLSocket;
        BCSSLParameters parameters = bCSSLSocket.getParameters();
        parameters.setApplicationProtocols((String[]) C6113j.f20745a.m23442b(list).toArray(new String[0]));
        bCSSLSocket.setParameters(parameters);
    }

    @Override // p168p6.C6113j
    /* renamed from: g */
    public String mo23404g(SSLSocket sSLSocket) {
        C0042f.m158e(sSLSocket, "sslSocket");
        if (!(sSLSocket instanceof BCSSLSocket)) {
            return super.mo23404g(sSLSocket);
        }
        String applicationProtocol = ((BCSSLSocket) sSLSocket).getApplicationProtocol();
        if (applicationProtocol == null ? true : C0042f.m154a(applicationProtocol, "")) {
            return null;
        }
        return applicationProtocol;
    }

    @Override // p168p6.C6113j
    /* renamed from: m */
    public SSLContext mo23417m() throws NoSuchAlgorithmException {
        SSLContext sSLContext = SSLContext.getInstance("TLS", this.f20726d);
        C0042f.m157d(sSLContext, "getInstance(\"TLS\", provider)");
        return sSLContext;
    }

    @Override // p168p6.C6113j
    /* renamed from: o */
    public X509TrustManager mo23418o() throws NoSuchAlgorithmException, KeyStoreException, NoSuchProviderException {
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("PKIX", "BCJSSE");
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
