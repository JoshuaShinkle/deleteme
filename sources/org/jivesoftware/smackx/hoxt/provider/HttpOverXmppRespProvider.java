package org.jivesoftware.smackx.hoxt.provider;

import java.io.IOException;
import org.jivesoftware.smack.packet.AbstractC5586IQ;
import org.jivesoftware.smackx.hoxt.packet.HttpOverXmppResp;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public class HttpOverXmppRespProvider extends AbstractHttpOverXmppProvider {
    @Override // p008a7.InterfaceC0049c
    /* renamed from: b */
    public AbstractC5586IQ mo180b(XmlPullParser xmlPullParser) throws XmlPullParserException, NumberFormatException, IOException {
        String attributeValue = xmlPullParser.getAttributeValue("", "version");
        String attributeValue2 = xmlPullParser.getAttributeValue("", "statusMessage");
        int i9 = Integer.parseInt(xmlPullParser.getAttributeValue("", "statusCode"));
        HttpOverXmppResp.C5648a c5648a = new HttpOverXmppResp.C5648a();
        c5648a.m22524e(attributeValue);
        c5648a.m22537h(attributeValue2);
        c5648a.m22536g(i9);
        m22543g(xmlPullParser, "resp", c5648a);
        HttpOverXmppResp httpOverXmppResp = new HttpOverXmppResp();
        httpOverXmppResp.m22535H(c5648a);
        return httpOverXmppResp;
    }
}
