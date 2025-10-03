package okhttp3;

import java.net.Proxy;
import java.net.ProxySelector;
import java.util.List;
import java.util.Objects;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.C5518s;
import p007a6.C0042f;
import p098i6.C5057d;

/* renamed from: okhttp3.a */
/* loaded from: classes2.dex */
public final class C5482a {

    /* renamed from: a */
    public final InterfaceC5515p f18485a;

    /* renamed from: b */
    public final SocketFactory f18486b;

    /* renamed from: c */
    public final SSLSocketFactory f18487c;

    /* renamed from: d */
    public final HostnameVerifier f18488d;

    /* renamed from: e */
    public final CertificatePinner f18489e;

    /* renamed from: f */
    public final InterfaceC5484b f18490f;

    /* renamed from: g */
    public final Proxy f18491g;

    /* renamed from: h */
    public final ProxySelector f18492h;

    /* renamed from: i */
    public final C5518s f18493i;

    /* renamed from: j */
    public final List<Protocol> f18494j;

    /* renamed from: k */
    public final List<C5510k> f18495k;

    public C5482a(String str, int i9, InterfaceC5515p interfaceC5515p, SocketFactory socketFactory, SSLSocketFactory sSLSocketFactory, HostnameVerifier hostnameVerifier, CertificatePinner certificatePinner, InterfaceC5484b interfaceC5484b, Proxy proxy, List<? extends Protocol> list, List<C5510k> list2, ProxySelector proxySelector) {
        C0042f.m158e(str, "uriHost");
        C0042f.m158e(interfaceC5515p, "dns");
        C0042f.m158e(socketFactory, "socketFactory");
        C0042f.m158e(interfaceC5484b, "proxyAuthenticator");
        C0042f.m158e(list, "protocols");
        C0042f.m158e(list2, "connectionSpecs");
        C0042f.m158e(proxySelector, "proxySelector");
        this.f18485a = interfaceC5515p;
        this.f18486b = socketFactory;
        this.f18487c = sSLSocketFactory;
        this.f18488d = hostnameVerifier;
        this.f18489e = certificatePinner;
        this.f18490f = interfaceC5484b;
        this.f18491g = proxy;
        this.f18492h = proxySelector;
        this.f18493i = new C5518s.a().m21678o(sSLSocketFactory != null ? "https" : "http").m21668e(str).m21674k(i9).m21664a();
        this.f18494j = C5057d.m19779S(list);
        this.f18495k = C5057d.m19779S(list2);
    }

    /* renamed from: a */
    public final CertificatePinner m21217a() {
        return this.f18489e;
    }

    /* renamed from: b */
    public final List<C5510k> m21218b() {
        return this.f18495k;
    }

    /* renamed from: c */
    public final InterfaceC5515p m21219c() {
        return this.f18485a;
    }

    /* renamed from: d */
    public final boolean m21220d(C5482a c5482a) {
        C0042f.m158e(c5482a, "that");
        return C0042f.m154a(this.f18485a, c5482a.f18485a) && C0042f.m154a(this.f18490f, c5482a.f18490f) && C0042f.m154a(this.f18494j, c5482a.f18494j) && C0042f.m154a(this.f18495k, c5482a.f18495k) && C0042f.m154a(this.f18492h, c5482a.f18492h) && C0042f.m154a(this.f18491g, c5482a.f18491g) && C0042f.m154a(this.f18487c, c5482a.f18487c) && C0042f.m154a(this.f18488d, c5482a.f18488d) && C0042f.m154a(this.f18489e, c5482a.f18489e) && this.f18493i.m21657l() == c5482a.f18493i.m21657l();
    }

    /* renamed from: e */
    public final HostnameVerifier m21221e() {
        return this.f18488d;
    }

    public boolean equals(Object obj) {
        if (obj instanceof C5482a) {
            C5482a c5482a = (C5482a) obj;
            if (C0042f.m154a(this.f18493i, c5482a.f18493i) && m21220d(c5482a)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: f */
    public final List<Protocol> m21222f() {
        return this.f18494j;
    }

    /* renamed from: g */
    public final Proxy m21223g() {
        return this.f18491g;
    }

    /* renamed from: h */
    public final InterfaceC5484b m21224h() {
        return this.f18490f;
    }

    public int hashCode() {
        return ((((((((((((((((((527 + this.f18493i.hashCode()) * 31) + this.f18485a.hashCode()) * 31) + this.f18490f.hashCode()) * 31) + this.f18494j.hashCode()) * 31) + this.f18495k.hashCode()) * 31) + this.f18492h.hashCode()) * 31) + Objects.hashCode(this.f18491g)) * 31) + Objects.hashCode(this.f18487c)) * 31) + Objects.hashCode(this.f18488d)) * 31) + Objects.hashCode(this.f18489e);
    }

    /* renamed from: i */
    public final ProxySelector m21225i() {
        return this.f18492h;
    }

    /* renamed from: j */
    public final SocketFactory m21226j() {
        return this.f18486b;
    }

    /* renamed from: k */
    public final SSLSocketFactory m21227k() {
        return this.f18487c;
    }

    /* renamed from: l */
    public final C5518s m21228l() {
        return this.f18493i;
    }

    public String toString() {
        StringBuilder sb;
        Object obj;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Address{");
        sb2.append(this.f18493i.m21653h());
        sb2.append(':');
        sb2.append(this.f18493i.m21657l());
        sb2.append(", ");
        if (this.f18491g != null) {
            sb = new StringBuilder();
            sb.append("proxy=");
            obj = this.f18491g;
        } else {
            sb = new StringBuilder();
            sb.append("proxySelector=");
            obj = this.f18492h;
        }
        sb.append(obj);
        sb2.append(sb.toString());
        sb2.append('}');
        return sb2.toString();
    }
}
