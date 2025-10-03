package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.internal.GoogleApiManager;

/* loaded from: classes2.dex */
final class zabe implements Runnable {
    private final /* synthetic */ GoogleApiManager.zaa zaa;

    public zabe(GoogleApiManager.zaa zaaVar) {
        this.zaa = zaaVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zaa.zam();
    }
}
