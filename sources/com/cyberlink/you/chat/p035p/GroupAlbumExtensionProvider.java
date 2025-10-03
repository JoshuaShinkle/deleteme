package com.cyberlink.you.chat.p035p;

import com.cyberlink.you.chat.C2898i;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.xmlpull.v1.XmlPullParser;
import p008a7.InterfaceC0051e;

/* loaded from: classes.dex */
public class GroupAlbumExtensionProvider implements InterfaceC0051e {
    @Override // p008a7.InterfaceC0051e
    /* renamed from: a */
    public InterfaceC5595c mo181a(XmlPullParser xmlPullParser) {
        C2898i c2898i = new C2898i("groupAlbum", "U");
        c2898i.m14375g("albumId", xmlPullParser.getAttributeValue(null, "albumId"));
        c2898i.m14375g("albumName", xmlPullParser.getAttributeValue(null, "albumName"));
        c2898i.m14375g("mediaId", xmlPullParser.getAttributeValue(null, "mediaId"));
        c2898i.m14375g("mediaType", xmlPullParser.getAttributeValue(null, "mediaType"));
        c2898i.m14375g("width", xmlPullParser.getAttributeValue(null, "width"));
        c2898i.m14375g("height", xmlPullParser.getAttributeValue(null, "height"));
        c2898i.m14375g("numberUpload", xmlPullParser.getAttributeValue(null, "numberUpload"));
        c2898i.m14376h(xmlPullParser.nextText());
        return c2898i;
    }
}
