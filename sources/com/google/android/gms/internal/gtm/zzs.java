package com.google.android.gms.internal.gtm;

import com.google.android.gms.common.internal.ShowFirstParty;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ShowFirstParty
/* loaded from: classes2.dex */
public final class zzs extends com.google.android.gms.analytics.zzi<zzs> {
    private Map<Integer, String> zzuh = new HashMap(4);

    public final String toString() {
        HashMap map = new HashMap();
        for (Map.Entry<Integer, String> entry : this.zzuh.entrySet()) {
            String strValueOf = String.valueOf(entry.getKey());
            StringBuilder sb = new StringBuilder(strValueOf.length() + 9);
            sb.append("dimension");
            sb.append(strValueOf);
            map.put(sb.toString(), entry.getValue());
        }
        return com.google.android.gms.analytics.zzi.zza((Object) map);
    }

    @Override // com.google.android.gms.analytics.zzi
    public final /* synthetic */ void zzb(com.google.android.gms.analytics.zzi zziVar) {
        ((zzs) zziVar).zzuh.putAll(this.zzuh);
    }

    public final Map<Integer, String> zzbk() {
        return Collections.unmodifiableMap(this.zzuh);
    }
}
