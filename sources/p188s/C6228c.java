package p188s;

import android.app.AppOpsManager;
import android.content.Context;

/* renamed from: s.c */
/* loaded from: classes.dex */
public final class C6228c {
    /* renamed from: a */
    public static int m23804a(Context context, String str, String str2) {
        return ((AppOpsManager) context.getSystemService(AppOpsManager.class)).noteProxyOpNoThrow(str, str2);
    }

    /* renamed from: b */
    public static String m23805b(String str) {
        return AppOpsManager.permissionToOp(str);
    }
}
