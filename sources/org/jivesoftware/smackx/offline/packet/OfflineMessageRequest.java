package org.jivesoftware.smackx.offline.packet;

import com.google.firebase.remoteconfig.RemoteConfigComponent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jivesoftware.smack.packet.AbstractC5586IQ;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p008a7.InterfaceC0049c;

/* loaded from: classes.dex */
public class OfflineMessageRequest extends AbstractC5586IQ {

    /* renamed from: p */
    public List<C5660a> f19774p = new ArrayList();

    /* renamed from: q */
    public boolean f19775q = false;

    /* renamed from: r */
    public boolean f19776r = false;

    public static class Provider implements InterfaceC0049c {
        /* renamed from: a */
        public final C5660a m22619a(XmlPullParser xmlPullParser) {
            C5660a c5660a = new C5660a(xmlPullParser.getAttributeValue("", "node"));
            c5660a.m22623d(xmlPullParser.getAttributeValue("", "action"));
            c5660a.m22624e(xmlPullParser.getAttributeValue("", "jid"));
            boolean z8 = false;
            while (!z8) {
                if (xmlPullParser.next() == 3 && xmlPullParser.getName().equals("item")) {
                    z8 = true;
                }
            }
            return c5660a;
        }

        @Override // p008a7.InterfaceC0049c
        /* renamed from: b */
        public AbstractC5586IQ mo180b(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
            OfflineMessageRequest offlineMessageRequest = new OfflineMessageRequest();
            boolean z8 = false;
            while (!z8) {
                int next = xmlPullParser.next();
                if (next == 2) {
                    if (xmlPullParser.getName().equals("item")) {
                        offlineMessageRequest.m22615G(m22619a(xmlPullParser));
                    } else if (xmlPullParser.getName().equals("purge")) {
                        offlineMessageRequest.m22618J(true);
                    } else if (xmlPullParser.getName().equals(RemoteConfigComponent.FETCH_FILE_NAME)) {
                        offlineMessageRequest.m22617I(true);
                    }
                } else if (next == 3 && xmlPullParser.getName().equals("offline")) {
                    z8 = true;
                }
            }
            return offlineMessageRequest;
        }
    }

    /* renamed from: org.jivesoftware.smackx.offline.packet.OfflineMessageRequest$a */
    public static class C5660a {

        /* renamed from: a */
        public String f19777a;

        /* renamed from: b */
        public String f19778b;

        /* renamed from: c */
        public String f19779c;

        public C5660a(String str) {
            this.f19779c = str;
        }

        /* renamed from: a */
        public String m22620a() {
            return this.f19777a;
        }

        /* renamed from: b */
        public String m22621b() {
            return this.f19778b;
        }

        /* renamed from: c */
        public String m22622c() {
            return this.f19779c;
        }

        /* renamed from: d */
        public void m22623d(String str) {
            this.f19777a = str;
        }

        /* renamed from: e */
        public void m22624e(String str) {
            this.f19778b = str;
        }

        /* renamed from: f */
        public String m22625f() {
            StringBuilder sb = new StringBuilder();
            sb.append("<item");
            if (m22620a() != null) {
                sb.append(" action=\"");
                sb.append(m22620a());
                sb.append("\"");
            }
            if (m22621b() != null) {
                sb.append(" jid=\"");
                sb.append(m22621b());
                sb.append("\"");
            }
            if (m22622c() != null) {
                sb.append(" node=\"");
                sb.append(m22622c());
                sb.append("\"");
            }
            sb.append("/>");
            return sb.toString();
        }
    }

    /* renamed from: G */
    public void m22615G(C5660a c5660a) {
        synchronized (this.f19774p) {
            this.f19774p.add(c5660a);
        }
    }

    @Override // org.jivesoftware.smack.packet.AbstractC5586IQ
    /* renamed from: H, reason: merged with bridge method [inline-methods] */
    public String mo9675y() {
        StringBuilder sb = new StringBuilder();
        sb.append("<offline xmlns=\"http://jabber.org/protocol/offline\">");
        synchronized (this.f19774p) {
            for (int i9 = 0; i9 < this.f19774p.size(); i9++) {
                sb.append(this.f19774p.get(i9).m22625f());
            }
        }
        if (this.f19775q) {
            sb.append("<purge/>");
        }
        if (this.f19776r) {
            sb.append("<fetch/>");
        }
        sb.append(m22159i());
        sb.append("</offline>");
        return sb.toString();
    }

    /* renamed from: I */
    public void m22617I(boolean z8) {
        this.f19776r = z8;
    }

    /* renamed from: J */
    public void m22618J(boolean z8) {
        this.f19775q = z8;
    }
}
