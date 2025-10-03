package com.google.android.gms.common.api;

import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.zabz;

/* loaded from: classes2.dex */
public abstract class ResultTransform<R extends Result, S extends Result> {
    public final PendingResult<S> createFailedResult(@RecentlyNonNull Status status) {
        return new zabz(status);
    }

    public Status onFailure(@RecentlyNonNull Status status) {
        return status;
    }

    @RecentlyNullable
    public abstract PendingResult<S> onSuccess(@RecentlyNonNull R r8);
}
