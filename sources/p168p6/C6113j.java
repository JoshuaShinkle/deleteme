package p168p6;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.collections.C5227j;
import okhttp3.C5522w;
import okhttp3.Protocol;
import p007a6.C0040d;
import p007a6.C0042f;
import p177q6.C6167e;
import p195s6.AbstractC6269c;
import p195s6.C6267a;
import p195s6.C6268b;
import p195s6.InterfaceC6271e;
import p204t6.C6322c;

/* renamed from: p6.j */
/* loaded from: classes.dex */
public class C6113j {

    /* renamed from: a */
    public static final a f20745a;

    /* renamed from: b */
    public static volatile C6113j f20746b;

    /* renamed from: c */
    public static final Logger f20747c;

    /* renamed from: p6.j$a */
    public static final class a {
        public a() {
        }

        public /* synthetic */ a(C0040d c0040d) {
            this();
        }

        /* renamed from: b */
        public final List<String> m23442b(List<? extends Protocol> list) {
            C0042f.m158e(list, "protocols");
            ArrayList arrayList = new ArrayList();
            for (Object obj : list) {
                if (((Protocol) obj) != Protocol.HTTP_1_0) {
                    arrayList.add(obj);
                }
            }
            ArrayList arrayList2 = new ArrayList(C5227j.m20408n(arrayList, 10));
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                arrayList2.add(((Protocol) it.next()).toString());
            }
            return arrayList2;
        }

        /* renamed from: c */
        public final byte[] m23443c(List<? extends Protocol> list) {
            C0042f.m158e(list, "protocols");
            C6322c c6322c = new C6322c();
            for (String str : m23442b(list)) {
                c6322c.writeByte(str.length());
                c6322c.mo24221j(str);
            }
            return c6322c.m24191D();
        }

        /* renamed from: d */
        public final C6113j m23444d() {
            C6167e.f20802a.m23626b();
            C6113j c6113jM23406a = C6104a.f20715e.m23406a();
            if (c6113jM23406a != null) {
                return c6113jM23406a;
            }
            C6113j c6113jM23413a = C6105b.f20718f.m23413a();
            C0042f.m155b(c6113jM23413a);
            return c6113jM23413a;
        }

        /* renamed from: e */
        public final C6113j m23445e() {
            C6112i c6112iM23436a;
            C6106c c6106cM23419a;
            C6107d c6107dM23424b;
            if (m23450j() && (c6107dM23424b = C6107d.f20727e.m23424b()) != null) {
                return c6107dM23424b;
            }
            if (m23449i() && (c6106cM23419a = C6106c.f20724e.m23419a()) != null) {
                return c6106cM23419a;
            }
            if (m23451k() && (c6112iM23436a = C6112i.f20742e.m23436a()) != null) {
                return c6112iM23436a;
            }
            C6111h c6111hM23433a = C6111h.f20740d.m23433a();
            if (c6111hM23433a != null) {
                return c6111hM23433a;
            }
            C6113j c6113jM23429a = C6108e.f20731i.m23429a();
            return c6113jM23429a != null ? c6113jM23429a : new C6113j();
        }

        /* renamed from: f */
        public final C6113j m23446f() {
            return m23448h() ? m23444d() : m23445e();
        }

        /* renamed from: g */
        public final C6113j m23447g() {
            return C6113j.f20746b;
        }

        /* renamed from: h */
        public final boolean m23448h() {
            return C0042f.m154a("Dalvik", System.getProperty("java.vm.name"));
        }

        /* renamed from: i */
        public final boolean m23449i() {
            return C0042f.m154a("BC", Security.getProviders()[0].getName());
        }

        /* renamed from: j */
        public final boolean m23450j() {
            return C0042f.m154a("Conscrypt", Security.getProviders()[0].getName());
        }

