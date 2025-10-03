package com.google.android.gms.internal.p260authapi;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.IStatusCallback;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes2.dex */
final class zzai extends IStatusCallback.Stub {
    private final /* synthetic */ TaskCompletionSource zzbn;

    public zzai(zzaf zzafVar, TaskCompletionSource taskCompletionSource) {
        this.zzbn = taskCompletionSource;
    }

    @Override // com.google.android.gms.common.api.internal.IStatusCallback
    public final void onResult(Status status) {
        TaskUtil.setResultOrApiException(status, this.zzbn);
    }
}
