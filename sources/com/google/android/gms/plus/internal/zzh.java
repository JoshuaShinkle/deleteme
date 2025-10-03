package com.google.android.gms.plus.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.common.internal.ICancelToken;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.plus.zzr;
import com.google.android.gms.plus.People;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

/* loaded from: classes2.dex */
public final class zzh extends GmsClient<zzf> {
    private Person zzr;
    private final zzn zzs;

    public zzh(Context context, Looper looper, ClientSettings clientSettings, zzn zznVar, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 2, clientSettings, connectionCallbacks, onConnectionFailedListener);
        this.zzs = zznVar;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.plus.internal.IPlusService");
        return iInterfaceQueryLocalInterface instanceof zzf ? (zzf) iInterfaceQueryLocalInterface : new zzg(iBinder);
    }

    @VisibleForTesting
    public final String getAccountName() {
        checkConnected();
        try {
            return ((zzf) getService()).getAccountName();
        } catch (RemoteException e9) {
            throw new IllegalStateException(e9);
        }
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final Bundle getGetServiceRequestExtraArgs() {
        Bundle bundleZze = this.zzs.zze();
        bundleZze.putStringArray("request_visible_actions", this.zzs.zzc());
        bundleZze.putString(ServiceSpecificExtraArgs.PlusExtraArgs.PLUS_AUTH_PACKAGE, this.zzs.zzd());
        return bundleZze;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final int getMinApkVersion() {
        return 12451000;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final String getServiceDescriptor() {
        return "com.google.android.gms.plus.internal.IPlusService";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final String getStartServiceAction() {
        return "com.google.android.gms.plus.service.START";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final void onPostInitHandler(int i9, IBinder iBinder, Bundle bundle, int i10) {
        if (i9 == 0 && bundle != null && bundle.containsKey("loaded_person")) {
            this.zzr = zzr.zza(bundle.getByteArray("loaded_person"));
        }
        super.onPostInitHandler(i9, iBinder, bundle, i10);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final boolean requiresSignIn() {
        Set<Scope> applicableScopes = getClientSettings().getApplicableScopes(Plus.API);
        if (applicableScopes == null || applicableScopes.isEmpty()) {
            return false;
        }
        return (applicableScopes.size() == 1 && applicableScopes.contains(new Scope("plus_one_placeholder_scope"))) ? false : true;
    }

    @VisibleForTesting
    public final ICancelToken zza(BaseImplementation.ResultHolder<People.LoadPeopleResult> resultHolder, int i9, String str) {
        checkConnected();
        zzj zzjVar = new zzj(resultHolder);
        try {
            return ((zzf) getService()).zza(zzjVar, 1, i9, -1, str);
        } catch (RemoteException unused) {
            zzjVar.zza(DataHolder.empty(8), (String) null);
            return null;
        }
    }

    @VisibleForTesting
    public final void zza() {
        checkConnected();
        try {
            this.zzr = null;
            ((zzf) getService()).zza();
        } catch (RemoteException e9) {
            throw new IllegalStateException(e9);
        }
    }

    public final void zza(BaseImplementation.ResultHolder<People.LoadPeopleResult> resultHolder) {
        checkConnected();
        zzj zzjVar = new zzj(resultHolder);
        try {
            ((zzf) getService()).zza(zzjVar, 2, 1, -1, null);
        } catch (RemoteException unused) {
            zzjVar.zza(DataHolder.empty(8), (String) null);
        }
    }

    @VisibleForTesting
    public final void zza(BaseImplementation.ResultHolder<People.LoadPeopleResult> resultHolder, Collection<String> collection) {
        checkConnected();
        zzj zzjVar = new zzj(resultHolder);
        try {
            ((zzf) getService()).zza(zzjVar, new ArrayList(collection));
        } catch (RemoteException unused) {
            zzjVar.zza(DataHolder.empty(8), (String) null);
        }
    }

    public final void zza(BaseImplementation.ResultHolder<People.LoadPeopleResult> resultHolder, String[] strArr) {
        zza(resultHolder, Arrays.asList(strArr));
    }

    @VisibleForTesting
    public final Person zzb() {
        checkConnected();
        return this.zzr;
    }

    @VisibleForTesting
    public final void zzb(BaseImplementation.ResultHolder<Status> resultHolder) {
        checkConnected();
        zza();
        zzk zzkVar = new zzk(resultHolder);
        try {
            ((zzf) getService()).zza(zzkVar);
        } catch (RemoteException unused) {
            zzkVar.zza(8, (Bundle) null);
        }
    }
}
