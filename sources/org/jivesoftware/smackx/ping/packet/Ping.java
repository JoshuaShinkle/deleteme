package org.jivesoftware.smackx.ping.packet;

import org.jivesoftware.smack.packet.AbstractC5586IQ;

/* loaded from: classes.dex */
public class Ping extends AbstractC5586IQ {
    public Ping() {
    }

    @Override // org.jivesoftware.smack.packet.AbstractC5586IQ
    /* renamed from: G, reason: merged with bridge method [inline-methods] */
    public String mo9675y() {
        return "<ping xmlns='urn:xmpp:ping' />";
    }

    public Ping(String str) {
        m22167t(str);
        m22070F(AbstractC5586IQ.a.f19231b);
    }
}
