package p022c1;

import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import p226w1.C6516i;

/* renamed from: c1.i */
/* loaded from: classes.dex */
public final class C0712i implements InterfaceC0705b {

    /* renamed from: a */
    public final C0710g<a, Object> f3367a = new C0710g<>();

    /* renamed from: b */
    public final b f3368b = new b();

    /* renamed from: c */
    public final Map<Class<?>, NavigableMap<Integer, Integer>> f3369c = new HashMap();

    /* renamed from: d */
    public final Map<Class<?>, InterfaceC0704a<?>> f3370d = new HashMap();

    /* renamed from: e */
    public final int f3371e;

    /* renamed from: f */
    public int f3372f;

    /* renamed from: c1.i$a */
    public static final class a implements InterfaceC0715l {

        /* renamed from: a */
        public final b f3373a;

        /* renamed from: b */
        public int f3374b;

        /* renamed from: c */
        public Class<?> f3375c;

        public a(b bVar) {
            this.f3373a = bVar;
        }

        @Override // p022c1.InterfaceC0715l
        /* renamed from: a */
        public void mo3515a() {
            this.f3373a.m3484c(this);
        }

        /* renamed from: b */
        public void m3516b(int i9, Class<?> cls) {
            this.f3374b = i9;
            this.f3375c = cls;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return this.f3374b == aVar.f3374b && this.f3375c == aVar.f3375c;
        }

        public int hashCode() {
            int i9 = this.f3374b * 31;
            Class<?> cls = this.f3375c;
            return i9 + (cls != null ? cls.hashCode() : 0);
        }

        public String toString() {
            return "Key{size=" + this.f3374b + "array=" + this.f3375c + '}';
        }
    }

    /* renamed from: c1.i$b */
    public static final class b extends AbstractC0706c<a> {
        @Override // p022c1.AbstractC0706c
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public a mo3482a() {
            return new a(this);
        }

        /* renamed from: e */
        public a m3518e(int i9, Class<?> cls) {
            a aVarM3483b = m3483b();
            aVarM3483b.m3516b(i9, cls);
            return aVarM3483b;
        }
    }

    public C0712i(int i9) {
        this.f3371e = i9;
    }

