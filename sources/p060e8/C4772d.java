package p060e8;

/* renamed from: e8.d */
/* loaded from: classes.dex */
public final class C4772d {
    /* renamed from: a */
    public static void m18995a(String str) {
        throw new IllegalArgumentException(str);
    }

    /* renamed from: b */
    public static void m18996b(boolean z8) {
        if (z8) {
            throw new IllegalArgumentException("Must be false");
        }
    }

    /* renamed from: c */
    public static void m18997c(boolean z8, String str) {
        if (z8) {
            throw new IllegalArgumentException(str);
        }
    }

    /* renamed from: d */
    public static void m18998d(boolean z8) {
        if (!z8) {
            throw new IllegalArgumentException("Must be true");
        }
    }

    /* renamed from: e */
    public static void m18999e(boolean z8, String str) {
        if (!z8) {
            throw new IllegalArgumentException(str);
        }
    }

    /* renamed from: f */
    public static void m19000f(Object[] objArr) {
        m19001g(objArr, "Array must not contain any null objects");
    }

    /* renamed from: g */
    public static void m19001g(Object[] objArr, String str) {
        for (Object obj : objArr) {
            if (obj == null) {
                throw new IllegalArgumentException(str);
            }
        }
    }

    /* renamed from: h */
    public static void m19002h(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("String must not be empty");
        }
    }

    /* renamed from: i */
    public static void m19003i(String str, String str2) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException(str2);
        }
    }

    /* renamed from: j */
    public static void m19004j(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Object must not be null");
        }
    }

    /* renamed from: k */
    public static void m19005k(Object obj, String str) {
        if (obj == null) {
            throw new IllegalArgumentException(str);
        }
    }
}
