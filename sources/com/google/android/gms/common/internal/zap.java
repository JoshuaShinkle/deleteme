package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
final class zap implements PendingResult.StatusListener {
    private final /* synthetic */ PendingResult zaa;
    private final /* synthetic */ TaskCompletionSource zab;
    private final /* synthetic */ PendingResultUtil.ResultConverter zac;
    private final /* synthetic */ PendingResultUtil.zaa zad;

    public zap(PendingResult pendingResult, TaskCompletionSource taskCompletionSource, PendingResultUtil.ResultConverter resultConverter, PendingResultUtil.zaa zaaVar) {
        this.zaa = pendingResult;
        this.zab = taskCompletionSource;
        this.zac = resultConverter;
        this.zad = zaaVar;
    }

    @Override // com.google.android.gms.common.api.PendingResult.StatusListener
    public final void onComplete(Status status) {
        if (!status.isSuccess()) {
            this.zab.setException(this.zad.zaa(status));
        } else {
            this.zab.setResult(this.zac.convert(this.zaa.await(0L, TimeUnit.MILLISECONDS)));
        }
    }
}
