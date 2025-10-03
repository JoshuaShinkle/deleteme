package p058e6;

import java.util.Iterator;
import p007a6.C0042f;
import p017b6.InterfaceC0691a;
import p257z5.InterfaceC6832b;

/* renamed from: e6.i */
/* loaded from: classes2.dex */
public final class C4767i<T, R> implements InterfaceC4761c<R> {

    /* renamed from: a */
    public final InterfaceC4761c<T> f16561a;

    /* renamed from: b */
    public final InterfaceC6832b<T, R> f16562b;

    /* renamed from: e6.i$a */
    public static final class a implements Iterator<R>, InterfaceC0691a {

        /* renamed from: b */
        public final Iterator<T> f16563b;

        /* renamed from: c */
        public final /* synthetic */ C4767i<T, R> f16564c;

        public a(C4767i<T, R> c4767i) {
            this.f16564c = c4767i;
            this.f16563b = c4767i.f16561a.iterator();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f16563b.hasNext();
        }

        @Override // java.util.Iterator
        public R next() {
            return (R) this.f16564c.f16562b.mo20353b(this.f16563b.next());
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public C4767i(InterfaceC4761c<? extends T> interfaceC4761c, InterfaceC6832b<? super T, ? extends R> interfaceC6832b) {
        C0042f.m158e(interfaceC4761c, "sequence");
        C0042f.m158e(interfaceC6832b, "transformer");
        this.f16561a = interfaceC4761c;
        this.f16562b = interfaceC6832b;
    }

    @Override // p058e6.InterfaceC4761c
    public Iterator<R> iterator() {
        return new a(this);
    }
}
