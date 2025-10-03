package p132m;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* renamed from: m.a */
/* loaded from: classes.dex */
public class C5302a<K, V> extends C5308g<K, V> implements Map<K, V> {

    /* renamed from: i */
    public AbstractC5307f<K, V> f18001i;

    /* renamed from: m.a$a */
    public class a extends AbstractC5307f<K, V> {
        public a() {
        }

        @Override // p132m.AbstractC5307f
        /* renamed from: a */
        public void mo20695a() {
            C5302a.this.clear();
        }

        @Override // p132m.AbstractC5307f
        /* renamed from: b */
        public Object mo20696b(int i9, int i10) {
            return C5302a.this.f18042c[(i9 << 1) + i10];
        }

        @Override // p132m.AbstractC5307f
        /* renamed from: c */
        public Map<K, V> mo20697c() {
            return C5302a.this;
        }

        @Override // p132m.AbstractC5307f
        /* renamed from: d */
        public int mo20698d() {
            return C5302a.this.f18043d;
        }

        @Override // p132m.AbstractC5307f
        /* renamed from: e */
        public int mo20699e(Object obj) {
            return C5302a.this.m20748f(obj);
        }

        @Override // p132m.AbstractC5307f
        /* renamed from: f */
        public int mo20700f(Object obj) {
            return C5302a.this.m20750h(obj);
        }

        @Override // p132m.AbstractC5307f
        /* renamed from: g */
        public void mo20701g(K k9, V v8) {
            C5302a.this.put(k9, v8);
        }

        @Override // p132m.AbstractC5307f
        /* renamed from: h */
        public void mo20702h(int i9) {
            C5302a.this.mo20753k(i9);
        }

        @Override // p132m.AbstractC5307f
        /* renamed from: i */
        public V mo20703i(int i9, V v8) {
            return C5302a.this.mo20754l(i9, v8);
        }
    }

    public C5302a() {
    }

    @Override // java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        return m20693n().m20736l();
    }

    @Override // java.util.Map
    public Set<K> keySet() {
        return m20693n().m20737m();
    }

    /* renamed from: n */
    public final AbstractC5307f<K, V> m20693n() {
        if (this.f18001i == null) {
            this.f18001i = new a();
        }
        return this.f18001i;
    }

    /* renamed from: o */
    public boolean m20694o(Collection<?> collection) {
        return AbstractC5307f.m20735p(this, collection);
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        m20746c(this.f18043d + map.size());
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.Map
    public Collection<V> values() {
        return m20693n().m20738n();
    }

    public C5302a(int i9) {
        super(i9);
    }

    public C5302a(C5308g c5308g) {
        super(c5308g);
    }
}
