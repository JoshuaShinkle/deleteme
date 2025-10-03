package com.cyberlink.you.chat.p035p;

import com.cyberlink.you.chat.C2898i;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.xmlpull.v1.XmlPullParser;
import p008a7.InterfaceC0051e;
import p209u2.C6383t;

/* loaded from: classes.dex */
public class FileExtensionProvider implements InterfaceC0051e {
    @Override // p008a7.InterfaceC0051e
    /* renamed from: a */
    public InterfaceC5595c mo181a(XmlPullParser xmlPullParser) {
        C2898i c2898i = new C2898i("file", "U");
        c2898i.m14375g("mediaId", xmlPullParser.getAttributeValue(null, "mediaId"));
        c2898i.m14375g("mediaType", xmlPullParser.getAttributeValue(null, "mediaType"));
        c2898i.m14375g("albumId", xmlPullParser.getAttributeValue(null, "albumId"));
        c2898i.m14375g("mediaSize", xmlPullParser.getAttributeValue(null, "mediaSize"));
        c2898i.m14375g("mediaName", xmlPullParser.getAttributeValue(null, "mediaName"));
        c2898i.m14375g("expirationTime", xmlPullParser.getAttributeValue(null, "expirationTime"));
        c2898i.m14375g("original", xmlPullParser.getAttributeValue(null, "original"));
        String attributeValue = xmlPullParser.getAttributeValue(null, "key");
        if (!C6383t.m24517f(attributeValue)) {
            c2898i.m14375g("key", attributeValue);
        }
        String attributeValue2 = xmlPullParser.getAttributeValue(null, "auth");
        if (!C6383t.m24517f(attributeValue2)) {
            c2898i.m14375g("auth", attributeValue2);
        }
        return c2898i;
    }
}
