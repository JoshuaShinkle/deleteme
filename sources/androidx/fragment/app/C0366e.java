package androidx.fragment.app;

import androidx.fragment.app.Fragment;
import java.lang.reflect.InvocationTargetException;
import p132m.C5308g;

/* renamed from: androidx.fragment.app.e */
/* loaded from: classes.dex */
public class C0366e {

    /* renamed from: a */
    public static final C5308g<String, Class<?>> f2008a = new C5308g<>();

    /* renamed from: b */
    public static boolean m1837b(ClassLoader classLoader, String str) {
        try {
            return Fragment.class.isAssignableFrom(m1838c(classLoader, str));
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    /* renamed from: c */
    public static Class<?> m1838c(ClassLoader classLoader, String str) throws ClassNotFoundException {
        C5308g<String, Class<?>> c5308g = f2008a;
        Class<?> cls = c5308g.get(str);
        if (cls != null) {
            return cls;
        }
        Class<?> cls2 = Class.forName(str, false, classLoader);
        c5308g.put(str, cls2);
        return cls2;
    }

    /* renamed from: d */
    public static Class<? extends Fragment> m1839d(ClassLoader classLoader, String str) {
        try {
            return m1838c(classLoader, str);
        } catch (ClassCastException e9) {
            throw new Fragment.InstantiationException("Unable to instantiate fragment " + str + ": make sure class is a valid subclass of Fragment", e9);
        } catch (ClassNotFoundException e10) {
            throw new Fragment.InstantiationException("Unable to instantiate fragment " + str + ": make sure class name exists", e10);
        }
    }

    /* renamed from: a */
    public Fragment mo1840a(ClassLoader classLoader, String str) {
        try {
            return m1839d(classLoader, str).getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (IllegalAccessException e9) {
            throw new Fragment.InstantiationException("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an empty constructor that is public", e9);
        } catch (InstantiationException e10) {
            throw new Fragment.InstantiationException("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an empty constructor that is public", e10);
        } catch (NoSuchMethodException e11) {
            throw new Fragment.InstantiationException("Unable to instantiate fragment " + str + ": could not find Fragment constructor", e11);
        } catch (InvocationTargetException e12) {
            throw new Fragment.InstantiationException("Unable to instantiate fragment " + str + ": calling Fragment constructor caused an exception", e12);
        }
    }
}
