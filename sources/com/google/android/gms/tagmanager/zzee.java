package com.google.android.gms.tagmanager;

import java.util.Map;

/* loaded from: classes2.dex */
final class zzee extends zzbq {

    /* renamed from: ID */
    private static final String f15353ID = com.google.android.gms.internal.gtm.zza.PLATFORM.toString();
    private static final com.google.android.gms.internal.gtm.zzl zzain = zzgj.zzi("Android");

    public zzee() {
        super(f15353ID, new String[0]);
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final com.google.android.gms.internal.gtm.zzl zzb(Map<String, com.google.android.gms.internal.gtm.zzl> map) {
        return zzain;
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final boolean zzgw() {
        return true;
    }
}
