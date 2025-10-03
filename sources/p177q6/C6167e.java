package p177q6;

import android.util.Log;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.collections.C5239v;
import kotlin.text.C5257n;
import kotlin.text.StringsKt__StringsKt;
import okhttp3.C5522w;
import p007a6.C0042f;
import p129l6.C5298e;
import p157o6.C5470b;

/* renamed from: q6.e */
/* loaded from: classes.dex */
public final class C6167e {

    /* renamed from: a */
    public static final C6167e f20802a = new C6167e();

    /* renamed from: b */
    public static final CopyOnWriteArraySet<Logger> f20803b = new CopyOnWriteArraySet<>();

    /* renamed from: c */
    public static final Map<String, String> f20804c;

    static {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Package r22 = C5522w.class.getPackage();
        String name = r22 != null ? r22.getName() : null;
        if (name != null) {
            linkedHashMap.put(name, "OkHttp");
        }
        String name2 = C5522w.class.getName();
        C0042f.m157d(name2, "OkHttpClient::class.java.name");
        linkedHashMap.put(name2, "okhttp.OkHttpClient");
        String name3 = C5470b.class.getName();
        C0042f.m157d(name3, "Http2::class.java.name");
        linkedHashMap.put(name3, "okhttp.Http2");
        String name4 = C5298e.class.getName();
        C0042f.m157d(name4, "TaskRunner::class.java.name");
        linkedHashMap.put(name4, "okhttp.TaskRunner");
        linkedHashMap.put("okhttp3.mockwebserver.MockWebServer", "okhttp.MockWebServer");
        f20804c = C5239v.m20436d(linkedHashMap);
    }

    /* renamed from: a */
    public final void m23625a(String str, int i9, String str2, Throwable th) {
        int iMin;
        C0042f.m158e(str, "loggerName");
        C0042f.m158e(str2, "message");
        String strM23628d = m23628d(str);
        if (Log.isLoggable(strM23628d, i9)) {
            if (th != null) {
                str2 = str2 + '\n' + Log.getStackTraceString(th);
            }
            int length = str2.length();
            int i10 = 0;
            while (i10 < length) {
                int iM20461M = StringsKt__StringsKt.m20461M(str2, '\n', i10, false, 4, null);
                if (iM20461M == -1) {
                    iM20461M = length;
                }
                while (true) {
                    iMin = Math.min(iM20461M, i10 + 4000);
                    String strSubstring = str2.substring(i10, iMin);
                    C0042f.m157d(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
                    Log.println(i9, strM23628d, strSubstring);
                    if (iMin >= iM20461M) {
                        break;
                    } else {
                        i10 = iMin;
                    }
                }
                i10 = iMin + 1;
            }
        }
    }

    /* renamed from: b */
    public final void m23626b() {
        for (Map.Entry<String, String> entry : f20804c.entrySet()) {
            m23627c(entry.getKey(), entry.getValue());
        }
    }

    /* renamed from: c */
    public final void m23627c(String str, String str2) throws SecurityException {
        Logger logger = Logger.getLogger(str);
        if (f20803b.add(logger)) {
            logger.setUseParentHandlers(false);
            logger.setLevel(Log.isLoggable(str2, 3) ? Level.FINE : Log.isLoggable(str2, 4) ? Level.INFO : Level.WARNING);
            logger.addHandler(C6168f.f20805a);
        }
    }

    /* renamed from: d */
    public final String m23628d(String str) {
        String str2 = f20804c.get(str);
        return str2 == null ? C5257n.m20526n0(str, 23) : str2;
    }
}
