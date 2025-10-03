package com.google.android.gms.common.internal;

import android.content.Intent;
import com.google.android.gms.common.api.internal.LifecycleFragment;

/* loaded from: classes2.dex */
final class zaf extends zab {
    private final /* synthetic */ Intent zaa;
    private final /* synthetic */ LifecycleFragment zab;
    private final /* synthetic */ int zac = 2;

    public zaf(Intent intent, LifecycleFragment lifecycleFragment, int i9) {
        this.zaa = intent;
        this.zab = lifecycleFragment;
    }

    @Override // com.google.android.gms.common.internal.zab
    public final void zaa() {
        Intent intent = this.zaa;
        if (intent != null) {
            this.zab.startActivityForResult(intent, this.zac);
        }
    }
}
