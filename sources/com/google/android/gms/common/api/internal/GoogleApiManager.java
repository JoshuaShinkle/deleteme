package com.google.android.gms.common.api.internal;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.RecentlyNonNull;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.HasApiKey;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.UnsupportedApiCallException;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.checkerframework.checker.initialization.qual.NotOnlyInitialized;
import p132m.C5302a;
import p132m.C5303b;

@KeepForSdk
/* loaded from: classes2.dex */
public class GoogleApiManager implements Handler.Callback {

    @RecentlyNonNull
    public static final Status zaa = new Status(4, "Sign-out occurred while this API call was in progress.");
    private static final Status zab = new Status(4, "The user must be signed in to make this API call.");
    private static final Object zaf = new Object();
    private static GoogleApiManager zag;
    private final Context zah;
    private final GoogleApiAvailability zai;
    private final com.google.android.gms.common.internal.zaj zaj;

    @NotOnlyInitialized
    private final Handler zaq;
    private volatile boolean zar;
    private long zac = DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS;
    private long zad = 120000;
    private long zae = 10000;
    private final AtomicInteger zak = new AtomicInteger(1);
    private final AtomicInteger zal = new AtomicInteger(0);
    private final Map<ApiKey<?>, zaa<?>> zam = new ConcurrentHashMap(5, 0.75f, 1);
    private zax zan = null;
    private final Set<ApiKey<?>> zao = new C5303b();
    private final Set<ApiKey<?>> zap = new C5303b();

    public class zaa<O extends Api.ApiOptions> implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, zar {

        @NotOnlyInitialized
        private final Api.Client zac;
        private final ApiKey<O> zad;
        private final zaw zae;
        private final int zah;
        private final zacb zai;
        private boolean zaj;
        private final Queue<com.google.android.gms.common.api.internal.zac> zab = new LinkedList();
        private final Set<zaj> zaf = new HashSet();
        private final Map<ListenerHolder.ListenerKey<?>, zabs> zag = new HashMap();
        private final List<zab> zak = new ArrayList();
        private ConnectionResult zal = null;

