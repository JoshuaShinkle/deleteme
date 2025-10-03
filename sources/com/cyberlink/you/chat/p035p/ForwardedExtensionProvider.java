package com.cyberlink.you.chat.p035p;

import com.cyberlink.you.chat.C2898i;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.xmlpull.v1.XmlPullParser;
import p008a7.InterfaceC0051e;

/* loaded from: classes.dex */
public class ForwardedExtensionProvider implements InterfaceC0051e {
    @Override // p008a7.InterfaceC0051e
    /* renamed from: a */
    public InterfaceC5595c mo181a(XmlPullParser xmlPullParser) {
        return new C2898i("forwarded", "urn:xmpp:forward:0");
    }
}
