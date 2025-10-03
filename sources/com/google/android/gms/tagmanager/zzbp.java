package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Map;

@VisibleForTesting
/* loaded from: classes2.dex */
final class zzbp extends zzbq {

    /* renamed from: ID */
    private static final String f15339ID = com.google.android.gms.internal.gtm.zza.EVENT.toString();
    private final zzfb zzaee;

    public zzbp(zzfb zzfbVar) {
        super(f15339ID, new String[0]);
        this.zzaee = zzfbVar;
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final com.google.android.gms.internal.gtm.zzl zzb(Map<String, com.google.android.gms.internal.gtm.zzl> map) {
        String strZzjf = this.zzaee.zzjf();
        return strZzjf == null ? zzgj.zzkc() : zzgj.zzi(strZzjf);
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final boolean zzgw() {
        return false;
    }
}