        public zaa(GoogleApi<O> googleApi) {
            Api.Client clientZaa = googleApi.zaa(GoogleApiManager.this.zaq.getLooper(), this);
            this.zac = clientZaa;
            this.zad = googleApi.getApiKey();
            this.zae = new zaw();
            this.zah = googleApi.zaa();
            if (clientZaa.requiresSignIn()) {
                this.zai = googleApi.zaa(GoogleApiManager.this.zah, GoogleApiManager.this.zaq);
            } else {
                this.zai = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zaa(int i9) {
            zad();
            this.zaj = true;
            this.zae.zaa(i9, this.zac.getLastDisconnectMessage());
            GoogleApiManager.this.zaq.sendMessageDelayed(Message.obtain(GoogleApiManager.this.zaq, 9, this.zad), GoogleApiManager.this.zac);
            GoogleApiManager.this.zaq.sendMessageDelayed(Message.obtain(GoogleApiManager.this.zaq, 11, this.zad), GoogleApiManager.this.zad);
            GoogleApiManager.this.zaj.zaa();
            Iterator<zabs> it = this.zag.values().iterator();
            while (it.hasNext()) {
                it.next().zac.run();
            }
        }

        private final boolean zab(ConnectionResult connectionResult) {
            synchronized (GoogleApiManager.zaf) {
                if (GoogleApiManager.this.zan == null || !GoogleApiManager.this.zao.contains(this.zad)) {
                    return false;
                }
                GoogleApiManager.this.zan.zab(connectionResult, this.zah);
                return true;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zam() {
            zad();
            zac(ConnectionResult.RESULT_SUCCESS);
            zao();
            Iterator<zabs> it = this.zag.values().iterator();
            while (it.hasNext()) {
                zabs next = it.next();
                if (zaa(next.zaa.getRequiredFeatures()) != null) {
                    it.remove();
                } else {
                    try {
                        next.zaa.registerListener(this.zac, new TaskCompletionSource<>());
                    } catch (DeadObjectException unused) {
                        onConnectionSuspended(3);
                        this.zac.disconnect("DeadObjectException thrown while calling register listener method.");
                    } catch (RemoteException unused2) {
                        it.remove();
                    }
                }
            }
            zan();
            zap();
        }

        private final void zan() {
            ArrayList arrayList = new ArrayList(this.zab);
            int size = arrayList.size();
            int i9 = 0;
            while (i9 < size) {
                Object obj = arrayList.get(i9);
                i9++;
                com.google.android.gms.common.api.internal.zac zacVar = (com.google.android.gms.common.api.internal.zac) obj;
                if (!this.zac.isConnected()) {
                    return;
                }
                if (zab(zacVar)) {
                    this.zab.remove(zacVar);
                }
            }
        }

        private final void zao() {
            if (this.zaj) {
                GoogleApiManager.this.zaq.removeMessages(11, this.zad);
                GoogleApiManager.this.zaq.removeMessages(9, this.zad);
                this.zaj = false;
            }
        }

        private final void zap() {
            GoogleApiManager.this.zaq.removeMessages(12, this.zad);
            GoogleApiManager.this.zaq.sendMessageDelayed(GoogleApiManager.this.zaq.obtainMessage(12, this.zad), GoogleApiManager.this.zae);
        }

        @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
        public final void onConnected(Bundle bundle) {
            if (Looper.myLooper() == GoogleApiManager.this.zaq.getLooper()) {
                zam();
            } else {
                GoogleApiManager.this.zaq.post(new zabe(this));
            }
        }

        @Override // com.google.android.gms.common.api.internal.OnConnectionFailedListener
        public final void onConnectionFailed(ConnectionResult connectionResult) {
            zaa(connectionResult, (Exception) null);
        }

        @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
        public final void onConnectionSuspended(int i9) {
            if (Looper.myLooper() == GoogleApiManager.this.zaq.getLooper()) {
                zaa(i9);
            } else {
                GoogleApiManager.this.zaq.post(new zabd(this, i9));
            }
        }

        public final Map<ListenerHolder.ListenerKey<?>, zabs> zac() {
            return this.zag;
        }

        public final void zad() {
            Preconditions.checkHandlerThread(GoogleApiManager.this.zaq);
            this.zal = null;
        }

        public final ConnectionResult zae() {
            Preconditions.checkHandlerThread(GoogleApiManager.this.zaq);
            return this.zal;
        }

        public final void zaf() {
            Preconditions.checkHandlerThread(GoogleApiManager.this.zaq);
            if (this.zaj) {
                zai();
            }
        }

        public final void zag() {
            Preconditions.checkHandlerThread(GoogleApiManager.this.zaq);
            if (this.zaj) {
                zao();
                zaa(GoogleApiManager.this.zai.isGooglePlayServicesAvailable(GoogleApiManager.this.zah) == 18 ? new Status(21, "Connection timed out waiting for Google Play services update to complete.") : new Status(22, "API failed to connect while resuming due to an unknown error."));
                this.zac.disconnect("Timing out connection while resuming.");
            }
        }

        public final boolean zah() {
            return zaa(true);
        }

        public final void zai() {
            Preconditions.checkHandlerThread(GoogleApiManager.this.zaq);
            if (this.zac.isConnected() || this.zac.isConnecting()) {
                return;
            }
            try {
                int iZaa = GoogleApiManager.this.zaj.zaa(GoogleApiManager.this.zah, this.zac);
                if (iZaa == 0) {
                    zac zacVar = GoogleApiManager.this.new zac(this.zac, this.zad);
                    if (this.zac.requiresSignIn()) {
                        ((zacb) Preconditions.checkNotNull(this.zai)).zaa(zacVar);
                    }
                    try {
                        this.zac.connect(zacVar);
                        return;
                    } catch (SecurityException e9) {
                        zaa(new ConnectionResult(10), e9);
                        return;
                    }
                }
                ConnectionResult connectionResult = new ConnectionResult(iZaa, null);
                String name = this.zac.getClass().getName();
                String strValueOf = String.valueOf(connectionResult);
                StringBuilder sb = new StringBuilder(name.length() + 35 + strValueOf.length());
                sb.append("The service for ");
                sb.append(name);
                sb.append(" is not available: ");
                sb.append(strValueOf);
                Log.w("GoogleApiManager", sb.toString());
                onConnectionFailed(connectionResult);
            } catch (IllegalStateException e10) {
                zaa(new ConnectionResult(10), e10);
            }
        }

        public final boolean zaj() {
            return this.zac.isConnected();
        }

        public final boolean zak() {
            return this.zac.requiresSignIn();
        }

        public final int zal() {
            return this.zah;
        }

        private final void zac(com.google.android.gms.common.api.internal.zac zacVar) {
            zacVar.zaa(this.zae, zak());
            try {
                zacVar.zac(this);
            } catch (DeadObjectException unused) {
                onConnectionSuspended(1);
                this.zac.disconnect("DeadObjectException thrown while running ApiCallRunner.");
            } catch (Throwable th) {
                throw new IllegalStateException(String.format("Error in GoogleApi implementation for client %s.", this.zac.getClass().getName()), th);
            }
        }

        private final Status zad(ConnectionResult connectionResult) {
            return GoogleApiManager.zab((ApiKey<?>) this.zad, connectionResult);
        }

        public final Api.Client zab() {
            return this.zac;
        }

        private final boolean zab(com.google.android.gms.common.api.internal.zac zacVar) {
            if (!(zacVar instanceof com.google.android.gms.common.api.internal.zab)) {
                zac(zacVar);
                return true;
            }
            com.google.android.gms.common.api.internal.zab zabVar = (com.google.android.gms.common.api.internal.zab) zacVar;
            Feature featureZaa = zaa(zabVar.zaa((zaa<?>) this));
            if (featureZaa == null) {
                zac(zacVar);
                return true;
            }
            String name = this.zac.getClass().getName();
            String name2 = featureZaa.getName();
            long version = featureZaa.getVersion();
            StringBuilder sb = new StringBuilder(name.length() + 77 + String.valueOf(name2).length());
            sb.append(name);
            sb.append(" could not execute call because it requires feature (");
            sb.append(name2);
            sb.append(", ");
            sb.append(version);
            sb.append(").");
            Log.w("GoogleApiManager", sb.toString());
            if (GoogleApiManager.this.zar && zabVar.zab((zaa<?>) this)) {
                zab zabVar2 = new zab(this.zad, featureZaa, null);
                int iIndexOf = this.zak.indexOf(zabVar2);
                if (iIndexOf >= 0) {
                    zab zabVar3 = this.zak.get(iIndexOf);
                    GoogleApiManager.this.zaq.removeMessages(15, zabVar3);
                    GoogleApiManager.this.zaq.sendMessageDelayed(Message.obtain(GoogleApiManager.this.zaq, 15, zabVar3), GoogleApiManager.this.zac);
                    return false;
                }
                this.zak.add(zabVar2);
                GoogleApiManager.this.zaq.sendMessageDelayed(Message.obtain(GoogleApiManager.this.zaq, 15, zabVar2), GoogleApiManager.this.zac);
                GoogleApiManager.this.zaq.sendMessageDelayed(Message.obtain(GoogleApiManager.this.zaq, 16, zabVar2), GoogleApiManager.this.zad);
                ConnectionResult connectionResult = new ConnectionResult(2, null);
                if (zab(connectionResult)) {
                    return false;
                }
                GoogleApiManager.this.zaa(connectionResult, this.zah);
                return false;
            }
            zabVar.zaa(new UnsupportedApiCallException(featureZaa));
            return true;
        }

        private final void zac(ConnectionResult connectionResult) {
            Iterator<zaj> it = this.zaf.iterator();
            while (it.hasNext()) {
                it.next().zaa(this.zad, connectionResult, Objects.equal(connectionResult, ConnectionResult.RESULT_SUCCESS) ? this.zac.getEndpointPackageName() : null);
            }
            this.zaf.clear();
        }

        public final void zaa(ConnectionResult connectionResult) {
            Preconditions.checkHandlerThread(GoogleApiManager.this.zaq);
            Api.Client client = this.zac;
            String name = client.getClass().getName();
            String strValueOf = String.valueOf(connectionResult);
            StringBuilder sb = new StringBuilder(name.length() + 25 + strValueOf.length());
            sb.append("onSignInFailed for ");
            sb.append(name);
            sb.append(" with ");
            sb.append(strValueOf);
            client.disconnect(sb.toString());
            onConnectionFailed(connectionResult);
        }

        @Override // com.google.android.gms.common.api.internal.zar
        public final void zaa(ConnectionResult connectionResult, Api<?> api, boolean z8) {
            if (Looper.myLooper() == GoogleApiManager.this.zaq.getLooper()) {
                onConnectionFailed(connectionResult);
            } else {
                GoogleApiManager.this.zaq.post(new zabg(this, connectionResult));
            }
        }

        private final void zaa(ConnectionResult connectionResult, Exception exc) {
            Preconditions.checkHandlerThread(GoogleApiManager.this.zaq);
            zacb zacbVar = this.zai;
            if (zacbVar != null) {
                zacbVar.zaa();
            }
            zad();
            GoogleApiManager.this.zaj.zaa();
            zac(connectionResult);
            if (connectionResult.getErrorCode() == 4) {
                zaa(GoogleApiManager.zab);
                return;
            }
            if (this.zab.isEmpty()) {
                this.zal = connectionResult;
                return;
            }
            if (exc == null) {
                if (!GoogleApiManager.this.zar) {
                    zaa(zad(connectionResult));
                    return;
                }
                zaa(zad(connectionResult), (Exception) null, true);
                if (this.zab.isEmpty() || zab(connectionResult) || GoogleApiManager.this.zaa(connectionResult, this.zah)) {
                    return;
                }
                if (connectionResult.getErrorCode() == 18) {
                    this.zaj = true;
                }
                if (this.zaj) {
                    GoogleApiManager.this.zaq.sendMessageDelayed(Message.obtain(GoogleApiManager.this.zaq, 9, this.zad), GoogleApiManager.this.zac);
                    return;
                } else {
                    zaa(zad(connectionResult));
                    return;
                }
            }
            Preconditions.checkHandlerThread(GoogleApiManager.this.zaq);
            zaa((Status) null, exc, false);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zab(zab zabVar) {
            Feature[] featureArrZaa;
            if (this.zak.remove(zabVar)) {
                GoogleApiManager.this.zaq.removeMessages(15, zabVar);
                GoogleApiManager.this.zaq.removeMessages(16, zabVar);
                Feature feature = zabVar.zab;
                ArrayList arrayList = new ArrayList(this.zab.size());
                for (com.google.android.gms.common.api.internal.zac zacVar : this.zab) {
                    if ((zacVar instanceof com.google.android.gms.common.api.internal.zab) && (featureArrZaa = ((com.google.android.gms.common.api.internal.zab) zacVar).zaa((zaa<?>) this)) != null && ArrayUtils.contains(featureArrZaa, feature)) {
                        arrayList.add(zacVar);
                    }
                }
                int size = arrayList.size();
                int i9 = 0;
                while (i9 < size) {
                    Object obj = arrayList.get(i9);
                    i9++;
                    com.google.android.gms.common.api.internal.zac zacVar2 = (com.google.android.gms.common.api.internal.zac) obj;
                    this.zab.remove(zacVar2);
                    zacVar2.zaa(new UnsupportedApiCallException(feature));
                }
            }
        }

        public final void zaa(com.google.android.gms.common.api.internal.zac zacVar) {
            Preconditions.checkHandlerThread(GoogleApiManager.this.zaq);
            if (this.zac.isConnected()) {
                if (zab(zacVar)) {
                    zap();
                    return;
                } else {
                    this.zab.add(zacVar);
                    return;
                }
            }
            this.zab.add(zacVar);
            ConnectionResult connectionResult = this.zal;
            if (connectionResult != null && connectionResult.hasResolution()) {
                onConnectionFailed(this.zal);
            } else {
                zai();
            }
        }

        public final void zaa() {
            Preconditions.checkHandlerThread(GoogleApiManager.this.zaq);
            zaa(GoogleApiManager.zaa);
            this.zae.zab();
            for (ListenerHolder.ListenerKey listenerKey : (ListenerHolder.ListenerKey[]) this.zag.keySet().toArray(new ListenerHolder.ListenerKey[0])) {
                zaa(new zah(listenerKey, new TaskCompletionSource()));
            }
            zac(new ConnectionResult(4));
            if (this.zac.isConnected()) {
                this.zac.onUserSignOut(new zabf(this));
            }
        }

        private final void zaa(Status status, Exception exc, boolean z8) {
            Preconditions.checkHandlerThread(GoogleApiManager.this.zaq);
            if ((status == null) != (exc == null)) {
                Iterator<com.google.android.gms.common.api.internal.zac> it = this.zab.iterator();
                while (it.hasNext()) {
                    com.google.android.gms.common.api.internal.zac next = it.next();
                    if (!z8 || next.zaa == 2) {
                        if (status != null) {
                            next.zaa(status);
                        } else {
                            next.zaa(exc);
                        }
                        it.remove();
                    }
                }
                return;
            }
            throw new IllegalArgumentException("Status XOR exception should be null");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zaa(Status status) {
            Preconditions.checkHandlerThread(GoogleApiManager.this.zaq);
            zaa(status, (Exception) null, false);
        }

        private final boolean zaa(boolean z8) {
            Preconditions.checkHandlerThread(GoogleApiManager.this.zaq);
            if (!this.zac.isConnected() || this.zag.size() != 0) {
                return false;
            }
            if (!this.zae.zaa()) {
                this.zac.disconnect("Timing out service connection.");
                return true;
            }
            if (z8) {
                zap();
            }
            return false;
        }

        public final void zaa(zaj zajVar) {
            Preconditions.checkHandlerThread(GoogleApiManager.this.zaq);
            this.zaf.add(zajVar);
        }

        /* JADX WARN: Multi-variable type inference failed */
        private final Feature zaa(Feature[] featureArr) {
            if (featureArr != null && featureArr.length != 0) {
                Feature[] availableFeatures = this.zac.getAvailableFeatures();
                if (availableFeatures == null) {
                    availableFeatures = new Feature[0];
                }
                C5302a c5302a = new C5302a(availableFeatures.length);
                for (Feature feature : availableFeatures) {
                    c5302a.put(feature.getName(), Long.valueOf(feature.getVersion()));
                }
                for (Feature feature2 : featureArr) {
                    Long l9 = (Long) c5302a.get(feature2.getName());
                    if (l9 == null || l9.longValue() < feature2.getVersion()) {
                        return feature2;
                    }
                }
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zaa(zab zabVar) {
            if (this.zak.contains(zabVar) && !this.zaj) {
                if (!this.zac.isConnected()) {
                    zai();
                } else {
                    zan();
                }
            }
        }

        public static /* synthetic */ boolean zaa(zaa zaaVar, boolean z8) {
            return zaaVar.zaa(false);
        }
    }

    @KeepForSdk
    private GoogleApiManager(Context context, Looper looper, GoogleApiAvailability googleApiAvailability) {
        this.zar = true;
        this.zah = context;
        com.google.android.gms.internal.base.zap zapVar = new com.google.android.gms.internal.base.zap(looper, this);
        this.zaq = zapVar;
        this.zai = googleApiAvailability;
        this.zaj = new com.google.android.gms.common.internal.zaj(googleApiAvailability);
        if (DeviceProperties.isAuto(context)) {
            this.zar = false;
        }
        zapVar.sendMessage(zapVar.obtainMessage(6));
    }

    @KeepForSdk
    public static void reportSignOut() {
        synchronized (zaf) {
            GoogleApiManager googleApiManager = zag;
            if (googleApiManager != null) {
                googleApiManager.zal.incrementAndGet();
                Handler handler = googleApiManager.zaq;
                handler.sendMessageAtFrontOfQueue(handler.obtainMessage(10));
            }
        }
    }

    @RecentlyNonNull
    public static GoogleApiManager zaa(@RecentlyNonNull Context context) {
        GoogleApiManager googleApiManager;
        synchronized (zaf) {
            if (zag == null) {
                HandlerThread handlerThread = new HandlerThread("GoogleApiHandler", 9);
                handlerThread.start();
                zag = new GoogleApiManager(context.getApplicationContext(), handlerThread.getLooper(), GoogleApiAvailability.getInstance());
            }
            googleApiManager = zag;
        }
        return googleApiManager;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final zaa<?> zac(GoogleApi<?> googleApi) {
        Object apiKey = googleApi.getApiKey();
        zaa<?> zaaVar = this.zam.get(apiKey);
        if (zaaVar == null) {
            zaaVar = new zaa<>(googleApi);
            this.zam.put(apiKey, zaaVar);
        }
        if (zaaVar.zak()) {
            this.zap.add(apiKey);
        }
        zaaVar.zai();
        return zaaVar;
    }

    @Override // android.os.Handler.Callback
    @RecentlyNonNull
    public boolean handleMessage(@RecentlyNonNull Message message) {
        int i9 = message.what;
        zaa<?> zaaVar = null;
        switch (i9) {
            case 1:
                this.zae = ((Boolean) message.obj).booleanValue() ? 10000L : 300000L;
                this.zaq.removeMessages(12);
                for (ApiKey<?> apiKey : this.zam.keySet()) {
                    Handler handler = this.zaq;
                    handler.sendMessageDelayed(handler.obtainMessage(12, apiKey), this.zae);
                }
                return true;
            case 2:
                zaj zajVar = (zaj) message.obj;
                Iterator<ApiKey<?>> it = zajVar.zaa().iterator();
                while (true) {
                    if (it.hasNext()) {
                        ApiKey<?> next = it.next();
                        zaa<?> zaaVar2 = this.zam.get(next);
                        if (zaaVar2 == null) {
                            zajVar.zaa(next, new ConnectionResult(13), null);
                        } else if (zaaVar2.zaj()) {
                            zajVar.zaa(next, ConnectionResult.RESULT_SUCCESS, zaaVar2.zab().getEndpointPackageName());
                        } else {
                            ConnectionResult connectionResultZae = zaaVar2.zae();
                            if (connectionResultZae != null) {
                                zajVar.zaa(next, connectionResultZae, null);
                            } else {
                                zaaVar2.zaa(zajVar);
                                zaaVar2.zai();
                            }
                        }
                    }
                }
                return true;
            case 3:
                for (zaa<?> zaaVar3 : this.zam.values()) {
                    zaaVar3.zad();
                    zaaVar3.zai();
                }
                return true;
            case 4:
            case 8:
            case 13:
                zabr zabrVar = (zabr) message.obj;
                zaa<?> zaaVarZac = this.zam.get(zabrVar.zac.getApiKey());
                if (zaaVarZac == null) {
                    zaaVarZac = zac(zabrVar.zac);
                }
                if (!zaaVarZac.zak() || this.zal.get() == zabrVar.zab) {
                    zaaVarZac.zaa(zabrVar.zaa);
                } else {
                    zabrVar.zaa.zaa(zaa);
                    zaaVarZac.zaa();
                }
                return true;
            case 5:
                int i10 = message.arg1;
                ConnectionResult connectionResult = (ConnectionResult) message.obj;
                Iterator<zaa<?>> it2 = this.zam.values().iterator();
                while (true) {
                    if (it2.hasNext()) {
                        zaa<?> next2 = it2.next();
                        if (next2.zal() == i10) {
                            zaaVar = next2;
                        }
                    }
                }
                if (zaaVar == null) {
                    StringBuilder sb = new StringBuilder(76);
                    sb.append("Could not find API instance ");
                    sb.append(i10);
                    sb.append(" while trying to fail enqueued calls.");
                    Log.wtf("GoogleApiManager", sb.toString(), new Exception());
                } else if (connectionResult.getErrorCode() == 13) {
                    String errorString = this.zai.getErrorString(connectionResult.getErrorCode());
                    String errorMessage = connectionResult.getErrorMessage();
                    StringBuilder sb2 = new StringBuilder(String.valueOf(errorString).length() + 69 + String.valueOf(errorMessage).length());
                    sb2.append("Error resolution was canceled by the user, original error message: ");
                    sb2.append(errorString);
                    sb2.append(": ");
                    sb2.append(errorMessage);
                    zaaVar.zaa(new Status(17, sb2.toString()));
                } else {
                    zaaVar.zaa(zab((ApiKey<?>) ((zaa) zaaVar).zad, connectionResult));
                }
                return true;
            case 6:
                if (this.zah.getApplicationContext() instanceof Application) {
                    BackgroundDetector.initialize((Application) this.zah.getApplicationContext());
                    BackgroundDetector.getInstance().addListener(new zabc(this));
                    if (!BackgroundDetector.getInstance().readCurrentStateIfPossible(true)) {
                        this.zae = 300000L;
                    }
                }
                return true;
            case 7:
                zac((GoogleApi<?>) message.obj);
                return true;
            case 9:
                if (this.zam.containsKey(message.obj)) {
                    this.zam.get(message.obj).zaf();
                }
                return true;
            case 10:
                Iterator<ApiKey<?>> it3 = this.zap.iterator();
                while (it3.hasNext()) {
                    zaa<?> zaaVarRemove = this.zam.remove(it3.next());
                    if (zaaVarRemove != null) {
                        zaaVarRemove.zaa();
                    }
                }
                this.zap.clear();
                return true;
            case 11:
                if (this.zam.containsKey(message.obj)) {
                    this.zam.get(message.obj).zag();
                }
                return true;
            case 12:
                if (this.zam.containsKey(message.obj)) {
                    this.zam.get(message.obj).zah();
                }
                return true;
            case 14:
                zaaa zaaaVar = (zaaa) message.obj;
                ApiKey<?> apiKeyZaa = zaaaVar.zaa();
                if (this.zam.containsKey(apiKeyZaa)) {
                    zaaaVar.zab().setResult(Boolean.valueOf(zaa.zaa((zaa) this.zam.get(apiKeyZaa), false)));
                } else {
                    zaaaVar.zab().setResult(Boolean.FALSE);
                }
                return true;
            case 15:
                zab zabVar = (zab) message.obj;
                if (this.zam.containsKey(zabVar.zaa)) {
                    this.zam.get(zabVar.zaa).zaa(zabVar);
                }
                return true;
            case 16:
                zab zabVar2 = (zab) message.obj;
                if (this.zam.containsKey(zabVar2.zaa)) {
                    this.zam.get(zabVar2.zaa).zab(zabVar2);
                }
                return true;
            default:
                StringBuilder sb3 = new StringBuilder(31);
                sb3.append("Unknown message id: ");
                sb3.append(i9);
                Log.w("GoogleApiManager", sb3.toString());
                return false;
        }
    }

    @RecentlyNonNull
    public final int zab() {
        return this.zak.getAndIncrement();
    }

    public class zac implements zace, BaseGmsClient.ConnectionProgressReportCallbacks {
        private final Api.Client zab;
        private final ApiKey<?> zac;
        private IAccountAccessor zad = null;
        private Set<Scope> zae = null;
        private boolean zaf = false;

        public zac(Api.Client client, ApiKey<?> apiKey) {
            this.zab = client;
            this.zac = apiKey;
        }

        @Override // com.google.android.gms.common.internal.BaseGmsClient.ConnectionProgressReportCallbacks
        public final void onReportServiceBinding(ConnectionResult connectionResult) {
            GoogleApiManager.this.zaq.post(new zabi(this, connectionResult));
        }

        @Override // com.google.android.gms.common.api.internal.zace
        public final void zaa(ConnectionResult connectionResult) {
            zaa zaaVar = (zaa) GoogleApiManager.this.zam.get(this.zac);
            if (zaaVar != null) {
                zaaVar.zaa(connectionResult);
            }
        }

        @Override // com.google.android.gms.common.api.internal.zace
        public final void zaa(IAccountAccessor iAccountAccessor, Set<Scope> set) {
            if (iAccountAccessor != null && set != null) {
                this.zad = iAccountAccessor;
                this.zae = set;
                zaa();
            } else {
                Log.wtf("GoogleApiManager", "Received null response from onSignInSuccess", new Exception());
                zaa(new ConnectionResult(4));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zaa() {
            IAccountAccessor iAccountAccessor;
            if (!this.zaf || (iAccountAccessor = this.zad) == null) {
                return;
            }
            this.zab.getRemoteService(iAccountAccessor, this.zae);
        }

        public static /* synthetic */ boolean zaa(zac zacVar, boolean z8) {
            zacVar.zaf = true;
            return true;
        }
    }

    public final void zab(zax zaxVar) {
        synchronized (zaf) {
            if (this.zan == zaxVar) {
                this.zan = null;
                this.zao.clear();
            }
        }
    }

    public static class zab {
        private final ApiKey<?> zaa;
        private final Feature zab;

        private zab(ApiKey<?> apiKey, Feature feature) {
            this.zaa = apiKey;
            this.zab = feature;
        }

        public final boolean equals(Object obj) {
            if (obj != null && (obj instanceof zab)) {
                zab zabVar = (zab) obj;
                if (Objects.equal(this.zaa, zabVar.zaa) && Objects.equal(this.zab, zabVar.zab)) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            return Objects.hashCode(this.zaa, this.zab);
        }

        public final String toString() {
            return Objects.toStringHelper(this).add("key", this.zaa).add("feature", this.zab).toString();
        }

        public /* synthetic */ zab(ApiKey apiKey, Feature feature, zabc zabcVar) {
            this(apiKey, feature);
        }
    }

    @RecentlyNonNull
    public final Task<Boolean> zab(@RecentlyNonNull GoogleApi<?> googleApi) {
        zaaa zaaaVar = new zaaa(googleApi.getApiKey());
        Handler handler = this.zaq;
        handler.sendMessage(handler.obtainMessage(14, zaaaVar));
        return zaaaVar.zab().getTask();
    }

    public final void zac() {
        Handler handler = this.zaq;
        handler.sendMessage(handler.obtainMessage(3));
    }

    @RecentlyNonNull
    public static GoogleApiManager zaa() {
        GoogleApiManager googleApiManager;
        synchronized (zaf) {
            Preconditions.checkNotNull(zag, "Must guarantee manager is non-null before using getInstance");
            googleApiManager = zag;
        }
        return googleApiManager;
    }

    public final void zab(@RecentlyNonNull ConnectionResult connectionResult, @RecentlyNonNull int i9) {
        if (zaa(connectionResult, i9)) {
            return;
        }
        Handler handler = this.zaq;
        handler.sendMessage(handler.obtainMessage(5, i9, 0, connectionResult));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Status zab(ApiKey<?> apiKey, ConnectionResult connectionResult) {
        String apiName = apiKey.getApiName();
        String strValueOf = String.valueOf(connectionResult);
        StringBuilder sb = new StringBuilder(String.valueOf(apiName).length() + 63 + strValueOf.length());
        sb.append("API: ");
        sb.append(apiName);
        sb.append(" is not available on this device. Connection failed with: ");
        sb.append(strValueOf);
        return new Status(connectionResult, sb.toString());
    }

    public final void zaa(@RecentlyNonNull GoogleApi<?> googleApi) {
        Handler handler = this.zaq;
        handler.sendMessage(handler.obtainMessage(7, googleApi));
    }

    public final void zaa(zax zaxVar) {
        synchronized (zaf) {
            if (this.zan != zaxVar) {
                this.zan = zaxVar;
                this.zao.clear();
            }
            this.zao.addAll(zaxVar.zac());
        }
    }

    @RecentlyNonNull
    public final Task<Map<ApiKey<?>, String>> zaa(@RecentlyNonNull Iterable<? extends HasApiKey<?>> iterable) {
        zaj zajVar = new zaj(iterable);
        Handler handler = this.zaq;
        handler.sendMessage(handler.obtainMessage(2, zajVar));
        return zajVar.zab();
    }

    public final <O extends Api.ApiOptions> void zaa(@RecentlyNonNull GoogleApi<O> googleApi, @RecentlyNonNull int i9, @RecentlyNonNull BaseImplementation.ApiMethodImpl<? extends Result, Api.AnyClient> apiMethodImpl) {
        zad zadVar = new zad(i9, apiMethodImpl);
        Handler handler = this.zaq;
        handler.sendMessage(handler.obtainMessage(4, new zabr(zadVar, this.zal.get(), googleApi)));
    }

    public final <O extends Api.ApiOptions, ResultT> void zaa(@RecentlyNonNull GoogleApi<O> googleApi, @RecentlyNonNull int i9, @RecentlyNonNull TaskApiCall<Api.AnyClient, ResultT> taskApiCall, @RecentlyNonNull TaskCompletionSource<ResultT> taskCompletionSource, @RecentlyNonNull StatusExceptionMapper statusExceptionMapper) {
        zaf zafVar = new zaf(i9, taskApiCall, taskCompletionSource, statusExceptionMapper);
        Handler handler = this.zaq;
        handler.sendMessage(handler.obtainMessage(4, new zabr(zafVar, this.zal.get(), googleApi)));
    }

    @RecentlyNonNull
    public final <O extends Api.ApiOptions> Task<Void> zaa(@RecentlyNonNull GoogleApi<O> googleApi, @RecentlyNonNull RegisterListenerMethod<Api.AnyClient, ?> registerListenerMethod, @RecentlyNonNull UnregisterListenerMethod<Api.AnyClient, ?> unregisterListenerMethod, @RecentlyNonNull Runnable runnable) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        zag zagVar = new zag(new zabs(registerListenerMethod, unregisterListenerMethod, runnable), taskCompletionSource);
        Handler handler = this.zaq;
        handler.sendMessage(handler.obtainMessage(8, new zabr(zagVar, this.zal.get(), googleApi)));
        return taskCompletionSource.getTask();
    }

    @RecentlyNonNull
    public final <O extends Api.ApiOptions> Task<Boolean> zaa(@RecentlyNonNull GoogleApi<O> googleApi, @RecentlyNonNull ListenerHolder.ListenerKey<?> listenerKey) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        zah zahVar = new zah(listenerKey, taskCompletionSource);
        Handler handler = this.zaq;
        handler.sendMessage(handler.obtainMessage(13, new zabr(zahVar, this.zal.get(), googleApi)));
        return taskCompletionSource.getTask();
    }

    public final boolean zaa(ConnectionResult connectionResult, int i9) {
        return this.zai.zaa(this.zah, connectionResult, i9);
    }
}
