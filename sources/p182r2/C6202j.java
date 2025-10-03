package p182r2;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: r2.j */
/* loaded from: classes.dex */
public class C6202j {

    /* renamed from: a */
    public static final ConcurrentHashMap<String, Date> f20890a = new ConcurrentHashMap<>();

    /* renamed from: a */
    public static void m23722a(String str) {
        f20890a.put(str, new Date());
    }

    /* renamed from: b */
    public static Date m23723b(String str) {
        return f20890a.remove(str);
    }
}
