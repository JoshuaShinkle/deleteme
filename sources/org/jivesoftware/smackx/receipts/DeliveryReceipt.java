package org.jivesoftware.smackx.receipts;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.util.List;
import java.util.Map;
import org.jivesoftware.smack.packet.AbstractC5594b;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.jivesoftware.smack.provider.EmbeddedExtensionProvider;

/* loaded from: classes.dex */
public class DeliveryReceipt implements InterfaceC5595c {

    /* renamed from: a */
    public String f19882a;

    public static class Provider extends EmbeddedExtensionProvider {
        @Override // org.jivesoftware.smack.provider.EmbeddedExtensionProvider
        /* renamed from: b */
        public InterfaceC5595c mo22169b(String str, String str2, Map<String, String> map, List<? extends InterfaceC5595c> list) {
            return new DeliveryReceipt(map.get(TtmlNode.ATTR_ID));
        }
    }

    public DeliveryReceipt(String str) {
        this.f19882a = str;
    }

    /* renamed from: c */
    public static DeliveryReceipt m22696c(AbstractC5594b abstractC5594b) {
        return (DeliveryReceipt) abstractC5594b.m22157g("received", "urn:xmpp:receipts");
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: b */
    public String mo191b() {
        return "received";
    }

    /* renamed from: d */
    public String m22697d() {
        return this.f19882a;
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public String mo190a() {
        return "<received xmlns='urn:xmpp:receipts' id='" + this.f19882a + "'/>";
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    public String getNamespace() {
        return "urn:xmpp:receipts";
    }
}
