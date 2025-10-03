package p007a6;

import java.util.List;
import p017b6.InterfaceC0691a;

/* renamed from: a6.j */
/* loaded from: classes2.dex */
public class C0046j {
    /* renamed from: a */
    public static List m171a(Object obj) {
        if (obj instanceof InterfaceC0691a) {
            m175e(obj, "kotlin.collections.MutableList");
        }
        return m172b(obj);
    }

    /* renamed from: b */
    public static List m172b(Object obj) {
        try {
            return (List) obj;
        } catch (ClassCastException e9) {
            throw m174d(e9);
        }
    }

    /* renamed from: c */
    public static <T extends Throwable> T m173c(T t8) {
        return (T) C0042f.m162i(t8, C0046j.class.getName());
    }

    /* renamed from: d */
    public static ClassCastException m174d(ClassCastException classCastException) {
        throw ((ClassCastException) m173c(classCastException));
    }

    /* renamed from: e */
    public static void m175e(Object obj, String str) {
        m176f((obj == null ? "null" : obj.getClass().getName()) + " cannot be cast to " + str);
    }

    /* renamed from: f */
    public static void m176f(String str) {
        throw m174d(new ClassCastException(str));
    }
}
