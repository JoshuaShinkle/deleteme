package com.google.firebase.remoteconfig;

import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.internal.ConfigContainer;

/* loaded from: classes2.dex */
final /* synthetic */ class FirebaseRemoteConfig$$Lambda$10 implements SuccessContinuation {
    private static final FirebaseRemoteConfig$$Lambda$10 instance = new FirebaseRemoteConfig$$Lambda$10();

    private FirebaseRemoteConfig$$Lambda$10() {
    }

    @Override // com.google.android.gms.tasks.SuccessContinuation
    public Task then(Object obj) {
        return FirebaseRemoteConfig.lambda$setDefaultsWithStringsMapAsync$8((ConfigContainer) obj);
    }
}
