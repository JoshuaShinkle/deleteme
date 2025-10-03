package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Handler;
import android.os.IInterface;
import android.os.Looper;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

@KeepForSdk
/* loaded from: classes2.dex */
public abstract class GmsClient<T extends IInterface> extends BaseGmsClient<T> implements Api.Client, zak {
    private final ClientSettings zaa;
    private final Set<Scope> zab;
    private final Account zac;

    @VisibleForTesting
    @KeepForSdk
    public GmsClient(@RecentlyNonNull Context context, @RecentlyNonNull Handler handler, @RecentlyNonNull int i9, @RecentlyNonNull ClientSettings clientSettings) {
        this(context, handler, GmsClientSupervisor.getInstance(context), GoogleApiAvailability.getInstance(), i9, clientSettings, (GoogleApiClient.ConnectionCallbacks) null, (GoogleApiClient.OnConnectionFailedListener) null);
    }

    private final Set<Scope> zaa(Set<Scope> set) {
        Set<Scope> setValidateScopes = validateScopes(set);
        Iterator<Scope> it = setValidateScopes.iterator();
        while (it.hasNext()) {
            if (!set.contains(it.next())) {
                throw new IllegalStateException("Expanding scopes is not permitted, use implied scopes instead");
            }
        }
        return setValidateScopes;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    @RecentlyNullable
    public final Account getAccount() {
        return this.zac;
    }

    @RecentlyNonNull
    @KeepForSdk
    public final ClientSettings getClientSettings() {
        return this.zaa;
    }

    @Override // com.google.android.gms.common.api.Api.Client
    @RecentlyNonNull
    @KeepForSdk
    public Feature[] getRequiredFeatures() {
        return new Feature[0];
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    @RecentlyNonNull
    @KeepForSdk
    public final Set<Scope> getScopes() {
        return this.zab;
    }

    @Override // com.google.android.gms.common.api.Api.Client
    @KeepForSdk
    public Set<Scope> getScopesForConnectionlessNonSignIn() {
        return requiresSignIn() ? this.zab : Collections.emptySet();
    }

    @KeepForSdk
    public Set<Scope> validateScopes(@RecentlyNonNull Set<Scope> set) {
        return set;
    }

    @KeepForSdk
    public GmsClient(@RecentlyNonNull Context context, @RecentlyNonNull Looper looper, @RecentlyNonNull int i9, @RecentlyNonNull ClientSettings clientSettings, @RecentlyNonNull ConnectionCallbacks connectionCallbacks, @RecentlyNonNull OnConnectionFailedListener onConnectionFailedListener) {
        this(context, looper, GmsClientSupervisor.getInstance(context), GoogleApiAvailability.getInstance(), i9, clientSettings, (ConnectionCallbacks) Preconditions.checkNotNull(connectionCallbacks), (OnConnectionFailedListener) Preconditions.checkNotNull(onConnectionFailedListener));
    }

    private static BaseGmsClient.BaseConnectionCallbacks zaa(ConnectionCallbacks connectionCallbacks) {
        if (connectionCallbacks == null) {
            return null;
        }
        return new zag(connectionCallbacks);
    }

    private static BaseGmsClient.BaseOnConnectionFailedListener zaa(OnConnectionFailedListener onConnectionFailedListener) {
        if (onConnectionFailedListener == null) {
            return null;
        }
        return new zai(onConnectionFailedListener);
    }

    @KeepForSdk
    @Deprecated
    public GmsClient(@RecentlyNonNull Context context, @RecentlyNonNull Looper looper, @RecentlyNonNull int i9, @RecentlyNonNull ClientSettings clientSettings, @RecentlyNonNull GoogleApiClient.ConnectionCallbacks connectionCallbacks, @RecentlyNonNull GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this(context, looper, i9, clientSettings, (ConnectionCallbacks) connectionCallbacks, (OnConnectionFailedListener) onConnectionFailedListener);
    }

    @KeepForSdk
    public GmsClient(@RecentlyNonNull Context context, @RecentlyNonNull Looper looper, @RecentlyNonNull int i9, @RecentlyNonNull ClientSettings clientSettings) {
        this(context, looper, GmsClientSupervisor.getInstance(context), GoogleApiAvailability.getInstance(), i9, clientSettings, (GoogleApiClient.ConnectionCallbacks) null, (GoogleApiClient.OnConnectionFailedListener) null);
    }

    @VisibleForTesting
    private GmsClient(Context context, Looper looper, GmsClientSupervisor gmsClientSupervisor, GoogleApiAvailability googleApiAvailability, int i9, ClientSettings clientSettings, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, gmsClientSupervisor, googleApiAvailability, i9, zaa(connectionCallbacks), zaa(onConnectionFailedListener), clientSettings.zab());
        this.zaa = clientSettings;
        this.zac = clientSettings.getAccount();
        this.zab = zaa(clientSettings.getAllRequestedScopes());
    }

    @VisibleForTesting
    private GmsClient(Context context, Looper looper, GmsClientSupervisor gmsClientSupervisor, GoogleApiAvailability googleApiAvailability, int i9, ClientSettings clientSettings, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this(context, looper, gmsClientSupervisor, googleApiAvailability, i9, clientSettings, (ConnectionCallbacks) null, (OnConnectionFailedListener) null);
    }

    @VisibleForTesting
    private GmsClient(Context context, Handler handler, GmsClientSupervisor gmsClientSupervisor, GoogleApiAvailability googleApiAvailability, int i9, ClientSettings clientSettings, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, handler, gmsClientSupervisor, googleApiAvailability, i9, zaa((ConnectionCallbacks) null), zaa((OnConnectionFailedListener) null));
        this.zaa = (ClientSettings) Preconditions.checkNotNull(clientSettings);
        this.zac = clientSettings.getAccount();
        this.zab = zaa(clientSettings.getAllRequestedScopes());
    }

    @VisibleForTesting
    @Deprecated
    private GmsClient(Context context, Handler handler, GmsClientSupervisor gmsClientSupervisor, GoogleApiAvailability googleApiAvailability, int i9, ClientSettings clientSettings, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this(context, handler, gmsClientSupervisor, googleApiAvailability, i9, clientSettings, (ConnectionCallbacks) null, (OnConnectionFailedListener) null);
    }
}
