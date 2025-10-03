package org.jivesoftware.smackx.offline.packet;

import java.io.IOException;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p008a7.InterfaceC0051e;

/* loaded from: classes.dex */
public class OfflineMessageInfo implements InterfaceC5595c {

    /* renamed from: a */
    public String f19773a = null;

    public static class Provider implements InterfaceC0051e {
        @Override // p008a7.InterfaceC0051e
        /* renamed from: a */
        public InterfaceC5595c mo181a(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
            OfflineMessageInfo offlineMessageInfo = new OfflineMessageInfo();
            boolean z8 = false;
            while (!z8) {
                int next = xmlPullParser.next();
                if (next == 2) {
                    if (xmlPullParser.getName().equals("item")) {
                        offlineMessageInfo.m22613d(xmlPullParser.getAttributeValue("", "node"));
                    }
                } else if (next == 3 && xmlPullParser.getName().equals("offline")) {
                    z8 = true;
                }
            }
            return offlineMessageInfo;
        }
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: b */
    public String mo191b() {
        return "offline";
    }

    /* renamed from: c */
    public String m22612c() {
        return this.f19773a;
    }

    /* renamed from: d */
    public void m22613d(String str) {
        this.f19773a = str;
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public String mo190a() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(mo191b());
        sb.append(" xmlns=\"");
        sb.append(getNamespace());
        sb.append("\">");
        if (m22612c() != null) {
            sb.append("<item node=\"");
            sb.append(m22612c());
            sb.append("\"/>");
        }
        sb.append("</");
        sb.append(mo191b());
        sb.append(">");
        return sb.toString();
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    public String getNamespace() {
        return "http://jabber.org/protocol/offline";
    }
}
