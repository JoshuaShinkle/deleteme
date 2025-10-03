package p251z;

import android.os.Build;
import android.os.Trace;
import android.util.Log;
import java.lang.reflect.Method;

/* renamed from: z.f */
/* loaded from: classes.dex */
public final class C6800f {

    /* renamed from: a */
    public static long f22537a;

    /* renamed from: b */
    public static Method f22538b;

    /* renamed from: c */
    public static Method f22539c;

    /* renamed from: d */
    public static Method f22540d;

    /* renamed from: e */
    public static Method f22541e;

    static {
        if (Build.VERSION.SDK_INT < 29) {
            try {
                f22537a = Trace.class.getField("TRACE_TAG_APP").getLong(null);
                Class cls = Long.TYPE;
                f22538b = Trace.class.getMethod("isTagEnabled", cls);
                Class cls2 = Integer.TYPE;
                f22539c = Trace.class.getMethod("asyncTraceBegin", cls, String.class, cls2);
                f22540d = Trace.class.getMethod("asyncTraceEnd", cls, String.class, cls2);
                f22541e = Trace.class.getMethod("traceCounter", cls, String.class, cls2);
            } catch (Exception e9) {
                Log.i("TraceCompat", "Unable to initialize via reflection.", e9);
            }
        }
    }

    /* renamed from: a */
    public static void m25355a(String str) {
        Trace.beginSection(str);
    }

    /* renamed from: b */
    public static void m25356b() {
        Trace.endSection();
    }
}
