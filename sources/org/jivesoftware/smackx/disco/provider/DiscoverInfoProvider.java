package org.jivesoftware.smackx.disco.provider;

import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.io.IOException;
import org.jivesoftware.smack.packet.AbstractC5586IQ;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.jivesoftware.smackx.disco.packet.DiscoverInfo;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p008a7.InterfaceC0049c;

/* loaded from: classes.dex */
public class DiscoverInfoProvider implements InterfaceC0049c {
    @Override // p008a7.InterfaceC0049c
    /* renamed from: b */
    public AbstractC5586IQ mo180b(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        DiscoverInfo discoverInfo = new DiscoverInfo();
        discoverInfo.m22503N(xmlPullParser.getAttributeValue("", "node"));
        boolean z8 = false;
        String attributeValue = "";
        String attributeValue2 = attributeValue;
        String attributeValue3 = attributeValue2;
        String attributeValue4 = attributeValue3;
        String attributeValue5 = attributeValue4;
        while (!z8) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("identity")) {
                    attributeValue = xmlPullParser.getAttributeValue("", "category");
                    attributeValue2 = xmlPullParser.getAttributeValue("", AppMeasurementSdk.ConditionalUserProperty.NAME);
                    attributeValue3 = xmlPullParser.getAttributeValue("", "type");
                    attributeValue4 = xmlPullParser.getAttributeValue(xmlPullParser.getNamespace("xml"), "lang");
                } else if (xmlPullParser.getName().equals("feature")) {
                    attributeValue5 = xmlPullParser.getAttributeValue("", "var");
                } else {
                    discoverInfo.m22154b(PacketParserUtils.m22252o(xmlPullParser.getName(), xmlPullParser.getNamespace(), xmlPullParser));
                }
            } else if (next == 3) {
                if (xmlPullParser.getName().equals("identity")) {
                    DiscoverInfo.C5637b c5637b = new DiscoverInfo.C5637b(attributeValue, attributeValue2, attributeValue3);
                    if (attributeValue4 != null) {
                        c5637b.m22508c(attributeValue4);
                    }
                    discoverInfo.m22500K(c5637b);
                }
                if (xmlPullParser.getName().equals("feature")) {
                    discoverInfo.m22496G(attributeValue5);
                }
                if (xmlPullParser.getName().equals(SearchIntents.EXTRA_QUERY)) {
                    z8 = true;
                }
            }
        }
        return discoverInfo;
    }
}
