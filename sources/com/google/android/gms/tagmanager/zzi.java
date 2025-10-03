package com.google.android.gms.tagmanager;

import android.content.Context;
import java.util.Map;

/* loaded from: classes2.dex */
final class zzi extends zzbq {

    /* renamed from: ID */
    private static final String f15366ID = com.google.android.gms.internal.gtm.zza.APP_ID.toString();
    private final Context zzrm;

    public zzi(Context context) {
        super(f15366ID, new String[0]);
        this.zzrm = context;
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final com.google.android.gms.internal.gtm.zzl zzb(Map<String, com.google.android.gms.internal.gtm.zzl> map) {
        return zzgj.zzi(this.zzrm.getPackageName());
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final boolean zzgw() {
        return true;
    }
}
