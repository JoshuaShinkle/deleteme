package com.google.firebase.remoteconfig.internal;

import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.internal.ConfigFetchHandler;

/* loaded from: classes2.dex */
final /* synthetic */ class ConfigFetchHandler$$Lambda$4 implements SuccessContinuation {
    private final ConfigFetchHandler.FetchResponse arg$1;

    private ConfigFetchHandler$$Lambda$4(ConfigFetchHandler.FetchResponse fetchResponse) {
        this.arg$1 = fetchResponse;
    }

    public static SuccessContinuation lambdaFactory$(ConfigFetchHandler.FetchResponse fetchResponse) {
        return new ConfigFetchHandler$$Lambda$4(fetchResponse);
    }

    @Override // com.google.android.gms.tasks.SuccessContinuation
    public Task then(Object obj) {
        return ConfigFetchHandler.lambda$fetchFromBackendAndCacheResponse$3(this.arg$1, (ConfigContainer) obj);
    }
}
