package com.google.android.gms.tagmanager;

import android.os.Build;
import java.util.Map;

/* loaded from: classes2.dex */
final class zzea extends zzbq {

    /* renamed from: ID */
    private static final String f15352ID = com.google.android.gms.internal.gtm.zza.OS_VERSION.toString();

    public zzea() {
        super(f15352ID, new String[0]);
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final com.google.android.gms.internal.gtm.zzl zzb(Map<String, com.google.android.gms.internal.gtm.zzl> map) {
        return zzgj.zzi(Build.VERSION.RELEASE);
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final boolean zzgw() {
        return true;
    }
}
