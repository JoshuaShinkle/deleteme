package com.google.android.gms.common.api.internal;

/* loaded from: classes2.dex */
final class zacc implements Runnable {
    private final /* synthetic */ com.google.android.gms.signin.internal.zam zaa;
    private final /* synthetic */ zacb zab;

    public zacc(zacb zacbVar, com.google.android.gms.signin.internal.zam zamVar) {
        this.zab = zacbVar;
        this.zaa = zamVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zab.zab(this.zaa);
    }
}
