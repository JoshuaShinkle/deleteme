package com.google.android.gms.internal.gtm;

/* loaded from: classes2.dex */
final class zzaf implements Runnable {
    private final /* synthetic */ int zzvv;
    private final /* synthetic */ zzae zzvw;

    public zzaf(zzae zzaeVar, int i9) {
        this.zzvw = zzaeVar;
        this.zzvv = i9;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzvw.zzvu.zzg(this.zzvv * 1000);
    }
}
