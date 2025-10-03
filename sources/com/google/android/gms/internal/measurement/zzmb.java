package com.google.android.gms.internal.measurement;

/* loaded from: classes2.dex */
public final class zzmb implements zzeb<zzme> {
    private static zzmb zza = new zzmb();
    private final zzeb<zzme> zzb;

    private zzmb(zzeb<zzme> zzebVar) {
        this.zzb = zzea.zza((zzeb) zzebVar);
    }

    public static boolean zzb() {
        return ((zzme) zza.zza()).zza();
    }

    public static boolean zzc() {
        return ((zzme) zza.zza()).zzb();
    }

    public static boolean zzd() {
        return ((zzme) zza.zza()).zzc();
    }

    public static long zze() {
        return ((zzme) zza.zza()).zzd();
    }

    @Override // com.google.android.gms.internal.measurement.zzeb
    public final /* synthetic */ zzme zza() {
        return this.zzb.zza();
    }

    public zzmb() {
        this(zzea.zza(new zzmd()));
    }
}
