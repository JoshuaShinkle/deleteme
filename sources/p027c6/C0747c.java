package p027c6;

import p007a6.C0040d;

/* renamed from: c6.c */
/* loaded from: classes2.dex */
public final class C0747c extends C0745a {

    /* renamed from: f */
    public static final a f3494f = new a(null);

    /* renamed from: g */
    public static final C0747c f3495g = new C0747c(1, 0);

    /* renamed from: c6.c$a */
    public static final class a {
        public a() {
        }

        public /* synthetic */ a(C0040d c0040d) {
            this();
        }

        /* renamed from: a */
        public final C0747c m3619a() {
            return C0747c.f3495g;
        }
    }

    public C0747c(int i9, int i10) {
        super(i9, i10, 1);
    }

    @Override // p027c6.C0745a
    public boolean equals(Object obj) {
        if (obj instanceof C0747c) {
            if (!isEmpty() || !((C0747c) obj).isEmpty()) {
                C0747c c0747c = (C0747c) obj;
                if (m3610a() != c0747c.m3610a() || m3611b() != c0747c.m3611b()) {
                }
            }
            return true;
        }
        return false;
    }

    /* renamed from: f */
    public boolean m3616f(int i9) {
        return m3610a() <= i9 && i9 <= m3611b();
    }

    /* renamed from: g */
    public Integer m3617g() {
        return Integer.valueOf(m3611b());
    }

    /* renamed from: h */
    public Integer m3618h() {
        return Integer.valueOf(m3610a());
    }

    @Override // p027c6.C0745a
    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (m3610a() * 31) + m3611b();
    }

    @Override // p027c6.C0745a
    public boolean isEmpty() {
        return m3610a() > m3611b();
    }

    @Override // p027c6.C0745a
    public String toString() {
        return m3610a() + ".." + m3611b();
    }
}
