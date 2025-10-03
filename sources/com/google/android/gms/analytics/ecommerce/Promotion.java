package com.google.android.gms.analytics.ecommerce;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.analytics.zzi;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashMap;
import java.util.Map;

@VisibleForTesting
/* loaded from: classes2.dex */
public class Promotion {
    public static final String ACTION_CLICK = "click";
    public static final String ACTION_VIEW = "view";
    private Map<String, String> zzvn = new HashMap();

    private final void put(String str, String str2) {
        Preconditions.checkNotNull(str, "Name should be non-null");
        this.zzvn.put(str, str2);
    }

    public Promotion setCreative(String str) {
        put("cr", str);
        return this;
    }

    public Promotion setId(String str) {
        put(TtmlNode.ATTR_ID, str);
        return this;
    }

    public Promotion setName(String str) {
        put("nm", str);
        return this;
    }

    public Promotion setPosition(String str) {
        put("ps", str);
        return this;
    }

    public String toString() {
        return zzi.zza((Map) this.zzvn);
    }

    public final Map<String, String> zzn(String str) {
        HashMap map = new HashMap();
        for (Map.Entry<String, String> entry : this.zzvn.entrySet()) {
            String strValueOf = String.valueOf(str);
            String strValueOf2 = String.valueOf(entry.getKey());
            map.put(strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf), entry.getValue());
        }
        return map;
    }
}
