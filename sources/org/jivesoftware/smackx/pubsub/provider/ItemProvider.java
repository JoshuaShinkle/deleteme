package org.jivesoftware.smackx.pubsub.provider;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.xmlpull.v1.XmlPullParser;
import p008a7.C0054h;
import p008a7.InterfaceC0051e;
import p232w7.C6553f;
import p232w7.C6555h;
import p232w7.C6557j;

/* loaded from: classes.dex */
public class ItemProvider implements InterfaceC0051e {
    @Override // p008a7.InterfaceC0051e
    /* renamed from: a */
    public InterfaceC5595c mo181a(XmlPullParser xmlPullParser) {
        String attributeValue = xmlPullParser.getAttributeValue(null, TtmlNode.ATTR_ID);
        String attributeValue2 = xmlPullParser.getAttributeValue(null, "node");
        if (xmlPullParser.next() == 3) {
            return new C6553f(attributeValue, attributeValue2);
        }
        String name = xmlPullParser.getName();
        String namespace = xmlPullParser.getNamespace();
        return C0054h.m187c(name, namespace) == null ? new C6555h(attributeValue, attributeValue2, new C6557j(name, namespace, PacketParserUtils.m22246i(xmlPullParser))) : new C6555h(attributeValue, attributeValue2, PacketParserUtils.m22252o(name, namespace, xmlPullParser));
    }
}
