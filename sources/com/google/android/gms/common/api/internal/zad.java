package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.util.Log;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes2.dex */
public final class zad<A extends BaseImplementation.ApiMethodImpl<? extends Result, Api.AnyClient>> extends zac {
    private final A zab;

    public zad(int i9, A a9) {
        super(i9);
        this.zab = (A) Preconditions.checkNotNull(a9, "Null methods are not runnable.");
    }

    @Override // com.google.android.gms.common.api.internal.zac
    public final void zaa(Status status) {
        try {
            this.zab.setFailedResult(status);
        } catch (IllegalStateException e9) {
            Log.w("ApiCallRunner", "Exception reporting failure", e9);
        }
    }

    @Override // com.google.android.gms.common.api.internal.zac
    public final void zac(GoogleApiManager.zaa<?> zaaVar) throws DeadObjectException {
        try {
            this.zab.run(zaaVar.zab());
        } catch (RuntimeException e9) {
            zaa(e9);
        }
    }

    @Override // com.google.android.gms.common.api.internal.zac
    public final void zaa(Exception exc) {
        String simpleName = exc.getClass().getSimpleName();
        String localizedMessage = exc.getLocalizedMessage();
        StringBuilder sb = new StringBuilder(simpleName.length() + 2 + String.valueOf(localizedMessage).length());
        sb.append(simpleName);
        sb.append(": ");
        sb.append(localizedMessage);
        try {
            this.zab.setFailedResult(new Status(10, sb.toString()));
        } catch (IllegalStateException e9) {
            Log.w("ApiCallRunner", "Exception reporting failure", e9);
        }
    }

    @Override // com.google.android.gms.common.api.internal.zac
    public final void zaa(zaw zawVar, boolean z8) {
        zawVar.zaa(this.zab, z8);
    }
}
