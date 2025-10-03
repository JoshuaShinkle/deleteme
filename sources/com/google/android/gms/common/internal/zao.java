package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.Response;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;

/* loaded from: classes2.dex */
final class zao implements PendingResultUtil.ResultConverter {
    private final /* synthetic */ Response zaa;

    public zao(Response response) {
        this.zaa = response;
    }

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final /* synthetic */ Object convert(Result result) {
        this.zaa.setResult(result);
        return this.zaa;
    }
}
