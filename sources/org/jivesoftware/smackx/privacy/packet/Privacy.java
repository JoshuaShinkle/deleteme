package org.jivesoftware.smackx.privacy.packet;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jivesoftware.smack.packet.AbstractC5586IQ;

/* loaded from: classes.dex */
public class Privacy extends AbstractC5586IQ {

    /* renamed from: q */
    public String f19802q;

    /* renamed from: s */
    public String f19804s;

    /* renamed from: p */
    public boolean f19801p = false;

    /* renamed from: r */
    public boolean f19803r = false;

    /* renamed from: t */
    public Map<String, List<PrivacyItem>> f19805t = new HashMap();

    /* renamed from: G */
    public String m22652G() {
        return this.f19802q;
    }

    @Override // org.jivesoftware.smack.packet.AbstractC5586IQ
    /* renamed from: H, reason: merged with bridge method [inline-methods] */
    public String mo9675y() {
        StringBuilder sb = new StringBuilder();
        sb.append("<query xmlns=\"jabber:iq:privacy\">");
        if (m22656K()) {
            sb.append("<active/>");
        } else if (m22652G() != null) {
            sb.append("<active name=\"");
            sb.append(m22652G());
            sb.append("\"/>");
        }
        if (m22657L()) {
            sb.append("<default/>");
        } else if (m22654I() != null) {
            sb.append("<default name=\"");
            sb.append(m22654I());
            sb.append("\"/>");
        }
        for (Map.Entry<String, List<PrivacyItem>> entry : m22655J().entrySet()) {
            String key = entry.getKey();
            List<PrivacyItem> value = entry.getValue();
            if (value.isEmpty()) {
                sb.append("<list name=\"");
                sb.append(key);
                sb.append("\"/>");
            } else {
                sb.append("<list name=\"");
                sb.append(key);
                sb.append("\">");
            }
            Iterator<PrivacyItem> it = value.iterator();
            while (it.hasNext()) {
                sb.append(it.next().m22676n());
            }
            if (!value.isEmpty()) {
                sb.append("</list>");
            }
        }
        sb.append(m22159i());
        sb.append("</query>");
        return sb.toString();
    }

    /* renamed from: I */
    public String m22654I() {
        return this.f19804s;
    }

    /* renamed from: J */
    public Map<String, List<PrivacyItem>> m22655J() {
        return this.f19805t;
    }

    /* renamed from: K */
    public boolean m22656K() {
        return this.f19801p;
    }

    /* renamed from: L */
    public boolean m22657L() {
        return this.f19803r;
    }

    /* renamed from: M */
    public void m22658M(String str) {
        this.f19802q = str;
    }

    /* renamed from: N */
    public void m22659N(boolean z8) {
        this.f19801p = z8;
    }

    /* renamed from: O */
    public void m22660O(boolean z8) {
        this.f19803r = z8;
    }

    /* renamed from: P */
    public void m22661P(String str) {
        this.f19804s = str;
    }

    /* renamed from: Q */
    public List<PrivacyItem> m22662Q(String str, List<PrivacyItem> list) {
        m22655J().put(str, list);
        return list;
    }
}
