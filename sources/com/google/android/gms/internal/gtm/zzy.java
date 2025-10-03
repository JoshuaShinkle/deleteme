package com.google.android.gms.internal.gtm;

import android.text.TextUtils;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.plus.PlusShare;
import java.util.HashMap;

@ShowFirstParty
/* loaded from: classes2.dex */
public final class zzy extends com.google.android.gms.analytics.zzi<zzy> {
    public String zzuq;
    public boolean zzur;

    public final String toString() {
        HashMap map = new HashMap();
        map.put(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, this.zzuq);
        map.put("fatal", Boolean.valueOf(this.zzur));
        return com.google.android.gms.analytics.zzi.zza((Object) map);
    }

    @Override // com.google.android.gms.analytics.zzi
    public final /* synthetic */ void zzb(com.google.android.gms.analytics.zzi zziVar) {
        zzy zzyVar = (zzy) zziVar;
        if (!TextUtils.isEmpty(this.zzuq)) {
            zzyVar.zzuq = this.zzuq;
        }
        boolean z8 = this.zzur;
        if (z8) {
            zzyVar.zzur = z8;
        }
    }
}
