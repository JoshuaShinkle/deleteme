package org.jivesoftware.smackx.search;

import com.google.android.gms.actions.SearchIntents;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.packet.AbstractC5586IQ;
import org.jivesoftware.smackx.search.C5668a;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p259z7.C6837a;
import p259z7.C6838b;

/* loaded from: classes.dex */
class SimpleUserSearch extends AbstractC5586IQ {

    /* renamed from: p */
    public C6837a f19883p;

    /* renamed from: q */
    public C5668a f19884q;

    /* renamed from: I */
    public static String m22701I(C6838b c6838b) {
        List<String> listM25554g = c6838b.m25554g();
        return listM25554g.isEmpty() ? "" : listM25554g.get(0);
    }

    @Override // org.jivesoftware.smack.packet.AbstractC5586IQ
    /* renamed from: G, reason: merged with bridge method [inline-methods] */
    public String mo9675y() {
        return "<query xmlns=\"jabber:iq:search\">" + m22703H() + "</query>";
    }

    /* renamed from: H */
    public final String m22703H() {
        StringBuilder sb = new StringBuilder();
        if (this.f19883p == null) {
            this.f19883p = C6837a.m25543c(this);
        }
        C6837a c6837a = this.f19883p;
        if (c6837a == null) {
            return "";
        }
        for (C6838b c6838b : c6837a.m25545b()) {
            String strM25555h = c6838b.m25555h();
            String strM22701I = m22701I(c6838b);
            if (strM22701I.trim().length() > 0) {
                sb.append("<");
                sb.append(strM25555h);
                sb.append(">");
                sb.append(strM22701I);
                sb.append("</");
                sb.append(strM25555h);
                sb.append(">");
            }
        }
        return sb.toString();
    }

    /* renamed from: J */
    public void m22704J(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        C5668a c5668a = new C5668a();
        c5668a.m22708a(new C5668a.a("JID", "jid", "text-single"));
        ArrayList arrayList = new ArrayList();
        boolean z8 = false;
        while (!z8) {
            if (xmlPullParser.getAttributeCount() > 0) {
                String attributeValue = xmlPullParser.getAttributeValue("", "jid");
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(attributeValue);
                arrayList.add(new C5668a.b("jid", arrayList2));
            }
            int next = xmlPullParser.next();
            if (next == 2 && xmlPullParser.getName().equals("item")) {
                arrayList = new ArrayList();
            } else if (next == 3 && xmlPullParser.getName().equals("item")) {
                c5668a.m22709b(new C5668a.c(arrayList));
            } else {
                boolean z9 = true;
                if (next == 2) {
                    String name = xmlPullParser.getName();
                    String strNextText = xmlPullParser.nextText();
                    ArrayList arrayList3 = new ArrayList();
                    arrayList3.add(strNextText);
                    arrayList.add(new C5668a.b(name, arrayList3));
                    Iterator<C5668a.a> it = c5668a.m22710c().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            z9 = false;
                            break;
                        } else if (it.next().m22711a().equals(name)) {
                            break;
                        }
                    }
                    if (!z9) {
                        c5668a.m22708a(new C5668a.a(name, name, "text-single"));
                    }
                } else if (next == 3 && xmlPullParser.getName().equals(SearchIntents.EXTRA_QUERY)) {
                    z8 = true;
                }
            }
        }
        this.f19884q = c5668a;
    }
}
