package p042d0;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.view.ViewConfiguration;

/* renamed from: d0.a0 */
/* loaded from: classes.dex */
public final class C4614a0 {
    /* renamed from: a */
    public static float m18390a(ViewConfiguration viewConfiguration, Context context) {
        return viewConfiguration.getScaledHorizontalScrollFactor();
    }

    /* renamed from: b */
    public static float m18391b(ViewConfiguration viewConfiguration, Context context) {
        return viewConfiguration.getScaledVerticalScrollFactor();
    }

    /* renamed from: c */
    public static boolean m18392c(ViewConfiguration viewConfiguration, Context context) {
        if (Build.VERSION.SDK_INT >= 28) {
            return viewConfiguration.shouldShowMenuShortcutsWhenKeyboardPresent();
        }
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("config_showMenuShortcutsWhenKeyboardPresent", "bool", "android");
        return identifier != 0 && resources.getBoolean(identifier);
    }
}
