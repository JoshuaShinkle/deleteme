package com.google.android.gms.internal.gtm;

import android.text.TextUtils;
import com.google.android.gms.common.internal.ShowFirstParty;
import java.util.HashMap;

@ShowFirstParty
/* loaded from: classes2.dex */
public final class zzac extends com.google.android.gms.analytics.zzi<zzac> {
    public String mCategory;
    public String zzvk;
    public long zzvl;
    public String zzvm;

    public final String toString() {
        HashMap map = new HashMap();
        map.put("variableName", this.zzvk);
        map.put("timeInMillis", Long.valueOf(this.zzvl));
        map.put("category", this.mCategory);
        map.put("label", this.zzvm);
        return com.google.android.gms.analytics.zzi.zza((Object) map);
    }

    @Override // com.google.android.gms.analytics.zzi
    public final /* synthetic */ void zzb(com.google.android.gms.analytics.zzi zziVar) {
        zzac zzacVar = (zzac) zziVar;
        if (!TextUtils.isEmpty(this.zzvk)) {
            zzacVar.zzvk = this.zzvk;
        }
        long j9 = this.zzvl;
        if (j9 != 0) {
            zzacVar.zzvl = j9;
        }
        if (!TextUtils.isEmpty(this.mCategory)) {
            zzacVar.mCategory = this.mCategory;
        }
        if (TextUtils.isEmpty(this.zzvm)) {
            return;
        }
        zzacVar.zzvm = this.zzvm;
    }
}
