package com.google.android.gms.dynamic;

import android.os.Bundle;
import com.google.android.gms.dynamic.DeferredLifecycleHelper;
import java.util.Iterator;

/* loaded from: classes2.dex */
final class zab implements OnDelegateCreatedListener {
    private final /* synthetic */ DeferredLifecycleHelper zaa;

    public zab(DeferredLifecycleHelper deferredLifecycleHelper) {
        this.zaa = deferredLifecycleHelper;
    }

    @Override // com.google.android.gms.dynamic.OnDelegateCreatedListener
    public final void onDelegateCreated(LifecycleDelegate lifecycleDelegate) {
        this.zaa.zaa = lifecycleDelegate;
        Iterator it = this.zaa.zac.iterator();
        while (it.hasNext()) {
            ((DeferredLifecycleHelper.zaa) it.next()).zaa(this.zaa.zaa);
        }
        this.zaa.zac.clear();
        DeferredLifecycleHelper.zaa(this.zaa, (Bundle) null);
    }
}
