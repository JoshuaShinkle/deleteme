package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.gtm.zzop;

/* loaded from: classes2.dex */
final class zzad implements zzdh<zzop> {
    private final /* synthetic */ zzy zzafg;

    private zzad(zzy zzyVar) {
        this.zzafg = zzyVar;
    }

    @Override // com.google.android.gms.tagmanager.zzdh
    public final /* synthetic */ void zze(zzop zzopVar) {
        zzop zzopVar2 = zzopVar;
        com.google.android.gms.internal.gtm.zzk zzkVar = zzopVar2.zzauy;
        if (zzkVar == null) {
            com.google.android.gms.internal.gtm.zzi zziVar = zzopVar2.zzqk;
            com.google.android.gms.internal.gtm.zzk zzkVar2 = new com.google.android.gms.internal.gtm.zzk();
            zzkVar2.zzqk = zziVar;
            zzkVar2.zzqj = null;
            zzkVar2.zzql = zziVar.version;
            zzkVar = zzkVar2;
        }
        this.zzafg.zza(zzkVar, zzopVar2.zzaux, true);
    }

    @Override // com.google.android.gms.tagmanager.zzdh
    public final void zzhj() {
    }

    @Override // com.google.android.gms.tagmanager.zzdh
    public final void zzs(int i9) {
        if (this.zzafg.zzafb) {
            return;
        }
        this.zzafg.zzk(0L);
    }

    public /* synthetic */ zzad(zzy zzyVar, zzz zzzVar) {
        this(zzyVar);
    }
}
