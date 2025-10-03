package okhttp3;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.net.ssl.SSLPeerUnverifiedException;
import kotlin.collections.C5226i;
import kotlin.collections.C5227j;
import kotlin.collections.C5234q;
import kotlin.text.C5255l;
import kotlin.text.StringsKt__StringsKt;
import okio.ByteString;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.p159io.IOUtils;
import p007a6.C0040d;
import p007a6.C0042f;
import p007a6.C0046j;
import p098i6.C5054a;
import p195s6.AbstractC6269c;
import p257z5.InterfaceC6831a;

/* loaded from: classes2.dex */
public final class CertificatePinner {

    /* renamed from: c */
    public static final C5478b f18457c = new C5478b(null);

    /* renamed from: d */
    public static final CertificatePinner f18458d = new C5477a().m21193b();

    /* renamed from: a */
    public final Set<C5479c> f18459a;

    /* renamed from: b */
    public final AbstractC6269c f18460b;

    /* renamed from: okhttp3.CertificatePinner$a */
    public static final class C5477a {

        /* renamed from: a */
        public final List<C5479c> f18461a = new ArrayList();

        /* renamed from: a */
        public final C5477a m21192a(String str, String... strArr) {
            C0042f.m158e(str, "pattern");
            C0042f.m158e(strArr, "pins");
            for (String str2 : strArr) {
                this.f18461a.add(new C5479c(str, str2));
            }
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* renamed from: b */
        public final CertificatePinner m21193b() {
            return new CertificatePinner(C5234q.m20421J(this.f18461a), null, 2, 0 == true ? 1 : 0);
        }
    }

    /* renamed from: okhttp3.CertificatePinner$b */
    public static final class C5478b {
        public C5478b() {
        }

        public /* synthetic */ C5478b(C0040d c0040d) {
            this();
        }

        /* renamed from: a */
        public final String m21194a(Certificate certificate) {
            C0042f.m158e(certificate, "certificate");
            if (!(certificate instanceof X509Certificate)) {
                throw new IllegalArgumentException("Certificate pinning requires X509 certificates".toString());
            }
            return "sha256/" + m21196c((X509Certificate) certificate).mo21875a();
        }

        /* renamed from: b */
        public final ByteString m21195b(X509Certificate x509Certificate) {
            C0042f.m158e(x509Certificate, "<this>");
            ByteString.C5526a c5526a = ByteString.f19095d;
            byte[] encoded = x509Certificate.getPublicKey().getEncoded();
            C0042f.m157d(encoded, "publicKey.encoded");
            return ByteString.C5526a.m21897f(c5526a, encoded, 0, 0, 3, null).m21890p();
        }

        /* renamed from: c */
        public final ByteString m21196c(X509Certificate x509Certificate) {
            C0042f.m158e(x509Certificate, "<this>");
            ByteString.C5526a c5526a = ByteString.f19095d;
            byte[] encoded = x509Certificate.getPublicKey().getEncoded();
            C0042f.m157d(encoded, "publicKey.encoded");
            return ByteString.C5526a.m21897f(c5526a, encoded, 0, 0, 3, null).m21891q();
        }
    }

    /* renamed from: okhttp3.CertificatePinner$c */
    public static final class C5479c {

        /* renamed from: a */
        public final String f18462a;

        /* renamed from: b */
        public final String f18463b;

        /* renamed from: c */
        public final ByteString f18464c;

