package com.google.android.gms.internal.measurement;

/* loaded from: classes2.dex */
public final class zzmn implements zzeb<zzmq> {
    private static zzmn zza = new zzmn();
    private final zzeb<zzmq> zzb;

    private zzmn(zzeb<zzmq> zzebVar) {
        this.zzb = zzea.zza((zzeb) zzebVar);
    }

    public static boolean zzb() {
        return ((zzmq) zza.zza()).zza();
    }

    public static boolean zzc() {
        return ((zzmq) zza.zza()).zzb();
    }

    @Override // com.google.android.gms.internal.measurement.zzeb
    public final /* synthetic */ zzmq zza() {
        return this.zzb.zza();
    }

    public zzmn() {
        this(zzea.zza(new zzmp()));
    }
}
