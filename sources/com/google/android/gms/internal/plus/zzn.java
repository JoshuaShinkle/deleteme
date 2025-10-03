package com.google.android.gms.internal.plus;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.Collection;

/* loaded from: classes2.dex */
final class zzn extends zzp {
    private final /* synthetic */ Collection zzal;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzn(zzj zzjVar, GoogleApiClient googleApiClient, Collection collection) {
        super(googleApiClient, null);
        this.zzal = collection;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) {
        ((com.google.android.gms.plus.internal.zzh) anyClient).zza(this, this.zzal);
    }
}
