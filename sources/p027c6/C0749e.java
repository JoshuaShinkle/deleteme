package p027c6;

import org.apache.commons.lang3.ClassUtils;
import p007a6.C0042f;
import p027c6.C0745a;

/* renamed from: c6.e */
/* loaded from: classes2.dex */
public class C0749e extends C0748d {
    /* renamed from: b */
    public static final int m3621b(int i9, int i10) {
        return i9 < i10 ? i10 : i9;
    }

    /* renamed from: c */
    public static final int m3622c(int i9, int i10) {
        return i9 > i10 ? i10 : i9;
    }

    /* renamed from: d */
    public static final int m3623d(int i9, int i10, int i11) {
        if (i10 <= i11) {
            return i9 < i10 ? i10 : i9 > i11 ? i11 : i9;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + i11 + " is less than minimum " + i10 + ClassUtils.PACKAGE_SEPARATOR_CHAR);
    }

    /* renamed from: e */
    public static final C0745a m3624e(int i9, int i10) {
        return C0745a.f3486e.m3614a(i9, i10, -1);
    }

    /* renamed from: f */
    public static final C0745a m3625f(C0745a c0745a, int i9) {
        C0042f.m158e(c0745a, "<this>");
        C0748d.m3620a(i9 > 0, Integer.valueOf(i9));
        C0745a.a aVar = C0745a.f3486e;
        int iM3610a = c0745a.m3610a();
        int iM3611b = c0745a.m3611b();
        if (c0745a.m3612c() <= 0) {
            i9 = -i9;
        }
        return aVar.m3614a(iM3610a, iM3611b, i9);
    }

    /* renamed from: g */
    public static final C0747c m3626g(int i9, int i10) {
        return i10 <= Integer.MIN_VALUE ? C0747c.f3494f.m3619a() : new C0747c(i9, i10 - 1);
    }
}
