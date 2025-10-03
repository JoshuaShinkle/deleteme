package com.cyberlink.you.chat.p035p;

import com.cyberlink.you.chat.C2898i;
import com.cyberlink.you.utility.CLUtility;
import com.google.android.gms.plus.PlusShare;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.xmlpull.v1.XmlPullParser;
import p008a7.InterfaceC0051e;
import p209u2.C6383t;

/* loaded from: classes.dex */
public class MediaExtensionProvider implements InterfaceC0051e {
    @Override // p008a7.InterfaceC0051e
    /* renamed from: a */
    public InterfaceC5595c mo181a(XmlPullParser xmlPullParser) {
        C2898i c2898i = new C2898i("media", "U");
        c2898i.m14375g("mediaId", xmlPullParser.getAttributeValue(null, "mediaId"));
        c2898i.m14375g("mediaType", xmlPullParser.getAttributeValue(null, "mediaType"));
        c2898i.m14375g("albumId", xmlPullParser.getAttributeValue(null, "albumId"));
        c2898i.m14375g("thumbnail", xmlPullParser.getAttributeValue(null, "thumbnail"));
        c2898i.m14375g("original", xmlPullParser.getAttributeValue(null, "original"));
        c2898i.m14375g("width", xmlPullParser.getAttributeValue(null, "width"));
        c2898i.m14375g("height", xmlPullParser.getAttributeValue(null, "height"));
        String strM16478P2 = CLUtility.m16478P2(xmlPullParser.getAttributeValue(null, PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION));
        if (!C6383t.m24517f(strM16478P2)) {
            c2898i.m14375g(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, strM16478P2);
        }
        String attributeValue = xmlPullParser.getAttributeValue(null, "noteMediaType");
        if (!C6383t.m24517f(attributeValue)) {
            c2898i.m14375g("noteMediaType", attributeValue);
        }
        String strM16478P22 = CLUtility.m16478P2(xmlPullParser.getAttributeValue(null, "noteMediaDescription"));
        if (!C6383t.m24517f(strM16478P22)) {
            c2898i.m14375g("noteMediaDescription", strM16478P22);
        }
        String attributeValue2 = xmlPullParser.getAttributeValue(null, "commentCount");
        if (!C6383t.m24517f(attributeValue2)) {
            c2898i.m14375g("commentCount", attributeValue2);
        }
        String attributeValue3 = xmlPullParser.getAttributeValue(null, "thumbKey");
        if (!C6383t.m24517f(attributeValue3)) {
            c2898i.m14375g("thumbKey", attributeValue3);
        }
        String attributeValue4 = xmlPullParser.getAttributeValue(null, "thumbAuth");
        if (!C6383t.m24517f(attributeValue4)) {
            c2898i.m14375g("thumbAuth", attributeValue4);
        }
        String attributeValue5 = xmlPullParser.getAttributeValue(null, "key");
        if (!C6383t.m24517f(attributeValue5)) {
            c2898i.m14375g("key", attributeValue5);
        }
        String attributeValue6 = xmlPullParser.getAttributeValue(null, "auth");
        if (!C6383t.m24517f(attributeValue6)) {
            c2898i.m14375g("auth", attributeValue6);
        }
        return c2898i;
    }
}
