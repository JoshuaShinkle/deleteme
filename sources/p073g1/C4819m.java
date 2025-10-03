package p073g1;

import java.util.Queue;
import p226w1.C6513f;
import p226w1.C6517j;

/* renamed from: g1.m */
/* loaded from: classes.dex */
public class C4819m<A, B> {

    /* renamed from: a */
    public final C6513f<b<A>, B> f16770a;

    /* renamed from: g1.m$a */
    public class a extends C6513f<b<A>, B> {
        public a(long j9) {
            super(j9);
        }

        @Override // p226w1.C6513f
        /* renamed from: n, reason: merged with bridge method [inline-methods] */
        public void mo18615j(b<A> bVar, B b9) {
            bVar.m19125c();
        }
    }

    /* renamed from: g1.m$b */
    public static final class b<A> {

        /* renamed from: d */
        public static final Queue<b<?>> f16772d = C6517j.m24945f(0);

        /* renamed from: a */
        public int f16773a;

        /* renamed from: b */
        public int f16774b;

        /* renamed from: c */
        public A f16775c;

        /* renamed from: a */
        public static <A> b<A> m19123a(A a9, int i9, int i10) {
            b<A> bVar;
            Queue<b<?>> queue = f16772d;
            synchronized (queue) {
                bVar = (b) queue.poll();
            }
            if (bVar == null) {
                bVar = new b<>();
            }
            bVar.m19124b(a9, i9, i10);
            return bVar;
        }

        /* renamed from: b */
        public final void m19124b(A a9, int i9, int i10) {
            this.f16775c = a9;
            this.f16774b = i9;
            this.f16773a = i10;
        }

        /* renamed from: c */
        public void m19125c() {
            Queue<b<?>> queue = f16772d;
            synchronized (queue) {
                queue.offer(this);
            }
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            return this.f16774b == bVar.f16774b && this.f16773a == bVar.f16773a && this.f16775c.equals(bVar.f16775c);
        }

        public int hashCode() {
            return (((this.f16773a * 31) + this.f16774b) * 31) + this.f16775c.hashCode();
        }
    }

    public C4819m(long j9) {
        this.f16770a = new a(j9);
    }

    /* renamed from: a */
    public B m19120a(A a9, int i9, int i10) {
        b<A> bVarM19123a = b.m19123a(a9, i9, i10);
        B bM24926g = this.f16770a.m24926g(bVarM19123a);
        bVarM19123a.m19125c();
        return bM24926g;
    }

    /* renamed from: b */
    public void m19121b(A a9, int i9, int i10, B b9) {
        this.f16770a.m24928k(b.m19123a(a9, i9, i10), b9);
    }
}
