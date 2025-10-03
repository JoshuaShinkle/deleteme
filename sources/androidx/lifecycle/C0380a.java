package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: androidx.lifecycle.a */
/* loaded from: classes.dex */
public class C0380a {

    /* renamed from: c */
    public static C0380a f2210c = new C0380a();

    /* renamed from: a */
    public final Map<Class, a> f2211a = new HashMap();

    /* renamed from: b */
    public final Map<Class, Boolean> f2212b = new HashMap();

    /* renamed from: androidx.lifecycle.a$a */
    public static class a {

        /* renamed from: a */
        public final Map<Lifecycle.Event, List<b>> f2213a = new HashMap();

        /* renamed from: b */
        public final Map<b, Lifecycle.Event> f2214b;

        public a(Map<b, Lifecycle.Event> map) {
            this.f2214b = map;
            for (Map.Entry<b, Lifecycle.Event> entry : map.entrySet()) {
                Lifecycle.Event value = entry.getValue();
                List<b> arrayList = this.f2213a.get(value);
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                    this.f2213a.put(value, arrayList);
                }
                arrayList.add(entry.getKey());
            }
        }

        /* renamed from: b */
        public static void m2071b(List<b> list, InterfaceC0385f interfaceC0385f, Lifecycle.Event event, Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    list.get(size).m2073a(interfaceC0385f, event, obj);
                }
            }
        }

        /* renamed from: a */
        public void m2072a(InterfaceC0385f interfaceC0385f, Lifecycle.Event event, Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            m2071b(this.f2213a.get(event), interfaceC0385f, event, obj);
            m2071b(this.f2213a.get(Lifecycle.Event.ON_ANY), interfaceC0385f, event, obj);
        }
    }

    /* renamed from: androidx.lifecycle.a$b */
    public static class b {

        /* renamed from: a */
        public final int f2215a;

        /* renamed from: b */
        public final Method f2216b;

        public b(int i9, Method method) throws SecurityException {
            this.f2215a = i9;
            this.f2216b = method;
            method.setAccessible(true);
        }

        /* renamed from: a */
        public void m2073a(InterfaceC0385f interfaceC0385f, Lifecycle.Event event, Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            try {
                int i9 = this.f2215a;
                if (i9 == 0) {
                    this.f2216b.invoke(obj, new Object[0]);
                } else if (i9 == 1) {
                    this.f2216b.invoke(obj, interfaceC0385f);
                } else {
                    if (i9 != 2) {
                        return;
                    }
                    this.f2216b.invoke(obj, interfaceC0385f, event);
                }
            } catch (IllegalAccessException e9) {
                throw new RuntimeException(e9);
            } catch (InvocationTargetException e10) {
                throw new RuntimeException("Failed to call observer method", e10.getCause());
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            b bVar = (b) obj;
            return this.f2215a == bVar.f2215a && this.f2216b.getName().equals(bVar.f2216b.getName());
        }

        public int hashCode() {
            return (this.f2215a * 31) + this.f2216b.getName().hashCode();
        }
    }

    /* renamed from: a */
    public final a m2066a(Class cls, Method[] methodArr) {
        int i9;
        a aVarM2068c;
        Class superclass = cls.getSuperclass();
        HashMap map = new HashMap();
        if (superclass != null && (aVarM2068c = m2068c(superclass)) != null) {
            map.putAll(aVarM2068c.f2214b);
        }
        for (Class<?> cls2 : cls.getInterfaces()) {
            for (Map.Entry<b, Lifecycle.Event> entry : m2068c(cls2).f2214b.entrySet()) {
                m2070e(map, entry.getKey(), entry.getValue(), cls);
            }
        }
        if (methodArr == null) {
            methodArr = m2067b(cls);
        }
        boolean z8 = false;
        for (Method method : methodArr) {
            InterfaceC0392m interfaceC0392m = (InterfaceC0392m) method.getAnnotation(InterfaceC0392m.class);
            if (interfaceC0392m != null) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length <= 0) {
                    i9 = 0;
                } else {
                    if (!parameterTypes[0].isAssignableFrom(InterfaceC0385f.class)) {
                        throw new IllegalArgumentException("invalid parameter type. Must be one and instanceof LifecycleOwner");
                    }
                    i9 = 1;
                }
                Lifecycle.Event eventValue = interfaceC0392m.value();
                if (parameterTypes.length > 1) {
                    if (!parameterTypes[1].isAssignableFrom(Lifecycle.Event.class)) {
                        throw new IllegalArgumentException("invalid parameter type. second arg must be an event");
                    }
                    if (eventValue != Lifecycle.Event.ON_ANY) {
                        throw new IllegalArgumentException("Second arg is supported only for ON_ANY value");
                    }
                    i9 = 2;
                }
                if (parameterTypes.length > 2) {
                    throw new IllegalArgumentException("cannot have more than 2 params");
                }
                m2070e(map, new b(i9, method), eventValue, cls);
                z8 = true;
            }
        }
        a aVar = new a(map);
        this.f2211a.put(cls, aVar);
        this.f2212b.put(cls, Boolean.valueOf(z8));
        return aVar;
    }

    /* renamed from: b */
    public final Method[] m2067b(Class cls) {
        try {
            return cls.getDeclaredMethods();
        } catch (NoClassDefFoundError e9) {
            throw new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", e9);
        }
    }

    /* renamed from: c */
    public a m2068c(Class cls) {
        a aVar = this.f2211a.get(cls);
        return aVar != null ? aVar : m2066a(cls, null);
    }

    /* renamed from: d */
    public boolean m2069d(Class cls) {
        Boolean bool = this.f2212b.get(cls);
        if (bool != null) {
            return bool.booleanValue();
        }
        Method[] methodArrM2067b = m2067b(cls);
        for (Method method : methodArrM2067b) {
            if (((InterfaceC0392m) method.getAnnotation(InterfaceC0392m.class)) != null) {
                m2066a(cls, methodArrM2067b);
                return true;
            }
        }
        this.f2212b.put(cls, Boolean.FALSE);
        return false;
    }

    /* renamed from: e */
    public final void m2070e(Map<b, Lifecycle.Event> map, b bVar, Lifecycle.Event event, Class cls) {
        Lifecycle.Event event2 = map.get(bVar);
        if (event2 == null || event == event2) {
            if (event2 == null) {
                map.put(bVar, event);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Method " + bVar.f2216b.getName() + " in " + cls.getName() + " already declared with different @OnLifecycleEvent value: previous value " + event2 + ", new value " + event);
    }
}
