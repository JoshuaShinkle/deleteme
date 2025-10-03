package okhttp3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.net.ssl.SSLSocket;
import kotlin.collections.C5234q;
import p007a6.C0040d;
import p007a6.C0042f;
import p098i6.C5057d;
import p212u5.C6420a;

/* renamed from: okhttp3.k */
/* loaded from: classes2.dex */
public final class C5510k {

    /* renamed from: e */
    public static final b f18893e = new b(null);

    /* renamed from: f */
    public static final C5491h[] f18894f;

    /* renamed from: g */
    public static final C5491h[] f18895g;

    /* renamed from: h */
    public static final C5510k f18896h;

    /* renamed from: i */
    public static final C5510k f18897i;

    /* renamed from: j */
    public static final C5510k f18898j;

    /* renamed from: k */
    public static final C5510k f18899k;

    /* renamed from: a */
    public final boolean f18900a;

    /* renamed from: b */
    public final boolean f18901b;

    /* renamed from: c */
    public final String[] f18902c;

    /* renamed from: d */
    public final String[] f18903d;

    /* renamed from: okhttp3.k$b */
    public static final class b {
        public b() {
        }

        public /* synthetic */ b(C0040d c0040d) {
            this();
        }
    }

    static {
        C5491h c5491h = C5491h.f18628o1;
        C5491h c5491h2 = C5491h.f18631p1;
        C5491h c5491h3 = C5491h.f18634q1;
        C5491h c5491h4 = C5491h.f18586a1;
        C5491h c5491h5 = C5491h.f18598e1;
        C5491h c5491h6 = C5491h.f18589b1;
        C5491h c5491h7 = C5491h.f18601f1;
        C5491h c5491h8 = C5491h.f18619l1;
        C5491h c5491h9 = C5491h.f18616k1;
        C5491h[] c5491hArr = {c5491h, c5491h2, c5491h3, c5491h4, c5491h5, c5491h6, c5491h7, c5491h8, c5491h9};
        f18894f = c5491hArr;
        C5491h[] c5491hArr2 = {c5491h, c5491h2, c5491h3, c5491h4, c5491h5, c5491h6, c5491h7, c5491h8, c5491h9, C5491h.f18556L0, C5491h.f18558M0, C5491h.f18612j0, C5491h.f18615k0, C5491h.f18547H, C5491h.f18555L, C5491h.f18617l};
        f18895g = c5491hArr2;
        a aVarM21566c = new a(true).m21566c((C5491h[]) Arrays.copyOf(c5491hArr, c5491hArr.length));
        TlsVersion tlsVersion = TlsVersion.TLS_1_3;
        TlsVersion tlsVersion2 = TlsVersion.TLS_1_2;
        f18896h = aVarM21566c.m21569f(tlsVersion, tlsVersion2).m21567d(true).m21564a();
        f18897i = new a(true).m21566c((C5491h[]) Arrays.copyOf(c5491hArr2, c5491hArr2.length)).m21569f(tlsVersion, tlsVersion2).m21567d(true).m21564a();
        f18898j = new a(true).m21566c((C5491h[]) Arrays.copyOf(c5491hArr2, c5491hArr2.length)).m21569f(tlsVersion, tlsVersion2, TlsVersion.TLS_1_1, TlsVersion.TLS_1_0).m21567d(true).m21564a();
        f18899k = new a(false).m21564a();
    }

    public C5510k(boolean z8, boolean z9, String[] strArr, String[] strArr2) {
        this.f18900a = z8;
        this.f18901b = z9;
        this.f18902c = strArr;
        this.f18903d = strArr2;
    }

    /* renamed from: c */
    public final void m21557c(SSLSocket sSLSocket, boolean z8) {
        C0042f.m158e(sSLSocket, "sslSocket");
        C5510k c5510kM21561g = m21561g(sSLSocket, z8);
        if (c5510kM21561g.m21563i() != null) {
            sSLSocket.setEnabledProtocols(c5510kM21561g.f18903d);
        }
        if (c5510kM21561g.m21558d() != null) {
            sSLSocket.setEnabledCipherSuites(c5510kM21561g.f18902c);
        }
    }

