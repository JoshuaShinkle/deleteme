package p218v2;

import android.util.Log;

/* renamed from: v2.z */
/* loaded from: classes.dex */
public class C6478z {
    /* renamed from: a */
    public static String m24809a(Object... objArr) {
        StringBuilder sb = new StringBuilder();
        for (Object obj : objArr) {
            if (obj != null) {
                sb.append(obj.toString());
            } else {
                sb.append("null");
            }
        }
        return sb.toString();
    }

    /* renamed from: b */
    public static int m24810b(String str, Object... objArr) {
        return Log.d(str, m24809a(objArr));
    }

    /* renamed from: c */
    public static int m24811c(String str, Object... objArr) {
        return Log.e(str, m24809a(objArr));
    }

    /* renamed from: d */
    public static int m24812d() {
        return 3;
    }

    /* renamed from: e */
    public static int m24813e(String str, Object... objArr) {
        return Log.i(str, m24809a(objArr));
    }

    /* renamed from: f */
    public static int m24814f(String str, Object... objArr) {
        return -1;
    }

    /* renamed from: g */
    public static int m24815g(String str, Object... objArr) {
        return Log.w(str, m24809a(objArr));
    }
}
