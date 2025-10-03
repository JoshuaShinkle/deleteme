package org.jivesoftware.smackx.carbons;

import org.jivesoftware.smack.packet.AbstractC5586IQ;

/* loaded from: classes.dex */
class CarbonManager$2 extends AbstractC5586IQ {

    /* renamed from: p */
    public final /* synthetic */ boolean f19616p;

    @Override // org.jivesoftware.smack.packet.AbstractC5586IQ
    /* renamed from: G, reason: merged with bridge method [inline-methods] */
    public String mo9675y() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(this.f19616p ? "enable" : "disable");
        sb.append(" xmlns='");
        sb.append("urn:xmpp:carbons:2");
        sb.append("'/>");
        return sb.toString();
    }
}
