package p191s2;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.cyberlink.uno.log.UNOFileLog;
import java.util.Map;

/* renamed from: s2.a */
/* loaded from: classes.dex */
public final class C6253a {

    /* renamed from: a */
    public static final Object f21095a = new Object();

    /* renamed from: b */
    public static volatile InterfaceC6254b f21096b = null;

    /* renamed from: c */
    public static String f21097c = "";

    /* renamed from: d */
    public static boolean f21098d = false;

    /* renamed from: a */
    public static void m23955a() {
        if (f21096b != null) {
            return;
        }
        UNOFileLog.m7214k(UNOFileLog.LogType.EXCEPTIONEVENT, "UNO need to be initial at first");
        throw new IllegalStateException("UNO.init() must be called first.");
    }

    /* renamed from: b */
    public static void m23956b(Context context, String str, Map<String, Object> map) {
        m23957c(context, str, map, null);
    }

    /* renamed from: c */
    public static void m23957c(Context context, String str, Map<String, Object> map, String str2) {
        Log.v("UNO", "init URL:" + str);
        if (context == null) {
            throw new IllegalArgumentException("context must not be null.");
        }
        if (str == null) {
            throw new IllegalArgumentException("serverURL must not be null.");
        }
        if (!m23958d(str)) {
            throw new IllegalArgumentException("serverURL should be a http:// or https:// URL.");
        }
        synchronized (f21095a) {
            if (f21096b != null) {
                if (f21098d) {
                    throw new IllegalStateException("init() was called. It must not be called multiple times.");
                }
                Log.w("UNO", "init() was called. It must not be called multiple times.");
            } else {
                f21097c = str;
                f21096b = new C6257e(context, str, C6258f.m23970b(context), map);
                if (str2 != null) {
                    UNOFileLog.m7210g(str2);
                }
                UNOFileLog.m7217n(UNOFileLog.LogType.MESSAGE, "UNO initial success", true);
            }
        }
    }

    /* renamed from: d */
    public static boolean m23958d(String str) {
        return str != null && (str.startsWith("http://") || str.startsWith("https://"));
    }

    /* renamed from: e */
    public static void m23959e() {
        Log.v("UNO", "ENTER onStart");
        m23955a();
        f21096b.onStart();
        Log.v("UNO", "LEAVE onStart");
    }

    /* renamed from: f */
    public static void m23960f() {
        Log.v("UNO", "ENTER onStop");
        m23955a();
        f21096b.onStop();
        Log.v("UNO", "LEAVE onStop");
    }

    /* renamed from: g */
    public static void m23961g() {
        m23955a();
        f21096b.mo23965b();
    }

    /* renamed from: h */
    public static void m23962h(String str, String str2) {
        Log.v("UNO", "recordEvent key:" + str + " value:" + str2);
        m23955a();
        if (TextUtils.isEmpty(str)) {
            UNOFileLog.m7214k(UNOFileLog.LogType.EXCEPTIONEVENT, "Event key is null or empty");
            throw new IllegalArgumentException("key must not be null or empty.");
        }
        if (TextUtils.isEmpty(str2)) {
            UNOFileLog.m7214k(UNOFileLog.LogType.EXCEPTIONEVENT, "Event value is null or empty");
            throw new IllegalArgumentException("value must not be null or empty.");
        }
        int iMo23964a = f21096b.mo23964a();
        UNOFileLog.m7214k(UNOFileLog.LogType.APPEVENT, "key:" + str + " id:" + iMo23964a + " value:" + str2);
        f21096b.mo23966c(str, iMo23964a, str2);
    }

    /* renamed from: i */
    public static void m23963i(Map<String, Object> map) {
        m23955a();
        f21096b.mo23967d(map);
    }
}
