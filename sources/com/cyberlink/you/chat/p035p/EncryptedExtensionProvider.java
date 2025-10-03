package com.cyberlink.you.chat.p035p;

import com.cyberlink.you.chat.C2898i;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.jivesoftware.smack.util.C5616j;
import org.xmlpull.v1.XmlPullParser;
import p008a7.InterfaceC0051e;

/* loaded from: classes.dex */
public class EncryptedExtensionProvider implements InterfaceC0051e {
    @Override // p008a7.InterfaceC0051e
    /* renamed from: a */
    public InterfaceC5595c mo181a(XmlPullParser xmlPullParser) {
        C2898i c2898i = new C2898i("encrypted", "U");
        String attributeValue = xmlPullParser.getAttributeValue(null, "type");
        if (!C5616j.m22344i(attributeValue)) {
            c2898i.m14375g("type", attributeValue);
        }
        String attributeValue2 = xmlPullParser.getAttributeValue(null, "sessionId");
        if (!C5616j.m22344i(attributeValue2)) {
            c2898i.m14375g("sessionId", attributeValue2);
        }
        c2898i.m14376h(xmlPullParser.nextText());
        return c2898i;
    }
}
