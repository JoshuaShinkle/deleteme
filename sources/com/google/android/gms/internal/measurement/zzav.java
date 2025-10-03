package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzag;

/* loaded from: classes2.dex */
final class zzav extends zzag.zzb {
    private final /* synthetic */ String zzc;
    private final /* synthetic */ zzag zzd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzav(zzag zzagVar, String str) {
        super(zzagVar);
        this.zzd = zzagVar;
        this.zzc = str;
    }

    @Override // com.google.android.gms.internal.measurement.zzag.zzb
    public final void zza() {
        this.zzd.zzm.beginAdUnitExposure(this.zzc, this.zzb);
    }
}
