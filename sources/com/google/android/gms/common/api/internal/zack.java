package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.ref.WeakReference;

/* loaded from: classes2.dex */
public final class zack<R extends Result> extends TransformedResult<R> implements ResultCallback<R> {
    private final WeakReference<GoogleApiClient> zag;
    private final zacm zah;
    private ResultTransform<? super R, ? extends Result> zaa = null;
    private zack<? extends Result> zab = null;
    private volatile ResultCallbacks<? super R> zac = null;
    private PendingResult<R> zad = null;
    private final Object zae = new Object();
    private Status zaf = null;
    private boolean zai = false;

    public zack(WeakReference<GoogleApiClient> weakReference) {
        Preconditions.checkNotNull(weakReference, "GoogleApiClient reference must not be null");
        this.zag = weakReference;
        GoogleApiClient googleApiClient = weakReference.get();
        this.zah = new zacm(this, googleApiClient != null ? googleApiClient.getLooper() : Looper.getMainLooper());
    }

    private final void zab() {
        if (this.zaa == null && this.zac == null) {
            return;
        }
        GoogleApiClient googleApiClient = this.zag.get();
        if (!this.zai && this.zaa != null && googleApiClient != null) {
            googleApiClient.zaa(this);
            this.zai = true;
        }
        Status status = this.zaf;
        if (status != null) {
            zab(status);
            return;
        }
        PendingResult<R> pendingResult = this.zad;
        if (pendingResult != null) {
            pendingResult.setResultCallback(this);
        }
    }

    private final boolean zac() {
        return (this.zac == null || this.zag.get() == null) ? false : true;
    }

    @Override // com.google.android.gms.common.api.TransformedResult
    public final void andFinally(ResultCallbacks<? super R> resultCallbacks) {
        synchronized (this.zae) {
            boolean z8 = true;
            Preconditions.checkState(this.zac == null, "Cannot call andFinally() twice.");
            if (this.zaa != null) {
                z8 = false;
            }
            Preconditions.checkState(z8, "Cannot call then() and andFinally() on the same TransformedResult.");
            this.zac = resultCallbacks;
            zab();
        }
    }

    @Override // com.google.android.gms.common.api.ResultCallback
    public final void onResult(R r8) {
        synchronized (this.zae) {
            if (!r8.getStatus().isSuccess()) {
                zaa(r8.getStatus());
                zaa(r8);
            } else if (this.zaa != null) {
                zaca.zaa().submit(new zacj(this, r8));
            } else if (zac()) {
                ((ResultCallbacks) Preconditions.checkNotNull(this.zac)).onSuccess(r8);
            }
        }
    }

    @Override // com.google.android.gms.common.api.TransformedResult
    public final <S extends Result> TransformedResult<S> then(ResultTransform<? super R, ? extends S> resultTransform) {
        zack<? extends Result> zackVar;
        synchronized (this.zae) {
            boolean z8 = true;
            Preconditions.checkState(this.zaa == null, "Cannot call then() twice.");
            if (this.zac != null) {
                z8 = false;
            }
            Preconditions.checkState(z8, "Cannot call then() and andFinally() on the same TransformedResult.");
            this.zaa = resultTransform;
            zackVar = new zack<>(this.zag);
            this.zab = zackVar;
            zab();
        }
        return zackVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void zaa(PendingResult<?> pendingResult) {
        synchronized (this.zae) {
            this.zad = pendingResult;
            zab();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zaa(Status status) {
        synchronized (this.zae) {
            this.zaf = status;
            zab(status);
        }
    }

    public final void zaa() {
        this.zac = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zaa(Result result) {
        if (result instanceof Releasable) {
            try {
                ((Releasable) result).release();
            } catch (RuntimeException e9) {
                String strValueOf = String.valueOf(result);
                StringBuilder sb = new StringBuilder(strValueOf.length() + 18);
                sb.append("Unable to release ");
                sb.append(strValueOf);
                Log.w("TransformedResultImpl", sb.toString(), e9);
            }
        }
    }

    private final void zab(Status status) {
        synchronized (this.zae) {
            ResultTransform<? super R, ? extends Result> resultTransform = this.zaa;
            if (resultTransform != null) {
                ((zack) Preconditions.checkNotNull(this.zab)).zaa((Status) Preconditions.checkNotNull(resultTransform.onFailure(status), "onFailure must not return null"));
            } else if (zac()) {
                ((ResultCallbacks) Preconditions.checkNotNull(this.zac)).onFailure(status);
            }
        }
    }
}
