package p086h4;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: h4.u */
/* loaded from: classes.dex */
public class C5017u {

    /* renamed from: a */
    public long f17302a;

    /* renamed from: b */
    public String f17303b;

    /* renamed from: c */
    public String f17304c;

    /* renamed from: d */
    public String f17305d;

    /* renamed from: e */
    public int f17306e;

    /* renamed from: a */
    public static List<C5017u> m19531a(String str) throws JSONException {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONObject(str).getJSONArray("results");
            int length = jSONArray.length();
            for (int i9 = 0; i9 < length; i9++) {
                arrayList.add(m19532c(jSONArray.getJSONObject(i9)));
            }
        } catch (JSONException e9) {
            Log.e("StickerPack", "[fromJsonArray]", e9);
        }
        return arrayList;
    }

    /* renamed from: c */
    public static C5017u m19532c(JSONObject jSONObject) {
        C5017u c5017u = new C5017u();
        c5017u.f17302a = jSONObject.getLong("packId");
        c5017u.f17303b = jSONObject.isNull("expirationDate") ? "" : jSONObject.getString("expirationDate");
        c5017u.f17304c = jSONObject.isNull("purchaseDate") ? "" : jSONObject.getString("purchaseDate");
        c5017u.f17305d = jSONObject.isNull("purchaseType") ? "" : jSONObject.getString("purchaseType");
        c5017u.f17306e = jSONObject.isNull("packOrder") ? 0 : jSONObject.getInt("packOrder");
        return c5017u;
    }

    /* renamed from: b */
    public long m19533b() {
        return this.f17302a;
    }

    public String toString() {
        return "StickerPack{id=" + this.f17302a + ", expirationDate='" + this.f17303b + "', purchaseDate='" + this.f17304c + "', purchaseType='" + this.f17305d + "', packOrder=" + this.f17306e + '}';
    }
}