        public C5479c(String str, String str2) {
            C0042f.m158e(str, "pattern");
            C0042f.m158e(str2, "pin");
            if (!((C5255l.m20525x(str, "*.", false, 2, null) && StringsKt__StringsKt.m20462N(str, "*", 1, false, 4, null) == -1) || (C5255l.m20525x(str, "**.", false, 2, null) && StringsKt__StringsKt.m20462N(str, "*", 2, false, 4, null) == -1) || StringsKt__StringsKt.m20462N(str, "*", 0, false, 6, null) == -1)) {
                throw new IllegalArgumentException(("Unexpected pattern: " + str).toString());
            }
            String strM19759e = C5054a.m19759e(str);
            if (strM19759e == null) {
                throw new IllegalArgumentException("Invalid pattern: " + str);
            }
            this.f18462a = strM19759e;
            if (C5255l.m20525x(str2, "sha1/", false, 2, null)) {
                this.f18463b = "sha1";
                ByteString.C5526a c5526a = ByteString.f19095d;
                String strSubstring = str2.substring(5);
                C0042f.m157d(strSubstring, "this as java.lang.String).substring(startIndex)");
                ByteString byteStringM21898a = c5526a.m21898a(strSubstring);
                if (byteStringM21898a != null) {
                    this.f18464c = byteStringM21898a;
                    return;
                }
                throw new IllegalArgumentException("Invalid pin hash: " + str2);
            }
            if (!C5255l.m20525x(str2, "sha256/", false, 2, null)) {
                throw new IllegalArgumentException("pins must start with 'sha256/' or 'sha1/': " + str2);
            }
            this.f18463b = "sha256";
            ByteString.C5526a c5526a2 = ByteString.f19095d;
            String strSubstring2 = str2.substring(7);
            C0042f.m157d(strSubstring2, "this as java.lang.String).substring(startIndex)");
            ByteString byteStringM21898a2 = c5526a2.m21898a(strSubstring2);
            if (byteStringM21898a2 != null) {
                this.f18464c = byteStringM21898a2;
                return;
            }
            throw new IllegalArgumentException("Invalid pin hash: " + str2);
        }

        /* renamed from: a */
        public final ByteString m21197a() {
            return this.f18464c;
        }

        /* renamed from: b */
        public final String m21198b() {
            return this.f18463b;
        }

        /* renamed from: c */
        public final boolean m21199c(String str) {
            C0042f.m158e(str, "hostname");
            if (C5255l.m20525x(this.f18462a, "**.", false, 2, null)) {
                int length = this.f18462a.length() - 3;
                int length2 = str.length() - length;
                if (!C5255l.m20516o(str, str.length() - length, this.f18462a, 3, length, false, 16, null)) {
                    return false;
                }
                if (length2 != 0 && str.charAt(length2 - 1) != '.') {
                    return false;
                }
            } else {
                if (!C5255l.m20525x(this.f18462a, "*.", false, 2, null)) {
                    return C0042f.m154a(str, this.f18462a);
                }
                int length3 = this.f18462a.length() - 1;
                int length4 = str.length() - length3;
                if (!C5255l.m20516o(str, str.length() - length3, this.f18462a, 1, length3, false, 16, null) || StringsKt__StringsKt.m20466R(str, ClassUtils.PACKAGE_SEPARATOR_CHAR, length4 - 1, false, 4, null) != -1) {
                    return false;
                }
            }
            return true;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof C5479c)) {
                return false;
            }
            C5479c c5479c = (C5479c) obj;
            return C0042f.m154a(this.f18462a, c5479c.f18462a) && C0042f.m154a(this.f18463b, c5479c.f18463b) && C0042f.m154a(this.f18464c, c5479c.f18464c);
        }

        public int hashCode() {
            return (((this.f18462a.hashCode() * 31) + this.f18463b.hashCode()) * 31) + this.f18464c.hashCode();
        }

