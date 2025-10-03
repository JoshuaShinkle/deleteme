package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.pm.PackageManager;
import java.util.Map;

/* loaded from: classes2.dex */
final class zzk extends zzbq {

    /* renamed from: ID */
    private static final String f15368ID = com.google.android.gms.internal.gtm.zza.APP_VERSION.toString();
    private final Context zzrm;

    public zzk(Context context) {
        super(f15368ID, new String[0]);
        this.zzrm = context;
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final com.google.android.gms.internal.gtm.zzl zzb(Map<String, com.google.android.gms.internal.gtm.zzl> map) {
        try {
            return zzgj.zzi(Integer.valueOf(this.zzrm.getPackageManager().getPackageInfo(this.zzrm.getPackageName(), 0).versionCode));
        } catch (PackageManager.NameNotFoundException e9) {
            String packageName = this.zzrm.getPackageName();
            String message = e9.getMessage();
            StringBuilder sb = new StringBuilder(String.valueOf(packageName).length() + 25 + String.valueOf(message).length());
            sb.append("Package name ");
            sb.append(packageName);
            sb.append(" not found. ");
            sb.append(message);
            zzdi.zzav(sb.toString());
            return zzgj.zzkc();
        }
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final boolean zzgw() {
        return true;
    }
}
