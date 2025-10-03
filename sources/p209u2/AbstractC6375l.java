package p209u2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: u2.l */
/* loaded from: classes.dex */
public abstract class AbstractC6375l extends ThreadPoolExecutor {

    /* renamed from: b */
    public final Object f21534b;

    /* renamed from: c */
    public final boolean f21535c;

    /* renamed from: d */
    public final AtomicBoolean f21536d;

    public AbstractC6375l(int i9, int i10, long j9, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue, ThreadFactory threadFactory) {
        this(false, i9, i10, j9, timeUnit, blockingQueue, threadFactory);
    }

    /* renamed from: a */
    public void m24495a() {
        if (this.f21535c) {
            synchronized (this.f21534b) {
                if (this.f21536d.getAndSet(true)) {
                }
            }
        }
    }

    /* renamed from: b */
    public void m24496b() {
        if (this.f21535c) {
            synchronized (this.f21534b) {
                this.f21536d.getAndSet(false);
                this.f21534b.notifyAll();
            }
        }
    }

    @Override // java.util.concurrent.ThreadPoolExecutor
    public void beforeExecute(Thread thread, Runnable runnable) {
        if (this.f21535c && this.f21536d.get()) {
            synchronized (this.f21534b) {
                while (this.f21536d.get()) {
                    try {
                        this.f21534b.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
        super.beforeExecute(thread, runnable);
    }

    public AbstractC6375l(boolean z8, int i9, int i10, long j9, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue, ThreadFactory threadFactory) {
        super(i9, i10, j9, timeUnit, blockingQueue, threadFactory);
        this.f21534b = new Object();
        this.f21536d = new AtomicBoolean(false);
        this.f21535c = z8;
    }
}
