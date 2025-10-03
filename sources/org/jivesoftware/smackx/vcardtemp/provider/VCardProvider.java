package org.jivesoftware.smackx.vcardtemp.provider;

import com.google.zxing.client.android.Intents;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import org.jivesoftware.smack.packet.AbstractC5586IQ;
import org.jivesoftware.smack.util.C5616j;
import org.jivesoftware.smackx.vcardtemp.packet.VCard;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p008a7.InterfaceC0049c;

/* loaded from: classes.dex */
public class VCardProvider implements InterfaceC0049c {

    /* renamed from: a */
    public static final Logger f19949a = Logger.getLogger(VCardProvider.class.getName());

    /* renamed from: org.jivesoftware.smackx.vcardtemp.provider.VCardProvider$a */
    public static class C5676a {

        /* renamed from: a */
        public final VCard f19950a;

        /* renamed from: b */
        public final Document f19951b;

        public C5676a(VCard vCard, Document document) {
            this.f19950a = vCard;
            this.f19951b = document;
        }

        /* renamed from: a */
        public final void m22795a(StringBuilder sb, Node node) throws DOMException {
            NodeList childNodes = node.getChildNodes();
            for (int i9 = 0; i9 < childNodes.getLength(); i9++) {
                Node nodeItem = childNodes.item(i9);
                String nodeValue = nodeItem.getNodeValue();
                if (nodeValue != null) {
                    sb.append(nodeValue);
                }
                m22795a(sb, nodeItem);
            }
        }

        /* renamed from: b */
        public final String m22796b(String str) {
            NodeList elementsByTagName = this.f19951b.getElementsByTagName(str);
            if (elementsByTagName == null || elementsByTagName.getLength() != 1) {
                return null;
            }
            return m22797c(elementsByTagName.item(0));
        }

        /* renamed from: c */
        public final String m22797c(Node node) throws DOMException {
            StringBuilder sb = new StringBuilder();
            m22795a(sb, node);
            return sb.toString();
        }

        /* renamed from: d */
        public void m22798d() throws DOMException {
            this.f19950a.m22768j0(m22796b("GIVEN"));
            this.f19950a.m22769k0(m22796b("FAMILY"));
            this.f19950a.m22770l0(m22796b("MIDDLE"));
            m22803i();
            m22801g();
            this.f19950a.m22771m0(m22796b("ORGNAME"));
            this.f19950a.m22772n0(m22796b("ORGUNIT"));
            m22804j();
            m22802h();
            m22800f();
        }

        /* renamed from: e */
        public final boolean m22799e(String str) {
            return "HOME".equals(str) || "WORK".equals(str);
        }

        /* renamed from: f */
        public final void m22800f() {
            NodeList elementsByTagName = this.f19951b.getElementsByTagName("ADR");
            if (elementsByTagName == null) {
                return;
            }
            for (int i9 = 0; i9 < elementsByTagName.getLength(); i9++) {
                Element element = (Element) elementsByTagName.item(i9);
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                NodeList childNodes = element.getChildNodes();
                String str = null;
                for (int i10 = 0; i10 < childNodes.getLength(); i10++) {
                    Node nodeItem = childNodes.item(i10);
                    if (nodeItem.getNodeType() == 1) {
                        String nodeName = nodeItem.getNodeName();
                        if (m22799e(nodeName)) {
                            str = nodeName;
                        } else {
                            arrayList.add(nodeName);
                            arrayList2.add(m22797c(nodeItem));
                        }
                    }
                }
                for (int i11 = 0; i11 < arrayList2.size(); i11++) {
                    if ("HOME".equals(str)) {
                        this.f19950a.m22761c0((String) arrayList.get(i11), (String) arrayList2.get(i11));
                    } else {
                        this.f19950a.m22762d0((String) arrayList.get(i11), (String) arrayList2.get(i11));
                    }
                }
            }
        }

        /* renamed from: g */
        public final void m22801g() {
            NodeList elementsByTagName = this.f19951b.getElementsByTagName("USERID");
            if (elementsByTagName == null) {
                return;
            }
            for (int i9 = 0; i9 < elementsByTagName.getLength(); i9++) {
                Element element = (Element) elementsByTagName.item(i9);
                if ("WORK".equals(element.getParentNode().getFirstChild().getNodeName())) {
                    this.f19950a.m22765g0(m22797c(element));
                } else {
                    this.f19950a.m22764f0(m22797c(element));
                }
            }
        }

