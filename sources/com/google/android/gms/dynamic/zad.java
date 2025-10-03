package com.google.android.gms.dynamic;

import android.os.Bundle;
import com.google.android.gms.dynamic.DeferredLifecycleHelper;

/* loaded from: classes2.dex */
final class zad implements DeferredLifecycleHelper.zaa {
    private final /* synthetic */ Bundle zaa;
    private final /* synthetic */ DeferredLifecycleHelper zab;

    public zad(DeferredLifecycleHelper deferredLifecycleHelper, Bundle bundle) {
        this.zab = deferredLifecycleHelper;
        this.zaa = bundle;
    }

    @Override // com.google.android.gms.dynamic.DeferredLifecycleHelper.zaa
    public final int zaa() {
        return 1;
    }

    @Override // com.google.android.gms.dynamic.DeferredLifecycleHelper.zaa
    public final void zaa(LifecycleDelegate lifecycleDelegate) {
        this.zab.zaa.onCreate(this.zaa);
    }
}
