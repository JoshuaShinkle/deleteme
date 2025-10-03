package com.perfectcorp.utility;

import android.os.AsyncTask;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes2.dex */
public abstract class PromisedTask<TParam, TProgress, TResult> {
    private static final int CORE_POOL_SIZE;
    private static final int CPU_COUNT;
    protected static Executor DEFAULT_EXECUTOR = Executors.newFixedThreadPool(20);
    private static final int KEEP_ALIVE = 1;
    private static final int MAXIMUM_POOL_SIZE;
    protected static final int MAX_THREAD_POOL = 20;
    public static Executor TRACED_EXECUTOR = null;
    public static boolean bIsDeveloperMode = false;
    private static final BlockingQueue<Runnable> sPoolWorkQueue;
    private static final ThreadFactory sThreadFactory;
    protected AsyncTask<Object, Object, Object> mTask;
    protected Executor mExecutor = DEFAULT_EXECUTOR;
    protected PromisedTask<TResult, ?, ?> mNextTask = null;
    private Integer mErrorCode = null;
    private Boolean mIsExecuted = Boolean.FALSE;
    private TResult mResult = null;
    protected InterfaceC4505e mProgressReporter = null;

    public static class CustomErrorException extends RuntimeException {
        public final int errorCode;

        public CustomErrorException(String str, int i9) {
            super(str);
            this.errorCode = i9;
        }
    }

    /* renamed from: com.perfectcorp.utility.PromisedTask$a */
    public class ThreadFactoryC4501a implements ThreadFactory {

