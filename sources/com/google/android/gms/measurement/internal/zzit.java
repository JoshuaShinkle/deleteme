package com.google.android.gms.measurement.internal;

/* loaded from: classes2.dex */
final class zzit implements Runnable {
    private final /* synthetic */ zzin zza;
    private final /* synthetic */ long zzb;
    private final /* synthetic */ zzim zzc;

    public zzit(zzim zzimVar, zzin zzinVar, long j9) {
        this.zzc = zzimVar;
        this.zza = zzinVar;
        this.zzb = j9;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzc.zza(this.zza, false, this.zzb);
        zzim zzimVar = this.zzc;
        zzimVar.zza = null;
        zzimVar.zzg().zza((zzin) null);
    }
}
