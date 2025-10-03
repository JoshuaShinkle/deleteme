package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import androidx.fragment.app.FragmentActivity;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.service.Common;
import com.google.android.gms.common.util.ClientLibraryUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.signin.SignInOptions;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;

/* loaded from: classes2.dex */
public final class zaap extends GoogleApiClient implements zabm {
    final Map<Api.AnyClientKey<?>, Api.Client> zab;
    Set<Scope> zac;
    Set<zack> zad;
    final zacl zae;
    private final Lock zaf;
    private final com.google.android.gms.common.internal.zah zag;
    private final int zai;
    private final Context zaj;
    private final Looper zak;
    private volatile boolean zal;
    private long zam;
    private long zan;
    private final zaaw zao;
    private final GoogleApiAvailability zap;

    @VisibleForTesting
    private zabj zaq;
    private final ClientSettings zar;
    private final Map<Api<?>, Boolean> zas;
    private final Api.AbstractClientBuilder<? extends com.google.android.gms.signin.zad, SignInOptions> zat;
    private final ListenerHolders zau;
    private final ArrayList<zap> zav;
    private Integer zaw;
    private final com.google.android.gms.common.internal.zak zax;
    private zabn zah = null;

    @VisibleForTesting
    final Queue<BaseImplementation.ApiMethodImpl<?, ?>> zaa = new LinkedList();

