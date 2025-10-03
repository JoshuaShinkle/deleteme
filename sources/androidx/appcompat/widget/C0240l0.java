package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import p010b.C0569j;
import p215v.C6427a;

/* renamed from: androidx.appcompat.widget.l0 */
/* loaded from: classes.dex */
public class C0240l0 {

    /* renamed from: a */
    public static final ThreadLocal<TypedValue> f1142a = new ThreadLocal<>();

    /* renamed from: b */
    public static final int[] f1143b = {-16842910};

    /* renamed from: c */
    public static final int[] f1144c = {R.attr.state_focused};

    /* renamed from: d */
    public static final int[] f1145d = {R.attr.state_activated};

    /* renamed from: e */
    public static final int[] f1146e = {R.attr.state_pressed};

    /* renamed from: f */
    public static final int[] f1147f = {R.attr.state_checked};

    /* renamed from: g */
    public static final int[] f1148g = {R.attr.state_selected};

    /* renamed from: h */
    public static final int[] f1149h = {-16842919, -16842908};

    /* renamed from: i */
    public static final int[] f1150i = new int[0];

    /* renamed from: j */
    public static final int[] f1151j = new int[1];

    /* renamed from: a */
    public static void m934a(View view, Context context) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(C0569j.AppCompatTheme);
        try {
            if (!typedArrayObtainStyledAttributes.hasValue(C0569j.AppCompatTheme_windowActionBar)) {
                Log.e("ThemeUtils", "View " + view.getClass() + " is an AppCompat widget that can only be used with a Theme.AppCompat theme (or descendant).");
            }
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    /* renamed from: b */
    public static int m935b(Context context, int i9) {
        ColorStateList colorStateListM938e = m938e(context, i9);
        if (colorStateListM938e != null && colorStateListM938e.isStateful()) {
            return colorStateListM938e.getColorForState(f1143b, colorStateListM938e.getDefaultColor());
        }
        TypedValue typedValueM939f = m939f();
        context.getTheme().resolveAttribute(R.attr.disabledAlpha, typedValueM939f, true);
        return m937d(context, i9, typedValueM939f.getFloat());
    }

    /* renamed from: c */
    public static int m936c(Context context, int i9) {
        int[] iArr = f1151j;
        iArr[0] = i9;
        C0250q0 c0250q0M1003u = C0250q0.m1003u(context, null, iArr);
        try {
            return c0250q0M1003u.m1006b(0, 0);
        } finally {
            c0250q0M1003u.m1024w();
        }
    }

    /* renamed from: d */
    public static int m937d(Context context, int i9, float f9) {
        return C6427a.m24590d(m936c(context, i9), Math.round(Color.alpha(r0) * f9));
    }

    /* renamed from: e */
    public static ColorStateList m938e(Context context, int i9) {
        int[] iArr = f1151j;
        iArr[0] = i9;
        C0250q0 c0250q0M1003u = C0250q0.m1003u(context, null, iArr);
        try {
            return c0250q0M1003u.m1007c(0);
        } finally {
            c0250q0M1003u.m1024w();
        }
    }

    /* renamed from: f */
    public static TypedValue m939f() {
        ThreadLocal<TypedValue> threadLocal = f1142a;
        TypedValue typedValue = threadLocal.get();
        if (typedValue != null) {
            return typedValue;
        }
        TypedValue typedValue2 = new TypedValue();
        threadLocal.set(typedValue2);
        return typedValue2;
    }
}
