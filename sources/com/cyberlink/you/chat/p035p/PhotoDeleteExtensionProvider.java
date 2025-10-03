package com.cyberlink.you.chat.p035p;

import com.cyberlink.you.chat.C2898i;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.xmlpull.v1.XmlPullParser;
import p008a7.InterfaceC0051e;

/* loaded from: classes.dex */
public class PhotoDeleteExtensionProvider implements InterfaceC0051e {
    @Override // p008a7.InterfaceC0051e
    /* renamed from: a */
    public InterfaceC5595c mo181a(XmlPullParser xmlPullParser) {
        C2898i c2898i = new C2898i("photoDelete", "U");
        c2898i.m14375g("albumId", xmlPullParser.getAttributeValue(null, "albumId"));
        c2898i.m14375g("albumName", xmlPullParser.getAttributeValue(null, "albumName"));
        c2898i.m14375g("numberDelete", xmlPullParser.getAttributeValue(null, "numberDelete"));
        return c2898i;
    }
}
