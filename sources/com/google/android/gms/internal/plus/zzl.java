package com.google.android.gms.internal.plus;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

/* loaded from: classes2.dex */
final class zzl extends zzp {
    private final /* synthetic */ String zzak;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzl(zzj zzjVar, GoogleApiClient googleApiClient, String str) {
        super(googleApiClient, null);
        this.zzak = str;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) {
        setCancelToken(((com.google.android.gms.plus.internal.zzh) anyClient).zza(this, 0, this.zzak));
    }
}
