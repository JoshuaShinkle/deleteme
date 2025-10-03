package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzag;

/* loaded from: classes2.dex */
final class zzat extends zzag.zzb {
    private final /* synthetic */ long zzc;
    private final /* synthetic */ zzag zzd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzat(zzag zzagVar, long j9) {
        super(zzagVar);
        this.zzd = zzagVar;
        this.zzc = j9;
    }

    @Override // com.google.android.gms.internal.measurement.zzag.zzb
    public final void zza() {
        this.zzd.zzm.setSessionTimeoutDuration(this.zzc);
    }
}
