package com.google.android.gms.internal.appinvite;

import android.app.Activity;
import android.content.Intent;
import com.google.android.gms.appinvite.AppInviteInvitationResult;
import com.google.android.gms.appinvite.AppInviteReferral;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import java.lang.ref.WeakReference;

/* loaded from: classes2.dex */
final class zzk extends zzh<AppInviteInvitationResult> {
    private final WeakReference<Activity> zzl;
    private final boolean zzm;
    private final Intent zzn;

    public zzk(zzf zzfVar, GoogleApiClient googleApiClient, Activity activity, boolean z8) {
        super(googleApiClient);
        this.zzm = z8;
        this.zzl = new WeakReference<>(activity);
        this.zzn = activity != null ? activity.getIntent() : null;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ Result createFailedResult(Status status) {
        return new zzp(status, new Intent());
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) {
        zzm zzmVar = (zzm) anyClient;
        if (!AppInviteReferral.hasReferral(this.zzn)) {
            zzmVar.zza(new zzn(this));
        } else {
            setResult((zzk) new zzp(Status.RESULT_SUCCESS, this.zzn));
            zzmVar.zza((zzo) null);
        }
    }
}
