package com.google.android.gms.tagmanager;

import android.content.ComponentCallbacks2;
import android.content.res.Configuration;

/* loaded from: classes2.dex */
final class zzgc implements ComponentCallbacks2 {
    private final /* synthetic */ TagManager zzalh;

    public zzgc(TagManager tagManager) {
        this.zzalh = tagManager;
    }

    @Override // android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
    }

    @Override // android.content.ComponentCallbacks
    public final void onLowMemory() {
    }

    @Override // android.content.ComponentCallbacks2
    public final void onTrimMemory(int i9) {
        if (i9 == 20) {
            this.zzalh.dispatch();
        }
    }
}
