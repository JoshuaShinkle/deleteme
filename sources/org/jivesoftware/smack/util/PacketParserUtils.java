package org.jivesoftware.smack.util;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.AbstractC5586IQ;
import org.jivesoftware.smack.packet.AbstractC5594b;
import org.jivesoftware.smack.packet.Bind;
import org.jivesoftware.smack.packet.C5593a;
import org.jivesoftware.smack.packet.C5596d;
import org.jivesoftware.smack.packet.CLResumed;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Registration;
import org.jivesoftware.smack.packet.RosterPacket;
import org.jivesoftware.smack.packet.StreamMgmt;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smack.sasl.SASLMechanism;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import p008a7.C0054h;
import p008a7.InterfaceC0049c;
import p008a7.InterfaceC0051e;

/* loaded from: classes.dex */
public class PacketParserUtils {

    /* renamed from: a */
    public static final Logger f19424a = Logger.getLogger(PacketParserUtils.class.getName());

    public static class UnparsedResultIQ extends AbstractC5586IQ {

        /* renamed from: p */
        public final String f19425p;

        public UnparsedResultIQ(String str) {
            this.f19425p = str;
        }

        @Override // org.jivesoftware.smack.packet.AbstractC5586IQ
        /* renamed from: G, reason: merged with bridge method [inline-methods] */
        public String mo9675y() {
            return this.f19425p;
        }
    }

    /* renamed from: a */
    public static String m22238a(XmlPullParser xmlPullParser, int i9) throws XmlPullParserException, IOException {
        C5618l c5618l = new C5618l();
        int eventType = xmlPullParser.getEventType();
        boolean z8 = false;
        while (true) {
            if (eventType == 2) {
                c5618l.m22364o(xmlPullParser.getName());
                String namespace = xmlPullParser.getNamespace();
                if (C5616j.m22343h(namespace)) {
                    c5618l.m22355f("xmlns", namespace);
                }
                for (int i10 = 0; i10 < xmlPullParser.getAttributeCount(); i10++) {
                    c5618l.m22355f(xmlPullParser.getAttributeName(i10), xmlPullParser.getAttributeValue(i10));
                }
                if (xmlPullParser.isEmptyElementTag()) {
                    c5618l.m22358i();
                    z8 = true;
                } else {
                    c5618l.m22370u();
                }
            } else if (eventType == 3) {
                if (z8) {
                    z8 = false;
                } else {
                    c5618l.m22356g(xmlPullParser.getName());
                }
                if (xmlPullParser.getDepth() <= i9) {
                    return c5618l.toString();
                }
            } else if (eventType == 4) {
                c5618l.append(C5616j.m22341f(xmlPullParser.getText()));
            }
            eventType = xmlPullParser.next();
        }
    }

    /* renamed from: b */
    public static Object m22239b(Class<?> cls, String str) {
        if (cls.isAssignableFrom(String.class)) {
            return str;
        }
        if (cls.isAssignableFrom(Boolean.TYPE)) {
            return Boolean.valueOf(str);
        }
        if (cls.isAssignableFrom(Integer.TYPE)) {
            return Integer.valueOf(str);
        }
        if (cls.isAssignableFrom(Long.TYPE)) {
            return Long.valueOf(str);
        }
        if (cls.isAssignableFrom(Float.TYPE)) {
            return Float.valueOf(str);
        }
        if (cls.isAssignableFrom(Double.TYPE)) {
            return Double.valueOf(str);
        }
        if (cls.isAssignableFrom(Class.class)) {
            return Class.forName(str);
        }
        return null;
    }

    /* renamed from: c */
    public static String m22240c(XmlPullParser xmlPullParser) {
        for (int i9 = 0; i9 < xmlPullParser.getAttributeCount(); i9++) {
            String attributeName = xmlPullParser.getAttributeName(i9);
            if ("xml:lang".equals(attributeName) || ("lang".equals(attributeName) && "xml".equals(xmlPullParser.getAttributePrefix(i9)))) {
                return xmlPullParser.getAttributeValue(i9);
            }
        }
        return null;
    }

    /* renamed from: d */
    public static XmlPullParser m22241d() throws XmlPullParserException {
        XmlPullParser xmlPullParserNewPullParser = XmlPullParserFactory.newInstance().newPullParser();
        xmlPullParserNewPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
        return xmlPullParserNewPullParser;
    }

