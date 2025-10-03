package com.google.android.gms.internal.measurement;

/* loaded from: classes2.dex */
public final class zzod implements zzeb<zzog> {
    private static zzod zza = new zzod();
    private final zzeb<zzog> zzb;

    private zzod(zzeb<zzog> zzebVar) {
        this.zzb = zzea.zza((zzeb) zzebVar);
    }

    public static boolean zzb() {
        return ((zzog) zza.zza()).zza();
    }

    public static boolean zzc() {
        return ((zzog) zza.zza()).zzb();
    }

    public static boolean zzd() {
        return ((zzog) zza.zza()).zzc();
    }

    @Override // com.google.android.gms.internal.measurement.zzeb
    public final /* synthetic */ zzog zza() {
        return this.zzb.zza();
    }

    public zzod() {
        this(zzea.zza(new zzof()));
    }
}
