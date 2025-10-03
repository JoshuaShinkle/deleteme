package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import com.google.android.gms.internal.measurement.zzag;

/* loaded from: classes2.dex */
final class zzax extends zzag.zzb {
    private final /* synthetic */ zzt zzc;
    private final /* synthetic */ zzag zzd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzax(zzag zzagVar, zzt zztVar) {
        super(zzagVar);
        this.zzd = zzagVar;
        this.zzc = zztVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzag.zzb
    public final void zza() {
        this.zzd.zzm.getGmpAppId(this.zzc);
    }

    @Override // com.google.android.gms.internal.measurement.zzag.zzb
    public final void zzb() {
        this.zzc.zza((Bundle) null);
    }
}
