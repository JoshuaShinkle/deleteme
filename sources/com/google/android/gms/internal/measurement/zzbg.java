package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import com.google.android.gms.internal.measurement.zzag;

/* loaded from: classes2.dex */
final class zzbg extends zzag.zzb {
    private final /* synthetic */ zzt zzc;
    private final /* synthetic */ int zzd;
    private final /* synthetic */ zzag zze;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzbg(zzag zzagVar, zzt zztVar, int i9) {
        super(zzagVar);
        this.zze = zzagVar;
        this.zzc = zztVar;
        this.zzd = i9;
    }

    @Override // com.google.android.gms.internal.measurement.zzag.zzb
    public final void zza() {
        this.zze.zzm.getTestFlag(this.zzc, this.zzd);
    }

    @Override // com.google.android.gms.internal.measurement.zzag.zzb
    public final void zzb() {
        this.zzc.zza((Bundle) null);
    }
}
