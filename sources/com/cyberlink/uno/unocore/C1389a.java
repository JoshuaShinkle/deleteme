package com.cyberlink.uno.unocore;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.cyberlink.uno.unocore.a */
/* loaded from: classes.dex */
public class C1389a {
    /* renamed from: a */
    public static String m7226a(Context context) {
        return String.format("%s.%d", m7228c(context), Integer.valueOf(m7229d(context)));
    }

    /* renamed from: b */
    public static String m7227b(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        int i9 = applicationInfo.labelRes;
        return i9 == 0 ? applicationInfo.nonLocalizedLabel.toString() : context.getString(i9);
    }

    /* renamed from: c */
    public static String m7228c(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            Log.w("UNOCoreAppInfo", "No app version found");
            return "1.0";
        }
    }

    /* renamed from: d */
    public static int m7229d(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            Log.w("UNOCoreAppInfo", "No app version found");
            return 1;
        }
    }

    /* renamed from: e */
    public static Map<String, Object> m7230e(Context context) {
        HashMap map = new HashMap();
        map.put("PRODUCT", m7227b(context));
        map.put("VERSION", m7228c(context));
        map.put("BUILDNUMBER", String.valueOf(m7229d(context)));
        map.put("DISPLAYBUILDNUMBER", m7226a(context));
        map.put("exe_name", m7231f(context));
        map.put("VERSIONTYPE", "");
        map.put("SR", "");
        map.put("CUSTOMERNO", "");
        map.put("CHANNEL", "");
        return map;
    }

    /* renamed from: f */
    public static String m7231f(Context context) {
        return context.getPackageName();
    }
}
