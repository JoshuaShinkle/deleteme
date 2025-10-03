package com.google.android.gms.internal.measurement;

/* loaded from: classes2.dex */
public final class zzng implements zznd {
    private static final zzdc<Boolean> zza;
    private static final zzdc<Boolean> zzb;
    private static final zzdc<Boolean> zzc;

    static {
        zzdl zzdlVar = new zzdl(zzdd.zza("com.google.android.gms.measurement"));
        zza = zzdlVar.zza("measurement.client.sessions.check_on_reset_and_enable2", true);
        zzb = zzdlVar.zza("measurement.client.sessions.check_on_startup", true);
        zzc = zzdlVar.zza("measurement.client.sessions.start_session_before_view_screen", true);
    }

    @Override // com.google.android.gms.internal.measurement.zznd
    public final boolean zza() {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zznd
    public final boolean zzb() {
        return zza.zzc().booleanValue();
    }
}
