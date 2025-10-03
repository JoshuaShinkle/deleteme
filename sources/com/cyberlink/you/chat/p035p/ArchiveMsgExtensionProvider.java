package com.cyberlink.you.chat.p035p;

import com.cyberlink.you.chat.C2898i;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.firebase.messaging.Constants;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.xmlpull.v1.XmlPullParser;
import p008a7.InterfaceC0051e;

/* loaded from: classes.dex */
public class ArchiveMsgExtensionProvider implements InterfaceC0051e {
    @Override // p008a7.InterfaceC0051e
    /* renamed from: a */
    public InterfaceC5595c mo181a(XmlPullParser xmlPullParser) {
        C2898i c2898i = new C2898i("message", "urn:xmpp:forward:0");
        c2898i.m14375g(TtmlNode.ATTR_ID, xmlPullParser.getAttributeValue(null, TtmlNode.ATTR_ID));
        c2898i.m14375g("type", xmlPullParser.getAttributeValue(null, "type"));
        c2898i.m14375g(Constants.MessagePayloadKeys.FROM, xmlPullParser.getAttributeValue(null, Constants.MessagePayloadKeys.FROM));
        c2898i.m14375g("to", xmlPullParser.getAttributeValue(null, "to"));
        return c2898i;
    }
}
