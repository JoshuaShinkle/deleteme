package com.google.android.gms.common.api.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.GmsClientSupervisor;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.Preconditions;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Set;

@KeepForSdk
/* loaded from: classes2.dex */
public final class NonGmsServiceBrokerClient implements ServiceConnection, Api.Client {
    private static final String zaa = "NonGmsServiceBrokerClient";
    private final String zab;
    private final String zac;
    private final ComponentName zad;
    private final Context zae;
    private final ConnectionCallbacks zaf;
    private final Handler zag;
    private final OnConnectionFailedListener zah;
    private IBinder zai;
    private boolean zaj;
    private String zak;

    @KeepForSdk
    public NonGmsServiceBrokerClient(@RecentlyNonNull Context context, @RecentlyNonNull Looper looper, @RecentlyNonNull String str, @RecentlyNonNull String str2, @RecentlyNonNull ConnectionCallbacks connectionCallbacks, @RecentlyNonNull OnConnectionFailedListener onConnectionFailedListener) {
        this(context, looper, str, str2, null, connectionCallbacks, onConnectionFailedListener);
    }

    private final void zaa(String str) {
        new StringBuilder(String.valueOf(str).length() + 30 + String.valueOf(this.zai).length());
    }

    private final void zab() {
        if (Thread.currentThread() != this.zag.getLooper().getThread()) {
            throw new IllegalStateException("This method should only run on the NonGmsServiceBrokerClient's handler thread.");
        }
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public final void connect(@RecentlyNonNull BaseGmsClient.ConnectionProgressReportCallbacks connectionProgressReportCallbacks) {
        zab();
        zaa("Connect started.");
        if (isConnected()) {
            try {
                disconnect("connect() called when already connected");
            } catch (Exception unused) {
            }
        }
        try {
            Intent intent = new Intent();
            ComponentName componentName = this.zad;
            if (componentName != null) {
                intent.setComponent(componentName);
            } else {
                intent.setPackage(this.zab).setAction(this.zac);
            }
            boolean zBindService = this.zae.bindService(intent, this, GmsClientSupervisor.getDefaultBindFlags());
            this.zaj = zBindService;
            if (!zBindService) {
                this.zai = null;
                this.zah.onConnectionFailed(new ConnectionResult(16));
            }
            zaa("Finished connect.");
        } catch (SecurityException e9) {
            this.zaj = false;
            this.zai = null;
            throw e9;
        }
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public final void disconnect(@RecentlyNonNull String str) {
        zab();
        this.zak = str;
        disconnect();
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public final void dump(@RecentlyNonNull String str, FileDescriptor fileDescriptor, @RecentlyNonNull PrintWriter printWriter, String[] strArr) {
    }

    @Override // com.google.android.gms.common.api.Api.Client
    @RecentlyNonNull
    public final Feature[] getAvailableFeatures() {
        return new Feature[0];
    }

    @RecentlyNullable
    @KeepForSdk
    public final IBinder getBinder() {
        zab();
        return this.zai;
    }

    @Override // com.google.android.gms.common.api.Api.Client
    @RecentlyNonNull
    public final String getEndpointPackageName() {
        String str = this.zab;
        if (str != null) {
            return str;
        }
        Preconditions.checkNotNull(this.zad);
        return this.zad.getPackageName();
    }

    @Override // com.google.android.gms.common.api.Api.Client
    @RecentlyNullable
    public final String getLastDisconnectMessage() {
        return this.zak;
    }

    @Override // com.google.android.gms.common.api.Api.Client
    @RecentlyNonNull
    public final int getMinApkVersion() {
        return 0;
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public final void getRemoteService(IAccountAccessor iAccountAccessor, Set<Scope> set) {
    }

    @Override // com.google.android.gms.common.api.Api.Client
    @RecentlyNonNull
    public final Feature[] getRequiredFeatures() {
        return new Feature[0];
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public final Set<Scope> getScopesForConnectionlessNonSignIn() {
        return Collections.emptySet();
    }

    @Override // com.google.android.gms.common.api.Api.Client
    @RecentlyNullable
    public final IBinder getServiceBrokerBinder() {
        return null;
    }

    @Override // com.google.android.gms.common.api.Api.Client
    @RecentlyNonNull
    public final Intent getSignInIntent() {
        return new Intent();
    }

    @Override // com.google.android.gms.common.api.Api.Client
    @RecentlyNonNull
    public final boolean isConnected() {
        zab();
        return this.zai != null;
    }

    @Override // com.google.android.gms.common.api.Api.Client
    @RecentlyNonNull
    public final boolean isConnecting() {
        zab();
        return this.zaj;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(@RecentlyNonNull ComponentName componentName, @RecentlyNonNull final IBinder iBinder) {
        this.zag.post(new Runnable(this, iBinder) { // from class: com.google.android.gms.common.api.internal.zabp
            private final NonGmsServiceBrokerClient zaa;
            private final IBinder zab;

            {
                this.zaa = this;
                this.zab = iBinder;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zaa.zaa(this.zab);
            }
        });
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(@RecentlyNonNull ComponentName componentName) {
        this.zag.post(new Runnable(this) { // from class: com.google.android.gms.common.api.internal.zabq
            private final NonGmsServiceBrokerClient zaa;

            {
                this.zaa = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zaa.zaa();
            }
        });
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public final void onUserSignOut(@RecentlyNonNull BaseGmsClient.SignOutCallbacks signOutCallbacks) {
    }

    @Override // com.google.android.gms.common.api.Api.Client
    @RecentlyNonNull
    public final boolean providesSignIn() {
        return false;
    }

    @Override // com.google.android.gms.common.api.Api.Client
    @RecentlyNonNull
    public final boolean requiresAccount() {
        return false;
    }

    @Override // com.google.android.gms.common.api.Api.Client
    @RecentlyNonNull
    public final boolean requiresGooglePlayServices() {
        return false;
    }

    @Override // com.google.android.gms.common.api.Api.Client
    @RecentlyNonNull
    public final boolean requiresSignIn() {
        return false;
    }

    @KeepForSdk
    public NonGmsServiceBrokerClient(@RecentlyNonNull Context context, @RecentlyNonNull Looper looper, @RecentlyNonNull ComponentName componentName, @RecentlyNonNull ConnectionCallbacks connectionCallbacks, @RecentlyNonNull OnConnectionFailedListener onConnectionFailedListener) {
        this(context, looper, null, null, componentName, connectionCallbacks, onConnectionFailedListener);
    }

    public final /* synthetic */ void zaa() {
        this.zaj = false;
        this.zai = null;
        zaa("Disconnected.");
        this.zaf.onConnectionSuspended(1);
    }

    private NonGmsServiceBrokerClient(Context context, Looper looper, String str, String str2, ComponentName componentName, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        this.zaj = false;
        this.zak = null;
        this.zae = context;
        this.zag = new com.google.android.gms.internal.base.zap(looper);
        this.zaf = connectionCallbacks;
        this.zah = onConnectionFailedListener;
        boolean z8 = (str == null || str2 == null) ? false : true;
        boolean z9 = componentName != null;
        if (!z8 ? z9 : !z9) {
            this.zab = str;
            this.zac = str2;
            this.zad = componentName;
            return;
        }
        throw new AssertionError("Must specify either package or component, but not both");
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public final void disconnect() {
        zab();
        zaa("Disconnect called.");
        this.zae.unbindService(this);
        this.zaj = false;
        this.zai = null;
    }

    public final /* synthetic */ void zaa(IBinder iBinder) {
        this.zaj = false;
        this.zai = iBinder;
        zaa("Connected.");
        this.zaf.onConnected(new Bundle());
    }
}
