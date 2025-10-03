package p081h;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: h.b */
/* loaded from: classes.dex */
public class C4976b extends AbstractC4977c {

    /* renamed from: a */
    public final Object f17081a = new Object();

    /* renamed from: b */
    public final ExecutorService f17082b = Executors.newFixedThreadPool(2, new a());

    /* renamed from: c */
    public volatile Handler f17083c;

    /* renamed from: h.b$a */
    public class a implements ThreadFactory {

        /* renamed from: a */
        public final AtomicInteger f17084a = new AtomicInteger(0);

        public a() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName(String.format("arch_disk_io_%d", Integer.valueOf(this.f17084a.getAndIncrement())));
            return thread;
        }
    }

    @Override // p081h.AbstractC4977c
    /* renamed from: a */
    public void mo19265a(Runnable runnable) {
        this.f17082b.execute(runnable);
    }

    @Override // p081h.AbstractC4977c
    /* renamed from: b */
    public boolean mo19266b() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    @Override // p081h.AbstractC4977c
    /* renamed from: c */
    public void mo19267c(Runnable runnable) {
        if (this.f17083c == null) {
            synchronized (this.f17081a) {
                if (this.f17083c == null) {
                    this.f17083c = new Handler(Looper.getMainLooper());
                }
            }
        }
        this.f17083c.post(runnable);
    }
}
