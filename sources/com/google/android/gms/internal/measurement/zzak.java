package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import com.google.android.gms.internal.measurement.zzag;

/* loaded from: classes2.dex */
final class zzak extends zzag.zzb {
    private final /* synthetic */ String zzc;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ zzt zze;
    private final /* synthetic */ zzag zzf;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzak(zzag zzagVar, String str, String str2, zzt zztVar) {
        super(zzagVar);
        this.zzf = zzagVar;
        this.zzc = str;
        this.zzd = str2;
        this.zze = zztVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzag.zzb
    public final void zza() {
        this.zzf.zzm.getConditionalUserProperties(this.zzc, this.zzd, this.zze);
    }

    @Override // com.google.android.gms.internal.measurement.zzag.zzb
    public final void zzb() {
        this.zze.zza((Bundle) null);
    }
}
