package com.google.android.gms.internal.measurement;

/* loaded from: classes2.dex */
public final class zzlu implements zzeb<zzlt> {
    private static zzlu zza = new zzlu();
    private final zzeb<zzlt> zzb;

    private zzlu(zzeb<zzlt> zzebVar) {
        this.zzb = zzea.zza((zzeb) zzebVar);
    }

    public static boolean zzb() {
        return ((zzlt) zza.zza()).zza();
    }

    public static long zzc() {
        return ((zzlt) zza.zza()).zzb();
    }

    @Override // com.google.android.gms.internal.measurement.zzeb
    public final /* synthetic */ zzlt zza() {
        return this.zzb.zza();
    }

    public zzlu() {
        this(zzea.zza(new zzlw()));
    }
}
