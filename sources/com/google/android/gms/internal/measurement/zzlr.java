package com.google.android.gms.internal.measurement;

/* loaded from: classes2.dex */
public final class zzlr implements zzls {
    private static final zzdc<Boolean> zza;
    private static final zzdc<Boolean> zzb;

    static {
        zzdl zzdlVar = new zzdl(zzdd.zza("com.google.android.gms.measurement"));
        zza = zzdlVar.zza("measurement.androidId.delete_feature", true);
        zzb = zzdlVar.zza("measurement.log_androidId_enabled", false);
    }

    @Override // com.google.android.gms.internal.measurement.zzls
    public final boolean zza() {
        return zza.zzc().booleanValue();
    }
}
