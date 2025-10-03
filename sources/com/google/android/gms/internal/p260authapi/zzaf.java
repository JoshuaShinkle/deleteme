package com.google.android.gms.internal.p260authapi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.BeginSignInResult;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.auth.api.identity.SignInOptions;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Iterator;

/* loaded from: classes2.dex */
public final class zzaf extends GoogleApi<SignInOptions> implements SignInClient {
    private static final Api<SignInOptions> API;
    private static final Api.ClientKey<zzak> CLIENT_KEY;
    private static final Api.AbstractClientBuilder<zzak, SignInOptions> zzbm;

    static {
        Api.ClientKey<zzak> clientKey = new Api.ClientKey<>();
        CLIENT_KEY = clientKey;
        zzag zzagVar = new zzag();
        zzbm = zzagVar;
        API = new Api<>("Auth.Api.Identity.SignIn.API", zzagVar, clientKey);
    }

    public zzaf(Context context, SignInOptions signInOptions) {
        super(context, API, SignInOptions.Builder.zzc(signInOptions).zze(zzal.zzs()).build(), GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    @Override // com.google.android.gms.auth.api.identity.SignInClient
    public final Task<BeginSignInResult> beginSignIn(BeginSignInRequest beginSignInRequest) {
        final BeginSignInRequest beginSignInRequestBuild = BeginSignInRequest.zzc(beginSignInRequest).zzd(getApiOptions().zzg()).build();
        return doRead(TaskApiCall.builder().setFeatures(zzam.zzcz).run(new RemoteCall(this, beginSignInRequestBuild) { // from class: com.google.android.gms.internal.auth-api.zzae
            private final zzaf zzbk;
            private final BeginSignInRequest zzbl;

            {
                this.zzbk = this;
                this.zzbl = beginSignInRequestBuild;
            }

            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                zzaf zzafVar = this.zzbk;
                BeginSignInRequest beginSignInRequest2 = this.zzbl;
                ((zzad) ((zzak) obj).getService()).zzc(new zzaj(zzafVar, (TaskCompletionSource) obj2), (BeginSignInRequest) Preconditions.checkNotNull(beginSignInRequest2));
            }
        }).setAutoResolveMissingFeatures(false).build());
    }

    @Override // com.google.android.gms.auth.api.identity.SignInClient
    public final SignInCredential getSignInCredentialFromIntent(Intent intent) throws ApiException {
        if (intent == null) {
            throw new ApiException(Status.RESULT_INTERNAL_ERROR);
        }
        Status status = (Status) SafeParcelableSerializer.deserializeFromIntentExtra(intent, "status", Status.CREATOR);
        if (status == null) {
            throw new ApiException(Status.RESULT_CANCELED);
        }
        if (!status.isSuccess()) {
            throw new ApiException(status);
        }
        SignInCredential signInCredential = (SignInCredential) SafeParcelableSerializer.deserializeFromIntentExtra(intent, "sign_in_credential", SignInCredential.CREATOR);
        if (signInCredential != null) {
            return signInCredential;
        }
        throw new ApiException(Status.RESULT_INTERNAL_ERROR);
    }

    @Override // com.google.android.gms.auth.api.identity.SignInClient
    public final Task<Void> signOut() {
        getApplicationContext().getSharedPreferences("com.google.android.gms.signin", 0).edit().clear().apply();
        Iterator<GoogleApiClient> it = GoogleApiClient.getAllClients().iterator();
        while (it.hasNext()) {
            it.next().maybeSignOut();
        }
        GoogleApiManager.reportSignOut();
        return doRead(TaskApiCall.builder().setFeatures(zzam.zzda).run(new RemoteCall(this) { // from class: com.google.android.gms.internal.auth-api.zzah
            private final zzaf zzbk;

            {
                this.zzbk = this;
            }

            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                zzaf zzafVar = this.zzbk;
                ((zzad) ((zzak) obj).getService()).zzc(new zzai(zzafVar, (TaskCompletionSource) obj2), zzafVar.getApiOptions().zzg());
            }
        }).setAutoResolveMissingFeatures(false).build());
    }

    public zzaf(Activity activity, SignInOptions signInOptions) {
        super(activity, API, SignInOptions.Builder.zzc(signInOptions).zze(zzal.zzs()).build(), GoogleApi.Settings.DEFAULT_SETTINGS);
    }
}
