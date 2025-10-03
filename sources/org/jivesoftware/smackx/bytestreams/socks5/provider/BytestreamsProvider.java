package org.jivesoftware.smackx.bytestreams.socks5.provider;

import com.google.android.gms.actions.SearchIntents;
import java.io.IOException;
import org.jivesoftware.smack.packet.AbstractC5586IQ;
import org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p008a7.InterfaceC0049c;

/* loaded from: classes.dex */
public class BytestreamsProvider implements InterfaceC0049c {
    @Override // p008a7.InterfaceC0049c
    /* renamed from: b */
    public AbstractC5586IQ mo180b(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Bytestream bytestream = new Bytestream();
        String attributeValue = xmlPullParser.getAttributeValue("", "sid");
        String attributeValue2 = xmlPullParser.getAttributeValue("", "mode");
        boolean z8 = false;
        while (true) {
            String attributeValue3 = null;
            String attributeValue4 = null;
            String attributeValue5 = null;
            while (!z8) {
                int next = xmlPullParser.next();
                String name = xmlPullParser.getName();
                if (next == 2) {
                    if (name.equals(Bytestream.C5625b.f19609e)) {
                        attributeValue4 = xmlPullParser.getAttributeValue("", "jid");
                        attributeValue5 = xmlPullParser.getAttributeValue("", "host");
                        attributeValue3 = xmlPullParser.getAttributeValue("", "port");
                    } else if (name.equals(Bytestream.C5626c.f19613c)) {
                        bytestream.m22423T(xmlPullParser.getAttributeValue("", "jid"));
                    } else if (name.equals(Bytestream.C5624a.f19605c)) {
                        bytestream.m22422S(xmlPullParser.getAttributeValue("", "jid"));
                    }
                } else if (next != 3) {
                    continue;
                } else if (name.equals("streamhost")) {
                    if (attributeValue3 == null) {
                        bytestream.m22410G(attributeValue4, attributeValue5);
                    } else {
                        bytestream.m22411H(attributeValue4, attributeValue5, Integer.parseInt(attributeValue3));
                    }
                } else if (name.equals(SearchIntents.EXTRA_QUERY)) {
                    z8 = true;
                }
            }
            bytestream.m22420Q(Bytestream.Mode.m22424a(attributeValue2));
            bytestream.m22421R(attributeValue);
            return bytestream;
        }
    }
}
