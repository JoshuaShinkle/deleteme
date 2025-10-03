package com.google.android.gms.common.api.internal;

/* loaded from: classes2.dex */
abstract class zaan implements Runnable {
    private final /* synthetic */ zaad zaa;

    private zaan(zaad zaadVar) {
        this.zaa = zaadVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.zaa.zab.lock();
        try {
            if (Thread.interrupted()) {
                return;
            }
            zaa();
        } catch (RuntimeException e9) {
            this.zaa.zaa.zaa(e9);
        } finally {
            this.zaa.zab.unlock();
        }
    }

    public abstract void zaa();

    public /* synthetic */ zaan(zaad zaadVar, zaag zaagVar) {
        this(zaadVar);
    }
}
