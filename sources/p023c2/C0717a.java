package p023c2;

import android.os.Build;
import android.util.Log;

/* renamed from: c2.a */
/* loaded from: classes.dex */
public final class C0717a {
    /* renamed from: a */
    public static void m3546a(boolean z8) {
        if (!z8) {
            throw new AssertionError("Expected condition to be true");
        }
    }

    /* renamed from: b */
    public static String m3547b() {
        return "@[name=" + Thread.currentThread().getName() + ", id=" + Thread.currentThread().getId() + "]";
    }

    /* renamed from: c */
    public static void m3548c(String str) {
        Log.d(str, "Android SDK: " + Build.VERSION.SDK_INT + ", Release: " + Build.VERSION.RELEASE + ", Brand: " + Build.BRAND + ", Device: " + Build.DEVICE + ", Id: " + Build.ID + ", Hardware: " + Build.HARDWARE + ", Manufacturer: " + Build.MANUFACTURER + ", Model: " + Build.MODEL + ", Product: " + Build.PRODUCT);
    }
}
