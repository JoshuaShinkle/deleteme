package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import com.google.android.gms.internal.measurement.zzag;

/* loaded from: classes2.dex */
final class zzba extends zzag.zzb {
    private final /* synthetic */ String zzc;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ boolean zze;
    private final /* synthetic */ zzt zzf;
    private final /* synthetic */ zzag zzg;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzba(zzag zzagVar, String str, String str2, boolean z8, zzt zztVar) {
        super(zzagVar);
        this.zzg = zzagVar;
        this.zzc = str;
        this.zzd = str2;
        this.zze = z8;
        this.zzf = zztVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzag.zzb
    public final void zza() {
        this.zzg.zzm.getUserProperties(this.zzc, this.zzd, this.zze, this.zzf);
    }

    @Override // com.google.android.gms.internal.measurement.zzag.zzb
    public final void zzb() {
        this.zzf.zza((Bundle) null);
    }
}
