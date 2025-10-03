package p202t4;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/* renamed from: t4.a */
/* loaded from: classes.dex */
public class C6312a<K, V> extends AbstractMap<K, V> implements Map<K, V> {

    /* renamed from: b */
    public final ReferenceQueue<K> f21281b;

    /* renamed from: c */
    public int f21282c;

    /* renamed from: d */
    public d<K, V>[] f21283d;

    /* renamed from: e */
    public final int f21284e;

    /* renamed from: f */
    public int f21285f;

    /* renamed from: g */
    public volatile int f21286g;

    /* renamed from: h */
    public Set<K> f21287h;

    /* renamed from: i */
    public Collection<V> f21288i;

    /* renamed from: t4.a$a */
    public class a extends AbstractSet<Map.Entry<K, V>> {

        /* renamed from: t4.a$a$a, reason: collision with other inner class name */
        public class C6884a implements d.a<Map.Entry<K, V>, K, V> {
            public C6884a() {
            }

            @Override // p202t4.C6312a.d.a
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public Map.Entry<K, V> mo24145a(Map.Entry<K, V> entry) {
                return entry;
            }
        }

        public a() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            C6312a.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            d dVarM24141h;
            if (!(obj instanceof Map.Entry) || (dVarM24141h = C6312a.this.m24141h(((Map.Entry) obj).getKey())) == null) {
                return false;
            }
            if (dVarM24141h.get() != null || dVarM24141h.f21296c) {
                return obj.equals(dVarM24141h);
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return new e(new C6884a());
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            if (!contains(obj)) {
                return false;
            }
            C6312a.this.remove(((Map.Entry) obj).getKey());
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return C6312a.this.size();
        }
    }

    /* renamed from: t4.a$b */
    public class b extends AbstractSet<K> {

        /* renamed from: t4.a$b$a */
        public class a implements d.a<K, K, V> {
            public a() {
            }

            @Override // p202t4.C6312a.d.a
            /* renamed from: a */
            public K mo24145a(Map.Entry<K, V> entry) {
                return entry.getKey();
            }
        }

