package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes2.dex */
abstract class zae<T> extends zab {
    protected final TaskCompletionSource<T> zab;

    public zae(int i9, TaskCompletionSource<T> taskCompletionSource) {
        super(i9);
        this.zab = taskCompletionSource;
    }

    @Override // com.google.android.gms.common.api.internal.zac
    public void zaa(Status status) {
        this.zab.trySetException(new ApiException(status));
    }

    @Override // com.google.android.gms.common.api.internal.zac
    public void zaa(zaw zawVar, boolean z8) {
    }

    @Override // com.google.android.gms.common.api.internal.zac
    public final void zac(GoogleApiManager.zaa<?> zaaVar) throws DeadObjectException {
        try {
            zad(zaaVar);
        } catch (DeadObjectException e9) {
            zaa(zac.zab(e9));
            throw e9;
        } catch (RemoteException e10) {
            zaa(zac.zab(e10));
        } catch (RuntimeException e11) {
            zaa(e11);
        }
    }

    public abstract void zad(GoogleApiManager.zaa<?> zaaVar);

    @Override // com.google.android.gms.common.api.internal.zac
    public void zaa(Exception exc) {
        this.zab.trySetException(exc);
    }
}
