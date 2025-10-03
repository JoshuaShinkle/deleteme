package com.google.android.gms.common.api.internal;

import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.ref.WeakReference;

/* loaded from: classes2.dex */
final class zaaf implements BaseGmsClient.ConnectionProgressReportCallbacks {
    private final WeakReference<zaad> zaa;
    private final Api<?> zab;
    private final boolean zac;

    public zaaf(zaad zaadVar, Api<?> api, boolean z8) {
        this.zaa = new WeakReference<>(zaadVar);
        this.zab = api;
        this.zac = z8;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.ConnectionProgressReportCallbacks
    public final void onReportServiceBinding(ConnectionResult connectionResult) {
        zaad zaadVar = this.zaa.get();
        if (zaadVar == null) {
            return;
        }
        Preconditions.checkState(Looper.myLooper() == zaadVar.zaa.zad.getLooper(), "onReportServiceBinding must be called on the GoogleApiClient handler thread");
        zaadVar.zab.lock();
        try {
            if (zaadVar.zab(0)) {
                if (!connectionResult.isSuccess()) {
                    zaadVar.zab(connectionResult, this.zab, this.zac);
                }
                if (zaadVar.zad()) {
                    zaadVar.zae();
                }
            }
        } finally {
            zaadVar.zab.unlock();
        }
    }
}
