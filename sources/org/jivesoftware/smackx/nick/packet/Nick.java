package org.jivesoftware.smackx.nick.packet;

import java.io.IOException;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p008a7.InterfaceC0051e;

/* loaded from: classes.dex */
public class Nick implements InterfaceC5595c {

    /* renamed from: a */
    public String f19772a;

    public static class Provider implements InterfaceC0051e {
        @Override // p008a7.InterfaceC0051e
        /* renamed from: a */
        public InterfaceC5595c mo181a(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
            xmlPullParser.next();
            String text = xmlPullParser.getText();
            while (xmlPullParser.getEventType() != 3) {
                xmlPullParser.next();
            }
            return new Nick(text);
        }
    }

    public Nick(String str) {
        this.f19772a = str;
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: b */
    public String mo191b() {
        return "nick";
    }

    /* renamed from: c */
    public String m22610c() {
        return this.f19772a;
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public String mo190a() {
        return "<nick xmlns=\"http://jabber.org/protocol/nick\">" + m22610c() + "</nick>";
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    public String getNamespace() {
        return "http://jabber.org/protocol/nick";
    }
}
