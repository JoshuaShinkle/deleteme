package p053e1;

import android.os.Process;
import android.os.StrictMode;
import android.util.Log;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: e1.a */
/* loaded from: classes.dex */
public final class ExecutorServiceC4708a implements ExecutorService {

    /* renamed from: c */
    public static final long f16455c = TimeUnit.SECONDS.toMillis(10);

    /* renamed from: d */
    public static volatile int f16456d;

    /* renamed from: b */
    public final ExecutorService f16457b;

    /* renamed from: e1.a$a */
    public static final class a implements ThreadFactory {

        /* renamed from: a */
        public final String f16458a;

        /* renamed from: b */
        public final b f16459b;

        /* renamed from: c */
        public final boolean f16460c;

        /* renamed from: d */
        public int f16461d;

        /* renamed from: e1.a$a$a, reason: collision with other inner class name */
        public class C6866a extends Thread {
            public C6866a(Runnable runnable, String str) {
                super(runnable, str);
            }

            @Override // java.lang.Thread, java.lang.Runnable
            public void run() throws SecurityException, IllegalArgumentException {
                Process.setThreadPriority(9);
                if (a.this.f16460c) {
                    StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectNetwork().penaltyDeath().build());
                }
                try {
                    super.run();
                } catch (Throwable th) {
                    a.this.f16459b.mo18851a(th);
                }
            }
        }

        public a(String str, b bVar, boolean z8) {
            this.f16458a = str;
            this.f16459b = bVar;
            this.f16460c = z8;
        }

        @Override // java.util.concurrent.ThreadFactory
        public synchronized Thread newThread(Runnable runnable) {
            C6866a c6866a;
            c6866a = new C6866a(runnable, "glide-" + this.f16458a + "-thread-" + this.f16461d);
            this.f16461d = this.f16461d + 1;
            return c6866a;
        }
    }

    /* renamed from: e1.a$b */
    public interface b {

        /* renamed from: a */
        public static final b f16463a = new a();

        /* renamed from: b */
        public static final b f16464b;

        /* renamed from: c */
        public static final b f16465c;

        /* renamed from: d */
        public static final b f16466d;

        /* renamed from: e1.a$b$a */
        public class a implements b {
            @Override // p053e1.ExecutorServiceC4708a.b
            /* renamed from: a */
            public void mo18851a(Throwable th) {
            }
        }

        /* renamed from: e1.a$b$b, reason: collision with other inner class name */
        public class C6867b implements b {
            @Override // p053e1.ExecutorServiceC4708a.b
            /* renamed from: a */
            public void mo18851a(Throwable th) {
                if (th == null || !Log.isLoggable("GlideExecutor", 6)) {
                    return;
                }
                Log.e("GlideExecutor", "Request threw uncaught throwable", th);
            }
        }

        /* renamed from: e1.a$b$c */
        public class c implements b {
            @Override // p053e1.ExecutorServiceC4708a.b
            /* renamed from: a */
            public void mo18851a(Throwable th) {
                if (th != null) {
                    throw new RuntimeException("Request threw uncaught throwable", th);
                }
            }
        }

        static {
            C6867b c6867b = new C6867b();
            f16464b = c6867b;
            f16465c = new c();
            f16466d = c6867b;
        }

        /* renamed from: a */
        void mo18851a(Throwable th);
    }

    public ExecutorServiceC4708a(ExecutorService executorService) {
        this.f16457b = executorService;
    }

    /* renamed from: a */
    public static int m18843a() {
        if (f16456d == 0) {
            f16456d = Math.min(4, C4709b.m18852a());
        }
        return f16456d;
    }

    /* renamed from: b */
    public static ExecutorServiceC4708a m18844b() {
        return m18845c(m18843a() >= 4 ? 2 : 1, b.f16466d);
    }

    /* renamed from: c */
    public static ExecutorServiceC4708a m18845c(int i9, b bVar) {
        return new ExecutorServiceC4708a(new ThreadPoolExecutor(0, i9, f16455c, TimeUnit.MILLISECONDS, new PriorityBlockingQueue(), new a("animation", bVar, true)));
    }

    /* renamed from: d */
    public static ExecutorServiceC4708a m18846d() {
        return m18847e(1, "disk-cache", b.f16466d);
    }

    /* renamed from: e */
    public static ExecutorServiceC4708a m18847e(int i9, String str, b bVar) {
        return new ExecutorServiceC4708a(new ThreadPoolExecutor(i9, i9, 0L, TimeUnit.MILLISECONDS, new PriorityBlockingQueue(), new a(str, bVar, true)));
    }

    /* renamed from: f */
    public static ExecutorServiceC4708a m18848f() {
        return m18849g(m18843a(), "source", b.f16466d);
    }

    /* renamed from: g */
    public static ExecutorServiceC4708a m18849g(int i9, String str, b bVar) {
        return new ExecutorServiceC4708a(new ThreadPoolExecutor(i9, i9, 0L, TimeUnit.MILLISECONDS, new PriorityBlockingQueue(), new a(str, bVar, false)));
    }

    /* renamed from: h */
    public static ExecutorServiceC4708a m18850h() {
        return new ExecutorServiceC4708a(new ThreadPoolExecutor(0, Integer.MAX_VALUE, f16455c, TimeUnit.MILLISECONDS, new SynchronousQueue(), new a("source-unlimited", b.f16466d, false)));
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean awaitTermination(long j9, TimeUnit timeUnit) {
        return this.f16457b.awaitTermination(j9, timeUnit);
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        this.f16457b.execute(runnable);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection) {
        return this.f16457b.invokeAll(collection);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> T invokeAny(Collection<? extends Callable<T>> collection) {
        return (T) this.f16457b.invokeAny(collection);
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isShutdown() {
        return this.f16457b.isShutdown();
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isTerminated() {
        return this.f16457b.isTerminated();
    }

    @Override // java.util.concurrent.ExecutorService
    public void shutdown() {
        this.f16457b.shutdown();
    }

    @Override // java.util.concurrent.ExecutorService
    public List<Runnable> shutdownNow() {
        return this.f16457b.shutdownNow();
    }

    @Override // java.util.concurrent.ExecutorService
    public Future<?> submit(Runnable runnable) {
        return this.f16457b.submit(runnable);
    }

    public String toString() {
        return this.f16457b.toString();
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection, long j9, TimeUnit timeUnit) {
        return this.f16457b.invokeAll(collection, j9, timeUnit);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> T invokeAny(Collection<? extends Callable<T>> collection, long j9, TimeUnit timeUnit) {
        return (T) this.f16457b.invokeAny(collection, j9, timeUnit);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> Future<T> submit(Runnable runnable, T t8) {
        return this.f16457b.submit(runnable, t8);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> Future<T> submit(Callable<T> callable) {
        return this.f16457b.submit(callable);
    }
}
