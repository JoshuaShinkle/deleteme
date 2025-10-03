package com.google.android.gms.internal.measurement;

import android.net.Uri;
import java.util.Map;

/* loaded from: classes2.dex */
public final class zzcy {
    private final Map<String, Map<String, String>> zza;

    public zzcy(Map<String, Map<String, String>> map) {
        this.zza = map;
    }

    public final String zza(Uri uri, String str, String str2, String str3) {
        if (uri == null) {
            return null;
        }
        Map<String, String> map = this.zza.get(uri.toString());
        if (map == null) {
            return null;
        }
        if (str2 != null) {
            String strValueOf = String.valueOf(str3);
            str3 = strValueOf.length() != 0 ? str2.concat(strValueOf) : new String(str2);
        }
        return map.get(str3);
    }
}
