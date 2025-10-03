package com.google.android.gms.internal.measurement;

/* loaded from: classes2.dex */
public final class zznx implements zzeb<zzoa> {
    private static zznx zza = new zznx();
    private final zzeb<zzoa> zzb;

    private zznx(zzeb<zzoa> zzebVar) {
        this.zzb = zzea.zza((zzeb) zzebVar);
    }

    public static boolean zzb() {
        return ((zzoa) zza.zza()).zza();
    }

    public static boolean zzc() {
        return ((zzoa) zza.zza()).zzb();
    }

    public static boolean zzd() {
        return ((zzoa) zza.zza()).zzc();
    }

    public static boolean zze() {
        return ((zzoa) zza.zza()).zzd();
    }

    @Override // com.google.android.gms.internal.measurement.zzeb
    public final /* synthetic */ zzoa zza() {
        return this.zzb.zza();
    }

    public zznx() {
        this(zzea.zza(new zznz()));
    }
}
