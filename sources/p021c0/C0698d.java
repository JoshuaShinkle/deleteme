package p021c0;

import org.apache.commons.lang3.StringUtils;

/* renamed from: c0.d */
/* loaded from: classes.dex */
public class C0698d<F, S> {

    /* renamed from: a */
    public final F f3353a;

    /* renamed from: b */
    public final S f3354b;

    public C0698d(F f9, S s8) {
        this.f3353a = f9;
        this.f3354b = s8;
    }

    /* renamed from: a */
    public static <A, B> C0698d<A, B> m3463a(A a9, B b9) {
        return new C0698d<>(a9, b9);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof C0698d)) {
            return false;
        }
        C0698d c0698d = (C0698d) obj;
        return C0697c.m3461a(c0698d.f3353a, this.f3353a) && C0697c.m3461a(c0698d.f3354b, this.f3354b);
    }

    public int hashCode() {
        F f9 = this.f3353a;
        int iHashCode = f9 == null ? 0 : f9.hashCode();
        S s8 = this.f3354b;
        return iHashCode ^ (s8 != null ? s8.hashCode() : 0);
    }

    public String toString() {
        return "Pair{" + String.valueOf(this.f3353a) + StringUtils.SPACE + String.valueOf(this.f3354b) + "}";
    }
}
