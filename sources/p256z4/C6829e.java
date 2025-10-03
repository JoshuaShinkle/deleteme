package p256z4;

import java.security.SecureRandom;
import org.json.JSONException;
import org.json.JSONObject;
import p006a5.C0035c;

/* renamed from: z4.e */
/* loaded from: classes2.dex */
public class C6829e {

    /* renamed from: a */
    public long f22706a;

    /* renamed from: b */
    public long f22707b;

    /* renamed from: c */
    public long f22708c;

    /* renamed from: d */
    public String f22709d;

    /* renamed from: e */
    public final SecureRandom f22710e;

    public C6829e() {
        m25530d();
        this.f22710e = new SecureRandom();
    }

    /* renamed from: a */
    public JSONObject m25527a() {
        return m25529c(true);
    }

    /* renamed from: b */
    public JSONObject m25528b() {
        return m25529c(false);
    }

    /* renamed from: c */
    public final JSONObject m25529c(boolean z8) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("$mp_event_id", Long.toHexString(this.f22710e.nextLong()));
            jSONObject.put("$mp_session_id", this.f22709d);
            jSONObject.put("$mp_session_seq_id", z8 ? this.f22706a : this.f22707b);
            jSONObject.put("$mp_session_start_sec", this.f22708c);
            if (z8) {
                this.f22706a++;
            } else {
                this.f22707b++;
            }
        } catch (JSONException e9) {
            C0035c.m142d(C6825a.f22663a, "Cannot create session metadata JSON object", e9);
        }
        return jSONObject;
    }

    /* renamed from: d */
    public void m25530d() {
        this.f22706a = 0L;
        this.f22707b = 0L;
        this.f22709d = Long.toHexString(new SecureRandom().nextLong());
        this.f22708c = System.currentTimeMillis() / 1000;
    }
}
