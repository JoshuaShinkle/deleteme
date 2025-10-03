package org.jivesoftware.smackx.bytestreams.ibb.packet;

import java.util.Locale;
import org.jivesoftware.smack.packet.AbstractC5586IQ;
import org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamManager;

/* loaded from: classes.dex */
public class Open extends AbstractC5586IQ {

    /* renamed from: p */
    public final String f19580p;

    /* renamed from: q */
    public final int f19581q;

    /* renamed from: r */
    public final InBandBytestreamManager.StanzaType f19582r;

    public Open(String str, int i9, InBandBytestreamManager.StanzaType stanzaType) {
        if (str == null || "".equals(str)) {
            throw new IllegalArgumentException("Session ID must not be null or empty");
        }
        if (i9 <= 0) {
            throw new IllegalArgumentException("Block size must be greater than zero");
        }
        this.f19580p = str;
        this.f19581q = i9;
        this.f19582r = stanzaType;
        m22070F(AbstractC5586IQ.a.f19232c);
    }

    /* renamed from: G */
    public int m22399G() {
        return this.f19581q;
    }

    @Override // org.jivesoftware.smack.packet.AbstractC5586IQ
    /* renamed from: H, reason: merged with bridge method [inline-methods] */
    public String mo9675y() {
        return "<open xmlns=\"http://jabber.org/protocol/ibb\" block-size=\"" + this.f19581q + "\" sid=\"" + this.f19580p + "\" stanza=\"" + this.f19582r.toString().toLowerCase(Locale.US) + "\"/>";
    }

    /* renamed from: I */
    public String m22401I() {
        return this.f19580p;
    }
}
