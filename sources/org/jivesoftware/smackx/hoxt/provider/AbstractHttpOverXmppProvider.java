package org.jivesoftware.smackx.hoxt.provider;

import com.google.android.exoplayer2.util.MimeTypes;
import java.io.IOException;
import java.util.HashSet;
import org.jivesoftware.smack.util.C5616j;
import org.jivesoftware.smackx.hoxt.packet.AbstractHttpOverXmpp;
import org.jivesoftware.smackx.shim.provider.HeaderProvider;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p008a7.InterfaceC0049c;
import p250y7.C6793a;
import p250y7.C6794b;

/* loaded from: classes.dex */
public abstract class AbstractHttpOverXmppProvider implements InterfaceC0049c {
    /* renamed from: a */
    public final void m22538a(XmlPullParser xmlPullParser, StringBuilder sb) {
        int attributeCount = xmlPullParser.getAttributeCount();
        if (attributeCount > 0) {
            for (int i9 = 0; i9 < attributeCount; i9++) {
                sb.append(' ');
                sb.append(xmlPullParser.getAttributeName(i9));
                sb.append("=\"");
                sb.append(C5616j.m22341f(xmlPullParser.getAttributeValue(i9)));
                sb.append('\"');
            }
        }
    }

    /* renamed from: c */
    public final AbstractHttpOverXmpp.C5640b m22539c(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        String text = null;
        boolean z8 = false;
        while (!z8) {
            int next = xmlPullParser.next();
            if (next == 3) {
                if (!xmlPullParser.getName().equals("base64")) {
                    throw new IllegalArgumentException("unexpected end tag of: " + xmlPullParser.getName());
                }
                z8 = true;
            } else {
                if (next != 4) {
                    throw new IllegalArgumentException("unexpected eventType: " + next);
                }
                text = xmlPullParser.getText();
            }
        }
        return new AbstractHttpOverXmpp.C5640b(text);
    }

    /* renamed from: d */
    public final AbstractHttpOverXmpp.C5641c m22540d(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        AbstractHttpOverXmpp.C5641c c5641c = new AbstractHttpOverXmpp.C5641c(xmlPullParser.getAttributeValue("", "streamId"));
        for (boolean z8 = false; !z8; z8 = true) {
            int next = xmlPullParser.next();
            if (next != 3) {
                throw new IllegalArgumentException("unexpected event type: " + next);
            }
            if (!xmlPullParser.getName().equals("chunkedBase64")) {
                throw new IllegalArgumentException("unexpected end tag: " + xmlPullParser.getName());
            }
        }
        return c5641c;
    }