        /* renamed from: h */
        public final void m22802h() throws DOMException {
            NodeList elementsByTagName = this.f19951b.getElementsByTagName("TEL");
            if (elementsByTagName == null) {
                return;
            }
            for (int i9 = 0; i9 < elementsByTagName.getLength(); i9++) {
                NodeList childNodes = elementsByTagName.item(i9).getChildNodes();
                String strM22797c = null;
                String str = null;
                String str2 = null;
                for (int i10 = 0; i10 < childNodes.getLength(); i10++) {
                    Node nodeItem = childNodes.item(i10);
                    if (nodeItem.getNodeType() == 1) {
                        String nodeName = nodeItem.getNodeName();
                        if ("NUMBER".equals(nodeName)) {
                            strM22797c = m22797c(nodeItem);
                        } else if (m22799e(nodeName)) {
                            str2 = nodeName;
                        } else {
                            str = nodeName;
                        }
                    }
                }
                if (strM22797c != null) {
                    if (str == null) {
                        str = "VOICE";
                    }
                    if ("HOME".equals(str2)) {
                        this.f19950a.m22773o0(str, strM22797c);
                    } else {
                        this.f19950a.m22774p0(str, strM22797c);
                    }
                }
            }
        }

        /* renamed from: i */
        public final void m22803i() throws DOMException {
            NodeList elementsByTagName = this.f19951b.getElementsByTagName("PHOTO");
            if (elementsByTagName.getLength() != 1) {
                return;
            }
            NodeList childNodes = elementsByTagName.item(0).getChildNodes();
            int length = childNodes.getLength();
            ArrayList<Node> arrayList = new ArrayList(length);
            for (int i9 = 0; i9 < length; i9++) {
                arrayList.add(childNodes.item(i9));
            }
            String str = null;
            String str2 = null;
            for (Node node : arrayList) {
                String nodeName = node.getNodeName();
                String textContent = node.getTextContent();
                if (nodeName.equals("BINVAL")) {
                    str = textContent;
                } else if (nodeName.equals(Intents.WifiConnect.TYPE)) {
                    str2 = textContent;
                }
            }
            if (str == null || str2 == null) {
                return;
            }
            this.f19950a.m22763e0(str, str2);
        }

        /* renamed from: j */
        public final void m22804j() {
            NodeList childNodes = this.f19951b.getDocumentElement().getChildNodes();
            for (int i9 = 0; i9 < childNodes.getLength(); i9++) {
                Node nodeItem = childNodes.item(i9);
                if (nodeItem instanceof Element) {
                    Element element = (Element) nodeItem;
                    String nodeName = element.getNodeName();
                    if (element.getChildNodes().getLength() == 0) {
                        this.f19950a.m22766h0(nodeName, "");
                    } else if (element.getChildNodes().getLength() == 1 && (element.getChildNodes().item(0) instanceof Text)) {
                        this.f19950a.m22766h0(nodeName, m22797c(element));
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public static VCard m22794a(String str) throws DOMException {
        VCard vCard = new VCard();
        new C5676a(vCard, DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(str.getBytes("UTF-8")))).m22798d();
        return vCard;
    }

    @Override // p008a7.InterfaceC0049c
    /* renamed from: b */
    public AbstractC5586IQ mo180b(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        StringBuilder sb = new StringBuilder();
        try {
            int eventType = xmlPullParser.getEventType();
            while (true) {
                if (eventType == 2) {
                    sb.append('<');
                    sb.append(xmlPullParser.getName());
                    sb.append('>');
                } else if (eventType == 3) {
                    sb.append("</");
                    sb.append(xmlPullParser.getName());
                    sb.append('>');
                } else if (eventType == 4) {
                    sb.append(C5616j.m22341f(xmlPullParser.getText()));
                }
                if (eventType == 3) {
                    if ("vCard".equals(xmlPullParser.getName())) {
                        break;
                    }
                }
                eventType = xmlPullParser.next();
            }
        } catch (IOException e9) {
            f19949a.log(Level.SEVERE, "Exception parsing VCard", (Throwable) e9);
        } catch (XmlPullParserException e10) {
            f19949a.log(Level.SEVERE, "Exception parsing VCard", (Throwable) e10);
        }
        return m22794a(sb.toString());
    }
}
