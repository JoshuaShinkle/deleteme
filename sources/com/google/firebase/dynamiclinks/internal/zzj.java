package com.google.firebase.dynamiclinks.internal;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.dynamiclinks.ShortDynamicLink;

/* loaded from: classes2.dex */
final class zzj extends TaskApiCall<zzd, ShortDynamicLink> {
    private final Bundle zze;

    public zzj(Bundle bundle) {
        this.zze = bundle;
    }

    @Override // com.google.android.gms.common.api.internal.TaskApiCall
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient, TaskCompletionSource<ShortDynamicLink> taskCompletionSource) {
        zzd zzdVar = (zzd) anyClient;
        try {
            ((zzm) zzdVar.getService()).zza(new zzg(taskCompletionSource), this.zze);
        } catch (RemoteException unused) {
        }
    }
}
