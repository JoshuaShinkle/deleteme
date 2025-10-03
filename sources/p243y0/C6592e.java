package p243y0;

import java.security.MessageDigest;
import p132m.C5302a;
import p226w1.C6509b;

/* renamed from: y0.e */
/* loaded from: classes.dex */
public final class C6592e implements InterfaceC6589b {

    /* renamed from: b */
    public final C5302a<C6591d<?>, Object> f22146b = new C6509b();

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: f */
    public static <T> void m25208f(C6591d<T> c6591d, Object obj, MessageDigest messageDigest) {
        c6591d.m25207g(obj, messageDigest);
    }

    @Override // p243y0.InterfaceC6589b
    /* renamed from: a */
    public void mo3265a(MessageDigest messageDigest) {
        for (int i9 = 0; i9 < this.f22146b.size(); i9++) {
            m25208f(this.f22146b.m20751i(i9), this.f22146b.m20755m(i9), messageDigest);
        }
    }

    /* renamed from: c */
    public <T> T m25209c(C6591d<T> c6591d) {
        return this.f22146b.containsKey(c6591d) ? (T) this.f22146b.get(c6591d) : c6591d.m25205c();
    }

    /* renamed from: d */
    public void m25210d(C6592e c6592e) {
        this.f22146b.mo20752j(c6592e.f22146b);
    }

    /* renamed from: e */
    public <T> C6592e m25211e(C6591d<T> c6591d, T t8) {
        this.f22146b.put(c6591d, t8);
        return this;
    }

    @Override // p243y0.InterfaceC6589b
    public boolean equals(Object obj) {
        if (obj instanceof C6592e) {
            return this.f22146b.equals(((C6592e) obj).f22146b);
        }
        return false;
    }

    @Override // p243y0.InterfaceC6589b
    public int hashCode() {
        return this.f22146b.hashCode();
    }

    public String toString() {
        return "Options{values=" + this.f22146b + '}';
    }
}
