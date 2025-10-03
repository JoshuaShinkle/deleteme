package p132m;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/* renamed from: m.f */
/* loaded from: classes.dex */
public abstract class AbstractC5307f<K, V> {

    /* renamed from: a */
    public AbstractC5307f<K, V>.b f18022a;

    /* renamed from: b */
    public AbstractC5307f<K, V>.c f18023b;

    /* renamed from: c */
    public AbstractC5307f<K, V>.e f18024c;

    /* renamed from: m.f$a */
    public final class a<T> implements Iterator<T> {

        /* renamed from: b */
        public final int f18025b;

        /* renamed from: c */
        public int f18026c;

        /* renamed from: d */
        public int f18027d;

        /* renamed from: e */
        public boolean f18028e = false;

        public a(int i9) {
            this.f18025b = i9;
            this.f18026c = AbstractC5307f.this.mo20698d();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f18027d < this.f18026c;
        }

        @Override // java.util.Iterator
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T t8 = (T) AbstractC5307f.this.mo20696b(this.f18027d, this.f18025b);
            this.f18027d++;
            this.f18028e = true;
            return t8;
        }

        @Override // java.util.Iterator
        public void remove() {
            if (!this.f18028e) {
                throw new IllegalStateException();
            }
            int i9 = this.f18027d - 1;
            this.f18027d = i9;
            this.f18026c--;
            this.f18028e = false;
            AbstractC5307f.this.mo20702h(i9);
        }
    }

    /* renamed from: m.f$b */
    public final class b implements Set<Map.Entry<K, V>> {
        public b() {
        }

        @Override // java.util.Set, java.util.Collection
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public boolean add(Map.Entry<K, V> entry) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean addAll(Collection<? extends Map.Entry<K, V>> collection) {
            int iMo20698d = AbstractC5307f.this.mo20698d();
            for (Map.Entry<K, V> entry : collection) {
                AbstractC5307f.this.mo20701g(entry.getKey(), entry.getValue());
            }
            return iMo20698d != AbstractC5307f.this.mo20698d();
        }

        @Override // java.util.Set, java.util.Collection
        public void clear() {
            AbstractC5307f.this.mo20695a();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            int iMo20699e = AbstractC5307f.this.mo20699e(entry.getKey());
            if (iMo20699e < 0) {
                return false;
            }
            return C5304c.m20714c(AbstractC5307f.this.mo20696b(iMo20699e, 1), entry.getValue());
        }

        @Override // java.util.Set, java.util.Collection
        public boolean containsAll(Collection<?> collection) {
            Iterator<?> it = collection.iterator();
            while (it.hasNext()) {
                if (!contains(it.next())) {
                    return false;
                }
            }
            return true;
        }

        @Override // java.util.Set, java.util.Collection
        public boolean equals(Object obj) {
            return AbstractC5307f.m20733k(this, obj);
        }

        @Override // java.util.Set, java.util.Collection
        public int hashCode() {
            int iHashCode = 0;
            for (int iMo20698d = AbstractC5307f.this.mo20698d() - 1; iMo20698d >= 0; iMo20698d--) {
                Object objMo20696b = AbstractC5307f.this.mo20696b(iMo20698d, 0);
                Object objMo20696b2 = AbstractC5307f.this.mo20696b(iMo20698d, 1);
                iHashCode += (objMo20696b == null ? 0 : objMo20696b.hashCode()) ^ (objMo20696b2 == null ? 0 : objMo20696b2.hashCode());
            }
            return iHashCode;
        }

        @Override // java.util.Set, java.util.Collection
        public boolean isEmpty() {
            return AbstractC5307f.this.mo20698d() == 0;
        }

        @Override // java.util.Set, java.util.Collection, java.lang.Iterable
        public Iterator<Map.Entry<K, V>> iterator() {
            return new d();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean remove(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean removeAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean retainAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public int size() {
            return AbstractC5307f.this.mo20698d();
        }

        @Override // java.util.Set, java.util.Collection
        public Object[] toArray() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public <T> T[] toArray(T[] tArr) {
            throw new UnsupportedOperationException();
        }
    }

    /* renamed from: m.f$c */
    public final class c implements Set<K> {
        public c() {
        }

        @Override // java.util.Set, java.util.Collection
        public boolean add(K k9) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean addAll(Collection<? extends K> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public void clear() {
            AbstractC5307f.this.mo20695a();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean contains(Object obj) {
            return AbstractC5307f.this.mo20699e(obj) >= 0;
        }

        @Override // java.util.Set, java.util.Collection
        public boolean containsAll(Collection<?> collection) {
            return AbstractC5307f.m20732j(AbstractC5307f.this.mo20697c(), collection);
        }

        @Override // java.util.Set, java.util.Collection
        public boolean equals(Object obj) {
            return AbstractC5307f.m20733k(this, obj);
        }

        @Override // java.util.Set, java.util.Collection
        public int hashCode() {
            int iHashCode = 0;
            for (int iMo20698d = AbstractC5307f.this.mo20698d() - 1; iMo20698d >= 0; iMo20698d--) {
                Object objMo20696b = AbstractC5307f.this.mo20696b(iMo20698d, 0);
                iHashCode += objMo20696b == null ? 0 : objMo20696b.hashCode();
            }
            return iHashCode;
        }

        @Override // java.util.Set, java.util.Collection
        public boolean isEmpty() {
            return AbstractC5307f.this.mo20698d() == 0;
        }

        @Override // java.util.Set, java.util.Collection, java.lang.Iterable
        public Iterator<K> iterator() {
            return new a(0);
        }

        @Override // java.util.Set, java.util.Collection
        public boolean remove(Object obj) {
            int iMo20699e = AbstractC5307f.this.mo20699e(obj);
            if (iMo20699e < 0) {
                return false;
            }
            AbstractC5307f.this.mo20702h(iMo20699e);
            return true;
        }

        @Override // java.util.Set, java.util.Collection
        public boolean removeAll(Collection<?> collection) {
            return AbstractC5307f.m20734o(AbstractC5307f.this.mo20697c(), collection);
        }

        @Override // java.util.Set, java.util.Collection
        public boolean retainAll(Collection<?> collection) {
            return AbstractC5307f.m20735p(AbstractC5307f.this.mo20697c(), collection);
        }

        @Override // java.util.Set, java.util.Collection
        public int size() {
            return AbstractC5307f.this.mo20698d();
        }

        @Override // java.util.Set, java.util.Collection
        public Object[] toArray() {
            return AbstractC5307f.this.m20739q(0);
        }

        @Override // java.util.Set, java.util.Collection
        public <T> T[] toArray(T[] tArr) {
            return (T[]) AbstractC5307f.this.m20740r(tArr, 0);
        }
    }

    /* renamed from: m.f$d */
    public final class d implements Iterator<Map.Entry<K, V>>, Map.Entry<K, V> {

        /* renamed from: b */
        public int f18032b;

        /* renamed from: d */
        public boolean f18034d = false;

        /* renamed from: c */
        public int f18033c = -1;

        public d() {
            this.f18032b = AbstractC5307f.this.mo20698d() - 1;
        }

        @Override // java.util.Iterator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Map.Entry<K, V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            this.f18033c++;
            this.f18034d = true;
            return this;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (!this.f18034d) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            return C5304c.m20714c(entry.getKey(), AbstractC5307f.this.mo20696b(this.f18033c, 0)) && C5304c.m20714c(entry.getValue(), AbstractC5307f.this.mo20696b(this.f18033c, 1));
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            if (this.f18034d) {
                return (K) AbstractC5307f.this.mo20696b(this.f18033c, 0);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            if (this.f18034d) {
                return (V) AbstractC5307f.this.mo20696b(this.f18033c, 1);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f18033c < this.f18032b;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            if (!this.f18034d) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }
            Object objMo20696b = AbstractC5307f.this.mo20696b(this.f18033c, 0);
            Object objMo20696b2 = AbstractC5307f.this.mo20696b(this.f18033c, 1);
            return (objMo20696b == null ? 0 : objMo20696b.hashCode()) ^ (objMo20696b2 != null ? objMo20696b2.hashCode() : 0);
        }

        @Override // java.util.Iterator
        public void remove() {
            if (!this.f18034d) {
                throw new IllegalStateException();
            }
            AbstractC5307f.this.mo20702h(this.f18033c);
            this.f18033c--;
            this.f18032b--;
            this.f18034d = false;
        }

        @Override // java.util.Map.Entry
        public V setValue(V v8) {
            if (this.f18034d) {
                return (V) AbstractC5307f.this.mo20703i(this.f18033c, v8);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public String toString() {
            return getKey() + "=" + getValue();
        }
    }

    /* renamed from: m.f$e */
    public final class e implements Collection<V> {
        public e() {
        }

        @Override // java.util.Collection
        public boolean add(V v8) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public boolean addAll(Collection<? extends V> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public void clear() {
            AbstractC5307f.this.mo20695a();
        }

        @Override // java.util.Collection
        public boolean contains(Object obj) {
            return AbstractC5307f.this.mo20700f(obj) >= 0;
        }

        @Override // java.util.Collection
        public boolean containsAll(Collection<?> collection) {
            Iterator<?> it = collection.iterator();
            while (it.hasNext()) {
                if (!contains(it.next())) {
                    return false;
                }
            }
            return true;
        }

        @Override // java.util.Collection
        public boolean isEmpty() {
            return AbstractC5307f.this.mo20698d() == 0;
        }

        @Override // java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return new a(1);
        }

        @Override // java.util.Collection
        public boolean remove(Object obj) {
            int iMo20700f = AbstractC5307f.this.mo20700f(obj);
            if (iMo20700f < 0) {
                return false;
            }
            AbstractC5307f.this.mo20702h(iMo20700f);
            return true;
        }

        @Override // java.util.Collection
        public boolean removeAll(Collection<?> collection) {
            int iMo20698d = AbstractC5307f.this.mo20698d();
            int i9 = 0;
            boolean z8 = false;
            while (i9 < iMo20698d) {
                if (collection.contains(AbstractC5307f.this.mo20696b(i9, 1))) {
                    AbstractC5307f.this.mo20702h(i9);
                    i9--;
                    iMo20698d--;
                    z8 = true;
                }
                i9++;
            }
            return z8;
        }

        @Override // java.util.Collection
        public boolean retainAll(Collection<?> collection) {
            int iMo20698d = AbstractC5307f.this.mo20698d();
            int i9 = 0;
            boolean z8 = false;
            while (i9 < iMo20698d) {
                if (!collection.contains(AbstractC5307f.this.mo20696b(i9, 1))) {
                    AbstractC5307f.this.mo20702h(i9);
                    i9--;
                    iMo20698d--;
                    z8 = true;
                }
                i9++;
            }
            return z8;
        }

        @Override // java.util.Collection
        public int size() {
            return AbstractC5307f.this.mo20698d();
        }

        @Override // java.util.Collection
        public Object[] toArray() {
            return AbstractC5307f.this.m20739q(1);
        }

        @Override // java.util.Collection
        public <T> T[] toArray(T[] tArr) {
            return (T[]) AbstractC5307f.this.m20740r(tArr, 1);
        }
    }

    /* renamed from: j */
    public static <K, V> boolean m20732j(Map<K, V> map, Collection<?> collection) {
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            if (!map.containsKey(it.next())) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: k */
    public static <T> boolean m20733k(Set<T> set, Object obj) {
        if (set == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set2 = (Set) obj;
            try {
                if (set.size() == set2.size()) {
                    if (set.containsAll(set2)) {
                        return true;
                    }
                }
                return false;
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    /* renamed from: o */
    public static <K, V> boolean m20734o(Map<K, V> map, Collection<?> collection) {
        int size = map.size();
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            map.remove(it.next());
        }
        return size != map.size();
    }

    /* renamed from: p */
    public static <K, V> boolean m20735p(Map<K, V> map, Collection<?> collection) {
        int size = map.size();
        Iterator<K> it = map.keySet().iterator();
        while (it.hasNext()) {
            if (!collection.contains(it.next())) {
                it.remove();
            }
        }
        return size != map.size();
    }

    /* renamed from: a */
    public abstract void mo20695a();

    /* renamed from: b */
    public abstract Object mo20696b(int i9, int i10);

    /* renamed from: c */
    public abstract Map<K, V> mo20697c();

    /* renamed from: d */
    public abstract int mo20698d();

    /* renamed from: e */
    public abstract int mo20699e(Object obj);

    /* renamed from: f */
    public abstract int mo20700f(Object obj);

    /* renamed from: g */
    public abstract void mo20701g(K k9, V v8);

    /* renamed from: h */
    public abstract void mo20702h(int i9);

    /* renamed from: i */
    public abstract V mo20703i(int i9, V v8);

    /* renamed from: l */
    public Set<Map.Entry<K, V>> m20736l() {
        if (this.f18022a == null) {
            this.f18022a = new b();
        }
        return this.f18022a;
    }

    /* renamed from: m */
    public Set<K> m20737m() {
        if (this.f18023b == null) {
            this.f18023b = new c();
        }
        return this.f18023b;
    }

    /* renamed from: n */
    public Collection<V> m20738n() {
        if (this.f18024c == null) {
            this.f18024c = new e();
        }
        return this.f18024c;
    }

    /* renamed from: q */
    public Object[] m20739q(int i9) {
        int iMo20698d = mo20698d();
        Object[] objArr = new Object[iMo20698d];
        for (int i10 = 0; i10 < iMo20698d; i10++) {
            objArr[i10] = mo20696b(i10, i9);
        }
        return objArr;
    }

    /* renamed from: r */
    public <T> T[] m20740r(T[] tArr, int i9) {
        int iMo20698d = mo20698d();
        if (tArr.length < iMo20698d) {
            tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), iMo20698d));
        }
        for (int i10 = 0; i10 < iMo20698d; i10++) {
            tArr[i10] = mo20696b(i10, i9);
        }
        if (tArr.length > iMo20698d) {
            tArr[iMo20698d] = null;
        }
        return tArr;
    }
}
