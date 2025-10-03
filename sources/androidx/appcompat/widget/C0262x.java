package androidx.appcompat.widget;

import android.R;
import android.annotation.SuppressLint;
import android.graphics.Insets;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.ScaleDrawable;
import android.os.Build;
import android.util.Log;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import p041d.C4612a;
import p224w.C6494a;
import p224w.InterfaceC6502f;

@SuppressLint({"RestrictedAPI"})
/* renamed from: androidx.appcompat.widget.x */
/* loaded from: classes.dex */
public class C0262x {

    /* renamed from: a */
    public static final int[] f1220a = {R.attr.state_checked};

    /* renamed from: b */
    public static final int[] f1221b = new int[0];

    /* renamed from: c */
    public static final Rect f1222c = new Rect();

    /* renamed from: d */
    public static Class<?> f1223d;

    static {
        try {
            f1223d = Class.forName("android.graphics.Insets");
        } catch (ClassNotFoundException unused) {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    public static boolean m1073a(Drawable drawable) {
        if (!(drawable instanceof DrawableContainer)) {
            if (drawable instanceof InterfaceC6502f) {
                return m1073a(((InterfaceC6502f) drawable).m24873b());
            }
            if (drawable instanceof C4612a) {
                return m1073a(((C4612a) drawable).getWrappedDrawable());
            }
            if (drawable instanceof ScaleDrawable) {
                return m1073a(((ScaleDrawable) drawable).getDrawable());
            }
            return true;
        }
        Drawable.ConstantState constantState = drawable.getConstantState();
        if (!(constantState instanceof DrawableContainer.DrawableContainerState)) {
            return true;
        }
        for (Drawable drawable2 : ((DrawableContainer.DrawableContainerState) constantState).getChildren()) {
            if (!m1073a(drawable2)) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: b */
    public static void m1074b(Drawable drawable) {
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x008d  */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Rect m1075c(Drawable drawable) throws IllegalAccessException, SecurityException, IllegalArgumentException, InvocationTargetException {
        char c9;
        if (Build.VERSION.SDK_INT >= 29) {
            Insets opticalInsets = drawable.getOpticalInsets();
            Rect rect = new Rect();
            rect.left = opticalInsets.left;
            rect.right = opticalInsets.right;
            rect.top = opticalInsets.top;
            rect.bottom = opticalInsets.bottom;
            return rect;
        }
        if (f1223d != null) {
            try {
                Drawable drawableM24848k = C6494a.m24848k(drawable);
                Object objInvoke = drawableM24848k.getClass().getMethod("getOpticalInsets", new Class[0]).invoke(drawableM24848k, new Object[0]);
                if (objInvoke != null) {
                    Rect rect2 = new Rect();
                    for (Field field : f1223d.getFields()) {
                        String name = field.getName();
                        switch (name.hashCode()) {
                            case -1383228885:
                                if (name.equals("bottom")) {
                                    c9 = 3;
                                    break;
                                } else {
                                    c9 = 65535;
                                    break;
                                }
                            case 115029:
                                if (name.equals("top")) {
                                    c9 = 1;
                                    break;
                                }
                                break;
                            case 3317767:
                                if (name.equals(TtmlNode.LEFT)) {
                                    c9 = 0;
                                    break;
                                }
                                break;
                            case 108511772:
                                if (name.equals(TtmlNode.RIGHT)) {
                                    c9 = 2;
                                    break;
                                }
                                break;
                            default:
                                c9 = 65535;
                                break;
                        }
                        if (c9 == 0) {
                            rect2.left = field.getInt(objInvoke);
                        } else if (c9 == 1) {
                            rect2.top = field.getInt(objInvoke);
                        } else if (c9 == 2) {
                            rect2.right = field.getInt(objInvoke);
                        } else if (c9 == 3) {
                            rect2.bottom = field.getInt(objInvoke);
                        }
                    }
                    return rect2;
                }
            } catch (Exception unused) {
                Log.e("DrawableUtils", "Couldn't obtain the optical insets. Ignoring.");
            }
        }
        return f1222c;
    }

    /* renamed from: d */
    public static PorterDuff.Mode m1076d(int i9, PorterDuff.Mode mode) {
        if (i9 == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (i9 == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (i9 == 9) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        switch (i9) {
            case 14:
                return PorterDuff.Mode.MULTIPLY;
            case 15:
                return PorterDuff.Mode.SCREEN;
            case 16:
                return PorterDuff.Mode.ADD;
            default:
                return mode;
        }
    }
}
