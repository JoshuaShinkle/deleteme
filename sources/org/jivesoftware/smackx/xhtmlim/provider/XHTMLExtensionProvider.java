package org.jivesoftware.smackx.xhtmlim.provider;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.io.IOException;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.jivesoftware.smack.util.C5616j;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p008a7.InterfaceC0051e;
import p029c8.C0753a;

/* loaded from: classes.dex */
public class XHTMLExtensionProvider implements InterfaceC0051e {
    /* renamed from: b */
    public static void m22811b(StringBuilder sb, XmlPullParser xmlPullParser, boolean z8) {
        if (z8) {
            sb.append("/>");
            return;
        }
        sb.append("</");
        sb.append(xmlPullParser.getName());
        sb.append('>');
    }

    /* renamed from: c */
    public static void m22812c(StringBuilder sb, XmlPullParser xmlPullParser, boolean z8) {
        sb.append('<');
        String prefix = xmlPullParser.getPrefix();
        if (C5616j.m22343h(prefix)) {
            sb.append(prefix);
            sb.append(':');
        }
        sb.append(xmlPullParser.getName());
        int attributeCount = xmlPullParser.getAttributeCount();
        if (z8) {
            String namespace = xmlPullParser.getNamespace();
            if (C5616j.m22343h(namespace)) {
                sb.append(" xmlns='");
                sb.append(namespace);
                sb.append('\'');
            }
        }
        for (int i9 = 0; i9 < attributeCount; i9++) {
            sb.append(' ');
            String attributeNamespace = xmlPullParser.getAttributeNamespace(i9);
            if (C5616j.m22343h(attributeNamespace)) {
                sb.append(attributeNamespace);
                sb.append(':');
            }
            sb.append(xmlPullParser.getAttributeName(i9));
            String attributeValue = xmlPullParser.getAttributeValue(i9);
            if (attributeValue != null) {
                sb.append("='");
                sb.append(C5616j.m22341f(attributeValue));
                sb.append('\'');
            }
        }
    }

    /* renamed from: d */
    public static boolean m22813d(StringBuilder sb, XmlPullParser xmlPullParser) {
        if (xmlPullParser.getText() == null) {
            return false;
        }
        sb.append(C5616j.m22341f(xmlPullParser.getText()));
        return true;
    }

    /* renamed from: e */
    public static boolean m22814e(boolean z8, StringBuilder sb) {
        if (!z8) {
            return false;
        }
        sb.append('>');
        return false;
    }

    @Override // p008a7.InterfaceC0051e
    /* renamed from: a */
    public InterfaceC5595c mo181a(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        boolean z8;
        C0753a c0753a = new C0753a();
        String strMo191b = c0753a.mo191b();
        int depth = xmlPullParser.getDepth();
        int depth2 = xmlPullParser.getDepth();
        StringBuilder sb = new StringBuilder();
        while (true) {
            boolean zM22814e = false;
            while (true) {
                int next = xmlPullParser.next();
                if (next == 2) {
                    if (TtmlNode.TAG_BODY.equals(xmlPullParser.getName())) {
                        sb = new StringBuilder();
                        depth2 = xmlPullParser.getDepth();
                        z8 = true;
                    } else {
                        z8 = false;
                    }
                    m22814e(zM22814e, sb);
                    m22812c(sb, xmlPullParser, z8);
                    zM22814e = true;
                } else if (next == 4) {
                    zM22814e = m22814e(zM22814e, sb);
                    m22813d(sb, xmlPullParser);
                } else if (next == 3) {
                    break;
                }
            }
            String name = xmlPullParser.getName();
            if (strMo191b.equals(name) && xmlPullParser.getDepth() <= depth) {
                return c0753a;
            }
            m22811b(sb, xmlPullParser, zM22814e);
            if (TtmlNode.TAG_BODY.equals(name) && xmlPullParser.getDepth() <= depth2) {
                c0753a.m3635c(sb.toString());
            }
        }
    }
}
