package org.jivesoftware.smackx.receipts;

import org.jivesoftware.smack.packet.AbstractC5594b;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.xmlpull.v1.XmlPullParser;
import p008a7.InterfaceC0051e;

/* loaded from: classes.dex */
public class DeliveryReceiptRequest implements InterfaceC5595c {

    public static class Provider implements InterfaceC0051e {
        @Override // p008a7.InterfaceC0051e
        /* renamed from: a */
        public InterfaceC5595c mo181a(XmlPullParser xmlPullParser) {
            return new DeliveryReceiptRequest();
        }
    }

    /* renamed from: c */
    public static DeliveryReceiptRequest m22699c(AbstractC5594b abstractC5594b) {
        return (DeliveryReceiptRequest) abstractC5594b.m22157g("request", "urn:xmpp:receipts");
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: b */
    public String mo191b() {
        return "request";
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public String mo190a() {
        return "<request xmlns='urn:xmpp:receipts'/>";
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    public String getNamespace() {
        return "urn:xmpp:receipts";
    }
}
