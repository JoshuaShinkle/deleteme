package com.google.android.gms.common.api.internal;

import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.tasks.TaskCompletionSource;

@KeepForSdk
/* loaded from: classes2.dex */
public abstract class UnregisterListenerMethod<A extends Api.AnyClient, L> {
    private final ListenerHolder.ListenerKey<L> zaa;

    @KeepForSdk
    public UnregisterListenerMethod(@RecentlyNonNull ListenerHolder.ListenerKey<L> listenerKey) {
        this.zaa = listenerKey;
    }

    @RecentlyNonNull
    @KeepForSdk
    public ListenerHolder.ListenerKey<L> getListenerKey() {
        return this.zaa;
    }

    @KeepForSdk
    public abstract void unregisterListener(@RecentlyNonNull A a9, @RecentlyNonNull TaskCompletionSource<Boolean> taskCompletionSource);
}
