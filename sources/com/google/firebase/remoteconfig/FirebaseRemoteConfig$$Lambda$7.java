package com.google.firebase.remoteconfig;

import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.internal.ConfigFetchHandler;

/* loaded from: classes2.dex */
final /* synthetic */ class FirebaseRemoteConfig$$Lambda$7 implements SuccessContinuation {
    private static final FirebaseRemoteConfig$$Lambda$7 instance = new FirebaseRemoteConfig$$Lambda$7();

    private FirebaseRemoteConfig$$Lambda$7() {
    }

    @Override // com.google.android.gms.tasks.SuccessContinuation
    public Task then(Object obj) {
        return FirebaseRemoteConfig.lambda$fetch$5((ConfigFetchHandler.FetchResponse) obj);
    }
}
