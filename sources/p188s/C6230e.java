package p188s;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

/* renamed from: s.e */
/* loaded from: classes.dex */
public final class C6230e {
    /* renamed from: a */
    public static Intent m23808a(Activity activity) {
        Intent parentActivityIntent = activity.getParentActivityIntent();
        if (parentActivityIntent != null) {
            return parentActivityIntent;
        }
        String strM23810c = m23810c(activity);
        if (strM23810c == null) {
            return null;
        }
        ComponentName componentName = new ComponentName(activity, strM23810c);
        try {
            return m23811d(activity, componentName) == null ? Intent.makeMainActivity(componentName) : new Intent().setComponent(componentName);
        } catch (PackageManager.NameNotFoundException unused) {
            Log.e("NavUtils", "getParentActivityIntent: bad parentActivityName '" + strM23810c + "' in manifest");
            return null;
        }
    }

    /* renamed from: b */
    public static Intent m23809b(Context context, ComponentName componentName) throws PackageManager.NameNotFoundException {
        String strM23811d = m23811d(context, componentName);
        if (strM23811d == null) {
            return null;
        }
        ComponentName componentName2 = new ComponentName(componentName.getPackageName(), strM23811d);
        return m23811d(context, componentName2) == null ? Intent.makeMainActivity(componentName2) : new Intent().setComponent(componentName2);
    }

    /* renamed from: c */
    public static String m23810c(Activity activity) {
        try {
            return m23811d(activity, activity.getComponentName());
        } catch (PackageManager.NameNotFoundException e9) {
            throw new IllegalArgumentException(e9);
        }
    }

    /* renamed from: d */
    public static String m23811d(Context context, ComponentName componentName) throws PackageManager.NameNotFoundException {
        String string;
        ActivityInfo activityInfo = context.getPackageManager().getActivityInfo(componentName, Build.VERSION.SDK_INT >= 29 ? 269222528 : 787072);
        String str = activityInfo.parentActivityName;
        if (str != null) {
            return str;
        }
        Bundle bundle = activityInfo.metaData;
        if (bundle == null || (string = bundle.getString("android.support.PARENT_ACTIVITY")) == null) {
            return null;
        }
        if (string.charAt(0) != '.') {
            return string;
        }
        return context.getPackageName() + string;
    }

    /* renamed from: e */
    public static void m23812e(Activity activity, Intent intent) {
        activity.navigateUpTo(intent);
    }

    /* renamed from: f */
    public static boolean m23813f(Activity activity, Intent intent) {
        return activity.shouldUpRecreateTask(intent);
    }
}
