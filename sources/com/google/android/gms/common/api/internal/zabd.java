package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.internal.GoogleApiManager;

/* loaded from: classes2.dex */
final class zabd implements Runnable {
    private final /* synthetic */ int zaa;
    private final /* synthetic */ GoogleApiManager.zaa zab;

    public zabd(GoogleApiManager.zaa zaaVar, int i9) {
        this.zab = zaaVar;
        this.zaa = i9;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zab.zaa(this.zaa);
    }
}
