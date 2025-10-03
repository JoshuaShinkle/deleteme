package com.android.billingclient.api;

import android.text.TextUtils;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class Purchase {

    /* renamed from: a */
    public final String f3506a;

    /* renamed from: b */
    public final String f3507b;

    /* renamed from: c */
    public final JSONObject f3508c;

    public Purchase(String str, String str2) {
        this.f3506a = str;
        this.f3507b = str2;
        this.f3508c = new JSONObject(str);
    }

    /* renamed from: a */
    public String m3640a() {
        return this.f3506a;
    }

    /* renamed from: b */
    public int m3641b() {
        return this.f3508c.optInt("purchaseState", 1) != 4 ? 1 : 2;
    }

    /* renamed from: c */
    public String m3642c() {
        JSONObject jSONObject = this.f3508c;
        return jSONObject.optString("token", jSONObject.optString("purchaseToken"));
    }

    /* renamed from: d */
    public String m3643d() {
        return this.f3507b;
    }

    @Deprecated
    /* renamed from: e */
    public ArrayList<String> m3644e() {
        return m3646g();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Purchase)) {
            return false;
        }
        Purchase purchase = (Purchase) obj;
        return TextUtils.equals(this.f3506a, purchase.m3640a()) && TextUtils.equals(this.f3507b, purchase.m3643d());
    }

    /* renamed from: f */
    public boolean m3645f() {
        return this.f3508c.optBoolean("acknowledged", true);
    }

    /* renamed from: g */
    public final ArrayList m3646g() {
        ArrayList arrayList = new ArrayList();
        if (this.f3508c.has("productIds")) {
            JSONArray jSONArrayOptJSONArray = this.f3508c.optJSONArray("productIds");
            if (jSONArrayOptJSONArray != null) {
                for (int i9 = 0; i9 < jSONArrayOptJSONArray.length(); i9++) {
                    arrayList.add(jSONArrayOptJSONArray.optString(i9));
                }
            }
        } else if (this.f3508c.has("productId")) {
            arrayList.add(this.f3508c.optString("productId"));
        }
        return arrayList;
    }

    public int hashCode() {
        return this.f3506a.hashCode();
    }

    public String toString() {
        return "Purchase. Json: ".concat(String.valueOf(this.f3506a));
    }
}
