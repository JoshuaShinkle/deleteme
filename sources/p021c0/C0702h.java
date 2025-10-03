package p021c0;

/* renamed from: c0.h */
/* loaded from: classes.dex */
public final class C0702h {
    /* renamed from: a */
    public static int m3467a(int i9) {
        if (i9 >= 0) {
            return i9;
        }
        throw new IllegalArgumentException();
    }

    /* renamed from: b */
    public static <T> T m3468b(T t8) {
        t8.getClass();
        return t8;
    }

    /* renamed from: c */
    public static <T> T m3469c(T t8, Object obj) {
        if (t8 != null) {
            return t8;
        }
        throw new NullPointerException(String.valueOf(obj));
    }
}
