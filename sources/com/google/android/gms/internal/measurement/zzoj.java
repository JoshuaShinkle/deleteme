package com.google.android.gms.internal.measurement;

/* loaded from: classes2.dex */
public final class zzoj implements zzeb<zzom> {
    private static zzoj zza = new zzoj();
    private final zzeb<zzom> zzb;

    private zzoj(zzeb<zzom> zzebVar) {
        this.zzb = zzea.zza((zzeb) zzebVar);
    }

    public static boolean zzb() {
        return ((zzom) zza.zza()).zza();
    }

    @Override // com.google.android.gms.internal.measurement.zzeb
    public final /* synthetic */ zzom zza() {
        return this.zzb.zza();
    }

    public zzoj() {
        this(zzea.zza(new zzol()));
    }
}
