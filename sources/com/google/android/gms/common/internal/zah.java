package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import org.checkerframework.checker.initialization.qual.NotOnlyInitialized;

/* loaded from: classes2.dex */
public final class zah implements Handler.Callback {

    @NotOnlyInitialized
    private final zak zaa;
    private final Handler zah;
    private final ArrayList<GoogleApiClient.ConnectionCallbacks> zab = new ArrayList<>();

    @VisibleForTesting
    private final ArrayList<GoogleApiClient.ConnectionCallbacks> zac = new ArrayList<>();
    private final ArrayList<GoogleApiClient.OnConnectionFailedListener> zad = new ArrayList<>();
    private volatile boolean zae = false;
    private final AtomicInteger zaf = new AtomicInteger(0);
    private boolean zag = false;
    private final Object zai = new Object();

    public zah(Looper looper, zak zakVar) {
        this.zaa = zakVar;
        this.zah = new com.google.android.gms.internal.base.zap(looper, this);
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        int i9 = message.what;
        if (i9 != 1) {
            StringBuilder sb = new StringBuilder(45);
            sb.append("Don't know how to handle message: ");
            sb.append(i9);
            Log.wtf("GmsClientEvents", sb.toString(), new Exception());
            return false;
        }
        GoogleApiClient.ConnectionCallbacks connectionCallbacks = (GoogleApiClient.ConnectionCallbacks) message.obj;
        synchronized (this.zai) {
            if (this.zae && this.zaa.isConnected() && this.zab.contains(connectionCallbacks)) {
                connectionCallbacks.onConnected(this.zaa.getConnectionHint());
            }
        }
        return true;
    }

    public final void zaa() {
        this.zae = false;
        this.zaf.incrementAndGet();
    }

    public final void zab() {
        this.zae = true;
    }

    public final void zac(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        Preconditions.checkNotNull(connectionCallbacks);
        synchronized (this.zai) {
            if (!this.zab.remove(connectionCallbacks)) {
                String strValueOf = String.valueOf(connectionCallbacks);
                StringBuilder sb = new StringBuilder(strValueOf.length() + 52);
                sb.append("unregisterConnectionCallbacks(): listener ");
                sb.append(strValueOf);
                sb.append(" not found");
                Log.w("GmsClientEvents", sb.toString());
            } else if (this.zag) {
                this.zac.add(connectionCallbacks);
            }
        }
    }

    public final boolean zab(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        boolean zContains;
        Preconditions.checkNotNull(connectionCallbacks);
        synchronized (this.zai) {
            zContains = this.zab.contains(connectionCallbacks);
        }
        return zContains;
    }

    @VisibleForTesting
    public final void zaa(Bundle bundle) {
        Preconditions.checkHandlerThread(this.zah, "onConnectionSuccess must only be called on the Handler thread");
        synchronized (this.zai) {
            boolean z8 = true;
            Preconditions.checkState(!this.zag);
            this.zah.removeMessages(1);
            this.zag = true;
            if (this.zac.size() != 0) {
                z8 = false;
            }
            Preconditions.checkState(z8);
            ArrayList arrayList = new ArrayList(this.zab);
            int i9 = this.zaf.get();
            int size = arrayList.size();
            int i10 = 0;
            while (i10 < size) {
                Object obj = arrayList.get(i10);
                i10++;
                GoogleApiClient.ConnectionCallbacks connectionCallbacks = (GoogleApiClient.ConnectionCallbacks) obj;
                if (!this.zae || !this.zaa.isConnected() || this.zaf.get() != i9) {
                    break;
                } else if (!this.zac.contains(connectionCallbacks)) {
                    connectionCallbacks.onConnected(bundle);
                }
            }
            this.zac.clear();
            this.zag = false;
        }
    }

    public final boolean zab(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        boolean zContains;
        Preconditions.checkNotNull(onConnectionFailedListener);
        synchronized (this.zai) {
            zContains = this.zad.contains(onConnectionFailedListener);
        }
        return zContains;
    }

    public final void zac(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        Preconditions.checkNotNull(onConnectionFailedListener);
        synchronized (this.zai) {
            if (!this.zad.remove(onConnectionFailedListener)) {
                String strValueOf = String.valueOf(onConnectionFailedListener);
                StringBuilder sb = new StringBuilder(strValueOf.length() + 57);
                sb.append("unregisterConnectionFailedListener(): listener ");
                sb.append(strValueOf);
                sb.append(" not found");
                Log.w("GmsClientEvents", sb.toString());
            }
        }
    }

    @VisibleForTesting
    public final void zaa(int i9) {
        Preconditions.checkHandlerThread(this.zah, "onUnintentionalDisconnection must only be called on the Handler thread");
        this.zah.removeMessages(1);
        synchronized (this.zai) {
            this.zag = true;
            ArrayList arrayList = new ArrayList(this.zab);
            int i10 = this.zaf.get();
            int size = arrayList.size();
            int i11 = 0;
            while (i11 < size) {
                Object obj = arrayList.get(i11);
                i11++;
                GoogleApiClient.ConnectionCallbacks connectionCallbacks = (GoogleApiClient.ConnectionCallbacks) obj;
                if (!this.zae || this.zaf.get() != i10) {
                    break;
                } else if (this.zab.contains(connectionCallbacks)) {
                    connectionCallbacks.onConnectionSuspended(i9);
                }
            }
            this.zac.clear();
            this.zag = false;
        }
    }

    @VisibleForTesting
    public final void zaa(ConnectionResult connectionResult) {
        Preconditions.checkHandlerThread(this.zah, "onConnectionFailure must only be called on the Handler thread");
        this.zah.removeMessages(1);
        synchronized (this.zai) {
            ArrayList arrayList = new ArrayList(this.zad);
            int i9 = this.zaf.get();
            int size = arrayList.size();
            int i10 = 0;
            while (i10 < size) {
                Object obj = arrayList.get(i10);
                i10++;
                GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener = (GoogleApiClient.OnConnectionFailedListener) obj;
                if (this.zae && this.zaf.get() == i9) {
                    if (this.zad.contains(onConnectionFailedListener)) {
                        onConnectionFailedListener.onConnectionFailed(connectionResult);
                    }
                }
                return;
            }
        }
    }

    public final void zaa(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        Preconditions.checkNotNull(connectionCallbacks);
        synchronized (this.zai) {
            if (this.zab.contains(connectionCallbacks)) {
                String strValueOf = String.valueOf(connectionCallbacks);
                StringBuilder sb = new StringBuilder(strValueOf.length() + 62);
                sb.append("registerConnectionCallbacks(): listener ");
                sb.append(strValueOf);
                sb.append(" is already registered");
                Log.w("GmsClientEvents", sb.toString());
            } else {
                this.zab.add(connectionCallbacks);
            }
        }
        if (this.zaa.isConnected()) {
            Handler handler = this.zah;
            handler.sendMessage(handler.obtainMessage(1, connectionCallbacks));
        }
    }

    public final void zaa(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        Preconditions.checkNotNull(onConnectionFailedListener);
        synchronized (this.zai) {
            if (this.zad.contains(onConnectionFailedListener)) {
                String strValueOf = String.valueOf(onConnectionFailedListener);
                StringBuilder sb = new StringBuilder(strValueOf.length() + 67);
                sb.append("registerConnectionFailedListener(): listener ");
                sb.append(strValueOf);
                sb.append(" is already registered");
                Log.w("GmsClientEvents", sb.toString());
            } else {
                this.zad.add(onConnectionFailedListener);
            }
        }
    }
}
