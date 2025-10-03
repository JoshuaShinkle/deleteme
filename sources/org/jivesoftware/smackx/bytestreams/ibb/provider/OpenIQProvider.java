package org.jivesoftware.smackx.bytestreams.ibb.provider;

import java.util.Locale;
import org.jivesoftware.smack.packet.AbstractC5586IQ;
import org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamManager;
import org.jivesoftware.smackx.bytestreams.ibb.packet.Open;
import org.xmlpull.v1.XmlPullParser;
import p008a7.InterfaceC0049c;

/* loaded from: classes.dex */
public class OpenIQProvider implements InterfaceC0049c {
    @Override // p008a7.InterfaceC0049c
    /* renamed from: b */
    public AbstractC5586IQ mo180b(XmlPullParser xmlPullParser) throws NumberFormatException {
        String attributeValue = xmlPullParser.getAttributeValue("", "sid");
        int i9 = Integer.parseInt(xmlPullParser.getAttributeValue("", "block-size"));
        String attributeValue2 = xmlPullParser.getAttributeValue("", "stanza");
        return new Open(attributeValue, i9, attributeValue2 == null ? InBandBytestreamManager.StanzaType.IQ : InBandBytestreamManager.StanzaType.valueOf(attributeValue2.toUpperCase(Locale.US)));
    }
}
