package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Map;

@VisibleForTesting
/* loaded from: classes2.dex */
final class zzas extends zzbq {

    /* renamed from: ID */
    private static final String f15332ID = com.google.android.gms.internal.gtm.zza.CUSTOM_VAR.toString();
    private static final String NAME = com.google.android.gms.internal.gtm.zzb.NAME.toString();
    private static final String zzafw = com.google.android.gms.internal.gtm.zzb.DEFAULT_VALUE.toString();
    private final DataLayer zzaed;

    public zzas(DataLayer dataLayer) {
        super(f15332ID, NAME);
        this.zzaed = dataLayer;
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final com.google.android.gms.internal.gtm.zzl zzb(Map<String, com.google.android.gms.internal.gtm.zzl> map) {
        Object obj = this.zzaed.get(zzgj.zzc(map.get(NAME)));
        if (obj != null) {
            return zzgj.zzi(obj);
        }
        com.google.android.gms.internal.gtm.zzl zzlVar = map.get(zzafw);
        return zzlVar != null ? zzlVar : zzgj.zzkc();
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final boolean zzgw() {
        return false;
    }
}
