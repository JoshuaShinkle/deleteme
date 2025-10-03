package com.google.android.gms.common.api;

import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.api.internal.BasePendingResult;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public final class Batch extends BasePendingResult<BatchResult> {
    private int zab;
    private boolean zac;
    private boolean zad;
    private final PendingResult<?>[] zae;
    private final Object zaf;

    public static final class Builder {
        private List<PendingResult<?>> zaa = new ArrayList();
        private GoogleApiClient zab;

        public Builder(@RecentlyNonNull GoogleApiClient googleApiClient) {
            this.zab = googleApiClient;
        }

        @RecentlyNonNull
        public final <R extends Result> BatchResultToken<R> add(@RecentlyNonNull PendingResult<R> pendingResult) {
            BatchResultToken<R> batchResultToken = new BatchResultToken<>(this.zaa.size());
            this.zaa.add(pendingResult);
            return batchResultToken;
        }

        @RecentlyNonNull
        public final Batch build() {
            return new Batch(this.zaa, this.zab, null);
        }
    }

    private Batch(List<PendingResult<?>> list, GoogleApiClient googleApiClient) {
        super(googleApiClient);
        this.zaf = new Object();
        int size = list.size();
        this.zab = size;
        PendingResult<?>[] pendingResultArr = new PendingResult[size];
        this.zae = pendingResultArr;
        if (list.isEmpty()) {
            setResult(new BatchResult(Status.RESULT_SUCCESS, pendingResultArr));
            return;
        }
        for (int i9 = 0; i9 < list.size(); i9++) {
            PendingResult<?> pendingResult = list.get(i9);
            this.zae[i9] = pendingResult;
            pendingResult.addStatusListener(new zab(this));
        }
    }

    public static /* synthetic */ boolean zab(Batch batch, boolean z8) {
        batch.zac = true;
        return true;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult, com.google.android.gms.common.api.PendingResult
    public final void cancel() {
        super.cancel();
        for (PendingResult<?> pendingResult : this.zae) {
            pendingResult.cancel();
        }
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    @RecentlyNonNull
    public final BatchResult createFailedResult(@RecentlyNonNull Status status) {
        return new BatchResult(status, this.zae);
    }

    public static /* synthetic */ boolean zaa(Batch batch, boolean z8) {
        batch.zad = true;
        return true;
    }

    public static /* synthetic */ int zab(Batch batch) {
        int i9 = batch.zab;
        batch.zab = i9 - 1;
        return i9;
    }

    public /* synthetic */ Batch(List list, GoogleApiClient googleApiClient, zab zabVar) {
        this(list, googleApiClient);
    }
}
