package com.google.android.gms.internal.measurement;

/* loaded from: classes2.dex */
public final class zzov implements zzeb<zzoy> {
    private static zzov zza = new zzov();
    private final zzeb<zzoy> zzb;

    private zzov(zzeb<zzoy> zzebVar) {
        this.zzb = zzea.zza((zzeb) zzebVar);
    }

    public static boolean zzb() {
        return ((zzoy) zza.zza()).zza();
    }

    public static boolean zzc() {
        return ((zzoy) zza.zza()).zzb();
    }

    @Override // com.google.android.gms.internal.measurement.zzeb
    public final /* synthetic */ zzoy zza() {
        return this.zzb.zza();
    }

    public zzov() {
        this(zzea.zza(new zzox()));
    }
}
