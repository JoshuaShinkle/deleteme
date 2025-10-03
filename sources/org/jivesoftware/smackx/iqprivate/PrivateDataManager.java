package org.jivesoftware.smackx.iqprivate;

import com.google.android.gms.actions.SearchIntents;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import java.util.WeakHashMap;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.AbstractC5586IQ;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p008a7.InterfaceC0049c;
import p169p7.C6114a;
import p169p7.InterfaceC6115b;
import p178q7.InterfaceC6177a;
import p222v6.AbstractC6491d;

/* loaded from: classes.dex */
public class PrivateDataManager extends AbstractC6491d {

    /* renamed from: b */
    public static final Map<XMPPConnection, PrivateDataManager> f19739b = new WeakHashMap();

    /* renamed from: c */
    public static Map<String, InterfaceC6177a> f19740c = new Hashtable();

    /* renamed from: org.jivesoftware.smackx.iqprivate.PrivateDataManager$1 */
    class C56541 extends AbstractC5586IQ {

        /* renamed from: p */
        public final /* synthetic */ String f19741p;

        /* renamed from: q */
        public final /* synthetic */ String f19742q;

        @Override // org.jivesoftware.smack.packet.AbstractC5586IQ
        /* renamed from: G, reason: merged with bridge method [inline-methods] */
        public String mo9675y() {
            return "<query xmlns=\"jabber:iq:private\"><" + this.f19741p + " xmlns=\"" + this.f19742q + "\"/></query>";
        }
    }

    /* renamed from: org.jivesoftware.smackx.iqprivate.PrivateDataManager$2 */
    class C56552 extends AbstractC5586IQ {

        /* renamed from: p */
        public final /* synthetic */ InterfaceC6115b f19743p;

        @Override // org.jivesoftware.smack.packet.AbstractC5586IQ
        /* renamed from: G, reason: merged with bridge method [inline-methods] */
        public String mo9675y() {
            return "<query xmlns=\"jabber:iq:private\">" + this.f19743p.mo23452a() + "</query>";
        }
    }

    public static class PrivateDataIQProvider implements InterfaceC0049c {
        @Override // p008a7.InterfaceC0049c
        /* renamed from: b */
        public AbstractC5586IQ mo180b(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
            InterfaceC6115b interfaceC6115bM23648a = null;
            boolean z8 = false;
            while (!z8) {
                int next = xmlPullParser.next();
                if (next == 2) {
                    String name = xmlPullParser.getName();
                    String namespace = xmlPullParser.getNamespace();
                    InterfaceC6177a interfaceC6177aM22559c = PrivateDataManager.m22559c(name, namespace);
                    if (interfaceC6177aM22559c != null) {
                        interfaceC6115bM23648a = interfaceC6177aM22559c.m23648a(xmlPullParser);
                    } else {
                        C6114a c6114a = new C6114a(name, namespace);
                        boolean z9 = false;
                        while (!z9) {
                            int next2 = xmlPullParser.next();
                            if (next2 == 2) {
                                String name2 = xmlPullParser.getName();
                                if (xmlPullParser.isEmptyElementTag()) {
                                    c6114a.m23455d(name2, "");
                                } else if (xmlPullParser.next() == 4) {
                                    c6114a.m23455d(name2, xmlPullParser.getText());
                                }
                            } else if (next2 == 3 && xmlPullParser.getName().equals(name)) {
                                z9 = true;
                            }
                        }
                        interfaceC6115bM23648a = c6114a;
                    }
                } else if (next == 3 && xmlPullParser.getName().equals(SearchIntents.EXTRA_QUERY)) {
                    z8 = true;
                }
            }
            return new PrivateDataResult(interfaceC6115bM23648a);
        }
    }

    public static class PrivateDataResult extends AbstractC5586IQ {

        /* renamed from: p */
        public InterfaceC6115b f19744p;

        public PrivateDataResult(InterfaceC6115b interfaceC6115b) {
            this.f19744p = interfaceC6115b;
        }

        @Override // org.jivesoftware.smack.packet.AbstractC5586IQ
        /* renamed from: G, reason: merged with bridge method [inline-methods] */
        public String mo9675y() {
            StringBuilder sb = new StringBuilder();
            sb.append("<query xmlns=\"jabber:iq:private\">");
            InterfaceC6115b interfaceC6115b = this.f19744p;
            if (interfaceC6115b != null) {
                sb.append(interfaceC6115b.mo23452a());
            }
            sb.append("</query>");
            return sb.toString();
        }
    }

    /* renamed from: c */
    public static InterfaceC6177a m22559c(String str, String str2) {
        return f19740c.get(m22560d(str, str2));
    }

    /* renamed from: d */
    public static String m22560d(String str, String str2) {
        return "<" + str + "/><" + str2 + "/>";
    }
}
