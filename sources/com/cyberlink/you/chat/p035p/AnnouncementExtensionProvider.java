package com.cyberlink.you.chat.p035p;

import com.cyberlink.you.chat.C2898i;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.plus.PlusShare;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.xmlpull.v1.XmlPullParser;
import p008a7.InterfaceC0051e;

/* loaded from: classes.dex */
public class AnnouncementExtensionProvider implements InterfaceC0051e {
    @Override // p008a7.InterfaceC0051e
    /* renamed from: a */
    public InterfaceC5595c mo181a(XmlPullParser xmlPullParser) {
        C2898i c2898i = new C2898i("announcement", "urn:xmpp:announcement:0");
        c2898i.m14375g("type", xmlPullParser.getAttributeValue(null, "type"));
        c2898i.m14375g("template", xmlPullParser.getAttributeValue(null, "template"));
        c2898i.m14375g("url", xmlPullParser.getAttributeValue(null, "url"));
        c2898i.m14375g("titleOfUrl", xmlPullParser.getAttributeValue(null, "titleOfUrl"));
        c2898i.m14375g(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, xmlPullParser.getAttributeValue(null, PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE));
        c2898i.m14375g(TtmlNode.ATTR_ID, xmlPullParser.getAttributeValue(null, TtmlNode.ATTR_ID));
        c2898i.m14375g("shareToFB", xmlPullParser.getAttributeValue(null, "shareToFB"));
        return c2898i;
    }
}
