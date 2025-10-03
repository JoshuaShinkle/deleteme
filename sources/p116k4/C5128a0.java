package p116k4;

import android.content.Context;
import android.content.pm.PackageManager;

/* renamed from: k4.a0 */
/* loaded from: classes.dex */
public final class C5128a0 {
    /* renamed from: a */
    public static boolean m20001a(Context context, String str) throws PackageManager.NameNotFoundException {
        if (str == null) {
            return false;
        }
        String packageName = context.getPackageName();
        if (packageName != null && packageName.equals(str)) {
            return true;
        }
        PackageManager packageManager = context.getPackageManager();
        try {
            packageManager.getPackageInfo(str, 1);
            return packageManager.getApplicationInfo(str, 0).enabled;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }
}