        /* renamed from: a */
        public final AtomicInteger f15921a = new AtomicInteger(1);

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "AsyncTask #" + this.f15921a.getAndIncrement());
        }
    }

    /* renamed from: com.perfectcorp.utility.PromisedTask$b */
    public class C4502b extends ThreadPoolExecutor {

        /* renamed from: b */
        public ConcurrentHashMap<Integer, StackTraceElement> f15922b;

        /* renamed from: c */
        public ConcurrentHashMap<Integer, a> f15923c;

        /* renamed from: com.perfectcorp.utility.PromisedTask$b$a */
        public class a {

            /* renamed from: a */
            public final StackTraceElement f15924a;

            /* renamed from: b */
            public final Thread f15925b;

            public a(StackTraceElement stackTraceElement, Thread thread) {
                this.f15924a = stackTraceElement;
                this.f15925b = thread;
            }
        }

        public C4502b(int i9, int i10, long j9, TimeUnit timeUnit, BlockingQueue blockingQueue, ThreadFactory threadFactory) {
            super(i9, i10, j9, timeUnit, (BlockingQueue<Runnable>) blockingQueue, threadFactory);
            this.f15922b = new ConcurrentHashMap<>();
            this.f15923c = new ConcurrentHashMap<>();
        }

        /* renamed from: a */
        public final String m18095a(Thread thread) {
            StringBuilder sb = new StringBuilder();
            for (StackTraceElement stackTraceElement : thread.getStackTrace()) {
                sb.append(stackTraceElement);
                sb.append('\n');
            }
            return sb.toString();
        }

        @Override // java.util.concurrent.ThreadPoolExecutor
        public void afterExecute(Runnable runnable, Throwable th) {
            super.afterExecute(runnable, th);
            this.f15923c.remove(Integer.valueOf(runnable.hashCode()));
            if (PromisedTask.bIsDeveloperMode) {
                for (Map.Entry<Integer, a> entry : this.f15923c.entrySet()) {
                    a value = entry.getValue();
                    C4507b.m18101b("Running Task ", entry.getKey(), " from: ", value.f15924a, ", stack:\n", m18095a(value.f15925b));
                }
            }
        }

        @Override // java.util.concurrent.ThreadPoolExecutor
        public void beforeExecute(Thread thread, Runnable runnable) {
            this.f15923c.put(Integer.valueOf(runnable.hashCode()), new a(this.f15922b.remove(Integer.valueOf(runnable.hashCode())), thread));
            super.beforeExecute(thread, runnable);
        }

        @Override // java.util.concurrent.ThreadPoolExecutor, java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            this.f15922b.put(Integer.valueOf(runnable.hashCode()), stackTrace[4]);
            if (PromisedTask.bIsDeveloperMode) {
                C4507b.m18101b("Queue Task ", Integer.valueOf(runnable.hashCode()), " from: ", stackTrace[4]);
            }
            if (!this.f15923c.isEmpty() && PromisedTask.bIsDeveloperMode) {
                for (Map.Entry<Integer, a> entry : this.f15923c.entrySet()) {
                    a value = entry.getValue();
                    C4507b.m18101b("Running Task ", entry.getKey(), " from: ", value.f15924a, ", stack:\n", m18095a(value.f15925b));
                }
            }
            super.execute(runnable);
        }
    }

    /* renamed from: com.perfectcorp.utility.PromisedTask$c */
    public class AsyncTaskC4503c extends AsyncTask<Object, Object, Object> {

        /* renamed from: com.perfectcorp.utility.PromisedTask$c$a */
        public class a implements InterfaceC4505e {
            public a() {
            }

            @Override // com.perfectcorp.utility.PromisedTask.InterfaceC4505e
            /* renamed from: a */
            public void mo18097a(Object obj) {
                AsyncTaskC4503c.this.publishProgress(obj);
            }
        }

        public AsyncTaskC4503c() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.os.AsyncTask
        public Object doInBackground(Object... objArr) {
            PromisedTask.this.mProgressReporter = new a();
            try {
                return PromisedTask.this.doInBackground(objArr[0]);
            } catch (CustomErrorException e9) {
                C4507b.m18102c(e9);
                e9.printStackTrace();
                PromisedTask.this.reportError(e9.errorCode);
                return null;
            } catch (Exception e10) {
                C4507b.m18102c(e10);
                e10.printStackTrace();
                PromisedTask.this.reportError(-2147483647);
                return null;
            } catch (OutOfMemoryError e11) {
                C4507b.m18102c(e11);
                e11.printStackTrace();
                PromisedTask.this.reportError(-2147483642);
                System.gc();
                return null;
            }
        }

        @Override // android.os.AsyncTask
        public void onCancelled() {
            PromisedTask promisedTask = PromisedTask.this;
            if (promisedTask.mNextTask == null) {
                promisedTask.onCancelled();
                return;
            }
            if (promisedTask.mErrorCode != null) {
                PromisedTask promisedTask2 = PromisedTask.this;
                promisedTask2.mNextTask.onError(promisedTask2.mErrorCode.intValue());
            } else {
                PromisedTask.this.mNextTask.onCancelled();
            }
            PromisedTask.this.uninit();
        }

        @Override // android.os.AsyncTask
        public void onPostExecute(Object obj) {
            PromisedTask.this.onPostExecute(obj);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.os.AsyncTask
        public void onProgressUpdate(Object... objArr) {
            PromisedTask.this.onProgressUpdate(objArr[0]);
        }
    }

    /* renamed from: com.perfectcorp.utility.PromisedTask$d */
    public static abstract class AbstractC4504d<TResult2> extends PromisedTask<TResult2, Void, TResult2> {
        @Override // com.perfectcorp.utility.PromisedTask
        public TResult2 doInBackground(TResult2 tresult2) {
            return tresult2;
        }

        public abstract void onDone(TResult2 tresult2);

        @Override // com.perfectcorp.utility.PromisedTask
        public synchronized void onPostExecute(TResult2 tresult2) {
            onDone(tresult2);
        }
    }

    /* renamed from: com.perfectcorp.utility.PromisedTask$e */
    public interface InterfaceC4505e {
        /* renamed from: a */
        void mo18097a(Object obj);
    }

    static {
        int iAvailableProcessors = Runtime.getRuntime().availableProcessors();
        CPU_COUNT = iAvailableProcessors;
        int i9 = iAvailableProcessors + 1;
        CORE_POOL_SIZE = i9;
        int i10 = (iAvailableProcessors * 2) + 1;
        MAXIMUM_POOL_SIZE = i10;
        ThreadFactoryC4501a threadFactoryC4501a = new ThreadFactoryC4501a();
        sThreadFactory = threadFactoryC4501a;
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(128);
        sPoolWorkQueue = linkedBlockingQueue;
        TRACED_EXECUTOR = new C4502b(i9, i10, 1L, TimeUnit.SECONDS, linkedBlockingQueue, threadFactoryC4501a);
    }

    public PromisedTask() {
        this.mTask = null;
        this.mTask = new AsyncTaskC4503c();
    }

    private int getErrorCodeByMsg(int i9, String str) {
        if (C4509d.m18120b(str)) {
            return i9;
        }
        C4507b.m18101b("[PromisedTask] errorMsg : " + str);
        if (str.contains("User count limit exceeded")) {
            return 4031;
        }
        if (str.contains("Registration required")) {
            return 4032;
        }
        if (str.contains("Registration is under process")) {
            return 4033;
        }
        return i9;
    }

    public final boolean cancel(boolean z8) {
        return this.mTask.cancel(z8);
    }

    public abstract TResult doInBackground(TParam tparam);

    public final synchronized void done(AbstractC4504d<TResult> abstractC4504d) {
        this.mNextTask = abstractC4504d;
        if (this.mIsExecuted.booleanValue()) {
            this.mNextTask.executeOnExecutor(this.mExecutor, this.mResult);
        }
    }

    public final PromisedTask<TParam, TProgress, TResult> execute(TParam tparam) {
        return executeOnExecutor(this.mExecutor, tparam);
    }

    public final PromisedTask<TParam, TProgress, TResult> executeOnExecutor(Executor executor, TParam tparam) {
        this.mExecutor = executor;
        if (!this.mTask.isCancelled()) {
            this.mTask.executeOnExecutor(executor, tparam);
        }
        return this;
    }

    public final TResult get() {
        return (TResult) this.mTask.get();
    }

    public final boolean isCancelled() {
        return this.mTask.isCancelled();
    }

    public void onCancelled() {
        PromisedTask<TResult, ?, ?> promisedTask = this.mNextTask;
        if (promisedTask != null) {
            promisedTask.onCancelled();
        }
        uninit();
    }

    public void onError(int i9) {
        this.mErrorCode = Integer.valueOf(i9);
        cancel(true);
    }

    public synchronized void onPostExecute(TResult tresult) {
        this.mResult = tresult;
        PromisedTask<TResult, ?, ?> promisedTask = this.mNextTask;
        if (promisedTask != null) {
            promisedTask.executeOnExecutor(this.mExecutor, tresult);
        }
        this.mIsExecuted = Boolean.TRUE;
    }

    public void onProgressUpdate(TProgress tprogress) {
    }

    public final void reportError(int i9) {
        reportError(i9, "");
    }

    public void reportProgress(TProgress tprogress) {
        this.mProgressReporter.mo18097a(tprogress);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final synchronized <TProgress2, TResult2> PromisedTask<TResult, TProgress2, TResult2> then(PromisedTask<TResult, TProgress2, TResult2> promisedTask) {
        this.mNextTask = promisedTask;
        if (this.mIsExecuted.booleanValue()) {
            this.mNextTask.executeOnExecutor(this.mExecutor, this.mResult);
        }
        return promisedTask;
    }

    public void uninit() {
        this.mNextTask = null;
    }

    public final TResult get(long j9, TimeUnit timeUnit) {
        return (TResult) this.mTask.get(j9, timeUnit);
    }

    public final void reportError(int i9, String str) {
        onError(getErrorCodeByMsg(i9, str));
    }
}
