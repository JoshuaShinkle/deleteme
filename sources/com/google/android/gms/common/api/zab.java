package com.google.android.gms.common.api;

import com.google.android.gms.common.api.PendingResult;

/* loaded from: classes2.dex */
final class zab implements PendingResult.StatusListener {
    private final /* synthetic */ Batch zaa;

    public zab(Batch batch) {
        this.zaa = batch;
    }

    @Override // com.google.android.gms.common.api.PendingResult.StatusListener
    public final void onComplete(Status status) {
        synchronized (this.zaa.zaf) {
            if (this.zaa.isCanceled()) {
                return;
            }
            if (status.isCanceled()) {
                Batch.zaa(this.zaa, true);
            } else if (!status.isSuccess()) {
                Batch.zab(this.zaa, true);
            }
            Batch.zab(this.zaa);
            if (this.zaa.zab == 0) {
                if (this.zaa.zad) {
                    super/*com.google.android.gms.common.api.internal.BasePendingResult*/.cancel();
                } else {
                    Status status2 = this.zaa.zac ? new Status(13) : Status.RESULT_SUCCESS;
                    Batch batch = this.zaa;
                    batch.setResult(new BatchResult(status2, batch.zae));
                }
            }
        }
    }
}
