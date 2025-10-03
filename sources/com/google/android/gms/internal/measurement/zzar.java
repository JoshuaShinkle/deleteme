package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzag;

/* loaded from: classes2.dex */
final class zzar extends zzag.zzb {
    private final /* synthetic */ zzag zzc;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzar(zzag zzagVar) {
        super(zzagVar);
        this.zzc = zzagVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzag.zzb
    public final void zza() {
        this.zzc.zzm.resetAnalyticsData(this.zza);
    }
}
