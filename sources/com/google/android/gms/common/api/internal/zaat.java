package com.google.android.gms.common.api.internal;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

/* loaded from: classes2.dex */
final class zaat implements ResultCallback<Status> {
    private final /* synthetic */ StatusPendingResult zaa;
    private final /* synthetic */ boolean zab;
    private final /* synthetic */ GoogleApiClient zac;
    private final /* synthetic */ zaap zad;

    public zaat(zaap zaapVar, StatusPendingResult statusPendingResult, boolean z8, GoogleApiClient googleApiClient) {
        this.zad = zaapVar;
        this.zaa = statusPendingResult;
        this.zab = z8;
        this.zac = googleApiClient;
    }

    @Override // com.google.android.gms.common.api.ResultCallback
    public final /* synthetic */ void onResult(Result result) {
        Status status = (Status) result;
        Storage.getInstance(this.zad.zaj).zaa();
        if (status.isSuccess() && this.zad.isConnected()) {
            this.zad.reconnect();
        }
        this.zaa.setResult(status);
        if (this.zab) {
            this.zac.disconnect();
        }
    }
}
