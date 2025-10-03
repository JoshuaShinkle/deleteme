package p042d0;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* renamed from: d0.f */
/* loaded from: classes.dex */
public class C4623f {

    /* renamed from: a */
    public static boolean f16244a = false;

    /* renamed from: b */
    public static Method f16245b = null;

    /* renamed from: c */
    public static boolean f16246c = false;

    /* renamed from: d */
    public static Field f16247d;

    /* renamed from: d0.f$a */
    public interface a {
        boolean superDispatchKeyEvent(KeyEvent keyEvent);
    }

    /* renamed from: a */
    public static boolean m18421a(ActionBar actionBar, KeyEvent keyEvent) {
        if (!f16244a) {
            try {
                f16245b = actionBar.getClass().getMethod("onMenuKeyEvent", KeyEvent.class);
            } catch (NoSuchMethodException unused) {
            }
            f16244a = true;
        }
        Method method = f16245b;
        if (method != null) {
            try {
                return ((Boolean) method.invoke(actionBar, keyEvent)).booleanValue();
            } catch (IllegalAccessException | InvocationTargetException unused2) {
            }
        }
        return false;
    }

    /* renamed from: b */
    public static boolean m18422b(Activity activity, KeyEvent keyEvent) {
        activity.onUserInteraction();
        Window window = activity.getWindow();
        if (window.hasFeature(8)) {
            ActionBar actionBar = activity.getActionBar();
            if (keyEvent.getKeyCode() == 82 && actionBar != null && m18421a(actionBar, keyEvent)) {
                return true;
            }
        }
        if (window.superDispatchKeyEvent(keyEvent)) {
            return true;
        }
        View decorView = window.getDecorView();
        if (C4647u.m18539e(decorView, keyEvent)) {
            return true;
        }
        return keyEvent.dispatch(activity, decorView != null ? decorView.getKeyDispatcherState() : null, activity);
    }

    /* renamed from: c */
    public static boolean m18423c(Dialog dialog, KeyEvent keyEvent) throws NoSuchFieldException, SecurityException {
        DialogInterface.OnKeyListener onKeyListenerM18426f = m18426f(dialog);
        if (onKeyListenerM18426f != null && onKeyListenerM18426f.onKey(dialog, keyEvent.getKeyCode(), keyEvent)) {
            return true;
        }
        Window window = dialog.getWindow();
        if (window.superDispatchKeyEvent(keyEvent)) {
            return true;
        }
        View decorView = window.getDecorView();
        if (C4647u.m18539e(decorView, keyEvent)) {
            return true;
        }
        return keyEvent.dispatch(dialog, decorView != null ? decorView.getKeyDispatcherState() : null, dialog);
    }

    /* renamed from: d */
    public static boolean m18424d(View view, KeyEvent keyEvent) {
        return C4647u.m18541f(view, keyEvent);
    }

    /* renamed from: e */
    public static boolean m18425e(a aVar, View view, Window.Callback callback, KeyEvent keyEvent) {
        if (aVar == null) {
            return false;
        }
        return Build.VERSION.SDK_INT >= 28 ? aVar.superDispatchKeyEvent(keyEvent) : callback instanceof Activity ? m18422b((Activity) callback, keyEvent) : callback instanceof Dialog ? m18423c((Dialog) callback, keyEvent) : (view != null && C4647u.m18539e(view, keyEvent)) || aVar.superDispatchKeyEvent(keyEvent);
    }

    /* renamed from: f */
    public static DialogInterface.OnKeyListener m18426f(Dialog dialog) throws NoSuchFieldException, SecurityException {
        if (!f16246c) {
            try {
                Field declaredField = Dialog.class.getDeclaredField("mOnKeyListener");
                f16247d = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException unused) {
            }
            f16246c = true;
        }
        Field field = f16247d;
        if (field == null) {
            return null;
        }
        try {
            return (DialogInterface.OnKeyListener) field.get(dialog);
        } catch (IllegalAccessException unused2) {
            return null;
        }
    }
}
