package org.jivesoftware.smackx.pubsub.packet;

import org.jivesoftware.smack.packet.AbstractC5586IQ;

/* loaded from: classes.dex */
public class PubSub extends AbstractC5586IQ {

    /* renamed from: p */
    public PubSubNamespace f19876p = PubSubNamespace.BASIC;

    @Override // org.jivesoftware.smack.packet.AbstractC5586IQ
    /* renamed from: G, reason: merged with bridge method [inline-methods] */
    public String mo9675y() {
        return "<" + m22691H() + " xmlns=\"" + m22692I() + "\">" + m22159i() + "</" + m22691H() + ">";
    }

    /* renamed from: H */
    public String m22691H() {
        return "pubsub";
    }

    /* renamed from: I */
    public String m22692I() {
        return this.f19876p.m22695a();
    }

    /* renamed from: J */
    public void m22693J(PubSubNamespace pubSubNamespace) {
        this.f19876p = pubSubNamespace;
    }
}
