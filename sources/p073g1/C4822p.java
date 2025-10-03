package p073g1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import p021c0.InterfaceC0699e;

/* renamed from: g1.p */
/* loaded from: classes.dex */
public class C4822p {

    /* renamed from: a */
    public final C4824r f16779a;

    /* renamed from: b */
    public final a f16780b;

    /* renamed from: g1.p$a */
    public static class a {

        /* renamed from: a */
        public final Map<Class<?>, C6870a<?>> f16781a = new HashMap();

        /* renamed from: g1.p$a$a, reason: collision with other inner class name */
        public static class C6870a<Model> {

            /* renamed from: a */
            public final List<InterfaceC4820n<Model, ?>> f16782a;

            public C6870a(List<InterfaceC4820n<Model, ?>> list) {
                this.f16782a = list;
            }
        }

        /* renamed from: a */
        public void m19134a() {
            this.f16781a.clear();
        }

        /* renamed from: b */
        public <Model> List<InterfaceC4820n<Model, ?>> m19135b(Class<Model> cls) {
            C6870a<?> c6870a = this.f16781a.get(cls);
            if (c6870a == null) {
                return null;
            }
            return (List<InterfaceC4820n<Model, ?>>) c6870a.f16782a;
        }

        /* renamed from: c */
        public <Model> void m19136c(Class<Model> cls, List<InterfaceC4820n<Model, ?>> list) {
            if (this.f16781a.put(cls, new C6870a<>(list)) == null) {
                return;
            }
            throw new IllegalStateException("Already cached loaders for model: " + cls);
        }
    }

    public C4822p(InterfaceC0699e<List<Throwable>> interfaceC0699e) {
        this(new C4824r(interfaceC0699e));
    }

    /* renamed from: b */
    public static <A> Class<A> m19126b(A a9) {
        return (Class<A>) a9.getClass();
    }

    /* renamed from: a */
    public synchronized <Model, Data> void m19127a(Class<Model> cls, Class<Data> cls2, InterfaceC4821o<? extends Model, ? extends Data> interfaceC4821o) {
        this.f16779a.m19140b(cls, cls2, interfaceC4821o);
        this.f16780b.m19134a();
    }

    /* renamed from: c */
    public synchronized List<Class<?>> m19128c(Class<?> cls) {
        return this.f16779a.m19144g(cls);
    }

    /* renamed from: d */
    public <A> List<InterfaceC4820n<A, ?>> m19129d(A a9) {
        List<InterfaceC4820n<A, ?>> listM19130e = m19130e(m19126b(a9));
        int size = listM19130e.size();
        List<InterfaceC4820n<A, ?>> listEmptyList = Collections.emptyList();
        boolean z8 = true;
        for (int i9 = 0; i9 < size; i9++) {
            InterfaceC4820n<A, ?> interfaceC4820n = listM19130e.get(i9);
            if (interfaceC4820n.mo3826a(a9)) {
                if (z8) {
                    listEmptyList = new ArrayList<>(size - i9);
                    z8 = false;
                }
                listEmptyList.add(interfaceC4820n);
            }
        }
        return listEmptyList;
    }

    /* renamed from: e */
    public final synchronized <A> List<InterfaceC4820n<A, ?>> m19130e(Class<A> cls) {
        List<InterfaceC4820n<A, ?>> listM19135b;
        listM19135b = this.f16780b.m19135b(cls);
        if (listM19135b == null) {
            listM19135b = Collections.unmodifiableList(this.f16779a.m19143e(cls));
            this.f16780b.m19136c(cls, listM19135b);
        }
        return listM19135b;
    }

    /* renamed from: f */
    public synchronized <Model, Data> void m19131f(Class<Model> cls, Class<Data> cls2, InterfaceC4821o<? extends Model, ? extends Data> interfaceC4821o) {
        this.f16779a.m19146i(cls, cls2, interfaceC4821o);
        this.f16780b.m19134a();
    }

    /* renamed from: g */
    public synchronized <Model, Data> void m19132g(Class<Model> cls, Class<Data> cls2, InterfaceC4821o<? extends Model, ? extends Data> interfaceC4821o) {
        m19133h(this.f16779a.m19148k(cls, cls2, interfaceC4821o));
        this.f16780b.m19134a();
    }

    /* renamed from: h */
    public final <Model, Data> void m19133h(List<InterfaceC4821o<? extends Model, ? extends Data>> list) {
        Iterator<InterfaceC4821o<? extends Model, ? extends Data>> it = list.iterator();
        while (it.hasNext()) {
            it.next().mo3832b();
        }
    }

    public C4822p(C4824r c4824r) {
        this.f16780b = new a();
        this.f16779a = c4824r;
    }
}
