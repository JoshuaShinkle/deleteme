package com.google.android.gms.internal.measurement;

/* loaded from: classes2.dex */
public final class zzms implements zzeb<zzmr> {
    private static zzms zza = new zzms();
    private final zzeb<zzmr> zzb;

    private zzms(zzeb<zzmr> zzebVar) {
        this.zzb = zzea.zza((zzeb) zzebVar);
    }

    public static boolean zzb() {
        return ((zzmr) zza.zza()).zza();
    }

    public static boolean zzc() {
        return ((zzmr) zza.zza()).zzb();
    }

    public static boolean zzd() {
        return ((zzmr) zza.zza()).zzc();
    }

    public static boolean zze() {
        return ((zzmr) zza.zza()).zzd();
    }

    @Override // com.google.android.gms.internal.measurement.zzeb
    public final /* synthetic */ zzmr zza() {
        return this.zzb.zza();
    }

    public zzms() {
        this(zzea.zza(new zzmu()));
    }
}
