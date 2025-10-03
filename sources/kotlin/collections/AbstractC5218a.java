package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import p007a6.C0040d;
import p007a6.C0042f;
import p017b6.InterfaceC0691a;

/* renamed from: kotlin.collections.a */
/* loaded from: classes2.dex */
public abstract class AbstractC5218a<E> extends AbstractCollection<E> implements List<E> {

    /* renamed from: b */
    public static final a f17831b = new a(null);

    /* renamed from: kotlin.collections.a$a */
    public static final class a {
        public a() {
        }

        public /* synthetic */ a(C0040d c0040d) {
            this();
        }

        /* renamed from: a */
        public final void m20369a(int i9, int i10, int i11) {
            if (i9 < 0 || i10 > i11) {
                throw new IndexOutOfBoundsException("startIndex: " + i9 + ", endIndex: " + i10 + ", size: " + i11);
            }
            if (i9 <= i10) {
                return;
            }
            throw new IllegalArgumentException("startIndex: " + i9 + " > endIndex: " + i10);
        }

        /* renamed from: b */
        public final void m20370b(int i9, int i10) {
            if (i9 < 0 || i9 >= i10) {
                throw new IndexOutOfBoundsException("index: " + i9 + ", size: " + i10);
            }
        }

        /* renamed from: c */
        public final void m20371c(int i9, int i10) {
            if (i9 < 0 || i9 > i10) {
                throw new IndexOutOfBoundsException("index: " + i9 + ", size: " + i10);
            }
        }

        /* renamed from: d */
        public final void m20372d(int i9, int i10, int i11) {
            if (i9 < 0 || i10 > i11) {
                throw new IndexOutOfBoundsException("fromIndex: " + i9 + ", toIndex: " + i10 + ", size: " + i11);
            }
            if (i9 <= i10) {
                return;
            }
            throw new IllegalArgumentException("fromIndex: " + i9 + " > toIndex: " + i10);
        }

        /* renamed from: e */
        public final boolean m20373e(Collection<?> collection, Collection<?> collection2) {
            C0042f.m158e(collection, "c");
            C0042f.m158e(collection2, "other");
            if (collection.size() != collection2.size()) {
                return false;
            }
            Iterator<?> it = collection2.iterator();
            Iterator<?> it2 = collection.iterator();
            while (it2.hasNext()) {
                if (!C0042f.m154a(it2.next(), it.next())) {
                    return false;
                }
            }
            return true;
        }

        /* renamed from: f */
        public final int m20374f(Collection<?> collection) {
            C0042f.m158e(collection, "c");
            Iterator<?> it = collection.iterator();
            int iHashCode = 1;
            while (it.hasNext()) {
                Object next = it.next();
                iHashCode = (iHashCode * 31) + (next != null ? next.hashCode() : 0);
            }
            return iHashCode;
        }
    }

    /* renamed from: kotlin.collections.a$b */
    public class b implements Iterator<E>, InterfaceC0691a {

        /* renamed from: b */
        public int f17832b;

        public b() {
        }

        /* renamed from: a */
        public final int m20375a() {
            return this.f17832b;
        }

        /* renamed from: b */
        public final void m20376b(int i9) {
            this.f17832b = i9;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f17832b < AbstractC5218a.this.size();
        }

        @Override // java.util.Iterator
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            AbstractC5218a<E> abstractC5218a = AbstractC5218a.this;
            int i9 = this.f17832b;
            this.f17832b = i9 + 1;
            return abstractC5218a.get(i9);
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    /* renamed from: kotlin.collections.a$c */
    public class c extends AbstractC5218a<E>.b implements ListIterator<E> {
        public c(int i9) {
            super();
            AbstractC5218a.f17831b.m20371c(i9, AbstractC5218a.this.size());
            m20376b(i9);
        }

        @Override // java.util.ListIterator
        public void add(E e9) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.ListIterator
        public boolean hasPrevious() {
            return m20375a() > 0;
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return m20375a();
        }

        @Override // java.util.ListIterator
        public E previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            AbstractC5218a<E> abstractC5218a = AbstractC5218a.this;
            m20376b(m20375a() - 1);
            return abstractC5218a.get(m20375a());
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return m20375a() - 1;
        }

        @Override // java.util.ListIterator
        public void set(E e9) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    /* renamed from: kotlin.collections.a$d */
    public static final class d<E> extends AbstractC5218a<E> implements RandomAccess {

        /* renamed from: c */
        public final AbstractC5218a<E> f17835c;

        /* renamed from: d */
        public final int f17836d;

        /* renamed from: e */
        public int f17837e;

        /* JADX WARN: Multi-variable type inference failed */
        public d(AbstractC5218a<? extends E> abstractC5218a, int i9, int i10) {
            C0042f.m158e(abstractC5218a, "list");
            this.f17835c = abstractC5218a;
            this.f17836d = i9;
            AbstractC5218a.f17831b.m20372d(i9, i10, abstractC5218a.size());
            this.f17837e = i10 - i9;
        }

        @Override // kotlin.collections.AbstractCollection
        /* renamed from: a */
        public int mo20352a() {
            return this.f17837e;
        }

        @Override // kotlin.collections.AbstractC5218a, java.util.List
        public E get(int i9) {
            AbstractC5218a.f17831b.m20370b(i9, this.f17837e);
            return this.f17835c.get(this.f17836d + i9);
        }
    }

    @Override // java.util.List
    public void add(int i9, E e9) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public boolean addAll(int i9, Collection<? extends E> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection, java.util.List
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof List) {
            return f17831b.m20373e(this, (Collection) obj);
        }
        return false;
    }

    @Override // java.util.List
    public abstract E get(int i9);

    @Override // java.util.Collection, java.util.List
    public int hashCode() {
        return f17831b.m20374f(this);
    }

    @Override // java.util.List
    public int indexOf(E e9) {
        Iterator<E> it = iterator();
        int i9 = 0;
        while (it.hasNext()) {
            if (C0042f.m154a(it.next(), e9)) {
                return i9;
            }
            i9++;
        }
        return -1;
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.List
    public Iterator<E> iterator() {
        return new b();
    }

    @Override // java.util.List
    public int lastIndexOf(E e9) {
        ListIterator<E> listIterator = listIterator(size());
        while (listIterator.hasPrevious()) {
            if (C0042f.m154a(listIterator.previous(), e9)) {
                return listIterator.nextIndex();
            }
        }
        return -1;
    }

    @Override // java.util.List
    public ListIterator<E> listIterator() {
        return new c(0);
    }

    @Override // java.util.List
    public E remove(int i9) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public E set(int i9, E e9) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public List<E> subList(int i9, int i10) {
        return new d(this, i9, i10);
    }

    @Override // java.util.List
    public ListIterator<E> listIterator(int i9) {
        return new c(i9);
    }
}
