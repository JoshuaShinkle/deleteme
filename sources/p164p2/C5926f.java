package p164p2;

import com.cyberlink.you.database.C2973l0;
import com.cyberlink.you.utility.ULogUtility;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: p2.f */
/* loaded from: classes.dex */
public class C5926f {

    /* renamed from: a */
    public JSONObject f20423a;

    public C5926f(C2973l0 c2973l0) throws JSONException {
        String str;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("mediaId", Long.toString(c2973l0.m15144p()));
            String strM15147s = c2973l0.m15147s();
            jSONObject.put("mediaType", strM15147s);
            jSONObject.put("albumId", c2973l0.m15131c());
            jSONObject.put("thumbnail", c2973l0.m15149u().f13200d);
            jSONObject.put("original", c2973l0.m15148t().f13200d);
            jSONObject.put("width", c2973l0.m15151w());
            jSONObject.put("height", c2973l0.m15141m());
            jSONObject.put("mediaSize", c2973l0.m15148t().f13197a);
            jSONObject.put("expirationTime", c2973l0.m15140l());
            jSONObject.put("mediaName", c2973l0.m15145q());
            if (strM15147s.compareTo("Video") == 0 && (str = c2973l0.m15148t().f13203g) != null && str.compareTo("") != 0) {
                jSONObject.put("duration", Long.parseLong(str, 10));
            }
            this.f20423a = jSONObject;
        } catch (JSONException unused) {
            ULogUtility.m16676l("MediaTAG", "Cannot generate media from mediaObj.");
        }
    }

    /* renamed from: a */
    public String m23379a() {
        return m23388j("albumId");
    }

    /* renamed from: b */
    public int m23380b() {
        return m23381c("height");
    }

    /* renamed from: c */
    public final int m23381c(String str) {
        try {
            return this.f20423a.getInt(str);
        } catch (JSONException unused) {
            return 0;
        }
    }

    /* renamed from: d */
    public final long m23382d(String str) {
        try {
            return this.f20423a.getLong(str);
        } catch (JSONException unused) {
            return 0L;
        }
    }

    /* renamed from: e */
    public long m23383e() {
        return Long.parseLong(m23388j("mediaId"));
    }

    /* renamed from: f */
    public String m23384f() {
        return m23388j("mediaName");
    }

    /* renamed from: g */
    public long m23385g() {
        return m23382d("mediaSize");
    }

    /* renamed from: h */
    public String m23386h() {
        return m23388j("mediaType");
    }

    /* renamed from: i */
    public String m23387i() {
        return m23388j("original");
    }

    /* renamed from: j */
    public final String m23388j(String str) {
        try {
            return this.f20423a.getString(str);
        } catch (JSONException unused) {
            return null;
        }
    }

    /* renamed from: k */
    public String m23389k() {
        return m23388j("thumbnail");
    }

    /* renamed from: l */
    public int m23390l() {
        return m23381c("width");
    }

    /* renamed from: m */
    public String m23391m() {
        return this.f20423a.toString();
    }

    /* renamed from: n */
    public C2973l0 m23392n() {
        C2973l0.a aVar = new C2973l0.a();
        aVar.f13200d = m23387i();
        C2973l0.a aVar2 = new C2973l0.a();
        aVar2.f13200d = m23389k();
        return new C2973l0(-1L, m23379a(), m23383e(), m23384f(), "", m23386h(), -1L, "", aVar2, aVar, m23390l(), m23380b(), 0, 0, 0, 0, 0L);
    }

    public C5926f(String str) {
        try {
            this.f20423a = new JSONObject(str);
        } catch (JSONException unused) {
            ULogUtility.m16676l("MediaTAG", "Cannot generate media from this string.");
        }
    }
}
