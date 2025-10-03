package com.google.android.gms.internal.measurement;

/* loaded from: classes2.dex */
public final class zzmy implements zzeb<zzmx> {
    private static zzmy zza = new zzmy();
    private final zzeb<zzmx> zzb;

    private zzmy(zzeb<zzmx> zzebVar) {
        this.zzb = zzea.zza((zzeb) zzebVar);
    }

    public static boolean zzb() {
        return ((zzmx) zza.zza()).zza();
    }

    public static boolean zzc() {
        return ((zzmx) zza.zza()).zzb();
    }

    @Override // com.google.android.gms.internal.measurement.zzeb
    public final /* synthetic */ zzmx zza() {
        return this.zzb.zza();
    }

    public zzmy() {
        this(zzea.zza(new zzna()));
    }
}
