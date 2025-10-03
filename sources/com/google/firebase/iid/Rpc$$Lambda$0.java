package com.google.firebase.iid;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* loaded from: classes2.dex */
final /* synthetic */ class Rpc$$Lambda$0 implements Continuation {
    static final Continuation $instance = new Rpc$$Lambda$0();

    private Rpc$$Lambda$0() {
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final Object then(Task task) {
        return Rpc.lambda$registerRpc$0$Rpc(task);
    }
}
