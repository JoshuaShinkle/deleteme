package com.cyberlink.you.chat.p035p;

import com.cyberlink.you.chat.C2898i;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.xmlpull.v1.XmlPullParser;
import p008a7.InterfaceC0051e;

/* loaded from: classes.dex */
public class CallExtensionProvider implements InterfaceC0051e {
    @Override // p008a7.InterfaceC0051e
    /* renamed from: a */
    public InterfaceC5595c mo181a(XmlPullParser xmlPullParser) {
        C2898i c2898i = new C2898i("call", "urn:xmpp:call:0");
        c2898i.m14375g("callerId", xmlPullParser.getAttributeValue(null, "callerId"));
        c2898i.m14375g("calleeId", xmlPullParser.getAttributeValue(null, "calleeId"));
        c2898i.m14375g("callId", xmlPullParser.getAttributeValue(null, "callId"));
        c2898i.m14375g("status", xmlPullParser.getAttributeValue(null, "status"));
        c2898i.m14375g("statusV2", xmlPullParser.getAttributeValue(null, "statusV2"));
        c2898i.m14375g("callType", xmlPullParser.getAttributeValue(null, "callType"));
        c2898i.m14375g("duration", xmlPullParser.getAttributeValue(null, "duration"));
        return c2898i;
    }
}
