package androidx.loader.content;

import android.os.Binder;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public abstract class ModernAsyncTask<Params, Progress, Result> {

    /* renamed from: g */
    public static final ThreadFactory f2236g;

    /* renamed from: h */
    public static final BlockingQueue<Runnable> f2237h;

    /* renamed from: i */
    public static final Executor f2238i;

    /* renamed from: j */
    public static HandlerC0403f f2239j;

    /* renamed from: k */
    public static volatile Executor f2240k;

    /* renamed from: b */
    public final AbstractCallableC0404g<Params, Result> f2241b;

    /* renamed from: c */
    public final FutureTask<Result> f2242c;

    /* renamed from: d */
    public volatile Status f2243d = Status.PENDING;

    /* renamed from: e */
    public final AtomicBoolean f2244e = new AtomicBoolean();

    /* renamed from: f */
    public final AtomicBoolean f2245f = new AtomicBoolean();

    public enum Status {
        PENDING,
        RUNNING,
        FINISHED
    }

    /* renamed from: androidx.loader.content.ModernAsyncTask$a */
    public static class ThreadFactoryC0398a implements ThreadFactory {

        /* renamed from: a */
        public final AtomicInteger f2250a = new AtomicInteger(1);

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "ModernAsyncTask #" + this.f2250a.getAndIncrement());
        }
    }

    /* renamed from: androidx.loader.content.ModernAsyncTask$b */
    public class C0399b extends AbstractCallableC0404g<Params, Result> {
        public C0399b() {
        }

        @Override // java.util.concurrent.Callable
        public Result call() {
            ModernAsyncTask.this.f2245f.set(true);
            Result result = null;
            try {
                Process.setThreadPriority(10);
                result = (Result) ModernAsyncTask.this.mo2121b(this.f2256b);
                Binder.flushPendingCommands();
                return result;
            } finally {
            }
        }
    }

    /* renamed from: androidx.loader.content.ModernAsyncTask$c */
    public class C0400c extends FutureTask<Result> {
        public C0400c(Callable callable) {
            super(callable);
        }

        @Override // java.util.concurrent.FutureTask
        public void done() {
            try {
                ModernAsyncTask.this.m2131m(get());
            } catch (InterruptedException e9) {
                Log.w("AsyncTask", e9);
            } catch (CancellationException unused) {
                ModernAsyncTask.this.m2131m(null);
            } catch (ExecutionException e10) {
                throw new RuntimeException("An error occurred while executing doInBackground()", e10.getCause());
            } catch (Throwable th) {
                throw new RuntimeException("An error occurred while executing doInBackground()", th);
            }
        }
    }

    /* renamed from: androidx.loader.content.ModernAsyncTask$d */
    public static /* synthetic */ class C0401d {

        /* renamed from: a */
        public static final /* synthetic */ int[] f2253a;

        static {
            int[] iArr = new int[Status.values().length];
            f2253a = iArr;
            try {
                iArr[Status.RUNNING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2253a[Status.FINISHED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* renamed from: androidx.loader.content.ModernAsyncTask$e */
    public static class C0402e<Data> {

        /* renamed from: a */
        public final ModernAsyncTask f2254a;

        /* renamed from: b */
        public final Data[] f2255b;

        public C0402e(ModernAsyncTask modernAsyncTask, Data... dataArr) {
            this.f2254a = modernAsyncTask;
            this.f2255b = dataArr;
        }
    }

    /* renamed from: androidx.loader.content.ModernAsyncTask$f */
    public static class HandlerC0403f extends Handler {
        public HandlerC0403f() {
            super(Looper.getMainLooper());
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            C0402e c0402e = (C0402e) message.obj;
            int i9 = message.what;
            if (i9 == 1) {
                c0402e.f2254a.m2123d(c0402e.f2255b[0]);
            } else {
                if (i9 != 2) {
                    return;
                }
                c0402e.f2254a.m2129k(c0402e.f2255b);
            }
        }
    }

    /* renamed from: androidx.loader.content.ModernAsyncTask$g */
    public static abstract class AbstractCallableC0404g<Params, Result> implements Callable<Result> {

        /* renamed from: b */
        public Params[] f2256b;
    }

    static {
        ThreadFactoryC0398a threadFactoryC0398a = new ThreadFactoryC0398a();
        f2236g = threadFactoryC0398a;
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(10);
        f2237h = linkedBlockingQueue;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 128, 1L, TimeUnit.SECONDS, linkedBlockingQueue, threadFactoryC0398a);
        f2238i = threadPoolExecutor;
        f2240k = threadPoolExecutor;
    }

    public ModernAsyncTask() {
        C0399b c0399b = new C0399b();
        this.f2241b = c0399b;
        this.f2242c = new C0400c(c0399b);
    }

    /* renamed from: e */
    public static Handler m2119e() {
        HandlerC0403f handlerC0403f;
        synchronized (ModernAsyncTask.class) {
            if (f2239j == null) {
                f2239j = new HandlerC0403f();
            }
            handlerC0403f = f2239j;
        }
        return handlerC0403f;
    }

    /* renamed from: a */
    public final boolean m2120a(boolean z8) {
        this.f2244e.set(true);
        return this.f2242c.cancel(z8);
    }

    /* renamed from: b */
    public abstract Result mo2121b(Params... paramsArr);

    /* renamed from: c */
    public final ModernAsyncTask<Params, Progress, Result> m2122c(Executor executor, Params... paramsArr) {
        if (this.f2243d == Status.PENDING) {
            this.f2243d = Status.RUNNING;
            m2128j();
            this.f2241b.f2256b = paramsArr;
            executor.execute(this.f2242c);
            return this;
        }
        int i9 = C0401d.f2253a[this.f2243d.ordinal()];
        if (i9 == 1) {
            throw new IllegalStateException("Cannot execute task: the task is already running.");
        }
        if (i9 != 2) {
            throw new IllegalStateException("We should never reach this state");
        }
        throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
    }

    /* renamed from: d */
    public void m2123d(Result result) {
        if (m2124f()) {
            mo2126h(result);
        } else {
            mo2127i(result);
        }
        this.f2243d = Status.FINISHED;
    }

    /* renamed from: f */
    public final boolean m2124f() {
        return this.f2244e.get();
    }

    /* renamed from: g */
    public void m2125g() {
    }

    /* renamed from: h */
    public void mo2126h(Result result) {
        m2125g();
    }

    /* renamed from: i */
    public void mo2127i(Result result) {
    }

    /* renamed from: j */
    public void m2128j() {
    }

    /* renamed from: k */
    public void m2129k(Progress... progressArr) {
    }

    /* renamed from: l */
    public Result m2130l(Result result) {
        m2119e().obtainMessage(1, new C0402e(this, result)).sendToTarget();
        return result;
    }

    /* renamed from: m */
    public void m2131m(Result result) {
        if (this.f2245f.get()) {
            return;
        }
        m2130l(result);
    }
}
