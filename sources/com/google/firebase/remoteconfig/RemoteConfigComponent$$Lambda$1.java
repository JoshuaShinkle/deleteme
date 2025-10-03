package com.google.firebase.remoteconfig;

import java.util.concurrent.Callable;

/* loaded from: classes2.dex */
final /* synthetic */ class RemoteConfigComponent$$Lambda$1 implements Callable {
    private final RemoteConfigComponent arg$1;

    private RemoteConfigComponent$$Lambda$1(RemoteConfigComponent remoteConfigComponent) {
        this.arg$1 = remoteConfigComponent;
    }

    public static Callable lambdaFactory$(RemoteConfigComponent remoteConfigComponent) {
        return new RemoteConfigComponent$$Lambda$1(remoteConfigComponent);
    }

    @Override // java.util.concurrent.Callable
    public Object call() {
        return this.arg$1.getDefault();
    }
}
