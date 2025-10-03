package com.google.android.gms.internal.auth;

import android.accounts.Account;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;

/* loaded from: classes2.dex */
final class zzl extends BaseImplementation.ApiMethodImpl<Result, zzr> {
    private final /* synthetic */ Account zzo;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzl(zzh zzhVar, Api api, GoogleApiClient googleApiClient, Account account) {
        super((Api<?>) api, googleApiClient);
        this.zzo = account;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final Result createFailedResult(Status status) {
        return new zzq(status);
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) {
        ((com.google.android.gms.auth.account.zzc) ((zzr) anyClient).getService()).zza(new zzm(this), this.zzo);
    }
}
