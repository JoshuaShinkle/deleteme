package org.jivesoftware.smackx.xevent.provider;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.io.IOException;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p008a7.InterfaceC0051e;
import p019b8.C0693a;

/* loaded from: classes.dex */
public class MessageEventProvider implements InterfaceC0051e {
    @Override // p008a7.InterfaceC0051e
    /* renamed from: a */
    public InterfaceC5595c mo181a(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        C0693a c0693a = new C0693a();
        boolean z8 = false;
        while (!z8) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals(TtmlNode.ATTR_ID)) {
                    c0693a.m3455m(xmlPullParser.nextText());
                }
                if (xmlPullParser.getName().equals("composing")) {
                    c0693a.m3451i(true);
                }
                if (xmlPullParser.getName().equals("delivered")) {
                    c0693a.m3452j(true);
                }
                if (xmlPullParser.getName().equals("displayed")) {
                    c0693a.m3453k(true);
                }
                if (xmlPullParser.getName().equals("offline")) {
                    c0693a.m3454l(true);
                }
            } else if (next == 3 && xmlPullParser.getName().equals("x")) {
                z8 = true;
            }
        }
        return c0693a;
    }
}
