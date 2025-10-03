package com.google.android.gms.tagmanager;

import java.util.Map;

/* loaded from: classes2.dex */
final class zzdf extends zzdy {

    /* renamed from: ID */
    private static final String f15348ID = com.google.android.gms.internal.gtm.zza.LESS_THAN.toString();

    public zzdf() {
        super(f15348ID);
    }

    @Override // com.google.android.gms.tagmanager.zzdy
    public final boolean zza(zzgi zzgiVar, zzgi zzgiVar2, Map<String, com.google.android.gms.internal.gtm.zzl> map) {
        return zzgiVar.compareTo(zzgiVar2) < 0;
    }
}
