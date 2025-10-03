package org.jivesoftware.smackx.search;

import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.common.Scopes;
import java.io.IOException;
import org.jivesoftware.smack.packet.AbstractC5586IQ;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p008a7.InterfaceC0049c;
import p009a8.C0055a;
import p259z7.C6838b;

/* loaded from: classes.dex */
public class UserSearch extends AbstractC5586IQ {

    public static class Provider implements InterfaceC0049c {
        @Override // p008a7.InterfaceC0049c
        /* renamed from: b */
        public AbstractC5586IQ mo180b(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
            SimpleUserSearch simpleUserSearch = new SimpleUserSearch();
            UserSearch userSearch = null;
            boolean z8 = false;
            while (!z8) {
                int next = xmlPullParser.next();
                if (next == 2 && xmlPullParser.getName().equals("instructions")) {
                    UserSearch.m22706H(simpleUserSearch, xmlPullParser.nextText(), xmlPullParser);
                    return simpleUserSearch;
                }
                if (next == 2 && xmlPullParser.getName().equals("item")) {
                    simpleUserSearch.m22704J(xmlPullParser);
                    return simpleUserSearch;
                }
                if (next == 2 && xmlPullParser.getNamespace().equals("jabber:x:data")) {
                    userSearch = new UserSearch();
                    userSearch.m22154b(PacketParserUtils.m22252o(xmlPullParser.getName(), xmlPullParser.getNamespace(), xmlPullParser));
                } else if (next == 3 && xmlPullParser.getName().equals(SearchIntents.EXTRA_QUERY)) {
                    z8 = true;
                }
            }
            return userSearch != null ? userSearch : simpleUserSearch;
        }
    }

    /* renamed from: H */
    public static void m22706H(SimpleUserSearch simpleUserSearch, String str, XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        C0055a c0055a = new C0055a("form");
        c0055a.m202m("User Search");
        c0055a.m193d(str);
        boolean z8 = false;
        while (!z8) {
            int next = xmlPullParser.next();
            if (next == 2 && !xmlPullParser.getNamespace().equals("jabber:x:data")) {
                String name = xmlPullParser.getName();
                C6838b c6838b = new C6838b(name);
                if (name.equals("first")) {
                    c6838b.m25558k("First Name");
                } else if (name.equals("last")) {
                    c6838b.m25558k("Last Name");
                } else if (name.equals(Scopes.EMAIL)) {
                    c6838b.m25558k("Email Address");
                } else if (name.equals("nick")) {
                    c6838b.m25558k("Nickname");
                }
                c6838b.m25560m("text-single");
                c0055a.m192c(c6838b);
            } else if (next == 3) {
                if (xmlPullParser.getName().equals(SearchIntents.EXTRA_QUERY)) {
                    z8 = true;
                }
            } else if (next == 2 && xmlPullParser.getNamespace().equals("jabber:x:data")) {
                simpleUserSearch.m22154b(PacketParserUtils.m22252o(xmlPullParser.getName(), xmlPullParser.getNamespace(), xmlPullParser));
                z8 = true;
            }
        }
        if (simpleUserSearch.m22157g("x", "jabber:x:data") == null) {
            simpleUserSearch.m22154b(c0055a);
        }
    }

    @Override // org.jivesoftware.smack.packet.AbstractC5586IQ
    /* renamed from: I, reason: merged with bridge method [inline-methods] */
    public String mo9675y() {
        return "<query xmlns=\"jabber:iq:search\">" + m22159i() + "</query>";
    }
}
