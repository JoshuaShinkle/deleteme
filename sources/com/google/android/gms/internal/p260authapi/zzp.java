package com.google.android.gms.internal.p260authapi;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;

/* loaded from: classes2.dex */
final class zzp extends zzh {
    private BaseImplementation.ResultHolder<Status> zzaq;

    public zzp(BaseImplementation.ResultHolder<Status> resultHolder) {
        this.zzaq = resultHolder;
    }

    @Override // com.google.android.gms.internal.p260authapi.zzh, com.google.android.gms.internal.p260authapi.zzv
    public final void zzd(Status status) {
        this.zzaq.setResult(status);
    }
}
