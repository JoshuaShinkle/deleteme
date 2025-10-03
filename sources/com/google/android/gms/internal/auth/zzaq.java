package com.google.android.gms.internal.auth;

import android.content.Context;
import com.google.android.gms.auth.api.AuthProxy;
import com.google.android.gms.auth.api.proxy.ProxyApi;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;

/* loaded from: classes2.dex */
abstract class zzaq extends BaseImplementation.ApiMethodImpl<ProxyApi.SpatulaHeaderResult, zzak> {
    public zzaq(GoogleApiClient googleApiClient) {
        super(AuthProxy.API, googleApiClient);
    }

    public static ProxyApi.SpatulaHeaderResult zzc(Status status) {
        return new zzax(status);
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public /* synthetic */ Result createFailedResult(Status status) {
        return zzc(status);
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public /* synthetic */ void doExecute(Api.AnyClient anyClient) {
        zzak zzakVar = (zzak) anyClient;
        zza(zzakVar.getContext(), (zzan) zzakVar.getService());
    }

    public abstract void zza(Context context, zzan zzanVar);
}
