package androidx.lifecycle;

/* renamed from: androidx.lifecycle.p */
/* loaded from: classes.dex */
public class C0395p {

    /* renamed from: a */
    public final a f2233a;

    /* renamed from: b */
    public final C0396q f2234b;

    /* renamed from: androidx.lifecycle.p$a */
    public interface a {
        /* renamed from: a */
        <T extends AbstractC0394o> T mo1979a(Class<T> cls);
    }

    /* renamed from: androidx.lifecycle.p$b */
    public static abstract class b implements a {
        @Override // androidx.lifecycle.C0395p.a
        /* renamed from: a */
        public <T extends AbstractC0394o> T mo1979a(Class<T> cls) {
            throw new UnsupportedOperationException("create(String, Class<?>) must be called on implementaions of KeyedFactory");
        }

        /* renamed from: b */
        public abstract <T extends AbstractC0394o> T m2115b(String str, Class<T> cls);
    }

    public C0395p(C0396q c0396q, a aVar) {
        this.f2233a = aVar;
        this.f2234b = c0396q;
    }

    /* renamed from: a */
    public <T extends AbstractC0394o> T m2113a(Class<T> cls) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName == null) {
            throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
        }
        return (T) m2114b("androidx.lifecycle.ViewModelProvider.DefaultKey:" + canonicalName, cls);
    }

    /* renamed from: b */
    public <T extends AbstractC0394o> T m2114b(String str, Class<T> cls) {
        T t8 = (T) this.f2234b.m2117b(str);
        if (cls.isInstance(t8)) {
            return t8;
        }
        a aVar = this.f2233a;
        T t9 = aVar instanceof b ? (T) ((b) aVar).m2115b(str, cls) : (T) aVar.mo1979a(cls);
        this.f2234b.m2118c(str, t9);
        return t9;
    }
}
