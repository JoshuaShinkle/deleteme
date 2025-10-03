package com.google.android.gms.internal.measurement;

/* loaded from: classes2.dex */
public final class zzlp implements zzeb<zzls> {
    private static zzlp zza = new zzlp();
    private final zzeb<zzls> zzb;

    private zzlp(zzeb<zzls> zzebVar) {
        this.zzb = zzea.zza((zzeb) zzebVar);
    }

    public static boolean zzb() {
        return ((zzls) zza.zza()).zza();
    }

    @Override // com.google.android.gms.internal.measurement.zzeb
    public final /* synthetic */ zzls zza() {
        return this.zzb.zza();
    }

    public zzlp() {
        this(zzea.zza(new zzlr()));
    }
}
