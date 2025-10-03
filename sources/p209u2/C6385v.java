package p209u2;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: u2.v */
/* loaded from: classes.dex */
public final class C6385v {

    /* renamed from: a */
    public static final ExecutorService f21553a;

    /* renamed from: b */
    public static final ExecutorService f21554b;

    /* renamed from: c */
    public static final Thread f21555c;

    /* renamed from: d */
    public static final Handler f21556d;

    /* renamed from: u2.v$b */
    public static class b implements ExecutorService {

        /* renamed from: b */
        public final ExecutorService f21557b;

        @Override // java.util.concurrent.ExecutorService
        public boolean awaitTermination(long j9, TimeUnit timeUnit) {
            return this.f21557b.awaitTermination(j9, timeUnit);
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            ExecutorService executorService = this.f21557b;
            if (!(runnable instanceof c)) {
                runnable = new c(runnable);
            }
            executorService.execute(runnable);
        }

        @Override // java.util.concurrent.ExecutorService
        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection) {
            return this.f21557b.invokeAll(collection);
        }

        @Override // java.util.concurrent.ExecutorService
        public <T> T invokeAny(Collection<? extends Callable<T>> collection) {
            return (T) this.f21557b.invokeAny(collection);
        }

        @Override // java.util.concurrent.ExecutorService
        public boolean isShutdown() {
            return this.f21557b.isShutdown();
        }

        @Override // java.util.concurrent.ExecutorService
        public boolean isTerminated() {
            return this.f21557b.isTerminated();
        }

        @Override // java.util.concurrent.ExecutorService
        public void shutdown() {
            throw new IllegalStateException("Cannot shutdown it");
        }

        @Override // java.util.concurrent.ExecutorService
        public List<Runnable> shutdownNow() {
            throw new IllegalStateException("Cannot shutdown it");
        }

        @Override // java.util.concurrent.ExecutorService
        public <T> Future<T> submit(Callable<T> callable) {
            return this.f21557b.submit(callable);
        }

        public b(ExecutorService executorService) {
            this.f21557b = executorService;
        }

        @Override // java.util.concurrent.ExecutorService
        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection, long j9, TimeUnit timeUnit) {
            return this.f21557b.invokeAll(collection, j9, timeUnit);
        }

        @Override // java.util.concurrent.ExecutorService
        public <T> T invokeAny(Collection<? extends Callable<T>> collection, long j9, TimeUnit timeUnit) {
            return (T) this.f21557b.invokeAny(collection, j9, timeUnit);
        }

        @Override // java.util.concurrent.ExecutorService
        public <T> Future<T> submit(Runnable runnable, T t8) {
            ExecutorService executorService = this.f21557b;
            if (!(runnable instanceof c)) {
                runnable = new c(runnable);
            }
            return executorService.submit(runnable, t8);
        }

        @Override // java.util.concurrent.ExecutorService
        public Future<?> submit(Runnable runnable) {
            ExecutorService executorService = this.f21557b;
            if (!(runnable instanceof c)) {
                runnable = new c(runnable);
            }
            return executorService.submit(runnable);
        }
    }

    /* renamed from: u2.v$c */
    public static final class c implements Runnable {

        /* renamed from: b */
        public final Runnable f21558b;

        /* renamed from: c */
        public final long f21559c;

        /* renamed from: d */
        public final String f21560d;

        /* renamed from: a */
        public static String m24528a() {
            return "";
        }

        /* renamed from: b */
        public static void m24529b(String str, long j9, long j10, Throwable... thArr) {
        }

        @Override // java.lang.Runnable
        public void run() {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            String name = Thread.currentThread().getName();
            try {
                this.f21558b.run();
                m24529b(this.f21560d, jElapsedRealtime - this.f21559c, SystemClock.elapsedRealtime() - jElapsedRealtime, new Throwable[0]);
            } finally {
            }
        }

        public c(Runnable runnable) {
            this.f21558b = runnable;
            this.f21559c = SystemClock.elapsedRealtime();
            this.f21560d = m24528a();
        }
    }

    static {
        f21553a = new b(Executors.newCachedThreadPool(new ThreadFactoryC6373j("Threads.CACHED")));
        int iAvailableProcessors = Runtime.getRuntime().availableProcessors();
        f21554b = new b(new ThreadPoolExecutor(Math.max(2, Math.min(iAvailableProcessors - 1, 4)), (iAvailableProcessors * 5) + 1, 30L, TimeUnit.SECONDS, new LinkedBlockingDeque(256), new ThreadFactoryC6373j("Threads.NETWORK")));
        f21555c = Looper.getMainLooper().getThread();
        f21556d = new Handler(Looper.getMainLooper());
    }

    /* renamed from: a */
    public static boolean m24523a() {
        return f21555c == Thread.currentThread();
    }

    /* renamed from: b */
    public static void m24524b(Runnable runnable) {
        f21556d.post(new c(runnable));
    }

    /* renamed from: c */
    public static void m24525c(Runnable runnable) {
        f21553a.execute(new c(runnable));
    }

    /* renamed from: d */
    public static void m24526d(Runnable runnable) {
        f21554b.execute(new c(runnable));
    }

    /* renamed from: e */
    public static void m24527e(Runnable runnable) {
        if (m24523a()) {
            runnable.run();
        } else {
            f21556d.post(new c(runnable));
        }
    }
}
