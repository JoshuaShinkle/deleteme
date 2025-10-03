package com.google.android.gms.internal.measurement;

/* loaded from: classes2.dex */
public final class zzpm implements zzeb<zzpl> {
    private static zzpm zza = new zzpm();
    private final zzeb<zzpl> zzb;

    private zzpm(zzeb<zzpl> zzebVar) {
        this.zzb = zzea.zza((zzeb) zzebVar);
    }

    public static boolean zzb() {
        return ((zzpl) zza.zza()).zza();
    }

    @Override // com.google.android.gms.internal.measurement.zzeb
    public final /* synthetic */ zzpl zza() {
        return this.zzb.zza();
    }

    public zzpm() {
        this(zzea.zza(new zzpo()));
    }
}
