package p168p6;

import android.os.Build;
import android.security.NetworkSecurityPolicy;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509TrustManager;
import kotlin.collections.C5226i;
import okhttp3.Protocol;
import p007a6.C0040d;
import p007a6.C0042f;
import p177q6.C6166d;
import p177q6.C6170h;
import p177q6.C6171i;
import p177q6.C6172j;
import p177q6.C6173k;
import p177q6.C6174l;
import p177q6.C6176n;
import p177q6.InterfaceC6175m;
import p195s6.AbstractC6269c;
import p195s6.InterfaceC6271e;

/* renamed from: p6.b */
/* loaded from: classes.dex */
public final class C6105b extends C6113j {

    /* renamed from: f */
    public static final a f20718f = new a(null);

    /* renamed from: g */
    public static final boolean f20719g;

    /* renamed from: d */
    public final List<InterfaceC6175m> f20720d;

    /* renamed from: e */
    public final C6172j f20721e;

    /* renamed from: p6.b$a */
    public static final class a {
        public a() {
        }

        public /* synthetic */ a(C0040d c0040d) {
            this();
        }

        /* renamed from: a */
        public final C6113j m23413a() {
            if (m23414b()) {
                return new C6105b();
            }
            return null;
        }

        /* renamed from: b */
        public final boolean m23414b() {
            return C6105b.f20719g;
        }
    }

    /* renamed from: p6.b$b */
    public static final class b implements InterfaceC6271e {

        /* renamed from: a */
        public final X509TrustManager f20722a;

        /* renamed from: b */
        public final Method f20723b;

        public b(X509TrustManager x509TrustManager, Method method) {
            C0042f.m158e(x509TrustManager, "trustManager");
            C0042f.m158e(method, "findByIssuerAndSignatureMethod");
            this.f20722a = x509TrustManager;
            this.f20723b = method;
        }

        @Override // p195s6.InterfaceC6271e
        /* renamed from: a */
        public X509Certificate mo23415a(X509Certificate x509Certificate) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            C0042f.m158e(x509Certificate, "cert");
            try {
                Object objInvoke = this.f20723b.invoke(this.f20722a, x509Certificate);
                C0042f.m156c(objInvoke, "null cannot be cast to non-null type java.security.cert.TrustAnchor");
                return ((TrustAnchor) objInvoke).getTrustedCert();
            } catch (IllegalAccessException e9) {
                throw new AssertionError("unable to get issues and signature", e9);
            } catch (InvocationTargetException unused) {
                return null;
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            return C0042f.m154a(this.f20722a, bVar.f20722a) && C0042f.m154a(this.f20723b, bVar.f20723b);
        }

        public int hashCode() {
            return (this.f20722a.hashCode() * 31) + this.f20723b.hashCode();
        }

        public String toString() {
            return "CustomTrustRootIndex(trustManager=" + this.f20722a + ", findByIssuerAndSignatureMethod=" + this.f20723b + ')';
        }
    }

    static {
        boolean z8 = false;
        if (C6113j.f20745a.m23448h() && Build.VERSION.SDK_INT < 30) {
            z8 = true;
        }
        f20719g = z8;
    }

    public C6105b() {
        List listM20403i = C5226i.m20403i(C6176n.a.m23646b(C6176n.f20824j, null, 1, null), new C6174l(C6170h.f20806f.m23635d()), new C6174l(C6173k.f20820a.m23644a()), new C6174l(C6171i.f20814a.m23639a()));
        ArrayList arrayList = new ArrayList();
        for (Object obj : listM20403i) {
            if (((InterfaceC6175m) obj).mo23618b()) {
                arrayList.add(obj);
            }
        }
        this.f20720d = arrayList;
        this.f20721e = C6172j.f20816d.m23642a();
    }

    @Override // p168p6.C6113j
    /* renamed from: c */
    public AbstractC6269c mo23402c(X509TrustManager x509TrustManager) {
        C0042f.m158e(x509TrustManager, "trustManager");
        C6166d c6166dM23624a = C6166d.f20799d.m23624a(x509TrustManager);
        return c6166dM23624a != null ? c6166dM23624a : super.mo23402c(x509TrustManager);
    }

    @Override // p168p6.C6113j
    /* renamed from: d */
    public InterfaceC6271e mo23409d(X509TrustManager x509TrustManager) throws NoSuchMethodException, SecurityException {
        C0042f.m158e(x509TrustManager, "trustManager");
        try {
            Method declaredMethod = x509TrustManager.getClass().getDeclaredMethod("findTrustAnchorByIssuerAndSignature", X509Certificate.class);
            declaredMethod.setAccessible(true);
            C0042f.m157d(declaredMethod, FirebaseAnalytics.Param.METHOD);
            return new b(x509TrustManager, declaredMethod);
        } catch (NoSuchMethodException unused) {
            return super.mo23409d(x509TrustManager);
        }
    }

    @Override // p168p6.C6113j
    /* renamed from: e */
    public void mo23403e(SSLSocket sSLSocket, String str, List<Protocol> list) {
        Object next;
        C0042f.m158e(sSLSocket, "sslSocket");
        C0042f.m158e(list, "protocols");
        Iterator<T> it = this.f20720d.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            } else {
                next = it.next();
                if (((InterfaceC6175m) next).mo23617a(sSLSocket)) {
                    break;
                }
            }
        }
        InterfaceC6175m interfaceC6175m = (InterfaceC6175m) next;
        if (interfaceC6175m != null) {
            interfaceC6175m.mo23620d(sSLSocket, str, list);
        }
    }

    @Override // p168p6.C6113j
    /* renamed from: f */
    public void mo23410f(Socket socket, InetSocketAddress inetSocketAddress, int i9) throws IOException {
        C0042f.m158e(socket, "socket");
        C0042f.m158e(inetSocketAddress, "address");
        try {
            socket.connect(inetSocketAddress, i9);
        } catch (ClassCastException e9) {
            if (Build.VERSION.SDK_INT != 26) {
                throw e9;
            }
            throw new IOException("Exception in connect", e9);
        }
    }

    @Override // p168p6.C6113j
    /* renamed from: g */
    public String mo23404g(SSLSocket sSLSocket) {
        Object next;
        C0042f.m158e(sSLSocket, "sslSocket");
        Iterator<T> it = this.f20720d.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (((InterfaceC6175m) next).mo23617a(sSLSocket)) {
                break;
            }
        }
        InterfaceC6175m interfaceC6175m = (InterfaceC6175m) next;
        if (interfaceC6175m != null) {
            return interfaceC6175m.mo23619c(sSLSocket);
        }
        return null;
    }

    @Override // p168p6.C6113j
    /* renamed from: h */
    public Object mo23411h(String str) {
        C0042f.m158e(str, "closer");
        return this.f20721e.m23640a(str);
    }

    @Override // p168p6.C6113j
    /* renamed from: i */
    public boolean mo23405i(String str) {
        C0042f.m158e(str, "hostname");
        return NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted(str);
    }

    @Override // p168p6.C6113j
    /* renamed from: l */
    public void mo23412l(String str, Object obj) {
        C0042f.m158e(str, "message");
        if (this.f20721e.m23641b(obj)) {
            return;
        }
        C6113j.m23439k(this, str, 5, null, 4, null);
    }
}