    @Override // p022c1.InterfaceC0705b
    /* renamed from: a */
    public synchronized void mo3478a(int i9) {
        try {
            if (i9 >= 40) {
                mo3479b();
            } else if (i9 >= 20 || i9 == 15) {
                m3506g(this.f3371e / 2);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // p022c1.InterfaceC0705b
    /* renamed from: b */
    public synchronized void mo3479b() {
        m3506g(0);
    }

    @Override // p022c1.InterfaceC0705b
    /* renamed from: c */
    public synchronized <T> T mo3480c(int i9, Class<T> cls) {
        return (T) m3510k(this.f3368b.m3518e(i9, cls), cls);
    }

    @Override // p022c1.InterfaceC0705b
    /* renamed from: d */
    public synchronized <T> T mo3481d(int i9, Class<T> cls) {
        Integer numCeilingKey;
        numCeilingKey = m3511l(cls).ceilingKey(Integer.valueOf(i9));
        return (T) m3510k(m3514o(i9, numCeilingKey) ? this.f3368b.m3518e(numCeilingKey.intValue(), cls) : this.f3368b.m3518e(i9, cls), cls);
    }

    /* renamed from: e */
    public final void m3504e(int i9, Class<?> cls) {
        NavigableMap<Integer, Integer> navigableMapM3511l = m3511l(cls);
        Integer num = navigableMapM3511l.get(Integer.valueOf(i9));
        if (num != null) {
            if (num.intValue() == 1) {
                navigableMapM3511l.remove(Integer.valueOf(i9));
                return;
            } else {
                navigableMapM3511l.put(Integer.valueOf(i9), Integer.valueOf(num.intValue() - 1));
                return;
            }
        }
        throw new NullPointerException("Tried to decrement empty size, size: " + i9 + ", this: " + this);
    }

    /* renamed from: f */
    public final void m3505f() {
        m3506g(this.f3371e);
    }

    /* renamed from: g */
    public final void m3506g(int i9) {
        while (this.f3372f > i9) {
            Object objM3498f = this.f3367a.m3498f();
            C6516i.m24938d(objM3498f);
            InterfaceC0704a interfaceC0704aM3507h = m3507h(objM3498f);
            this.f3372f -= interfaceC0704aM3507h.mo3477b(objM3498f) * interfaceC0704aM3507h.mo3476a();
            m3504e(interfaceC0704aM3507h.mo3477b(objM3498f), objM3498f.getClass());
            if (Log.isLoggable(interfaceC0704aM3507h.getTag(), 2)) {
                Log.v(interfaceC0704aM3507h.getTag(), "evicted: " + interfaceC0704aM3507h.mo3477b(objM3498f));
            }
        }
    }

    /* renamed from: h */
    public final <T> InterfaceC0704a<T> m3507h(T t8) {
        return m3508i(t8.getClass());
    }

    /* renamed from: i */
    public final <T> InterfaceC0704a<T> m3508i(Class<T> cls) {
        InterfaceC0704a<T> c0709f = (InterfaceC0704a) this.f3370d.get(cls);
        if (c0709f == null) {
            if (cls.equals(int[].class)) {
                c0709f = new C0711h();
            } else {
                if (!cls.equals(byte[].class)) {
                    throw new IllegalArgumentException("No array pool found for: " + cls.getSimpleName());
                }
                c0709f = new C0709f();
            }
            this.f3370d.put(cls, c0709f);
        }
        return c0709f;
    }

    /* renamed from: j */
    public final <T> T m3509j(a aVar) {
        return (T) this.f3367a.m3494a(aVar);
    }

    /* renamed from: k */
    public final <T> T m3510k(a aVar, Class<T> cls) {
        InterfaceC0704a<T> interfaceC0704aM3508i = m3508i(cls);
        T t8 = (T) m3509j(aVar);
        if (t8 != null) {
            this.f3372f -= interfaceC0704aM3508i.mo3477b(t8) * interfaceC0704aM3508i.mo3476a();
            m3504e(interfaceC0704aM3508i.mo3477b(t8), cls);
        }
        if (t8 != null) {
            return t8;
        }
        if (Log.isLoggable(interfaceC0704aM3508i.getTag(), 2)) {
            Log.v(interfaceC0704aM3508i.getTag(), "Allocated " + aVar.f3374b + " bytes");
        }
        return interfaceC0704aM3508i.newArray(aVar.f3374b);
    }

    /* renamed from: l */
    public final NavigableMap<Integer, Integer> m3511l(Class<?> cls) {
        NavigableMap<Integer, Integer> navigableMap = this.f3369c.get(cls);
        if (navigableMap != null) {
            return navigableMap;
        }
        TreeMap treeMap = new TreeMap();
        this.f3369c.put(cls, treeMap);
        return treeMap;
    }

    /* renamed from: m */
    public final boolean m3512m() {
        int i9 = this.f3372f;
        return i9 == 0 || this.f3371e / i9 >= 2;
    }

    /* renamed from: n */
    public final boolean m3513n(int i9) {
        return i9 <= this.f3371e / 2;
    }

    /* renamed from: o */
    public final boolean m3514o(int i9, Integer num) {
        return num != null && (m3512m() || num.intValue() <= i9 * 8);
    }

    @Override // p022c1.InterfaceC0705b
    public synchronized <T> void put(T t8) {
        Class<?> cls = t8.getClass();
        InterfaceC0704a<T> interfaceC0704aM3508i = m3508i(cls);
        int iMo3477b = interfaceC0704aM3508i.mo3477b(t8);
        int iMo3476a = interfaceC0704aM3508i.mo3476a() * iMo3477b;
        if (m3513n(iMo3476a)) {
            a aVarM3518e = this.f3368b.m3518e(iMo3477b, cls);
            this.f3367a.m3497d(aVarM3518e, t8);
            NavigableMap<Integer, Integer> navigableMapM3511l = m3511l(cls);
            Integer num = navigableMapM3511l.get(Integer.valueOf(aVarM3518e.f3374b));
            Integer numValueOf = Integer.valueOf(aVarM3518e.f3374b);
            int iIntValue = 1;
            if (num != null) {
                iIntValue = 1 + num.intValue();
            }
            navigableMapM3511l.put(numValueOf, Integer.valueOf(iIntValue));
            this.f3372f += iMo3476a;
            m3505f();
        }
    }
}
