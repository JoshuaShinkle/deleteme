package org.jivesoftware.smack.packet;

import com.google.android.gms.actions.SearchIntents;
import java.util.Map;
import org.jivesoftware.smack.util.C5618l;

/* loaded from: classes.dex */
public class Registration extends AbstractC5586IQ {

    /* renamed from: p */
    public String f19276p = null;

    /* renamed from: q */
    public Map<String, String> f19277q = null;

    @Override // org.jivesoftware.smack.packet.AbstractC5586IQ
    /* renamed from: G, reason: merged with bridge method [inline-methods] */
    public C5618l mo9675y() {
        C5618l c5618l = new C5618l();
        c5618l.m22364o(SearchIntents.EXTRA_QUERY);
        c5618l.m22372w("jabber:iq:register");
        c5618l.m22370u();
        c5618l.m22368s("instructions", this.f19276p);
        Map<String, String> map = this.f19277q;
        if (map != null && map.size() > 0) {
            for (String str : this.f19277q.keySet()) {
                c5618l.m22361l(str, this.f19277q.get(str));
            }
        }
        c5618l.append(m22159i());
        c5618l.m22356g(SearchIntents.EXTRA_QUERY);
        return c5618l;
    }

    /* renamed from: H */
    public void m22125H(Map<String, String> map) {
        this.f19277q = map;
    }

    /* renamed from: I */
    public void m22126I(String str) {
        this.f19276p = str;
    }
}
