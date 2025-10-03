package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzag;

/* loaded from: classes2.dex */
final class zzap extends zzag.zzb {
    private final /* synthetic */ Boolean zzc;
    private final /* synthetic */ zzag zzd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzap(zzag zzagVar, Boolean bool) {
        super(zzagVar);
        this.zzd = zzagVar;
        this.zzc = bool;
    }

    @Override // com.google.android.gms.internal.measurement.zzag.zzb
    public final void zza() {
        if (this.zzc != null) {
            this.zzd.zzm.setMeasurementEnabled(this.zzc.booleanValue(), this.zza);
        } else {
            this.zzd.zzm.clearMeasurementEnabled(this.zza);
        }
    }
}
