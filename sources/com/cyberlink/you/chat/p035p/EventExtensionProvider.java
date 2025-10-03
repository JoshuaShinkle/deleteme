package com.cyberlink.you.chat.p035p;

import com.cyberlink.you.chat.C2898i;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p008a7.InterfaceC0051e;

/* loaded from: classes.dex */
public class EventExtensionProvider implements InterfaceC0051e {
    @Override // p008a7.InterfaceC0051e
    /* renamed from: a */
    public InterfaceC5595c mo181a(XmlPullParser xmlPullParser) throws XmlPullParserException {
        C2898i c2898i = new C2898i("event", "urn:xmpp:custom:event");
        int eventType = xmlPullParser.getEventType();
        if (eventType != 3 && eventType == 2) {
            for (int i9 = 0; i9 < xmlPullParser.getAttributeCount(); i9++) {
                c2898i.m14375g(xmlPullParser.getAttributeName(i9), xmlPullParser.getAttributeValue(i9));
            }
        }
        return c2898i;
    }
}