    /* renamed from: d */
    public final List<C5491h> m21558d() {
        String[] strArr = this.f18902c;
        if (strArr == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            arrayList.add(C5491h.f18587b.m21265b(str));
        }
        return C5234q.m20418G(arrayList);
    }

    /* renamed from: e */
    public final boolean m21559e(SSLSocket sSLSocket) {
        C0042f.m158e(sSLSocket, "socket");
        if (!this.f18900a) {
            return false;
        }
        String[] strArr = this.f18903d;
        if (strArr != null && !C5057d.m19807u(strArr, sSLSocket.getEnabledProtocols(), C6420a.m24572b())) {
            return false;
        }
        String[] strArr2 = this.f18902c;
        return strArr2 == null || C5057d.m19807u(strArr2, sSLSocket.getEnabledCipherSuites(), C5491h.f18587b.m21266c());
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof C5510k)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        boolean z8 = this.f18900a;
        C5510k c5510k = (C5510k) obj;
        if (z8 != c5510k.f18900a) {
            return false;
        }
        return !z8 || (Arrays.equals(this.f18902c, c5510k.f18902c) && Arrays.equals(this.f18903d, c5510k.f18903d) && this.f18901b == c5510k.f18901b);
    }

    /* renamed from: f */
    public final boolean m21560f() {
        return this.f18900a;
    }

    /* renamed from: g */
    public final C5510k m21561g(SSLSocket sSLSocket, boolean z8) {
        String[] enabledCipherSuites;
        String[] enabledProtocols;
        if (this.f18902c != null) {
            String[] enabledCipherSuites2 = sSLSocket.getEnabledCipherSuites();
            C0042f.m157d(enabledCipherSuites2, "sslSocket.enabledCipherSuites");
            enabledCipherSuites = C5057d.m19765E(enabledCipherSuites2, this.f18902c, C5491h.f18587b.m21266c());
        } else {
            enabledCipherSuites = sSLSocket.getEnabledCipherSuites();
        }
        if (this.f18903d != null) {
            String[] enabledProtocols2 = sSLSocket.getEnabledProtocols();
            C0042f.m157d(enabledProtocols2, "sslSocket.enabledProtocols");
            enabledProtocols = C5057d.m19765E(enabledProtocols2, this.f18903d, C6420a.m24572b());
        } else {
            enabledProtocols = sSLSocket.getEnabledProtocols();
        }
        String[] supportedCipherSuites = sSLSocket.getSupportedCipherSuites();
        C0042f.m157d(supportedCipherSuites, "supportedCipherSuites");
        int iM19810x = C5057d.m19810x(supportedCipherSuites, "TLS_FALLBACK_SCSV", C5491h.f18587b.m21266c());
        if (z8 && iM19810x != -1) {
            C0042f.m157d(enabledCipherSuites, "cipherSuitesIntersection");
            String str = supportedCipherSuites[iM19810x];
            C0042f.m157d(str, "supportedCipherSuites[indexOfFallbackScsv]");
            enabledCipherSuites = C5057d.m19801o(enabledCipherSuites, str);
        }
        a aVar = new a(this);
        C0042f.m157d(enabledCipherSuites, "cipherSuitesIntersection");
        a aVarM21565b = aVar.m21565b((String[]) Arrays.copyOf(enabledCipherSuites, enabledCipherSuites.length));
        C0042f.m157d(enabledProtocols, "tlsVersionsIntersection");
        return aVarM21565b.m21568e((String[]) Arrays.copyOf(enabledProtocols, enabledProtocols.length)).m21564a();
    }

    /* renamed from: h */
    public final boolean m21562h() {
        return this.f18901b;
    }

    public int hashCode() {
        if (!this.f18900a) {
            return 17;
        }
        String[] strArr = this.f18902c;
        int iHashCode = (527 + (strArr != null ? Arrays.hashCode(strArr) : 0)) * 31;
        String[] strArr2 = this.f18903d;
        return ((iHashCode + (strArr2 != null ? Arrays.hashCode(strArr2) : 0)) * 31) + (!this.f18901b ? 1 : 0);
    }

    /* renamed from: i */
    public final List<TlsVersion> m21563i() {
        String[] strArr = this.f18903d;
        if (strArr == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            arrayList.add(TlsVersion.f18478b.m21216a(str));
        }
        return C5234q.m20418G(arrayList);
    }

    public String toString() {
        if (!this.f18900a) {
            return "ConnectionSpec()";
        }
        return "ConnectionSpec(cipherSuites=" + Objects.toString(m21558d(), "[all enabled]") + ", tlsVersions=" + Objects.toString(m21563i(), "[all enabled]") + ", supportsTlsExtensions=" + this.f18901b + ')';
    }

    /* renamed from: okhttp3.k$a */
    public static final class a {

        /* renamed from: a */
        public boolean f18904a;

        /* renamed from: b */
        public String[] f18905b;

        /* renamed from: c */
        public String[] f18906c;

        /* renamed from: d */
        public boolean f18907d;

        public a(boolean z8) {
            this.f18904a = z8;
        }

        /* renamed from: a */
        public final C5510k m21564a() {
            return new C5510k(this.f18904a, this.f18907d, this.f18905b, this.f18906c);
        }

        /* renamed from: b */
        public final a m21565b(String... strArr) {
            C0042f.m158e(strArr, "cipherSuites");
            if (!this.f18904a) {
                throw new IllegalArgumentException("no cipher suites for cleartext connections".toString());
            }
            if (!(!(strArr.length == 0))) {
                throw new IllegalArgumentException("At least one cipher suite is required".toString());
            }
            this.f18905b = (String[]) strArr.clone();
            return this;
        }

        /* renamed from: c */
        public final a m21566c(C5491h... c5491hArr) {
            C0042f.m158e(c5491hArr, "cipherSuites");
            if (!this.f18904a) {
                throw new IllegalArgumentException("no cipher suites for cleartext connections".toString());
            }
            ArrayList arrayList = new ArrayList(c5491hArr.length);
            for (C5491h c5491h : c5491hArr) {
                arrayList.add(c5491h.m21262c());
            }
            String[] strArr = (String[]) arrayList.toArray(new String[0]);
            return m21565b((String[]) Arrays.copyOf(strArr, strArr.length));
        }

        /* renamed from: d */
        public final a m21567d(boolean z8) {
            if (!this.f18904a) {
                throw new IllegalArgumentException("no TLS extensions for cleartext connections".toString());
            }
            this.f18907d = z8;
            return this;
        }

        /* renamed from: e */
        public final a m21568e(String... strArr) {
            C0042f.m158e(strArr, "tlsVersions");
            if (!this.f18904a) {
                throw new IllegalArgumentException("no TLS versions for cleartext connections".toString());
            }
            if (!(!(strArr.length == 0))) {
                throw new IllegalArgumentException("At least one TLS version is required".toString());
            }
            this.f18906c = (String[]) strArr.clone();
            return this;
        }

        /* renamed from: f */
        public final a m21569f(TlsVersion... tlsVersionArr) {
            C0042f.m158e(tlsVersionArr, "tlsVersions");
            if (!this.f18904a) {
                throw new IllegalArgumentException("no TLS versions for cleartext connections".toString());
            }
            ArrayList arrayList = new ArrayList(tlsVersionArr.length);
            for (TlsVersion tlsVersion : tlsVersionArr) {
                arrayList.add(tlsVersion.m21215b());
            }
            String[] strArr = (String[]) arrayList.toArray(new String[0]);
            return m21568e((String[]) Arrays.copyOf(strArr, strArr.length));
        }

        public a(C5510k c5510k) {
            C0042f.m158e(c5510k, "connectionSpec");
            this.f18904a = c5510k.m21560f();
            this.f18905b = c5510k.f18902c;
            this.f18906c = c5510k.f18903d;
            this.f18907d = c5510k.m21562h();
        }
    }
}
