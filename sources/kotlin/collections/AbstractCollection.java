package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import p007a6.C0039c;
import p007a6.C0042f;
import p017b6.InterfaceC0691a;
import p257z5.InterfaceC6832b;

/* loaded from: classes2.dex */
public abstract class AbstractCollection<E> implements Collection<E>, InterfaceC0691a {
    /* renamed from: a */
    public abstract int mo20352a();

    @Override // java.util.Collection
    public boolean add(E e9) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends E> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean contains(E e9) {
        if (isEmpty()) {
            return false;
        }
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            if (C0042f.m154a(it.next(), e9)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
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
        return size() == 0;
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
        return mo20352a();
    }

    @Override // java.util.Collection
    public Object[] toArray() {
        return C0039c.m152a(this);
    }

    public String toString() {
        return C5234q.m20429y(this, ", ", "[", "]", 0, null, new InterfaceC6832b<E, CharSequence>(this) { // from class: kotlin.collections.AbstractCollection.toString.1
            final /* synthetic */ AbstractCollection<E> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
                this.this$0 = this;
            }

            @Override // p257z5.InterfaceC6832b
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public final CharSequence mo20353b(E e9) {
                return e9 == this.this$0 ? "(this Collection)" : String.valueOf(e9);
            }
        }, 24, null);
    }

    @Override // java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        C0042f.m158e(tArr, "array");
        return (T[]) C0039c.m153b(this, tArr);
    }
}
