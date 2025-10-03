package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

/* loaded from: classes2.dex */
final class zav implements PendingResult.StatusListener {
    private final /* synthetic */ BasePendingResult zaa;
    private final /* synthetic */ zaw zab;

    public zav(zaw zawVar, BasePendingResult basePendingResult) {
        this.zab = zawVar;
        this.zaa = basePendingResult;
    }

    @Override // com.google.android.gms.common.api.PendingResult.StatusListener
    public final void onComplete(Status status) {
        this.zab.zaa.remove(this.zaa);
    }
}
