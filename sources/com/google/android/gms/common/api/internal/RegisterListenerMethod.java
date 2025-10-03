package com.google.android.gms.common.api.internal;

import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.tasks.TaskCompletionSource;

@KeepForSdk
/* loaded from: classes2.dex */
public abstract class RegisterListenerMethod<A extends Api.AnyClient, L> {
    private final ListenerHolder<L> zaa;
    private final Feature[] zab;
    private final boolean zac;

    @KeepForSdk
    public RegisterListenerMethod(@RecentlyNonNull ListenerHolder<L> listenerHolder) {
        this.zaa = listenerHolder;
        this.zab = null;
        this.zac = false;
    }

    @KeepForSdk
    public void clearListener() {
        this.zaa.clear();
    }

    @RecentlyNullable
    @KeepForSdk
    public ListenerHolder.ListenerKey<L> getListenerKey() {
        return this.zaa.getListenerKey();
    }

    @RecentlyNullable
    @KeepForSdk
    public Feature[] getRequiredFeatures() {
        return this.zab;
    }

    @KeepForSdk
    public abstract void registerListener(@RecentlyNonNull A a9, @RecentlyNonNull TaskCompletionSource<Void> taskCompletionSource);

    @RecentlyNonNull
    public final boolean zaa() {
        return this.zac;
    }

    @KeepForSdk
    public RegisterListenerMethod(@RecentlyNonNull ListenerHolder<L> listenerHolder, @RecentlyNonNull Feature[] featureArr, @RecentlyNonNull boolean z8) {
        this.zaa = listenerHolder;
        this.zab = featureArr;
        this.zac = z8;
    }
}
