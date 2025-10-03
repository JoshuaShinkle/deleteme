package org.jivesoftware.smack.provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.xmlpull.v1.XmlPullParser;
import p008a7.InterfaceC0051e;

/* loaded from: classes.dex */
public abstract class EmbeddedExtensionProvider implements InterfaceC0051e {
    @Override // p008a7.InterfaceC0051e
    /* renamed from: a */
    public final InterfaceC5595c mo181a(XmlPullParser xmlPullParser) {
        String namespace = xmlPullParser.getNamespace();
        String name = xmlPullParser.getName();
        HashMap map = new HashMap();
        for (int i9 = 0; i9 < xmlPullParser.getAttributeCount(); i9++) {
            map.put(xmlPullParser.getAttributeName(i9), xmlPullParser.getAttributeValue(i9));
        }
        ArrayList arrayList = new ArrayList();
        do {
            if (xmlPullParser.next() == 2) {
                arrayList.add(PacketParserUtils.m22252o(xmlPullParser.getName(), xmlPullParser.getNamespace(), xmlPullParser));
            }
        } while (!name.equals(xmlPullParser.getName()));
        return mo22169b(name, namespace, map, arrayList);
    }

    /* renamed from: b */
    public abstract InterfaceC5595c mo22169b(String str, String str2, Map<String, String> map, List<? extends InterfaceC5595c> list);
}
