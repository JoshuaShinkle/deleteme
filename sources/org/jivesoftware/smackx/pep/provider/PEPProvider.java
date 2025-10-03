package org.jivesoftware.smackx.pep.provider;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p008a7.InterfaceC0051e;

/* loaded from: classes.dex */
public class PEPProvider implements InterfaceC0051e {

    /* renamed from: a */
    public Map<String, InterfaceC0051e> f19780a = new HashMap();

    /* renamed from: b */
    public InterfaceC5595c f19781b;

    @Override // p008a7.InterfaceC0051e
    /* renamed from: a */
    public InterfaceC5595c mo181a(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        boolean z8 = false;
        while (!z8) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (!xmlPullParser.getName().equals("event") && xmlPullParser.getName().equals(FirebaseAnalytics.Param.ITEMS)) {
                    InterfaceC0051e interfaceC0051e = this.f19780a.get(xmlPullParser.getAttributeValue("", "node"));
                    if (interfaceC0051e != null) {
                        this.f19781b = interfaceC0051e.mo181a(xmlPullParser);
                    }
                }
            } else if (next == 3 && xmlPullParser.getName().equals("event")) {
                z8 = true;
            }
        }
        return this.f19781b;
    }
}
