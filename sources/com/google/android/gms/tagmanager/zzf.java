package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Map;

/* loaded from: classes2.dex */
final class zzf extends zzbq {

    /* renamed from: ID */
    private static final String f15358ID = com.google.android.gms.internal.gtm.zza.ADVERTISING_TRACKING_ENABLED.toString();
    private final zza zzadt;

    public zzf(Context context) {
        this(zza.zzf(context));
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final com.google.android.gms.internal.gtm.zzl zzb(Map<String, com.google.android.gms.internal.gtm.zzl> map) {
        return zzgj.zzi(Boolean.valueOf(!this.zzadt.isLimitAdTrackingEnabled()));
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final boolean zzgw() {
        return false;
    }

    @VisibleForTesting
    private zzf(zza zzaVar) {
        super(f15358ID, new String[0]);
        this.zzadt = zzaVar;
    }
}
