package androidx.loader.content;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import androidx.core.os.OperationCanceledException;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import p021c0.C0703i;

/* renamed from: androidx.loader.content.a */
/* loaded from: classes.dex */
public abstract class AbstractC0405a<D> extends C0406b<D> {
    static final boolean DEBUG = false;
    static final String TAG = "AsyncTaskLoader";
    volatile AbstractC0405a<D>.a mCancellingTask;
    private final Executor mExecutor;
    Handler mHandler;
    long mLastLoadCompleteTime;
    volatile AbstractC0405a<D>.a mTask;
    long mUpdateThrottle;

    /* renamed from: androidx.loader.content.a$a */
    public final class a extends ModernAsyncTask<Void, Void, D> implements Runnable {

        /* renamed from: l */
        public final CountDownLatch f2257l = new CountDownLatch(1);

        /* renamed from: m */
        public boolean f2258m;

        public a() {
        }

        @Override // androidx.loader.content.ModernAsyncTask
        /* renamed from: h */
        public void mo2126h(D d9) {
            try {
                AbstractC0405a.this.dispatchOnCancelled(this, d9);
            } finally {
                this.f2257l.countDown();
            }
        }

        @Override // androidx.loader.content.ModernAsyncTask
        /* renamed from: i */
        public void mo2127i(D d9) {
            try {
                AbstractC0405a.this.dispatchOnLoadComplete(this, d9);
            } finally {
                this.f2257l.countDown();
            }
        }

        @Override // androidx.loader.content.ModernAsyncTask
        /* renamed from: n, reason: merged with bridge method [inline-methods] */
        public D mo2121b(Void... voidArr) {
            try {
                return (D) AbstractC0405a.this.onLoadInBackground();
            } catch (OperationCanceledException e9) {
                if (m2124f()) {
                    return null;
                }
                throw e9;
            }
        }

        /* renamed from: o */
        public void m2133o() throws InterruptedException {
            try {
                this.f2257l.await();
            } catch (InterruptedException unused) {
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f2258m = false;
            AbstractC0405a.this.executePendingTask();
        }
    }

    public AbstractC0405a(Context context) {
        this(context, ModernAsyncTask.f2238i);
    }

    public void cancelLoadInBackground() {
    }

    public void dispatchOnCancelled(AbstractC0405a<D>.a aVar, D d9) {
        onCanceled(d9);
        if (this.mCancellingTask == aVar) {
            rollbackContentChanged();
            this.mLastLoadCompleteTime = SystemClock.uptimeMillis();
            this.mCancellingTask = null;
            deliverCancellation();
            executePendingTask();
        }
    }

    public void dispatchOnLoadComplete(AbstractC0405a<D>.a aVar, D d9) {
        if (this.mTask != aVar) {
            dispatchOnCancelled(aVar, d9);
            return;
        }
        if (isAbandoned()) {
            onCanceled(d9);
            return;
        }
        commitContentChanged();
        this.mLastLoadCompleteTime = SystemClock.uptimeMillis();
        this.mTask = null;
        deliverResult(d9);
    }

    @Override // androidx.loader.content.C0406b
    @Deprecated
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        if (this.mTask != null) {
            printWriter.print(str);
            printWriter.print("mTask=");
            printWriter.print(this.mTask);
            printWriter.print(" waiting=");
            printWriter.println(this.mTask.f2258m);
        }
        if (this.mCancellingTask != null) {
            printWriter.print(str);
            printWriter.print("mCancellingTask=");
            printWriter.print(this.mCancellingTask);
            printWriter.print(" waiting=");
            printWriter.println(this.mCancellingTask.f2258m);
        }
        if (this.mUpdateThrottle != 0) {
            printWriter.print(str);
            printWriter.print("mUpdateThrottle=");
            C0703i.m3472c(this.mUpdateThrottle, printWriter);
            printWriter.print(" mLastLoadCompleteTime=");
            C0703i.m3471b(this.mLastLoadCompleteTime, SystemClock.uptimeMillis(), printWriter);
            printWriter.println();
        }
    }

    public void executePendingTask() {
        if (this.mCancellingTask != null || this.mTask == null) {
            return;
        }
        if (this.mTask.f2258m) {
            this.mTask.f2258m = false;
            this.mHandler.removeCallbacks(this.mTask);
        }
        if (this.mUpdateThrottle <= 0 || SystemClock.uptimeMillis() >= this.mLastLoadCompleteTime + this.mUpdateThrottle) {
            this.mTask.m2122c(this.mExecutor, null);
        } else {
            this.mTask.f2258m = true;
            this.mHandler.postAtTime(this.mTask, this.mLastLoadCompleteTime + this.mUpdateThrottle);
        }
    }

    public boolean isLoadInBackgroundCanceled() {
        return this.mCancellingTask != null;
    }

    public abstract D loadInBackground();

    @Override // androidx.loader.content.C0406b
    public boolean onCancelLoad() {
        if (this.mTask == null) {
            return false;
        }
        if (!this.mStarted) {
            this.mContentChanged = true;
        }
        if (this.mCancellingTask != null) {
            if (this.mTask.f2258m) {
                this.mTask.f2258m = false;
                this.mHandler.removeCallbacks(this.mTask);
            }
            this.mTask = null;
            return false;
        }
        if (this.mTask.f2258m) {
            this.mTask.f2258m = false;
            this.mHandler.removeCallbacks(this.mTask);
            this.mTask = null;
            return false;
        }
        boolean zM2120a = this.mTask.m2120a(false);
        if (zM2120a) {
            this.mCancellingTask = this.mTask;
            cancelLoadInBackground();
        }
        this.mTask = null;
        return zM2120a;
    }

    public void onCanceled(D d9) {
    }

    @Override // androidx.loader.content.C0406b
    public void onForceLoad() {
        super.onForceLoad();
        cancelLoad();
        this.mTask = new a();
        executePendingTask();
    }

    public D onLoadInBackground() {
        return loadInBackground();
    }

    public void setUpdateThrottle(long j9) {
        this.mUpdateThrottle = j9;
        if (j9 != 0) {
            this.mHandler = new Handler();
        }
    }

    public void waitForLoader() throws InterruptedException {
        AbstractC0405a<D>.a aVar = this.mTask;
        if (aVar != null) {
            aVar.m2133o();
        }
    }

    private AbstractC0405a(Context context, Executor executor) {
        super(context);
        this.mLastLoadCompleteTime = -10000L;
        this.mExecutor = executor;
    }
}
