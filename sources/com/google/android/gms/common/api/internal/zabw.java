package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.RegistrationMethods;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes2.dex */
final class zabw extends RegisterListenerMethod {
    private final /* synthetic */ RegistrationMethods.Builder zaa;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zabw(RegistrationMethods.Builder builder, ListenerHolder listenerHolder, Feature[] featureArr, boolean z8) {
        super(listenerHolder, featureArr, z8);
        this.zaa = builder;
    }

    @Override // com.google.android.gms.common.api.internal.RegisterListenerMethod
    public final void registerListener(Api.AnyClient anyClient, TaskCompletionSource taskCompletionSource) {
        this.zaa.zaa.accept(anyClient, taskCompletionSource);
    }
}
