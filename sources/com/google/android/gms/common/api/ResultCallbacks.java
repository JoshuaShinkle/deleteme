package com.google.android.gms.common.api;

import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Result;

/* loaded from: classes2.dex */
public abstract class ResultCallbacks<R extends Result> implements ResultCallback<R> {
    public abstract void onFailure(Status status);

    @Override // com.google.android.gms.common.api.ResultCallback
    @KeepForSdk
    public final void onResult(R r8) {
        Status status = r8.getStatus();
        if (status.isSuccess()) {
            onSuccess(r8);
            return;
        }
        onFailure(status);
        if (r8 instanceof Releasable) {
            try {
                ((Releasable) r8).release();
            } catch (RuntimeException e9) {
                Log.w("ResultCallbacks", "Unable to release ".concat(String.valueOf(r8)), e9);
            }
        }
    }

    public abstract void onSuccess(R r8);
}
