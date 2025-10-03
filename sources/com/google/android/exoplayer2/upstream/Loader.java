package com.google.android.exoplayer2.upstream;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.TraceUtil;
import com.google.android.exoplayer2.util.Util;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import java.io.IOException;
import java.util.concurrent.ExecutorService;

/* loaded from: classes.dex */
public final class Loader implements LoaderErrorThrower {
    public static final int DONT_RETRY = 2;
    public static final int DONT_RETRY_FATAL = 3;
    public static final int RETRY = 0;
    public static final int RETRY_RESET_ERROR_COUNT = 1;
    private LoadTask<? extends Loadable> currentTask;
    private final ExecutorService downloadExecutorService;
    private IOException fatalError;

    public interface Callback<T extends Loadable> {
        void onLoadCanceled(T t8, long j9, long j10, boolean z8);

        void onLoadCompleted(T t8, long j9, long j10);

        int onLoadError(T t8, long j9, long j10, IOException iOException);
    }

    @SuppressLint({"HandlerLeak"})
    public final class LoadTask<T extends Loadable> extends Handler implements Runnable {
        private static final int MSG_CANCEL = 1;
        private static final int MSG_END_OF_SOURCE = 2;
        private static final int MSG_FATAL_ERROR = 4;
        private static final int MSG_IO_EXCEPTION = 3;
        private static final int MSG_START = 0;
        private static final String TAG = "LoadTask";
        private final Callback<T> callback;
        private IOException currentError;
        public final int defaultMinRetryCount;
        private int errorCount;
        private volatile Thread executorThread;
        private final T loadable;
        private volatile boolean released;
        private final long startTimeMs;

        public LoadTask(Looper looper, T t8, Callback<T> callback, int i9, long j9) {
            super(looper);
            this.loadable = t8;
            this.callback = callback;
            this.defaultMinRetryCount = i9;
            this.startTimeMs = j9;
        }

        private void execute() {
            this.currentError = null;
            Loader.this.downloadExecutorService.execute(Loader.this.currentTask);
        }

        private void finish() {
            Loader.this.currentTask = null;
        }

        private long getRetryDelayMillis() {
            return Math.min((this.errorCount - 1) * CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT, 5000);
        }

        public void cancel(boolean z8) {
            this.released = z8;
            this.currentError = null;
            if (hasMessages(0)) {
                removeMessages(0);
                if (!z8) {
                    sendEmptyMessage(1);
                }
            } else {
                this.loadable.cancelLoad();
                if (this.executorThread != null) {
                    this.executorThread.interrupt();
                }
            }
            if (z8) {
                finish();
                long jElapsedRealtime = SystemClock.elapsedRealtime();
                this.callback.onLoadCanceled(this.loadable, jElapsedRealtime, jElapsedRealtime - this.startTimeMs, true);
            }
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (this.released) {
                return;
            }
            int i9 = message.what;
            if (i9 == 0) {
                execute();
                return;
            }
            if (i9 == 4) {
                throw ((Error) message.obj);
            }
            finish();
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            long j9 = jElapsedRealtime - this.startTimeMs;
            if (this.loadable.isLoadCanceled()) {
                this.callback.onLoadCanceled(this.loadable, jElapsedRealtime, j9, false);
                return;
            }
            int i10 = message.what;
            if (i10 == 1) {
                this.callback.onLoadCanceled(this.loadable, jElapsedRealtime, j9, false);
                return;
            }
            if (i10 == 2) {
                try {
                    this.callback.onLoadCompleted(this.loadable, jElapsedRealtime, j9);
                    return;
                } catch (RuntimeException e9) {
                    Log.e(TAG, "Unexpected exception handling load completed", e9);
                    Loader.this.fatalError = new UnexpectedLoaderException(e9);
                    return;
                }
            }
            if (i10 != 3) {
                return;
            }
            IOException iOException = (IOException) message.obj;
            this.currentError = iOException;
            int iOnLoadError = this.callback.onLoadError(this.loadable, jElapsedRealtime, j9, iOException);
            if (iOnLoadError == 3) {
                Loader.this.fatalError = this.currentError;
            } else if (iOnLoadError != 2) {
                this.errorCount = iOnLoadError != 1 ? 1 + this.errorCount : 1;
                start(getRetryDelayMillis());
            }
        }

