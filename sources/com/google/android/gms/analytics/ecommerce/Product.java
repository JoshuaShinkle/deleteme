package com.google.android.gms.analytics.ecommerce;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.analytics.zzd;
import com.google.android.gms.analytics.zzi;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashMap;
import java.util.Map;

@VisibleForTesting
/* loaded from: classes2.dex */
public class Product {
    private Map<String, String> zzvn = new HashMap();

    private final void put(String str, String str2) {
        Preconditions.checkNotNull(str, "Name should be non-null");
        this.zzvn.put(str, str2);
    }

    public Product setBrand(String str) {
        put(TtmlNode.TAG_BR, str);
        return this;
    }

    public Product setCategory(String str) {
        put("ca", str);
        return this;
    }

    public Product setCouponCode(String str) {
        put("cc", str);
        return this;
    }

    public Product setCustomDimension(int i9, String str) {
        put(zzd.zzo(i9), str);
        return this;
    }

    public Product setCustomMetric(int i9, int i10) {
        put(zzd.zzp(i9), Integer.toString(i10));
        return this;
    }

    public Product setId(String str) {
        put(TtmlNode.ATTR_ID, str);
        return this;
    }

    public Product setName(String str) {
        put("nm", str);
        return this;
    }

    public Product setPosition(int i9) {
        put("ps", Integer.toString(i9));
        return this;
    }

    public Product setPrice(double d9) {
        put("pr", Double.toString(d9));
        return this;
    }

    public Product setQuantity(int i9) {
        put("qt", Integer.toString(i9));
        return this;
    }

    public Product setVariant(String str) {
        put("va", str);
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
