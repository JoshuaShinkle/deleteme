package org.jivesoftware.smackx.privacy.provider;

import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.io.IOException;
import java.util.ArrayList;
import org.jivesoftware.smack.packet.AbstractC5586IQ;
import org.jivesoftware.smack.packet.C5593a;
import org.jivesoftware.smackx.privacy.packet.Privacy;
import org.jivesoftware.smackx.privacy.packet.PrivacyItem;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p008a7.InterfaceC0049c;

/* loaded from: classes.dex */
public class PrivacyProvider implements InterfaceC0049c {
    /* renamed from: a */
    public PrivacyItem m22677a(XmlPullParser xmlPullParser) throws XmlPullParserException, NumberFormatException, IOException {
        String attributeValue = xmlPullParser.getAttributeValue("", "action");
        String attributeValue2 = xmlPullParser.getAttributeValue("", "order");
        String attributeValue3 = xmlPullParser.getAttributeValue("", "type");
        boolean z8 = false;
        boolean z9 = "allow".equalsIgnoreCase(attributeValue) || !"deny".equalsIgnoreCase(attributeValue);
        int i9 = Integer.parseInt(attributeValue2);
        if (attributeValue3 == null) {
            return new PrivacyItem(z9, i9);
        }
        PrivacyItem privacyItem = new PrivacyItem(PrivacyItem.Type.valueOf(attributeValue3), xmlPullParser.getAttributeValue("", "value"), z9, i9);
        while (!z8) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("iq")) {
                    privacyItem.m22672j(true);
                }
                if (xmlPullParser.getName().equals("message")) {
                    privacyItem.m22673k(true);
                }
                if (xmlPullParser.getName().equals("presence-in")) {
                    privacyItem.m22674l(true);
                }
                if (xmlPullParser.getName().equals("presence-out")) {
                    privacyItem.m22675m(true);
                }
            } else if (next == 3 && xmlPullParser.getName().equals("item")) {
                z8 = true;
            }
        }
        return privacyItem;
    }

    @Override // p008a7.InterfaceC0049c
    /* renamed from: b */
    public AbstractC5586IQ mo180b(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Privacy privacy = new Privacy();
        privacy.m22154b(new C5593a(xmlPullParser.getName(), xmlPullParser.getNamespace()));
        boolean z8 = false;
        while (!z8) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)) {
                    String attributeValue = xmlPullParser.getAttributeValue("", AppMeasurementSdk.ConditionalUserProperty.NAME);
                    if (attributeValue == null) {
                        privacy.m22659N(true);
                    } else {
                        privacy.m22658M(attributeValue);
                    }
                } else if (xmlPullParser.getName().equals("default")) {
                    String attributeValue2 = xmlPullParser.getAttributeValue("", AppMeasurementSdk.ConditionalUserProperty.NAME);
                    if (attributeValue2 == null) {
                        privacy.m22660O(true);
                    } else {
                        privacy.m22661P(attributeValue2);
                    }
                } else if (xmlPullParser.getName().equals("list")) {
                    m22678c(xmlPullParser, privacy);
                }
            } else if (next == 3 && xmlPullParser.getName().equals(SearchIntents.EXTRA_QUERY)) {
                z8 = true;
            }
        }
        return privacy;
    }

    /* renamed from: c */
    public void m22678c(XmlPullParser xmlPullParser, Privacy privacy) throws XmlPullParserException, IOException {
        String attributeValue = xmlPullParser.getAttributeValue("", AppMeasurementSdk.ConditionalUserProperty.NAME);
        ArrayList arrayList = new ArrayList();
        boolean z8 = false;
        while (!z8) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("item")) {
                    arrayList.add(m22677a(xmlPullParser));
                }
            } else if (next == 3 && xmlPullParser.getName().equals("list")) {
                z8 = true;
            }
        }
        privacy.m22662Q(attributeValue, arrayList);
    }
}
