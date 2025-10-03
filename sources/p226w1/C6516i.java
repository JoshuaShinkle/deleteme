package p226w1;

import android.text.TextUtils;
import java.util.Collection;

/* renamed from: w1.i */
/* loaded from: classes.dex */
public final class C6516i {
    /* renamed from: a */
    public static void m24935a(boolean z8, String str) {
        if (!z8) {
            throw new IllegalArgumentException(str);
        }
    }

    /* renamed from: b */
    public static String m24936b(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Must not be null or empty");
        }
        return str;
    }

    /* renamed from: c */
    public static <T extends Collection<Y>, Y> T m24937c(T t8) {
        if (t8.isEmpty()) {
            throw new IllegalArgumentException("Must not be empty.");
        }
        return t8;
    }

    /* renamed from: d */
    public static <T> T m24938d(T t8) {
        return (T) m24939e(t8, "Argument must not be null");
    }

    /* renamed from: e */
    public static <T> T m24939e(T t8, String str) {
        if (t8 != null) {
            return t8;
        }
        throw new NullPointerException(str);
    }
}
