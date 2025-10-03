package p012b1;

import java.security.MessageDigest;
import java.util.Map;
import p226w1.C6516i;
import p243y0.C6592e;
import p243y0.InterfaceC6589b;
import p243y0.InterfaceC6595h;

/* renamed from: b1.e */
/* loaded from: classes.dex */
public class C0590e implements InterfaceC6589b {

    /* renamed from: b */
    public final Object f3112b;

    /* renamed from: c */
    public final int f3113c;

    /* renamed from: d */
    public final int f3114d;

    /* renamed from: e */
    public final Class<?> f3115e;

    /* renamed from: f */
    public final Class<?> f3116f;

    /* renamed from: g */
    public final InterfaceC6589b f3117g;

    /* renamed from: h */
    public final Map<Class<?>, InterfaceC6595h<?>> f3118h;

    /* renamed from: i */
    public final C6592e f3119i;

    /* renamed from: j */
    public int f3120j;

    public C0590e(Object obj, InterfaceC6589b interfaceC6589b, int i9, int i10, Map<Class<?>, InterfaceC6595h<?>> map, Class<?> cls, Class<?> cls2, C6592e c6592e) {
        this.f3112b = C6516i.m24938d(obj);
        this.f3117g = (InterfaceC6589b) C6516i.m24939e(interfaceC6589b, "Signature must not be null");
        this.f3113c = i9;
        this.f3114d = i10;
        this.f3118h = (Map) C6516i.m24938d(map);
        this.f3115e = (Class) C6516i.m24939e(cls, "Resource class must not be null");
        this.f3116f = (Class) C6516i.m24939e(cls2, "Transcode class must not be null");
        this.f3119i = (C6592e) C6516i.m24938d(c6592e);
    }

    @Override // p243y0.InterfaceC6589b
    /* renamed from: a */
    public void mo3265a(MessageDigest messageDigest) {
        throw new UnsupportedOperationException();
    }

    @Override // p243y0.InterfaceC6589b
    public boolean equals(Object obj) {
        if (!(obj instanceof C0590e)) {
            return false;
        }
        C0590e c0590e = (C0590e) obj;
        return this.f3112b.equals(c0590e.f3112b) && this.f3117g.equals(c0590e.f3117g) && this.f3114d == c0590e.f3114d && this.f3113c == c0590e.f3113c && this.f3118h.equals(c0590e.f3118h) && this.f3115e.equals(c0590e.f3115e) && this.f3116f.equals(c0590e.f3116f) && this.f3119i.equals(c0590e.f3119i);
    }

    @Override // p243y0.InterfaceC6589b
    public int hashCode() {
        if (this.f3120j == 0) {
            int iHashCode = this.f3112b.hashCode();
            this.f3120j = iHashCode;
            int iHashCode2 = (((((iHashCode * 31) + this.f3117g.hashCode()) * 31) + this.f3113c) * 31) + this.f3114d;
            this.f3120j = iHashCode2;
            int iHashCode3 = (iHashCode2 * 31) + this.f3118h.hashCode();
            this.f3120j = iHashCode3;
            int iHashCode4 = (iHashCode3 * 31) + this.f3115e.hashCode();
            this.f3120j = iHashCode4;
            int iHashCode5 = (iHashCode4 * 31) + this.f3116f.hashCode();
            this.f3120j = iHashCode5;
            this.f3120j = (iHashCode5 * 31) + this.f3119i.hashCode();
        }
        return this.f3120j;
    }

    public String toString() {
        return "EngineKey{model=" + this.f3112b + ", width=" + this.f3113c + ", height=" + this.f3114d + ", resourceClass=" + this.f3115e + ", transcodeClass=" + this.f3116f + ", signature=" + this.f3117g + ", hashCode=" + this.f3120j + ", transformations=" + this.f3118h + ", options=" + this.f3119i + '}';
    }
}
