package com.google.android.gms.tagmanager;

import java.util.Map;

/* loaded from: classes2.dex */
final class zzfy extends zzfz {

    /* renamed from: ID */
    private static final String f15361ID = com.google.android.gms.internal.gtm.zza.STARTS_WITH.toString();

    public zzfy() {
        super(f15361ID);
    }

    @Override // com.google.android.gms.tagmanager.zzfz
    public final boolean zza(String str, String str2, Map<String, com.google.android.gms.internal.gtm.zzl> map) {
        return str.startsWith(str2);
    }
}