    public zaap(Context context, Lock lock, Looper looper, ClientSettings clientSettings, GoogleApiAvailability googleApiAvailability, Api.AbstractClientBuilder<? extends com.google.android.gms.signin.zad, SignInOptions> abstractClientBuilder, Map<Api<?>, Boolean> map, List<GoogleApiClient.ConnectionCallbacks> list, List<GoogleApiClient.OnConnectionFailedListener> list2, Map<Api.AnyClientKey<?>, Api.Client> map2, int i9, int i10, ArrayList<zap> arrayList) {
        this.zam = ClientLibraryUtils.isPackageSide() ? 10000L : 120000L;
        this.zan = DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS;
        this.zac = new HashSet();
        this.zau = new ListenerHolders();
        this.zaw = null;
        this.zad = null;
        zaas zaasVar = new zaas(this);
        this.zax = zaasVar;
        this.zaj = context;
        this.zaf = lock;
        this.zag = new com.google.android.gms.common.internal.zah(looper, zaasVar);
        this.zak = looper;
        this.zao = new zaaw(this, looper);
        this.zap = googleApiAvailability;
        this.zai = i9;
        if (i9 >= 0) {
            this.zaw = Integer.valueOf(i10);
        }
        this.zas = map;
        this.zab = map2;
        this.zav = arrayList;
        this.zae = new zacl();
        Iterator<GoogleApiClient.ConnectionCallbacks> it = list.iterator();
        while (it.hasNext()) {
            this.zag.zaa(it.next());
        }
        Iterator<GoogleApiClient.OnConnectionFailedListener> it2 = list2.iterator();
        while (it2.hasNext()) {
            this.zag.zaa(it2.next());
        }
        this.zar = clientSettings;
        this.zat = abstractClientBuilder;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zaa(GoogleApiClient googleApiClient, StatusPendingResult statusPendingResult, boolean z8) {
        Common.zaa.zaa(googleApiClient).setResultCallback(new zaat(this, statusPendingResult, z8, googleApiClient));
    }

    private static String zab(int i9) {
        return i9 != 1 ? i9 != 2 ? i9 != 3 ? "UNKNOWN" : "SIGN_IN_MODE_NONE" : "SIGN_IN_MODE_OPTIONAL" : "SIGN_IN_MODE_REQUIRED";
    }

    private final void zad() {
        this.zag.zab();
        ((zabn) Preconditions.checkNotNull(this.zah)).zaa();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zae() {
        this.zaf.lock();
        try {
            if (this.zal) {
                zad();
            }
        } finally {
            this.zaf.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zaf() {
        this.zaf.lock();
        try {
            if (zab()) {
                zad();
            }
        } finally {
            this.zaf.unlock();
        }
    }

    private final boolean zag() {
        this.zaf.lock();
        try {
            if (this.zad != null) {
                return !r0.isEmpty();
            }
            this.zaf.unlock();
            return false;
        } finally {
            this.zaf.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final ConnectionResult blockingConnect() {
        boolean z8 = true;
        Preconditions.checkState(Looper.myLooper() != Looper.getMainLooper(), "blockingConnect must not be called on the UI thread");
        this.zaf.lock();
        try {
            if (this.zai >= 0) {
                if (this.zaw == null) {
                    z8 = false;
                }
                Preconditions.checkState(z8, "Sign-in mode should have been set explicitly by auto-manage.");
            } else {
                Integer num = this.zaw;
                if (num == null) {
                    this.zaw = Integer.valueOf(zaa((Iterable<Api.Client>) this.zab.values(), false));
                } else if (num.intValue() == 2) {
                    throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
                }
            }
            zaa(((Integer) Preconditions.checkNotNull(this.zaw)).intValue());
            this.zag.zab();
            return ((zabn) Preconditions.checkNotNull(this.zah)).zab();
        } finally {
            this.zaf.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final PendingResult<Status> clearDefaultAccountAndReconnect() {
        Preconditions.checkState(isConnected(), "GoogleApiClient is not connected yet.");
        Integer num = this.zaw;
        Preconditions.checkState(num == null || num.intValue() != 2, "Cannot use clearDefaultAccountAndReconnect with GOOGLE_SIGN_IN_API");
        StatusPendingResult statusPendingResult = new StatusPendingResult(this);
        if (this.zab.containsKey(Common.CLIENT_KEY)) {
            zaa(this, statusPendingResult, false);
        } else {
            AtomicReference atomicReference = new AtomicReference();
            GoogleApiClient googleApiClientBuild = new GoogleApiClient.Builder(this.zaj).addApi(Common.API).addConnectionCallbacks(new zaar(this, atomicReference, statusPendingResult)).addOnConnectionFailedListener(new zaau(this, statusPendingResult)).setHandler(this.zao).build();
            atomicReference.set(googleApiClientBuild);
            googleApiClientBuild.connect();
        }
        return statusPendingResult;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void connect() {
        this.zaf.lock();
        try {
            if (this.zai >= 0) {
                Preconditions.checkState(this.zaw != null, "Sign-in mode should have been set explicitly by auto-manage.");
            } else {
                Integer num = this.zaw;
                if (num == null) {
                    this.zaw = Integer.valueOf(zaa((Iterable<Api.Client>) this.zab.values(), false));
                } else if (num.intValue() == 2) {
                    throw new IllegalStateException("Cannot call connect() when SignInMode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
                }
            }
            connect(((Integer) Preconditions.checkNotNull(this.zaw)).intValue());
        } finally {
            this.zaf.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void disconnect() {
        this.zaf.lock();
        try {
            this.zae.zaa();
            zabn zabnVar = this.zah;
            if (zabnVar != null) {
                zabnVar.zac();
            }
            this.zau.zaa();
            for (BaseImplementation.ApiMethodImpl<?, ?> apiMethodImpl : this.zaa) {
                apiMethodImpl.zaa((zacn) null);
                apiMethodImpl.cancel();
            }
            this.zaa.clear();
            if (this.zah == null) {
                return;
            }
            zab();
            this.zag.zaa();
        } finally {
            this.zaf.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.append((CharSequence) str).append("mContext=").println(this.zaj);
        printWriter.append((CharSequence) str).append("mResuming=").print(this.zal);
        printWriter.append(" mWorkQueue.size()=").print(this.zaa.size());
        printWriter.append(" mUnconsumedApiCalls.size()=").println(this.zae.zab.size());
        zabn zabnVar = this.zah;
        if (zabnVar != null) {
            zabnVar.zaa(str, fileDescriptor, printWriter, strArr);
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(T t8) {
        Api<?> api = t8.getApi();
        boolean zContainsKey = this.zab.containsKey(t8.getClientKey());
        String strZad = api != null ? api.zad() : "the API";
        StringBuilder sb = new StringBuilder(String.valueOf(strZad).length() + 65);
        sb.append("GoogleApiClient is not configured to use ");
        sb.append(strZad);
        sb.append(" required for this call.");
        Preconditions.checkArgument(zContainsKey, sb.toString());
        this.zaf.lock();
        try {
            zabn zabnVar = this.zah;
            if (zabnVar != null) {
                return (T) zabnVar.zaa((zabn) t8);
            }
            this.zaa.add(t8);
            return t8;
        } finally {
            this.zaf.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(T t8) {
        Api<?> api = t8.getApi();
        boolean zContainsKey = this.zab.containsKey(t8.getClientKey());
        String strZad = api != null ? api.zad() : "the API";
        StringBuilder sb = new StringBuilder(String.valueOf(strZad).length() + 65);
        sb.append("GoogleApiClient is not configured to use ");
        sb.append(strZad);
        sb.append(" required for this call.");
        Preconditions.checkArgument(zContainsKey, sb.toString());
        this.zaf.lock();
        try {
            zabn zabnVar = this.zah;
            if (zabnVar == null) {
                throw new IllegalStateException("GoogleApiClient is not connected yet.");
            }
            if (!this.zal) {
                return (T) zabnVar.zab(t8);
            }
            this.zaa.add(t8);
            while (!this.zaa.isEmpty()) {
                BaseImplementation.ApiMethodImpl<?, ?> apiMethodImplRemove = this.zaa.remove();
                this.zae.zaa(apiMethodImplRemove);
                apiMethodImplRemove.setFailedResult(Status.RESULT_INTERNAL_ERROR);
            }
            return t8;
        } finally {
            this.zaf.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final <C extends Api.Client> C getClient(Api.AnyClientKey<C> anyClientKey) {
        C c9 = (C) this.zab.get(anyClientKey);
        Preconditions.checkNotNull(c9, "Appropriate Api was not requested.");
        return c9;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final ConnectionResult getConnectionResult(Api<?> api) {
        this.zaf.lock();
        try {
            if (!isConnected() && !this.zal) {
                throw new IllegalStateException("Cannot invoke getConnectionResult unless GoogleApiClient is connected");
            }
            if (!this.zab.containsKey(api.zac())) {
                throw new IllegalArgumentException(String.valueOf(api.zad()).concat(" was never registered with GoogleApiClient"));
            }
            ConnectionResult connectionResultZaa = ((zabn) Preconditions.checkNotNull(this.zah)).zaa(api);
            if (connectionResultZaa != null) {
                return connectionResultZaa;
            }
            if (this.zal) {
                return ConnectionResult.RESULT_SUCCESS;
            }
            Log.w("GoogleApiClientImpl", zac());
            Log.wtf("GoogleApiClientImpl", String.valueOf(api.zad()).concat(" requested in getConnectionResult is not connected but is not present in the failed  connections map"), new Exception());
            return new ConnectionResult(8, null);
        } finally {
            this.zaf.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final Context getContext() {
        return this.zaj;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final Looper getLooper() {
        return this.zak;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final boolean hasApi(Api<?> api) {
        return this.zab.containsKey(api.zac());
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final boolean hasConnectedApi(Api<?> api) {
        Api.Client client;
        return isConnected() && (client = this.zab.get(api.zac())) != null && client.isConnected();
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final boolean isConnected() {
        zabn zabnVar = this.zah;
        return zabnVar != null && zabnVar.zad();
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final boolean isConnecting() {
        zabn zabnVar = this.zah;
        return zabnVar != null && zabnVar.zae();
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final boolean isConnectionCallbacksRegistered(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        return this.zag.zab(connectionCallbacks);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final boolean isConnectionFailedListenerRegistered(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return this.zag.zab(onConnectionFailedListener);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final boolean maybeSignIn(SignInConnectionListener signInConnectionListener) {
        zabn zabnVar = this.zah;
        return zabnVar != null && zabnVar.zaa(signInConnectionListener);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void maybeSignOut() {
        zabn zabnVar = this.zah;
        if (zabnVar != null) {
            zabnVar.zag();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void reconnect() {
        disconnect();
        connect();
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void registerConnectionCallbacks(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        this.zag.zaa(connectionCallbacks);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void registerConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this.zag.zaa(onConnectionFailedListener);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final <L> ListenerHolder<L> registerListener(L l9) {
        this.zaf.lock();
        try {
            return this.zau.zaa(l9, this.zak, "NO_TYPE");
        } finally {
            this.zaf.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void stopAutoManage(FragmentActivity fragmentActivity) {
        LifecycleActivity lifecycleActivity = new LifecycleActivity((Activity) fragmentActivity);
        if (this.zai < 0) {
            throw new IllegalStateException("Called stopAutoManage but automatic lifecycle management is not enabled.");
        }
        zai.zaa(lifecycleActivity).zaa(this.zai);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void unregisterConnectionCallbacks(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        this.zag.zac(connectionCallbacks);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void unregisterConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this.zag.zac(onConnectionFailedListener);
    }

    public final boolean zab() {
        if (!this.zal) {
            return false;
        }
        this.zal = false;
        this.zao.removeMessages(2);
        this.zao.removeMessages(1);
        zabj zabjVar = this.zaq;
        if (zabjVar != null) {
            zabjVar.zaa();
            this.zaq = null;
        }
        return true;
    }

    public final String zac() {
        StringWriter stringWriter = new StringWriter();
        dump("", null, new PrintWriter(stringWriter), null);
        return stringWriter.toString();
    }

    private final void zaa(int i9) {
        Integer num = this.zaw;
        if (num == null) {
            this.zaw = Integer.valueOf(i9);
        } else if (num.intValue() != i9) {
            String strZab = zab(i9);
            String strZab2 = zab(this.zaw.intValue());
            StringBuilder sb = new StringBuilder(String.valueOf(strZab).length() + 51 + String.valueOf(strZab2).length());
            sb.append("Cannot use sign-in mode: ");
            sb.append(strZab);
            sb.append(". Mode was already set to ");
            sb.append(strZab2);
            throw new IllegalStateException(sb.toString());
        }
        if (this.zah != null) {
            return;
        }
        boolean z8 = false;
        boolean z9 = false;
        for (Api.Client client : this.zab.values()) {
            if (client.requiresSignIn()) {
                z8 = true;
            }
            if (client.providesSignIn()) {
                z9 = true;
            }
        }
        int iIntValue = this.zaw.intValue();
        if (iIntValue == 1) {
            if (!z8) {
                throw new IllegalStateException("SIGN_IN_MODE_REQUIRED cannot be used on a GoogleApiClient that does not contain any authenticated APIs. Use connect() instead.");
            }
            if (z9) {
                throw new IllegalStateException("Cannot use SIGN_IN_MODE_REQUIRED with GOOGLE_SIGN_IN_API. Use connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
        } else if (iIntValue == 2 && z8) {
            this.zah = zaq.zaa(this.zaj, this, this.zaf, this.zak, this.zap, this.zab, this.zar, this.zas, this.zat, this.zav);
            return;
        }
        this.zah = new zaax(this.zaj, this, this.zaf, this.zak, this.zap, this.zab, this.zar, this.zas, this.zat, this.zav, this);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void zab(zack zackVar) {
        zabn zabnVar;
        this.zaf.lock();
        try {
            Set<zack> set = this.zad;
            if (set == null) {
                Log.wtf("GoogleApiClientImpl", "Attempted to remove pending transform when no transforms are registered.", new Exception());
            } else if (!set.remove(zackVar)) {
                Log.wtf("GoogleApiClientImpl", "Failed to remove pending transform - this may lead to memory leaks!", new Exception());
            } else if (!zag() && (zabnVar = this.zah) != null) {
                zabnVar.zaf();
            }
        } finally {
            this.zaf.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void connect(int i9) {
        this.zaf.lock();
        boolean z8 = true;
        if (i9 != 3 && i9 != 1 && i9 != 2) {
            z8 = false;
        }
        try {
            StringBuilder sb = new StringBuilder(33);
            sb.append("Illegal sign-in mode: ");
            sb.append(i9);
            Preconditions.checkArgument(z8, sb.toString());
            zaa(i9);
            zad();
        } finally {
            this.zaf.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final ConnectionResult blockingConnect(long j9, TimeUnit timeUnit) {
        Preconditions.checkState(Looper.myLooper() != Looper.getMainLooper(), "blockingConnect must not be called on the UI thread");
        Preconditions.checkNotNull(timeUnit, "TimeUnit must not be null");
        this.zaf.lock();
        try {
            Integer num = this.zaw;
            if (num == null) {
                this.zaw = Integer.valueOf(zaa((Iterable<Api.Client>) this.zab.values(), false));
            } else if (num.intValue() == 2) {
                throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
            zaa(((Integer) Preconditions.checkNotNull(this.zaw)).intValue());
            this.zag.zab();
            return ((zabn) Preconditions.checkNotNull(this.zah)).zaa(j9, timeUnit);
        } finally {
            this.zaf.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabm
    public final void zaa(Bundle bundle) {
        while (!this.zaa.isEmpty()) {
            execute(this.zaa.remove());
        }
        this.zag.zaa(bundle);
    }

    @Override // com.google.android.gms.common.api.internal.zabm
    public final void zaa(ConnectionResult connectionResult) {
        if (!this.zap.isPlayServicesPossiblyUpdating(this.zaj, connectionResult.getErrorCode())) {
            zab();
        }
        if (this.zal) {
            return;
        }
        this.zag.zaa(connectionResult);
        this.zag.zaa();
    }

    @Override // com.google.android.gms.common.api.internal.zabm
    public final void zaa(int i9, boolean z8) {
        if (i9 == 1 && !z8 && !this.zal) {
            this.zal = true;
            if (this.zaq == null && !ClientLibraryUtils.isPackageSide()) {
                try {
                    this.zaq = this.zap.zaa(this.zaj.getApplicationContext(), new zaav(this));
                } catch (SecurityException unused) {
                }
            }
            zaaw zaawVar = this.zao;
            zaawVar.sendMessageDelayed(zaawVar.obtainMessage(1), this.zam);
            zaaw zaawVar2 = this.zao;
            zaawVar2.sendMessageDelayed(zaawVar2.obtainMessage(2), this.zan);
        }
        for (BasePendingResult basePendingResult : (BasePendingResult[]) this.zae.zab.toArray(new BasePendingResult[0])) {
            basePendingResult.forceFailureUnlessReady(zacl.zaa);
        }
        this.zag.zaa(i9);
        this.zag.zaa();
        if (i9 == 2) {
            zad();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void zaa(zack zackVar) {
        this.zaf.lock();
        try {
            if (this.zad == null) {
                this.zad = new HashSet();
            }
            this.zad.add(zackVar);
        } finally {
            this.zaf.unlock();
        }
    }

    public static int zaa(Iterable<Api.Client> iterable, boolean z8) {
        boolean z9 = false;
        boolean z10 = false;
        for (Api.Client client : iterable) {
            if (client.requiresSignIn()) {
                z9 = true;
            }
            if (client.providesSignIn()) {
                z10 = true;
            }
        }
        if (z9) {
            return (z10 && z8) ? 2 : 1;
        }
        return 3;
    }
}