    /* renamed from: e */
    public final AbstractHttpOverXmpp.C5642d m22541e(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        AbstractHttpOverXmpp.InterfaceC5643e interfaceC5643eM22545i = null;
        boolean z8 = false;
        while (!z8) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals(MimeTypes.BASE_TYPE_TEXT)) {
                    interfaceC5643eM22545i = m22545i(xmlPullParser);
                } else if (xmlPullParser.getName().equals("base64")) {
                    interfaceC5643eM22545i = m22539c(xmlPullParser);
                } else if (xmlPullParser.getName().equals("chunkedBase64")) {
                    interfaceC5643eM22545i = m22540d(xmlPullParser);
                } else if (xmlPullParser.getName().equals("xml")) {
                    interfaceC5643eM22545i = m22546j(xmlPullParser);
                } else {
                    if (!xmlPullParser.getName().equals("ibb")) {
                        if (xmlPullParser.getName().equals("sipub")) {
                            throw new UnsupportedOperationException("sipub is not supported yet");
                        }
                        if (xmlPullParser.getName().equals("jingle")) {
                            throw new UnsupportedOperationException("jingle is not supported yet");
                        }
                        throw new IllegalArgumentException("unsupported child tag: " + xmlPullParser.getName());
                    }
                    interfaceC5643eM22545i = m22544h(xmlPullParser);
                }
            } else if (next == 3 && xmlPullParser.getName().equals("data")) {
                z8 = true;
            }
        }
        return new AbstractHttpOverXmpp.C5642d(interfaceC5643eM22545i);
    }

    /* renamed from: f */
    public final C6794b m22542f(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        HeaderProvider headerProvider = new HeaderProvider();
        HashSet hashSet = new HashSet();
        boolean z8 = false;
        while (!z8) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("header")) {
                    hashSet.add((C6793a) headerProvider.mo181a(xmlPullParser));
                }
            } else if (next == 3 && xmlPullParser.getName().equals("headers")) {
                z8 = true;
            }
        }
        return new C6794b(hashSet);
    }

    /* renamed from: g */
    public void m22543g(XmlPullParser xmlPullParser, String str, AbstractHttpOverXmpp.AbstractC5639a abstractC5639a) throws XmlPullParserException, IOException {
        boolean z8 = false;
        while (!z8) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("headers")) {
                    abstractC5639a.m22523d(m22542f(xmlPullParser));
                } else {
                    if (!xmlPullParser.getName().endsWith("data")) {
                        throw new IllegalArgumentException("unexpected tag:" + xmlPullParser.getName() + "'");
                    }
                    abstractC5639a.m22522c(m22541e(xmlPullParser));
                }
            } else if (next == 3 && xmlPullParser.getName().equals(str)) {
                z8 = true;
            }
        }
    }

    /* renamed from: h */
    public final AbstractHttpOverXmpp.C5644f m22544h(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        AbstractHttpOverXmpp.C5644f c5644f = new AbstractHttpOverXmpp.C5644f(xmlPullParser.getAttributeValue("", "sid"));
        for (boolean z8 = false; !z8; z8 = true) {
            int next = xmlPullParser.next();
            if (next != 3) {
                throw new IllegalArgumentException("unexpected event type: " + next);
            }
            if (!xmlPullParser.getName().equals("ibb")) {
                throw new IllegalArgumentException("unexpected end tag: " + xmlPullParser.getName());
            }
        }
        return c5644f;
    }

    /* renamed from: i */
    public final AbstractHttpOverXmpp.C5645g m22545i(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        String text = null;
        boolean z8 = false;
        while (!z8) {
            int next = xmlPullParser.next();
            if (next == 3) {
                if (!xmlPullParser.getName().equals(MimeTypes.BASE_TYPE_TEXT)) {
                    throw new IllegalArgumentException("unexpected end tag of: " + xmlPullParser.getName());
                }
                z8 = true;
            } else {
                if (next != 4) {
                    throw new IllegalArgumentException("unexpected eventType: " + next);
                }
                text = xmlPullParser.getText();
            }
        }
        return new AbstractHttpOverXmpp.C5645g(text);
    }

    /* renamed from: j */
    public final AbstractHttpOverXmpp.C5646h m22546j(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        StringBuilder sb = new StringBuilder();
        boolean z8 = false;
        while (true) {
            boolean z9 = true;
            while (!z8) {
                int next = xmlPullParser.next();
                if (next == 3 && xmlPullParser.getName().equals("xml")) {
                    z8 = true;
                } else if (next == 2) {
                    if (!z9) {
                        sb.append('>');
                    }
                    sb.append('<');
                    sb.append(xmlPullParser.getName());
                    m22538a(xmlPullParser, sb);
                    z9 = false;
                } else if (next == 3) {
                    if (z9) {
                        sb.append("</");
                        sb.append(xmlPullParser.getName());
                        sb.append('>');
                    }
                } else {
                    if (next != 4) {
                        throw new IllegalArgumentException("unexpected eventType: " + next);
                    }
                    if (!z9) {
                        sb.append('>');
                        z9 = true;
                    }
                    sb.append(C5616j.m22341f(xmlPullParser.getText()));
                }
            }
            return new AbstractHttpOverXmpp.C5646h(sb.toString());
            sb.append("/>");
        }
    }
}
