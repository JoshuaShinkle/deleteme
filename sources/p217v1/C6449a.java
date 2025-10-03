package p217v1;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import p243y0.InterfaceC6589b;

/* renamed from: v1.a */
/* loaded from: classes.dex */
public final class C6449a {

    /* renamed from: a */
    public static final ConcurrentMap<String, InterfaceC6589b> f21716a = new ConcurrentHashMap();

    /* renamed from: a */
    public static PackageInfo m24693a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e9) {
            Log.e("AppVersionSignature", "Cannot resolve info for" + context.getPackageName(), e9);
            return null;
        }
    }

    /* renamed from: b */
    public static String m24694b(PackageInfo packageInfo) {
        return packageInfo != null ? String.valueOf(packageInfo.versionCode) : UUID.randomUUID().toString();
    }

    /* renamed from: c */
    public static InterfaceC6589b m24695c(Context context) {
        String packageName = context.getPackageName();
        ConcurrentMap<String, InterfaceC6589b> concurrentMap = f21716a;
        InterfaceC6589b interfaceC6589b = concurrentMap.get(packageName);
        if (interfaceC6589b != null) {
            return interfaceC6589b;
        }
        InterfaceC6589b interfaceC6589bM24696d = m24696d(context);
        InterfaceC6589b interfaceC6589bPutIfAbsent = concurrentMap.putIfAbsent(packageName, interfaceC6589bM24696d);
        return interfaceC6589bPutIfAbsent == null ? interfaceC6589bM24696d : interfaceC6589bPutIfAbsent;
    }

    /* renamed from: d */
    public static InterfaceC6589b m24696d(Context context) {
        return new C6451c(m24694b(m24693a(context)));
    }
}
