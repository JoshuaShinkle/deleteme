package p116k4;

import android.content.Intent;
import android.os.Process;
import com.cyberlink.you.Globals;
import com.google.android.exoplayer2.C3322C;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/* renamed from: k4.q */
/* loaded from: classes.dex */
public class C5175q {
    /* renamed from: a */
    public static boolean m20219a() {
        Intent intent = new Intent();
        intent.setClassName("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.process.ProtectActivity");
        return Globals.m7388i0().getBaseContext().getPackageManager().queryIntentActivities(intent, C3322C.DEFAULT_BUFFER_SEGMENT_SIZE).size() > 0;
    }

    /* renamed from: b */
    public static String m20220b() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Object systemService = Globals.m7388i0().getBaseContext().getSystemService("user");
        if (systemService == null) {
            return "";
        }
        try {
            Object objInvoke = Process.class.getMethod("myUserHandle", null).invoke(Process.class, null);
            Long l9 = (Long) systemService.getClass().getMethod("getSerialNumberForUser", objInvoke.getClass()).invoke(systemService, objInvoke);
            if (l9 != null) {
                return String.valueOf(l9);
            }
        } catch (Exception unused) {
        }
        return "";
    }

    /* renamed from: c */
    public static void m20221c() throws IOException {
        try {
            Runtime.getRuntime().exec("am start -n com.huawei.systemmanager/.optimize.process.ProtectActivity --user " + m20220b());
        } catch (IOException unused) {
        }
    }
}
