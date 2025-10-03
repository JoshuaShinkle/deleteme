package p177q6;

import android.net.http.X509TrustManagerExtensions;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.X509TrustManager;
import p007a6.C0040d;
import p007a6.C0042f;
import p195s6.AbstractC6269c;

/* renamed from: q6.d */
/* loaded from: classes.dex */
public final class C6166d extends AbstractC6269c {

    /* renamed from: d */
    public static final a f20799d = new a(null);

    /* renamed from: b */
    public final X509TrustManager f20800b;

    /* renamed from: c */
    public final X509TrustManagerExtensions f20801c;

    /* renamed from: q6.d$a */
    public static final class a {
        public a() {
        }

        public /* synthetic */ a(C0040d c0040d) {
            this();
        }

        /* renamed from: a */
        public final C6166d m23624a(X509TrustManager x509TrustManager) {
            X509TrustManagerExtensions x509TrustManagerExtensions;
            C0042f.m158e(x509TrustManager, "trustManager");
            try {
                x509TrustManagerExtensions = new X509TrustManagerExtensions(x509TrustManager);
            } catch (IllegalArgumentException unused) {
                x509TrustManagerExtensions = null;
            }
            if (x509TrustManagerExtensions != null) {
                return new C6166d(x509TrustManager, x509TrustManagerExtensions);
            }
            return null;
        }
    }

    public C6166d(X509TrustManager x509TrustManager, X509TrustManagerExtensions x509TrustManagerExtensions) {
        C0042f.m158e(x509TrustManager, "trustManager");
        C0042f.m158e(x509TrustManagerExtensions, "x509TrustManagerExtensions");
        this.f20800b = x509TrustManager;
        this.f20801c = x509TrustManagerExtensions;
    }

    @Override // p195s6.AbstractC6269c
    /* renamed from: a */
    public List<Certificate> mo23623a(List<? extends Certificate> list, String str) throws CertificateException, SSLPeerUnverifiedException {
        C0042f.m158e(list, "chain");
        C0042f.m158e(str, "hostname");
        try {
            List<X509Certificate> listCheckServerTrusted = this.f20801c.checkServerTrusted((X509Certificate[]) list.toArray(new X509Certificate[0]), "RSA", str);
            C0042f.m157d(listCheckServerTrusted, "x509TrustManagerExtensio…ficates, \"RSA\", hostname)");
            return listCheckServerTrusted;
        } catch (CertificateException e9) {
            SSLPeerUnverifiedException sSLPeerUnverifiedException = new SSLPeerUnverifiedException(e9.getMessage());
            sSLPeerUnverifiedException.initCause(e9);
            throw sSLPeerUnverifiedException;
        }
    }

    public boolean equals(Object obj) {
        return (obj instanceof C6166d) && ((C6166d) obj).f20800b == this.f20800b;
    }

    public int hashCode() {
        return System.identityHashCode(this.f20800b);
    }
}
