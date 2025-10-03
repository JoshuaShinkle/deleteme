package p126l3;

import java.lang.Thread;

/* renamed from: l3.d */
/* loaded from: classes.dex */
public class C5285d {

    /* renamed from: a */
    public Runnable f17910a;

    /* renamed from: l3.d$a */
    public class a implements Thread.UncaughtExceptionHandler {

        /* renamed from: a */
        public final /* synthetic */ Thread.UncaughtExceptionHandler f17911a;

        public a(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
            this.f17911a = uncaughtExceptionHandler;
        }

        @Override // java.lang.Thread.UncaughtExceptionHandler
        public void uncaughtException(Thread thread, Throwable th) {
            Runnable runnable = C5285d.this.f17910a;
            C5285d.this.m20577b();
            if (runnable != null) {
                try {
                    runnable.run();
                } catch (Exception unused) {
                }
            }
            Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.f17911a;
            if (uncaughtExceptionHandler != null) {
                uncaughtExceptionHandler.uncaughtException(thread, th);
            }
        }
    }

    public C5285d(Runnable runnable) {
        this.f17910a = runnable;
        Thread threadCurrentThread = Thread.currentThread();
        threadCurrentThread.setUncaughtExceptionHandler(new a(threadCurrentThread.getUncaughtExceptionHandler()));
    }

    /* renamed from: b */
    public void m20577b() {
        this.f17910a = null;
    }
}
