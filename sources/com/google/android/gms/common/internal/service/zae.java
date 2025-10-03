package com.google.android.gms.common.internal.service;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;

/* loaded from: classes2.dex */
final class zae extends zaa {
    private final BaseImplementation.ResultHolder<Status> zaa;

    public zae(BaseImplementation.ResultHolder<Status> resultHolder) {
        this.zaa = resultHolder;
    }

    @Override // com.google.android.gms.common.internal.service.zaa, com.google.android.gms.common.internal.service.zai
    public final void zaa(int i9) {
        this.zaa.setResult(new Status(i9));
    }
}
