package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

/* loaded from: classes2.dex */
final class zzj extends zzp<GoogleSignInResult> {
    final /* synthetic */ Context val$context;
    final /* synthetic */ GoogleSignInOptions zzci;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzj(GoogleApiClient googleApiClient, Context context, GoogleSignInOptions googleSignInOptions) {
        super(googleApiClient);
        this.val$context = context;
        this.zzci = googleSignInOptions;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ Result createFailedResult(Status status) {
        return new GoogleSignInResult(null, status);
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) {
        ((zzv) ((zzh) anyClient).getService()).zzc(new zzi(this), this.zzci);
    }
}
