package com.google.firebase.iid;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* loaded from: classes2.dex */
final /* synthetic */ class FcmBroadcastProcessor$$Lambda$2 implements Continuation {
    static final Continuation $instance = new FcmBroadcastProcessor$$Lambda$2();

    private FcmBroadcastProcessor$$Lambda$2() {
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final Object then(Task task) {
        return FcmBroadcastProcessor.lambda$bindToMessagingService$3$FcmBroadcastProcessor(task);
    }
}
