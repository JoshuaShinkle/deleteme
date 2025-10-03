package com.cyberlink.you.chat.p035p;

import com.cyberlink.you.chat.C2898i;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.xmlpull.v1.XmlPullParser;
import p008a7.InterfaceC0051e;
import p209u2.C6383t;

/* loaded from: classes.dex */
public class ResultExtensionProvider implements InterfaceC0051e {

    /* renamed from: a */
    public String f12800a;

    public ResultExtensionProvider(String str) {
        this.f12800a = str;
    }

    @Override // p008a7.InterfaceC0051e
    /* renamed from: a */
    public InterfaceC5595c mo181a(XmlPullParser xmlPullParser) {
        C2898i c2898i = new C2898i("result", this.f12800a);
        String attributeValue = xmlPullParser.getAttributeValue(null, TtmlNode.ATTR_ID);
        if (!C6383t.m24517f(attributeValue)) {
            c2898i.m14375g(TtmlNode.ATTR_ID, attributeValue);
        }
        String attributeValue2 = xmlPullParser.getAttributeValue(null, "queryid");
        if (!C6383t.m24517f(attributeValue2)) {
            c2898i.m14375g("queryid", attributeValue2);
        }
        return c2898i;
    }
}
