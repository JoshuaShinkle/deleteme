package com.google.android.gms.internal.p261authapiphone;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes2.dex */
abstract class zzm extends TaskApiCall<zzi, Void> {
    private TaskCompletionSource<Void> zzf;

    private zzm() {
    }

    public /* synthetic */ zzm(zzk zzkVar) {
        this();
    }

    @Override // com.google.android.gms.common.api.internal.TaskApiCall
    public /* synthetic */ void doExecute(Api.AnyClient anyClient, TaskCompletionSource<Void> taskCompletionSource) {
        this.zzf = taskCompletionSource;
        zza((zze) ((zzi) anyClient).getService());
    }

    public abstract void zza(zze zzeVar);

    public final void zzb(Status status) {
        TaskUtil.setResultOrApiException(status, this.zzf);
    }
}
