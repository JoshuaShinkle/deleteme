package com.google.android.gms.tagmanager;

import java.util.Map;

/* loaded from: classes2.dex */
final class zzdk extends zzbq {

    /* renamed from: ID */
    private static final String f15349ID = com.google.android.gms.internal.gtm.zza.LOWERCASE_STRING.toString();
    private static final String zzags = com.google.android.gms.internal.gtm.zzb.ARG0.toString();

    public zzdk() {
        super(f15349ID, zzags);
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final com.google.android.gms.internal.gtm.zzl zzb(Map<String, com.google.android.gms.internal.gtm.zzl> map) {
        return zzgj.zzi(zzgj.zzc(map.get(zzags)).toLowerCase());
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final boolean zzgw() {
        return true;
    }
}
