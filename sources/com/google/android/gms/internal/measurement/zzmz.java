package com.google.android.gms.internal.measurement;

/* loaded from: classes2.dex */
public final class zzmz implements zzeb<zznc> {
    private static zzmz zza = new zzmz();
    private final zzeb<zznc> zzb;

    private zzmz(zzeb<zznc> zzebVar) {
        this.zzb = zzea.zza((zzeb) zzebVar);
    }

    public static boolean zzb() {
        return ((zznc) zza.zza()).zza();
    }

    public static boolean zzc() {
        return ((zznc) zza.zza()).zzb();
    }

    @Override // com.google.android.gms.internal.measurement.zzeb
    public final /* synthetic */ zznc zza() {
        return this.zzb.zza();
    }

    public zzmz() {
        this(zzea.zza(new zznb()));
    }
}
