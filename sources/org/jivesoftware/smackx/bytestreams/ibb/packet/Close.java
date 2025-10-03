package org.jivesoftware.smackx.bytestreams.ibb.packet;

import org.jivesoftware.smack.packet.AbstractC5586IQ;

/* loaded from: classes.dex */
public class Close extends AbstractC5586IQ {

    /* renamed from: p */
    public final String f19578p;

    public Close(String str) {
        if (str == null || "".equals(str)) {
            throw new IllegalArgumentException("Session ID must not be null or empty");
        }
        this.f19578p = str;
        m22070F(AbstractC5586IQ.a.f19232c);
    }

    @Override // org.jivesoftware.smack.packet.AbstractC5586IQ
    /* renamed from: G, reason: merged with bridge method [inline-methods] */
    public String mo9675y() {
        return "<close xmlns=\"http://jabber.org/protocol/ibb\" sid=\"" + this.f19578p + "\"/>";
    }

    /* renamed from: H */
    public String m22396H() {
        return this.f19578p;
    }
}
