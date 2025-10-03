package p197t;

import android.content.Context;
import android.os.Process;
import p188s.C6228c;

/* renamed from: t.b */
/* loaded from: classes.dex */
public final class C6274b {
    /* renamed from: a */
    public static int m24030a(Context context, String str, int i9, int i10, String str2) {
        if (context.checkPermission(str, i9, i10) == -1) {
            return -1;
        }
        String strM23805b = C6228c.m23805b(str);
        if (strM23805b == null) {
            return 0;
        }
        if (str2 == null) {
            String[] packagesForUid = context.getPackageManager().getPackagesForUid(i10);
            if (packagesForUid == null || packagesForUid.length <= 0) {
                return -1;
            }
            str2 = packagesForUid[0];
        }
        return C6228c.m23804a(context, strM23805b, str2) != 0 ? -2 : 0;
    }

    /* renamed from: b */
    public static int m24031b(Context context, String str) {
        return m24030a(context, str, Process.myPid(), Process.myUid(), context.getPackageName());
    }
}
