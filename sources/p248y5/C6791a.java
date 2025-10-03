package p248y5;

import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import p203t5.C6313a;

/* renamed from: y5.a */
/* loaded from: classes2.dex */
public final class C6791a {
    /* renamed from: a */
    public static final void m25347a(Closeable closeable, Throwable th) throws IllegalAccessException, IOException, IllegalArgumentException, InvocationTargetException {
        if (closeable != null) {
            if (th == null) {
                closeable.close();
                return;
            }
            try {
                closeable.close();
            } catch (Throwable th2) {
                C6313a.m24147a(th, th2);
            }
        }
    }
}
