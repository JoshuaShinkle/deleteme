package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes2.dex */
final class zacg extends TaskApiCall {
    private final /* synthetic */ TaskApiCall.Builder zaa;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zacg(TaskApiCall.Builder builder, Feature[] featureArr, boolean z8, int i9) {
        super(featureArr, z8, i9);
        this.zaa = builder;
    }

    @Override // com.google.android.gms.common.api.internal.TaskApiCall
    public final void doExecute(Api.AnyClient anyClient, TaskCompletionSource taskCompletionSource) {
        this.zaa.zaa.accept(anyClient, taskCompletionSource);
    }
}
