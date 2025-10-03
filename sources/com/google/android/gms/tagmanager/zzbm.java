package com.google.android.gms.tagmanager;

import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Map;

@ShowFirstParty
@VisibleForTesting
/* loaded from: classes2.dex */
public final class zzbm extends zzfz {

    /* renamed from: ID */
    private static final String f15338ID = com.google.android.gms.internal.gtm.zza.EQUALS.toString();

    public zzbm() {
        super(f15338ID);
    }

    @Override // com.google.android.gms.tagmanager.zzfz
    public final boolean zza(String str, String str2, Map<String, com.google.android.gms.internal.gtm.zzl> map) {
        return str.equals(str2);
    }
}
