package org.jivesoftware.smackx.bytestreams.ibb.provider;

import org.jivesoftware.smack.packet.AbstractC5586IQ;
import org.jivesoftware.smackx.bytestreams.ibb.packet.Close;
import org.xmlpull.v1.XmlPullParser;
import p008a7.InterfaceC0049c;

/* loaded from: classes.dex */
public class CloseIQProvider implements InterfaceC0049c {
    @Override // p008a7.InterfaceC0049c
    /* renamed from: b */
    public AbstractC5586IQ mo180b(XmlPullParser xmlPullParser) {
        return new Close(xmlPullParser.getAttributeValue("", "sid"));
    }
}
