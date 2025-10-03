package com.google.android.gms.internal.measurement;

/* loaded from: classes2.dex */
public final class zzma implements zzeb<zzlz> {
    private static zzma zza = new zzma();
    private final zzeb<zzlz> zzb;

    private zzma(zzeb<zzlz> zzebVar) {
        this.zzb = zzea.zza((zzeb) zzebVar);
    }

    public static boolean zzb() {
        return ((zzlz) zza.zza()).zza();
    }

    public static boolean zzc() {
        return ((zzlz) zza.zza()).zzb();
    }

    public static boolean zzd() {
        return ((zzlz) zza.zza()).zzc();
    }

    @Override // com.google.android.gms.internal.measurement.zzeb
    public final /* synthetic */ zzlz zza() {
        return this.zzb.zza();
    }

    public zzma() {
        this(zzea.zza(new zzmc()));
    }
}
