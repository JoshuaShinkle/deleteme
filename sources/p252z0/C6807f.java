package p252z0;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import p226w1.C6516i;
import p252z0.InterfaceC6806e;

/* renamed from: z0.f */
/* loaded from: classes.dex */
public class C6807f {

    /* renamed from: b */
    public static final InterfaceC6806e.a<?> f22549b = new a();

    /* renamed from: a */
    public final Map<Class<?>, InterfaceC6806e.a<?>> f22550a = new HashMap();

    /* renamed from: z0.f$a */
    public class a implements InterfaceC6806e.a<Object> {
        @Override // p252z0.InterfaceC6806e.a
        /* renamed from: a */
        public Class<Object> mo19966a() {
            throw new UnsupportedOperationException("Not implemented");
        }

        @Override // p252z0.InterfaceC6806e.a
        /* renamed from: b */
        public InterfaceC6806e<Object> mo19967b(Object obj) {
            return new b(obj);
        }
    }

    /* renamed from: z0.f$b */
    public static final class b implements InterfaceC6806e<Object> {

        /* renamed from: a */
        public final Object f22551a;

        public b(Object obj) {
            this.f22551a = obj;
        }

        @Override // p252z0.InterfaceC6806e
        /* renamed from: a */
        public Object mo19963a() {
            return this.f22551a;
        }

        @Override // p252z0.InterfaceC6806e
        /* renamed from: b */
        public void mo19964b() {
        }
    }

    /* renamed from: a */
    public synchronized <T> InterfaceC6806e<T> m25366a(T t8) {
        InterfaceC6806e.a<?> aVar;
        C6516i.m24938d(t8);
        aVar = this.f22550a.get(t8.getClass());
        if (aVar == null) {
            Iterator<InterfaceC6806e.a<?>> it = this.f22550a.values().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                InterfaceC6806e.a<?> next = it.next();
                if (next.mo19966a().isAssignableFrom(t8.getClass())) {
                    aVar = next;
                    break;
                }
            }
        }
        if (aVar == null) {
            aVar = f22549b;
        }
        return (InterfaceC6806e<T>) aVar.mo19967b(t8);
    }

    /* renamed from: b */
    public synchronized void m25367b(InterfaceC6806e.a<?> aVar) {
        this.f22550a.put(aVar.mo19966a(), aVar);
    }
}
