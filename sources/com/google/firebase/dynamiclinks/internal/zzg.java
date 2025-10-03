package com.google.firebase.dynamiclinks.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.dynamiclinks.ShortDynamicLink;

/* loaded from: classes2.dex */
final class zzg extends zzh {
    private final TaskCompletionSource<ShortDynamicLink> zzt;

    public zzg(TaskCompletionSource<ShortDynamicLink> taskCompletionSource) {
        this.zzt = taskCompletionSource;
    }

    @Override // com.google.firebase.dynamiclinks.internal.zzh, com.google.firebase.dynamiclinks.internal.zzk
    public final void zza(Status status, zzo zzoVar) {
        TaskUtil.setResultOrApiException(status, zzoVar, this.zzt);
    }
}
