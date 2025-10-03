package com.google.android.gms.internal.gtm;

/* loaded from: classes2.dex */
final class zzag implements Runnable {
    private final /* synthetic */ zzae zzvw;
    private final /* synthetic */ boolean zzvx;

    public zzag(zzae zzaeVar, boolean z8) {
        this.zzvw = zzaeVar;
        this.zzvx = z8;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzvw.zzvu.zzec();
    }
}
