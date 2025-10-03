package com.google.android.gms.internal.measurement;

/* loaded from: classes2.dex */
public final class zznk implements zzeb<zznj> {
    private static zznk zza = new zznk();
    private final zzeb<zznj> zzb;

    private zznk(zzeb<zznj> zzebVar) {
        this.zzb = zzea.zza((zzeb) zzebVar);
    }

    public static boolean zzb() {
        return ((zznj) zza.zza()).zza();
    }

    public static boolean zzc() {
        return ((zznj) zza.zza()).zzb();
    }

    @Override // com.google.android.gms.internal.measurement.zzeb
    public final /* synthetic */ zznj zza() {
        return this.zzb.zza();
    }

    public zznk() {
        this(zzea.zza(new zznm()));
    }
}
