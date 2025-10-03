package p181r1;

import java.util.ArrayList;
import java.util.List;
import p243y0.InterfaceC6594g;

/* renamed from: r1.f */
/* loaded from: classes.dex */
public class C6188f {

    /* renamed from: a */
    public final List<a<?>> f20849a = new ArrayList();

    /* renamed from: r1.f$a */
    public static final class a<T> {

        /* renamed from: a */
        public final Class<T> f20850a;

        /* renamed from: b */
        public final InterfaceC6594g<T> f20851b;

        public a(Class<T> cls, InterfaceC6594g<T> interfaceC6594g) {
            this.f20850a = cls;
            this.f20851b = interfaceC6594g;
        }

        /* renamed from: a */
        public boolean m23668a(Class<?> cls) {
            return this.f20850a.isAssignableFrom(cls);
        }
    }

    /* renamed from: a */
    public synchronized <Z> void m23666a(Class<Z> cls, InterfaceC6594g<Z> interfaceC6594g) {
        this.f20849a.add(new a<>(cls, interfaceC6594g));
    }

    /* renamed from: b */
    public synchronized <Z> InterfaceC6594g<Z> m23667b(Class<Z> cls) {
        int size = this.f20849a.size();
        for (int i9 = 0; i9 < size; i9++) {
            a<?> aVar = this.f20849a.get(i9);
            if (aVar.m23668a(cls)) {
                return (InterfaceC6594g<Z>) aVar.f20851b;
            }
        }
        return null;
    }
}
