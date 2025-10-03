package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.signin.SignInOptions;
import java.util.Set;

/* loaded from: classes2.dex */
public final class zacb extends com.google.android.gms.signin.internal.zad implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private static Api.AbstractClientBuilder<? extends com.google.android.gms.signin.zad, SignInOptions> zaa = com.google.android.gms.signin.zaa.zaa;
    private final Context zab;
    private final Handler zac;
    private final Api.AbstractClientBuilder<? extends com.google.android.gms.signin.zad, SignInOptions> zad;
    private Set<Scope> zae;
    private ClientSettings zaf;
    private com.google.android.gms.signin.zad zag;
    private zace zah;

    public zacb(Context context, Handler handler, ClientSettings clientSettings) {
        this(context, handler, clientSettings, zaa);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zab(com.google.android.gms.signin.internal.zam zamVar) {
        ConnectionResult connectionResultZaa = zamVar.zaa();
        if (connectionResultZaa.isSuccess()) {
            com.google.android.gms.common.internal.zas zasVar = (com.google.android.gms.common.internal.zas) Preconditions.checkNotNull(zamVar.zab());
            ConnectionResult connectionResultZab = zasVar.zab();
            if (!connectionResultZab.isSuccess()) {
                String strValueOf = String.valueOf(connectionResultZab);
                StringBuilder sb = new StringBuilder(strValueOf.length() + 48);
                sb.append("Sign-in succeeded with resolve account failure: ");
                sb.append(strValueOf);
                Log.wtf("SignInCoordinator", sb.toString(), new Exception());
                this.zah.zaa(connectionResultZab);
                this.zag.disconnect();
                return;
            }
            this.zah.zaa(zasVar.zaa(), this.zae);
        } else {
            this.zah.zaa(connectionResultZaa);
        }
        this.zag.disconnect();
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnected(Bundle bundle) {
        this.zag.zaa(this);
    }

    @Override // com.google.android.gms.common.api.internal.OnConnectionFailedListener
    public final void onConnectionFailed(ConnectionResult connectionResult) {
        this.zah.zaa(connectionResult);
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnectionSuspended(int i9) {
        this.zag.disconnect();
    }

    public final void zaa(zace zaceVar) {
        com.google.android.gms.signin.zad zadVar = this.zag;
        if (zadVar != null) {
            zadVar.disconnect();
        }
        this.zaf.zaa(Integer.valueOf(System.identityHashCode(this)));
        Api.AbstractClientBuilder<? extends com.google.android.gms.signin.zad, SignInOptions> abstractClientBuilder = this.zad;
        Context context = this.zab;
        Looper looper = this.zac.getLooper();
        ClientSettings clientSettings = this.zaf;
        this.zag = (com.google.android.gms.signin.zad) abstractClientBuilder.buildClient(context, looper, clientSettings, (ClientSettings) clientSettings.zac(), (GoogleApiClient.ConnectionCallbacks) this, (GoogleApiClient.OnConnectionFailedListener) this);
        this.zah = zaceVar;
        Set<Scope> set = this.zae;
        if (set == null || set.isEmpty()) {
            this.zac.post(new zacd(this));
        } else {
            this.zag.zab();
        }
    }

    private zacb(Context context, Handler handler, ClientSettings clientSettings, Api.AbstractClientBuilder<? extends com.google.android.gms.signin.zad, SignInOptions> abstractClientBuilder) {
        this.zab = context;
        this.zac = handler;
        this.zaf = (ClientSettings) Preconditions.checkNotNull(clientSettings, "ClientSettings must not be null");
        this.zae = clientSettings.getRequiredScopes();
        this.zad = abstractClientBuilder;
    }

    public final void zaa() {
        com.google.android.gms.signin.zad zadVar = this.zag;
        if (zadVar != null) {
            zadVar.disconnect();
        }
    }

    @Override // com.google.android.gms.signin.internal.zad, com.google.android.gms.signin.internal.zac
    public final void zaa(com.google.android.gms.signin.internal.zam zamVar) {
        this.zac.post(new zacc(this, zamVar));
    }
}
