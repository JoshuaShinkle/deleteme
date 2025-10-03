package com.google.android.gms.tagmanager;

import java.util.Map;

/* loaded from: classes2.dex */
final class zzbs extends zzdy {

    /* renamed from: ID */
    private static final String f15340ID = com.google.android.gms.internal.gtm.zza.GREATER_EQUALS.toString();

    public zzbs() {
        super(f15340ID);
    }

    @Override // com.google.android.gms.tagmanager.zzdy
    public final boolean zza(zzgi zzgiVar, zzgi zzgiVar2, Map<String, com.google.android.gms.internal.gtm.zzl> map) {
        return zzgiVar.compareTo(zzgiVar2) >= 0;
    }
}
