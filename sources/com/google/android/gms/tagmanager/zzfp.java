package com.google.android.gms.tagmanager;

/* loaded from: classes2.dex */
final class zzfp implements Runnable {
    private final /* synthetic */ zzfn zzakz;

    public zzfp(zzfn zzfnVar) {
        this.zzakz = zzfnVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzakz.zzakp.dispatch();
    }
}
