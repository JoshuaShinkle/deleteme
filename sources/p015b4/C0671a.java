package p015b4;

import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: b4.a */
/* loaded from: classes.dex */
public class C0671a {

    /* renamed from: a */
    public String f3277a;

    public C0671a(String str) {
        this.f3277a = str;
    }

    /* renamed from: a */
    public static C0671a m3377a(String str) {
        try {
            return new C0671a(new JSONObject(str).getString("accountToken"));
        } catch (JSONException e9) {
            throw new IllegalArgumentException("AccountToken parseError : " + str, e9);
        }
    }

    /* renamed from: b */
    public String m3378b() {
        return this.f3277a;
    }
}
