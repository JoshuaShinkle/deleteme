package org.jivesoftware.smackx.hoxt.provider;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.IOException;
import org.jivesoftware.smack.packet.AbstractC5586IQ;
import org.jivesoftware.smackx.hoxt.packet.HttpMethod;
import org.jivesoftware.smackx.hoxt.packet.HttpOverXmppReq;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public class HttpOverXmppReqProvider extends AbstractHttpOverXmppProvider {
    @Override // p008a7.InterfaceC0049c
    /* renamed from: b */
    public AbstractC5586IQ mo180b(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        String attributeValue = xmlPullParser.getAttributeValue("", FirebaseAnalytics.Param.METHOD);
        String attributeValue2 = xmlPullParser.getAttributeValue("", "resource");
        String attributeValue3 = xmlPullParser.getAttributeValue("", "version");
        String attributeValue4 = xmlPullParser.getAttributeValue("", "maxChunkSize");
        HttpOverXmppReq.C5647a c5647a = new HttpOverXmppReq.C5647a(HttpMethod.valueOf(attributeValue), attributeValue2);
        c5647a.m22524e(attributeValue3);
        Boolean boolValueOf = Boolean.TRUE;
        String attributeValue5 = xmlPullParser.getAttributeValue("", "sipub");
        String attributeValue6 = xmlPullParser.getAttributeValue("", "ibb");
        String attributeValue7 = xmlPullParser.getAttributeValue("", "jingle");
        Boolean boolValueOf2 = attributeValue5 != null ? Boolean.valueOf(attributeValue5) : boolValueOf;
        Boolean boolValueOf3 = attributeValue6 != null ? Boolean.valueOf(attributeValue6) : boolValueOf;
        if (attributeValue7 != null) {
            boolValueOf = Boolean.valueOf(attributeValue7);
        }
        c5647a.m22530g(boolValueOf3.booleanValue());
        c5647a.m22533j(boolValueOf2.booleanValue());
        c5647a.m22531h(boolValueOf.booleanValue());
        if (attributeValue4 != null) {
            c5647a.m22532i(Integer.parseInt(attributeValue4));
        }
        m22543g(xmlPullParser, "req", c5647a);
        HttpOverXmppReq httpOverXmppReq = new HttpOverXmppReq();
        httpOverXmppReq.m22529H(c5647a);
        return httpOverXmppReq;
    }
}
