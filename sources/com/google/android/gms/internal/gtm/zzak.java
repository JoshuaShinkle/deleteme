package com.google.android.gms.internal.gtm;

/* loaded from: classes2.dex */
final class zzak implements Runnable {
    private final /* synthetic */ zzae zzvw;
    private final /* synthetic */ zzbw zzwb;

    public zzak(zzae zzaeVar, zzbw zzbwVar) {
        this.zzvw = zzaeVar;
        this.zzwb = zzbwVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzvw.zzvu.zzb(this.zzwb);
    }
}
