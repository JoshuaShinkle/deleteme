package com.google.firebase.remoteconfig.internal;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* loaded from: classes2.dex */
final /* synthetic */ class ConfigFetchHandler$$Lambda$1 implements Continuation {
    private final ConfigFetchHandler arg$1;
    private final long arg$2;

    private ConfigFetchHandler$$Lambda$1(ConfigFetchHandler configFetchHandler, long j9) {
        this.arg$1 = configFetchHandler;
        this.arg$2 = j9;
    }

    public static Continuation lambdaFactory$(ConfigFetchHandler configFetchHandler, long j9) {
        return new ConfigFetchHandler$$Lambda$1(configFetchHandler, j9);
    }

    @Override // com.google.android.gms.tasks.Continuation
    public Object then(Task task) {
        return ConfigFetchHandler.lambda$fetch$0(this.arg$1, this.arg$2, task);
    }
}
