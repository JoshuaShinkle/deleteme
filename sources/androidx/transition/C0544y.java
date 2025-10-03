package androidx.transition;

import android.util.Log;
import android.view.ViewGroup;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* renamed from: androidx.transition.y */
/* loaded from: classes.dex */
public class C0544y {

    /* renamed from: a */
    public static Method f2973a;

    /* renamed from: b */
    public static boolean f2974b;

    /* renamed from: a */
    public static void m3123a() throws NoSuchMethodException, SecurityException {
        if (f2974b) {
            return;
        }
        try {
            Method declaredMethod = ViewGroup.class.getDeclaredMethod("suppressLayout", Boolean.TYPE);
            f2973a = declaredMethod;
            declaredMethod.setAccessible(true);
        } catch (NoSuchMethodException e9) {
            Log.i("ViewUtilsApi18", "Failed to retrieve suppressLayout method", e9);
        }
        f2974b = true;
    }

    /* renamed from: b */
    public static void m3124b(ViewGroup viewGroup, boolean z8) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        m3123a();
        Method method = f2973a;
        if (method != null) {
            try {
                method.invoke(viewGroup, Boolean.valueOf(z8));
            } catch (IllegalAccessException e9) {
                Log.i("ViewUtilsApi18", "Failed to invoke suppressLayout method", e9);
            } catch (InvocationTargetException e10) {
                Log.i("ViewUtilsApi18", "Error invoking suppressLayout method", e10);
            }
        }
    }
}
