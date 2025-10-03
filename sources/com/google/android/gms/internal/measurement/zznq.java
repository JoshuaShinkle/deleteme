package com.google.android.gms.internal.measurement;

/* loaded from: classes2.dex */
public final class zznq implements zzeb<zznp> {
    private static zznq zza = new zznq();
    private final zzeb<zznp> zzb;

    private zznq(zzeb<zznp> zzebVar) {
        this.zzb = zzea.zza((zzeb) zzebVar);
    }

    public static boolean zzb() {
        return ((zznp) zza.zza()).zza();
    }

    public static boolean zzc() {
        return ((zznp) zza.zza()).zzb();
    }

    @Override // com.google.android.gms.internal.measurement.zzeb
    public final /* synthetic */ zznp zza() {
        return this.zzb.zza();
    }

    public zznq() {
        this(zzea.zza(new zzns()));
    }
}
