package com.google.android.gms.tagmanager;

import android.content.Context;
import android.provider.Settings;
import java.util.Map;

/* loaded from: classes2.dex */
final class zzdm extends zzbq {

    /* renamed from: ID */
    private static final String f15350ID = com.google.android.gms.internal.gtm.zza.MOBILE_ADWORDS_UNIQUE_ID.toString();
    private final Context zzrm;

    public zzdm(Context context) {
        super(f15350ID, new String[0]);
        this.zzrm = context;
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final com.google.android.gms.internal.gtm.zzl zzb(Map<String, com.google.android.gms.internal.gtm.zzl> map) {
        String string = Settings.Secure.getString(this.zzrm.getContentResolver(), "android_id");
        return string == null ? zzgj.zzkc() : zzgj.zzi(string);
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final boolean zzgw() {
        return true;
    }
}
