package com.google.android.gms.common.api.internal;

/* loaded from: classes2.dex */
final class zabh implements Runnable {
    private final /* synthetic */ zabf zaa;

    public zabh(zabf zabfVar) {
        this.zaa = zabfVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zaa.zaa.zac.disconnect(this.zaa.zaa.zac.getClass().getName().concat(" disconnecting because it was signed out."));
    }
}
