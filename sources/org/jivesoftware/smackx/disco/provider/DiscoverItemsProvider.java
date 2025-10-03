package org.jivesoftware.smackx.disco.provider;

import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.io.IOException;
import org.jivesoftware.smack.packet.AbstractC5586IQ;
import org.jivesoftware.smackx.disco.packet.DiscoverItems;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p008a7.InterfaceC0049c;

/* loaded from: classes.dex */
public class DiscoverItemsProvider implements InterfaceC0049c {
    @Override // p008a7.InterfaceC0049c
    /* renamed from: b */
    public AbstractC5586IQ mo180b(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        DiscoverItems discoverItems = new DiscoverItems();
        discoverItems.m22514K(xmlPullParser.getAttributeValue("", "node"));
        boolean z8 = false;
        String attributeValue = "";
        String attributeValue2 = attributeValue;
        String attributeValue3 = attributeValue2;
        String attributeValue4 = attributeValue3;
        while (!z8) {
            int next = xmlPullParser.next();
            if (next == 2 && "item".equals(xmlPullParser.getName())) {
                attributeValue = xmlPullParser.getAttributeValue("", "jid");
                attributeValue2 = xmlPullParser.getAttributeValue("", AppMeasurementSdk.ConditionalUserProperty.NAME);
                attributeValue3 = xmlPullParser.getAttributeValue("", "node");
                attributeValue4 = xmlPullParser.getAttributeValue("", "action");
            } else if (next == 3 && "item".equals(xmlPullParser.getName())) {
                DiscoverItems.C5638a c5638a = new DiscoverItems.C5638a(attributeValue);
                c5638a.m22516b(attributeValue2);
                c5638a.m22517c(attributeValue3);
                c5638a.m22515a(attributeValue4);
                discoverItems.m22510G(c5638a);
            } else if (next == 3 && SearchIntents.EXTRA_QUERY.equals(xmlPullParser.getName())) {
                z8 = true;
            }
        }
        return discoverItems;
    }
}
