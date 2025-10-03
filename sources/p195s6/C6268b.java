package p195s6;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import javax.security.auth.x500.X500Principal;
import p007a6.C0042f;

/* renamed from: s6.b */
/* loaded from: classes.dex */
public final class C6268b implements InterfaceC6271e {

    /* renamed from: a */
    public final Map<X500Principal, Set<X509Certificate>> f21150a;

    public C6268b(X509Certificate... x509CertificateArr) {
        C0042f.m158e(x509CertificateArr, "caCerts");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (X509Certificate x509Certificate : x509CertificateArr) {
            X500Principal subjectX500Principal = x509Certificate.getSubjectX500Principal();
            C0042f.m157d(subjectX500Principal, "caCert.subjectX500Principal");
            Object linkedHashSet = linkedHashMap.get(subjectX500Principal);
            if (linkedHashSet == null) {
                linkedHashSet = new LinkedHashSet();
                linkedHashMap.put(subjectX500Principal, linkedHashSet);
            }
            ((Set) linkedHashSet).add(x509Certificate);
        }
        this.f21150a = linkedHashMap;
    }

    @Override // p195s6.InterfaceC6271e
    /* renamed from: a */
    public X509Certificate mo23415a(X509Certificate x509Certificate) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException, CertificateException, NoSuchProviderException {
        boolean z8;
        C0042f.m158e(x509Certificate, "cert");
        Set<X509Certificate> set = this.f21150a.get(x509Certificate.getIssuerX500Principal());
        Object obj = null;
        if (set == null) {
            return null;
        }
        Iterator<T> it = set.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            try {
                x509Certificate.verify(((X509Certificate) next).getPublicKey());
                z8 = true;
            } catch (Exception unused) {
                z8 = false;
            }
            if (z8) {
                obj = next;
                break;
            }
        }
        return (X509Certificate) obj;
    }

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof C6268b) && C0042f.m154a(((C6268b) obj).f21150a, this.f21150a));
    }

    public int hashCode() {
        return this.f21150a.hashCode();
    }
}
