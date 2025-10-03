package androidx.activity;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.lifecycle.InterfaceC0383d;
import androidx.lifecycle.InterfaceC0385f;
import androidx.lifecycle.Lifecycle;
import java.lang.reflect.Field;

/* loaded from: classes.dex */
final class ImmLeaksCleaner implements InterfaceC0383d {

    /* renamed from: b */
    public static int f161b;

    /* renamed from: c */
    public static Field f162c;

    /* renamed from: d */
    public static Field f163d;

    /* renamed from: e */
    public static Field f164e;

    /* renamed from: a */
    public Activity f165a;

    /* renamed from: h */
    public static void m210h() throws NoSuchFieldException, SecurityException {
        try {
            f161b = 2;
            Field declaredField = InputMethodManager.class.getDeclaredField("mServedView");
            f163d = declaredField;
            declaredField.setAccessible(true);
            Field declaredField2 = InputMethodManager.class.getDeclaredField("mNextServedView");
            f164e = declaredField2;
            declaredField2.setAccessible(true);
            Field declaredField3 = InputMethodManager.class.getDeclaredField("mH");
            f162c = declaredField3;
            declaredField3.setAccessible(true);
            f161b = 1;
        } catch (NoSuchFieldException unused) {
        }
    }

    @Override // androidx.lifecycle.InterfaceC0383d
    /* renamed from: c */
    public void mo209c(InterfaceC0385f interfaceC0385f, Lifecycle.Event event) throws IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException {
        if (event != Lifecycle.Event.ON_DESTROY) {
            return;
        }
        if (f161b == 0) {
            m210h();
        }
        if (f161b == 1) {
            InputMethodManager inputMethodManager = (InputMethodManager) this.f165a.getSystemService("input_method");
            try {
                Object obj = f162c.get(inputMethodManager);
                if (obj == null) {
                    return;
                }
                synchronized (obj) {
                    try {
                        try {
                            View view = (View) f163d.get(inputMethodManager);
                            if (view == null) {
                                return;
                            }
                            if (view.isAttachedToWindow()) {
                                return;
                            }
                            try {
                                f164e.set(inputMethodManager, null);
                                inputMethodManager.isActive();
                            } catch (IllegalAccessException unused) {
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                    } catch (ClassCastException unused2) {
                    } catch (IllegalAccessException unused3) {
                    }
                }
            } catch (IllegalAccessException unused4) {
            }
        }
    }
}
