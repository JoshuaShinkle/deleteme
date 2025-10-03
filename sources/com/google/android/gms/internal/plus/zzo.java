package com.google.android.gms.internal.plus;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

/* loaded from: classes2.dex */
final class zzo extends zzp {
    private final /* synthetic */ String[] zzam;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzo(zzj zzjVar, GoogleApiClient googleApiClient, String[] strArr) {
        super(googleApiClient, null);
        this.zzam = strArr;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) {
        ((com.google.android.gms.plus.internal.zzh) anyClient).zza(this, this.zzam);
    }
}
