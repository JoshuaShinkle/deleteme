package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import p007a6.C0038b;
import p007a6.C0039c;
import p007a6.C0042f;
import p017b6.InterfaceC0691a;

/* renamed from: kotlin.collections.b */
/* loaded from: classes2.dex */
public final class C5219b<T> implements Collection<T>, InterfaceC0691a {

    /* renamed from: b */
    public final T[] f17838b;

    /* renamed from: c */
    public final boolean f17839c;

    public C5219b(T[] tArr, boolean z8) {
        C0042f.m158e(tArr, "values");
        this.f17838b = tArr;
        this.f17839c = z8;
    }

    /* renamed from: a */
    public int m20377a() {
        return this.f17838b.length;
    }

    @Override // java.util.Collection
    public boolean add(T t8) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends T> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean contains(Object obj) {
        return C5223f.m20385h(this.f17838b, obj);
    }

    @Override // java.util.Collection
    public boolean containsAll(Collection<? extends Object> collection) {
        C0042f.m158e(collection, "elements");
        Collection<? extends Object> collection2 = collection;
        if (collection2.isEmpty()) {
            return true;
        }
        Iterator<T> it = collection2.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        return this.f17838b.length == 0;
    }

    @Override // java.util.Collection, java.lang.Iterable
    public Iterator<T> iterator() {
        return C0038b.m151a(this.f17838b);
    }

    @Override // java.util.Collection
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean removeAll(Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean retainAll(Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final /* bridge */ int size() {
        return m20377a();
    }

    @Override // java.util.Collection
    public final Object[] toArray() {
        return C5225h.m20395a(this.f17838b, this.f17839c);
    }

    @Override // java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        C0042f.m158e(tArr, "array");
        return (T[]) C0039c.m153b(this, tArr);
    }
}
