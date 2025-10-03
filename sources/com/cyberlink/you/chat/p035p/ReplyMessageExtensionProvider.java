package com.cyberlink.you.chat.p035p;

import com.cyberlink.you.chat.C2898i;
import com.cyberlink.you.utility.ULogUtility;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.io.IOException;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p008a7.InterfaceC0051e;

/* loaded from: classes.dex */
public class ReplyMessageExtensionProvider implements InterfaceC0051e {
    @Override // p008a7.InterfaceC0051e
    /* renamed from: a */
    public InterfaceC5595c mo181a(XmlPullParser xmlPullParser) throws XmlPullParserException, JSONException, IOException {
        C2898i c2898i = new C2898i("textReply", "urn:xmpp:textreply:0");
        c2898i.m14375g("sourceId", xmlPullParser.getAttributeValue(null, "sourceId"));
        c2898i.m14375g("sourceSender", xmlPullParser.getAttributeValue(null, "sourceSender"));
        c2898i.m14375g("sourceType", xmlPullParser.getAttributeValue(null, "sourceType"));
        try {
            JSONObject jSONObject = new JSONObject();
            boolean z8 = false;
            String name = null;
            boolean z9 = false;
            while (true) {
                if (z8 && z9) {
                    break;
                }
                int next = xmlPullParser.next();
                if (next == 2) {
                    name = xmlPullParser.getName();
                } else if (next == 4) {
                    String text = xmlPullParser.getText();
                    if (name != null) {
                        jSONObject.put(name, text);
                    }
                } else if (next == 3) {
                    if (xmlPullParser.getName().equals("source")) {
                        z9 = true;
                    } else if (xmlPullParser.getName().equals(TtmlNode.TAG_BODY)) {
                        z8 = true;
                    }
                    name = null;
                } else if (next == 1) {
                    ULogUtility.m16676l("ReplyMessageExtensionProvider", "Parsing end document tag is not expect");
                    break;
                }
            }
            c2898i.m14376h(jSONObject.toString());
        } catch (Exception e9) {
            e9.printStackTrace();
        }
        return c2898i;
    }
}
