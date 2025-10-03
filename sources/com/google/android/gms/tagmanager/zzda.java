package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.gtm.zzot;
import com.google.android.gms.internal.gtm.zzov;
import com.google.android.gms.internal.gtm.zzow;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes2.dex */
final class zzda {
    public static zzov zzbf(String str) {
        com.google.android.gms.internal.gtm.zzl zzlVarZzi = zzgj.zzi(zzg(new JSONObject(str)));
        zzow zzowVarZzmn = zzov.zzmn();
        for (int i9 = 0; i9 < zzlVarZzi.zzqo.length; i9++) {
            zzowVarZzmn.zzc(zzot.zzml().zzb(com.google.android.gms.internal.gtm.zzb.INSTANCE_NAME.toString(), zzlVarZzi.zzqo[i9]).zzb(com.google.android.gms.internal.gtm.zzb.FUNCTION.toString(), zzgj.zzbp(zzt.zzgy())).zzb(zzt.zzgz(), zzlVarZzi.zzqp[i9]).zzmm());
        }
        return zzowVarZzmn.zzmp();
    }

    @VisibleForTesting
    private static Object zzg(Object obj) {
        if (obj instanceof JSONArray) {
            throw new RuntimeException("JSONArrays are not supported");
        }
        if (JSONObject.NULL.equals(obj)) {
            throw new RuntimeException("JSON nulls are not supported");
        }
        if (!(obj instanceof JSONObject)) {
            return obj;
        }
        JSONObject jSONObject = (JSONObject) obj;
        HashMap map = new HashMap();
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            map.put(next, zzg(jSONObject.get(next)));
        }
        return map;
    }
}
