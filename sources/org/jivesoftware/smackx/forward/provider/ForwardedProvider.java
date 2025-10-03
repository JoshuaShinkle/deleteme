package org.jivesoftware.smackx.forward.provider;

import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.xmlpull.v1.XmlPullParser;
import p008a7.InterfaceC0051e;
import p130l7.C5299a;
import p149n7.C5375a;

/* loaded from: classes.dex */
public class ForwardedProvider implements InterfaceC0051e {
    @Override // p008a7.InterfaceC0051e
    /* renamed from: a */
    public InterfaceC5595c mo181a(XmlPullParser xmlPullParser) throws Exception {
        Message messageM22251n = null;
        boolean z8 = false;
        C5299a c5299a = null;
        while (!z8) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("delay")) {
                    c5299a = (C5299a) PacketParserUtils.m22252o(xmlPullParser.getName(), xmlPullParser.getNamespace(), xmlPullParser);
                } else {
                    if (!xmlPullParser.getName().equals("message")) {
                        throw new Exception("Unsupported forwarded packet type: " + xmlPullParser.getName());
                    }
                    messageM22251n = PacketParserUtils.m22251n(xmlPullParser);
                }
            } else if (next == 3 && xmlPullParser.getName().equals("forwarded")) {
                z8 = true;
            }
        }
        if (messageM22251n != null) {
            return new C5375a(c5299a, messageM22251n);
        }
        throw new Exception("forwarded extension must contain a packet");
    }
}
