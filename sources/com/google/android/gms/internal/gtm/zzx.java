package com.google.android.gms.internal.gtm;

import android.text.TextUtils;
import com.google.android.gms.common.internal.ShowFirstParty;
import java.util.HashMap;

@ShowFirstParty
/* loaded from: classes2.dex */
public final class zzx extends com.google.android.gms.analytics.zzi<zzx> {
    private String category;
    private String label;
    private long value;
    private String zzup;

    public final String getAction() {
        return this.zzup;
    }

    public final String getLabel() {
        return this.label;
    }

    public final long getValue() {
        return this.value;
    }

    public final String toString() {
        HashMap map = new HashMap();
        map.put("category", this.category);
        map.put("action", this.zzup);
        map.put("label", this.label);
        map.put("value", Long.valueOf(this.value));
        return com.google.android.gms.analytics.zzi.zza((Object) map);
    }

    @Override // com.google.android.gms.analytics.zzi
    public final /* synthetic */ void zzb(com.google.android.gms.analytics.zzi zziVar) {
        zzx zzxVar = (zzx) zziVar;
        if (!TextUtils.isEmpty(this.category)) {
            zzxVar.category = this.category;
        }
        if (!TextUtils.isEmpty(this.zzup)) {
            zzxVar.zzup = this.zzup;
        }
        if (!TextUtils.isEmpty(this.label)) {
            zzxVar.label = this.label;
        }
        long j9 = this.value;
        if (j9 != 0) {
            zzxVar.value = j9;
        }
    }

    public final String zzbr() {
        return this.category;
    }
}
