package p226w1;

/* renamed from: w1.h */
/* loaded from: classes.dex */
public class C6515h {

    /* renamed from: a */
    public Class<?> f21923a;

    /* renamed from: b */
    public Class<?> f21924b;

    /* renamed from: c */
    public Class<?> f21925c;

    public C6515h() {
    }

    /* renamed from: a */
    public void m24933a(Class<?> cls, Class<?> cls2) {
        m24934b(cls, cls2, null);
    }

    /* renamed from: b */
    public void m24934b(Class<?> cls, Class<?> cls2, Class<?> cls3) {
        this.f21923a = cls;
        this.f21924b = cls2;
        this.f21925c = cls3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        C6515h c6515h = (C6515h) obj;
        return this.f21923a.equals(c6515h.f21923a) && this.f21924b.equals(c6515h.f21924b) && C6517j.m24943d(this.f21925c, c6515h.f21925c);
    }

    public int hashCode() {
        int iHashCode = ((this.f21923a.hashCode() * 31) + this.f21924b.hashCode()) * 31;
        Class<?> cls = this.f21925c;
        return iHashCode + (cls != null ? cls.hashCode() : 0);
    }

    public String toString() {
        return "MultiClassKey{first=" + this.f21923a + ", second=" + this.f21924b + '}';
    }

    public C6515h(Class<?> cls, Class<?> cls2) {
        m24933a(cls, cls2);
    }

    public C6515h(Class<?> cls, Class<?> cls2, Class<?> cls3) {
        m24934b(cls, cls2, cls3);
    }
}
