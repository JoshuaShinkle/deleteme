package org.jivesoftware.smackx.muc.provider;

import com.google.android.gms.actions.SearchIntents;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.IOException;
import org.jivesoftware.smack.packet.AbstractC5586IQ;
import org.jivesoftware.smackx.muc.packet.MUCAdmin;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p008a7.InterfaceC0049c;

/* loaded from: classes.dex */
public class MUCAdminProvider implements InterfaceC0049c {
    /* renamed from: a */
    public final MUCAdmin.C5657a m22603a(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        MUCAdmin.C5657a c5657a = new MUCAdmin.C5657a(xmlPullParser.getAttributeValue("", FirebaseAnalytics.Param.AFFILIATION), xmlPullParser.getAttributeValue("", "role"));
        c5657a.m22579i(xmlPullParser.getAttributeValue("", "nick"));
        c5657a.m22578h(xmlPullParser.getAttributeValue("", "jid"));
        boolean z8 = false;
        while (!z8) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("actor")) {
                    c5657a.m22577g(xmlPullParser.getAttributeValue("", "jid"));
                }
                if (xmlPullParser.getName().equals("reason")) {
                    c5657a.m22580j(xmlPullParser.nextText());
                }
            } else if (next == 3 && xmlPullParser.getName().equals("item")) {
                z8 = true;
            }
        }
        return c5657a;
    }

    @Override // p008a7.InterfaceC0049c
    /* renamed from: b */
    public AbstractC5586IQ mo180b(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        MUCAdmin mUCAdmin = new MUCAdmin();
        boolean z8 = false;
        while (!z8) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("item")) {
                    mUCAdmin.m22569G(m22603a(xmlPullParser));
                }
            } else if (next == 3 && xmlPullParser.getName().equals(SearchIntents.EXTRA_QUERY)) {
                z8 = true;
            }
        }
        return mUCAdmin;
    }
}
