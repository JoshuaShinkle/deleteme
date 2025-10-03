package com.google.android.gms.internal.p260authapi;

import com.google.android.gms.auth.api.identity.BeginSignInResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes2.dex */
final class zzaj extends zzaa {
    private final /* synthetic */ TaskCompletionSource zzbn;

    public zzaj(zzaf zzafVar, TaskCompletionSource taskCompletionSource) {
        this.zzbn = taskCompletionSource;
    }

    @Override // com.google.android.gms.internal.p260authapi.zzab
    public final void zzc(Status status, BeginSignInResult beginSignInResult) {
        TaskUtil.setResultOrApiException(status, beginSignInResult, this.zzbn);
    }
}
