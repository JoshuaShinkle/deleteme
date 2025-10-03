package com.google.android.gms.internal.measurement;

/* loaded from: classes2.dex */
public final class zzox implements zzoy {
    private static final zzdc<Boolean> zza;
    private static final zzdc<Long> zzb;

    static {
        zzdl zzdlVar = new zzdl(zzdd.zza("com.google.android.gms.measurement"));
        zza = zzdlVar.zza("measurement.service.ssaid_removal", true);
        zzb = zzdlVar.zza("measurement.id.ssaid_removal", 0L);
    }

    @Override // com.google.android.gms.internal.measurement.zzoy
    public final boolean zza() {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzoy
    public final boolean zzb() {
        return zza.zzc().booleanValue();
    }
}
