package p007a6;

import java.util.Arrays;
import kotlin.UninitializedPropertyAccessException;

/* renamed from: a6.f */
/* loaded from: classes2.dex */
public class C0042f {
    /* renamed from: a */
    public static boolean m154a(Object obj, Object obj2) {
        return obj == null ? obj2 == null : obj.equals(obj2);
    }

    /* renamed from: b */
    public static void m155b(Object obj) {
        if (obj == null) {
            m163j();
        }
    }

    /* renamed from: c */
    public static void m156c(Object obj, String str) {
        if (obj == null) {
            m164k(str);
        }
    }

    /* renamed from: d */
    public static void m157d(Object obj, String str) {
        if (obj != null) {
            return;
        }
        throw ((NullPointerException) m161h(new NullPointerException(str + " must not be null")));
    }

    /* renamed from: e */
    public static void m158e(Object obj, String str) {
        if (obj == null) {
            m165l(str);
        }
    }

    /* renamed from: f */
    public static int m159f(int i9, int i10) {
        if (i9 < i10) {
            return -1;
        }
        return i9 == i10 ? 0 : 1;
    }

    /* renamed from: g */
    public static String m160g(String str) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String name = C0042f.class.getName();
        int i9 = 0;
        while (!stackTrace[i9].getClassName().equals(name)) {
            i9++;
        }
        while (stackTrace[i9].getClassName().equals(name)) {
            i9++;
        }
        StackTraceElement stackTraceElement = stackTrace[i9];
        return "Parameter specified as non-null is null: method " + stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName() + ", parameter " + str;
    }

    /* renamed from: h */
    public static <T extends Throwable> T m161h(T t8) {
        return (T) m162i(t8, C0042f.class.getName());
    }

    /* renamed from: i */
    public static <T extends Throwable> T m162i(T t8, String str) {
        StackTraceElement[] stackTrace = t8.getStackTrace();
        int length = stackTrace.length;
        int i9 = -1;
        for (int i10 = 0; i10 < length; i10++) {
            if (str.equals(stackTrace[i10].getClassName())) {
                i9 = i10;
            }
        }
        t8.setStackTrace((StackTraceElement[]) Arrays.copyOfRange(stackTrace, i9 + 1, length));
        return t8;
    }

    /* renamed from: j */
    public static void m163j() {
        throw ((NullPointerException) m161h(new NullPointerException()));
    }

    /* renamed from: k */
    public static void m164k(String str) {
        throw ((NullPointerException) m161h(new NullPointerException(str)));
    }

    /* renamed from: l */
    public static void m165l(String str) {
        throw ((NullPointerException) m161h(new NullPointerException(m160g(str))));
    }

    /* renamed from: m */
    public static void m166m(String str) {
        throw ((UninitializedPropertyAccessException) m161h(new UninitializedPropertyAccessException(str)));
    }

    /* renamed from: n */
    public static void m167n(String str) {
        m166m("lateinit property " + str + " has not been initialized");
    }
}
