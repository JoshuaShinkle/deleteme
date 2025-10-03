package com.google.android.gms.internal.measurement;

/* loaded from: classes2.dex */
public final class zzmt implements zzeb<zzmw> {
    private static zzmt zza = new zzmt();
    private final zzeb<zzmw> zzb;

    private zzmt(zzeb<zzmw> zzebVar) {
        this.zzb = zzea.zza((zzeb) zzebVar);
    }

    public static boolean zzb() {
        return ((zzmw) zza.zza()).zza();
    }

    public static boolean zzc() {
        return ((zzmw) zza.zza()).zzb();
    }

    @Override // com.google.android.gms.internal.measurement.zzeb
    public final /* synthetic */ zzmw zza() {
        return this.zzb.zza();
    }

    public zzmt() {
        this(zzea.zza(new zzmv()));
    }
}
