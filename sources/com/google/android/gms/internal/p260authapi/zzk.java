package com.google.android.gms.internal.p260authapi;

import android.content.Context;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

/* loaded from: classes2.dex */
final class zzk extends zzo<Status> {
    private final /* synthetic */ Credential zzao;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzk(zzj zzjVar, GoogleApiClient googleApiClient, Credential credential) {
        super(googleApiClient);
        this.zzao = credential;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ Result createFailedResult(Status status) {
        return status;
    }

    @Override // com.google.android.gms.internal.p260authapi.zzo
    public final void zzc(Context context, zzx zzxVar) {
        zzxVar.zzc(new zzp(this), new zzz(this.zzao));
    }
}
