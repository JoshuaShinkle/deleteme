package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Map;

/* loaded from: classes2.dex */
final class zze extends zzbq {

    /* renamed from: ID */
    private static final String f15351ID = com.google.android.gms.internal.gtm.zza.ADVERTISER_ID.toString();
    private final zza zzadt;

    public zze(Context context) {
        this(zza.zzf(context));
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final com.google.android.gms.internal.gtm.zzl zzb(Map<String, com.google.android.gms.internal.gtm.zzl> map) {
        String strZzgq = this.zzadt.zzgq();
        return strZzgq == null ? zzgj.zzkc() : zzgj.zzi(strZzgq);
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final boolean zzgw() {
        return false;
    }

    @VisibleForTesting
    private zze(zza zzaVar) {
        super(f15351ID, new String[0]);
        this.zzadt = zzaVar;
        zzaVar.zzgq();
    }
}
