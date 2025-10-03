package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.signin.SignInOptions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;

/* loaded from: classes2.dex */
public final class zaad implements zaay {
    private final zaax zaa;
    private final Lock zab;
    private final Context zac;
    private final GoogleApiAvailabilityLight zad;
    private ConnectionResult zae;
    private int zaf;
    private int zah;
    private com.google.android.gms.signin.zad zak;
    private boolean zal;
    private boolean zam;
    private boolean zan;
    private IAccountAccessor zao;
    private boolean zap;
    private boolean zaq;
    private final ClientSettings zar;
    private final Map<Api<?>, Boolean> zas;
    private final Api.AbstractClientBuilder<? extends com.google.android.gms.signin.zad, SignInOptions> zat;
    private int zag = 0;
    private final Bundle zai = new Bundle();
    private final Set<Api.AnyClientKey> zaj = new HashSet();
    private ArrayList<Future<?>> zau = new ArrayList<>();

    public zaad(zaax zaaxVar, ClientSettings clientSettings, Map<Api<?>, Boolean> map, GoogleApiAvailabilityLight googleApiAvailabilityLight, Api.AbstractClientBuilder<? extends com.google.android.gms.signin.zad, SignInOptions> abstractClientBuilder, Lock lock, Context context) {
        this.zaa = zaaxVar;
        this.zar = clientSettings;
        this.zas = map;
        this.zad = googleApiAvailabilityLight;
        this.zat = abstractClientBuilder;
        this.zab = lock;
        this.zac = context;
    }

