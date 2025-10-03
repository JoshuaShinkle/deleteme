package androidx.transition;

import android.graphics.Matrix;
import android.util.Log;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* renamed from: androidx.transition.d0 */
/* loaded from: classes.dex */
public class C0518d0 extends C0516c0 {

    /* renamed from: e */
    public static Method f2887e;

    /* renamed from: f */
    public static boolean f2888f;

    /* renamed from: g */
    public static Method f2889g;

    /* renamed from: h */
    public static boolean f2890h;

    @Override // androidx.transition.C0522f0
    /* renamed from: f */
    public void mo3067f(View view, Matrix matrix) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        m3069j();
        Method method = f2887e;
        if (method != null) {
            try {
                method.invoke(view, matrix);
            } catch (IllegalAccessException unused) {
            } catch (InvocationTargetException e9) {
                throw new RuntimeException(e9.getCause());
            }
        }
    }

    @Override // androidx.transition.C0522f0
    /* renamed from: g */
    public void mo3068g(View view, Matrix matrix) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        m3070k();
        Method method = f2889g;
        if (method != null) {
            try {
                method.invoke(view, matrix);
            } catch (IllegalAccessException unused) {
            } catch (InvocationTargetException e9) {
                throw new RuntimeException(e9.getCause());
            }
        }
    }

    /* renamed from: j */
    public final void m3069j() throws NoSuchMethodException, SecurityException {
        if (f2888f) {
            return;
        }
        try {
            Method declaredMethod = View.class.getDeclaredMethod("transformMatrixToGlobal", Matrix.class);
            f2887e = declaredMethod;
            declaredMethod.setAccessible(true);
        } catch (NoSuchMethodException e9) {
            Log.i("ViewUtilsApi21", "Failed to retrieve transformMatrixToGlobal method", e9);
        }
        f2888f = true;
    }

    /* renamed from: k */
    public final void m3070k() throws NoSuchMethodException, SecurityException {
        if (f2890h) {
            return;
        }
        try {
            Method declaredMethod = View.class.getDeclaredMethod("transformMatrixToLocal", Matrix.class);
            f2889g = declaredMethod;
            declaredMethod.setAccessible(true);
        } catch (NoSuchMethodException e9) {
            Log.i("ViewUtilsApi21", "Failed to retrieve transformMatrixToLocal method", e9);
        }
        f2890h = true;
    }
}
