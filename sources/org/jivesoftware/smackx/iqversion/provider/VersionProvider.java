package org.jivesoftware.smackx.iqversion.provider;

import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.io.IOException;
import org.jivesoftware.smack.packet.AbstractC5586IQ;
import org.jivesoftware.smackx.iqversion.packet.Version;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p008a7.InterfaceC0049c;

/* loaded from: classes.dex */
public class VersionProvider implements InterfaceC0049c {
    @Override // p008a7.InterfaceC0049c
    /* renamed from: b */
    public AbstractC5586IQ mo180b(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        String strNextText = null;
        String strNextText2 = null;
        boolean z8 = false;
        String strNextText3 = null;
        while (!z8) {
            int next = xmlPullParser.next();
            String name = xmlPullParser.getName();
            if (next == 2) {
                if (name.equals(AppMeasurementSdk.ConditionalUserProperty.NAME)) {
                    strNextText = xmlPullParser.nextText();
                } else if (name.equals("version")) {
                    strNextText3 = xmlPullParser.nextText();
                } else if (name.equals("os")) {
                    strNextText2 = xmlPullParser.nextText();
                }
            } else if (next == 3 && name.equals(SearchIntents.EXTRA_QUERY)) {
                z8 = true;
            }
        }
        return new Version(strNextText, strNextText3, strNextText2);
    }
}
