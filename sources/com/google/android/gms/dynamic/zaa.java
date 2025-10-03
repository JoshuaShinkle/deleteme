package com.google.android.gms.dynamic;

import android.app.Activity;
import android.os.Bundle;
import com.google.android.gms.dynamic.DeferredLifecycleHelper;

/* loaded from: classes2.dex */
final class zaa implements DeferredLifecycleHelper.zaa {
    private final /* synthetic */ Activity zaa;
    private final /* synthetic */ Bundle zab;
    private final /* synthetic */ Bundle zac;
    private final /* synthetic */ DeferredLifecycleHelper zad;

    public zaa(DeferredLifecycleHelper deferredLifecycleHelper, Activity activity, Bundle bundle, Bundle bundle2) {
        this.zad = deferredLifecycleHelper;
        this.zaa = activity;
        this.zab = bundle;
        this.zac = bundle2;
    }

    @Override // com.google.android.gms.dynamic.DeferredLifecycleHelper.zaa
    public final int zaa() {
        return 0;
    }

    @Override // com.google.android.gms.dynamic.DeferredLifecycleHelper.zaa
    public final void zaa(LifecycleDelegate lifecycleDelegate) {
        this.zad.zaa.onInflate(this.zaa, this.zab, this.zac);
    }
}
