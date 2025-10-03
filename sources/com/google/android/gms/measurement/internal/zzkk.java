package com.google.android.gms.measurement.internal;

/* loaded from: classes2.dex */
final class zzkk extends zzaj {
    private final /* synthetic */ zzkp zza;
    private final /* synthetic */ zzkl zzb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzkk(zzkl zzklVar, zzgw zzgwVar, zzkp zzkpVar) {
        super(zzgwVar);
        this.zzb = zzklVar;
        this.zza = zzkpVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzaj
    public final void zza() {
        this.zzb.zze();
        this.zzb.zzq().zzw().zza("Starting upload from DelayedRunnable");
        this.zza.zzo();
    }
}
