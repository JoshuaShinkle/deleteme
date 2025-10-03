package p023c2;

import android.util.Log;
import java.lang.reflect.Field;

/* renamed from: c2.b */
/* loaded from: classes.dex */
public class C0718b {

    /* renamed from: a */
    public static final String f3399a = "b";

    /* renamed from: a */
    public static long m3549a(Class cls, Object obj, String str) throws NoSuchFieldException, SecurityException {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField.getLong(obj);
        } catch (IllegalAccessException unused) {
            Log.e(f3399a, "Cannot access such field: " + str + " in class: " + cls.getName());
            return 0L;
        } catch (NoSuchFieldException unused2) {
            Log.e(f3399a, "No such field: " + str + " in class: " + cls.getName());
            return 0L;
        }
    }
}
