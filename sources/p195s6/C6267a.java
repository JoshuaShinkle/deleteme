package p195s6;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import p007a6.C0040d;
import p007a6.C0042f;

/* renamed from: s6.a */
/* loaded from: classes.dex */
public final class C6267a extends AbstractC6269c {

    /* renamed from: c */
    public static final a f21148c = new a(null);

    /* renamed from: b */
    public final InterfaceC6271e f21149b;

    /* renamed from: s6.a$a */
    public static final class a {
        public a() {
        }

        public /* synthetic */ a(C0040d c0040d) {
            this();
        }
    }

    public C6267a(InterfaceC6271e interfaceC6271e) {
        C0042f.m158e(interfaceC6271e, "trustRootIndex");
        this.f21149b = interfaceC6271e;
    }

    @Override // p195s6.AbstractC6269c
    /* renamed from: a */
    public List<Certificate> mo23623a(List<? extends Certificate> list, String str) throws SSLPeerUnverifiedException {
        C0042f.m158e(list, "chain");
        C0042f.m158e(str, "hostname");
        ArrayDeque arrayDeque = new ArrayDeque(list);
        ArrayList arrayList = new ArrayList();
        Object objRemoveFirst = arrayDeque.removeFirst();
        C0042f.m157d(objRemoveFirst, "queue.removeFirst()");
        arrayList.add(objRemoveFirst);
        boolean z8 = false;
        for (int i9 = 0; i9 < 9; i9++) {
            Object obj = arrayList.get(arrayList.size() - 1);
            C0042f.m156c(obj, "null cannot be cast to non-null type java.security.cert.X509Certificate");
            X509Certificate x509Certificate = (X509Certificate) obj;
            X509Certificate x509CertificateMo23415a = this.f21149b.mo23415a(x509Certificate);
            if (x509CertificateMo23415a == null) {
                Iterator it = arrayDeque.iterator();
                C0042f.m157d(it, "queue.iterator()");
                while (it.hasNext()) {
                    Object next = it.next();
                    C0042f.m156c(next, "null cannot be cast to non-null type java.security.cert.X509Certificate");
                    X509Certificate x509Certificate2 = (X509Certificate) next;
                    if (m24010b(x509Certificate, x509Certificate2)) {
                        it.remove();
                        arrayList.add(x509Certificate2);
                    }
                }
                if (z8) {
                    return arrayList;
                }
                throw new SSLPeerUnverifiedException("Failed to find a trusted cert that signed " + x509Certificate);
            }
            if (arrayList.size() > 1 || !C0042f.m154a(x509Certificate, x509CertificateMo23415a)) {
                arrayList.add(x509CertificateMo23415a);
            }
            if (m24010b(x509CertificateMo23415a, x509CertificateMo23415a)) {
                return arrayList;
            }
            z8 = true;
        }
        throw new SSLPeerUnverifiedException("Certificate chain too long: " + arrayList);
    }

    /* renamed from: b */
    public final boolean m24010b(X509Certificate x509Certificate, X509Certificate x509Certificate2) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException, CertificateException, NoSuchProviderException {
        if (!C0042f.m154a(x509Certificate.getIssuerDN(), x509Certificate2.getSubjectDN())) {
            return false;
        }
        try {
            x509Certificate.verify(x509Certificate2.getPublicKey());
            return true;
        } catch (GeneralSecurityException unused) {
            return false;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof C6267a) && C0042f.m154a(((C6267a) obj).f21149b, this.f21149b);
    }

    public int hashCode() {
        return this.f21149b.hashCode();
    }
}
