package org.jivesoftware.smackx.ping.provider;

import org.jivesoftware.smack.packet.AbstractC5586IQ;
import org.jivesoftware.smackx.ping.packet.Ping;
import org.xmlpull.v1.XmlPullParser;
import p008a7.InterfaceC0049c;

/* loaded from: classes.dex */
public class PingProvider implements InterfaceC0049c {
    @Override // p008a7.InterfaceC0049c
    /* renamed from: b */
    public AbstractC5586IQ mo180b(XmlPullParser xmlPullParser) {
        return new Ping();
    }
}
