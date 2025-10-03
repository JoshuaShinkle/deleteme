package org.jivesoftware.smackx.attention.packet;

import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.xmlpull.v1.XmlPullParser;
import p008a7.InterfaceC0051e;

/* loaded from: classes.dex */
public class AttentionExtension implements InterfaceC5595c {

    public static class Provider implements InterfaceC0051e {
        @Override // p008a7.InterfaceC0051e
        /* renamed from: a */
        public InterfaceC5595c mo181a(XmlPullParser xmlPullParser) {
            return new AttentionExtension();
        }
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: b */
    public String mo191b() {
        return "attention";
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public String mo190a() {
        return "<" + mo191b() + " xmlns=\"" + getNamespace() + "\"/>";
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    public String getNamespace() {
        return "urn:xmpp:attention:0";
    }
}
