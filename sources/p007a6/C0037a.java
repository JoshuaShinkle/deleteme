package p007a6;

import java.util.Iterator;
import java.util.NoSuchElementException;
import p017b6.InterfaceC0691a;

/* renamed from: a6.a */
/* loaded from: classes2.dex */
public final class C0037a<T> implements Iterator<T>, InterfaceC0691a {

    /* renamed from: b */
    public final T[] f133b;

    /* renamed from: c */
    public int f134c;

    public C0037a(T[] tArr) {
        C0042f.m158e(tArr, "array");
        this.f133b = tArr;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f134c < this.f133b.length;
    }

    @Override // java.util.Iterator
    public T next() {
        try {
            T[] tArr = this.f133b;
            int i9 = this.f134c;
            this.f134c = i9 + 1;
            return tArr[i9];
        } catch (ArrayIndexOutOfBoundsException e9) {
            this.f134c--;
            throw new NoSuchElementException(e9.getMessage());
        }
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
