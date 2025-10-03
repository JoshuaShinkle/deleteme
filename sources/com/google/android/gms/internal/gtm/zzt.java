package com.google.android.gms.internal.gtm;

import android.annotation.SuppressLint;
import com.google.android.gms.common.internal.ShowFirstParty;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ShowFirstParty
/* loaded from: classes2.dex */
public final class zzt extends com.google.android.gms.analytics.zzi<zzt> {
    private Map<Integer, Double> zzui = new HashMap(4);

    @SuppressLint({"UseSparseArrays"})
    public zzt() {
    }

    public final String toString() {
        HashMap map = new HashMap();
        for (Map.Entry<Integer, Double> entry : this.zzui.entrySet()) {
            String strValueOf = String.valueOf(entry.getKey());
            StringBuilder sb = new StringBuilder(strValueOf.length() + 6);
            sb.append("metric");
            sb.append(strValueOf);
            map.put(sb.toString(), entry.getValue());
        }
        return com.google.android.gms.analytics.zzi.zza((Object) map);
    }

    @Override // com.google.android.gms.analytics.zzi
    public final /* synthetic */ void zzb(com.google.android.gms.analytics.zzi zziVar) {
        ((zzt) zziVar).zzui.putAll(this.zzui);
    }

    public final Map<Integer, Double> zzbl() {
        return Collections.unmodifiableMap(this.zzui);
    }
}
