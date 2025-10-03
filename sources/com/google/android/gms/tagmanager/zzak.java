package com.google.android.gms.tagmanager;

import java.util.Map;

/* loaded from: classes2.dex */
final class zzak extends zzfz {

    /* renamed from: ID */
    private static final String f15330ID = com.google.android.gms.internal.gtm.zza.CONTAINS.toString();

    public zzak() {
        super(f15330ID);
    }

    @Override // com.google.android.gms.tagmanager.zzfz
    public final boolean zza(String str, String str2, Map<String, com.google.android.gms.internal.gtm.zzl> map) {
        return str.contains(str2);
    }
}
