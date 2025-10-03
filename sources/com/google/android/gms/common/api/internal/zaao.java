package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes2.dex */
final class zaao implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private final /* synthetic */ zaad zaa;

    private zaao(zaad zaadVar) {
        this.zaa = zaadVar;
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnected(Bundle bundle) {
        ((com.google.android.gms.signin.zad) Preconditions.checkNotNull(this.zaa.zak)).zaa(new zaam(this.zaa));
    }

    @Override // com.google.android.gms.common.api.internal.OnConnectionFailedListener
    public final void onConnectionFailed(ConnectionResult connectionResult) {
        this.zaa.zab.lock();
        try {
            if (this.zaa.zaa(connectionResult)) {
                this.zaa.zag();
                this.zaa.zae();
            } else {
                this.zaa.zab(connectionResult);
            }
        } finally {
            this.zaa.zab.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnectionSuspended(int i9) {
    }

    public /* synthetic */ zaao(zaad zaadVar, zaag zaagVar) {
        this(zaadVar);
    }
}
