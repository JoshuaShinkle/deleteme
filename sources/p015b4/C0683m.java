package p015b4;

import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: b4.m */
/* loaded from: classes.dex */
public class C0683m {

    /* renamed from: a */
    public String f3295a;

    /* renamed from: b */
    public String f3296b;

    /* renamed from: c */
    public boolean f3297c;

    /* renamed from: d */
    public boolean f3298d;

    /* renamed from: e */
    public boolean f3299e;

    public C0683m(String str, String str2, boolean z8, boolean z9, boolean z10) {
        this.f3295a = str;
        this.f3296b = str2;
        this.f3297c = z8;
        this.f3298d = z9;
        this.f3299e = z10;
    }

    /* renamed from: c */
    public static C0683m m3409c(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            return new C0683m(jSONObject.getString("accountToken"), jSONObject.getString("accountSource"), jSONObject.getBoolean("verifyPin"), jSONObject.getBoolean("verifyEmail"), jSONObject.getBoolean("notifyAlreadyConnected"));
        } catch (JSONException e9) {
            throw new IllegalArgumentException("SignInResponse parseError : " + str, e9);
        }
    }

    /* renamed from: a */
    public String m3410a() {
        return this.f3296b;
    }

    /* renamed from: b */
    public String m3411b() {
        return this.f3295a;
    }

    /* renamed from: d */
    public boolean m3412d() {
        return this.f3299e;
    }

    /* renamed from: e */
    public boolean m3413e() {
        return this.f3298d;
    }

    /* renamed from: f */
    public boolean m3414f() {
        return this.f3297c;
    }

    public String toString() {
        return "SignInResponse{accountToken='" + this.f3295a + "', accountSource='" + this.f3296b + "', verifyPin=" + this.f3297c + ", verifyEmail=" + this.f3298d + ", notifyAlreadyConnected=" + this.f3299e + '}';
    }
}
