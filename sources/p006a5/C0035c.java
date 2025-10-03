package p006a5;

import android.util.Log;

/* renamed from: a5.c */
/* loaded from: classes2.dex */
public class C0035c {

    /* renamed from: a */
    public static int f132a = 5;

    /* renamed from: a */
    public static void m139a(String str, String str2) {
        if (m146h(3)) {
            Log.d(str, str2);
        }
    }

    /* renamed from: b */
    public static void m140b(String str, String str2, Throwable th) {
        if (m146h(3)) {
            Log.d(str, str2, th);
        }
    }

    /* renamed from: c */
    public static void m141c(String str, String str2) {
        if (m146h(6)) {
            Log.e(str, str2);
        }
    }

    /* renamed from: d */
    public static void m142d(String str, String str2, Throwable th) {
        if (m146h(6)) {
            Log.e(str, str2, th);
        }
    }

    /* renamed from: e */
    public static void m143e(String str, String str2) {
        if (m146h(4)) {
            Log.i(str, str2);
        }
    }

    /* renamed from: f */
    public static void m144f(String str, String str2, Throwable th) {
        if (m146h(4)) {
            Log.i(str, str2, th);
        }
    }

    /* renamed from: g */
    public static void m145g(int i9) {
        f132a = i9;
    }

    /* renamed from: h */
    public static boolean m146h(int i9) {
        return f132a <= i9;
    }

    /* renamed from: i */
    public static void m147i(String str, String str2) {
        if (m146h(2)) {
            Log.v(str, str2);
        }
    }

    /* renamed from: j */
    public static void m148j(String str, String str2, Throwable th) {
        if (m146h(2)) {
            Log.v(str, str2, th);
        }
    }

    /* renamed from: k */
    public static void m149k(String str, String str2) {
        if (m146h(5)) {
            Log.w(str, str2);
        }
    }
}
