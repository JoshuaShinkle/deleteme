package com.google.android.gms.tagmanager;

import java.util.Map;

/* loaded from: classes2.dex */
final class zzt extends zzbq {

    /* renamed from: ID */
    private static final String f15371ID = com.google.android.gms.internal.gtm.zza.CONSTANT.toString();
    private static final String VALUE = com.google.android.gms.internal.gtm.zzb.VALUE.toString();

    public zzt() {
        super(f15371ID, VALUE);
    }

    public static String zzgy() {
        return f15371ID;
    }

    public static String zzgz() {
        return VALUE;
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final com.google.android.gms.internal.gtm.zzl zzb(Map<String, com.google.android.gms.internal.gtm.zzl> map) {
        return map.get(VALUE);
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final boolean zzgw() {
        return true;
    }
}
