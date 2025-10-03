package p091i;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* renamed from: i.b */
/* loaded from: classes.dex */
public class C5037b<K, V> implements Iterable<Map.Entry<K, V>> {

    /* renamed from: b */
    public c<K, V> f17391b;

    /* renamed from: c */
    public c<K, V> f17392c;

    /* renamed from: d */
    public WeakHashMap<f<K, V>, Boolean> f17393d = new WeakHashMap<>();

    /* renamed from: e */
    public int f17394e = 0;

    /* renamed from: i.b$a */
    public static class a<K, V> extends e<K, V> {
        public a(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        @Override // p091i.C5037b.e
        /* renamed from: b */
        public c<K, V> mo19689b(c<K, V> cVar) {
            return cVar.f17398e;
        }

        @Override // p091i.C5037b.e
        /* renamed from: c */
        public c<K, V> mo19690c(c<K, V> cVar) {
            return cVar.f17397d;
        }
    }

    /* renamed from: i.b$b */
    public static class b<K, V> extends e<K, V> {
        public b(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        @Override // p091i.C5037b.e
        /* renamed from: b */
        public c<K, V> mo19689b(c<K, V> cVar) {
            return cVar.f17397d;
        }

        @Override // p091i.C5037b.e
        /* renamed from: c */
        public c<K, V> mo19690c(c<K, V> cVar) {
            return cVar.f17398e;
        }
    }

    /* renamed from: i.b$c */
    public static class c<K, V> implements Map.Entry<K, V> {

        /* renamed from: b */
        public final K f17395b;

        /* renamed from: c */
        public final V f17396c;

        /* renamed from: d */
        public c<K, V> f17397d;

        /* renamed from: e */
        public c<K, V> f17398e;

        public c(K k9, V v8) {
            this.f17395b = k9;
            this.f17396c = v8;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof c)) {
                return false;
            }
            c cVar = (c) obj;
            return this.f17395b.equals(cVar.f17395b) && this.f17396c.equals(cVar.f17396c);
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.f17395b;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.f17396c;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            return this.f17395b.hashCode() ^ this.f17396c.hashCode();
        }

        @Override // java.util.Map.Entry
        public V setValue(V v8) {
            throw new UnsupportedOperationException("An entry modification is not supported");
        }

        public String toString() {
            return this.f17395b + "=" + this.f17396c;
        }
    }

    /* renamed from: i.b$d */
    public class d implements Iterator<Map.Entry<K, V>>, f<K, V> {

        /* renamed from: b */
        public c<K, V> f17399b;

        /* renamed from: c */
        public boolean f17400c = true;

        public d() {
        }

        @Override // p091i.C5037b.f
        /* renamed from: a */
        public void mo19691a(c<K, V> cVar) {
            c<K, V> cVar2 = this.f17399b;
            if (cVar == cVar2) {
                c<K, V> cVar3 = cVar2.f17398e;
                this.f17399b = cVar3;
                this.f17400c = cVar3 == null;
            }
        }

