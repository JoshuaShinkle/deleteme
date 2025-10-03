package p221v5;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import kotlin.collections.C5223f;
import p007a6.C0042f;

/* renamed from: v5.a */
/* loaded from: classes2.dex */
public class C6485a {

    /* renamed from: v5.a$a */
    public static final class a {

        /* renamed from: a */
        public static final a f21809a = new a();

        /* renamed from: b */
        public static final Method f21810b;

        /* renamed from: c */
        public static final Method f21811c;

        /* JADX WARN: Removed duplicated region for block: B:10:0x0039  */
        static {
            Method method;
            Method method2;
            Method[] methods = Throwable.class.getMethods();
            C0042f.m155b(methods);
            int length = methods.length;
            int i9 = 0;
            int i10 = 0;
            while (true) {
                method = null;
                if (i10 >= length) {
                    method2 = null;
                    break;
                }
                method2 = methods[i10];
                if (C0042f.m154a(method2.getName(), "addSuppressed")) {
                    Class<?>[] parameterTypes = method2.getParameterTypes();
                    C0042f.m157d(parameterTypes, "getParameterTypes(...)");
                    boolean z8 = C0042f.m154a(C5223f.m20391n(parameterTypes), Throwable.class);
                    if (z8) {
                        break;
                    } else {
                        i10++;
                    }
                }
            }
            f21810b = method2;
            int length2 = methods.length;
            while (true) {
                if (i9 >= length2) {
                    break;
                }
                Method method3 = methods[i9];
                if (C0042f.m154a(method3.getName(), "getSuppressed")) {
                    method = method3;
                    break;
                }
                i9++;
            }
            f21811c = method;
        }
    }

    /* renamed from: a */
    public void mo24819a(Throwable th, Throwable th2) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        C0042f.m158e(th, "cause");
        C0042f.m158e(th2, "exception");
        Method method = a.f21810b;
        if (method != null) {
            method.invoke(th, th2);
        }
    }
}
