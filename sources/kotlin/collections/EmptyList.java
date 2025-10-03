package kotlin.collections;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import org.apache.commons.lang3.ClassUtils;
import p007a6.C0039c;
import p007a6.C0042f;
import p017b6.InterfaceC0691a;

/* loaded from: classes2.dex */
public final class EmptyList implements List, Serializable, RandomAccess, InterfaceC0691a {

    /* renamed from: b */
    public static final EmptyList f17828b = new EmptyList();
    private static final long serialVersionUID = -7390468764508069838L;

    private EmptyList() {
    }

    private final Object readResolve() {
        return f17828b;
    }

    /* renamed from: a */
    public boolean m20355a(Void r22) {
        C0042f.m158e(r22, "element");
        return false;
    }

    @Override // java.util.List
    public /* bridge */ /* synthetic */ void add(int i9, Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public /* bridge */ /* synthetic */ boolean add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public boolean addAll(int i9, Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public boolean addAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public Void get(int i9) {
        throw new IndexOutOfBoundsException("Empty list doesn't contain element at index " + i9 + ClassUtils.PACKAGE_SEPARATOR_CHAR);
    }

    /* renamed from: c */
    public int m20357c() {
        return 0;
    }

    @Override // java.util.List, java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof Void) {
            return m20355a((Void) obj);
        }
        return false;
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(Collection collection) {
        C0042f.m158e(collection, "elements");
        return collection.isEmpty();
    }

    /* renamed from: d */
    public int m20358d(Void r22) {
        C0042f.m158e(r22, "element");
        return -1;
    }

    /* renamed from: e */
    public int m20359e(Void r22) {
        C0042f.m158e(r22, "element");
        return -1;
    }

    @Override // java.util.List, java.util.Collection
    public boolean equals(Object obj) {
        return (obj instanceof List) && ((List) obj).isEmpty();
    }

    @Override // java.util.List, java.util.Collection
    public int hashCode() {
        return 1;
    }

    @Override // java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof Void) {
            return m20358d((Void) obj);
        }
        return -1;
    }

    @Override // java.util.List, java.util.Collection
    public boolean isEmpty() {
        return true;
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator iterator() {
        return C5235r.f17841b;
    }

    @Override // java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof Void) {
            return m20359e((Void) obj);
        }
        return -1;
    }

    @Override // java.util.List
    public ListIterator listIterator() {
        return C5235r.f17841b;
    }

    @Override // java.util.List
    public /* bridge */ /* synthetic */ Object remove(int i9) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public boolean removeAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public /* bridge */ /* synthetic */ Object set(int i9, Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public final /* bridge */ int size() {
        return m20357c();
    }

    @Override // java.util.List
    public List subList(int i9, int i10) {
        if (i9 == 0 && i10 == 0) {
            return this;
        }
        throw new IndexOutOfBoundsException("fromIndex: " + i9 + ", toIndex: " + i10);
    }

    @Override // java.util.List, java.util.Collection
    public Object[] toArray() {
        return C0039c.m152a(this);
    }

    @Override // java.util.List, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        C0042f.m158e(tArr, "array");
        return (T[]) C0039c.m153b(this, tArr);
    }

    public String toString() {
        return "[]";
    }

    @Override // java.util.List
    public ListIterator listIterator(int i9) {
        if (i9 == 0) {
            return C5235r.f17841b;
        }
        throw new IndexOutOfBoundsException("Index: " + i9);
    }
}
