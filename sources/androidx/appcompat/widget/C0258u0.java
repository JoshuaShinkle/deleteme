package androidx.appcompat.widget;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import p042d0.C4647u;

/* renamed from: androidx.appcompat.widget.u0 */
/* loaded from: classes.dex */
public class C0258u0 {

    /* renamed from: a */
    public static Method f1219a;

    static {
        try {
            Method declaredMethod = View.class.getDeclaredMethod("computeFitSystemWindows", Rect.class, Rect.class);
            f1219a = declaredMethod;
            if (declaredMethod.isAccessible()) {
                return;
            }
            f1219a.setAccessible(true);
        } catch (NoSuchMethodException unused) {
            Log.d("ViewUtils", "Could not find method computeFitSystemWindows. Oh well.");
        }
    }

    /* renamed from: a */
    public static void m1067a(View view, Rect rect, Rect rect2) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method method = f1219a;
        if (method != null) {
            try {
                method.invoke(view, rect, rect2);
            } catch (Exception e9) {
                Log.d("ViewUtils", "Could not invoke computeFitSystemWindows", e9);
            }
        }
    }

    /* renamed from: b */
    public static boolean m1068b(View view) {
        return C4647u.m18567s(view) == 1;
    }

    /* renamed from: c */
    public static void m1069c(View view) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        try {
            Method method = view.getClass().getMethod("makeOptionalFitsSystemWindows", new Class[0]);
            if (!method.isAccessible()) {
                method.setAccessible(true);
            }
            method.invoke(view, new Object[0]);
        } catch (IllegalAccessException e9) {
            Log.d("ViewUtils", "Could not invoke makeOptionalFitsSystemWindows", e9);
        } catch (NoSuchMethodException unused) {
            Log.d("ViewUtils", "Could not find method makeOptionalFitsSystemWindows. Oh well...");
        } catch (InvocationTargetException e10) {
            Log.d("ViewUtils", "Could not invoke makeOptionalFitsSystemWindows", e10);
        }
    }
}
