package com.google.android.gms.common.internal;

import android.content.Intent;
import androidx.fragment.app.Fragment;

/* loaded from: classes2.dex */
final class zad extends zab {
    private final /* synthetic */ Intent zaa;
    private final /* synthetic */ Fragment zab;
    private final /* synthetic */ int zac;

    public zad(Intent intent, Fragment fragment, int i9) {
        this.zaa = intent;
        this.zab = fragment;
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
