package com.google.android.gms.common.api.internal;

/* loaded from: classes2.dex */
abstract class zaba {
    private final zaay zaa;

    public zaba(zaay zaayVar) {
        this.zaa = zaayVar;
    }

    public abstract void zaa();

    public final void zaa(zaax zaaxVar) {
        zaaxVar.zaf.lock();
        try {
            if (zaaxVar.zan != this.zaa) {
                return;
            }
            zaa();
        } finally {
            zaaxVar.zaf.unlock();
        }
    }
}
