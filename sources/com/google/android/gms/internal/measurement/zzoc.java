package com.google.android.gms.internal.measurement;

/* loaded from: classes2.dex */
public final class zzoc implements zzeb<zzob> {
    private static zzoc zza = new zzoc();
    private final zzeb<zzob> zzb;

    private zzoc(zzeb<zzob> zzebVar) {
        this.zzb = zzea.zza((zzeb) zzebVar);
    }

    public static boolean zzb() {
        return ((zzob) zza.zza()).zza();
    }

    public static boolean zzc() {
        return ((zzob) zza.zza()).zzb();
    }

    public static boolean zzd() {
        return ((zzob) zza.zza()).zzc();
    }

    @Override // com.google.android.gms.internal.measurement.zzeb
    public final /* synthetic */ zzob zza() {
        return this.zzb.zza();
    }

    public zzoc() {
        this(zzea.zza(new zzoe()));
    }
}
