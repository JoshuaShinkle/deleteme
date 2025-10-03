package org.jivesoftware.smack.util;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/* renamed from: org.jivesoftware.smack.util.c */
/* loaded from: classes.dex */
public class C5609c<K, V> implements Map<K, V> {

    /* renamed from: i */
    public static final Logger f19487i = Logger.getLogger(C5609c.class.getName());

    /* renamed from: b */
    public Map<K, d<V>> f19488b;

    /* renamed from: c */
    public e f19489c;

    /* renamed from: d */
    public e f19490d;

    /* renamed from: e */
    public int f19491e;

    /* renamed from: f */
    public long f19492f;

    /* renamed from: g */
    public long f19493g;

    /* renamed from: h */
    public long f19494h = 0;

    /* renamed from: org.jivesoftware.smack.util.c$a */
    public class a extends AbstractCollection<V> {

        /* renamed from: b */
        public Collection<d<V>> f19495b;

        /* renamed from: org.jivesoftware.smack.util.c$a$a, reason: collision with other inner class name */
        public class C6880a implements Iterator<V> {

            /* renamed from: b */
            public Iterator<d<V>> f19497b;

            public C6880a() {
                this.f19497b = a.this.f19495b.iterator();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.f19497b.hasNext();
            }

            @Override // java.util.Iterator
            public V next() {
                return this.f19497b.next().f19506a;
            }

            @Override // java.util.Iterator
            public void remove() {
                this.f19497b.remove();
            }
        }

        public a() {
            this.f19495b = C5609c.this.f19488b.values();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return new C6880a();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return this.f19495b.size();
        }
    }

    /* renamed from: org.jivesoftware.smack.util.c$b */
    public class b extends AbstractSet<Map.Entry<K, V>> {

        /* renamed from: b */
        public final Set<Map.Entry<K, d<V>>> f19499b;

        /* renamed from: org.jivesoftware.smack.util.c$b$a */
        public class a implements Iterator<Map.Entry<K, V>> {

            /* renamed from: b */
            public final Iterator<Map.Entry<K, d<V>>> f19501b;

            /* renamed from: org.jivesoftware.smack.util.c$b$a$a, reason: collision with other inner class name */
            public class C6881a extends c<K, V> {
                public C6881a(Object obj, Object obj2) {
                    super(obj, obj2);
                }

                @Override // java.util.Map.Entry
                public V setValue(V v8) {
                    throw new UnsupportedOperationException("Cannot set");
                }
            }

            public a() {
                this.f19501b = b.this.f19499b.iterator();
            }

            @Override // java.util.Iterator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Map.Entry<K, V> next() {
                Map.Entry<K, d<V>> next = this.f19501b.next();
                return new C6881a(next.getKey(), next.getValue().f19506a);
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.f19501b.hasNext();
            }

            @Override // java.util.Iterator
            public void remove() {
                this.f19501b.remove();
            }
        }

