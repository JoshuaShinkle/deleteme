package org.jivesoftware.smackx.carbons.provider;

import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.jivesoftware.smackx.carbons.packet.CarbonExtension;
import org.xmlpull.v1.XmlPullParser;
import p008a7.InterfaceC0051e;
import p149n7.C5375a;

/* loaded from: classes.dex */
public class CarbonManagerProvider implements InterfaceC0051e {
    @Override // p008a7.InterfaceC0051e
    /* renamed from: a */
    public InterfaceC5595c mo181a(XmlPullParser xmlPullParser) throws Exception {
        CarbonExtension.Direction directionValueOf = CarbonExtension.Direction.valueOf(xmlPullParser.getName());
        C5375a c5375a = null;
        boolean z8 = false;
        while (!z8) {
            int next = xmlPullParser.next();
            if (next == 2 && xmlPullParser.getName().equals("forwarded")) {
                c5375a = (C5375a) PacketParserUtils.m22252o("forwarded", "urn:xmpp:forward:0", xmlPullParser);
            } else if (next == 3 && directionValueOf == CarbonExtension.Direction.valueOf(xmlPullParser.getName())) {
                z8 = true;
            }
        }
        if (c5375a != null) {
            return new CarbonExtension(directionValueOf, c5375a);
        }
        throw new Exception("sent/received must contain exactly one <forwarded> tag");
    }
}
