package p168p6;

import android.annotation.SuppressLint;
import android.os.Build;
import android.security.NetworkSecurityPolicy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509TrustManager;
import kotlin.collections.C5226i;
import okhttp3.Protocol;
import p007a6.C0040d;
import p007a6.C0042f;
import p177q6.C6165c;
import p177q6.C6166d;
import p177q6.C6170h;
import p177q6.C6171i;
import p177q6.C6173k;
import p177q6.C6174l;
import p177q6.InterfaceC6175m;
import p195s6.AbstractC6269c;

/* renamed from: p6.a */
/* loaded from: classes.dex */
public final class C6104a extends C6113j {

    /* renamed from: e */
    public static final a f20715e = new a(null);

    /* renamed from: f */
    public static final boolean f20716f;

    /* renamed from: d */
    public final List<InterfaceC6175m> f20717d;

    /* renamed from: p6.a$a */
    public static final class a {
        public a() {
        }

        public /* synthetic */ a(C0040d c0040d) {
            this();
        }

        /* renamed from: a */
        public final C6113j m23406a() {
            if (m23407b()) {
                return new C6104a();
            }
            return null;
        }

        /* renamed from: b */
        public final boolean m23407b() {
            return C6104a.f20716f;
        }
    }

    static {
        f20716f = C6113j.f20745a.m23448h() && Build.VERSION.SDK_INT >= 29;
    }

    public C6104a() {
        List listM20403i = C5226i.m20403i(C6165c.f20798a.m23621a(), new C6174l(C6170h.f20806f.m23635d()), new C6174l(C6173k.f20820a.m23644a()), new C6174l(C6171i.f20814a.m23639a()));
        ArrayList arrayList = new ArrayList();
        for (Object obj : listM20403i) {
            if (((InterfaceC6175m) obj).mo23618b()) {
                arrayList.add(obj);
            }
        }
        this.f20717d = arrayList;
    }

    @Override // p168p6.C6113j
    /* renamed from: c */
    public AbstractC6269c mo23402c(X509TrustManager x509TrustManager) {
        C0042f.m158e(x509TrustManager, "trustManager");
        C6166d c6166dM23624a = C6166d.f20799d.m23624a(x509TrustManager);
        return c6166dM23624a != null ? c6166dM23624a : super.mo23402c(x509TrustManager);
    }

    @Override // p168p6.C6113j
    /* renamed from: e */
    public void mo23403e(SSLSocket sSLSocket, String str, List<? extends Protocol> list) {
        Object next;
        C0042f.m158e(sSLSocket, "sslSocket");
        C0042f.m158e(list, "protocols");
        Iterator<T> it = this.f20717d.iterator();
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
    /* renamed from: g */
    public String mo23404g(SSLSocket sSLSocket) {
        Object next;
        C0042f.m158e(sSLSocket, "sslSocket");
        Iterator<T> it = this.f20717d.iterator();
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
    @SuppressLint({"NewApi"})
    /* renamed from: i */
    public boolean mo23405i(String str) {
        C0042f.m158e(str, "hostname");
        return NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted(str);
    }
}
