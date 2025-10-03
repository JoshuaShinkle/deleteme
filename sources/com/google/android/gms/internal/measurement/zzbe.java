package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import com.google.android.gms.internal.measurement.zzag;

/* loaded from: classes2.dex */
final class zzbe extends zzag.zzb {
    private final /* synthetic */ String zzc;
    private final /* synthetic */ zzt zzd;
    private final /* synthetic */ zzag zze;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzbe(zzag zzagVar, String str, zzt zztVar) {
        super(zzagVar);
        this.zze = zzagVar;
        this.zzc = str;
        this.zzd = zztVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzag.zzb
    public final void zza() {
        this.zze.zzm.getMaxUserProperties(this.zzc, this.zzd);
    }

    @Override // com.google.android.gms.internal.measurement.zzag.zzb
    public final void zzb() {
        this.zzd.zza((Bundle) null);
    }
}
