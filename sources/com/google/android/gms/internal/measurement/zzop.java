package com.google.android.gms.internal.measurement;

/* loaded from: classes2.dex */
public final class zzop implements zzeb<zzos> {
    private static zzop zza = new zzop();
    private final zzeb<zzos> zzb;

    private zzop(zzeb<zzos> zzebVar) {
        this.zzb = zzea.zza((zzeb) zzebVar);
    }

    public static boolean zzb() {
        return ((zzos) zza.zza()).zza();
    }

    @Override // com.google.android.gms.internal.measurement.zzeb
    public final /* synthetic */ zzos zza() {
        return this.zzb.zza();
    }

    public zzop() {
        this(zzea.zza(new zzor()));
    }
}
