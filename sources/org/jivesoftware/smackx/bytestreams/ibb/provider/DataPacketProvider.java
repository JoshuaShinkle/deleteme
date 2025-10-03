package org.jivesoftware.smackx.bytestreams.ibb.provider;

import org.jivesoftware.smack.packet.AbstractC5586IQ;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.jivesoftware.smackx.bytestreams.ibb.packet.Data;
import org.xmlpull.v1.XmlPullParser;
import p008a7.InterfaceC0049c;
import p008a7.InterfaceC0051e;
import p089h7.C5029a;

/* loaded from: classes.dex */
public class DataPacketProvider implements InterfaceC0051e, InterfaceC0049c {
    @Override // p008a7.InterfaceC0051e
    /* renamed from: a */
    public InterfaceC5595c mo181a(XmlPullParser xmlPullParser) {
        return new C5029a(xmlPullParser.getAttributeValue("", "sid"), Long.parseLong(xmlPullParser.getAttributeValue("", "seq")), xmlPullParser.nextText());
    }

    @Override // p008a7.InterfaceC0049c
    /* renamed from: b */
    public AbstractC5586IQ mo180b(XmlPullParser xmlPullParser) {
        return new Data((C5029a) mo181a(xmlPullParser));
    }
}
