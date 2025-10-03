package com.google.android.gms.internal.measurement;

/* loaded from: classes2.dex */
public final class zzoi implements zzeb<zzoh> {
    private static zzoi zza = new zzoi();
    private final zzeb<zzoh> zzb;

    private zzoi(zzeb<zzoh> zzebVar) {
        this.zzb = zzea.zza((zzeb) zzebVar);
    }

    public static long zzb() {
        return ((zzoh) zza.zza()).zza();
    }

    @Override // com.google.android.gms.internal.measurement.zzeb
    public final /* synthetic */ zzoh zza() {
        return this.zzb.zza();
    }

    public zzoi() {
        this(zzea.zza(new zzok()));
    }
}