        public b() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            C6312a.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return C6312a.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return new e(new a());
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            if (!C6312a.this.containsKey(obj)) {
                return false;
            }
            C6312a.this.remove(obj);
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return C6312a.this.size();
        }
    }

    /* renamed from: t4.a$c */
    public class c extends AbstractCollection<V> {

        /* renamed from: t4.a$c$a */
        public class a implements d.a<V, K, V> {
            public a() {
            }

            @Override // p202t4.C6312a.d.a
            /* renamed from: a */
            public V mo24145a(Map.Entry<K, V> entry) {
                return entry.getValue();
            }
        }

        public c() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            C6312a.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            return C6312a.this.containsValue(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return new e(new a());
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return C6312a.this.size();
        }
    }

    /* renamed from: t4.a$d */
    public static final class d<K, V> extends WeakReference<K> implements Map.Entry<K, V> {

        /* renamed from: b */
        public final int f21295b;

        /* renamed from: c */
        public boolean f21296c;

        /* renamed from: d */
        public V f21297d;

        /* renamed from: e */
        public d<K, V> f21298e;

        /* renamed from: t4.a$d$a */
        public interface a<R, K, V> {
            /* renamed from: a */
            R mo24145a(Map.Entry<K, V> entry);
        }

        public d(K k9, V v8, ReferenceQueue<K> referenceQueue) {
            super(k9, referenceQueue);
            boolean z8 = k9 == null;
            this.f21296c = z8;
            this.f21295b = z8 ? 0 : C6312a.m24138f(k9);
            this.f21297d = v8;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            if (super.get() != entry.getKey()) {
                return false;
            }
            V v8 = this.f21297d;
            if (v8 == null) {
                if (v8 != entry.getValue()) {
                    return false;
                }
            } else if (!v8.equals(entry.getValue())) {
                return false;
            }
            return true;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return (K) super.get();
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.f21297d;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            int i9 = this.f21295b;
            V v8 = this.f21297d;
            return i9 + (v8 == null ? 0 : v8.hashCode());
        }

        @Override // java.util.Map.Entry
        public V setValue(V v8) {
            V v9 = this.f21297d;
            this.f21297d = v8;
            return v9;
        }

        public String toString() {
            return super.get() + "=" + this.f21297d;
        }
    }

    /* renamed from: t4.a$e */
    public class e<R> implements Iterator<R> {

        /* renamed from: b */
        public int f21299b = 0;

        /* renamed from: c */
        public int f21300c;

        /* renamed from: d */
        public d<K, V> f21301d;

        /* renamed from: e */
        public d<K, V> f21302e;

        /* renamed from: f */
        public K f21303f;

        /* renamed from: g */
        public final d.a<R, K, V> f21304g;

        public e(d.a<R, K, V> aVar) {
            this.f21304g = aVar;
            this.f21300c = C6312a.this.f21286g;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            d<K, V> dVar = this.f21302e;
            if (dVar != null && (this.f21303f != null || dVar.f21296c)) {
                return true;
            }
            while (true) {
                if (this.f21302e == null) {
                    while (this.f21299b < C6312a.this.f21283d.length) {
                        d<K, V>[] dVarArr = C6312a.this.f21283d;
                        int i9 = this.f21299b;
                        this.f21299b = i9 + 1;
                        d<K, V> dVar2 = dVarArr[i9];
                        this.f21302e = dVar2;
                        if (dVar2 != null) {
                            break;
                        }
                    }
                    if (this.f21302e == null) {
                        return false;
                    }
                }
                K k9 = this.f21302e.get();
                this.f21303f = k9;
                if (k9 != null) {
                    break;
                }
                d<K, V> dVar3 = this.f21302e;
                if (dVar3.f21296c) {
                    break;
                }
                this.f21302e = dVar3.f21298e;
            }
            return true;
        }

        @Override // java.util.Iterator
        public R next() {
            if (this.f21300c != C6312a.this.f21286g) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            d<K, V> dVar = this.f21302e;
            this.f21301d = dVar;
            this.f21302e = dVar.f21298e;
            R rMo24145a = this.f21304g.mo24145a(dVar);
            this.f21303f = null;
            return rMo24145a;
        }

        @Override // java.util.Iterator
        public void remove() {
            if (this.f21300c != C6312a.this.f21286g) {
                throw new ConcurrentModificationException();
            }
            d<K, V> dVar = this.f21301d;
            if (dVar == null) {
                throw new IllegalStateException();
            }
            C6312a.this.m24144l(dVar);
            this.f21301d = null;
            this.f21300c++;
        }
    }

    public C6312a() {
        this(16);
    }

    /* renamed from: f */
    public static <K> int m24138f(K k9) {
        return System.identityHashCode(k9);
    }

    /* renamed from: i */
    public static <K, V> d<K, V>[] m24139i(int i9) {
        return new d[i9];
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        if (this.f21282c > 0) {
            this.f21282c = 0;
            Arrays.fill(this.f21283d, (Object) null);
            this.f21286g++;
            while (this.f21281b.poll() != null) {
            }
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        return m24141h(obj) != null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsValue(Object obj) {
        m24142j();
        if (obj != null) {
            int length = this.f21283d.length;
            while (true) {
                length--;
                if (length < 0) {
                    return false;
                }
                for (d<K, V> dVar = this.f21283d[length]; dVar != null; dVar = dVar.f21298e) {
                    if ((dVar.get() != null || dVar.f21296c) && obj.equals(dVar.f21297d)) {
                        return true;
                    }
                }
            }
        } else {
            int length2 = this.f21283d.length;
            while (true) {
                length2--;
                if (length2 < 0) {
                    return false;
                }
                for (d<K, V> dVar2 = this.f21283d[length2]; dVar2 != null; dVar2 = dVar2.f21298e) {
                    if ((dVar2.get() != null || dVar2.f21296c) && dVar2.f21297d == null) {
                        return true;
                    }
                }
            }
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        m24142j();
        return new a();
    }

    /* renamed from: g */
    public final void m24140g() {
        this.f21285f = (int) ((this.f21283d.length * this.f21284e) / 10000);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        d<K, V> dVarM24141h = m24141h(obj);
        if (dVarM24141h != null) {
            return dVarM24141h.f21297d;
        }
        return null;
    }

    /* renamed from: h */
    public final d<K, V> m24141h(Object obj) {
        m24142j();
        if (obj == null) {
            for (d<K, V> dVar = this.f21283d[0]; dVar != null; dVar = dVar.f21298e) {
                if (dVar.f21296c) {
                    return dVar;
                }
            }
            return null;
        }
        int iM24138f = m24138f(obj) & Integer.MAX_VALUE;
        d<K, V>[] dVarArr = this.f21283d;
        for (d<K, V> dVar2 = dVarArr[iM24138f % dVarArr.length]; dVar2 != null; dVar2 = dVar2.f21298e) {
            if (obj == dVar2.get()) {
                return dVar2;
            }
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean isEmpty() {
        return size() == 0;
    }

    /* renamed from: j */
    public final void m24142j() {
        while (true) {
            d<K, V> dVar = (d) this.f21281b.poll();
            if (dVar == null) {
                return;
            } else {
                m24144l(dVar);
            }
        }
    }

    /* renamed from: k */
    public final void m24143k() {
        int length = this.f21283d.length * 2;
        d<K, V>[] dVarArrM24139i = m24139i(length);
        for (d<K, V> dVar : this.f21283d) {
            while (dVar != null) {
                int i9 = dVar.f21296c ? 0 : (dVar.f21295b & Integer.MAX_VALUE) % length;
                d<K, V> dVar2 = dVar.f21298e;
                dVar.f21298e = dVarArrM24139i[i9];
                dVarArrM24139i[i9] = dVar;
                dVar = dVar2;
            }
        }
        this.f21283d = dVarArrM24139i;
        m24140g();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        m24142j();
        if (this.f21287h == null) {
            this.f21287h = new b();
        }
        return this.f21287h;
    }

    /* renamed from: l */
    public final void m24144l(d<K, V> dVar) {
        int i9 = dVar.f21295b & Integer.MAX_VALUE;
        d<K, V>[] dVarArr = this.f21283d;
        int length = i9 % dVarArr.length;
        d<K, V> dVar2 = null;
        for (d<K, V> dVar3 = dVarArr[length]; dVar3 != null; dVar3 = dVar3.f21298e) {
            if (dVar == dVar3) {
                this.f21286g++;
                if (dVar2 == null) {
                    this.f21283d[length] = dVar3.f21298e;
                } else {
                    dVar2.f21298e = dVar3.f21298e;
                }
                this.f21282c--;
                return;
            }
            dVar2 = dVar3;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V put(K k9, V v8) {
        d<K, V> dVar;
        int iM24138f;
        m24142j();
        if (k9 != null) {
            int iM24138f2 = m24138f(k9) & Integer.MAX_VALUE;
            d<K, V>[] dVarArr = this.f21283d;
            iM24138f = iM24138f2 % dVarArr.length;
            dVar = dVarArr[iM24138f];
            while (dVar != null && k9 != dVar.get()) {
                dVar = dVar.f21298e;
            }
        } else {
            dVar = this.f21283d[0];
            while (dVar != null && !dVar.f21296c) {
                dVar = dVar.f21298e;
            }
            iM24138f = 0;
        }
        if (dVar != null) {
            V v9 = dVar.f21297d;
            dVar.f21297d = v8;
            return v9;
        }
        this.f21286g++;
        int i9 = this.f21282c + 1;
        this.f21282c = i9;
        if (i9 > this.f21285f) {
            m24143k();
            iM24138f = k9 != null ? (Integer.MAX_VALUE & m24138f(k9)) % this.f21283d.length : 0;
        }
        d<K, V> dVar2 = new d<>(k9, v8, this.f21281b);
        d<K, V>[] dVarArr2 = this.f21283d;
        dVar2.f21298e = dVarArr2[iM24138f];
        dVarArr2[iM24138f] = dVar2;
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        int length;
        d<K, V> dVar;
        d<K, V> dVar2;
        m24142j();
        if (obj != null) {
            int iM24138f = m24138f(obj) & Integer.MAX_VALUE;
            d<K, V>[] dVarArr = this.f21283d;
            length = iM24138f % dVarArr.length;
            dVar = dVarArr[length];
            dVar2 = null;
            while (dVar != null && obj != dVar.get()) {
                dVar2 = dVar;
                dVar = dVar.f21298e;
            }
        } else {
            length = 0;
            dVar = this.f21283d[0];
            dVar2 = null;
            while (dVar != null && !dVar.f21296c) {
                dVar2 = dVar;
                dVar = dVar.f21298e;
            }
        }
        if (dVar == null) {
            return null;
        }
        this.f21286g++;
        if (dVar2 == null) {
            this.f21283d[length] = dVar.f21298e;
        } else {
            dVar2.f21298e = dVar.f21298e;
        }
        this.f21282c--;
        return dVar.f21297d;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        m24142j();
        return this.f21282c;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Collection<V> values() {
        m24142j();
        if (this.f21288i == null) {
            this.f21288i = new c();
        }
        return this.f21288i;
    }

    public C6312a(int i9) {
        this(i9, 0.75f);
    }

    public C6312a(int i9, float f9) {
        if (i9 < 0) {
            throw new IllegalArgumentException("capacity < 0: " + i9);
        }
        if (f9 > BitmapDescriptorFactory.HUE_RED) {
            this.f21282c = 0;
            this.f21283d = m24139i(i9 == 0 ? 1 : i9);
            this.f21284e = (int) (f9 * 10000.0f);
            m24140g();
            this.f21281b = new ReferenceQueue<>();
            return;
        }
        throw new IllegalArgumentException("loadFactor <= 0: " + f9);
    }
}
