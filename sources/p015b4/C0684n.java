package p015b4;

import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: b4.n */
/* loaded from: classes.dex */
public class C0684n {

    /* renamed from: a */
    public String f3300a;

    /* renamed from: b */
    public String f3301b;

    /* renamed from: c */
    public Boolean f3302c;

    /* renamed from: d */
    public String f3303d;

    /* renamed from: e */
    public Boolean f3304e;

    /* renamed from: f */
    public Boolean f3305f;

    /* renamed from: g */
    public String f3306g;

    /* renamed from: h */
    public String f3307h;

    public C0684n(String str, String str2, Boolean bool, String str3, Boolean bool2, Boolean bool3, String str4, String str5) {
        this.f3300a = str;
        this.f3301b = str2;
        this.f3302c = bool;
        this.f3303d = str3;
        this.f3304e = bool2;
        this.f3305f = bool3;
        this.f3306g = str4;
        this.f3307h = str5;
    }

    /* renamed from: a */
    public static C0684n m3415a(String str) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("token");
            String string2 = jSONObject.getString("douToken");
            Boolean boolValueOf = Boolean.valueOf(jSONObject.getBoolean("hasRegistered"));
            String string3 = jSONObject.getString("douAccountStatus");
            JSONObject jSONObject2 = new JSONObject(jSONObject.getString("info"));
            return new C0684n(string, string2, boolValueOf, string3, Boolean.valueOf(!jSONObject2.has("transcode.enabled") || jSONObject2.getBoolean("transcode.enabled")), Boolean.valueOf(!jSONObject2.has("contact.enabled") || jSONObject2.getBoolean("contact.enabled")), jSONObject2.has("user.site") ? jSONObject2.getString("user.site") : "", jSONObject2.has("init.url") ? jSONObject2.getString("init.url") : "");
        } catch (JSONException e9) {
            throw new IllegalArgumentException("AccountToken parseError : " + str, e9);
        }
    }

    /* renamed from: b */
    public String m3416b() {
        return this.f3303d;
    }

    /* renamed from: c */
    public String m3417c() {
        return this.f3301b;
    }

    /* renamed from: d */
    public String m3418d() {
        return this.f3307h;
    }

    /* renamed from: e */
    public String m3419e() {
        return this.f3300a;
    }

    /* renamed from: f */
    public String m3420f() {
        return this.f3306g;
    }

    /* renamed from: g */
    public Boolean m3421g() {
        return this.f3304e;
    }

    /* renamed from: h */
    public Boolean m3422h() {
        return this.f3302c;
    }
}