        public void maybeThrowError(int i9) throws IOException {
            IOException iOException = this.currentError;
            if (iOException != null && this.errorCount > i9) {
                throw iOException;
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.executorThread = Thread.currentThread();
                if (!this.loadable.isLoadCanceled()) {
                    TraceUtil.beginSection("load:" + this.loadable.getClass().getSimpleName());
                    try {
                        this.loadable.load();
                        TraceUtil.endSection();
                    } catch (Throwable th) {
                        TraceUtil.endSection();
                        throw th;
                    }
                }
                if (this.released) {
                    return;
                }
                sendEmptyMessage(2);
            } catch (IOException e9) {
                if (this.released) {
                    return;
                }
                obtainMessage(3, e9).sendToTarget();
            } catch (OutOfMemoryError e10) {
                Log.e(TAG, "OutOfMemory error loading stream", e10);
                if (this.released) {
                    return;
                }
                obtainMessage(3, new UnexpectedLoaderException(e10)).sendToTarget();
            } catch (Error e11) {
                Log.e(TAG, "Unexpected error loading stream", e11);
                if (!this.released) {
                    obtainMessage(4, e11).sendToTarget();
                }
                throw e11;
            } catch (InterruptedException unused) {
                Assertions.checkState(this.loadable.isLoadCanceled());
                if (this.released) {
                    return;
                }
                sendEmptyMessage(2);
            } catch (Exception e12) {
                Log.e(TAG, "Unexpected exception loading stream", e12);
                if (this.released) {
                    return;
                }
                obtainMessage(3, new UnexpectedLoaderException(e12)).sendToTarget();
            }
        }

        public void start(long j9) {
            Assertions.checkState(Loader.this.currentTask == null);
            Loader.this.currentTask = this;
            if (j9 > 0) {
                sendEmptyMessageDelayed(0, j9);
            } else {
                execute();
            }
        }
    }

    public interface Loadable {
        void cancelLoad();

        boolean isLoadCanceled();

        void load();
    }

    public interface ReleaseCallback {
        void onLoaderReleased();
    }

    public static final class ReleaseTask implements Runnable {
        private final ReleaseCallback callback;

        public ReleaseTask(ReleaseCallback releaseCallback) {
            this.callback = releaseCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.callback.onLoaderReleased();
        }
    }

    public static final class UnexpectedLoaderException extends IOException {
        public UnexpectedLoaderException(Throwable th) {
            super("Unexpected " + th.getClass().getSimpleName() + ": " + th.getMessage(), th);
        }
    }

    public Loader(String str) {
        this.downloadExecutorService = Util.newSingleThreadExecutor(str);
    }

    public void cancelLoading() {
        this.currentTask.cancel(false);
    }

    public boolean isLoading() {
        return this.currentTask != null;
    }

    @Override // com.google.android.exoplayer2.upstream.LoaderErrorThrower
    public void maybeThrowError() throws IOException {
        maybeThrowError(Integer.MIN_VALUE);
    }

    public void release() {
        release(null);
    }

    public <T extends Loadable> long startLoading(T t8, Callback<T> callback, int i9) {
        Looper looperMyLooper = Looper.myLooper();
        Assertions.checkState(looperMyLooper != null);
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        new LoadTask(looperMyLooper, t8, callback, i9, jElapsedRealtime).start(0L);
        return jElapsedRealtime;
    }

    @Override // com.google.android.exoplayer2.upstream.LoaderErrorThrower
    public void maybeThrowError(int i9) throws IOException {
        IOException iOException = this.fatalError;
        if (iOException != null) {
            throw iOException;
        }
        LoadTask<? extends Loadable> loadTask = this.currentTask;
        if (loadTask != null) {
            if (i9 == Integer.MIN_VALUE) {
                i9 = loadTask.defaultMinRetryCount;
            }
            loadTask.maybeThrowError(i9);
        }
    }

    public void release(ReleaseCallback releaseCallback) {
        LoadTask<? extends Loadable> loadTask = this.currentTask;
        if (loadTask != null) {
            loadTask.cancel(true);
        }
        if (releaseCallback != null) {
            this.downloadExecutorService.execute(new ReleaseTask(releaseCallback));
        }
        this.downloadExecutorService.shutdown();
    }
}
