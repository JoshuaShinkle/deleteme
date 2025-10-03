package org.jivesoftware.smackx.pubsub.provider;

import java.io.IOException;
import org.jivesoftware.smack.packet.AbstractC5586IQ;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.jivesoftware.smackx.pubsub.packet.PubSub;
import org.jivesoftware.smackx.pubsub.packet.PubSubNamespace;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p008a7.InterfaceC0049c;

/* loaded from: classes.dex */
public class PubSubProvider implements InterfaceC0049c {
    @Override // p008a7.InterfaceC0049c
    /* renamed from: b */
    public AbstractC5586IQ mo180b(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        PubSub pubSub = new PubSub();
        String namespace = xmlPullParser.getNamespace();
        pubSub.m22693J(PubSubNamespace.m22694b(namespace));
        boolean z8 = false;
        while (!z8) {
            int next = xmlPullParser.next();
            if (next == 2) {
                InterfaceC5595c interfaceC5595cM22252o = PacketParserUtils.m22252o(xmlPullParser.getName(), namespace, xmlPullParser);
                if (interfaceC5595cM22252o != null) {
                    pubSub.m22154b(interfaceC5595cM22252o);
                }
            } else if (next == 3 && xmlPullParser.getName().equals("pubsub")) {
                z8 = true;
            }
        }
        return pubSub;
    }
}
