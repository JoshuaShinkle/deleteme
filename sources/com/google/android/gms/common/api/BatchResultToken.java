package com.google.android.gms.common.api;

import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.api.Result;

/* loaded from: classes2.dex */
public final class BatchResultToken<R extends Result> {

    @RecentlyNonNull
    protected final int mId;

    public BatchResultToken(int i9) {
        this.mId = i9;
    }
}
