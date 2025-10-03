package p067f5;

import android.os.Handler;
import android.os.Looper;

/* renamed from: f5.a */
/* loaded from: classes2.dex */
public final class C4788a {

    /* renamed from: a */
    public static final Handler f16639a;

    /* renamed from: b */
    public static boolean f16640b;

    static {
        Handler handler;
        try {
            handler = new Handler(Looper.getMainLooper());
        } catch (RuntimeException e9) {
            if (!e9.getMessage().contains("not mocked")) {
                throw e9;
            }
            handler = null;
        }
        f16639a = handler;
        f16640b = handler != null;
    }

    /* renamed from: a */
    public static boolean m19017a() {
        return !f16640b || Thread.currentThread() == Looper.getMainLooper().getThread();
    }

    /* renamed from: b */
    public static boolean m19018b(Runnable runnable) {
        return f16639a.post(runnable);
    }

    /* renamed from: c */
    public static void m19019c(Runnable runnable) {
        if (m19017a()) {
            runnable.run();
        } else {
            m19018b(runnable);
        }
    }
}
