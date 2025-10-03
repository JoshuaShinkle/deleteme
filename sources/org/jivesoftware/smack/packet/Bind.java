package org.jivesoftware.smack.packet;

import org.jivesoftware.smack.packet.AbstractC5586IQ;
import org.jivesoftware.smack.util.C5618l;

/* loaded from: classes.dex */
public class Bind extends AbstractC5586IQ {

    /* renamed from: p */
    public String f19219p = null;

    /* renamed from: q */
    public String f19220q = null;

    public Bind() {
        m22070F(AbstractC5586IQ.a.f19232c);
    }

    /* renamed from: G */
    public String m22050G() {
        return this.f19220q;
    }

    /* renamed from: H */
    public void m22051H(String str) {
        this.f19220q = str;
    }

    /* renamed from: I */
    public void m22052I(String str) {
        this.f19219p = str;
    }

    @Override // org.jivesoftware.smack.packet.AbstractC5586IQ
    /* renamed from: y */
    public CharSequence mo9675y() {
        C5618l c5618l = new C5618l();
        c5618l.m22364o("bind");
        c5618l.m22372w("urn:ietf:params:xml:ns:xmpp-bind");
        c5618l.m22370u();
        c5618l.m22368s("resource", this.f19219p);
        c5618l.m22368s("jid", this.f19220q);
        c5618l.m22356g("bind");
        return c5618l;
    }
}
