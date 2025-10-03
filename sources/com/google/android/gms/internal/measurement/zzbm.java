package com.google.android.gms.internal.measurement;

import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.measurement.zzag;

/* loaded from: classes2.dex */
final class zzbm extends zzag.zzb {
    private final /* synthetic */ String zzc;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ Object zze;
    private final /* synthetic */ boolean zzf;
    private final /* synthetic */ zzag zzg;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzbm(zzag zzagVar, String str, String str2, Object obj, boolean z8) {
        super(zzagVar);
        this.zzg = zzagVar;
        this.zzc = str;
        this.zzd = str2;
        this.zze = obj;
        this.zzf = z8;
    }

    @Override // com.google.android.gms.internal.measurement.zzag.zzb
    public final void zza() {
        this.zzg.zzm.setUserProperty(this.zzc, this.zzd, ObjectWrapper.wrap(this.zze), this.zzf, this.zza);
    }
}
