package p058e6;

import java.util.Iterator;
import org.apache.commons.lang3.ClassUtils;
import p007a6.C0042f;
import p017b6.InterfaceC0691a;

/* renamed from: e6.a */
/* loaded from: classes2.dex */
public final class C4759a<T> implements InterfaceC4761c<T>, InterfaceC4760b<T> {

    /* renamed from: a */
    public final InterfaceC4761c<T> f16556a;

    /* renamed from: b */
    public final int f16557b;

    /* renamed from: e6.a$a */
    public static final class a implements Iterator<T>, InterfaceC0691a {

        /* renamed from: b */
        public final Iterator<T> f16558b;

        /* renamed from: c */
        public int f16559c;

        public a(C4759a<T> c4759a) {
            this.f16558b = c4759a.f16556a.iterator();
            this.f16559c = c4759a.f16557b;
        }

        /* renamed from: a */
        public final void m18894a() {
            while (this.f16559c > 0 && this.f16558b.hasNext()) {
                this.f16558b.next();
                this.f16559c--;
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            m18894a();
            return this.f16558b.hasNext();
        }

        @Override // java.util.Iterator
        public T next() {
            m18894a();
            return this.f16558b.next();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public C4759a(InterfaceC4761c<? extends T> interfaceC4761c, int i9) {
        C0042f.m158e(interfaceC4761c, "sequence");
        this.f16556a = interfaceC4761c;
        this.f16557b = i9;
        if (i9 >= 0) {
            return;
        }
        throw new IllegalArgumentException(("count must be non-negative, but was " + i9 + ClassUtils.PACKAGE_SEPARATOR_CHAR).toString());
    }

    @Override // p058e6.InterfaceC4760b
    /* renamed from: a */
    public InterfaceC4761c<T> mo18893a(int i9) {
        int i10 = this.f16557b + i9;
        return i10 < 0 ? new C4759a(this, i9) : new C4759a(this.f16556a, i10);
    }

    @Override // p058e6.InterfaceC4761c
    public Iterator<T> iterator() {
        return new a(this);
    }
}
