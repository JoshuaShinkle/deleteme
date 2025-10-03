package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.ICancelToken;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@KeepForSdk
@KeepName
/* loaded from: classes2.dex */
public abstract class BasePendingResult<R extends Result> extends PendingResult<R> {
    static final ThreadLocal<Boolean> zaa = new zao();

    @KeepName
    private zaa mResultGuardian;
    private final Object zab;
    private final CallbackHandler<R> zac;
    private final WeakReference<GoogleApiClient> zad;
    private final CountDownLatch zae;
    private final ArrayList<PendingResult.StatusListener> zaf;
    private ResultCallback<? super R> zag;
    private final AtomicReference<zacn> zah;
    private R zai;
    private Status zaj;
    private volatile boolean zak;
    private boolean zal;
    private boolean zam;
    private ICancelToken zan;
    private volatile zack<R> zao;
    private boolean zap;

    @VisibleForTesting
    public static class CallbackHandler<R extends Result> extends com.google.android.gms.internal.base.zap {
        public CallbackHandler() {
            this(Looper.getMainLooper());
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.os.Handler
        public void handleMessage(@RecentlyNonNull Message message) {
            int i9 = message.what;
            if (i9 != 1) {
                if (i9 == 2) {
                    ((BasePendingResult) message.obj).forceFailureUnlessReady(Status.RESULT_TIMEOUT);
                    return;
                }
                StringBuilder sb = new StringBuilder(45);
                sb.append("Don't know how to handle message: ");
                sb.append(i9);
                Log.wtf("BasePendingResult", sb.toString(), new Exception());
                return;
            }
            Pair pair = (Pair) message.obj;
            ResultCallback resultCallback = (ResultCallback) pair.first;
            Result result = (Result) pair.second;
            try {
                resultCallback.onResult(result);
            } catch (RuntimeException e9) {
                BasePendingResult.zaa(result);
                throw e9;
            }
        }

        public final void zaa(@RecentlyNonNull ResultCallback<? super R> resultCallback, @RecentlyNonNull R r8) {
            sendMessage(obtainMessage(1, new Pair((ResultCallback) Preconditions.checkNotNull(BasePendingResult.zab(resultCallback)), r8)));
        }

        public CallbackHandler(@RecentlyNonNull Looper looper) {
            super(looper);
        }
    }

    public final class zaa {
        private zaa() {
        }

        public final void finalize() throws Throwable {
            BasePendingResult.zaa(BasePendingResult.this.zai);
            super.finalize();
        }

        public /* synthetic */ zaa(BasePendingResult basePendingResult, zao zaoVar) {
            this();
        }
    }

