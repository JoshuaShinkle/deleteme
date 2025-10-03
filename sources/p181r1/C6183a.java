package p181r1;

import java.util.ArrayList;
import java.util.List;
import p243y0.InterfaceC6588a;

/* renamed from: r1.a */
/* loaded from: classes.dex */
public class C6183a {

    /* renamed from: a */
    public final List<a<?>> f20835a = new ArrayList();

    /* renamed from: r1.a$a */
    public static final class a<T> {

        /* renamed from: a */
        public final Class<T> f20836a;

        /* renamed from: b */
        public final InterfaceC6588a<T> f20837b;

        public a(Class<T> cls, InterfaceC6588a<T> interfaceC6588a) {
            this.f20836a = cls;
            this.f20837b = interfaceC6588a;
        }

        /* renamed from: a */
        public boolean m23651a(Class<?> cls) {
            return this.f20836a.isAssignableFrom(cls);
        }
    }

    /* renamed from: a */
    public synchronized <T> void m23649a(Class<T> cls, InterfaceC6588a<T> interfaceC6588a) {
        this.f20835a.add(new a<>(cls, interfaceC6588a));
    }

    /* renamed from: b */
    public synchronized <T> InterfaceC6588a<T> m23650b(Class<T> cls) {
        for (a<?> aVar : this.f20835a) {
            if (aVar.m23651a(cls)) {
                return (InterfaceC6588a<T>) aVar.f20837b;
            }
        }
        return null;
    }
}
