package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzag;

/* loaded from: classes2.dex */
final class zzbf extends zzag.zzb {
    private final /* synthetic */ com.google.android.gms.measurement.internal.zzhd zzc;
    private final /* synthetic */ zzag zzd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzbf(zzag zzagVar, com.google.android.gms.measurement.internal.zzhd zzhdVar) {
        super(zzagVar);
        this.zzd = zzagVar;
        this.zzc = zzhdVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzag.zzb
    public final void zza() {
        this.zzd.zzm.setEventInterceptor(new zzag.zza(this.zzc));
    }
}
