package com.google.android.gms.tagmanager;

import android.content.Context;
import java.util.Map;

/* loaded from: classes2.dex */
final class zzcv extends zzbq {

    /* renamed from: ID */
    private static final String f15344ID = com.google.android.gms.internal.gtm.zza.INSTALL_REFERRER.toString();
    private static final String zzadu = com.google.android.gms.internal.gtm.zzb.COMPONENT.toString();
    private final Context zzrm;

    public zzcv(Context context) {
        super(f15344ID, new String[0]);
        this.zzrm = context;
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final com.google.android.gms.internal.gtm.zzl zzb(Map<String, com.google.android.gms.internal.gtm.zzl> map) {
        String str = zzadu;
        String strZze = zzcw.zze(this.zzrm, map.get(str) != null ? zzgj.zzc(map.get(str)) : null);
        return strZze != null ? zzgj.zzi(strZze) : zzgj.zzkc();
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final boolean zzgw() {
        return true;
    }
}
