package com.google.firebase.remoteconfig.internal;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import java.util.Date;

/* loaded from: classes2.dex */
final /* synthetic */ class ConfigFetchHandler$$Lambda$3 implements Continuation {
    private final ConfigFetchHandler arg$1;
    private final Date arg$2;

    private ConfigFetchHandler$$Lambda$3(ConfigFetchHandler configFetchHandler, Date date) {
        this.arg$1 = configFetchHandler;
        this.arg$2 = date;
    }

    public static Continuation lambdaFactory$(ConfigFetchHandler configFetchHandler, Date date) {
        return new ConfigFetchHandler$$Lambda$3(configFetchHandler, date);
    }

    @Override // com.google.android.gms.tasks.Continuation
    public Object then(Task task) {
        return ConfigFetchHandler.lambda$fetchIfCacheExpiredAndNotThrottled$2(this.arg$1, this.arg$2, task);
    }
}
