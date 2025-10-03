package com.google.firebase.remoteconfig;

import com.google.firebase.remoteconfig.internal.LegacyConfigsHandler;
import java.util.concurrent.Callable;

/* loaded from: classes2.dex */
final /* synthetic */ class RemoteConfigComponent$$Lambda$4 implements Callable {
    private final LegacyConfigsHandler arg$1;

    private RemoteConfigComponent$$Lambda$4(LegacyConfigsHandler legacyConfigsHandler) {
        this.arg$1 = legacyConfigsHandler;
    }

    public static Callable lambdaFactory$(LegacyConfigsHandler legacyConfigsHandler) {
        return new RemoteConfigComponent$$Lambda$4(legacyConfigsHandler);
    }

    @Override // java.util.concurrent.Callable
    public Object call() {
        return Boolean.valueOf(this.arg$1.saveLegacyConfigsIfNecessary());
    }
}
