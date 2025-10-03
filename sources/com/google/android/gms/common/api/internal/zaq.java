package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.signin.SignInOptions;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import p132m.C5302a;

/* loaded from: classes2.dex */
final class zaq implements zabn {
    private final Context zaa;
    private final zaap zab;
    private final Looper zac;
    private final zaax zad;
    private final zaax zae;
    private final Map<Api.AnyClientKey<?>, zaax> zaf;
    private final Api.Client zah;
    private Bundle zai;
    private final Lock zam;
    private final Set<SignInConnectionListener> zag = Collections.newSetFromMap(new WeakHashMap());
    private ConnectionResult zaj = null;
    private ConnectionResult zak = null;
    private boolean zal = false;
    private int zan = 0;

    private zaq(Context context, zaap zaapVar, Lock lock, Looper looper, GoogleApiAvailabilityLight googleApiAvailabilityLight, Map<Api.AnyClientKey<?>, Api.Client> map, Map<Api.AnyClientKey<?>, Api.Client> map2, ClientSettings clientSettings, Api.AbstractClientBuilder<? extends com.google.android.gms.signin.zad, SignInOptions> abstractClientBuilder, Api.Client client, ArrayList<zap> arrayList, ArrayList<zap> arrayList2, Map<Api<?>, Boolean> map3, Map<Api<?>, Boolean> map4) {
        this.zaa = context;
        this.zab = zaapVar;
        this.zam = lock;
        this.zac = looper;
        this.zah = client;
        this.zad = new zaax(context, zaapVar, lock, looper, googleApiAvailabilityLight, map2, null, map4, null, arrayList2, new zas(this, null));
        this.zae = new zaax(context, zaapVar, lock, looper, googleApiAvailabilityLight, map, clientSettings, map3, abstractClientBuilder, arrayList, new zau(this, null));
        C5302a c5302a = new C5302a();
        Iterator<Api.AnyClientKey<?>> it = map2.keySet().iterator();
        while (it.hasNext()) {
            c5302a.put(it.next(), this.zad);
        }
        Iterator<Api.AnyClientKey<?>> it2 = map.keySet().iterator();
        while (it2.hasNext()) {
            c5302a.put(it2.next(), this.zae);
        }
        this.zaf = Collections.unmodifiableMap(c5302a);
    }

