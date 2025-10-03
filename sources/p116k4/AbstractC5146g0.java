package p116k4;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.util.Log;
import java.util.UUID;
import p209u2.C6383t;

/* renamed from: k4.g0 */
/* loaded from: classes.dex */
public abstract class AbstractC5146g0 {
    /* renamed from: a */
    public static String m20043a(Context context) {
        String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        if (!C6383t.m24517f(string)) {
            return string;
        }
        if (context.getSharedPreferences("U", 0).contains("U.UUID")) {
            return m20048f("U.UUID", context);
        }
        String strM24518g = C6383t.m24518g(8);
        m20052j("U.UUID", strM24518g, context);
        return strM24518g;
    }

    /* renamed from: b */
    public static String m20044b(Context context) {
        return m20045c(context);
    }

    /* renamed from: c */
    public static String m20045c(Context context) {
        if (context.getSharedPreferences("U", 0).contains("InstallationId")) {
            return m20048f("InstallationId", context);
        }
        String string = UUID.randomUUID().toString();
        m20052j("InstallationId", string, context);
        return string;
    }

    /* renamed from: d */
    public static boolean m20046d(String str, boolean z8, Context context) {
        if (str == null || context == null) {
            throw new NullPointerException("Parameter can not be null.");
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("U", 0);
        Log.v("PrefHelper", "getBoolean(" + str + "," + z8 + ")");
        return Boolean.valueOf(sharedPreferences.getBoolean(str, z8)).booleanValue();
    }

    /* renamed from: e */
    public static Long m20047e(String str, Long l9, Context context) {
        if (str == null || context == null) {
            throw new IllegalArgumentException("Parameter can not be null.");
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("U", 0);
        Log.v("PrefHelper", "getLong(" + str + "," + l9 + ")");
        return Long.valueOf(sharedPreferences.getLong(str, l9.longValue()));
    }

    /* renamed from: f */
    public static String m20048f(String str, Context context) {
        return m20049g(str, "", context);
    }

    /* renamed from: g */
    public static String m20049g(String str, String str2, Context context) {
        if (str == null || str2 == null || context == null) {
            throw new NullPointerException("Parameter can not be null.");
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("U", 0);
        Log.v("PrefHelper", "getString(" + str + "," + str2 + ")");
        return sharedPreferences.getString(str, str2);
    }

    /* renamed from: h */
    public static void m20050h(String str, Boolean bool, Context context) {
        if (str == null || bool == null || context == null) {
            return;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("U", 0);
        Log.d("PrefHelper", "putBoolean(" + str + "," + bool + ")");
        sharedPreferences.edit().putBoolean(str, bool.booleanValue()).apply();
    }

    /* renamed from: i */
    public static void m20051i(String str, Long l9, Context context) {
        if (str == null || context == null) {
            return;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("U", 0);
        Log.d("PrefHelper", "putLong(" + str + "," + l9 + ")");
        sharedPreferences.edit().putLong(str, l9.longValue()).apply();
    }

    /* renamed from: j */
    public static void m20052j(String str, String str2, Context context) {
        if (str == null || str2 == null || context == null) {
            return;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("U", 0);
        Log.d("PrefHelper", "putString(" + str + "," + str2 + ")");
        sharedPreferences.edit().putString(str, str2).apply();
    }
}
