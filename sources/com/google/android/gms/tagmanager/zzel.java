package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@VisibleForTesting
/* loaded from: classes2.dex */
final class zzel extends zzfz {

    /* renamed from: ID */
    private static final String f15356ID = com.google.android.gms.internal.gtm.zza.REGEX.toString();
    private static final String zzajb = com.google.android.gms.internal.gtm.zzb.IGNORE_CASE.toString();

    public zzel() {
        super(f15356ID);
    }

    @Override // com.google.android.gms.tagmanager.zzfz
    public final boolean zza(String str, String str2, Map<String, com.google.android.gms.internal.gtm.zzl> map) {
        try {
            return Pattern.compile(str2, zzgj.zzg(map.get(zzajb)).booleanValue() ? 66 : 64).matcher(str).find();
        } catch (PatternSyntaxException unused) {
            return false;
        }
    }
}
