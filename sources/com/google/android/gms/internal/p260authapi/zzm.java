package com.google.android.gms.internal.p260authapi;

import android.content.Context;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

/* loaded from: classes2.dex */
final class zzm extends zzo<Status> {
    public zzm(zzj zzjVar, GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ Result createFailedResult(Status status) {
        return status;
    }

    @Override // com.google.android.gms.internal.p260authapi.zzo
    public final void zzc(Context context, zzx zzxVar) {
        zzxVar.zzc(new zzp(this));
    }
}
