package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.PendingResultUtil;

/* loaded from: classes2.dex */
final class zan implements PendingResultUtil.zaa {
    @Override // com.google.android.gms.common.internal.PendingResultUtil.zaa
    public final ApiException zaa(Status status) {
        return ApiExceptionUtil.fromStatus(status);
    }
}
