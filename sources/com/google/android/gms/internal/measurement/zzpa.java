package com.google.android.gms.internal.measurement;

/* loaded from: classes2.dex */
public final class zzpa implements zzeb<zzoz> {
    private static zzpa zza = new zzpa();
    private final zzeb<zzoz> zzb;

    private zzpa(zzeb<zzoz> zzebVar) {
        this.zzb = zzea.zza((zzeb) zzebVar);
    }

    public static boolean zzb() {
        return ((zzoz) zza.zza()).zza();
    }

    public static boolean zzc() {
        return ((zzoz) zza.zza()).zzb();
    }

    @Override // com.google.android.gms.internal.measurement.zzeb
    public final /* synthetic */ zzoz zza() {
        return this.zzb.zza();
    }

    public zzpa() {
        this(zzea.zza(new zzpc()));
    }
}
