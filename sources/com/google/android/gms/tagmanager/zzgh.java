package com.google.android.gms.tagmanager;

import java.util.Map;

/* loaded from: classes2.dex */
abstract class zzgh extends zzbq {
    public zzgh(String str, String... strArr) {
        super(str, strArr);
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public com.google.android.gms.internal.gtm.zzl zzb(Map<String, com.google.android.gms.internal.gtm.zzl> map) {
        zzd(map);
        return zzgj.zzkc();
    }

    public abstract void zzd(Map<String, com.google.android.gms.internal.gtm.zzl> map);

    @Override // com.google.android.gms.tagmanager.zzbq
    public boolean zzgw() {
        return false;
    }
}
