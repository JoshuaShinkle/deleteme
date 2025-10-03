package com.google.android.gms.tagmanager;

import java.util.Map;

/* loaded from: classes2.dex */
final class zzbl extends zzfz {

    /* renamed from: ID */
    private static final String f15337ID = com.google.android.gms.internal.gtm.zza.ENDS_WITH.toString();

    public zzbl() {
        super(f15337ID);
    }

    @Override // com.google.android.gms.tagmanager.zzfz
    public final boolean zza(String str, String str2, Map<String, com.google.android.gms.internal.gtm.zzl> map) {
        return str.endsWith(str2);
    }
}
