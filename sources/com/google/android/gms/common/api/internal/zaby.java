package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RegistrationMethods;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes2.dex */
final class zaby extends UnregisterListenerMethod {
    private final /* synthetic */ RegistrationMethods.Builder zaa;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zaby(RegistrationMethods.Builder builder, ListenerHolder.ListenerKey listenerKey) {
        super(listenerKey);
        this.zaa = builder;
    }

    @Override // com.google.android.gms.common.api.internal.UnregisterListenerMethod
    public final void unregisterListener(Api.AnyClient anyClient, TaskCompletionSource taskCompletionSource) {
        this.zaa.zab.accept(anyClient, taskCompletionSource);
    }
}
