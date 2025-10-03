package com.google.android.gms.tagmanager;

import java.util.Map;

/* loaded from: classes2.dex */
final class zzgl extends zzbq {

    /* renamed from: ID */
    private static final String f15364ID = com.google.android.gms.internal.gtm.zza.UPPERCASE_STRING.toString();
    private static final String zzags = com.google.android.gms.internal.gtm.zzb.ARG0.toString();

    public zzgl() {
        super(f15364ID, zzags);
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final com.google.android.gms.internal.gtm.zzl zzb(Map<String, com.google.android.gms.internal.gtm.zzl> map) {
        return zzgj.zzi(zzgj.zzc(map.get(zzags)).toUpperCase());
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final boolean zzgw() {
        return true;
    }
}
