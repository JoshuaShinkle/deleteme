package com.cyberlink.you.chat.p035p;

import com.cyberlink.you.chat.C2898i;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.xmlpull.v1.XmlPullParser;
import p008a7.InterfaceC0051e;

/* loaded from: classes.dex */
public class AnnouncementImageExtensionProvider implements InterfaceC0051e {
    @Override // p008a7.InterfaceC0051e
    /* renamed from: a */
    public InterfaceC5595c mo181a(XmlPullParser xmlPullParser) {
        C2898i c2898i = new C2898i("image", "urn:xmpp:announcement:image:0");
        c2898i.m14375g("src", xmlPullParser.getAttributeValue(null, "src"));
        c2898i.m14375g("width", xmlPullParser.getAttributeValue(null, "width"));
        c2898i.m14375g("height", xmlPullParser.getAttributeValue(null, "height"));
        return c2898i;
    }
}
