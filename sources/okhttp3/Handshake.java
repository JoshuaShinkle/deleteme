package okhttp3;

import java.io.IOException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import kotlin.C5216a;
import kotlin.collections.C5226i;
import kotlin.collections.C5227j;
import p007a6.C0040d;
import p007a6.C0042f;
import p098i6.C5057d;
import p203t5.InterfaceC6316d;
import p257z5.InterfaceC6831a;

/* loaded from: classes2.dex */
public final class Handshake {

    /* renamed from: e */
    public static final Companion f18465e = new Companion(null);

    /* renamed from: a */
    public final TlsVersion f18466a;

    /* renamed from: b */
    public final C5491h f18467b;

    /* renamed from: c */
    public final List<Certificate> f18468c;

    /* renamed from: d */
    public final InterfaceC6316d f18469d;

    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(C0040d c0040d) {
            this();
        }

        /* renamed from: a */
        public final Handshake m21207a(SSLSession sSLSession) throws IOException {
            final List<Certificate> listM20400f;
            C0042f.m158e(sSLSession, "<this>");
            String cipherSuite = sSLSession.getCipherSuite();
            if (cipherSuite == null) {
                throw new IllegalStateException("cipherSuite == null".toString());
            }
            if (C0042f.m154a(cipherSuite, "TLS_NULL_WITH_NULL_NULL") ? true : C0042f.m154a(cipherSuite, "SSL_NULL_WITH_NULL_NULL")) {
                throw new IOException("cipherSuite == " + cipherSuite);
            }
            C5491h c5491hM21265b = C5491h.f18587b.m21265b(cipherSuite);
            String protocol = sSLSession.getProtocol();
            if (protocol == null) {
                throw new IllegalStateException("tlsVersion == null".toString());
            }
            if (C0042f.m154a("NONE", protocol)) {
                throw new IOException("tlsVersion == NONE");
            }
            TlsVersion tlsVersionM21216a = TlsVersion.f18478b.m21216a(protocol);
            try {
                listM20400f = m21208b(sSLSession.getPeerCertificates());
            } catch (SSLPeerUnverifiedException unused) {
                listM20400f = C5226i.m20400f();
            }
            return new Handshake(tlsVersionM21216a, c5491hM21265b, m21208b(sSLSession.getLocalCertificates()), new InterfaceC6831a<List<? extends Certificate>>() { // from class: okhttp3.Handshake$Companion$handshake$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(0);
                }

                @Override // p257z5.InterfaceC6831a
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public final List<Certificate> mo21200a() {
                    return listM20400f;
                }
            });
        }

        /* renamed from: b */
        public final List<Certificate> m21208b(Certificate[] certificateArr) {
            return certificateArr != null ? C5057d.m19809w(Arrays.copyOf(certificateArr, certificateArr.length)) : C5226i.m20400f();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Handshake(TlsVersion tlsVersion, C5491h c5491h, List<? extends Certificate> list, final InterfaceC6831a<? extends List<? extends Certificate>> interfaceC6831a) {
        C0042f.m158e(tlsVersion, "tlsVersion");
        C0042f.m158e(c5491h, "cipherSuite");
        C0042f.m158e(list, "localCertificates");
        C0042f.m158e(interfaceC6831a, "peerCertificatesFn");
        this.f18466a = tlsVersion;
        this.f18467b = c5491h;
        this.f18468c = list;
        this.f18469d = C5216a.m20351a(new InterfaceC6831a<List<? extends Certificate>>() { // from class: okhttp3.Handshake$peerCertificates$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(0);
            }

            @Override // p257z5.InterfaceC6831a
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public final List<Certificate> mo21200a() {
                try {
                    return interfaceC6831a.mo21200a();
                } catch (SSLPeerUnverifiedException unused) {
                    return C5226i.m20400f();
                }
            }
        });
    }

    /* renamed from: a */
    public final C5491h m21202a() {
        return this.f18467b;
    }

    /* renamed from: b */
    public final String m21203b(Certificate certificate) {
        if (certificate instanceof X509Certificate) {
            return ((X509Certificate) certificate).getSubjectDN().toString();
        }
        String type = certificate.getType();
        C0042f.m157d(type, "type");
        return type;
    }

    /* renamed from: c */
    public final List<Certificate> m21204c() {
        return this.f18468c;
    }

    /* renamed from: d */
    public final List<Certificate> m21205d() {
        return (List) this.f18469d.getValue();
    }

    /* renamed from: e */
    public final TlsVersion m21206e() {
        return this.f18466a;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Handshake) {
            Handshake handshake = (Handshake) obj;
            if (handshake.f18466a == this.f18466a && C0042f.m154a(handshake.f18467b, this.f18467b) && C0042f.m154a(handshake.m21205d(), m21205d()) && C0042f.m154a(handshake.f18468c, this.f18468c)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return ((((((527 + this.f18466a.hashCode()) * 31) + this.f18467b.hashCode()) * 31) + m21205d().hashCode()) * 31) + this.f18468c.hashCode();
    }

    public String toString() {
        List<Certificate> listM21205d = m21205d();
        ArrayList arrayList = new ArrayList(C5227j.m20408n(listM21205d, 10));
        Iterator<T> it = listM21205d.iterator();
        while (it.hasNext()) {
            arrayList.add(m21203b((Certificate) it.next()));
        }
        String string = arrayList.toString();
        StringBuilder sb = new StringBuilder();
        sb.append("Handshake{tlsVersion=");
        sb.append(this.f18466a);
        sb.append(" cipherSuite=");
        sb.append(this.f18467b);
        sb.append(" peerCertificates=");
        sb.append(string);
        sb.append(" localCertificates=");
        List<Certificate> list = this.f18468c;
        ArrayList arrayList2 = new ArrayList(C5227j.m20408n(list, 10));
        Iterator<T> it2 = list.iterator();
        while (it2.hasNext()) {
            arrayList2.add(m21203b((Certificate) it2.next()));
        }
        sb.append(arrayList2);
        sb.append('}');
        return sb.toString();
    }
}
