package org.jivesoftware.smackx.bytestreams.ibb.packet;

import org.jivesoftware.smack.packet.AbstractC5586IQ;
import p089h7.C5029a;

/* loaded from: classes.dex */
public class Data extends AbstractC5586IQ {

    /* renamed from: p */
    public final C5029a f19579p;

    public Data(C5029a c5029a) {
        if (c5029a == null) {
            throw new IllegalArgumentException("Data must not be null");
        }
        this.f19579p = c5029a;
        m22154b(c5029a);
        m22070F(AbstractC5586IQ.a.f19232c);
    }

    @Override // org.jivesoftware.smack.packet.AbstractC5586IQ
    /* renamed from: G, reason: merged with bridge method [inline-methods] */
    public String mo9675y() {
        return this.f19579p.mo190a();
    }

    /* renamed from: H */
    public C5029a m22398H() {
        return this.f19579p;
    }
}
