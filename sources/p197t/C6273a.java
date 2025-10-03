package p197t;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Process;
import java.io.File;

/* renamed from: t.a */
/* loaded from: classes.dex */
public class C6273a {

    /* renamed from: a */
    public static final Object f21155a = new Object();

    /* renamed from: a */
    public static int m24022a(Context context, String str) {
        if (str != null) {
            return context.checkPermission(str, Process.myPid(), Process.myUid());
        }
        throw new IllegalArgumentException("permission is null");
    }

    /* renamed from: b */
    public static Context m24023b(Context context) {
        return context.createDeviceProtectedStorageContext();
    }

    /* renamed from: c */
    public static int m24024c(Context context, int i9) {
        return context.getColor(i9);
    }

    /* renamed from: d */
    public static Drawable m24025d(Context context, int i9) {
        return context.getDrawable(i9);
    }

    /* renamed from: e */
    public static File[] m24026e(Context context) {
        return context.getExternalCacheDirs();
    }

    /* renamed from: f */
    public static File[] m24027f(Context context, String str) {
        return context.getExternalFilesDirs(str);
    }

    /* renamed from: g */
    public static File m24028g(Context context) {
        return context.getNoBackupFilesDir();
    }

    /* renamed from: h */
    public static boolean m24029h(Context context, Intent[] intentArr, Bundle bundle) {
        context.startActivities(intentArr, bundle);
        return true;
    }
}
