package com.cyberlink.you.chat.p035p;

import com.cyberlink.you.chat.C2898i;
import com.google.android.exoplayer2.util.MimeTypes;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.xmlpull.v1.XmlPullParser;
import p008a7.InterfaceC0051e;

/* loaded from: classes.dex */
public class AudioExtensionProvider implements InterfaceC0051e {
    @Override // p008a7.InterfaceC0051e
    /* renamed from: a */
    public InterfaceC5595c mo181a(XmlPullParser xmlPullParser) {
        C2898i c2898i = new C2898i(MimeTypes.BASE_TYPE_AUDIO, "U");
        c2898i.m14375g("mediaId", xmlPullParser.getAttributeValue(null, "mediaId"));
        c2898i.m14375g("mediaType", xmlPullParser.getAttributeValue(null, "mediaType"));
        c2898i.m14375g("albumId", xmlPullParser.getAttributeValue(null, "albumId"));
        c2898i.m14375g("thumbnail", xmlPullParser.getAttributeValue(null, "thumbnail"));
        c2898i.m14375g("original", xmlPullParser.getAttributeValue(null, "original"));
        c2898i.m14375g("duration", xmlPullParser.getAttributeValue(null, "duration"));
        return c2898i;
    }
}
