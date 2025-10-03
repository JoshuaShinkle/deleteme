package p256z4;

import android.content.Context;
import android.content.pm.PackageManager;
import p006a5.C0035c;

/* renamed from: z4.a */
/* loaded from: classes2.dex */
public class C6825a {

    /* renamed from: a */
    public static String f22663a = "MixpanelAPI.ConfigurationChecker";

    /* renamed from: a */
    public static boolean m25464a(Context context) {
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();
        if (packageManager == null || packageName == null) {
            C0035c.m149k(f22663a, "Can't check configuration when using a Context with null packageManager or packageName");
            return false;
        }
        if (packageManager.checkPermission("android.permission.INTERNET", packageName) == 0) {
            return true;
        }
        C0035c.m149k(f22663a, "Package does not have permission android.permission.INTERNET - Mixpanel will not work at all!");
        C0035c.m143e(f22663a, "You can fix this by adding the following to your AndroidManifest.xml file:\n<uses-permission android:name=\"android.permission.INTERNET\" />");
        return false;
    }
}
