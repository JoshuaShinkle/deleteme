package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes2.dex */
public final class zaf<ResultT> extends zab {
    private final TaskApiCall<Api.AnyClient, ResultT> zab;
    private final TaskCompletionSource<ResultT> zac;
    private final StatusExceptionMapper zad;

    public zaf(int i9, TaskApiCall<Api.AnyClient, ResultT> taskApiCall, TaskCompletionSource<ResultT> taskCompletionSource, StatusExceptionMapper statusExceptionMapper) {
        super(i9);
        this.zac = taskCompletionSource;
        this.zab = taskApiCall;
        this.zad = statusExceptionMapper;
        if (i9 == 2 && taskApiCall.shouldAutoResolveMissingFeatures()) {
            throw new IllegalArgumentException("Best-effort write calls cannot pass methods that should auto-resolve missing features.");
        }
    }

    @Override // com.google.android.gms.common.api.internal.zac
    public final void zaa(Status status) {
        this.zac.trySetException(this.zad.getException(status));
    }

    @Override // com.google.android.gms.common.api.internal.zab
    public final boolean zab(GoogleApiManager.zaa<?> zaaVar) {
        return this.zab.shouldAutoResolveMissingFeatures();
    }

    @Override // com.google.android.gms.common.api.internal.zac
    public final void zac(GoogleApiManager.zaa<?> zaaVar) throws DeadObjectException {
        try {
            this.zab.doExecute(zaaVar.zab(), this.zac);
        } catch (DeadObjectException e9) {
            throw e9;
        } catch (RemoteException e10) {
            zaa(zac.zab(e10));
        } catch (RuntimeException e11) {
            zaa(e11);
        }
    }

    @Override // com.google.android.gms.common.api.internal.zac
    public final void zaa(Exception exc) {
        this.zac.trySetException(exc);
    }

    @Override // com.google.android.gms.common.api.internal.zac
    public final void zaa(zaw zawVar, boolean z8) {
        zawVar.zaa(this.zac, z8);
    }

    @Override // com.google.android.gms.common.api.internal.zab
    public final Feature[] zaa(GoogleApiManager.zaa<?> zaaVar) {
        return this.zab.zaa();
    }
}
