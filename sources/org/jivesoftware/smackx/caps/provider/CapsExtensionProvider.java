package org.jivesoftware.smackx.caps.provider;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p008a7.InterfaceC0051e;
import p109j7.C5105a;

/* loaded from: classes.dex */
public class CapsExtensionProvider implements InterfaceC0051e {
    @Override // p008a7.InterfaceC0051e
    /* renamed from: a */
    public InterfaceC5595c mo181a(XmlPullParser xmlPullParser) throws XmlPullParserException, SmackException, IOException {
        if (xmlPullParser.getEventType() != 2 || !xmlPullParser.getName().equalsIgnoreCase("c")) {
            throw new SmackException("Malformed Caps element");
        }
        String attributeValue = xmlPullParser.getAttributeValue(null, "hash");
        String attributeValue2 = xmlPullParser.getAttributeValue(null, "ver");
        String attributeValue3 = xmlPullParser.getAttributeValue(null, "node");
        xmlPullParser.next();
        if (xmlPullParser.getEventType() != 3 || !xmlPullParser.getName().equalsIgnoreCase("c")) {
            throw new SmackException("Malformed nested Caps element");
        }
        if (attributeValue == null || attributeValue2 == null || attributeValue3 == null) {
            throw new SmackException("Caps elment with missing attributes");
        }
        return new C5105a(attributeValue3, attributeValue2, attributeValue);
    }
}
