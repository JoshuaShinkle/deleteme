package com.google.android.gms.internal.measurement;

/* loaded from: classes2.dex */
public final class zznm implements zznj {
    private static final zzdc<Boolean> zza;
    private static final zzdc<Boolean> zzb;
    private static final zzdc<Long> zzc;

    static {
        zzdl zzdlVar = new zzdl(zzdd.zza("com.google.android.gms.measurement"));
        zza = zzdlVar.zza("measurement.collection.efficient_engagement_reporting_enabled_2", true);
        zzb = zzdlVar.zza("measurement.collection.redundant_engagement_removal_enabled", false);
        zzc = zzdlVar.zza("measurement.id.collection.redundant_engagement_removal_enabled", 0L);
    }

    @Override // com.google.android.gms.internal.measurement.zznj
    public final boolean zza() {
        return zza.zzc().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznj
    public final boolean zzb() {
        return zzb.zzc().booleanValue();
    }
}
