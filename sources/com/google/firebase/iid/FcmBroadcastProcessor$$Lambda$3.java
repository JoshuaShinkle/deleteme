package com.google.firebase.iid;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* loaded from: classes2.dex */
final /* synthetic */ class FcmBroadcastProcessor$$Lambda$3 implements Continuation {
    static final Continuation $instance = new FcmBroadcastProcessor$$Lambda$3();

    private FcmBroadcastProcessor$$Lambda$3() {
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final Object then(Task task) {
        return FcmBroadcastProcessor.lambda$startMessagingService$1$FcmBroadcastProcessor(task);
    }
}
