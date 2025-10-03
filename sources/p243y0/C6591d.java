package p243y0;

import java.security.MessageDigest;
import p226w1.C6516i;

/* renamed from: y0.d */
/* loaded from: classes.dex */
public final class C6591d<T> {

    /* renamed from: e */
    public static final b<Object> f22141e = new a();

    /* renamed from: a */
    public final T f22142a;

    /* renamed from: b */
    public final b<T> f22143b;

    /* renamed from: c */
    public final String f22144c;

    /* renamed from: d */
    public volatile byte[] f22145d;

    /* renamed from: y0.d$a */
    public class a implements b<Object> {
        @Override // p243y0.C6591d.b
        /* renamed from: a */
        public void mo19916a(byte[] bArr, Object obj, MessageDigest messageDigest) {
        }
    }

    /* renamed from: y0.d$b */
    public interface b<T> {
        /* renamed from: a */
        void mo19916a(byte[] bArr, T t8, MessageDigest messageDigest);
    }

    public C6591d(String str, T t8, b<T> bVar) {
        this.f22144c = C6516i.m24936b(str);
        this.f22142a = t8;
        this.f22143b = (b) C6516i.m24938d(bVar);
    }

    /* renamed from: a */
    public static <T> C6591d<T> m25201a(String str, T t8, b<T> bVar) {
        return new C6591d<>(str, t8, bVar);
    }

    /* renamed from: b */
    public static <T> b<T> m25202b() {
        return (b<T>) f22141e;
    }

    /* renamed from: e */
    public static <T> C6591d<T> m25203e(String str) {
        return new C6591d<>(str, null, m25202b());
    }

    /* renamed from: f */
    public static <T> C6591d<T> m25204f(String str, T t8) {
        return new C6591d<>(str, t8, m25202b());
    }

    /* renamed from: c */
    public T m25205c() {
        return this.f22142a;
    }

    /* renamed from: d */
    public final byte[] m25206d() {
        if (this.f22145d == null) {
            this.f22145d = this.f22144c.getBytes(InterfaceC6589b.f22139a);
        }
        return this.f22145d;
    }

    public boolean equals(Object obj) {
        if (obj instanceof C6591d) {
            return this.f22144c.equals(((C6591d) obj).f22144c);
        }
        return false;
    }

    /* renamed from: g */
    public void m25207g(T t8, MessageDigest messageDigest) {
        this.f22143b.mo19916a(m25206d(), t8, messageDigest);
    }

    public int hashCode() {
        return this.f22144c.hashCode();
    }

    public String toString() {
        return "Option{key='" + this.f22144c + "'}";
    }
}
