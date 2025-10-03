package p087h5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/* renamed from: h5.c */
/* loaded from: classes2.dex */
public final class C5025c {

    /* renamed from: h5.c$b */
    public static class b<E> implements Iterable<E> {

        /* renamed from: b */
        public final List<Iterable<? extends E>> f17344b;

        @Override // java.lang.Iterable
        public Iterator<E> iterator() {
            return new c(this.f17344b);
        }

        public b(Iterable<? extends Iterable<? extends E>> iterable) {
            ArrayList arrayList = new ArrayList();
            this.f17344b = arrayList;
            C5025c.m19594a(arrayList, iterable);
        }
    }

    /* renamed from: h5.c$c */
    public static class c<E> implements Iterator<E> {

        /* renamed from: b */
        public final Iterator<? extends Iterable<? extends E>> f17345b;

        /* renamed from: c */
        public Iterator<? extends E> f17346c;

        public c(Iterable<? extends Iterable<? extends E>> iterable) {
            this.f17345b = iterable.iterator();
            m19598a();
        }

        /* renamed from: a */
        public final void m19598a() {
            while (this.f17345b.hasNext()) {
                Iterator<? extends E> it = this.f17345b.next().iterator();
                this.f17346c = it;
                if (it.hasNext()) {
                    break;
                }
            }
            Iterator<? extends E> it2 = this.f17346c;
            if (it2 == null || it2.hasNext()) {
                return;
            }
            this.f17346c = null;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            Iterator<? extends E> it = this.f17346c;
            return it != null && it.hasNext();
        }

        @Override // java.util.Iterator
        public E next() {
            Iterator<? extends E> it = this.f17346c;
            if (it == null) {
                throw new NoSuchElementException();
            }
            E next = it.next();
            if (!this.f17346c.hasNext()) {
                m19598a();
            }
            return next;
        }

        @Override // java.util.Iterator
        public void remove() {
            Iterator<? extends E> it = this.f17346c;
            if (it == null) {
                throw new IllegalStateException();
            }
            it.remove();
            if (this.f17346c.hasNext()) {
                return;
            }
            m19598a();
        }
    }

    /* renamed from: h5.c$d */
    public static class d<E> implements Iterable<E> {

        /* renamed from: b */
        public final Iterable<? extends E> f17347b;

        public d(Iterable<? extends E> iterable) {
            this.f17347b = iterable;
        }

        @Override // java.lang.Iterable
        public Iterator<E> iterator() {
            return new e(this.f17347b.iterator());
        }
    }

    /* renamed from: h5.c$e */
    public static class e<E> implements Iterator<E> {

        /* renamed from: b */
        public final Iterator<? extends E> f17348b;

        /* renamed from: c */
        public E f17349c;

        public e(Iterator<? extends E> it) {
            this.f17348b = it;
            m19599a();
        }

        /* renamed from: a */
        public final void m19599a() {
            while (this.f17348b.hasNext()) {
                E next = this.f17348b.next();
                this.f17349c = next;
                if (next != null) {
                    return;
                }
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f17349c != null;
        }

        @Override // java.util.Iterator
        public E next() {
            E e9 = this.f17349c;
            if (e9 == null) {
                throw new NoSuchElementException();
            }
            this.f17349c = null;
            m19599a();
            return e9;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /* renamed from: a */
    public static <E> void m19594a(Collection<? super E> collection, Iterable<? extends E> iterable) {
        if (iterable instanceof Collection) {
            collection.addAll((Collection) iterable);
            return;
        }
        Iterator<? extends E> it = iterable.iterator();
        while (it.hasNext()) {
            collection.add(it.next());
        }
    }

    /* renamed from: b */
    public static <E> b<E> m19595b(Iterable<? extends Iterable<? extends E>> iterable) {
        return new b<>(iterable);
    }

    @SafeVarargs
    /* renamed from: c */
    public static <E> b<E> m19596c(Iterable<? extends E>... iterableArr) {
        return m19595b(Arrays.asList(iterableArr));
    }

    /* renamed from: d */
    public static <E> Iterable<E> m19597d(Iterable<E> iterable) {
        return iterable instanceof d ? iterable : new d(iterable);
    }
}