        /* renamed from: k */
        public final boolean m23451k() {
            return C0042f.m154a("OpenJSSE", Security.getProviders()[0].getName());
        }
    }

    static {
        a aVar = new a(null);
        f20745a = aVar;
        f20746b = aVar.m23446f();
        f20747c = Logger.getLogger(C5522w.class.getName());
    }

    /* renamed from: k */
    public static /* synthetic */ void m23439k(C6113j c6113j, String str, int i9, Throwable th, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: log");
        }
        if ((i10 & 2) != 0) {
            i9 = 4;
        }
        if ((i10 & 4) != 0) {
            th = null;
        }
        c6113j.m23440j(str, i9, th);
    }

    /* renamed from: b */
    public void mo23426b(SSLSocket sSLSocket) {
        C0042f.m158e(sSLSocket, "sslSocket");
    }

    /* renamed from: c */
    public AbstractC6269c mo23402c(X509TrustManager x509TrustManager) {
        C0042f.m158e(x509TrustManager, "trustManager");
        return new C6267a(mo23409d(x509TrustManager));
    }

    /* renamed from: d */
    public InterfaceC6271e mo23409d(X509TrustManager x509TrustManager) {
        C0042f.m158e(x509TrustManager, "trustManager");
        X509Certificate[] acceptedIssuers = x509TrustManager.getAcceptedIssuers();
        C0042f.m157d(acceptedIssuers, "trustManager.acceptedIssuers");
        return new C6268b((X509Certificate[]) Arrays.copyOf(acceptedIssuers, acceptedIssuers.length));
    }

    /* renamed from: e */
    public void mo23403e(SSLSocket sSLSocket, String str, List<Protocol> list) {
        C0042f.m158e(sSLSocket, "sslSocket");
        C0042f.m158e(list, "protocols");
    }

    /* renamed from: f */
    public void mo23410f(Socket socket, InetSocketAddress inetSocketAddress, int i9) throws IOException {
        C0042f.m158e(socket, "socket");
        C0042f.m158e(inetSocketAddress, "address");
        socket.connect(inetSocketAddress, i9);
    }

    /* renamed from: g */
    public String mo23404g(SSLSocket sSLSocket) {
        C0042f.m158e(sSLSocket, "sslSocket");
        return null;
    }

    /* renamed from: h */
    public Object mo23411h(String str) {
        C0042f.m158e(str, "closer");
        if (f20747c.isLoggable(Level.FINE)) {
            return new Throwable(str);
        }
        return null;
    }

    /* renamed from: i */
    public boolean mo23405i(String str) {
        C0042f.m158e(str, "hostname");
        return true;
    }

    /* renamed from: j */
    public void m23440j(String str, int i9, Throwable th) {
        C0042f.m158e(str, "message");
        f20747c.log(i9 == 5 ? Level.WARNING : Level.INFO, str, th);
    }

    /* renamed from: l */
    public void mo23412l(String str, Object obj) {
        C0042f.m158e(str, "message");
        if (obj == null) {
            str = str + " To see where this was allocated, set the OkHttpClient logger level to FINE: Logger.getLogger(OkHttpClient.class.getName()).setLevel(Level.FINE);";
        }
        m23440j(str, 5, (Throwable) obj);
    }

    /* renamed from: m */
    public SSLContext mo23417m() throws NoSuchAlgorithmException {
        SSLContext sSLContext = SSLContext.getInstance("TLS");
        C0042f.m157d(sSLContext, "getInstance(\"TLS\")");
        return sSLContext;
    }

    /* renamed from: n */
    public SSLSocketFactory mo23422n(X509TrustManager x509TrustManager) throws KeyManagementException {
        C0042f.m158e(x509TrustManager, "trustManager");
        try {
            SSLContext sSLContextMo23417m = mo23417m();
            sSLContextMo23417m.init(null, new TrustManager[]{x509TrustManager}, null);
            SSLSocketFactory socketFactory = sSLContextMo23417m.getSocketFactory();
            C0042f.m157d(socketFactory, "newSSLContext().apply {\n…ll)\n      }.socketFactory");
            return socketFactory;
        } catch (GeneralSecurityException e9) {
            throw new AssertionError("No System TLS: " + e9, e9);
        }
    }

    /* renamed from: o */
    public X509TrustManager mo23418o() throws NoSuchAlgorithmException, KeyStoreException {
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
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

    public String toString() {
        String simpleName = getClass().getSimpleName();
        C0042f.m157d(simpleName, "javaClass.simpleName");
        return simpleName;
    }
}
