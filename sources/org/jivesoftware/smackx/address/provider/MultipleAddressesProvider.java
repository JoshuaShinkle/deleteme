package org.jivesoftware.smackx.address.provider;

import java.io.IOException;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p008a7.InterfaceC0051e;
import p049d7.C4685a;

/* loaded from: classes.dex */
public class MultipleAddressesProvider implements InterfaceC0051e {
    @Override // p008a7.InterfaceC0051e
    /* renamed from: a */
    public InterfaceC5595c mo181a(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        C4685a c4685a = new C4685a();
        boolean z8 = false;
        while (true) {
            boolean z9 = z8;
            while (!z9) {
                int next = xmlPullParser.next();
                if (next == 2) {
                    if (xmlPullParser.getName().equals("address")) {
                        c4685a.m18729c(xmlPullParser.getAttributeValue("", "type"), xmlPullParser.getAttributeValue("", "jid"), xmlPullParser.getAttributeValue("", "node"), xmlPullParser.getAttributeValue("", "desc"), "true".equals(xmlPullParser.getAttributeValue("", "delivered")), xmlPullParser.getAttributeValue("", "uri"));
                    }
                } else if (next != 3 || !xmlPullParser.getName().equals(c4685a.mo191b())) {
                }
            }
            return c4685a;
            z8 = true;
        }
    }
}
