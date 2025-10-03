package okhttp3;

import java.net.InetSocketAddress;
import java.net.Proxy;
import p007a6.C0042f;

/* renamed from: okhttp3.b0 */
/* loaded from: classes2.dex */
public final class C5485b0 {

    /* renamed from: a */
    public final C5482a f18504a;

    /* renamed from: b */
    public final Proxy f18505b;

    /* renamed from: c */
    public final InetSocketAddress f18506c;

    public C5485b0(C5482a c5482a, Proxy proxy, InetSocketAddress inetSocketAddress) {
        C0042f.m158e(c5482a, "address");
        C0042f.m158e(proxy, "proxy");
        C0042f.m158e(inetSocketAddress, "socketAddress");
        this.f18504a = c5482a;
        this.f18505b = proxy;
        this.f18506c = inetSocketAddress;
    }

    /* renamed from: a */
    public final C5482a m21235a() {
        return this.f18504a;
    }

    /* renamed from: b */
    public final Proxy m21236b() {
        return this.f18505b;
    }

    /* renamed from: c */
    public final boolean m21237c() {
        return this.f18504a.m21227k() != null && this.f18505b.type() == Proxy.Type.HTTP;
    }

    /* renamed from: d */
    public final InetSocketAddress m21238d() {
        return this.f18506c;
    }

    public boolean equals(Object obj) {
        if (obj instanceof C5485b0) {
            C5485b0 c5485b0 = (C5485b0) obj;
            if (C0042f.m154a(c5485b0.f18504a, this.f18504a) && C0042f.m154a(c5485b0.f18505b, this.f18505b) && C0042f.m154a(c5485b0.f18506c, this.f18506c)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return ((((527 + this.f18504a.hashCode()) * 31) + this.f18505b.hashCode()) * 31) + this.f18506c.hashCode();
    }

    public String toString() {
        return "Route{" + this.f18506c + '}';
    }
}
