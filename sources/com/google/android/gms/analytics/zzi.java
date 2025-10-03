package com.google.android.gms.analytics;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzi;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@VisibleForTesting
/* loaded from: classes2.dex */
public abstract class zzi<T extends zzi> {
    public static String zza(Map map) {
        return zza(map, 1);
    }

    public abstract void zzb(T t8);

    public static String zza(Object obj) {
        return zza(obj, 0);
    }

    private static String zza(Object obj, int i9) {
        if (i9 > 10) {
            return "ERROR: Recursive toString calls";
        }
        if (obj == null) {
            return "";
        }
        if (obj instanceof String) {
            return TextUtils.isEmpty((String) obj) ? "" : obj.toString();
        }
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue() == 0 ? "" : obj.toString();
        }
        if (obj instanceof Long) {
            return ((Long) obj).longValue() == 0 ? "" : obj.toString();
        }
        if (obj instanceof Double) {
            return ((Double) obj).doubleValue() == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? "" : obj.toString();
        }
        if (obj instanceof Boolean) {
            return !((Boolean) obj).booleanValue() ? "" : obj.toString();
        }
        if (obj instanceof List) {
            StringBuilder sb = new StringBuilder();
            if (i9 > 0) {
                sb.append("[");
            }
            int length = sb.length();
            for (Object obj2 : (List) obj) {
                if (sb.length() > length) {
                    sb.append(", ");
                }
                sb.append(zza(obj2, i9 + 1));
            }
            if (i9 > 0) {
                sb.append("]");
            }
            return sb.toString();
        }
        if (obj instanceof Map) {
            StringBuilder sb2 = new StringBuilder();
            boolean z8 = false;
            int length2 = 0;
            for (Map.Entry entry : new TreeMap((Map) obj).entrySet()) {
                String strZza = zza(entry.getValue(), i9 + 1);
                if (!TextUtils.isEmpty(strZza)) {
                    if (i9 > 0 && !z8) {
                        sb2.append("{");
                        length2 = sb2.length();
                        z8 = true;
                    }
                    if (sb2.length() > length2) {
                        sb2.append(", ");
                    }
                    sb2.append((String) entry.getKey());
                    sb2.append('=');
                    sb2.append(strZza);
                }
            }
            if (z8) {
                sb2.append("}");
            }
            return sb2.toString();
        }
        return obj.toString();
    }
}
