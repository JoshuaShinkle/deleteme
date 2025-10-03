package com.google.android.gms.internal.measurement;

/* loaded from: classes2.dex */
public final class zzpg implements zzeb<zzpf> {
    private static zzpg zza = new zzpg();
    private final zzeb<zzpf> zzb;

    private zzpg(zzeb<zzpf> zzebVar) {
        this.zzb = zzea.zza((zzeb) zzebVar);
    }

    public static boolean zzb() {
        return ((zzpf) zza.zza()).zza();
    }

    public static boolean zzc() {
        return ((zzpf) zza.zza()).zzb();
    }

    @Override // com.google.android.gms.internal.measurement.zzeb
    public final /* synthetic */ zzpf zza() {
        return this.zzb.zza();
    }

    public zzpg() {
        this(zzea.zza(new zzpi()));
    }
}
