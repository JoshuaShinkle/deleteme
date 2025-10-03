package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;

/* loaded from: classes2.dex */
final class zacd implements Runnable {
    private final /* synthetic */ zacb zaa;

    public zacd(zacb zacbVar) {
        this.zaa = zacbVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zaa.zah.zaa(new ConnectionResult(4));
    }
}
