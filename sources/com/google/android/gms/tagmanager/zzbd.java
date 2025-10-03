package com.google.android.gms.tagmanager;

import android.os.Build;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
final class zzbd extends zzbq {

    /* renamed from: ID */
    private static final String f15335ID = com.google.android.gms.internal.gtm.zza.DEVICE_NAME.toString();

    public zzbd() {
        super(f15335ID, new String[0]);
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final com.google.android.gms.internal.gtm.zzl zzb(Map<String, com.google.android.gms.internal.gtm.zzl> map) {
        String str = Build.MANUFACTURER;
        String string = Build.MODEL;
        if (!string.startsWith(str) && !str.equals("unknown")) {
            StringBuilder sb = new StringBuilder(str.length() + 1 + string.length());
            sb.append(str);
            sb.append(StringUtils.SPACE);
            sb.append(string);
            string = sb.toString();
        }
        return zzgj.zzi(string);
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final boolean zzgw() {
        return true;
    }
}