    public static zaq zaa(Context context, zaap zaapVar, Lock lock, Looper looper, GoogleApiAvailabilityLight googleApiAvailabilityLight, Map<Api.AnyClientKey<?>, Api.Client> map, ClientSettings clientSettings, Map<Api<?>, Boolean> map2, Api.AbstractClientBuilder<? extends com.google.android.gms.signin.zad, SignInOptions> abstractClientBuilder, ArrayList<zap> arrayList) {
        C5302a c5302a = new C5302a();
        C5302a c5302a2 = new C5302a();
        Api.Client client = null;
        for (Map.Entry<Api.AnyClientKey<?>, Api.Client> entry : map.entrySet()) {
            Api.Client value = entry.getValue();
            if (value.providesSignIn()) {
                client = value;
            }
            if (value.requiresSignIn()) {
                c5302a.put(entry.getKey(), value);
            } else {
                c5302a2.put(entry.getKey(), value);
            }
        }
        Preconditions.checkState(!c5302a.isEmpty(), "CompositeGoogleApiClient should not be used without any APIs that require sign-in.");
        C5302a c5302a3 = new C5302a();
        C5302a c5302a4 = new C5302a();
        for (Api<?> api : map2.keySet()) {
            Api.AnyClientKey<?> anyClientKeyZac = api.zac();
            if (c5302a.containsKey(anyClientKeyZac)) {
                c5302a3.put(api, map2.get(api));
            } else {
                if (!c5302a2.containsKey(anyClientKeyZac)) {
                    throw new IllegalStateException("Each API in the isOptionalMap must have a corresponding client in the clients map.");
                }
                c5302a4.put(api, map2.get(api));
            }
        }
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        int size = arrayList.size();
        int i9 = 0;
        while (i9 < size) {
            zap zapVar = arrayList.get(i9);
            i9++;
            zap zapVar2 = zapVar;
            if (c5302a3.containsKey(zapVar2.zaa)) {
                arrayList2.add(zapVar2);
            } else {
                if (!c5302a4.containsKey(zapVar2.zaa)) {
                    throw new IllegalStateException("Each ClientCallbacks must have a corresponding API in the isOptionalMap");
                }
                arrayList3.add(zapVar2);
            }
        }
        return new zaq(context, zaapVar, lock, looper, googleApiAvailabilityLight, c5302a, c5302a2, clientSettings, abstractClientBuilder, client, arrayList2, arrayList3, c5302a3, c5302a4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zah() {
        ConnectionResult connectionResult;
        if (!zab(this.zaj)) {
            if (this.zaj != null && zab(this.zak)) {
                this.zae.zac();
                zaa((ConnectionResult) Preconditions.checkNotNull(this.zaj));
                return;
            }
            ConnectionResult connectionResult2 = this.zaj;
            if (connectionResult2 == null || (connectionResult = this.zak) == null) {
                return;
            }
            if (this.zae.zac < this.zad.zac) {
                connectionResult2 = connectionResult;
            }
            zaa(connectionResult2);
            return;
        }
        if (zab(this.zak) || zaj()) {
            int i9 = this.zan;
            if (i9 == 1) {
                zai();
            } else if (i9 != 2) {
                Log.wtf("CompositeGAC", "Attempted to call success callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new AssertionError());
            } else {
                ((zaap) Preconditions.checkNotNull(this.zab)).zaa(this.zai);
                zai();
            }
            this.zan = 0;
            return;
        }
        ConnectionResult connectionResult3 = this.zak;
        if (connectionResult3 != null) {
            if (this.zan == 1) {
                zai();
            } else {
                zaa(connectionResult3);
                this.zad.zac();
            }
        }
    }

    private final void zai() {
        Iterator<SignInConnectionListener> it = this.zag.iterator();
        while (it.hasNext()) {
            it.next().onComplete();
        }
        this.zag.clear();
    }

    private final boolean zaj() {
        ConnectionResult connectionResult = this.zak;
        return connectionResult != null && connectionResult.getErrorCode() == 4;
    }

    private final PendingIntent zak() {
        if (this.zah == null) {
            return null;
        }
        return PendingIntent.getActivity(this.zaa, System.identityHashCode(this.zab), this.zah.getSignInIntent(), 134217728);
    }

    @Override // com.google.android.gms.common.api.internal.zabn
    public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T zab(T t8) {
        if (!zac((BaseImplementation.ApiMethodImpl<? extends Result, ? extends Api.AnyClient>) t8)) {
            return (T) this.zad.zab((zaax) t8);
        }
        if (!zaj()) {
            return (T) this.zae.zab((zaax) t8);
        }
        t8.setFailedResult(new Status(4, (String) null, zak()));
        return t8;
    }

    @Override // com.google.android.gms.common.api.internal.zabn
    public final void zac() {
        this.zak = null;
        this.zaj = null;
        this.zan = 0;
        this.zad.zac();
        this.zae.zac();
        zai();
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0021  */
    @Override // com.google.android.gms.common.api.internal.zabn
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean zad() {
        boolean z8;
        this.zam.lock();
        try {
            if (this.zad.zad()) {
                z8 = true;
                if (!this.zae.zad() && !zaj()) {
                    if (this.zan != 1) {
                        z8 = false;
                    }
                }
            }
            return z8;
        } finally {
            this.zam.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabn
    public final boolean zae() {
        this.zam.lock();
        try {
            return this.zan == 2;
        } finally {
            this.zam.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabn
    public final void zaf() {
        this.zad.zaf();
        this.zae.zaf();
    }

    @Override // com.google.android.gms.common.api.internal.zabn
    public final void zag() {
        this.zam.lock();
        try {
            boolean zZae = zae();
            this.zae.zac();
            this.zak = new ConnectionResult(4);
            if (zZae) {
                new com.google.android.gms.internal.base.zap(this.zac).post(new zat(this));
            } else {
                zai();
            }
        } finally {
            this.zam.unlock();
        }
    }

    private final boolean zac(BaseImplementation.ApiMethodImpl<? extends Result, ? extends Api.AnyClient> apiMethodImpl) {
        zaax zaaxVar = this.zaf.get(apiMethodImpl.getClientKey());
        Preconditions.checkNotNull(zaaxVar, "GoogleApiClient is not configured to use the API required for this call.");
        return zaaxVar.equals(this.zae);
    }

    @Override // com.google.android.gms.common.api.internal.zabn
    public final ConnectionResult zab() {
        throw new UnsupportedOperationException();
    }

    private static boolean zab(ConnectionResult connectionResult) {
        return connectionResult != null && connectionResult.isSuccess();
    }

    @Override // com.google.android.gms.common.api.internal.zabn
    public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T zaa(T t8) {
        if (zac((BaseImplementation.ApiMethodImpl<? extends Result, ? extends Api.AnyClient>) t8)) {
            if (zaj()) {
                t8.setFailedResult(new Status(4, (String) null, zak()));
                return t8;
            }
            return (T) this.zae.zaa((zaax) t8);
        }
        return (T) this.zad.zaa((zaax) t8);
    }

    @Override // com.google.android.gms.common.api.internal.zabn
    public final ConnectionResult zaa(Api<?> api) {
        if (Objects.equal(this.zaf.get(api.zac()), this.zae)) {
            if (zaj()) {
                return new ConnectionResult(4, zak());
            }
            return this.zae.zaa(api);
        }
        return this.zad.zaa(api);
    }

    @Override // com.google.android.gms.common.api.internal.zabn
    public final void zaa() {
        this.zan = 2;
        this.zal = false;
        this.zak = null;
        this.zaj = null;
        this.zad.zaa();
        this.zae.zaa();
    }

    @Override // com.google.android.gms.common.api.internal.zabn
    public final ConnectionResult zaa(long j9, TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.common.api.internal.zabn
    public final boolean zaa(SignInConnectionListener signInConnectionListener) {
        this.zam.lock();
        try {
            if ((zae() || zad()) && !this.zae.zad()) {
                this.zag.add(signInConnectionListener);
                if (this.zan == 0) {
                    this.zan = 1;
                }
                this.zak = null;
                this.zae.zaa();
                return true;
            }
            this.zam.unlock();
            return false;
        } finally {
            this.zam.unlock();
        }
    }

    private final void zaa(ConnectionResult connectionResult) {
        int i9 = this.zan;
        if (i9 == 1) {
            zai();
        } else if (i9 != 2) {
            Log.wtf("CompositeGAC", "Attempted to call failure callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new Exception());
        } else {
            this.zab.zaa(connectionResult);
            zai();
        }
        this.zan = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zaa(int i9, boolean z8) {
        this.zab.zaa(i9, z8);
        this.zak = null;
        this.zaj = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zaa(Bundle bundle) {
        Bundle bundle2 = this.zai;
        if (bundle2 == null) {
            this.zai = bundle;
        } else if (bundle != null) {
            bundle2.putAll(bundle);
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabn
    public final void zaa(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.append((CharSequence) str).append("authClient").println(":");
        this.zae.zaa(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
        printWriter.append((CharSequence) str).append("anonClient").println(":");
        this.zad.zaa(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
    }
}
