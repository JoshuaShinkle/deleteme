package androidx.transition;

import android.util.Log;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* renamed from: androidx.transition.c0 */
/* loaded from: classes.dex */
public class C0516c0 extends C0522f0 {

    /* renamed from: a */
    public static Method f2879a;

    /* renamed from: b */
    public static boolean f2880b;

    /* renamed from: c */
    public static Method f2881c;

    /* renamed from: d */
    public static boolean f2882d;

    @Override // androidx.transition.C0522f0
    /* renamed from: a */
    public void mo3057a(View view) {
    }

    @Override // androidx.transition.C0522f0
    /* renamed from: b */
    public float mo3058b(View view) throws NoSuchMethodException, SecurityException {
        m3061h();
        Method method = f2881c;
        if (method != null) {
            try {
                return ((Float) method.invoke(view, new Object[0])).floatValue();
            } catch (IllegalAccessException unused) {
            } catch (InvocationTargetException e9) {
                throw new RuntimeException(e9.getCause());
            }
        }
        return super.mo3058b(view);
    }

    @Override // androidx.transition.C0522f0
    /* renamed from: c */
    public void mo3059c(View view) {
    }

    @Override // androidx.transition.C0522f0
    /* renamed from: e */
    public void mo3060e(View view, float f9) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        m3062i();
        Method method = f2879a;
        if (method == null) {
            view.setAlpha(f9);
            return;
        }
        try {
            method.invoke(view, Float.valueOf(f9));
        } catch (IllegalAccessException unused) {
        } catch (InvocationTargetException e9) {
            throw new RuntimeException(e9.getCause());
        }
    }

    /* renamed from: h */
    public final void m3061h() throws NoSuchMethodException, SecurityException {
        if (f2882d) {
            return;
        }
        try {
            Method declaredMethod = View.class.getDeclaredMethod("getTransitionAlpha", new Class[0]);
            f2881c = declaredMethod;
            declaredMethod.setAccessible(true);
        } catch (NoSuchMethodException e9) {
            Log.i("ViewUtilsApi19", "Failed to retrieve getTransitionAlpha method", e9);
        }
        f2882d = true;
    }

    /* renamed from: i */
    public final void m3062i() throws NoSuchMethodException, SecurityException {
        if (f2880b) {
            return;
        }
        try {
            Method declaredMethod = View.class.getDeclaredMethod("setTransitionAlpha", Float.TYPE);
            f2879a = declaredMethod;
            declaredMethod.setAccessible(true);
        } catch (NoSuchMethodException e9) {
            Log.i("ViewUtilsApi19", "Failed to retrieve setTransitionAlpha method", e9);
        }
        f2880b = true;
    }
}
