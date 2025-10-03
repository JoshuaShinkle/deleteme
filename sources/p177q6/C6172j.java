package p177q6;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import p007a6.C0040d;
import p007a6.C0042f;

/* renamed from: q6.j */
/* loaded from: classes.dex */
public final class C6172j {

    /* renamed from: d */
    public static final a f20816d = new a(null);

    /* renamed from: a */
    public final Method f20817a;

    /* renamed from: b */
    public final Method f20818b;

    /* renamed from: c */
    public final Method f20819c;

    /* renamed from: q6.j$a */
    public static final class a {
        public a() {
        }

        public /* synthetic */ a(C0040d c0040d) {
            this();
        }

        /* renamed from: a */
        public final C6172j m23642a() throws NoSuchMethodException, ClassNotFoundException, SecurityException {
            Method method;
            Method method2;
            Method method3;
            try {
                Class<?> cls = Class.forName("dalvik.system.CloseGuard");
                method = cls.getMethod("get", new Class[0]);
                method3 = cls.getMethod("open", String.class);
                method2 = cls.getMethod("warnIfOpen", new Class[0]);
            } catch (Exception unused) {
                method = null;
                method2 = null;
                method3 = null;
            }
            return new C6172j(method, method3, method2);
        }
    }

    public C6172j(Method method, Method method2, Method method3) {
        this.f20817a = method;
        this.f20818b = method2;
        this.f20819c = method3;
    }

    /* renamed from: a */
    public final Object m23640a(String str) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        C0042f.m158e(str, "closer");
        Method method = this.f20817a;
        if (method != null) {
            try {
                Object objInvoke = method.invoke(null, new Object[0]);
                Method method2 = this.f20818b;
                C0042f.m155b(method2);
                method2.invoke(objInvoke, str);
                return objInvoke;
            } catch (Exception unused) {
            }
        }
        return null;
    }

    /* renamed from: b */
    public final boolean m23641b(Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (obj == null) {
            return false;
        }
        try {
            Method method = this.f20819c;
            C0042f.m155b(method);
            method.invoke(obj, new Object[0]);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
