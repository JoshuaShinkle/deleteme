package androidx.transition;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* renamed from: androidx.transition.e0 */
/* loaded from: classes.dex */
public class C0520e0 extends C0518d0 {

    /* renamed from: i */
    public static Method f2905i;

    /* renamed from: j */
    public static boolean f2906j;

    @Override // androidx.transition.C0522f0
    /* renamed from: d */
    public void mo3074d(View view, int i9, int i10, int i11, int i12) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        m3075l();
        Method method = f2905i;
        if (method != null) {
            try {
                method.invoke(view, Integer.valueOf(i9), Integer.valueOf(i10), Integer.valueOf(i11), Integer.valueOf(i12));
            } catch (IllegalAccessException unused) {
            } catch (InvocationTargetException e9) {
                throw new RuntimeException(e9.getCause());
            }
        }
    }

    @SuppressLint({"PrivateApi"})
    /* renamed from: l */
    public final void m3075l() throws NoSuchMethodException, SecurityException {
        if (f2906j) {
            return;
        }
        try {
            Class cls = Integer.TYPE;
            Method declaredMethod = View.class.getDeclaredMethod("setLeftTopRightBottom", cls, cls, cls, cls);
            f2905i = declaredMethod;
            declaredMethod.setAccessible(true);
        } catch (NoSuchMethodException e9) {
            Log.i("ViewUtilsApi22", "Failed to retrieve setLeftTopRightBottom method", e9);
        }
        f2906j = true;
    }
}
