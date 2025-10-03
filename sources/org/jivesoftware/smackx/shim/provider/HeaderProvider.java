package org.jivesoftware.smackx.shim.provider;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.io.IOException;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p008a7.InterfaceC0051e;
import p250y7.C6793a;

/* loaded from: classes.dex */
public class HeaderProvider implements InterfaceC0051e {
    @Override // p008a7.InterfaceC0051e
    /* renamed from: a */
    public InterfaceC5595c mo181a(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        String attributeValue = xmlPullParser.getAttributeValue(null, AppMeasurementSdk.ConditionalUserProperty.NAME);
        xmlPullParser.next();
        String text = xmlPullParser.getEventType() == 4 ? xmlPullParser.getText() : null;
        while (xmlPullParser.getEventType() != 3) {
            xmlPullParser.next();
        }
        return new C6793a(attributeValue, text);
    }
}
