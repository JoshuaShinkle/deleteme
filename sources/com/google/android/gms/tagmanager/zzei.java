package com.google.android.gms.tagmanager;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.Map;

/* loaded from: classes2.dex */
final class zzei extends zzbq {

    /* renamed from: ID */
    private static final String f15354ID = com.google.android.gms.internal.gtm.zza.RANDOM.toString();
    private static final String zzaix = com.google.android.gms.internal.gtm.zzb.MIN.toString();
    private static final String zzaiy = com.google.android.gms.internal.gtm.zzb.MAX.toString();

    public zzei() {
        super(f15354ID, new String[0]);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0041  */
    @Override // com.google.android.gms.tagmanager.zzbq
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final com.google.android.gms.internal.gtm.zzl zzb(Map<String, com.google.android.gms.internal.gtm.zzl> map) {
        double dDoubleValue;
        double dDoubleValue2;
        com.google.android.gms.internal.gtm.zzl zzlVar = map.get(zzaix);
        com.google.android.gms.internal.gtm.zzl zzlVar2 = map.get(zzaiy);
        if (zzlVar == null || zzlVar == zzgj.zzkc() || zzlVar2 == null || zzlVar2 == zzgj.zzkc()) {
            dDoubleValue = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            dDoubleValue2 = 2.147483647E9d;
        } else {
            zzgi zzgiVarZzd = zzgj.zzd(zzlVar);
            zzgi zzgiVarZzd2 = zzgj.zzd(zzlVar2);
            if (zzgiVarZzd != zzgj.zzka() && zzgiVarZzd2 != zzgj.zzka()) {
                dDoubleValue = zzgiVarZzd.doubleValue();
                dDoubleValue2 = zzgiVarZzd2.doubleValue();
                if (dDoubleValue > dDoubleValue2) {
                }
            }
        }
        return zzgj.zzi(Long.valueOf(Math.round((Math.random() * (dDoubleValue2 - dDoubleValue)) + dDoubleValue)));
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final boolean zzgw() {
        return false;
    }
}
