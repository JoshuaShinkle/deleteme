package com.google.firebase.remoteconfig.internal;

import java.util.concurrent.Callable;

/* loaded from: classes2.dex */
final /* synthetic */ class ConfigCacheClient$$Lambda$3 implements Callable {
    private final ConfigStorageClient arg$1;

    private ConfigCacheClient$$Lambda$3(ConfigStorageClient configStorageClient) {
        this.arg$1 = configStorageClient;
    }

    public static Callable lambdaFactory$(ConfigStorageClient configStorageClient) {
        return new ConfigCacheClient$$Lambda$3(configStorageClient);
    }

    @Override // java.util.concurrent.Callable
    public Object call() {
        return this.arg$1.read();
    }
}
