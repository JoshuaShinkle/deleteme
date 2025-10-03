package com.google.android.gms.internal.gtm;

import android.text.TextUtils;
import com.google.android.gms.common.internal.ShowFirstParty;
import java.util.HashMap;

@ShowFirstParty
/* loaded from: classes2.dex */
public final class zzab extends com.google.android.gms.analytics.zzi<zzab> {
    public String zzvh;
    public String zzvi;
    public String zzvj;

    public final String toString() {
        HashMap map = new HashMap();
        map.put("network", this.zzvh);
        map.put("action", this.zzvi);
        map.put("target", this.zzvj);
        return com.google.android.gms.analytics.zzi.zza((Object) map);
    }

    @Override // com.google.android.gms.analytics.zzi
    public final /* synthetic */ void zzb(com.google.android.gms.analytics.zzi zziVar) {
        zzab zzabVar = (zzab) zziVar;
        if (!TextUtils.isEmpty(this.zzvh)) {
            zzabVar.zzvh = this.zzvh;
        }
        if (!TextUtils.isEmpty(this.zzvi)) {
            zzabVar.zzvi = this.zzvi;
        }
        if (TextUtils.isEmpty(this.zzvj)) {
            return;
        }
        zzabVar.zzvj = this.zzvj;
    }
}
