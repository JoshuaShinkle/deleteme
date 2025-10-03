package androidx.transition;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.Log;
import android.util.Property;
import android.view.View;
import java.lang.reflect.Field;
import p042d0.C4647u;

/* renamed from: androidx.transition.b0 */
/* loaded from: classes.dex */
public class C0514b0 {

    /* renamed from: b */
    public static Field f2838b;

    /* renamed from: c */
    public static boolean f2839c;

    /* renamed from: a */
    public static final C0522f0 f2837a = new C0520e0();

    /* renamed from: d */
    public static final Property<View, Float> f2840d = new a(Float.class, "translationAlpha");

    /* renamed from: e */
    public static final Property<View, Rect> f2841e = new b(Rect.class, "clipBounds");

    /* renamed from: androidx.transition.b0$a */
    public static class a extends Property<View, Float> {
        public a(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float get(View view) {
            return Float.valueOf(C0514b0.m3025d(view));
        }

        @Override // android.util.Property
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(View view, Float f9) {
            C0514b0.m3029h(view, f9.floatValue());
        }
    }

    /* renamed from: androidx.transition.b0$b */
    public static class b extends Property<View, Rect> {
        public b(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Rect get(View view) {
            return C4647u.m18555m(view);
        }

        @Override // android.util.Property
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(View view, Rect rect) {
            C4647u.m18540e0(view, rect);
        }
    }

    /* renamed from: a */
    public static void m3022a(View view) {
        f2837a.mo3057a(view);
    }

    /* renamed from: b */
    public static void m3023b() throws NoSuchFieldException, SecurityException {
        if (f2839c) {
            return;
        }
        try {
            Field declaredField = View.class.getDeclaredField("mViewFlags");
            f2838b = declaredField;
            declaredField.setAccessible(true);
        } catch (NoSuchFieldException unused) {
            Log.i("ViewUtils", "fetchViewFlagsField: ");
        }
        f2839c = true;
    }

    /* renamed from: c */
    public static InterfaceC0512a0 m3024c(View view) {
        return new C0545z(view);
    }

    /* renamed from: d */
    public static float m3025d(View view) {
        return f2837a.mo3058b(view);
    }

    /* renamed from: e */
    public static InterfaceC0528i0 m3026e(View view) {
        return new C0526h0(view);
    }

    /* renamed from: f */
    public static void m3027f(View view) {
        f2837a.mo3059c(view);
    }

    /* renamed from: g */
    public static void m3028g(View view, int i9, int i10, int i11, int i12) {
        f2837a.mo3074d(view, i9, i10, i11, i12);
    }

    /* renamed from: h */
    public static void m3029h(View view, float f9) {
        f2837a.mo3060e(view, f9);
    }

    /* renamed from: i */
    public static void m3030i(View view, int i9) throws IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException {
        m3023b();
        Field field = f2838b;
        if (field != null) {
            try {
                f2838b.setInt(view, i9 | (field.getInt(view) & (-13)));
            } catch (IllegalAccessException unused) {
            }
        }
    }

    /* renamed from: j */
    public static void m3031j(View view, Matrix matrix) {
        f2837a.mo3067f(view, matrix);
    }

    /* renamed from: k */
    public static void m3032k(View view, Matrix matrix) {
        f2837a.mo3068g(view, matrix);
    }
}
