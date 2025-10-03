package com.google.android.gms.common.internal;

import android.app.Activity;
import android.content.Intent;

/* loaded from: classes2.dex */
final class zae extends zab {
    private final /* synthetic */ Intent zaa;
    private final /* synthetic */ Activity zab;
    private final /* synthetic */ int zac;

    public zae(Intent intent, Activity activity, int i9) {
        this.zaa = intent;
        this.zab = activity;
        this.zac = i9;
    }

    @Override // com.google.android.gms.common.internal.zab
    public final void zaa() {
        Intent intent = this.zaa;
        if (intent != null) {
            this.zab.startActivityForResult(intent, this.zac);
        }
    }
}
