package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import com.google.android.gms.internal.measurement.zzag;

/* loaded from: classes2.dex */
final class zzbn extends zzag.zzb {
    private final /* synthetic */ Long zzc;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ String zze;
    private final /* synthetic */ Bundle zzf;
    private final /* synthetic */ boolean zzg;
    private final /* synthetic */ boolean zzh;
    private final /* synthetic */ zzag zzi;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzbn(zzag zzagVar, Long l9, String str, String str2, Bundle bundle, boolean z8, boolean z9) {
        super(zzagVar);
        this.zzi = zzagVar;
        this.zzc = l9;
        this.zzd = str;
        this.zze = str2;
        this.zzf = bundle;
        this.zzg = z8;
        this.zzh = z9;
    }

    @Override // com.google.android.gms.internal.measurement.zzag.zzb
    public final void zza() {
        Long l9 = this.zzc;
        this.zzi.zzm.logEvent(this.zzd, this.zze, this.zzf, this.zzg, this.zzh, l9 == null ? this.zza : l9.longValue());
    }
}
