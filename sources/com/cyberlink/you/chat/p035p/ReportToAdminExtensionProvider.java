package com.cyberlink.you.chat.p035p;

import com.cyberlink.you.chat.C2898i;
import java.io.IOException;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p008a7.InterfaceC0051e;

/* loaded from: classes.dex */
public class ReportToAdminExtensionProvider implements InterfaceC0051e {
    @Override // p008a7.InterfaceC0051e
    /* renamed from: a */
    public InterfaceC5595c mo181a(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        C2898i c2898i = new C2898i("reportToAdmin", "urn:xmpp:reportadmin:0");
        c2898i.m14375g("reportType", xmlPullParser.getAttributeValue(null, "reportType"));
        c2898i.m14375g("reporterId", xmlPullParser.getAttributeValue(null, "reporterId"));
        c2898i.m14375g("groupId", xmlPullParser.getAttributeValue(null, "groupId"));
        c2898i.m14375g("topicId", xmlPullParser.getAttributeValue(null, "topicId"));
        xmlPullParser.next();
        xmlPullParser.next();
        c2898i.m14376h(xmlPullParser.nextText());
        return c2898i;
    }
}
