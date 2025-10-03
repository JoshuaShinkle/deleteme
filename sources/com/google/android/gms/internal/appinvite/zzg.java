package com.google.android.gms.internal.appinvite;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

/* loaded from: classes2.dex */
final class zzg extends zzh<Status> {
    private final String zzj;

    public zzg(zzf zzfVar, GoogleApiClient googleApiClient, String str) {
        super(googleApiClient);
        this.zzj = str;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ Result createFailedResult(Status status) {
        return status;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) {
        zzm zzmVar = (zzm) anyClient;
        try {
            ((zzq) zzmVar.getService()).zza(new zzj(this), this.zzj);
        } catch (RemoteException unused) {
        }
    }
}
