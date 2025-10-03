package com.google.android.gms.measurement.internal;

/* loaded from: classes2.dex */
final class zzgo implements Runnable {
    private final /* synthetic */ zzar zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ zzgc zzc;

    public zzgo(zzgc zzgcVar, zzar zzarVar, String str) {
        this.zzc = zzgcVar;
        this.zza = zzarVar;
        this.zzb = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzc.zza.zzr();
        this.zzc.zza.zza(this.zza, this.zzb);
    }
}
