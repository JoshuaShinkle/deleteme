package org.jivesoftware.smackx.hoxt.provider;

import java.io.IOException;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p008a7.InterfaceC0051e;
import p158o7.C5476a;

/* loaded from: classes.dex */
public class Base64BinaryChunkProvider implements InterfaceC0051e {
    @Override // p008a7.InterfaceC0051e
    /* renamed from: a */
    public InterfaceC5595c mo181a(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        String attributeValue = xmlPullParser.getAttributeValue("", "streamId");
        String attributeValue2 = xmlPullParser.getAttributeValue("", "last");
        boolean z8 = false;
        boolean z9 = attributeValue2 != null ? Boolean.parseBoolean(attributeValue2) : false;
        String text = null;
        while (!z8) {
            int next = xmlPullParser.next();
            if (next == 3) {
                if (!xmlPullParser.getName().equals("chunk")) {
                    throw new IllegalArgumentException("unexpected end tag of: " + xmlPullParser.getName());
                }
                z8 = true;
            } else {
                if (next != 4) {
                    throw new IllegalArgumentException("unexpected eventType: " + next);
                }
                text = xmlPullParser.getText();
            }
        }
        return new C5476a(text, attributeValue, z9);
    }
}