    @Deprecated
    public BasePendingResult() {
        this.zab = new Object();
        this.zae = new CountDownLatch(1);
        this.zaf = new ArrayList<>();
        this.zah = new AtomicReference<>();
        this.zap = false;
        this.zac = new CallbackHandler<>(Looper.getMainLooper());
        this.zad = new WeakReference<>(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <R extends Result> ResultCallback<R> zab(ResultCallback<R> resultCallback) {
        return resultCallback;
    }

    private final R zac() {
        R r8;
        synchronized (this.zab) {
            Preconditions.checkState(!this.zak, "Result has already been consumed.");
            Preconditions.checkState(isReady(), "Result is not ready.");
            r8 = this.zai;
            this.zai = null;
            this.zag = null;
            this.zak = true;
        }
        zacn andSet = this.zah.getAndSet(null);
        if (andSet != null) {
            andSet.zaa(this);
        }
        return (R) Preconditions.checkNotNull(r8);
    }

    @Override // com.google.android.gms.common.api.PendingResult
    public final void addStatusListener(@RecentlyNonNull PendingResult.StatusListener statusListener) {
        Preconditions.checkArgument(statusListener != null, "Callback cannot be null.");
        synchronized (this.zab) {
            if (isReady()) {
                statusListener.onComplete(this.zaj);
            } else {
                this.zaf.add(statusListener);
            }
        }
    }

    @Override // com.google.android.gms.common.api.PendingResult
    @RecentlyNonNull
    public final R await() throws InterruptedException {
        Preconditions.checkNotMainThread("await must not be called on the UI thread");
        Preconditions.checkState(!this.zak, "Result has already been consumed");
        Preconditions.checkState(this.zao == null, "Cannot await if then() has been called.");
        try {
            this.zae.await();
        } catch (InterruptedException unused) {
            forceFailureUnlessReady(Status.RESULT_INTERRUPTED);
        }
        Preconditions.checkState(isReady(), "Result is not ready.");
        return (R) zac();
    }

    @Override // com.google.android.gms.common.api.PendingResult
    @KeepForSdk
    public void cancel() {
        synchronized (this.zab) {
            if (!this.zal && !this.zak) {
                ICancelToken iCancelToken = this.zan;
                if (iCancelToken != null) {
                    try {
                        iCancelToken.cancel();
                    } catch (RemoteException unused) {
                    }
                }
                zaa(this.zai);
                this.zal = true;
                zab((BasePendingResult<R>) createFailedResult(Status.RESULT_CANCELED));
            }
        }
    }

    @KeepForSdk
    public abstract R createFailedResult(@RecentlyNonNull Status status);

    @KeepForSdk
    @Deprecated
    public final void forceFailureUnlessReady(@RecentlyNonNull Status status) {
        synchronized (this.zab) {
            if (!isReady()) {
                setResult(createFailedResult(status));
                this.zam = true;
            }
        }
    }

    @Override // com.google.android.gms.common.api.PendingResult
    @RecentlyNonNull
    public boolean isCanceled() {
        boolean z8;
        synchronized (this.zab) {
            z8 = this.zal;
        }
        return z8;
    }

    @RecentlyNonNull
    @KeepForSdk
    public final boolean isReady() {
        return this.zae.getCount() == 0;
    }

    @KeepForSdk
    public final void setCancelToken(@RecentlyNonNull ICancelToken iCancelToken) {
        synchronized (this.zab) {
            this.zan = iCancelToken;
        }
    }

    @KeepForSdk
    public final void setResult(@RecentlyNonNull R r8) {
        synchronized (this.zab) {
            if (this.zam || this.zal) {
                zaa(r8);
                return;
            }
            isReady();
            boolean z8 = true;
            Preconditions.checkState(!isReady(), "Results have already been set");
            if (this.zak) {
                z8 = false;
            }
            Preconditions.checkState(z8, "Result has already been consumed");
            zab((BasePendingResult<R>) r8);
        }
    }

    @Override // com.google.android.gms.common.api.PendingResult
    @KeepForSdk
    public final void setResultCallback(ResultCallback<? super R> resultCallback) {
        synchronized (this.zab) {
            if (resultCallback == null) {
                this.zag = null;
                return;
            }
            boolean z8 = true;
            Preconditions.checkState(!this.zak, "Result has already been consumed.");
            if (this.zao != null) {
                z8 = false;
            }
            Preconditions.checkState(z8, "Cannot set callbacks if then() has been called.");
            if (isCanceled()) {
                return;
            }
            if (isReady()) {
                this.zac.zaa(resultCallback, zac());
            } else {
                this.zag = resultCallback;
            }
        }
    }

    @Override // com.google.android.gms.common.api.PendingResult
    @RecentlyNonNull
    public <S extends Result> TransformedResult<S> then(@RecentlyNonNull ResultTransform<? super R, ? extends S> resultTransform) {
        TransformedResult<S> transformedResultThen;
        Preconditions.checkState(!this.zak, "Result has already been consumed.");
        synchronized (this.zab) {
            Preconditions.checkState(this.zao == null, "Cannot call then() twice.");
            Preconditions.checkState(this.zag == null, "Cannot call then() if callbacks are set.");
            Preconditions.checkState(this.zal ? false : true, "Cannot call then() if result was canceled.");
            this.zap = true;
            this.zao = new zack<>(this.zad);
            transformedResultThen = this.zao.then(resultTransform);
            if (isReady()) {
                this.zac.zaa(this.zao, zac());
            } else {
                this.zag = this.zao;
            }
        }
        return transformedResultThen;
    }

    @RecentlyNonNull
    public final boolean zaa() {
        boolean zIsCanceled;
        synchronized (this.zab) {
            if (this.zad.get() == null || !this.zap) {
                cancel();
            }
            zIsCanceled = isCanceled();
        }
        return zIsCanceled;
    }

    public final void zab() {
        this.zap = this.zap || zaa.get().booleanValue();
    }

    private final void zab(R r8) {
        this.zai = r8;
        this.zaj = r8.getStatus();
        zao zaoVar = null;
        this.zan = null;
        this.zae.countDown();
        if (this.zal) {
            this.zag = null;
        } else {
            ResultCallback<? super R> resultCallback = this.zag;
            if (resultCallback != null) {
                this.zac.removeMessages(2);
                this.zac.zaa(resultCallback, zac());
            } else if (this.zai instanceof Releasable) {
                this.mResultGuardian = new zaa(this, zaoVar);
            }
        }
        ArrayList<PendingResult.StatusListener> arrayList = this.zaf;
        int size = arrayList.size();
        int i9 = 0;
        while (i9 < size) {
            PendingResult.StatusListener statusListener = arrayList.get(i9);
            i9++;
            statusListener.onComplete(this.zaj);
        }
        this.zaf.clear();
    }

    public final void zaa(zacn zacnVar) {
        this.zah.set(zacnVar);
    }

    public static void zaa(Result result) {
        if (result instanceof Releasable) {
            try {
                ((Releasable) result).release();
            } catch (RuntimeException e9) {
                String strValueOf = String.valueOf(result);
                StringBuilder sb = new StringBuilder(strValueOf.length() + 18);
                sb.append("Unable to release ");
                sb.append(strValueOf);
                Log.w("BasePendingResult", sb.toString(), e9);
            }
        }
    }

    @Override // com.google.android.gms.common.api.PendingResult
    @RecentlyNonNull
    public final R await(@RecentlyNonNull long j9, @RecentlyNonNull TimeUnit timeUnit) {
        if (j9 > 0) {
            Preconditions.checkNotMainThread("await must not be called on the UI thread when time is greater than zero.");
        }
        Preconditions.checkState(!this.zak, "Result has already been consumed.");
        Preconditions.checkState(this.zao == null, "Cannot await if then() has been called.");
        try {
            if (!this.zae.await(j9, timeUnit)) {
                forceFailureUnlessReady(Status.RESULT_TIMEOUT);
            }
        } catch (InterruptedException unused) {
            forceFailureUnlessReady(Status.RESULT_INTERRUPTED);
        }
        Preconditions.checkState(isReady(), "Result is not ready.");
        return (R) zac();
    }

    @KeepForSdk
    public BasePendingResult(GoogleApiClient googleApiClient) {
        this.zab = new Object();
        this.zae = new CountDownLatch(1);
        this.zaf = new ArrayList<>();
        this.zah = new AtomicReference<>();
        this.zap = false;
        this.zac = new CallbackHandler<>(googleApiClient != null ? googleApiClient.getLooper() : Looper.getMainLooper());
        this.zad = new WeakReference<>(googleApiClient);
    }

    @Override // com.google.android.gms.common.api.PendingResult
    @KeepForSdk
    public final void setResultCallback(@RecentlyNonNull ResultCallback<? super R> resultCallback, @RecentlyNonNull long j9, @RecentlyNonNull TimeUnit timeUnit) {
        synchronized (this.zab) {
            if (resultCallback == null) {
                this.zag = null;
                return;
            }
            boolean z8 = true;
            Preconditions.checkState(!this.zak, "Result has already been consumed.");
            if (this.zao != null) {
                z8 = false;
            }
            Preconditions.checkState(z8, "Cannot set callbacks if then() has been called.");
            if (isCanceled()) {
                return;
            }
            if (isReady()) {
                this.zac.zaa(resultCallback, zac());
            } else {
                this.zag = resultCallback;
                CallbackHandler<R> callbackHandler = this.zac;
                callbackHandler.sendMessageDelayed(callbackHandler.obtainMessage(2, this), timeUnit.toMillis(j9));
            }
        }
    }

    @KeepForSdk
    @Deprecated
    public BasePendingResult(@RecentlyNonNull Looper looper) {
        this.zab = new Object();
        this.zae = new CountDownLatch(1);
        this.zaf = new ArrayList<>();
        this.zah = new AtomicReference<>();
        this.zap = false;
        this.zac = new CallbackHandler<>(looper);
        this.zad = new WeakReference<>(null);
    }

    @VisibleForTesting
    @KeepForSdk
    public BasePendingResult(@RecentlyNonNull CallbackHandler<R> callbackHandler) {
        this.zab = new Object();
        this.zae = new CountDownLatch(1);
        this.zaf = new ArrayList<>();
        this.zah = new AtomicReference<>();
        this.zap = false;
        this.zac = (CallbackHandler) Preconditions.checkNotNull(callbackHandler, "CallbackHandler must not be null");
        this.zad = new WeakReference<>(null);
    }
}
