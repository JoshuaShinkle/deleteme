package org.jivesoftware.smackx.pubsub;

import org.apache.commons.lang3.StringUtils;
import p232w7.C6554g;

/* loaded from: classes.dex */
public class Subscription extends C6554g {

    /* renamed from: c */
    public String f19867c;

    /* renamed from: d */
    public String f19868d;

    /* renamed from: e */
    public State f19869e;

    /* renamed from: f */
    public boolean f19870f;

    public enum State {
        subscribed,
        unconfigured,
        pending,
        none
    }

    public Subscription(String str, String str2, String str3, State state, boolean z8) {
        super(PubSubElementType.f19863u, str2);
        this.f19867c = str;
        this.f19868d = str3;
        this.f19869e = state;
        this.f19870f = z8;
    }

    /* renamed from: d */
    public final void m22688d(StringBuilder sb, String str, String str2) {
        sb.append(StringUtils.SPACE);
        sb.append(str);
        sb.append("='");
        sb.append(str2);
        sb.append("'");
    }

    @Override // p232w7.C6554g, org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public String mo190a() {
        StringBuilder sb = new StringBuilder("<subscription");
        m22688d(sb, "jid", this.f19867c);
        if (m25121c() != null) {
            m22688d(sb, "node", m25121c());
        }
        String str = this.f19868d;
        if (str != null) {
            m22688d(sb, "subid", str);
        }
        State state = this.f19869e;
        if (state != null) {
            m22688d(sb, "subscription", state.toString());
        }
        sb.append("/>");
        return sb.toString();
    }
}
