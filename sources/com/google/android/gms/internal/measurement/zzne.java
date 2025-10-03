package com.google.android.gms.internal.measurement;

/* loaded from: classes2.dex */
public final class zzne implements zzeb<zznd> {
    private static zzne zza = new zzne();
    private final zzeb<zznd> zzb;

    private zzne(zzeb<zznd> zzebVar) {
        this.zzb = zzea.zza((zzeb) zzebVar);
    }

    public static boolean zzb() {
        return ((zznd) zza.zza()).zza();
    }

    public static boolean zzc() {
        return ((zznd) zza.zza()).zzb();
    }

    @Override // com.google.android.gms.internal.measurement.zzeb
    public final /* synthetic */ zznd zza() {
        return this.zzb.zza();
    }

    public zzne() {
        this(zzea.zza(new zzng()));
    }
}
