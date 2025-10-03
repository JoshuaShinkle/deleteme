package com.google.android.gms.common.api.internal;

/* loaded from: classes2.dex */
final class zat implements Runnable {
    private final /* synthetic */ zaq zaa;

    public zat(zaq zaqVar) {
        this.zaa = zaqVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zaa.zam.lock();
        try {
            this.zaa.zah();
        } finally {
            this.zaa.zam.unlock();
        }
    }
}
