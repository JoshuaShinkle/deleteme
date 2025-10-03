package com.cyberlink.uno.unocore;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.cyberlink.uno.unocore.h */
/* loaded from: classes.dex */
public class C1396h {
    /* renamed from: a */
    public static String m7284a(Context context) {
        return "1.1.2";
    }

    /* renamed from: b */
    public static Map<String, Object> m7285b(Context context) {
        HashMap map = new HashMap();
        map.put("uno_ver", m7284a(context));
        return map;
    }
}