    private static String zac(int i9) {
        return i9 != 0 ? i9 != 1 ? "UNKNOWN" : "STEP_GETTING_REMOTE_SERVICE" : "STEP_SERVICE_BINDINGS_AND_SIGN_IN";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zad() {
        int i9 = this.zah - 1;
        this.zah = i9;
        if (i9 > 0) {
            return false;
        }
        if (i9 < 0) {
            Log.w("GACConnecting", this.zaa.zad.zac());
            Log.wtf("GACConnecting", "GoogleApiClient received too many callbacks for the given step. Clients may be in an unexpected state; GoogleApiClient will now disconnect.", new Exception());
            zab(new ConnectionResult(8, null));
            return false;
        }
        ConnectionResult connectionResult = this.zae;
        if (connectionResult == null) {
            return true;
        }
        this.zaa.zac = this.zaf;
        zab(connectionResult);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zae() {
        if (this.zah != 0) {
            return;
        }
        if (!this.zam || this.zan) {
            ArrayList arrayList = new ArrayList();
            this.zag = 1;
            this.zah = this.zaa.zaa.size();
            for (Api.AnyClientKey<?> anyClientKey : this.zaa.zaa.keySet()) {
                if (!this.zaa.zab.containsKey(anyClientKey)) {
                    arrayList.add(this.zaa.zaa.get(anyClientKey));
                } else if (zad()) {
                    zaf();
                }
            }
            if (arrayList.isEmpty()) {
                return;
            }
            this.zau.add(zabb.zaa().submit(new zaaj(this, arrayList)));
        }
    }

    private final void zaf() {
        this.zaa.zai();
        zabb.zaa().execute(new zaag(this));
        com.google.android.gms.signin.zad zadVar = this.zak;
        if (zadVar != null) {
            if (this.zap) {
                zadVar.zaa((IAccountAccessor) Preconditions.checkNotNull(this.zao), this.zaq);
            }
            zaa(false);
        }
        Iterator<Api.AnyClientKey<?>> it = this.zaa.zab.keySet().iterator();
        while (it.hasNext()) {
            ((Api.Client) Preconditions.checkNotNull(this.zaa.zaa.get(it.next()))).disconnect();
        }
        this.zaa.zae.zaa(this.zai.isEmpty() ? null : this.zai);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zag() {
        this.zam = false;
        this.zaa.zad.zac = Collections.emptySet();
        for (Api.AnyClientKey<?> anyClientKey : this.zaj) {
            if (!this.zaa.zab.containsKey(anyClientKey)) {
                this.zaa.zab.put(anyClientKey, new ConnectionResult(17, null));
            }
        }
    }

    private final void zah() {
        ArrayList<Future<?>> arrayList = this.zau;
        int size = arrayList.size();
        int i9 = 0;
        while (i9 < size) {
            Future<?> future = arrayList.get(i9);
            i9++;
            future.cancel(true);
        }
        this.zau.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Set<Scope> zai() {
        if (this.zar == null) {
            return Collections.emptySet();
        }
        HashSet hashSet = new HashSet(this.zar.getRequiredScopes());
        Map<Api<?>, ClientSettings.zaa> mapZaa = this.zar.zaa();
        for (Api<?> api : mapZaa.keySet()) {
            if (!this.zaa.zab.containsKey(api.zac())) {
                hashSet.addAll(mapZaa.get(api).zaa);
            }
        }
        return hashSet;
    }

    @Override // com.google.android.gms.common.api.internal.zaay
    public final void zaa() {
        this.zaa.zab.clear();
        this.zam = false;
        zaag zaagVar = null;
        this.zae = null;
        this.zag = 0;
        this.zal = true;
        this.zan = false;
        this.zap = false;
        HashMap map = new HashMap();
        boolean z8 = false;
        for (Api<?> api : this.zas.keySet()) {
            Api.Client client = (Api.Client) Preconditions.checkNotNull(this.zaa.zaa.get(api.zac()));
            z8 |= api.zaa().getPriority() == 1;
            boolean zBooleanValue = this.zas.get(api).booleanValue();
            if (client.requiresSignIn()) {
                this.zam = true;
                if (zBooleanValue) {
                    this.zaj.add(api.zac());
                } else {
                    this.zal = false;
                }
            }
            map.put(client, new zaaf(this, api, zBooleanValue));
        }
        if (z8) {
            this.zam = false;
        }
        if (this.zam) {
            Preconditions.checkNotNull(this.zar);
            Preconditions.checkNotNull(this.zat);
            this.zar.zaa(Integer.valueOf(System.identityHashCode(this.zaa.zad)));
            zaao zaaoVar = new zaao(this, zaagVar);
            Api.AbstractClientBuilder<? extends com.google.android.gms.signin.zad, SignInOptions> abstractClientBuilder = this.zat;
            Context context = this.zac;
            Looper looper = this.zaa.zad.getLooper();
            ClientSettings clientSettings = this.zar;
            this.zak = (com.google.android.gms.signin.zad) abstractClientBuilder.buildClient(context, looper, clientSettings, (ClientSettings) clientSettings.zac(), (GoogleApiClient.ConnectionCallbacks) zaaoVar, (GoogleApiClient.OnConnectionFailedListener) zaaoVar);
        }
        this.zah = this.zaa.zaa.size();
        this.zau.add(zabb.zaa().submit(new zaai(this, map)));
    }

    @Override // com.google.android.gms.common.api.internal.zaay
    public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T zab(T t8) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }

    @Override // com.google.android.gms.common.api.internal.zaay
    public final void zac() {
    }

    @Override // com.google.android.gms.common.api.internal.zaay
    public final boolean zab() {
        zah();
        zaa(true);
        this.zaa.zaa((ConnectionResult) null);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void zab(ConnectionResult connectionResult, Api<?> api, boolean z8) {
        int priority = api.zaa().getPriority();
        boolean z9 = false;
        if (z8) {
            if (connectionResult.hasResolution() || this.zad.getErrorResolutionIntent(connectionResult.getErrorCode()) != null) {
            }
        } else if (this.zae == null || priority < this.zaf) {
            z9 = true;
        }
        if (z9) {
            this.zae = connectionResult;
            this.zaf = priority;
        }
        this.zaa.zab.put(api.zac(), connectionResult);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zab(ConnectionResult connectionResult) {
        zah();
        zaa(!connectionResult.hasResolution());
        this.zaa.zaa(connectionResult);
        this.zaa.zae.zaa(connectionResult);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zab(int i9) {
        if (this.zag == i9) {
            return true;
        }
        Log.w("GACConnecting", this.zaa.zad.zac());
        String strValueOf = String.valueOf(this);
        StringBuilder sb = new StringBuilder(strValueOf.length() + 23);
        sb.append("Unexpected callback in ");
        sb.append(strValueOf);
        Log.w("GACConnecting", sb.toString());
        int i10 = this.zah;
        StringBuilder sb2 = new StringBuilder(33);
        sb2.append("mRemainingConnections=");
        sb2.append(i10);
        Log.w("GACConnecting", sb2.toString());
        String strZac = zac(this.zag);
        String strZac2 = zac(i9);
        StringBuilder sb3 = new StringBuilder(String.valueOf(strZac).length() + 70 + String.valueOf(strZac2).length());
        sb3.append("GoogleApiClient connecting is in step ");
        sb3.append(strZac);
        sb3.append(" but received callback for step ");
        sb3.append(strZac2);
        Log.e("GACConnecting", sb3.toString(), new Exception());
        zab(new ConnectionResult(8, null));
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zaa(com.google.android.gms.signin.internal.zam zamVar) {
        if (zab(0)) {
            ConnectionResult connectionResultZaa = zamVar.zaa();
            if (connectionResultZaa.isSuccess()) {
                com.google.android.gms.common.internal.zas zasVar = (com.google.android.gms.common.internal.zas) Preconditions.checkNotNull(zamVar.zab());
                ConnectionResult connectionResultZab = zasVar.zab();
                if (!connectionResultZab.isSuccess()) {
                    String strValueOf = String.valueOf(connectionResultZab);
                    StringBuilder sb = new StringBuilder(strValueOf.length() + 48);
                    sb.append("Sign-in succeeded with resolve account failure: ");
                    sb.append(strValueOf);
                    Log.wtf("GACConnecting", sb.toString(), new Exception());
                    zab(connectionResultZab);
                    return;
                }
                this.zan = true;
                this.zao = (IAccountAccessor) Preconditions.checkNotNull(zasVar.zaa());
                this.zap = zasVar.zac();
                this.zaq = zasVar.zad();
                zae();
                return;
            }
            if (zaa(connectionResultZaa)) {
                zag();
                zae();
            } else {
                zab(connectionResultZaa);
            }
        }
    }

    @Override // com.google.android.gms.common.api.internal.zaay
    public final void zaa(Bundle bundle) {
        if (zab(1)) {
            if (bundle != null) {
                this.zai.putAll(bundle);
            }
            if (zad()) {
                zaf();
            }
        }
    }

    @Override // com.google.android.gms.common.api.internal.zaay
    public final void zaa(ConnectionResult connectionResult, Api<?> api, boolean z8) {
        if (zab(1)) {
            zab(connectionResult, api, z8);
            if (zad()) {
                zaf();
            }
        }
    }

    @Override // com.google.android.gms.common.api.internal.zaay
    public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T zaa(T t8) {
        this.zaa.zad.zaa.add(t8);
        return t8;
    }

    @Override // com.google.android.gms.common.api.internal.zaay
    public final void zaa(int i9) {
        zab(new ConnectionResult(8, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zaa(ConnectionResult connectionResult) {
        return this.zal && !connectionResult.hasResolution();
    }

    private final void zaa(boolean z8) {
        com.google.android.gms.signin.zad zadVar = this.zak;
        if (zadVar != null) {
            if (zadVar.isConnected() && z8) {
                zadVar.zaa();
            }
            zadVar.disconnect();
            this.zao = null;
        }
    }
}
