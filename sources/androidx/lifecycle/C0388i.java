package androidx.lifecycle;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: androidx.lifecycle.i */
/* loaded from: classes.dex */
public class C0388i {

    /* renamed from: a */
    public static Map<Class, Integer> f2228a = new HashMap();

    /* renamed from: b */
    public static Map<Class, List<Constructor<? extends InterfaceC0382c>>> f2229b = new HashMap();

    /* renamed from: a */
    public static InterfaceC0382c m2097a(Constructor<? extends InterfaceC0382c> constructor, Object obj) {
        try {
            return constructor.newInstance(obj);
        } catch (IllegalAccessException e9) {
            throw new RuntimeException(e9);
        } catch (InstantiationException e10) {
            throw new RuntimeException(e10);
        } catch (InvocationTargetException e11) {
            throw new RuntimeException(e11);
        }
    }

    /* renamed from: b */
    public static Constructor<? extends InterfaceC0382c> m2098b(Class<?> cls) throws NoSuchMethodException, SecurityException {
        try {
            Package r02 = cls.getPackage();
            String canonicalName = cls.getCanonicalName();
            String name = r02 != null ? r02.getName() : "";
            if (!name.isEmpty()) {
                canonicalName = canonicalName.substring(name.length() + 1);
            }
            String strM2099c = m2099c(canonicalName);
            if (!name.isEmpty()) {
                strM2099c = name + "." + strM2099c;
            }
            Constructor declaredConstructor = Class.forName(strM2099c).getDeclaredConstructor(cls);
            if (!declaredConstructor.isAccessible()) {
                declaredConstructor.setAccessible(true);
            }
            return declaredConstructor;
        } catch (ClassNotFoundException unused) {
            return null;
        } catch (NoSuchMethodException e9) {
            throw new RuntimeException(e9);
        }
    }

    /* renamed from: c */
    public static String m2099c(String str) {
        return str.replace(".", "_") + "_LifecycleAdapter";
    }

    /* renamed from: d */
    public static int m2100d(Class<?> cls) throws NoSuchMethodException, SecurityException {
        Integer num = f2228a.get(cls);
        if (num != null) {
            return num.intValue();
        }
        int iM2103g = m2103g(cls);
        f2228a.put(cls, Integer.valueOf(iM2103g));
        return iM2103g;
    }

    /* renamed from: e */
    public static boolean m2101e(Class<?> cls) {
        return cls != null && InterfaceC0384e.class.isAssignableFrom(cls);
    }

    /* renamed from: f */
    public static InterfaceC0383d m2102f(Object obj) {
        boolean z8 = obj instanceof InterfaceC0383d;
        boolean z9 = obj instanceof InterfaceC0381b;
        if (z8 && z9) {
            return new FullLifecycleObserverAdapter((InterfaceC0381b) obj, (InterfaceC0383d) obj);
        }
        if (z9) {
            return new FullLifecycleObserverAdapter((InterfaceC0381b) obj, null);
        }
        if (z8) {
            return (InterfaceC0383d) obj;
        }
        Class<?> cls = obj.getClass();
        if (m2100d(cls) != 2) {
            return new ReflectiveGenericLifecycleObserver(obj);
        }
        List<Constructor<? extends InterfaceC0382c>> list = f2229b.get(cls);
        if (list.size() == 1) {
            return new SingleGeneratedAdapterObserver(m2097a(list.get(0), obj));
        }
        InterfaceC0382c[] interfaceC0382cArr = new InterfaceC0382c[list.size()];
        for (int i9 = 0; i9 < list.size(); i9++) {
            interfaceC0382cArr[i9] = m2097a(list.get(i9), obj);
        }
        return new CompositeGeneratedAdaptersObserver(interfaceC0382cArr);
    }

    /* renamed from: g */
    public static int m2103g(Class<?> cls) throws NoSuchMethodException, SecurityException {
        ArrayList arrayList;
        if (cls.getCanonicalName() == null) {
            return 1;
        }
        Constructor<? extends InterfaceC0382c> constructorM2098b = m2098b(cls);
        if (constructorM2098b != null) {
            f2229b.put(cls, Collections.singletonList(constructorM2098b));
            return 2;
        }
        if (C0380a.f2210c.m2069d(cls)) {
            return 1;
        }
        Class<? super Object> superclass = cls.getSuperclass();
        if (!m2101e(superclass)) {
            arrayList = null;
        } else {
            if (m2100d(superclass) == 1) {
                return 1;
            }
            arrayList = new ArrayList(f2229b.get(superclass));
        }
        for (Class<?> cls2 : cls.getInterfaces()) {
            if (m2101e(cls2)) {
                if (m2100d(cls2) == 1) {
                    return 1;
                }
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.addAll(f2229b.get(cls2));
            }
        }
        if (arrayList == null) {
            return 1;
        }
        f2229b.put(cls, arrayList);
        return 2;
    }
}
