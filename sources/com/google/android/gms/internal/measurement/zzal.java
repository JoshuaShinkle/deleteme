package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import com.google.android.gms.internal.measurement.zzag;

/* loaded from: classes2.dex */
final class zzal extends zzag.zzb {
    private final /* synthetic */ String zzc;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ Bundle zze;
    private final /* synthetic */ zzag zzf;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzal(zzag zzagVar, String str, String str2, Bundle bundle) {
        super(zzagVar);
        this.zzf = zzagVar;
        this.zzc = str;
        this.zzd = str2;
        this.zze = bundle;
    }

    @Override // com.google.android.gms.internal.measurement.zzag.zzb
    public final void zza() {
        this.zzf.zzm.clearConditionalUserProperty(this.zzc, this.zzd, this.zze);
    }
}
