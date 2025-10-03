package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Map;

@VisibleForTesting
/* loaded from: classes2.dex */
final class zzaj extends zzbq {

    /* renamed from: ID */
    private static final String f15329ID = com.google.android.gms.internal.gtm.zza.CONTAINER_VERSION.toString();
    private final String version;

    public zzaj(String str) {
        super(f15329ID, new String[0]);
        this.version = str;
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final com.google.android.gms.internal.gtm.zzl zzb(Map<String, com.google.android.gms.internal.gtm.zzl> map) {
        String str = this.version;
        return str == null ? zzgj.zzkc() : zzgj.zzi(str);
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final boolean zzgw() {
        return true;
    }
}