        public b() {
            this.f19499b = C5609c.this.f19488b.entrySet();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return new a();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.f19499b.size();
        }
    }

    /* renamed from: org.jivesoftware.smack.util.c$c */
    public static class c<K, V> implements Map.Entry<K, V> {

        /* renamed from: b */
        public final K f19504b;

        /* renamed from: c */
        public V f19505c;

        public c(K k9, V v8) {
            this.f19504b = k9;
            this.f19505c = v8;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            if (getKey() != null ? getKey().equals(entry.getKey()) : entry.getKey() == null) {
                if (getValue() == null) {
                    if (entry.getValue() == null) {
                        return true;
                    }
                } else if (getValue().equals(entry.getValue())) {
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.f19504b;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.f19505c;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            return (getKey() == null ? 0 : getKey().hashCode()) ^ (getValue() != null ? getValue().hashCode() : 0);
        }
    }

    /* renamed from: org.jivesoftware.smack.util.c$d */
    public static class d<V> {

        /* renamed from: a */
        public V f19506a;

        /* renamed from: b */
        public f f19507b;

        /* renamed from: c */
        public f f19508c;

        /* renamed from: d */
        public int f19509d = 0;

        public d(V v8) {
            this.f19506a = v8;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof d) {
                return this.f19506a.equals(((d) obj).f19506a);
            }
            return false;
        }

        public int hashCode() {
            return this.f19506a.hashCode();
        }
    }

    /* renamed from: org.jivesoftware.smack.util.c$e */
    public static class e {

        /* renamed from: a */
        public f f19510a;

        public e() {
            f fVar = new f(TtmlNode.TAG_HEAD, null, null);
            this.f19510a = fVar;
            fVar.f19511a = fVar;
            fVar.f19512b = fVar;
        }

        /* renamed from: a */
        public f m22312a(Object obj) {
            f fVar = this.f19510a;
            f fVar2 = new f(obj, fVar.f19512b, fVar);
            fVar2.f19511a.f19512b = fVar2;
            fVar2.f19512b.f19511a = fVar2;
            return fVar2;
        }

        /* renamed from: b */
        public f m22313b(f fVar) {
            f fVar2 = this.f19510a;
            fVar.f19512b = fVar2.f19512b;
            fVar.f19511a = fVar2;
            fVar2.f19512b = fVar;
            fVar.f19512b.f19511a = fVar;
            return fVar;
        }

        /* renamed from: c */
        public void m22314c() {
            f fVarM22315d = m22315d();
            while (fVarM22315d != null) {
                fVarM22315d.m22316a();
                fVarM22315d = m22315d();
            }
            f fVar = this.f19510a;
            fVar.f19511a = fVar;
            fVar.f19512b = fVar;
        }

        /* renamed from: d */
        public f m22315d() {
            f fVar = this.f19510a;
            f fVar2 = fVar.f19511a;
            if (fVar2 == fVar) {
                return null;
            }
            return fVar2;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (f fVar = this.f19510a.f19512b; fVar != this.f19510a; fVar = fVar.f19512b) {
                sb.append(fVar.toString());
                sb.append(", ");
            }
            return sb.toString();
        }
    }

    /* renamed from: org.jivesoftware.smack.util.c$f */
    public static class f {

        /* renamed from: a */
        public f f19511a;

        /* renamed from: b */
        public f f19512b;

        /* renamed from: c */
        public Object f19513c;

        /* renamed from: d */
        public long f19514d;

        public f(Object obj, f fVar, f fVar2) {
            this.f19513c = obj;
            this.f19512b = fVar;
            this.f19511a = fVar2;
        }

        /* renamed from: a */
        public void m22316a() {
            f fVar = this.f19511a;
            fVar.f19512b = this.f19512b;
            this.f19512b.f19511a = fVar;
        }

        public String toString() {
            return this.f19513c.toString();
        }
    }

    public C5609c(int i9, long j9) {
        if (i9 == 0) {
            throw new IllegalArgumentException("Max cache size cannot be 0.");
        }
        this.f19491e = i9;
        this.f19492f = j9;
        this.f19488b = new HashMap(103);
        this.f19489c = new e();
        this.f19490d = new e();
    }

    /* renamed from: a */
    public synchronized void m22307a() {
        if (this.f19491e < 0) {
            return;
        }
        if (this.f19488b.size() > this.f19491e) {
            m22308b();
            int i9 = (int) (this.f19491e * 0.9d);
            for (int size = this.f19488b.size(); size > i9; size--) {
                if (m22309c(this.f19489c.m22315d().f19513c, true) == null) {
                    f19487i.warning("Error attempting to cullCache with remove(" + this.f19489c.m22315d().f19513c.toString() + ") - cacheObject not found in cache!");
                    this.f19489c.m22315d().m22316a();
                }
            }
        }
    }

    /* renamed from: b */
    public synchronized void m22308b() {
        if (this.f19492f <= 0) {
            return;
        }
        f fVarM22315d = this.f19490d.m22315d();
        if (fVarM22315d == null) {
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis() - this.f19492f;
        while (jCurrentTimeMillis > fVarM22315d.f19514d) {
            if (m22309c(fVarM22315d.f19513c, true) == null) {
                f19487i.warning("Error attempting to remove(" + fVarM22315d.f19513c.toString() + ") - cacheObject not found in cache!");
                fVarM22315d.m22316a();
            }
            fVarM22315d = this.f19490d.m22315d();
            if (fVarM22315d == null) {
                return;
            }
        }
    }

    /* renamed from: c */
    public synchronized V m22309c(Object obj, boolean z8) {
        d<V> dVarRemove = this.f19488b.remove(obj);
        if (dVarRemove == null) {
            return null;
        }
        dVarRemove.f19507b.m22316a();
        dVarRemove.f19508c.m22316a();
        dVarRemove.f19508c = null;
        dVarRemove.f19507b = null;
        return dVarRemove.f19506a;
    }

    @Override // java.util.Map
    public synchronized void clear() {
        for (Object obj : this.f19488b.keySet().toArray()) {
            remove(obj);
        }
        this.f19488b.clear();
        this.f19489c.m22314c();
        this.f19490d.m22314c();
        this.f19493g = 0L;
        this.f19494h = 0L;
    }

    @Override // java.util.Map
    public synchronized boolean containsKey(Object obj) {
        m22308b();
        return this.f19488b.containsKey(obj);
    }

    @Override // java.util.Map
    public synchronized boolean containsValue(Object obj) {
        m22308b();
        return this.f19488b.containsValue(new d(obj));
    }

    @Override // java.util.Map
    public synchronized Set<Map.Entry<K, V>> entrySet() {
        m22308b();
        return new b();
    }

    @Override // java.util.Map
    public synchronized V get(Object obj) {
        m22308b();
        d<V> dVar = this.f19488b.get(obj);
        if (dVar == null) {
            this.f19494h++;
            return null;
        }
        dVar.f19507b.m22316a();
        this.f19489c.m22313b(dVar.f19507b);
        this.f19493g++;
        dVar.f19509d++;
        return dVar.f19506a;
    }

    @Override // java.util.Map
    public synchronized boolean isEmpty() {
        m22308b();
        return this.f19488b.isEmpty();
    }

    @Override // java.util.Map
    public synchronized Set<K> keySet() {
        m22308b();
        return Collections.unmodifiableSet(this.f19488b.keySet());
    }

    @Override // java.util.Map
    public synchronized V put(K k9, V v8) {
        V vM22309c;
        vM22309c = this.f19488b.containsKey(k9) ? m22309c(k9, true) : null;
        d<V> dVar = new d<>(v8);
        this.f19488b.put(k9, dVar);
        dVar.f19507b = this.f19489c.m22312a(k9);
        f fVarM22312a = this.f19490d.m22312a(k9);
        fVarM22312a.f19514d = System.currentTimeMillis();
        dVar.f19508c = fVarM22312a;
        m22307a();
        return vM22309c;
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            V value = entry.getValue();
            if (value instanceof d) {
                value = ((d) value).f19506a;
            }
            put(entry.getKey(), value);
        }
    }

    @Override // java.util.Map
    public synchronized V remove(Object obj) {
        return m22309c(obj, false);
    }

    @Override // java.util.Map
    public synchronized int size() {
        m22308b();
        return this.f19488b.size();
    }

    @Override // java.util.Map
    public synchronized Collection<V> values() {
        m22308b();
        return Collections.unmodifiableCollection(new a());
    }
}
