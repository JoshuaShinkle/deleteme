package com.google.android.gms.common.api.internal;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes2.dex */
final class zay implements OnCompleteListener {
    private final /* synthetic */ TaskCompletionSource zaa;
    private final /* synthetic */ zaw zab;

    public zay(zaw zawVar, TaskCompletionSource taskCompletionSource) {
        this.zab = zawVar;
        this.zaa = taskCompletionSource;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(Task task) {
        this.zab.zab.remove(this.zaa);
    }
}
