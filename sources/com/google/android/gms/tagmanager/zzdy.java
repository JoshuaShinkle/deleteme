package com.google.android.gms.tagmanager;

import java.util.Map;

/* loaded from: classes2.dex */
abstract class zzdy extends zzef {
    public zzdy(String str) {
        super(str);
    }

    @Override // com.google.android.gms.tagmanager.zzef
    public final boolean zza(com.google.android.gms.internal.gtm.zzl zzlVar, com.google.android.gms.internal.gtm.zzl zzlVar2, Map<String, com.google.android.gms.internal.gtm.zzl> map) {
        zzgi zzgiVarZzd = zzgj.zzd(zzlVar);
        zzgi zzgiVarZzd2 = zzgj.zzd(zzlVar2);
        if (zzgiVarZzd == zzgj.zzka() || zzgiVarZzd2 == zzgj.zzka()) {
            return false;
        }
        return zza(zzgiVarZzd, zzgiVarZzd2, map);
    }

    public abstract boolean zza(zzgi zzgiVar, zzgi zzgiVar2, Map<String, com.google.android.gms.internal.gtm.zzl> map);
}
