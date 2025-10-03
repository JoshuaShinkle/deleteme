package com.google.android.gms.internal.gtm;

/* loaded from: classes2.dex */
final class zzct implements zzbw {
    private final /* synthetic */ Runnable zzacj;
    private final /* synthetic */ zzcq zzack;

    public zzct(zzcq zzcqVar, Runnable runnable) {
        this.zzack = zzcqVar;
        this.zzacj = runnable;
    }

    @Override // com.google.android.gms.internal.gtm.zzbw
    public final void zza(Throwable th) {
        this.zzack.handler.post(this.zzacj);
    }
}
