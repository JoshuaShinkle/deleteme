package com.google.android.gms.signin.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.zar;
import com.google.android.gms.signin.SignInOptions;

@KeepForSdk
/* loaded from: classes2.dex */
public class SignInClientImpl extends GmsClient<zae> implements com.google.android.gms.signin.zad {
    private final boolean zaa;
    private final ClientSettings zab;
    private final Bundle zac;
    private final Integer zad;

    public SignInClientImpl(@RecentlyNonNull Context context, @RecentlyNonNull Looper looper, @RecentlyNonNull boolean z8, @RecentlyNonNull ClientSettings clientSettings, @RecentlyNonNull Bundle bundle, @RecentlyNonNull GoogleApiClient.ConnectionCallbacks connectionCallbacks, @RecentlyNonNull GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 44, clientSettings, connectionCallbacks, onConnectionFailedListener);
        this.zaa = z8;
        this.zab = clientSettings;
        this.zac = bundle;
        this.zad = clientSettings.zad();
    }

    @RecentlyNonNull
    @KeepForSdk
    public static Bundle createBundleFromClientSettings(@RecentlyNonNull ClientSettings clientSettings) {
        SignInOptions signInOptionsZac = clientSettings.zac();
        Integer numZad = clientSettings.zad();
        Bundle bundle = new Bundle();
        bundle.putParcelable("com.google.android.gms.signin.internal.clientRequestedAccount", clientSettings.getAccount());
        if (numZad != null) {
            bundle.putInt("com.google.android.gms.common.internal.ClientSettings.sessionId", numZad.intValue());
        }
        if (signInOptionsZac != null) {
            bundle.putBoolean("com.google.android.gms.signin.internal.offlineAccessRequested", false);
            bundle.putBoolean("com.google.android.gms.signin.internal.idTokenRequested", false);
            bundle.putString("com.google.android.gms.signin.internal.serverClientId", null);
            bundle.putBoolean("com.google.android.gms.signin.internal.usePromptModeForAuthCode", true);
            bundle.putBoolean("com.google.android.gms.signin.internal.forceCodeForRefreshToken", false);
            bundle.putString("com.google.android.gms.signin.internal.hostedDomain", null);
            bundle.putString("com.google.android.gms.signin.internal.logSessionId", null);
            bundle.putBoolean("com.google.android.gms.signin.internal.waitForAccessTokenRefresh", false);
        }
        return bundle;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    @RecentlyNonNull
    public /* synthetic */ IInterface createServiceInterface(@RecentlyNonNull IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.signin.internal.ISignInService");
        return iInterfaceQueryLocalInterface instanceof zae ? (zae) iInterfaceQueryLocalInterface : new zah(iBinder);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    @RecentlyNonNull
    public Bundle getGetServiceRequestExtraArgs() {
        if (!getContext().getPackageName().equals(this.zab.getRealClientPackageName())) {
            this.zac.putString("com.google.android.gms.signin.internal.realClientPackageName", this.zab.getRealClientPackageName());
        }
        return this.zac;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    @RecentlyNonNull
    public int getMinApkVersion() {
        return 12451000;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    @RecentlyNonNull
    public String getServiceDescriptor() {
        return "com.google.android.gms.signin.internal.ISignInService";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    @RecentlyNonNull
    public String getStartServiceAction() {
        return "com.google.android.gms.signin.service.START";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    @RecentlyNonNull
    public boolean requiresSignIn() {
        return this.zaa;
    }

    @Override // com.google.android.gms.signin.zad
    public final void zaa(@RecentlyNonNull IAccountAccessor iAccountAccessor, @RecentlyNonNull boolean z8) {
        try {
            ((zae) getService()).zaa(iAccountAccessor, ((Integer) Preconditions.checkNotNull(this.zad)).intValue(), z8);
        } catch (RemoteException unused) {
            Log.w("SignInClientImpl", "Remote service probably died when saveDefaultAccount is called");
        }
    }

    @Override // com.google.android.gms.signin.zad
    public final void zab() {
        connect(new BaseGmsClient.LegacyClientCallbackAdapter());
    }

    @Override // com.google.android.gms.signin.zad
    public final void zaa() {
        try {
            ((zae) getService()).zaa(((Integer) Preconditions.checkNotNull(this.zad)).intValue());
        } catch (RemoteException unused) {
            Log.w("SignInClientImpl", "Remote service probably died when clearAccountFromSessionStore is called");
        }
    }

    public SignInClientImpl(@RecentlyNonNull Context context, @RecentlyNonNull Looper looper, @RecentlyNonNull boolean z8, @RecentlyNonNull ClientSettings clientSettings, @RecentlyNonNull SignInOptions signInOptions, @RecentlyNonNull GoogleApiClient.ConnectionCallbacks connectionCallbacks, @RecentlyNonNull GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this(context, looper, true, clientSettings, createBundleFromClientSettings(clientSettings), connectionCallbacks, onConnectionFailedListener);
    }

    @Override // com.google.android.gms.signin.zad
    public final void zaa(zac zacVar) {
        Preconditions.checkNotNull(zacVar, "Expecting a valid ISignInCallbacks");
        try {
            Account accountOrDefault = this.zab.getAccountOrDefault();
            ((zae) getService()).zaa(new zak(new zar(accountOrDefault, ((Integer) Preconditions.checkNotNull(this.zad)).intValue(), "<<default account>>".equals(accountOrDefault.name) ? Storage.getInstance(getContext()).getSavedDefaultGoogleSignInAccount() : null)), zacVar);
        } catch (RemoteException e9) {
            Log.w("SignInClientImpl", "Remote service probably died when signIn is called");
            try {
                zacVar.zaa(new zam(8));
            } catch (RemoteException unused) {
                Log.wtf("SignInClientImpl", "ISignInCallbacks#onSignInComplete should be executed from the same process, unexpected RemoteException.", e9);
            }
        }
    }
}
