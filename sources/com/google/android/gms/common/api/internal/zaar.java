package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes2.dex */
final class zaar implements GoogleApiClient.ConnectionCallbacks {
    private final /* synthetic */ AtomicReference zaa;
    private final /* synthetic */ StatusPendingResult zab;
    private final /* synthetic */ zaap zac;

    public zaar(zaap zaapVar, AtomicReference atomicReference, StatusPendingResult statusPendingResult) {
        this.zac = zaapVar;
        this.zaa = atomicReference;
        this.zab = statusPendingResult;
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnected(Bundle bundle) {
        this.zac.zaa((GoogleApiClient) Preconditions.checkNotNull((GoogleApiClient) this.zaa.get()), this.zab, true);
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnectionSuspended(int i9) {
    }
}
