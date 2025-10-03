package com.google.android.gms.common.api.internal;

import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ApiExceptionUtil;

@KeepForSdk
/* loaded from: classes2.dex */
public class ApiExceptionMapper implements StatusExceptionMapper {
    @Override // com.google.android.gms.common.api.internal.StatusExceptionMapper
    @RecentlyNonNull
    public Exception getException(@RecentlyNonNull Status status) {
        return ApiExceptionUtil.fromStatus(status);
    }
}
