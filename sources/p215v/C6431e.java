package p215v;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.Log;
import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import p206u.C6347a;

/* renamed from: v.e */
/* loaded from: classes.dex */
public class C6431e extends C6444r {

    /* renamed from: b */
    public static Class<?> f21662b = null;

    /* renamed from: c */
    public static Constructor<?> f21663c = null;

    /* renamed from: d */
    public static Method f21664d = null;

    /* renamed from: e */
    public static Method f21665e = null;

    /* renamed from: f */
    public static boolean f21666f = false;

    /* renamed from: f */
    public static boolean m24600f(Object obj, String str, int i9, boolean z8) throws NoSuchMethodException, ClassNotFoundException, SecurityException {
        m24602h();
        try {
            return ((Boolean) f21664d.invoke(obj, str, Integer.valueOf(i9), Boolean.valueOf(z8))).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e9) {
            throw new RuntimeException(e9);
        }
    }

    /* renamed from: g */
    public static Typeface m24601g(Object obj) throws NoSuchMethodException, ClassNotFoundException, SecurityException, ArrayIndexOutOfBoundsException, IllegalArgumentException, NegativeArraySizeException {
        m24602h();
        try {
            Object objNewInstance = Array.newInstance(f21662b, 1);
            Array.set(objNewInstance, 0, obj);
            return (Typeface) f21665e.invoke(null, objNewInstance);
        } catch (IllegalAccessException | InvocationTargetException e9) {
            throw new RuntimeException(e9);
        }
    }

    /* renamed from: h */
    public static void m24602h() throws NoSuchMethodException, ClassNotFoundException, SecurityException {
        Class<?> cls;
        Method method;
        Constructor<?> constructor;
        Method method2;
        if (f21666f) {
            return;
        }
        f21666f = true;
        try {
            cls = Class.forName("android.graphics.FontFamily");
            constructor = cls.getConstructor(new Class[0]);
            method2 = cls.getMethod("addFontWeightStyle", String.class, Integer.TYPE, Boolean.TYPE);
            method = Typeface.class.getMethod("createFromFamiliesWithDefault", Array.newInstance(cls, 1).getClass());
        } catch (ClassNotFoundException | NoSuchMethodException e9) {
            Log.e("TypefaceCompatApi21Impl", e9.getClass().getName(), e9);
            cls = null;
            method = null;
            constructor = null;
            method2 = null;
        }
        f21663c = constructor;
        f21662b = cls;
        f21664d = method2;
        f21665e = method;
    }

    /* renamed from: i */
    public static Object m24603i() throws NoSuchMethodException, ClassNotFoundException, SecurityException {
        m24602h();
        try {
            return f21663c.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e9) {
            throw new RuntimeException(e9);
        }
    }

    @Override // p215v.C6444r
    /* renamed from: a */
    public Typeface mo24604a(Context context, C6347a.b bVar, Resources resources, int i9) throws NoSuchMethodException, ClassNotFoundException, SecurityException {
        Object objM24603i = m24603i();
        for (C6347a.c cVar : bVar.m24353a()) {
            File fileM24639d = C6445s.m24639d(context);
            if (fileM24639d == null) {
                return null;
            }
            try {
                if (!C6445s.m24637b(fileM24639d, resources, cVar.m24355b())) {
                    return null;
                }
                if (!m24600f(objM24603i, fileM24639d.getPath(), cVar.m24358e(), cVar.m24359f())) {
                    return null;
                }
                fileM24639d.delete();
            } catch (RuntimeException unused) {
                return null;
            } finally {
                fileM24639d.delete();
            }
        }
        return m24601g(objM24603i);
    }
}
