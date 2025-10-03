package p230w5;

import java.lang.reflect.InvocationTargetException;
import p007a6.C0042f;
import p221v5.C6485a;

/* renamed from: w5.a */
/* loaded from: classes2.dex */
public class C6545a extends C6485a {

    /* renamed from: w5.a$a */
    public static final class a {

        /* renamed from: a */
        public static final a f22039a = new a();

        /* renamed from: b */
        public static final Integer f22040b;

        static {
            Object obj;
            Integer num = null;
            try {
                obj = Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);
            } catch (Throwable unused) {
            }
            Integer num2 = obj instanceof Integer ? (Integer) obj : null;
            if (num2 != null) {
                if (num2.intValue() > 0) {
                    num = num2;
                }
            }
            f22040b = num;
        }
    }

    @Override // p221v5.C6485a
    /* renamed from: a */
    public void mo24819a(Throwable th, Throwable th2) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        C0042f.m158e(th, "cause");
        C0042f.m158e(th2, "exception");
        if (m25117b(19)) {
            th.addSuppressed(th2);
        } else {
            super.mo24819a(th, th2);
        }
    }

    /* renamed from: b */
    public final boolean m25117b(int i9) {
        Integer num = a.f22040b;
        return num == null || num.intValue() >= i9;
    }
}
