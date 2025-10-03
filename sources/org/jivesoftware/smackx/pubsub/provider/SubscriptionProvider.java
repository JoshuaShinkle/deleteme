package org.jivesoftware.smackx.pubsub.provider;

import java.io.IOException;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.jivesoftware.smackx.pubsub.Subscription;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p008a7.InterfaceC0051e;

/* loaded from: classes.dex */
public class SubscriptionProvider implements InterfaceC0051e {
    @Override // p008a7.InterfaceC0051e
    /* renamed from: a */
    public InterfaceC5595c mo181a(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        String attributeValue = xmlPullParser.getAttributeValue(null, "jid");
        String attributeValue2 = xmlPullParser.getAttributeValue(null, "node");
        String attributeValue3 = xmlPullParser.getAttributeValue(null, "subid");
        String attributeValue4 = xmlPullParser.getAttributeValue(null, "subscription");
        boolean z8 = false;
        if (xmlPullParser.next() == 2 && xmlPullParser.getName().equals("subscribe-options")) {
            if (xmlPullParser.next() == 2 && xmlPullParser.getName().equals("required")) {
                z8 = true;
            }
            while (xmlPullParser.next() != 3 && xmlPullParser.getName() != "subscribe-options") {
            }
        }
        while (xmlPullParser.getEventType() != 3) {
            xmlPullParser.next();
        }
        return new Subscription(attributeValue, attributeValue2, attributeValue3, attributeValue4 != null ? Subscription.State.valueOf(attributeValue4) : null, z8);
    }
}
