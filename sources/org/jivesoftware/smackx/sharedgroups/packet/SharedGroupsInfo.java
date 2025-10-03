package org.jivesoftware.smackx.sharedgroups.packet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jivesoftware.smack.packet.AbstractC5586IQ;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p008a7.InterfaceC0049c;

/* loaded from: classes.dex */
public class SharedGroupsInfo extends AbstractC5586IQ {

    /* renamed from: p */
    public List<String> f19894p = new ArrayList();

    public static class Provider implements InterfaceC0049c {
        @Override // p008a7.InterfaceC0049c
        /* renamed from: b */
        public AbstractC5586IQ mo180b(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
            SharedGroupsInfo sharedGroupsInfo = new SharedGroupsInfo();
            boolean z8 = false;
            while (!z8) {
                int next = xmlPullParser.next();
                if (next == 2 && xmlPullParser.getName().equals("group")) {
                    sharedGroupsInfo.m22713H().add(xmlPullParser.nextText());
                } else if (next == 3 && xmlPullParser.getName().equals("sharedgroup")) {
                    z8 = true;
                }
            }
            return sharedGroupsInfo;
        }
    }

    @Override // org.jivesoftware.smack.packet.AbstractC5586IQ
    /* renamed from: G, reason: merged with bridge method [inline-methods] */
    public String mo9675y() {
        StringBuilder sb = new StringBuilder();
        sb.append("<sharedgroup xmlns=\"http://www.jivesoftware.org/protocol/sharedgroup\">");
        for (String str : this.f19894p) {
            sb.append("<group>");
            sb.append(str);
            sb.append("</group>");
        }
        sb.append("</sharedgroup>");
        return sb.toString();
    }

    /* renamed from: H */
    public List<String> m22713H() {
        return this.f19894p;
    }
}
