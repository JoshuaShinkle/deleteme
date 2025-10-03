package com.cyberlink.you.chat.p035p;

import com.cyberlink.you.chat.C2898i;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.xmlpull.v1.XmlPullParser;
import p008a7.InterfaceC0051e;

/* loaded from: classes.dex */
public class UserContactExtensionProvider implements InterfaceC0051e {
    @Override // p008a7.InterfaceC0051e
    /* renamed from: a */
    public InterfaceC5595c mo181a(XmlPullParser xmlPullParser) {
        C2898i c2898i = new C2898i("userContact", "urn:xmpp:contact:0");
        c2898i.m14375g("userId", xmlPullParser.getAttributeValue(null, "userId"));
        c2898i.m14375g("displayName", xmlPullParser.getAttributeValue(null, "displayName"));
        return c2898i;
    }
}
