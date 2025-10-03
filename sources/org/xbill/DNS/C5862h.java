package org.xbill.DNS;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/* renamed from: org.xbill.DNS.h */
/* loaded from: classes3.dex */
public final class C5862h {

    /* renamed from: a */
    public static Map f20273a;

    static {
        try {
            m23260b();
        } catch (SecurityException unused) {
        }
    }

    /* renamed from: a */
    public static boolean m23259a(String str) {
        Map map = f20273a;
        return (map == null || map.get(str.toLowerCase()) == null) ? false : true;
    }

    /* renamed from: b */
    public static void m23260b() {
        String property = System.getProperty("dnsjava.options");
        if (property != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(property, ",");
            while (stringTokenizer.hasMoreTokens()) {
                String strNextToken = stringTokenizer.nextToken();
                int iIndexOf = strNextToken.indexOf(61);
                if (iIndexOf == -1) {
                    m23261c(strNextToken);
                } else {
                    m23262d(strNextToken.substring(0, iIndexOf), strNextToken.substring(iIndexOf + 1));
                }
            }
        }
    }

    /* renamed from: c */
    public static void m23261c(String str) {
        if (f20273a == null) {
            f20273a = new HashMap();
        }
        f20273a.put(str.toLowerCase(), "true");
    }

    /* renamed from: d */
    public static void m23262d(String str, String str2) {
        if (f20273a == null) {
            f20273a = new HashMap();
        }
        f20273a.put(str.toLowerCase(), str2.toLowerCase());
    }
}
