package org.jivesoftware.smackx.muc.provider;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import java.io.IOException;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p008a7.InterfaceC0051e;
import p205t7.C6346a;

/* loaded from: classes.dex */
public class MUCUserProvider implements InterfaceC0051e {
    @Override // p008a7.InterfaceC0051e
    /* renamed from: a */
    public InterfaceC5595c mo181a(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        C6346a c6346a = new C6346a();
        boolean z8 = false;
        while (!z8) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("invite")) {
                    c6346a.m24308k(m22608d(xmlPullParser));
                }
                if (xmlPullParser.getName().equals("item")) {
                    c6346a.m24309l(m22609e(xmlPullParser));
                }
                if (xmlPullParser.getName().equals("password")) {
                    c6346a.m24310m(xmlPullParser.nextText());
                }
                if (xmlPullParser.getName().equals("status")) {
                    c6346a.m24311n(new C6346a.e(xmlPullParser.getAttributeValue("", "code")));
                }
                if (xmlPullParser.getName().equals("decline")) {
                    c6346a.m24306i(m22606b(xmlPullParser));
                }
                if (xmlPullParser.getName().equals("destroy")) {
                    c6346a.m24307j(m22607c(xmlPullParser));
                }
            } else if (next == 3 && xmlPullParser.getName().equals("x")) {
                z8 = true;
            }
        }
        return c6346a;
    }

    /* renamed from: b */
    public final C6346a.a m22606b(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        C6346a.a aVar = new C6346a.a();
        aVar.m24316d(xmlPullParser.getAttributeValue("", Constants.MessagePayloadKeys.FROM));
        aVar.m24318f(xmlPullParser.getAttributeValue("", "to"));
        boolean z8 = false;
        while (!z8) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("reason")) {
                    aVar.m24317e(xmlPullParser.nextText());
                }
            } else if (next == 3 && xmlPullParser.getName().equals("decline")) {
                z8 = true;
            }
        }
        return aVar;
    }

    /* renamed from: c */
    public final C6346a.b m22607c(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        C6346a.b bVar = new C6346a.b();
        bVar.m24322c(xmlPullParser.getAttributeValue("", "jid"));
        boolean z8 = false;
        while (!z8) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("reason")) {
                    bVar.m24323d(xmlPullParser.nextText());
                }
            } else if (next == 3 && xmlPullParser.getName().equals("destroy")) {
                z8 = true;
            }
        }
        return bVar;
    }

    /* renamed from: d */
    public final C6346a.c m22608d(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        C6346a.c cVar = new C6346a.c();
        cVar.m24328d(xmlPullParser.getAttributeValue("", Constants.MessagePayloadKeys.FROM));
        cVar.m24330f(xmlPullParser.getAttributeValue("", "to"));
        boolean z8 = false;
        while (!z8) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("reason")) {
                    cVar.m24329e(xmlPullParser.nextText());
                }
            } else if (next == 3 && xmlPullParser.getName().equals("invite")) {
                z8 = true;
            }
        }
        return cVar;
    }

    /* renamed from: e */
    public final C6346a.d m22609e(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        C6346a.d dVar = new C6346a.d(xmlPullParser.getAttributeValue("", FirebaseAnalytics.Param.AFFILIATION), xmlPullParser.getAttributeValue("", "role"));
        dVar.m24340i(xmlPullParser.getAttributeValue("", "nick"));
        dVar.m24339h(xmlPullParser.getAttributeValue("", "jid"));
        boolean z8 = false;
        while (!z8) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("actor")) {
                    dVar.m24338g(xmlPullParser.getAttributeValue("", "jid"));
                }
                if (xmlPullParser.getName().equals("reason")) {
                    dVar.m24341j(xmlPullParser.nextText());
                }
            } else if (next == 3 && xmlPullParser.getName().equals("item")) {
                z8 = true;
            }
        }
        return dVar;
    }
}
