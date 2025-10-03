package org.jivesoftware.smackx.xdata.provider;

import com.google.android.gms.plus.PlusShare;
import java.io.IOException;
import java.util.ArrayList;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p008a7.InterfaceC0051e;
import p009a8.C0055a;
import p259z7.C6838b;

/* loaded from: classes.dex */
public class DataFormProvider implements InterfaceC0051e {
    @Override // p008a7.InterfaceC0051e
    /* renamed from: a */
    public InterfaceC5595c mo181a(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        C0055a c0055a = new C0055a(xmlPullParser.getAttributeValue("", "type"));
        boolean z8 = false;
        while (!z8) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("instructions")) {
                    c0055a.m193d(xmlPullParser.nextText());
                } else if (xmlPullParser.getName().equals(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE)) {
                    c0055a.m202m(xmlPullParser.nextText());
                } else if (xmlPullParser.getName().equals("field")) {
                    c0055a.m192c(m22805b(xmlPullParser));
                } else if (xmlPullParser.getName().equals("item")) {
                    c0055a.m194e(m22806c(xmlPullParser));
                } else if (xmlPullParser.getName().equals("reported")) {
                    c0055a.m201l(m22808e(xmlPullParser));
                }
            } else if (next == 3 && xmlPullParser.getName().equals(c0055a.mo191b())) {
                z8 = true;
            }
        }
        return c0055a;
    }

    /* renamed from: b */
    public final C6838b m22805b(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        C6838b c6838b = new C6838b(xmlPullParser.getAttributeValue("", "var"));
        c6838b.m25558k(xmlPullParser.getAttributeValue("", "label"));
        c6838b.m25560m(xmlPullParser.getAttributeValue("", "type"));
        boolean z8 = false;
        while (!z8) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("desc")) {
                    c6838b.m25557j(xmlPullParser.nextText());
                } else if (xmlPullParser.getName().equals("value")) {
                    c6838b.m25549b(xmlPullParser.nextText());
                } else if (xmlPullParser.getName().equals("required")) {
                    c6838b.m25559l(true);
                } else if (xmlPullParser.getName().equals("option")) {
                    c6838b.m25548a(m22807d(xmlPullParser));
                }
            } else if (next == 3 && xmlPullParser.getName().equals("field")) {
                z8 = true;
            }
        }
        return c6838b;
    }

    /* renamed from: c */
    public final C0055a.a m22806c(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        ArrayList arrayList = new ArrayList();
        boolean z8 = false;
        while (!z8) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("field")) {
                    arrayList.add(m22805b(xmlPullParser));
                }
            } else if (next == 3 && xmlPullParser.getName().equals("item")) {
                z8 = true;
            }
        }
        return new C0055a.a(arrayList);
    }

    /* renamed from: d */
    public final C6838b.a m22807d(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        String attributeValue = xmlPullParser.getAttributeValue("", "label");
        boolean z8 = false;
        C6838b.a aVar = null;
        while (!z8) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("value")) {
                    aVar = new C6838b.a(attributeValue, xmlPullParser.nextText());
                }
            } else if (next == 3 && xmlPullParser.getName().equals("option")) {
                z8 = true;
            }
        }
        return aVar;
    }

    /* renamed from: e */
    public final C0055a.b m22808e(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        ArrayList arrayList = new ArrayList();
        boolean z8 = false;
        while (!z8) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("field")) {
                    arrayList.add(m22805b(xmlPullParser));
                }
            } else if (next == 3 && xmlPullParser.getName().equals("reported")) {
                z8 = true;
            }
        }
        return new C0055a.b(arrayList);
    }
}