    /* renamed from: e */
    public static CLResumed m22242e(XmlPullParser xmlPullParser) throws NumberFormatException {
        long j9;
        CLResumed cLResumed = new CLResumed();
        String attributeValue = xmlPullParser.getAttributeValue("", "status");
        String attributeValue2 = xmlPullParser.getAttributeValue("", "sessionid");
        String attributeValue3 = xmlPullParser.getAttributeValue("", "expiration");
        if (attributeValue == null) {
            attributeValue = "";
        }
        cLResumed.m22054B(attributeValue);
        cLResumed.m22053A(attributeValue2 != null ? attributeValue2 : "");
        try {
            j9 = Long.parseLong(attributeValue3);
        } catch (Exception unused) {
            j9 = 0;
        }
        cLResumed.m22062z(j9);
        return cLResumed;
    }

    /* renamed from: f */
    public static Collection<String> m22243f(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        ArrayList arrayList = new ArrayList();
        boolean z8 = false;
        while (!z8) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals(FirebaseAnalytics.Param.METHOD)) {
                    arrayList.add(xmlPullParser.nextText());
                }
            } else if (next == 3 && xmlPullParser.getName().equals("compression")) {
                z8 = true;
            }
        }
        return arrayList;
    }

    /* renamed from: g */
    public static String m22244g(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        if (xmlPullParser.isEmptyElementTag()) {
            return "";
        }
        xmlPullParser.next();
        return m22245h(xmlPullParser, xmlPullParser.getDepth());
    }

    /* renamed from: h */
    public static String m22245h(XmlPullParser xmlPullParser, int i9) throws XmlPullParserException, IOException {
        C5618l c5618l = new C5618l();
        int eventType = xmlPullParser.getEventType();
        boolean z8 = false;
        while (true) {
            if (eventType == 2) {
                c5618l.m22364o(xmlPullParser.getName());
                String namespace = xmlPullParser.getNamespace();
                if (C5616j.m22343h(namespace)) {
                    c5618l.m22355f("xmlns", namespace);
                }
                for (int i10 = 0; i10 < xmlPullParser.getAttributeCount(); i10++) {
                    c5618l.m22355f(xmlPullParser.getAttributeName(i10), xmlPullParser.getAttributeValue(i10));
                }
                if (xmlPullParser.isEmptyElementTag()) {
                    c5618l.m22358i();
                    z8 = true;
                } else {
                    c5618l.m22370u();
                }
            } else if (eventType == 3) {
                if (z8) {
                    z8 = false;
                } else {
                    c5618l.m22356g(xmlPullParser.getName());
                }
                if (xmlPullParser.getDepth() <= i9) {
                    return c5618l.toString();
                }
            } else if (eventType == 4) {
                c5618l.append(xmlPullParser.getText());
            }
            eventType = xmlPullParser.next();
        }
    }

    /* renamed from: i */
    public static String m22246i(XmlPullParser xmlPullParser) {
        return m22245h(xmlPullParser, xmlPullParser.getDepth());
    }

    /* renamed from: j */
    public static String m22247j(XmlPullParser xmlPullParser) throws XmlPullParserException {
        if (xmlPullParser.isEmptyElementTag()) {
            return "";
        }
        if (xmlPullParser.next() != 4) {
            throw new XmlPullParserException("Non-empty element tag not followed by text, while Mixed Content (XML 3.2.2) is disallowed");
        }
        String text = xmlPullParser.getText();
        if (xmlPullParser.next() == 3) {
            return text;
        }
        throw new XmlPullParserException("Non-empty element tag contains child-elements, while Mixed Content (XML 3.2.2) is disallowed");
    }

    /* renamed from: k */
    public static XMPPError m22248k(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        ArrayList arrayList = new ArrayList();
        String strNextText = null;
        String attributeValue = null;
        for (int i9 = 0; i9 < xmlPullParser.getAttributeCount(); i9++) {
            if (xmlPullParser.getAttributeName(i9).equals("type")) {
                attributeValue = xmlPullParser.getAttributeValue("", "type");
            }
        }
        boolean z8 = false;
        String str = null;
        while (!z8) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals(MimeTypes.BASE_TYPE_TEXT)) {
                    strNextText = xmlPullParser.nextText();
                } else {
                    String name = xmlPullParser.getName();
                    String namespace = xmlPullParser.getNamespace();
                    if ("urn:ietf:params:xml:ns:xmpp-stanzas".equals(namespace)) {
                        str = name;
                    } else {
                        arrayList.add(m22252o(name, namespace, xmlPullParser));
                    }
                }
            } else if (next == 3 && xmlPullParser.getName().equals(Constants.IPC_BUNDLE_KEY_SEND_ERROR)) {
                z8 = true;
            }
        }
        XMPPError.Type typeValueOf = XMPPError.Type.CANCEL;
        if (attributeValue != null) {
            try {
                typeValueOf = XMPPError.Type.valueOf(attributeValue.toUpperCase(Locale.US));
            } catch (IllegalArgumentException e9) {
                f19424a.log(Level.SEVERE, "Could not find error type for " + attributeValue.toUpperCase(Locale.US), (Throwable) e9);
            }
        }
        return new XMPPError(typeValueOf, str, strNextText, arrayList);
    }

    /* renamed from: l */
    public static AbstractC5586IQ m22249l(XmlPullParser xmlPullParser, XMPPConnection xMPPConnection) throws XmlPullParserException, IOException {
        String attributeValue = xmlPullParser.getAttributeValue("", TtmlNode.ATTR_ID);
        String attributeValue2 = xmlPullParser.getAttributeValue("", "to");
        String attributeValue3 = xmlPullParser.getAttributeValue("", Constants.MessagePayloadKeys.FROM);
        String attributeValue4 = xmlPullParser.getAttributeValue("", "cv");
        String attributeValue5 = xmlPullParser.getAttributeValue("", "empty");
        AbstractC5586IQ.a aVarM22074a = AbstractC5586IQ.a.m22074a(xmlPullParser.getAttributeValue("", "type"));
        boolean z8 = false;
        AbstractC5586IQ abstractC5586IQMo180b = null;
        XMPPError xMPPErrorM22248k = null;
        String str = null;
        while (!z8) {
            int next = xmlPullParser.next();
            if (next == 2) {
                String name = xmlPullParser.getName();
                String namespace = xmlPullParser.getNamespace();
                if (name.equals(Constants.IPC_BUNDLE_KEY_SEND_ERROR)) {
                    xMPPErrorM22248k = m22248k(xmlPullParser);
                } else if (name.equals(SearchIntents.EXTRA_QUERY) && namespace.equals("jabber:iq:roster")) {
                    abstractC5586IQMo180b = m22256s(xmlPullParser);
                } else if (name.equals(SearchIntents.EXTRA_QUERY) && namespace.equals("jabber:iq:register")) {
                    abstractC5586IQMo180b = m22254q(xmlPullParser);
                } else if (name.equals("bind") && namespace.equals("urn:ietf:params:xml:ns:xmpp-bind")) {
                    abstractC5586IQMo180b = m22255r(xmlPullParser);
                } else if (name.equals(TtmlNode.END)) {
                    String strM22247j = m22247j(xmlPullParser);
                    if (strM22247j != null && strM22247j.length() > 0) {
                        str = strM22247j;
                    }
                } else {
                    Object objM188d = C0054h.m188d(name, namespace);
                    if (objM188d != null) {
                        if (objM188d instanceof InterfaceC0049c) {
                            abstractC5586IQMo180b = ((InterfaceC0049c) objM188d).mo180b(xmlPullParser);
                        } else if (objM188d instanceof Class) {
                            abstractC5586IQMo180b = (AbstractC5586IQ) m22260w(name, (Class) objM188d, xmlPullParser);
                        }
                    } else if (AbstractC5586IQ.a.f19233d == aVarM22074a) {
                        abstractC5586IQMo180b = new UnparsedResultIQ(m22244g(xmlPullParser));
                    }
                }
            } else if (next == 3 && xmlPullParser.getName().equals("iq")) {
                z8 = true;
            }
        }
        if (abstractC5586IQMo180b == null) {
            if (AbstractC5586IQ.a.f19231b == aVarM22074a || AbstractC5586IQ.a.f19232c == aVarM22074a) {
                AbstractC5586IQ abstractC5586IQ = new AbstractC5586IQ() { // from class: org.jivesoftware.smack.util.PacketParserUtils.1
                    @Override // org.jivesoftware.smack.packet.AbstractC5586IQ
                    /* renamed from: G, reason: merged with bridge method [inline-methods] */
                    public String mo9675y() {
                        return null;
                    }
                };
                abstractC5586IQ.m22166s(attributeValue);
                abstractC5586IQ.m22167t(attributeValue3);
                abstractC5586IQ.m22165r(attributeValue2);
                abstractC5586IQ.m22070F(AbstractC5586IQ.a.f19234e);
                abstractC5586IQ.m22164q(new XMPPError(XMPPError.C5591a.f19314f));
                xMPPConnection.m21979P(abstractC5586IQ);
                return null;
            }
            abstractC5586IQMo180b = new AbstractC5586IQ() { // from class: org.jivesoftware.smack.util.PacketParserUtils.2
                @Override // org.jivesoftware.smack.packet.AbstractC5586IQ
                /* renamed from: G, reason: merged with bridge method [inline-methods] */
                public String mo9675y() {
                    return null;
                }
            };
        }
        abstractC5586IQMo180b.m22166s(attributeValue);
        abstractC5586IQMo180b.m22167t(attributeValue2);
        abstractC5586IQMo180b.m22165r(attributeValue3);
        abstractC5586IQMo180b.m22070F(aVarM22074a);
        abstractC5586IQMo180b.m22067C(attributeValue4);
        abstractC5586IQMo180b.m22068D(attributeValue5);
        abstractC5586IQMo180b.m22069E(str);
        abstractC5586IQMo180b.m22164q(xMPPErrorM22248k);
        return abstractC5586IQMo180b;
    }

    /* renamed from: m */
    public static Collection<String> m22250m(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        ArrayList arrayList = new ArrayList();
        boolean z8 = false;
        while (!z8) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("mechanism")) {
                    arrayList.add(xmlPullParser.nextText());
                }
            } else if (next == 3 && xmlPullParser.getName().equals("mechanisms")) {
                z8 = true;
            }
        }
        return arrayList;
    }

    /* renamed from: n */
    public static Message m22251n(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException, NumberFormatException {
        long j9;
        long j10;
        Message message = new Message();
        String strM22238a = m22238a(xmlPullParser, xmlPullParser.getDepth());
        message.m22096V(strM22238a);
        StringReader stringReader = new StringReader(strM22238a);
        XmlPullParser xmlPullParserM22241d = m22241d();
        xmlPullParserM22241d.setInput(stringReader);
        xmlPullParserM22241d.next();
        String attributeValue = xmlPullParserM22241d.getAttributeValue("", TtmlNode.ATTR_ID);
        if (attributeValue == null) {
            attributeValue = "ID_NOT_AVAILABLE";
        }
        message.m22166s(attributeValue);
        message.m22167t(xmlPullParserM22241d.getAttributeValue("", "to"));
        message.m22165r(xmlPullParserM22241d.getAttributeValue("", Constants.MessagePayloadKeys.FROM));
        message.m22100Z(Message.Type.m22107a(xmlPullParserM22241d.getAttributeValue("", "type")));
        try {
            message.m22093S(Integer.valueOf(xmlPullParserM22241d.getAttributeValue("", "mv")).intValue());
        } catch (Exception unused) {
        }
        String attributeValue2 = xmlPullParserM22241d.getAttributeValue("", "ts");
        String strNextText = null;
        if (attributeValue2 != null && !attributeValue2.isEmpty()) {
            try {
                j10 = Long.parseLong(attributeValue2);
            } catch (Exception unused2) {
                j10 = 0;
            }
            if (j10 == 0) {
                message.m22098X(null);
            } else {
                message.m22098X(new Date(j10));
            }
        }
        String attributeValue3 = xmlPullParserM22241d.getAttributeValue("", "st");
        if (attributeValue3 != null && !attributeValue3.isEmpty()) {
            try {
                j9 = Long.parseLong(attributeValue3);
            } catch (Exception unused3) {
                j9 = 0;
            }
            if (j9 == 0) {
                message.m22095U(null);
            } else {
                message.m22095U(new Date(j9));
            }
        }
        String strM22240c = m22240c(xmlPullParserM22241d);
        String attributeValue4 = xmlPullParserM22241d.getAttributeValue("", "cv");
        if (attributeValue4 != null && !attributeValue4.isEmpty()) {
            message.m22091Q(attributeValue4);
        }
        if (strM22240c == null || "".equals(strM22240c.trim())) {
            strM22240c = AbstractC5594b.m22151d();
        } else {
            message.m22092R(strM22240c);
        }
        boolean z8 = false;
        while (true) {
            if (z8) {
                break;
            }
            int next = xmlPullParserM22241d.next();
            if (next == 2) {
                String name = xmlPullParserM22241d.getName();
                String namespace = xmlPullParserM22241d.getNamespace();
                if (name.equals("subject")) {
                    String strM22240c2 = m22240c(xmlPullParserM22241d);
                    if (strM22240c2 == null) {
                        strM22240c2 = strM22240c;
                    }
                    String strM22247j = m22247j(xmlPullParserM22241d);
                    if (message.m22083I(strM22240c2) == null) {
                        message.m22103w(strM22240c2, strM22247j);
                    }
                } else if (name.equals(TtmlNode.TAG_BODY)) {
                    String strM22240c3 = m22240c(xmlPullParserM22241d);
                    if (strM22240c3 == null) {
                        strM22240c3 = strM22240c;
                    }
                    String strM22247j2 = m22247j(xmlPullParserM22241d);
                    if (message.m22075A(strM22240c3) == null) {
                        message.m22102v(strM22240c3, strM22247j2);
                    }
                } else if (name.equals("thread")) {
                    if (strNextText == null) {
                        strNextText = xmlPullParserM22241d.nextText();
                    }
                } else if (name.equals(Constants.IPC_BUNDLE_KEY_SEND_ERROR)) {
                    message.m22164q(m22248k(xmlPullParserM22241d));
                } else {
                    InterfaceC5595c interfaceC5595cM22252o = m22252o(name, namespace, xmlPullParserM22241d);
                    if (interfaceC5595cM22252o instanceof C5593a) {
                        message.m22094T(true);
                    } else {
                        message.m22154b(interfaceC5595cM22252o);
                    }
                }
            } else if (next == 3) {
                if (xmlPullParserM22241d.getName().equals("message")) {
                    z8 = true;
                }
            } else if (next == 1) {
                f19424a.warning("[PacketParserUtils] Parsing end document tag is not expect");
                break;
            }
        }
        message.m22099Y(strNextText);
        return message;
    }

    /* renamed from: o */
    public static InterfaceC5595c m22252o(String str, String str2, XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Object objM187c = C0054h.m187c(str, str2);
        if (objM187c != null) {
            if (objM187c instanceof InterfaceC0051e) {
                return ((InterfaceC0051e) objM187c).mo181a(xmlPullParser);
            }
            if (objM187c instanceof Class) {
                return (InterfaceC5595c) m22260w(str, (Class) objM187c, xmlPullParser);
            }
        }
        C5593a c5593a = new C5593a(str, str2);
        boolean z8 = false;
        while (!z8) {
            int next = xmlPullParser.next();
            if (next == 2) {
                String name = xmlPullParser.getName();
                if (xmlPullParser.isEmptyElementTag()) {
                    c5593a.m22150e(name, "");
                } else if (xmlPullParser.next() == 4) {
                    c5593a.m22150e(name, xmlPullParser.getText());
                }
            } else if (next == 3 && xmlPullParser.getName().equals(str)) {
                z8 = true;
            }
        }
        return c5593a;
    }

    /* renamed from: p */
    public static Presence m22253p(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Presence.Type typeValueOf = Presence.Type.available;
        String attributeValue = xmlPullParser.getAttributeValue("", "type");
        if (attributeValue != null && !attributeValue.equals("")) {
            try {
                typeValueOf = Presence.Type.valueOf(attributeValue);
            } catch (IllegalArgumentException unused) {
                f19424a.warning("Found invalid presence type " + attributeValue);
            }
        }
        Presence presence = new Presence(typeValueOf);
        presence.m22167t(xmlPullParser.getAttributeValue("", "to"));
        presence.m22165r(xmlPullParser.getAttributeValue("", Constants.MessagePayloadKeys.FROM));
        String attributeValue2 = xmlPullParser.getAttributeValue("", TtmlNode.ATTR_ID);
        presence.m22166s(attributeValue2 == null ? "ID_NOT_AVAILABLE" : attributeValue2);
        String strM22240c = m22240c(xmlPullParser);
        if (strM22240c != null && !"".equals(strM22240c.trim())) {
            presence.m22123z(strM22240c);
        }
        if (attributeValue2 == null) {
            attributeValue2 = "ID_NOT_AVAILABLE";
        }
        presence.m22166s(attributeValue2);
        boolean z8 = false;
        while (!z8) {
            int next = xmlPullParser.next();
            if (next == 2) {
                String name = xmlPullParser.getName();
                String namespace = xmlPullParser.getNamespace();
                if (name.equals("status")) {
                    presence.m22116C(xmlPullParser.nextText());
                } else if (name.equals(Constants.FirelogAnalytics.PARAM_PRIORITY)) {
                    try {
                        presence.m22115B(Integer.parseInt(xmlPullParser.nextText()));
                    } catch (NumberFormatException unused2) {
                    } catch (IllegalArgumentException unused3) {
                        presence.m22115B(0);
                    }
                } else if (name.equals("show")) {
                    String strNextText = xmlPullParser.nextText();
                    try {
                        presence.m22114A(Presence.Mode.valueOf(strNextText));
                    } catch (IllegalArgumentException unused4) {
                        f19424a.warning("Found invalid presence mode " + strNextText);
                    }
                } else if (name.equals(Constants.IPC_BUNDLE_KEY_SEND_ERROR)) {
                    presence.m22164q(m22248k(xmlPullParser));
                } else {
                    try {
                        presence.m22154b(m22252o(name, namespace, xmlPullParser));
                    } catch (Exception unused5) {
                        f19424a.warning("Failed to parse extension packet in Presence packet.");
                    }
                }
            } else if (next == 3 && xmlPullParser.getName().equals("presence")) {
                z8 = true;
            }
        }
        return presence;
    }

    /* renamed from: q */
    public static Registration m22254q(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Registration registration = new Registration();
        HashMap map = null;
        boolean z8 = false;
        while (!z8) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getNamespace().equals("jabber:iq:register")) {
                    String name = xmlPullParser.getName();
                    if (map == null) {
                        map = new HashMap();
                    }
                    String text = xmlPullParser.next() == 4 ? xmlPullParser.getText() : "";
                    if (name.equals("instructions")) {
                        registration.m22126I(text);
                    } else {
                        map.put(name, text);
                    }
                } else {
                    registration.m22154b(m22252o(xmlPullParser.getName(), xmlPullParser.getNamespace(), xmlPullParser));
                }
            } else if (next == 3 && xmlPullParser.getName().equals(SearchIntents.EXTRA_QUERY)) {
                z8 = true;
            }
        }
        registration.m22125H(map);
        return registration;
    }

    /* renamed from: r */
    public static Bind m22255r(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Bind bind = new Bind();
        boolean z8 = false;
        while (!z8) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("resource")) {
                    bind.m22052I(xmlPullParser.nextText());
                } else if (xmlPullParser.getName().equals("jid")) {
                    bind.m22051H(xmlPullParser.nextText());
                }
            } else if (next == 3 && xmlPullParser.getName().equals("bind")) {
                z8 = true;
            }
        }
        return bind;
    }

    /* renamed from: s */
    public static RosterPacket m22256s(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        String strNextText;
        RosterPacket rosterPacket = new RosterPacket();
        rosterPacket.m22128H(xmlPullParser.getAttributeValue("", "ver"));
        boolean z8 = false;
        RosterPacket.C5590a c5590a = null;
        while (!z8) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("item")) {
                    RosterPacket.C5590a c5590a2 = new RosterPacket.C5590a(xmlPullParser.getAttributeValue("", "jid"), xmlPullParser.getAttributeValue("", AppMeasurementSdk.ConditionalUserProperty.NAME));
                    c5590a2.m22131b(RosterPacket.ItemStatus.m22129a(xmlPullParser.getAttributeValue("", "ask")));
                    String attributeValue = xmlPullParser.getAttributeValue("", "subscription");
                    if (attributeValue == null) {
                        attributeValue = "none";
                    }
                    c5590a2.m22132c(RosterPacket.ItemType.valueOf(attributeValue));
                    c5590a = c5590a2;
                } else if (xmlPullParser.getName().equals("group") && c5590a != null && (strNextText = xmlPullParser.nextText()) != null && strNextText.trim().length() > 0) {
                    c5590a.m22130a(strNextText);
                }
            } else if (next == 3) {
                if (xmlPullParser.getName().equals("item")) {
                    rosterPacket.m22127G(c5590a);
                }
                if (xmlPullParser.getName().equals(SearchIntents.EXTRA_QUERY)) {
                    z8 = true;
                }
            }
        }
        return rosterPacket;
    }

    /* renamed from: t */
    public static SASLMechanism.SASLFailure m22257t(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        String name = null;
        boolean z8 = false;
        while (!z8) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (!xmlPullParser.getName().equals("failure")) {
                    name = xmlPullParser.getName();
                }
            } else if (next == 3 && xmlPullParser.getName().equals("failure")) {
                z8 = true;
            }
        }
        return new SASLMechanism.SASLFailure(name);
    }

    /* renamed from: u */
    public static C5596d m22258u(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int depth = xmlPullParser.getDepth();
        boolean z8 = false;
        String str = null;
        String text = null;
        while (!z8) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if ("urn:ietf:params:xml:ns:xmpp-streams".equals(xmlPullParser.getNamespace())) {
                    String name = xmlPullParser.getName();
                    if (!name.equals(MimeTypes.BASE_TYPE_TEXT) || xmlPullParser.isEmptyElementTag()) {
                        str = name;
                    } else {
                        xmlPullParser.next();
                        text = xmlPullParser.getText();
                    }
                }
            } else if (next == 3 && depth == xmlPullParser.getDepth()) {
                z8 = true;
            }
        }
        return new C5596d(str, text);
    }

    /* renamed from: v */
    public static StreamMgmt m22259v(XmlPullParser xmlPullParser) throws NumberFormatException {
        long j9;
        StreamMgmt streamMgmt = new StreamMgmt();
        String attributeValue = xmlPullParser.getAttributeValue("", TtmlNode.ATTR_ID);
        if (attributeValue == null) {
            attributeValue = "ID_NOT_AVAILABLE";
        }
        streamMgmt.m22166s(attributeValue);
        String attributeValue2 = xmlPullParser.getAttributeValue("", "ts");
        if (attributeValue2 != null && !attributeValue2.isEmpty()) {
            try {
                j9 = Long.parseLong(attributeValue2);
            } catch (Exception unused) {
                j9 = 0;
            }
            if (j9 == 0) {
                streamMgmt.m22139z(null);
            } else {
                streamMgmt.m22139z(new Date(j9));
            }
        }
        String attributeValue3 = xmlPullParser.getAttributeValue("", "status");
        if (attributeValue3 != null && !attributeValue3.isEmpty()) {
            streamMgmt.m22138y(attributeValue3);
        }
        return streamMgmt;
    }

    /* renamed from: w */
    public static Object m22260w(String str, Class<?> cls, XmlPullParser xmlPullParser) throws XmlPullParserException, IllegalAccessException, InstantiationException, IOException, IllegalArgumentException, InvocationTargetException {
        Object objNewInstance = cls.newInstance();
        boolean z8 = false;
        while (!z8) {
            int next = xmlPullParser.next();
            if (next == 2) {
                String name = xmlPullParser.getName();
                String strNextText = xmlPullParser.nextText();
                Class<?> returnType = objNewInstance.getClass().getMethod("get" + Character.toUpperCase(name.charAt(0)) + name.substring(1), new Class[0]).getReturnType();
                Object objM22239b = m22239b(returnType, strNextText);
                objNewInstance.getClass().getMethod("set" + Character.toUpperCase(name.charAt(0)) + name.substring(1), returnType).invoke(objNewInstance, objM22239b);
            } else if (next == 3 && xmlPullParser.getName().equals(str)) {
                z8 = true;
            }
        }
        return objNewInstance;
    }
}