        public String toString() {
            return this.f18463b + IOUtils.DIR_SEPARATOR_UNIX + this.f18464c.mo21875a();
        }
    }

    public CertificatePinner(Set<C5479c> set, AbstractC6269c abstractC6269c) {
        C0042f.m158e(set, "pins");
        this.f18459a = set;
        this.f18460b = abstractC6269c;
    }

    /* renamed from: a */
    public final void m21187a(final String str, final List<? extends Certificate> list) {
        C0042f.m158e(str, "hostname");
        C0042f.m158e(list, "peerCertificates");
        m21188b(str, new InterfaceC6831a<List<? extends X509Certificate>>() { // from class: okhttp3.CertificatePinner$check$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(0);
            }

            @Override // p257z5.InterfaceC6831a
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public final List<X509Certificate> mo21200a() {
                List<Certificate> listMo23623a;
                AbstractC6269c abstractC6269cM21190d = this.this$0.m21190d();
                if (abstractC6269cM21190d == null || (listMo23623a = abstractC6269cM21190d.mo23623a(list, str)) == null) {
                    listMo23623a = list;
                }
                List<Certificate> list2 = listMo23623a;
                ArrayList arrayList = new ArrayList(C5227j.m20408n(list2, 10));
                for (Certificate certificate : list2) {
                    C0042f.m156c(certificate, "null cannot be cast to non-null type java.security.cert.X509Certificate");
                    arrayList.add((X509Certificate) certificate);
                }
                return arrayList;
            }
        });
    }

    /* renamed from: b */
    public final void m21188b(String str, InterfaceC6831a<? extends List<? extends X509Certificate>> interfaceC6831a) throws SSLPeerUnverifiedException {
        C0042f.m158e(str, "hostname");
        C0042f.m158e(interfaceC6831a, "cleanedPeerCertificatesFn");
        List<C5479c> listM21189c = m21189c(str);
        if (listM21189c.isEmpty()) {
            return;
        }
        List<? extends X509Certificate> listMo21200a = interfaceC6831a.mo21200a();
        for (X509Certificate x509Certificate : listMo21200a) {
            ByteString byteStringM21196c = null;
            ByteString byteStringM21195b = null;
            for (C5479c c5479c : listM21189c) {
                String strM21198b = c5479c.m21198b();
                if (C0042f.m154a(strM21198b, "sha256")) {
                    if (byteStringM21196c == null) {
                        byteStringM21196c = f18457c.m21196c(x509Certificate);
                    }
                    if (C0042f.m154a(c5479c.m21197a(), byteStringM21196c)) {
                        return;
                    }
                } else {
                    if (!C0042f.m154a(strM21198b, "sha1")) {
                        throw new AssertionError("unsupported hashAlgorithm: " + c5479c.m21198b());
                    }
                    if (byteStringM21195b == null) {
                        byteStringM21195b = f18457c.m21195b(x509Certificate);
                    }
                    if (C0042f.m154a(c5479c.m21197a(), byteStringM21195b)) {
                        return;
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Certificate pinning failure!");
        sb.append("\n  Peer certificate chain:");
        for (X509Certificate x509Certificate2 : listMo21200a) {
            sb.append("\n    ");
            sb.append(f18457c.m21194a(x509Certificate2));
            sb.append(": ");
            sb.append(x509Certificate2.getSubjectDN().getName());
        }
        sb.append("\n  Pinned certificates for ");
        sb.append(str);
        sb.append(":");
        for (C5479c c5479c2 : listM21189c) {
            sb.append("\n    ");
            sb.append(c5479c2);
        }
        String string = sb.toString();
        C0042f.m157d(string, "StringBuilder().apply(builderAction).toString()");
        throw new SSLPeerUnverifiedException(string);
    }

    /* renamed from: c */
    public final List<C5479c> m21189c(String str) {
        C0042f.m158e(str, "hostname");
        Set<C5479c> set = this.f18459a;
        List<C5479c> listM20400f = C5226i.m20400f();
        for (Object obj : set) {
            if (((C5479c) obj).m21199c(str)) {
                if (listM20400f.isEmpty()) {
                    listM20400f = new ArrayList<>();
                }
                C0042f.m156c(listM20400f, "null cannot be cast to non-null type kotlin.collections.MutableList<T of okhttp3.internal.Util.filterList>");
                C0046j.m171a(listM20400f).add(obj);
            }
        }
        return listM20400f;
    }

    /* renamed from: d */
    public final AbstractC6269c m21190d() {
        return this.f18460b;
    }

    /* renamed from: e */
    public final CertificatePinner m21191e(AbstractC6269c abstractC6269c) {
        C0042f.m158e(abstractC6269c, "certificateChainCleaner");
        return C0042f.m154a(this.f18460b, abstractC6269c) ? this : new CertificatePinner(this.f18459a, abstractC6269c);
    }

    public boolean equals(Object obj) {
        if (obj instanceof CertificatePinner) {
            CertificatePinner certificatePinner = (CertificatePinner) obj;
            if (C0042f.m154a(certificatePinner.f18459a, this.f18459a) && C0042f.m154a(certificatePinner.f18460b, this.f18460b)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int iHashCode = (1517 + this.f18459a.hashCode()) * 41;
        AbstractC6269c abstractC6269c = this.f18460b;
        return iHashCode + (abstractC6269c != null ? abstractC6269c.hashCode() : 0);
    }

    public /* synthetic */ CertificatePinner(Set set, AbstractC6269c abstractC6269c, int i9, C0040d c0040d) {
        this(set, (i9 & 2) != 0 ? null : abstractC6269c);
    }
}
