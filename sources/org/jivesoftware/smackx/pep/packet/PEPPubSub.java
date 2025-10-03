package org.jivesoftware.smackx.pep.packet;

import org.jivesoftware.smack.packet.AbstractC5586IQ;

/* loaded from: classes.dex */
public class PEPPubSub extends AbstractC5586IQ {
    @Override // org.jivesoftware.smack.packet.AbstractC5586IQ
    /* renamed from: G, reason: merged with bridge method [inline-methods] */
    public String mo9675y() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(m22627H());
        sb.append(" xmlns=\"");
        sb.append(m22628I());
        sb.append("\">");
        sb.append("<publish node=\"");
        throw null;
    }

    /* renamed from: H */
    public String m22627H() {
        return "pubsub";
    }

    /* renamed from: I */
    public String m22628I() {
        return "http://jabber.org/protocol/pubsub";
    }
}
