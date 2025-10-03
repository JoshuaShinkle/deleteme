package com.android.billingclient.api;

import android.text.TextUtils;
import com.google.firebase.analytics.FirebaseAnalytics;
import org.json.JSONObject;

@Deprecated
/* loaded from: classes.dex */
public class SkuDetails {

    /* renamed from: a */
    public final String f3509a;

    /* renamed from: b */
    public final JSONObject f3510b;

    public SkuDetails(String str) {
        this.f3509a = str;
        JSONObject jSONObject = new JSONObject(str);
        this.f3510b = jSONObject;
        if (TextUtils.isEmpty(jSONObject.optString("productId"))) {
            throw new IllegalArgumentException("SKU cannot be empty.");
        }
        if (TextUtils.isEmpty(jSONObject.optString("type"))) {
            throw new IllegalArgumentException("SkuType cannot be empty.");
        }
    }

    /* renamed from: a */
    public String m3647a() {
        return this.f3510b.optString("freeTrialPeriod");
    }

    /* renamed from: b */
    public String m3648b() {
        return this.f3509a;
    }

    /* renamed from: c */
    public String m3649c() {
        return this.f3510b.optString(FirebaseAnalytics.Param.PRICE);
    }

    /* renamed from: d */
    public long m3650d() {
        return this.f3510b.optLong("price_amount_micros");
    }

    /* renamed from: e */
    public String m3651e() {
        return this.f3510b.optString("productId");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SkuDetails) {
            return TextUtils.equals(this.f3509a, ((SkuDetails) obj).f3509a);
        }
        return false;
    }

    /* renamed from: f */
    public String m3652f() {
        return this.f3510b.optString("subscriptionPeriod");
    }

    /* renamed from: g */
    public String m3653g() {
        return this.f3510b.optString("type");
    }

    /* renamed from: h */
    public int m3654h() {
        return this.f3510b.optInt("offer_type");
    }

    public int hashCode() {
        return this.f3509a.hashCode();
    }

    /* renamed from: i */
    public String m3655i() {
        return this.f3510b.optString("offer_id");
    }

    /* renamed from: j */
    public String m3656j() {
        String strOptString = this.f3510b.optString("offerIdToken");
        return strOptString.isEmpty() ? this.f3510b.optString("offer_id_token") : strOptString;
    }

    /* renamed from: k */
    public final String m3657k() {
        return this.f3510b.optString("packageName");
    }

    /* renamed from: l */
    public String m3658l() {
        return this.f3510b.optString("serializedDocid");
    }

    /* renamed from: m */
    public final String m3659m() {
        return this.f3510b.optString("skuDetailsToken");
    }

    public String toString() {
        return "SkuDetails: ".concat(String.valueOf(this.f3509a));
    }
}
