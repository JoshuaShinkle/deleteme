package com.google.android.gms.tagmanager;

import java.util.Map;

/* loaded from: classes2.dex */
final class zzbt extends zzdy {

    /* renamed from: ID */
    private static final String f15341ID = com.google.android.gms.internal.gtm.zza.GREATER_THAN.toString();

    public zzbt() {
        super(f15341ID);
    }

    @Override // com.google.android.gms.tagmanager.zzdy
    public final boolean zza(zzgi zzgiVar, zzgi zzgiVar2, Map<String, com.google.android.gms.internal.gtm.zzl> map) {
        return zzgiVar.compareTo(zzgiVar2) > 0;
    }
}
