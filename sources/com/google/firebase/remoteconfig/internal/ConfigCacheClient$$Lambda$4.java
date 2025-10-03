package com.google.firebase.remoteconfig.internal;

import java.util.concurrent.Executor;

/* loaded from: classes2.dex */
final /* synthetic */ class ConfigCacheClient$$Lambda$4 implements Executor {
    private static final ConfigCacheClient$$Lambda$4 instance = new ConfigCacheClient$$Lambda$4();

    private ConfigCacheClient$$Lambda$4() {
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        runnable.run();
    }
}
