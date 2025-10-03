package org.jivesoftware.smackx.iqlast.packet;

import com.google.android.gms.actions.SearchIntents;
import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.AbstractC5586IQ;
import org.jivesoftware.smack.util.C5618l;
import org.xmlpull.v1.XmlPullParser;
import p008a7.InterfaceC0049c;

/* loaded from: classes.dex */
public class LastActivity extends AbstractC5586IQ {

    /* renamed from: p */
    public long f19737p = -1;

    /* renamed from: q */
    public String f19738q;

    public static class Provider implements InterfaceC0049c {
        @Override // p008a7.InterfaceC0049c
        /* renamed from: b */
        public AbstractC5586IQ mo180b(XmlPullParser xmlPullParser) throws SmackException {
            if (xmlPullParser.getEventType() != 2) {
                throw new SmackException("Parser not in proper position, or bad XML.");
            }
            LastActivity lastActivity = new LastActivity();
            String attributeValue = xmlPullParser.getAttributeValue("", "seconds");
            if (attributeValue != null) {
                try {
                    lastActivity.m22557I(Long.parseLong(attributeValue));
                } catch (NumberFormatException e9) {
                    throw new SmackException("Could not parse last activity number", e9);
                }
            }
            try {
                lastActivity.m22558J(xmlPullParser.nextText());
                return lastActivity;
            } catch (IOException e10) {
                throw new SmackException(e10);
            }
        }
    }

    public LastActivity() {
        m22070F(AbstractC5586IQ.a.f19231b);
    }

    @Override // org.jivesoftware.smack.packet.AbstractC5586IQ
    /* renamed from: H, reason: merged with bridge method [inline-methods] */
    public C5618l mo9675y() {
        C5618l c5618l = new C5618l();
        c5618l.m22364o(SearchIntents.EXTRA_QUERY);
        c5618l.m22372w("jabber:iq:last");
        long j9 = this.f19737p;
        if (j9 != -1) {
            c5618l.m22355f("seconds", Long.toString(j9));
        }
        c5618l.m22358i();
        return c5618l;
    }

    /* renamed from: I */
    public void m22557I(long j9) {
        this.f19737p = j9;
    }

    /* renamed from: J */
    public final void m22558J(String str) {
        this.f19738q = str;
    }
}
