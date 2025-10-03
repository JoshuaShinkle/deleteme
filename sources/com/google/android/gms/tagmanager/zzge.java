package com.google.android.gms.tagmanager;

import java.util.Map;

/* loaded from: classes2.dex */
final class zzge extends zzbq {

    /* renamed from: ID */
    private static final String f15362ID = com.google.android.gms.internal.gtm.zza.TIME.toString();

    public zzge() {
        super(f15362ID, new String[0]);
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final com.google.android.gms.internal.gtm.zzl zzb(Map<String, com.google.android.gms.internal.gtm.zzl> map) {
        return zzgj.zzi(Long.valueOf(System.currentTimeMillis()));
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final boolean zzgw() {
        return false;
    }
}
