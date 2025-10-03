package com.google.android.gms.internal.p260authapi;

import android.content.Context;
import com.google.android.gms.auth.api.credentials.CredentialRequest;
import com.google.android.gms.auth.api.credentials.CredentialRequestResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

/* loaded from: classes2.dex */
final class zzi extends zzo<CredentialRequestResult> {
    private final /* synthetic */ CredentialRequest zzan;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzi(zzj zzjVar, GoogleApiClient googleApiClient, CredentialRequest credentialRequest) {
        super(googleApiClient);
        this.zzan = credentialRequest;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ Result createFailedResult(Status status) {
        return zzg.zzc(status);
    }

    @Override // com.google.android.gms.internal.p260authapi.zzo
    public final void zzc(Context context, zzx zzxVar) {
        zzxVar.zzc(new zzl(this), this.zzan);
    }
}
