package com.cyberlink.uno.unocore;

import android.content.Context;
import android.os.Build;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.cyberlink.uno.unocore.d */
/* loaded from: classes.dex */
public class C1392d {
    /* renamed from: a */
    public static String m7251a(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        char cCharAt = str.charAt(0);
        if (Character.isUpperCase(cCharAt)) {
            return str;
        }
        return Character.toUpperCase(cCharAt) + str.substring(1);
    }

    /* renamed from: b */
    public static String m7252b() {
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        if (str2.startsWith(str)) {
            return m7251a(str2);
        }
        return m7251a(str) + StringUtils.SPACE + str2;
    }

    /* renamed from: c */
    public static Map<String, Object> m7253c(Context context) {
        HashMap map = new HashMap();
        map.put("DEVICE", m7252b());
        map.put("OS", m7254d());
        map.put("OSVERSION", m7255e());
        return map;
    }

    /* renamed from: d */
    public static String m7254d() {
        return "Android";
    }

    /* renamed from: e */
    public static String m7255e() {
        return Build.VERSION.RELEASE;
    }
}
