package p168p6;

import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.Protocol;
import org.conscrypt.Conscrypt;
import org.conscrypt.ConscryptHostnameVerifier;
import p007a6.C0040d;
import p007a6.C0042f;

/* renamed from: p6.d */
/* loaded from: classes.dex */
public final class C6107d extends C6113j {

    /* renamed from: e */
    public static final a f20727e;

    /* renamed from: f */
    public static final boolean f20728f;

    /* renamed from: d */
    public final Provider f20729d;

    /* renamed from: p6.d$a */
    public static final class a {
        public a() {
        }

        public /* synthetic */ a(C0040d c0040d) {
            this();
        }

        /* renamed from: a */
        public final boolean m23423a(int i9, int i10, int i11) {
            Conscrypt.Version version = Conscrypt.version();
            return version.major() != i9 ? version.major() > i9 : version.minor() != i10 ? version.minor() > i10 : version.patch() >= i11;
        }

        /* renamed from: b */
        public final C6107d m23424b() {
            C0040d c0040d = null;
            if (m23425c()) {
                return new C6107d(c0040d);
            }
            return null;
        }

        /* renamed from: c */
        public final boolean m23425c() {
            return C6107d.f20728f;
        }
    }

    /* renamed from: p6.d$b */
    public static final class b implements ConscryptHostnameVerifier {

        /* renamed from: a */
        public static final b f20730a = new b();
    }

    static {
        a aVar = new a(null);
        f20727e = aVar;
        boolean z8 = false;
        try {
            Class.forName("org.conscrypt.Conscrypt$Version", false, aVar.getClass().getClassLoader());
            if (Conscrypt.isAvailable()) {
                if (aVar.m23423a(2, 1, 0)) {
                    z8 = true;
                }
            }
        } catch (ClassNotFoundException | NoClassDefFoundError unused) {
        }
        f20728f = z8;
    }

    public C6107d() {
        Provider providerNewProvider = Conscrypt.newProvider();
        C0042f.m157d(providerNewProvider, "newProvider()");
        this.f20729d = providerNewProvider;
    }

    public /* synthetic */ C6107d(C0040d c0040d) {
        this();
    }

    @Override // p168p6.C6113j
    /* renamed from: e */
    public void mo23403e(SSLSocket sSLSocket, String str, List<Protocol> list) {
        C0042f.m158e(sSLSocket, "sslSocket");
        C0042f.m158e(list, "protocols");
        if (!Conscrypt.isConscrypt(sSLSocket)) {
            super.mo23403e(sSLSocket, str, list);
        } else {
            Conscrypt.setUseSessionTickets(sSLSocket, true);
            Conscrypt.setApplicationProtocols(sSLSocket, (String[]) C6113j.f20745a.m23442b(list).toArray(new String[0]));
        }
    }

    @Override // p168p6.C6113j
    /* renamed from: g */
    public String mo23404g(SSLSocket sSLSocket) {
        C0042f.m158e(sSLSocket, "sslSocket");
        return Conscrypt.isConscrypt(sSLSocket) ? Conscrypt.getApplicationProtocol(sSLSocket) : super.mo23404g(sSLSocket);
    }

    @Override // p168p6.C6113j
    /* renamed from: m */
    public SSLContext mo23417m() throws NoSuchAlgorithmException {
        SSLContext sSLContext = SSLContext.getInstance("TLS", this.f20729d);
        C0042f.m157d(sSLContext, "getInstance(\"TLS\", provider)");
        return sSLContext;
    }

    @Override // p168p6.C6113j
    /* renamed from: n */
    public SSLSocketFactory mo23422n(X509TrustManager x509TrustManager) throws NoSuchAlgorithmException, KeyManagementException {
        C0042f.m158e(x509TrustManager, "trustManager");
        SSLContext sSLContextMo23417m = mo23417m();
        sSLContextMo23417m.init(null, new TrustManager[]{x509TrustManager}, null);
        SSLSocketFactory socketFactory = sSLContextMo23417m.getSocketFactory();
        C0042f.m157d(socketFactory, "newSSLContext().apply {\n…null)\n    }.socketFactory");
        return socketFactory;
    }

    @Override // p168p6.C6113j
    /* renamed from: o */
    public X509TrustManager mo23418o() throws NoSuchAlgorithmException, KeyStoreException {
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init((KeyStore) null);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        C0042f.m155b(trustManagers);
        if (trustManagers.length == 1 && (trustManagers[0] instanceof X509TrustManager)) {
            TrustManager trustManager = trustManagers[0];
            C0042f.m156c(trustManager, "null cannot be cast to non-null type javax.net.ssl.X509TrustManager");
            X509TrustManager x509TrustManager = (X509TrustManager) trustManager;
            Conscrypt.setHostnameVerifier(x509TrustManager, b.f20730a);
            return x509TrustManager;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unexpected default trust managers: ");
        String string = Arrays.toString(trustManagers);
        C0042f.m157d(string, "toString(this)");
        sb.append(string);
        throw new IllegalStateException(sb.toString().toString());
    }
}
