package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes2.dex */
public final class zap implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    public final Api<?> zaa;
    private final boolean zab;
    private zar zac;

    public zap(Api<?> api, boolean z8) {
        this.zaa = api;
        this.zab = z8;
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnected(Bundle bundle) {
        zaa().onConnected(bundle);
    }

    @Override // com.google.android.gms.common.api.internal.OnConnectionFailedListener
    public final void onConnectionFailed(ConnectionResult connectionResult) {
        zaa().zaa(connectionResult, this.zaa, this.zab);
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnectionSuspended(int i9) {
        zaa().onConnectionSuspended(i9);
    }

    public final void zaa(zar zarVar) {
        this.zac = zarVar;
    }

    private final zar zaa() {
        Preconditions.checkNotNull(this.zac, "Callbacks must be attached to a ClientConnectionHelper instance before connecting the client.");
        return this.zac;
    }
}