        @Override // java.util.Iterator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public Map.Entry<K, V> next() {
            if (this.f17400c) {
                this.f17400c = false;
                this.f17399b = C5037b.this.f17391b;
            } else {
                c<K, V> cVar = this.f17399b;
                this.f17399b = cVar != null ? cVar.f17397d : null;
            }
            return this.f17399b;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.f17400c) {
                return C5037b.this.f17391b != null;
            }
            c<K, V> cVar = this.f17399b;
            return (cVar == null || cVar.f17397d == null) ? false : true;
        }
    }

    /* renamed from: i.b$e */
    public static abstract class e<K, V> implements Iterator<Map.Entry<K, V>>, f<K, V> {

        /* renamed from: b */
        public c<K, V> f17402b;

        /* renamed from: c */
        public c<K, V> f17403c;

        public e(c<K, V> cVar, c<K, V> cVar2) {
            this.f17402b = cVar2;
            this.f17403c = cVar;
        }

        @Override // p091i.C5037b.f
        /* renamed from: a */
        public void mo19691a(c<K, V> cVar) {
            if (this.f17402b == cVar && cVar == this.f17403c) {
                this.f17403c = null;
                this.f17402b = null;
            }
            c<K, V> cVar2 = this.f17402b;
            if (cVar2 == cVar) {
                this.f17402b = mo19689b(cVar2);
            }
            if (this.f17403c == cVar) {
                this.f17403c = m19694e();
            }
        }

        /* renamed from: b */
        public abstract c<K, V> mo19689b(c<K, V> cVar);

        /* renamed from: c */
        public abstract c<K, V> mo19690c(c<K, V> cVar);

        @Override // java.util.Iterator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public Map.Entry<K, V> next() {
            c<K, V> cVar = this.f17403c;
            this.f17403c = m19694e();
            return cVar;
        }

        /* renamed from: e */
        public final c<K, V> m19694e() {
            c<K, V> cVar = this.f17403c;
            c<K, V> cVar2 = this.f17402b;
            if (cVar == cVar2 || cVar2 == null) {
                return null;
            }
            return mo19690c(cVar);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f17403c != null;
        }
    }

    /* renamed from: i.b$f */
    public interface f<K, V> {
        /* renamed from: a */
        void mo19691a(c<K, V> cVar);
    }

    /* renamed from: a */
    public Map.Entry<K, V> m19685a() {
        return this.f17391b;
    }

    /* renamed from: b */
    public c<K, V> mo19681b(K k9) {
        c<K, V> cVar = this.f17391b;
        while (cVar != null && !cVar.f17395b.equals(k9)) {
            cVar = cVar.f17397d;
        }
        return cVar;
    }

    /* renamed from: c */
    public C5037b<K, V>.d m19686c() {
        C5037b<K, V>.d dVar = new d();
        this.f17393d.put(dVar, Boolean.FALSE);
        return dVar;
    }

    /* renamed from: d */
    public Map.Entry<K, V> m19687d() {
        return this.f17392c;
    }

    public Iterator<Map.Entry<K, V>> descendingIterator() {
        b bVar = new b(this.f17392c, this.f17391b);
        this.f17393d.put(bVar, Boolean.FALSE);
        return bVar;
    }

    /* renamed from: e */
    public c<K, V> m19688e(K k9, V v8) {
        c<K, V> cVar = new c<>(k9, v8);
        this.f17394e++;
        c<K, V> cVar2 = this.f17392c;
        if (cVar2 == null) {
            this.f17391b = cVar;
            this.f17392c = cVar;
            return cVar;
        }
        cVar2.f17397d = cVar;
        cVar.f17398e = cVar2;
        this.f17392c = cVar;
        return cVar;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C5037b)) {
            return false;
        }
        C5037b c5037b = (C5037b) obj;
        if (size() != c5037b.size()) {
            return false;
        }
        Iterator<Map.Entry<K, V>> it = iterator();
        Iterator<Map.Entry<K, V>> it2 = c5037b.iterator();
        while (it.hasNext() && it2.hasNext()) {
            Map.Entry<K, V> next = it.next();
            Map.Entry<K, V> next2 = it2.next();
            if ((next == null && next2 != null) || (next != null && !next.equals(next2))) {
                return false;
            }
        }
        return (it.hasNext() || it2.hasNext()) ? false : true;
    }

    /* renamed from: f */
    public V mo19682f(K k9, V v8) {
        c<K, V> cVarMo19681b = mo19681b(k9);
        if (cVarMo19681b != null) {
            return cVarMo19681b.f17396c;
        }
        m19688e(k9, v8);
        return null;
    }

    /* renamed from: g */
    public V mo19683g(K k9) {
        c<K, V> cVarMo19681b = mo19681b(k9);
        if (cVarMo19681b == null) {
            return null;
        }
        this.f17394e--;
        if (!this.f17393d.isEmpty()) {
            Iterator<f<K, V>> it = this.f17393d.keySet().iterator();
            while (it.hasNext()) {
                it.next().mo19691a(cVarMo19681b);
            }
        }
        c<K, V> cVar = cVarMo19681b.f17398e;
        if (cVar != null) {
            cVar.f17397d = cVarMo19681b.f17397d;
        } else {
            this.f17391b = cVarMo19681b.f17397d;
        }
        c<K, V> cVar2 = cVarMo19681b.f17397d;
        if (cVar2 != null) {
            cVar2.f17398e = cVar;
        } else {
            this.f17392c = cVar;
        }
        cVarMo19681b.f17397d = null;
        cVarMo19681b.f17398e = null;
        return cVarMo19681b.f17396c;
    }

    public int hashCode() {
        Iterator<Map.Entry<K, V>> it = iterator();
        int iHashCode = 0;
        while (it.hasNext()) {
            iHashCode += it.next().hashCode();
        }
        return iHashCode;
    }

    @Override // java.lang.Iterable
    public Iterator<Map.Entry<K, V>> iterator() {
        a aVar = new a(this.f17391b, this.f17392c);
        this.f17393d.put(aVar, Boolean.FALSE);
        return aVar;
    }

    public int size() {
        return this.f17394e;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Iterator<Map.Entry<K, V>> it = iterator();
        while (it.hasNext()) {
            sb.append(it.next().toString());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
