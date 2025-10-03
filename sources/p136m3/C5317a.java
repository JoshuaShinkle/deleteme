package p136m3;

import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: m3.a */
/* loaded from: classes.dex */
public class C5317a {

    /* renamed from: a */
    public long f18071a;

    /* renamed from: b */
    public String f18072b;

    public C5317a(long j9, String str) {
        this.f18071a = j9;
        this.f18072b = str;
    }

    /* renamed from: a */
    public JSONObject m20811a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("userId", this.f18071a);
            jSONObject.put("comment", this.f18072b);
        } catch (JSONException e9) {
            e9.printStackTrace();
        }
        return jSONObject;
    }
}
