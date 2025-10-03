package p073g1;

import com.bumptech.glide.Registry;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import p021c0.InterfaceC0699e;
import p073g1.InterfaceC4820n;
import p226w1.C6516i;
import p243y0.C6592e;

/* renamed from: g1.r */
/* loaded from: classes.dex */
public class C4824r {

    /* renamed from: e */
    public static final c f16791e = new c();

    /* renamed from: f */
    public static final InterfaceC4820n<Object, Object> f16792f = new a();

    /* renamed from: a */
    public final List<b<?, ?>> f16793a;

    /* renamed from: b */
    public final c f16794b;

    /* renamed from: c */
    public final Set<b<?, ?>> f16795c;

    /* renamed from: d */
    public final InterfaceC0699e<List<Throwable>> f16796d;

    /* renamed from: g1.r$a */
    public static class a implements InterfaceC4820n<Object, Object> {
        @Override // p073g1.InterfaceC4820n
        /* renamed from: a */
        public boolean mo3826a(Object obj) {
            return false;
        }

        @Override // p073g1.InterfaceC4820n
        /* renamed from: b */
        public InterfaceC4820n.a<Object> mo3827b(Object obj, int i9, int i10, C6592e c6592e) {
            return null;
        }
    }

    /* renamed from: g1.r$b */
    public static class b<Model, Data> {

        /* renamed from: a */
        public final Class<Model> f16797a;

        /* renamed from: b */
        public final Class<Data> f16798b;

        /* renamed from: c */
        public final InterfaceC4821o<? extends Model, ? extends Data> f16799c;

        public b(Class<Model> cls, Class<Data> cls2, InterfaceC4821o<? extends Model, ? extends Data> interfaceC4821o) {
            this.f16797a = cls;
            this.f16798b = cls2;
            this.f16799c = interfaceC4821o;
        }

        /* renamed from: a */
        public boolean m19149a(Class<?> cls) {
            return this.f16797a.isAssignableFrom(cls);
        }

        /* renamed from: b */
        public boolean m19150b(Class<?> cls, Class<?> cls2) {
            return m19149a(cls) && this.f16798b.isAssignableFrom(cls2);
        }
    }

    /* renamed from: g1.r$c */
    public static class c {
        /* renamed from: a */
        public <Model, Data> C4823q<Model, Data> m19151a(List<InterfaceC4820n<Model, Data>> list, InterfaceC0699e<List<Throwable>> interfaceC0699e) {
            return new C4823q<>(list, interfaceC0699e);
        }
    }

    public C4824r(InterfaceC0699e<List<Throwable>> interfaceC0699e) {
        this(interfaceC0699e, f16791e);
    }

    /* renamed from: f */
    public static <Model, Data> InterfaceC4820n<Model, Data> m19138f() {
        return (InterfaceC4820n<Model, Data>) f16792f;
    }

    /* renamed from: a */
    public final <Model, Data> void m19139a(Class<Model> cls, Class<Data> cls2, InterfaceC4821o<? extends Model, ? extends Data> interfaceC4821o, boolean z8) {
        b<?, ?> bVar = new b<>(cls, cls2, interfaceC4821o);
        List<b<?, ?>> list = this.f16793a;
        list.add(z8 ? list.size() : 0, bVar);
    }

    /* renamed from: b */
    public synchronized <Model, Data> void m19140b(Class<Model> cls, Class<Data> cls2, InterfaceC4821o<? extends Model, ? extends Data> interfaceC4821o) {
        m19139a(cls, cls2, interfaceC4821o, true);
    }

    /* renamed from: c */
    public final <Model, Data> InterfaceC4820n<Model, Data> m19141c(b<?, ?> bVar) {
        return (InterfaceC4820n) C6516i.m24938d(bVar.f16799c.mo3831a(this));
    }

    /* renamed from: d */
    public synchronized <Model, Data> InterfaceC4820n<Model, Data> m19142d(Class<Model> cls, Class<Data> cls2) {
        try {
            ArrayList arrayList = new ArrayList();
            boolean z8 = false;
            for (b<?, ?> bVar : this.f16793a) {
                if (this.f16795c.contains(bVar)) {
                    z8 = true;
                } else if (bVar.m19150b(cls, cls2)) {
                    this.f16795c.add(bVar);
                    arrayList.add(m19141c(bVar));
                    this.f16795c.remove(bVar);
                }
            }
            if (arrayList.size() > 1) {
                return this.f16794b.m19151a(arrayList, this.f16796d);
            }
            if (arrayList.size() == 1) {
                return (InterfaceC4820n) arrayList.get(0);
            }
            if (!z8) {
                throw new Registry.NoModelLoaderAvailableException(cls, cls2);
            }
            return m19138f();
        } catch (Throwable th) {
            this.f16795c.clear();
            throw th;
        }
    }

    /* renamed from: e */
    public synchronized <Model> List<InterfaceC4820n<Model, ?>> m19143e(Class<Model> cls) {
        ArrayList arrayList;
        try {
            arrayList = new ArrayList();
            for (b<?, ?> bVar : this.f16793a) {
                if (!this.f16795c.contains(bVar) && bVar.m19149a(cls)) {
                    this.f16795c.add(bVar);
                    arrayList.add(m19141c(bVar));
                    this.f16795c.remove(bVar);
                }
            }
        } catch (Throwable th) {
            this.f16795c.clear();
            throw th;
        }
        return arrayList;
    }

    /* renamed from: g */
    public synchronized List<Class<?>> m19144g(Class<?> cls) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        for (b<?, ?> bVar : this.f16793a) {
            if (!arrayList.contains(bVar.f16798b) && bVar.m19149a(cls)) {
                arrayList.add(bVar.f16798b);
            }
        }
        return arrayList;
    }

    /* renamed from: h */
    public final <Model, Data> InterfaceC4821o<Model, Data> m19145h(b<?, ?> bVar) {
        return (InterfaceC4821o<Model, Data>) bVar.f16799c;
    }

    /* renamed from: i */
    public synchronized <Model, Data> void m19146i(Class<Model> cls, Class<Data> cls2, InterfaceC4821o<? extends Model, ? extends Data> interfaceC4821o) {
        m19139a(cls, cls2, interfaceC4821o, false);
    }

    /* renamed from: j */
    public synchronized <Model, Data> List<InterfaceC4821o<? extends Model, ? extends Data>> m19147j(Class<Model> cls, Class<Data> cls2) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        Iterator<b<?, ?>> it = this.f16793a.iterator();
        while (it.hasNext()) {
            b<?, ?> next = it.next();
            if (next.m19150b(cls, cls2)) {
                it.remove();
                arrayList.add(m19145h(next));
            }
        }
        return arrayList;
    }

    /* renamed from: k */
    public synchronized <Model, Data> List<InterfaceC4821o<? extends Model, ? extends Data>> m19148k(Class<Model> cls, Class<Data> cls2, InterfaceC4821o<? extends Model, ? extends Data> interfaceC4821o) {
        List<InterfaceC4821o<? extends Model, ? extends Data>> listM19147j;
        listM19147j = m19147j(cls, cls2);
        m19140b(cls, cls2, interfaceC4821o);
        return listM19147j;
    }

    public C4824r(InterfaceC0699e<List<Throwable>> interfaceC0699e, c cVar) {
        this.f16793a = new ArrayList();
        this.f16795c = new HashSet();
        this.f16796d = interfaceC0699e;
        this.f16794b = cVar;
    }
}
