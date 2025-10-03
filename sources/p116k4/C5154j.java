package p116k4;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.cyberlink.you.database.C2956d0;
import com.cyberlink.you.friends.UserInfo;
import com.cyberlink.you.utility.CLUtility;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import java.io.File;
import java.util.Locale;
import p209u2.C6369f;
import p209u2.C6383t;
import p209u2.C6384u;
import p209u2.C6385v;

/* renamed from: k4.j */
/* loaded from: classes.dex */
public class C5154j {
    /* renamed from: b */
    public static PackageInfo m20068b(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    /* renamed from: c */
    public static void m20069c(final Context context) {
        System.currentTimeMillis();
        m20082p();
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true);
        C6385v.m24525c(new Runnable() { // from class: k4.i
            @Override // java.lang.Runnable
            public final void run() {
                C5154j.m20070d(context);
            }
        });
    }

    /* renamed from: d */
    public static /* synthetic */ void m20070d(Context context) {
        m20072f(context);
        m20071e(context);
        m20080n(context);
        m20078l(context);
    }

    /* renamed from: e */
    public static void m20071e(Context context) {
        int iIsGooglePlayServicesAvailable = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context);
        boolean zIsUserResolvableError = GoogleApiAvailability.getInstance().isUserResolvableError(iIsGooglePlayServicesAvailable);
        if (iIsGooglePlayServicesAvailable == 0 || zIsUserResolvableError) {
            PackageInfo packageInfoM20068b = m20068b(context, "com.google.android.gms");
            if (packageInfoM20068b != null) {
                m20079m("GMS", "YES:" + iIsGooglePlayServicesAvailable);
                m20079m("GMS_Ver", Integer.valueOf(packageInfoM20068b.versionCode));
            } else {
                m20079m("GMS", "NO:" + iIsGooglePlayServicesAvailable);
            }
        } else {
            m20079m("GMS", "NO:" + iIsGooglePlayServicesAvailable);
        }
        if ((m20068b(context, "com.dimonvideo.luckypatcher") == null && m20068b(context, "com.chelpus.lackypatch") == null && m20068b(context, "com.android.vending.billing.InAppBillingService.LACK") == null) ? false : true) {
            m20079m("Lucky", "YES");
        }
        m20079m("U_DB_Ver", Integer.valueOf(C2956d0.m14992c()));
    }

    /* renamed from: f */
    public static void m20072f(Context context) {
        m20079m("DEVICE", Build.DEVICE);
        m20079m("MODEL", Build.MODEL);
        m20079m("PRODUCT", Build.PRODUCT);
        m20079m("BOARD", Build.BOARD);
        m20079m("HARDWARE", Build.HARDWARE);
        m20079m("BRAND", Build.BRAND);
        m20079m("MANUFACTURER", Build.MANUFACTURER);
        String strM20081o = m20081o();
        if (!C6383t.m24517f(strM20081o)) {
            m20079m("CPU_HW", strM20081o);
        }
        m20073g(context);
        m20074h(context);
    }

    /* renamed from: g */
    public static void m20073g(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager != null) {
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            activityManager.getMemoryInfo(memoryInfo);
            m20079m("Max_Hw_Mem", C6383t.m24514c(memoryInfo.totalMem));
        }
        m20079m("Max_Vm_Mem", C6383t.m24514c(Runtime.getRuntime().maxMemory()));
    }

    /* renamed from: h */
    public static void m20074h(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager == null) {
            return;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        m20079m("Screen_Px", displayMetrics.widthPixels + "x" + displayMetrics.heightPixels);
        Configuration configuration = context.getResources().getConfiguration();
        m20079m("Screen_Dp", configuration.screenWidthDp + "x" + configuration.screenHeightDp);
    }

    /* renamed from: i */
    public static void m20075i(String str) {
        try {
            throw new IllegalStateException(str);
        } catch (IllegalStateException e9) {
            m20076j(e9);
        }
    }

    /* renamed from: j */
    public static void m20076j(Throwable th) {
        FirebaseCrashlytics.getInstance().recordException(th);
    }

    /* renamed from: k */
    public static void m20077k(String str) {
        FirebaseCrashlytics.getInstance().log(str);
    }

    /* renamed from: l */
    public static void m20078l(Context context) {
        if (C6384u.m24521a(context)) {
            m20079m("DEV_FIN_ACTIVITY", "YES");
        }
        m20079m("Locale", Locale.getDefault());
    }

    /* renamed from: m */
    public static void m20079m(String str, Object obj) {
        if (obj instanceof Boolean) {
            FirebaseCrashlytics.getInstance().setCustomKey(str, ((Boolean) obj).booleanValue());
            return;
        }
        if (obj instanceof Integer) {
            FirebaseCrashlytics.getInstance().setCustomKey(str, ((Integer) obj).intValue());
            return;
        }
        if (obj instanceof Long) {
            FirebaseCrashlytics.getInstance().setCustomKey(str, ((Long) obj).longValue());
            return;
        }
        if (obj instanceof Float) {
            FirebaseCrashlytics.getInstance().setCustomKey(str, ((Float) obj).floatValue());
        } else if (obj instanceof Double) {
            FirebaseCrashlytics.getInstance().setCustomKey(str, ((Double) obj).doubleValue());
        } else {
            FirebaseCrashlytics.getInstance().setCustomKey(str, obj.toString());
        }
    }

    /* renamed from: n */
    public static void m20080n(Context context) {
        UserInfo userInfoM16497V0 = CLUtility.m16497V0(context);
        if (userInfoM16497V0 != null) {
            FirebaseCrashlytics.getInstance().setUserId(String.valueOf(userInfoM16497V0.f13777b));
        }
    }

    /* renamed from: o */
    public static String m20081o() {
        for (String str : C6369f.m24474p(new File("/proc/cpuinfo"))) {
            if (str != null && str.toLowerCase().startsWith("hardware\t:")) {
                return str.substring(10).trim();
            }
        }
        return null;
    }

    /* renamed from: p */
    public static void m20082p() {
    }
}
